package ucles.weblab.common.webapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
/**
 * A TitledLink is a Hateous link with the addition of a title. So in addition
 * to the rel and href attributes, there will also be a title attribute.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TitledLink extends Link {

    private String title;
    private String method;
    private String description;
    private JsonSchema schema;
    private String mediaType;

    public TitledLink(String href, String rel, String title, String method, String description, JsonSchema schema, String mediaType) {
        super(href, rel);
        this.title = title;
        this.method = method;
        this.description = description;
        this.schema = schema;
        this.mediaType = mediaType;
    }

    public TitledLink(String href, String rel, String title, String method, String description, JsonSchema schema) {
        super(href, rel);
        this.title = title;
        this.method = method;
        this.description = description;
        this.schema = schema;
    }

    public TitledLink(String href, String rel, String title, String method) {
        super(href, rel);
        this.title = title;
        this.method = method;
    }

    public TitledLink(ControllerLinkBuilder linkBuilder, String rel, String title) {
        this(linkBuilder.toString(), rel, title, null);
    }

    public TitledLink(ControllerLinkBuilder linkBuilder, String rel, String title, String method) {
        this(linkBuilder.toString(), rel, title, method);
    }

    protected TitledLink() {
    }

    public String getTitle() {
        return title;
    }

    public String getMethod() {
        return method;
    }

    public String getDescription() {
        return description;
    }

    public JsonSchema getSchema() {
        return schema;
    }

    public String getMediaType() {
        return mediaType;
    }
}
