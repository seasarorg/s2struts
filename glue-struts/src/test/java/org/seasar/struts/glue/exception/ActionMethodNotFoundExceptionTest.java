package org.seasar.struts.glue.exception;

import junit.framework.TestCase;

public class ActionMethodNotFoundExceptionTest extends TestCase {

    public void test() throws Exception {
        final ActionMethodNotFoundException e = new ActionMethodNotFoundException(
                "hoge", "foo");
        assertEquals("hoge", e.getClassName());
        assertEquals("foo", e.getMethodName());
        System.out.println(e.getMessage());
    }
}
