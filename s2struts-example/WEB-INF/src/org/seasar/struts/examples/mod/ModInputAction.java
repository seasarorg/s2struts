package org.seasar.struts.examples.mod;

import org.seasar.struts.annotation.tiger.ScopeType;
import org.seasar.struts.annotation.tiger.StrutsAction;
import org.seasar.struts.annotation.tiger.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(name = "modForm", scope = ScopeType.SESSION, validate = false)
public interface ModInputAction {

    @StrutsActionForward(path = "/pages/modInput.html")
    String SUCCESS = "success";

    String input();

}