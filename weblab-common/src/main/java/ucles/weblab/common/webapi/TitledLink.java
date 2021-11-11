package ucles.weblab.common.webapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

/**
 * A TitledLink is a Hateoas link with the addition of a title. So in addition
 * to the rel and href attributes, there will also be a title attribute.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TitledLink extends Link {

    private String title;
    private String method;
    private String description;
    private JsonSchema schema;
    private String mediaType;
    @JsonProperty("enctype")
    private String encType;

    public TitledLink(String href, String rel, String title, String method, String description, JsonSchema schema, String mediaType, String encType) {
        //noinspection deprecation
        super(href, rel); // all useful constructors are deprecated, so we cannot avoid this
        this.title = title;
        this.method = method;
        this.description = description;
        this.schema = schema;
        this.mediaType = mediaType;
        this.encType = encType;
    }

    public TitledLink(String href, String rel, String title, String method, String description, JsonSchema schema) {
        //noinspection deprecation
        super(href, rel); // all useful constructors are deprecated, so we cannot avoid this
        this.title = title;
        this.method = method;
        this.description = description;
        this.schema = schema;
    }

    public TitledLink(String href, String rel, String title, String method) {
        //noinspection deprecation
        super(href, rel); // all useful constructors are deprecated, so we cannot avoid this
        this.title = title;
        this.method = method;
    }

    public TitledLink(WebMvcLinkBuilder linkBuilder, String rel, String title) {
        this(linkBuilder.toString(), rel, title, null);
    }

    public TitledLink(WebMvcLinkBuilder linkBuilder, String rel, String title, String method) {
        this(linkBuilder.toString(), rel, title, method);
    }

    protected TitledLink() { // For Jackson
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

    public String getEncType() {
        return encType;
    }
}
