package org.seasar.struts.plugin.action.impl;

import org.seasar.struts.plugin.action.TestAction;

/**
 * @author Satoshi Kimura
 */
public class TestActionImpl implements TestAction {

    public TestActionImpl() {
    }

    public String exe() {
        return SUCCESS;
    }
}
