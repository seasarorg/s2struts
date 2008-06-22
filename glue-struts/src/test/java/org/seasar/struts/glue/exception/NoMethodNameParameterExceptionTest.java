package org.seasar.struts.glue.exception;

import junit.framework.TestCase;

public class NoMethodNameParameterExceptionTest extends TestCase {

    public void test() throws Exception {
        final NoMethodNameParameterException e = new NoMethodNameParameterException();
        System.out.println(e.getMessage());
    }
}
