package org.seasar.struts.zeroconfig.autoregister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.seasar.struts.zeroconfig.autoregister.ClassComparator;
import org.seasar.struts.zeroconfig.factory.TestStrutsConfigAnnotationAction;
import org.seasar.struts.zeroconfig.factory.TestValidatorAnnotationForm;

/**
 * @author Satoshi Kimura
 */
public class ClassComparatorTest extends TestCase {
    
    public void testCompare() {
        List list = new ArrayList();
        list.add(Action.class);
        list.add(TestStrutsConfigAnnotationAction.class);
        list.add(TestValidatorAnnotationForm.class);
        list.add(ActionForm.class);
        Object[] objects = list.toArray();
        Arrays.sort(objects, new ClassComparator());
        assertEquals(TestStrutsConfigAnnotationAction.class, objects[0]);
        assertEquals(TestValidatorAnnotationForm.class, objects[1]);
        assertEquals(Action.class, objects[2]);
        assertEquals(ActionForm.class, objects[3]);

    }
    
    public void testSort() {
        List list = new ArrayList();
        list.add(Action.class);
        list.add(TestStrutsConfigAnnotationAction.class);
        list.add(TestValidatorAnnotationForm.class);
        list.add(ActionForm.class);
        
        list = ClassComparator.sort(list);

        assertEquals(TestStrutsConfigAnnotationAction.class, list.get(0));
        assertEquals(TestValidatorAnnotationForm.class, list.get(1));
        assertEquals(Action.class, list.get(2));
        assertEquals(ActionForm.class, list.get(3));
}


}
