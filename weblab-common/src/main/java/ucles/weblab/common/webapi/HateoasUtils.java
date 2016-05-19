package ucles.weblab.common.webapi;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.function.Function;

/**
 * Utility methods for controllers adhering to HATEOAS principles.
 *
 * @since 20/03/15
 */
public class HateoasUtils {
    private HateoasUtils() { // Prevent instantiation
    }

    /**
     * Returns HTTP headers containing a Location header if the resource has a 'self' link.
     *
     * @param resource the resource to return headers for
     * @return headers containing Location if the resource had a 'self' link.
     * @see ResourceSupport#getId()
     */
    public static HttpHeaders locationHeader(ResourceSupport resource) {
        HttpHeaders headers = new HttpHeaders();
        final Link id = resource.getId();
        if (id != null) {
            headers.setLocation(URI.create(id.getHref()));
        }
        return headers;
    }

    /**
     * Returns a link as a URI string, with or without any parameters included.
     * @param linkBuilder the link, from {@link ControllerLinkBuilder#linkTo(Class)}.
     * @param withParameters true if parameters should be included in the URI string
     * @return the URI string
     */
    public static String toUriString(ControllerLinkBuilder linkBuilder, boolean withParameters) {
        UriComponentsBuilder uriComponentsBuilder = linkBuilder.toUriComponentsBuilder();
        if (!withParameters) {
            uriComponentsBuilder = uriComponentsBuilder.replaceQuery(null);
        }
        return uriComponentsBuilder.build(false).toString();
    }
}
