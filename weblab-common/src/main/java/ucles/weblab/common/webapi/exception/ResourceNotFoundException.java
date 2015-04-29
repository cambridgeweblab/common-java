package ucles.weblab.common.webapi.exception;

import java.io.Serializable;

/**
 * Exception to be thrown when a resource which is the target of an API operation is not found.
 *
 * @since 06/05/15
 */
public class ResourceNotFoundException extends RuntimeException {
    private final Serializable resourceId;

    public ResourceNotFoundException(Serializable resourceId) {
        super("Could not find requested resource.");
        this.resourceId = resourceId;
    }

    public Serializable getResourceId() {
        return resourceId;
    }
}
