package org.seasar.struts.examples.mod;

import org.seasar.struts.annotation.tiger.ScopeType;
import org.seasar.struts.annotation.tiger.StrutsAction;
import org.seasar.struts.annotation.tiger.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(name = "modForm", scope = ScopeType.SESSION, input = "/pages/modInput.html")
public interface ModAction {

    @StrutsActionForward(path = "/pages/modResult.html")
    String SUCCESS = "success";

    String mod();

}