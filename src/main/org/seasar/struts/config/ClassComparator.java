package org.seasar.struts.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.seasar.struts.factory.StrutsConfigAnnotationHandler;
import org.seasar.struts.factory.StrutsConfigAnnotationHandlerFactory;


/**
 * @author Satoshi Kimura
 */
class ClassComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        Class clazz1 = (Class) o1;
        Class clazz2 = (Class) o2;

        StrutsConfigAnnotationHandler annHandler = StrutsConfigAnnotationHandlerFactory.getAnnotationHandler();
        Object annotation1 = annHandler.createStrutsActionFormConfig(clazz1);
        Object annotation2 = annHandler.createStrutsActionFormConfig(clazz2);
        if (annotation1 == null) {
            annotation1 = annHandler.createStrutsActionConfig(clazz1);
        }
        if (annotation2 == null) {
            annotation2 = annHandler.createStrutsActionConfig(clazz2);
        }
        if (annotation1 != null && annotation2 != null) {
            return 0;
        } else if (annotation1 == null && annotation2 == null) {
            return 0;
        } else if (annotation1 != null) {
            return -1;
        } else {
            return 1;
        }
    }

    public static List sort(Collection collection) {
        Object[] objects = collection.toArray();
        Arrays.sort(objects, new ClassComparator());
        return Arrays.asList(objects);
    }
}
