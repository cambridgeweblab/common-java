package ucles.weblab.common.webapi;

import org.springframework.http.MediaType;

/**
 * Extends {@link org.springframework.http.MediaType} with UTF-8 versions.
 *
 * @since 07/05/15
 */
public class MoreMediaTypes {
    /**
     * @deprecated Use {@link MediaType#APPLICATION_JSON_UTF8_VALUE} instead.
     */
    @Deprecated
    public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

    /**
     * @deprecated Use {@link MediaType#APPLICATION_JSON_UTF8} instead.
     */
    @Deprecated
    public static final MediaType APPLICATION_JSON_UTF8;

    static {
        APPLICATION_JSON_UTF8 = MediaType.valueOf(APPLICATION_JSON_UTF8_VALUE);
    }

    private MoreMediaTypes() { // Prevent instantiation
    }
}
