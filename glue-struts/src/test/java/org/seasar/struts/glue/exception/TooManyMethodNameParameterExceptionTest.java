package org.seasar.struts.glue.exception;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

public class TooManyMethodNameParameterExceptionTest extends TestCase {

    public void test() throws Exception {
        final List<String> parameters = Arrays.asList("hoge", "foo", "bar");
        final TooManyMethodNameParameterException e = new TooManyMethodNameParameterException(
                parameters);
        assertEquals(parameters, e.getMethodNameParameters());
        System.out.println(e.getMessage());
    }
}
