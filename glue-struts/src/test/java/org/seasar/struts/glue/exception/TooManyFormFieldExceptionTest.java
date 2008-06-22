package org.seasar.struts.glue.exception;

import junit.framework.TestCase;

public class TooManyFormFieldExceptionTest extends TestCase {

    public void test() throws Exception {
        final TooManyFormFieldException e = new TooManyFormFieldException(
                "hoge");
        assertEquals("hoge", e.getClassName());
        System.out.println(e.getMessage());
    }
}
