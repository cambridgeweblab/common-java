package ucles.weblab.common.domain;

import java.lang.reflect.Proxy;

/**
 * Factory for creating builders for {@link Buildable} interfaces from {@link Buildable.Builder Builders}.
 *
 * @since 08/05/15
 */
public class BuilderProxyFactory {
    /**
     * Creates a new proxy which allows a buildable to built using a fluent interface.
     * The interface must define methods corresponding to property names on the buildable.
     *
     * @param builderClass   the builder fluent interface class
     * @param buildableClass the bean interface class
     * @param <T>            the data transfer object type to be built
     * @param <I>            the builder interface definition which must extend {@link Buildable.Builder}
     * @return a proxy object implementing the builder fluent interface to allow new objects to be created using method chaining
     */
    @SuppressWarnings("unchecked")
    public <T extends Buildable<T>, I extends Buildable.Builder<T>> I builder(Class<I> builderClass, Class<T> buildableClass) {
        return (I) Proxy.newProxyInstance(BuilderProxy.class.getClassLoader(),
                new Class[]{builderClass},
                new BuilderProxy<T>(buildableClass));
    }
}
