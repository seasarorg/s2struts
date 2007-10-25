package org.seasar.struts.lessconfig.autoregister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.seasar.struts.lessconfig.factory.TestStrutsConfigAnnotationAction;
import org.seasar.struts.lessconfig.factory.TestValidatorAnnotationForm;

/**
 * @author Satoshi Kimura
 */
public class ClassComparatorTest extends TestCase {

	@SuppressWarnings("unchecked")
	public void testCompare() {
		List<Class> list = new ArrayList<Class>();
		list.add(Action.class);
		list.add(TestStrutsConfigAnnotationAction.class);
		list.add(TestValidatorAnnotationForm.class);
		list.add(ActionForm.class);
		Object[] objects = list.toArray();
		Arrays.sort(objects, new ClassComparator());
		assertEquals(Action.class, objects[0]);
		assertEquals(TestStrutsConfigAnnotationAction.class, objects[1]);
		assertEquals(TestValidatorAnnotationForm.class, objects[2]);
		assertEquals(ActionForm.class, objects[3]);

	}

	@SuppressWarnings("unchecked")
	public void testSort() {
		List<Class> list = new ArrayList<Class>();
		list.add(Action.class);
		list.add(TestStrutsConfigAnnotationAction.class);
		list.add(TestValidatorAnnotationForm.class);
		list.add(ActionForm.class);

		list = ClassComparator.sort(list);

		assertEquals(Action.class, list.get(0));
		assertEquals(TestStrutsConfigAnnotationAction.class, list.get(1));
		assertEquals(TestValidatorAnnotationForm.class, list.get(2));
		assertEquals(ActionForm.class, list.get(3));
	}

}
