package ucles.weblab.common.webapi.exception;

/**
 * Exception to be thrown when access to a resource is forbidden for that user
 */
public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String reason) {
        super("Access forbidden: " + reason);
    }
}
