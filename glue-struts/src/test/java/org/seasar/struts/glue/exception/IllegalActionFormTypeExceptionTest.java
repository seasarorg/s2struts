package org.seasar.struts.glue.exception;

import junit.framework.TestCase;

public class IllegalActionFormTypeExceptionTest extends TestCase {

    public void test() {
        final IllegalActionFormTypeException e = new IllegalActionFormTypeException(
                "hoge", "foo");
        assertEquals("hoge", e.getClassName());
        assertEquals("foo", e.getFieldName());
        System.out.println(e.getMessage());
    }
}
