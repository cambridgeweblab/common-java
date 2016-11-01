package ucles.weblab.common.webapi.exception;

import java.io.Serializable;
import java.util.List;
import org.springframework.core.NestedRuntimeException;

/**
 *
 * @author Sukhraj
 */
public class ConflictException extends NestedRuntimeException {
    private final Serializable entityReference;
    private String detail; 
    private List<Object> conflictingItems;
    
    public ConflictException(String summary, Serializable entityReference) {
        super(summary);
        this.entityReference = entityReference;
    }
    
    public ConflictException(String msg, Serializable entityReference, Throwable cause) {
        super(msg, cause);
        this.entityReference = entityReference;
    }
    
    public ConflictException(String summary, String detail, List<Object> conflictingItems) {
        super(summary);
        this.detail = detail;
        this.conflictingItems = conflictingItems;
        this.entityReference = null;
    }

    public Serializable getEntityReference() {
        return entityReference;
    }

    public String getDetail() {
        return detail;
    }

    public List<Object> getConflictingItems() {
        return conflictingItems;
    }
    
}
