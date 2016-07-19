package ucles.weblab.common.webapi;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

/**
 * A TitledLink is a Hateous link with the addition of a title. So in addition
 * to the rel and href attributes, there will also be a title attribute.
 */
public class TitledLink extends Link {

    private String title;
    private String method;
    
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

}
