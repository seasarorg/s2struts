package org.seasar.struts.examples.web.upload;

import org.seasar.struts.annotation.tiger.StrutsActionForward;

public abstract class AbstractUpdateAction {

    @StrutsActionForward
    public static final String UPLOAD = "/pages/upload/upload.jsp";

    @StrutsActionForward
    public static final String RESULT = "/pages/upload/result.jsp";
}
