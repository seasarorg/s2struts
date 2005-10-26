package org.seasar.struts.annotation.tiger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.struts.config.StrutsActionFormConfig;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface StrutsActionForm {

    String name() default StrutsActionFormConfig.DEFAULT_NAME;

    boolean restricted() default StrutsActionFormConfig.DEFAULT_RESTRICTED;

}
