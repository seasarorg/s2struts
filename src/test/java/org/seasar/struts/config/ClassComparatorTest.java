package org.seasar.struts.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.seasar.struts.action.StrutsConfigAnnotationAction;
import org.seasar.struts.form.ValidatorAnnotationForm;

/**
 * @author Satoshi Kimura
 */
public class ClassComparatorTest extends TestCase {

	@SuppressWarnings("unchecked")
	public void testCompare() {
		List<Class> list = new ArrayList<Class>();
		list.add(Action.class);
		list.add(StrutsConfigAnnotationAction.class);
		list.add(ValidatorAnnotationForm.class);
		list.add(ActionForm.class);
		Object[] objects = list.toArray();
		Arrays.sort(objects, new ClassComparator());
		assertEquals(StrutsConfigAnnotationAction.class, objects[0]);
		assertEquals(ValidatorAnnotationForm.class, objects[1]);
		assertEquals(Action.class, objects[2]);
		assertEquals(ActionForm.class, objects[3]);
	}

	@SuppressWarnings("unchecked")
	public void testSort() {
		List<Class> list = new ArrayList<Class>();
		list.add(Action.class);
		list.add(StrutsConfigAnnotationAction.class);
		list.add(ValidatorAnnotationForm.class);
		list.add(ActionForm.class);
		list = ClassComparator.sort(list);
		assertEquals(StrutsConfigAnnotationAction.class, list.get(0));
		assertEquals(ValidatorAnnotationForm.class, list.get(1));
		assertEquals(Action.class, list.get(2));
		assertEquals(ActionForm.class, list.get(3));
	}

}
