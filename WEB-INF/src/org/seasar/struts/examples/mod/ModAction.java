package org.seasar.struts.examples.mod;

import org.seasar.struts.annotation.ScopeType;
import org.seasar.struts.annotation.StrutsAction;
import org.seasar.struts.annotation.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(path = "/mod", name = "modForm", scope = ScopeType.SESSION, validate = true, input = "/pages/modInput.jsp")
public interface ModAction {

    @StrutsActionForward(path = "/pages/modResult.jsp")
    String SUCCESS = "success";

    String mod();

}