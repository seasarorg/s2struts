package org.seasar.struts.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.seasar.struts.plugin.action.TestAction;
import org.seasar.struts.plugin.form.TestForm;

/**
 * @author Satoshi Kimura
 */
public class ClassComparatorTest extends TestCase {
    
    public void testCompare() {
        List list = new ArrayList();
        list.add(Action.class);
        list.add(TestAction.class);
        list.add(TestForm.class);
        list.add(ActionForm.class);
        Object[] objects = list.toArray();
        Arrays.sort(objects, new ClassComparator());
        assertEquals(TestAction.class, objects[0]);
        assertEquals(TestForm.class, objects[1]);
        assertEquals(Action.class, objects[2]);
        assertEquals(ActionForm.class, objects[3]);

    }
    
    public void testSort() {
        List list = new ArrayList();
        list.add(Action.class);
        list.add(TestAction.class);
        list.add(TestForm.class);
        list.add(ActionForm.class);
        
        list = ClassComparator.sort(list);

        assertEquals(TestAction.class, list.get(0));
        assertEquals(TestForm.class, list.get(1));
        assertEquals(Action.class, list.get(2));
        assertEquals(ActionForm.class, list.get(3));
}


}
