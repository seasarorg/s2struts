package org.seasar.struts.annotation.tiger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.struts.config.StrutsActionForwardConfig;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface StrutsActionForward {
    
    String path();

    boolean redirect() default StrutsActionForwardConfig.DEFAULT_REDIRECT;

}
