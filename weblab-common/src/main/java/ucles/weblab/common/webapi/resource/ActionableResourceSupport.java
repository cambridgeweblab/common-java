package ucles.weblab.common.webapi.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.module.jsonSchema.types.LinkDescriptionObject;
import org.springframework.hateoas.RepresentationModel;

import java.net.URI;
import ucles.weblab.common.webapi.TitledLink;

/**
 * Base class for DTOs to collect actions.
 *
 * @since 03/11/15
 */
@SuppressWarnings("PMD.UseSingleton")
public class ActionableResourceSupport<T> extends RepresentationModel<ActionableResourceSupport<T>> {

    @JsonIgnoreProperties({"jsonSchema"}) // LinkDescriptionObject incorrectly annotates jsonSchema.
    public static class Action extends LinkDescriptionObject {
        private String description;

        public Action(URI href, String title, String description, com.fasterxml.jackson.module.jsonSchema.JsonSchema schema) {
            super();
            setHref(href.toString());
            setTitle(title);
            setSchema(schema);
            this.description = description;
        }

        public TitledLink toTitledLink() {

            return new TitledLink(getHref(), "action:" + getRel(), getTitle(), getMethod(),
                    getDescription(), getSchema(), getMediaType(), getEnctype());
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "Action{" +
                    ", href='" + getHref() + '\'' +
                    ", title='" + getTitle() + '\'' +
                    ", description='" + description + '\'' +
                    ", schema=" + getSchema() + '\'' +
                    ", mediaType=" + getMediaType() +
                    '}';
        }
    }
}
