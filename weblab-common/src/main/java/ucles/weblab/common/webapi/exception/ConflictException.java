package ucles.weblab.common.webapi.exception;

import java.io.Serializable;
import org.springframework.core.NestedRuntimeException;

/**
 *
 * @author Sukhraj
 */
public class ConflictException extends NestedRuntimeException {
    private final Serializable entityReference;

    public ConflictException(String msg, Serializable entityReference) {
        super(msg);
        this.entityReference = entityReference;
    }

    public ConflictException(String msg, Serializable entityReference, Throwable cause) {
        super(msg, cause);
        this.entityReference = entityReference;
    }

    public Serializable getEntityReference() {
        return entityReference;
    }
    
}
