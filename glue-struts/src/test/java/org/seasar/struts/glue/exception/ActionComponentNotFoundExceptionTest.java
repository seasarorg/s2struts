package org.seasar.struts.glue.exception;

import junit.framework.TestCase;

import org.apache.struts.action.ActionMapping;
import org.seasar.struts.glue.exception.ActionComponentNotFoundException;

public class ActionComponentNotFoundExceptionTest extends TestCase {

    public void test() throws Exception {
        ActionMapping mapping = new ActionMapping();
        ActionComponentNotFoundException e = new ActionComponentNotFoundException(
                mapping);
        assertSame(mapping, e.getMapping());
    }
}
