package ucles.weblab.common.security;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * A converter that can be registered e.g. as the following in the static init block of the main Java config file
 * <code>
 *     PatternLayout.defaultConverterMap.put("user", LogbackUserConverter.class.getName());
 * </code>
 * so that %{width}user can be used in the format.
 * <p>
 * For Spring Boot, it is easiest to add the user by overriding the level format in application.properties by e.g.:
 * <code>
 *     logging.pattern.level=%10user %5p
 * </code>
 */
public class LogbackUserConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return auth.getName();
        }
        return "(no user)";
    }
}
