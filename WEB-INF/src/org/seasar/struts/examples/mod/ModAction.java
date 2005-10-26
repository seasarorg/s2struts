package org.seasar.struts.examples.mod;

import org.seasar.struts.annotation.tiger.ScopeType;
import org.seasar.struts.annotation.tiger.StrutsAction;
import org.seasar.struts.annotation.tiger.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(path = "/mod", name = "modForm", scope = ScopeType.SESSION, validate = true, input = "/pages/modInput.jsp")
public interface ModAction {

    @StrutsActionForward(path = "/pages/modResult.jsp")
    String SUCCESS = "success";

    String mod();

}