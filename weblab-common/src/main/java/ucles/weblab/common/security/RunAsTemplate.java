package ucles.weblab.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.function.Supplier;

/**
 * Executes the provided callback with security context set.
 * Use with a {@link PreAuthenticatedAuthenticationToken} to run code as a named user. Note that if there is not a
 * configured {@link org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider} then
 * the context is only good for accessing current user identity and cannot be used for authorization (so no protected
 * methods, for example).
 */
public class RunAsTemplate {
    private final Authentication auth;

    public RunAsTemplate(UserDetails user) {
        this(new PreAuthenticatedAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
    }

    public RunAsTemplate(Authentication auth) {
        this.auth = auth;
    }

    public <T> T execute(Supplier<T> callback) {
        final Authentication savedAuthentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            SecurityContextHolder.getContext().setAuthentication(auth);
            return callback.get();
        } finally {
            SecurityContextHolder.getContext().setAuthentication(savedAuthentication);
        }
    }

    public void execute(Runnable callback) {
        final Authentication savedAuthentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            SecurityContextHolder.getContext().setAuthentication(auth);
            callback.run();
        } finally {
            SecurityContextHolder.getContext().setAuthentication(savedAuthentication);
        }
    }
}
