package ucles.weblab.common.webapi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines actions which can be carried out on a resource.
 * Used along with {@link ucles.weblab.common.webapi.resource.ActionableResourceSupport} to populate a list of possible actions.
 *
 * @since 03/11/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ActionCommands {
    ActionCommand[] value() default {};

    /**
     * Do not use this in conjunction with {@link ActionCommand#message()} as that will also include any pre-existing actions.
     * @return Spring-EL expression identifying the business key to use to obtain actions from the workflow engine.
     */
    String businessKey() default "";
}
