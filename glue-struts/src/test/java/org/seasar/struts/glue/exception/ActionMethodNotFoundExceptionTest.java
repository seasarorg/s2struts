package org.seasar.struts.glue.exception;

import org.seasar.struts.glue.exception.ActionMethodNotFoundException;

import junit.framework.TestCase;

public class ActionMethodNotFoundExceptionTest extends TestCase {

    public void test() throws Exception {
        Exception cause = new Exception();
        ActionMethodNotFoundException e = new ActionMethodNotFoundException(
                cause, "hoge", "foo");
        assertEquals("hoge", e.getClassName());
        assertEquals("foo", e.getMethodName());
        e.printStackTrace();
    }
}
