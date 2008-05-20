package org.seasar.struts.examples.web.iterate;

import org.seasar.struts.annotation.tiger.StrutsActionForward;

public abstract class AbstractIterateAction {

    @StrutsActionForward
    public static final String ITERATE = "/pages/iterate/iterate.jsp";

    @StrutsActionForward
    public static final String RESULT = "/pages/iterate/result.jsp";
}
