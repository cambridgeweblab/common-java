package ucles.weblab.common.domain;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.unmodifiableMap;

/**
 * Proxy to implement immutable buildable instances without a concrete implementation, using a backing {@link org.apache.commons.beanutils.DynaBean}.
 *
 * @since 27/08/2013
 */
public class BuiltProxy<T extends Buildable<T>> implements InvocationHandler {
    @SuppressWarnings("unused")
    private static final Map<Class<?>, Object> DEFAULT_VALUES = unmodifiableMap(new HashMap<Class<?>, Object>() {
        // Default primitive values
        private boolean b;
        private byte by;
        private char c;
        private double d;
        private float f;
        private int i;
        private long l;
        private short s;

        {
            for (final Field field : getClass().getDeclaredFields()) {
                try {
                    put(field.getType(), field.get(this));
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
    });

    private final DynaBean backingBean;

    BuiltProxy(DynaBean backingBean) {
        this.backingBean = backingBean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("get")) {
            assert args == null || args.length == 0;
            final Object propertyValue = PropertyUtils.getProperty(backingBean, StringUtils.uncapitalize(method.getName().substring(3)));
            if (propertyValue == null) {
                if (method.getReturnType() == Optional.class) {
                    return Optional.empty();
                } else if (method.getReturnType().isPrimitive()) {
                    return defaultValue(method.getReturnType());
                }
            }
            return propertyValue;
        } else if (method.getName().startsWith("is") && (method.getReturnType() == Boolean.TYPE || method.getReturnType() == Boolean.class)) {
            assert args == null || args.length == 0;
            final Object propertyValue = PropertyUtils.getProperty(backingBean, StringUtils.uncapitalize(method.getName().substring(2)));
            if (propertyValue == null && method.getReturnType() == Boolean.TYPE) {
                return defaultValue(Boolean.TYPE);
            }
            return propertyValue;
        } else if (method.getName().equals("toString")) {
            assert args == null || args.length == 0;
            return toString();
        } else if (method.getName().equals("equals")) {
            assert args.length == 1;
            if (args[0] == this) {
                return true;
            }
            if (!(args[0] instanceof BuiltProxy)) {
                return false;
            }
            BuiltProxy<?> oBean = (BuiltProxy<?>) args[0];
            return dynaBeanEquals(this.backingBean, oBean.backingBean);
        } else if (method.getName().equals("hashCode")) {
            assert args == null || args.length == 0;
            return hashCode();
        } else {
            throw new NoSuchMethodException(method.getName());
        }

    }

    // Below methods copied from http://www.javaranch.com/journal/2004/04/files/DynaDTODynaBean.java

    private boolean dynaBeanEquals(DynaBean bean1, DynaBean bean2) {
        if (bean1 == bean2) {
            return true;
        }
        if (bean1 == null || bean2 == null) {
            return false;
        }
        DynaClass dynaClass1 = bean1.getDynaClass();
        DynaProperty[] props1 = dynaClass1.getDynaProperties();
        if (props1.length != bean2.getDynaClass().getDynaProperties().length) {
            return false;
        }
        boolean isEqual = true;
        for (int i = 0; i < props1.length && isEqual; i++) {
            DynaProperty prop1 = props1[i];
            DynaProperty prop2 = bean2.getDynaClass().getDynaProperty(prop1.getName());
            isEqual = dynaPropertyEquals(prop1,
                    prop2,
                    bean1.get(prop1.getName()),
                    bean2.get(prop2.getName()));
        }
        return isEqual;
    }

    private boolean dynaPropertyEquals(DynaProperty prop1,
                                       DynaProperty prop2,
                                       Object value1,
                                       Object value2) {
        return (prop1 == prop2) ||
                ((prop1 != null) && (prop2 != null) && prop1.getName().equals(prop2.getName()) && prop1.getType().equals(prop2.getType()) && valueEquals(value1, value2));
    }

    private boolean valueEquals(Object value1, Object value2) {
        return (value1 == value2) || ((value1 != null) && value1.equals(value2));
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = 17;
        DynaProperty[] props = backingBean.getDynaClass().getDynaProperties();
        for (DynaProperty prop : props) {
            int propHashCode = prop.getName().hashCode() ^
                    prop.getType().hashCode();
            Object propVal = backingBean.get(prop.getName());
            int valHashCode = (propVal == null ? 0 : propVal.hashCode());
            int c = propHashCode ^ valHashCode;
            result = 37 * result + c;
        }
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder out = new StringBuilder();
        out.append("[DtoProxy: ");
        DynaProperty[] properties = backingBean.getDynaClass().getDynaProperties();
        for (int i = 0; i < properties.length; i++) {
            String propName = properties[i].getName();
            Object propValue = backingBean.get(propName);
            out.append(propName).append('=').append(propValue);
            if (i < properties.length - 1) {
                out.append(", ");
            }
        }
        out.append("]");
        return out.toString();
    }

    /**
     * Returns the default value of {@code type} as defined by JLS --- {@code 0} for numbers, {@code
     * false} for {@code boolean} and {@code '\0'} for {@code char}. For non-primitive types and
     * {@code void}, null is returned.
     */
    public static <T> T defaultValue(Class<T> type) {
        @SuppressWarnings({"UnnecessaryLocalVariable", "unchecked"})
        final T t = (T) DEFAULT_VALUES.get(type);
        return t;
    }

}
