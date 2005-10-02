package org.seasar.struts.unit.mock;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * @author Satoshi Kimura
 */
public class MockActionMapping extends ActionMapping {
    public ActionForward findForward(String name) {
        ActionForward actionForward = new ActionForward();
        actionForward.setName(name);

        return actionForward;
    }
}