package org.seasar.struts.glue.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SessionAttribute {
    String name() default "";

    boolean imports() default true;

    boolean exports() default true;
}
