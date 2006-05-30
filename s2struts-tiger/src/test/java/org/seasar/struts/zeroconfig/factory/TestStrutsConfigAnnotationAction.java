package org.seasar.struts.zeroconfig.factory;

import org.seasar.struts.annotation.tiger.StrutsAction;
import org.seasar.struts.annotation.tiger.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(path = "testpath", name = "testname", input = "testinput",
        parameter = "testparameter", attribute = "testattribute",
        forward = "testforward", include = "testinclude",
        prefix = "testprefix", suffix = "testsuffix", roles = "testroles",
        cancellable=true)
public interface TestStrutsConfigAnnotationAction {

    @StrutsActionForward(path = "/test.jsp")
    String SUCCESS = "success";

    @StrutsActionForward(path = "/fail.jsp", redirect = true)
    String FAIL = "fail";

    String CONST = "const";

    public String exe();

}
