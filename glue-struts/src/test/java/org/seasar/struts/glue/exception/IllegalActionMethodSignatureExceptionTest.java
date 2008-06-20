package org.seasar.struts.glue.exception;

import org.seasar.struts.glue.exception.IllegalActionMethodSignatureException;

import junit.framework.TestCase;

public class IllegalActionMethodSignatureExceptionTest extends TestCase {

    public void test() throws Exception {
        IllegalActionMethodSignatureException e = new IllegalActionMethodSignatureException(
                "hoge", "foo");
        assertEquals("hoge", e.getClassName());
        assertEquals("foo", e.getMethodName());
        e.printStackTrace();
    }
}
