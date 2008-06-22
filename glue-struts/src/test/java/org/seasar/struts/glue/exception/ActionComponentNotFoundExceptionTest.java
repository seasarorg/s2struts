package org.seasar.struts.glue.exception;

import junit.framework.TestCase;

public class ActionComponentNotFoundExceptionTest extends TestCase {

    public void test() throws Exception {
        final ActionComponentNotFoundException e = new ActionComponentNotFoundException(
                "hoge");
        assertSame("hoge", e.getComponentName());
        System.out.println(e.getMessage());
    }
}
