package org.seasar.struts.glue.exception;

import org.seasar.struts.glue.exception.TooManyFormFieldException;

import junit.framework.TestCase;

public class TooManyFormFieldExceptionTest extends TestCase {

    public void test() throws Exception {
        TooManyFormFieldException e = new TooManyFormFieldException("hoge");
        assertEquals("hoge", e.getClassName());
        e.printStackTrace();
    }
}
