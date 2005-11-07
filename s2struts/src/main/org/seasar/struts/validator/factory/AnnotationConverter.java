package org.seasar.struts.validator.factory;

import java.util.Map;

/**
 * @author Katsuhiko Nagashima
 */
public interface AnnotationConverter {
    
    Map toMap(Object obj);

}
