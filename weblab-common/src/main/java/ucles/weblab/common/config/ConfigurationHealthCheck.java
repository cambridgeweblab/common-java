package ucles.weblab.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.core.env.Environment;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.boot.actuate.health.Status.UP;

/**
 * Fail health check if configuration is missing.
 * If the configuration is missing at startup, then an exception will be thrown to prevent startup. If after
 * startup a configuration variable is removed dynamically (e.g. when using Spring Cloud), then the heath check
 * will change from UP to DOWN.
 * To see details, the property management.endpoint.health.show-details must be set to 'when_authorized' or 'always'
 * */
public class ConfigurationHealthCheck extends AbstractHealthIndicator {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Environment environment;

    /** e.g. ["spring.datasource.hikari.maximum-pool-size", "suppress.errors"] */
    private final List<String> requiredProperties;


    public ConfigurationHealthCheck(Environment environment, List<String> requiredProperties) {
        this.environment = environment;
        this.requiredProperties = requiredProperties;

        Builder bldr = new Builder();
        doHealthCheck(bldr);
        Health health = bldr.build();

        if (health.getStatus() != UP) {
            throw new RuntimeException("System failed configuration healthcheck: " + health.toString());
        }
    }


    @Override
    final protected void doHealthCheck(Builder bldr) {

        List<String> missing = requiredProperties.stream()
                .filter(key -> !environment.containsProperty(key))
                .collect(toList());

        if (missing.isEmpty()) {
            bldr.up();
        } else {
            log.error("Environment health check failed. The following properties are not configured: {}", missing);
            bldr
                .withDetail("missing-properties", missing)
                .down();
        }
    }
}