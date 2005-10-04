package org.seasar.struts.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ValidatorTarget
public @interface Url {

    boolean allowallschemes() default false;

    boolean allow2slashes() default false;

    boolean nofragments() default false;

    String schemes() default "http,https,ftp";

}
