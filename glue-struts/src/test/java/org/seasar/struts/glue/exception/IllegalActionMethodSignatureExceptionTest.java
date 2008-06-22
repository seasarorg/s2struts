package org.seasar.struts.glue.exception;

import junit.framework.TestCase;

public class IllegalActionMethodSignatureExceptionTest extends TestCase {

    public void test() throws Exception {
        final IllegalActionMethodSignatureException e = new IllegalActionMethodSignatureException(
                "hoge", "foo");
        assertEquals("hoge", e.getClassName());
        assertEquals("foo", e.getMethodName());
        System.out.println(e.getMessage());
    }
}
