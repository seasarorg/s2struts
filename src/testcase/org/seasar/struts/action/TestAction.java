package org.seasar.struts.action;

import org.seasar.struts.annotation.StrutsAction;
import org.seasar.struts.annotation.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(path = "testpath", name = "testname", input = "testinput",
        parameter = "testparameter", attribute = "testattribute",
        forward = "testforward", include = "testinclude",
        prefix = "testprefix", suffix = "testsuffix", roles = "testroles")
public interface TestAction {

    @StrutsActionForward(path = "/test.jsp")
    String SUCCESS = "success";

    @StrutsActionForward(path = "/fail.jsp", redirect = true)
    String FAIL = "fail";

    String CONST = "const";

    public String exe();

}
