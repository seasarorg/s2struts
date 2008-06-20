package org.seasar.struts.glue.exception;

import org.seasar.struts.glue.exception.NoMethodNameParameterException;

import junit.framework.TestCase;

public class NoMethodNameParameterExceptionTest extends TestCase {

    public void test() throws Exception {
        NoMethodNameParameterException e = new NoMethodNameParameterException();
        e.printStackTrace();
    }
}
