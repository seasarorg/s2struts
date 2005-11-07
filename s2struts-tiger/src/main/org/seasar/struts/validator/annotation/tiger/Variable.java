package org.seasar.struts.validator.annotation.tiger;

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
@Target(ElementType.TYPE)
public @interface Variable {

    String name();

    String value();

    String key() default "";

    boolean resource() default true;

    boolean arg() default true;

}
