package org.seasar.struts.examples.validate;

import org.seasar.struts.annotation.ScopeType;
import org.seasar.struts.annotation.StrutsAction;

/**
 * @author Satoshi Kimura
 */
@StrutsAction(scope = ScopeType.SESSION)
public interface ValidateAction {
    String exe();
}
