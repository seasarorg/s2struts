package org.seasar.struts.examples.mod;

import org.seasar.struts.annotation.ScopeType;
import org.seasar.struts.annotation.StrutsAction;
import org.seasar.struts.annotation.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(path = "/modInput", name = "modForm", scope = ScopeType.SESSION, validate = false)
public interface ModInputAction {

    @StrutsActionForward(path = "/pages/modInput.jsp")
    String SUCCESS = "success";

    String input();

}