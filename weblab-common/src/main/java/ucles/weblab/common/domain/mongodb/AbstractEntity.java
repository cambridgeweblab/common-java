package ucles.weblab.common.domain.mongodb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

/**
 * @since 15/02/15
 */
@SuppressWarnings("PMD.AbstractClassWithoutAbstractMethod")
public abstract class AbstractEntity {
    @Id
    @JsonIgnore
    private String id;

    protected AbstractEntity() { // for Jackson etc
    }

    protected AbstractEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractEntity entity = (AbstractEntity) o;

        return id == null ? entity.id == null : id.equals(entity.id);

    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
