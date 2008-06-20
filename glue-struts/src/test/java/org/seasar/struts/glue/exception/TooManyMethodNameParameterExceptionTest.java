package org.seasar.struts.glue.exception;

import java.util.Arrays;
import java.util.List;

import org.seasar.struts.glue.exception.TooManyMethodNameParameterException;

import junit.framework.TestCase;

public class TooManyMethodNameParameterExceptionTest extends TestCase {

    public void test() throws Exception {
        List<String> parameters = Arrays.asList("hoge", "foo", "bar");
        TooManyMethodNameParameterException e = new TooManyMethodNameParameterException(
                parameters);
        assertEquals(parameters, e.getMethodNameParameters());
        e.printStackTrace();
    }
}
