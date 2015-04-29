package ucles.weblab.common.domain.mongodb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

/**
 * @since 15/02/15
 */
public abstract class AbstractEntity {
    @Id
    @JsonIgnore
    private String id;

    protected AbstractEntity() {
    }

    protected AbstractEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity entity = (AbstractEntity) o;

        return !(id != null ? !id.equals(entity.id) : entity.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
