package org.seasar.struts.util;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;

import junit.framework.TestCase;

/**
 * @author Satoshi Kimura
 * 
 */
public class ConstantAnnotationUtilTest extends TestCase {

	/*
	 * Test method for 'org.seasar.struts.util.ConstantAnnotationUtil.isConstantAnnotationStringField(Field)'
	 */
	public void testIsConstantAnnotationStringField() {
		BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Foo.class);
		assertTrue(ConstantAnnotationUtil.isConstantAnnotationStringField(beanDesc.getField("PUBLIC_STATIC_FINAL_STRING")));
		assertFalse(ConstantAnnotationUtil.isConstantAnnotationStringField(beanDesc.getField("PUBLIC_STATIC_FINAL_INT")));
		assertFalse(ConstantAnnotationUtil.isConstantAnnotationStringField(beanDesc.getField("PUBLIC_STATIC_STRING")));
		assertFalse(ConstantAnnotationUtil.isConstantAnnotationStringField(beanDesc.getField("PUBLIC_FINAL_STRING")));
		assertFalse(ConstantAnnotationUtil.isConstantAnnotationStringField(beanDesc.getField("PROTECTED_STATIC_FINAL_STRING")));
	}

	public static class Foo {
		public static final String PUBLIC_STATIC_FINAL_STRING = null;
		public static final int PUBLIC_STATIC_FINAL_INT = 0;
		public static String PUBLIC_STATIC_STRING = null;
		public final String PUBLIC_FINAL_STRING = null;
		protected static final String PROTECTED_STATIC_FINAL_STRING = null;
	}
    
    public void testIsConstantAnnotationIntField() {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Bar.class);
        assertTrue(ConstantAnnotationUtil.isConstantAnnotationIntField(beanDesc.getField("PUBLIC_STATIC_FINAL_INT")));
        assertFalse(ConstantAnnotationUtil.isConstantAnnotationIntField(beanDesc.getField("PUBLIC_STATIC_FINAL_STRING")));
        assertFalse(ConstantAnnotationUtil.isConstantAnnotationIntField(beanDesc.getField("PUBLIC_STATIC_INT")));
        assertFalse(ConstantAnnotationUtil.isConstantAnnotationIntField(beanDesc.getField("PUBLIC_FINAL_INT")));
        assertFalse(ConstantAnnotationUtil.isConstantAnnotationIntField(beanDesc.getField("PROTECTED_STATIC_FINAL_INT")));
    }

    public static class Bar {
        public static final int PUBLIC_STATIC_FINAL_INT = 0;
        public static final String PUBLIC_STATIC_FINAL_STRING = null;
        public static int PUBLIC_STATIC_INT = 0;
        public final int PUBLIC_FINAL_INT = 0;
        protected static final int PROTECTED_STATIC_FINAL_INT = 0;
    }
    
}
