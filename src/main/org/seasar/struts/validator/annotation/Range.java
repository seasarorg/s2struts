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
@ValidatorTarget(ValidatorNameType.TYPE_METHOD)
public @interface Range {
    
    String type() default "doubleRange";
    
    double min();
    
    double max();

}
