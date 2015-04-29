package ucles.weblab.common.domain;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Proxy to allow builders for buildable interfaces to themselves be specified as an interface only.
 * Buildable interfaces should provide by convention a public static <code>builder()</code> method which
 * returns a proxy object created by calling {@link BuilderProxyFactory#builder(Class, Class)} with
 * the specific DTO builder interface and the DTO class, which will itself be implement using {@link BuiltProxy}.
 */
public class BuilderProxy<T extends Buildable<T>> implements InvocationHandler {
    /**
     * Template DTO updated by the builder as interface methods are called.
     */
    private final DynaBean workInProgress;
    /**
     * Final output interface.
     */
    private final Class<T> buildable;

    /**
     * Private constructor as this class's status as an invocation handler is not for public exposure.
     *
     * @param buildable the interface to be built
     */
    BuilderProxy(final Class<T> buildable) {
        this.buildable = buildable;
        this.workInProgress = new LazyDynaBean();
    }

    /**
     * Handles all calls to the builder interface. Calls to {@link Buildable.Builder#get()}
     * are handled by returning a clone of the work-in-progress bean. All other calls are handled by setting
     * the property value of the same name on the bean.
     *
     * @param proxy  {@inheritDoc}
     * @param method {@inheritDoc}
     * @param args   {@inheritDoc}
     * @return the proxy interface to allow for method chaining, except for the <code>get</code> method which returns the clone
     * @throws Throwable {@inheritDoc}
     */
    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        if (method.getName().equals("get")) {
            assert args == null || args.length == 0;
            return Proxy.newProxyInstance(BuiltProxy.class.getClassLoader(), new Class[]{buildable}, new BuiltProxy<T>((DynaBean) BeanUtils.cloneBean(workInProgress)));
        } else {
            assert args.length == 1;
            PropertyUtils.setProperty(workInProgress, method.getName(), args[0]);
            return proxy;
        }
    }

}
