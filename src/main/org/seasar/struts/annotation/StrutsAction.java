package org.seasar.struts.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.struts.config.StrutsActionConfig;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface StrutsAction {
    
    String path() default StrutsActionConfig.DEFAULT_PATH;

    String name() default StrutsActionConfig.DEFAULT_NAME;

    ScopeType scope() default ScopeType.REQUEST;

    boolean validate() default StrutsActionConfig.DEFAULT_VALIDATE;

    String input() default StrutsActionConfig.DEFAULT_INPUT;

    String parameter() default StrutsActionConfig.DEFAULT_PARAMETER;

    String attribute() default StrutsActionConfig.DEFAULT_ATTRIBUTE;

    String forward() default StrutsActionConfig.DEFAULT_FORWARD;

    String include() default StrutsActionConfig.DEFAULT_INCLUDE;

    String prefix() default StrutsActionConfig.DEFAULT_PREFIX;

    String suffix() default StrutsActionConfig.DEFAULT_SUFFIX;

    boolean unknown() default StrutsActionConfig.DEFAULT_UNKNOWN;

    String roles() default StrutsActionConfig.DEFAULT_ROLES;

}
