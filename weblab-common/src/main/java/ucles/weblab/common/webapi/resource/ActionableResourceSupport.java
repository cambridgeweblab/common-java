package ucles.weblab.common.webapi.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.module.jsonSchema.types.LinkDescriptionObject;
import org.springframework.hateoas.ResourceSupport;

import java.net.URI;
import ucles.weblab.common.webapi.TitledLink;

/**
 * Base class for DTOs to collect actions.
 *
 * @since 03/11/15
 */
public class ActionableResourceSupport extends ResourceSupport {
    
    public static TitledLink convert(Action action) {
        
        TitledLink tl = new TitledLink(action.getHref(), "action:" + action.getRel(), action.getTitle(), action.getMethod(), action.getDescription(), action.getSchema(), action.getMediaType());
        return tl;
    }
    
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

        public String getDescription() {
            return description;
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
