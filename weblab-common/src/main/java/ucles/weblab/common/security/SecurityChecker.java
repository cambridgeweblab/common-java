package ucles.weblab.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.hateoas.server.core.MethodInvocation;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.util.SimpleMethodInvocation;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * Checks if a security expression is satisfied by or a method invocation is permitted for the current user.
 */
public class SecurityChecker {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final MethodSecurityExpressionHandler expressionHandler;

    private Method triggerCheckMethod;
    private SpelExpressionParser parser;

    private static class SecurityObject {
        public void triggerCheck() { /*NOP*/ }
    }

    public SecurityChecker(MethodSecurityExpressionHandler expressionHandler) {
        this.expressionHandler = expressionHandler;
        try {
            triggerCheckMethod = SecurityObject.class.getMethod("triggerCheck");
        } catch (NoSuchMethodException e) {
            logger.error("Error initialising SecurityChecked", e);
        }
        parser = new SpelExpressionParser();
    }

    public boolean check(String securityExpression) {
        if (logger.isDebugEnabled()) {
            logger.debug("Checking security expression [" + securityExpression + "]...");
        }

        SecurityObject securityObject = new SecurityObject();
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean checkResult = authentication != null;

        if (checkResult) {
            EvaluationContext evaluationContext = expressionHandler.createEvaluationContext(authentication, new SimpleMethodInvocation(securityObject, triggerCheckMethod));
            checkResult = ExpressionUtils.evaluateAsBoolean(parser.parseExpression(securityExpression), evaluationContext);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Check result: " + checkResult);
        }

        return checkResult;
    }

    public boolean check(MethodInvocation methodInvocation) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        PreAuthorize preAuth = methodInvocation.getMethod().getAnnotation(PreAuthorize.class);
        if (preAuth != null) {
            if (authentication == null) {
                return false;
            }
            EvaluationContext evaluationContext = expressionHandler.createEvaluationContext(authentication, new SimpleMethodInvocation(methodInvocation.getTargetType(), methodInvocation.getMethod()));
            return ExpressionUtils.evaluateAsBoolean(parser.parseExpression(preAuth.value()), evaluationContext);
        }
        Secured secured = methodInvocation.getMethod().getAnnotation(Secured.class);
        if (secured != null) {
            if (authentication != null) {
                return Arrays.stream(secured.value()).anyMatch(role -> contains(authentication.getAuthorities(), a -> a.getAuthority().equals(role)));
            }
            return false;
        }

        return true;
    }

    static <T> boolean contains(Collection<T> collection, Predicate<T> predicate) {
        return collection.stream().anyMatch(predicate);
    }


}
