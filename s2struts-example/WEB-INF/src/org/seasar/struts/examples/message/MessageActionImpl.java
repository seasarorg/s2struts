package org.seasar.struts.examples.message;

import org.seasar.struts.action.MessageManager;
import org.seasar.struts.examples.FowardNameConstants;

/**
 * @author Satoshi Kimura
 */
public class MessageActionImpl implements MessageAction {

    public String execute() {
        MessageManager.addError("examplemessage");
        MessageManager.addError("examplemessage", "foo");
        MessageManager.addError("examplemessage", "bar", "baz", "qux");
        MessageManager.saveErrors();

        return FowardNameConstants.SUCCESS;
    }

}
