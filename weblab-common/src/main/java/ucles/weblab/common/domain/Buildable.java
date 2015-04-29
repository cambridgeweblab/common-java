package ucles.weblab.common.domain;

import java.util.function.Supplier;

/**
 * Marker interface for interfaces which can have implementation instances built by a builder.
 *
 * @see ucles.weblab.common.test.BuilderProxy
 */
@SuppressWarnings("JavadocReference")
public interface Buildable<T> {
    /**
     * Base interface for builder interfaces. Specifies only that the builder provides a <code>get</code> method
     * which returns the buildable object based on the properties set so far. The builder can continue to be used
     * and further clones created, and modification to the clone properties will be isolated from the builder or other
     * clones created later.
     */
    interface Builder<T extends Buildable<T>> extends Supplier<T> {
    }
}
