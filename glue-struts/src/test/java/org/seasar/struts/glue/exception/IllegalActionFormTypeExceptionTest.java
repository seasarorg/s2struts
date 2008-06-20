package org.seasar.struts.glue.exception;

import org.seasar.struts.glue.exception.IllegalActionFormTypeException;

import junit.framework.TestCase;

public class IllegalActionFormTypeExceptionTest extends TestCase {

    public void test() {
        IllegalActionFormTypeException e = new IllegalActionFormTypeException(
                "hoge", "foo");
        assertEquals("hoge", e.getClassName());
        assertEquals("foo", e.getFieldName());
        e.printStackTrace();
    }
}
