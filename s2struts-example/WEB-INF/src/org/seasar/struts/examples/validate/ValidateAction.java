package org.seasar.struts.examples.validate;

import org.seasar.struts.annotation.tiger.ScopeType;
import org.seasar.struts.annotation.tiger.StrutsAction;

/**
 * @author Satoshi Kimura
 */
@StrutsAction(scope = ScopeType.SESSION)
public interface ValidateAction {
    String exe();
}
