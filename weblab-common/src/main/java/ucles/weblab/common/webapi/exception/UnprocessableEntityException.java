package ucles.weblab.common.webapi.exception;

import org.springframework.core.NestedRuntimeException;

import java.io.Serializable;

public class UnprocessableEntityException extends NestedRuntimeException {

    private final Serializable entityReference;

    public UnprocessableEntityException(String msg, Serializable entityReference) {
        super(msg);
        this.entityReference = entityReference;
    }

    public Serializable getEntityReference() {
        return entityReference;
    }

}
