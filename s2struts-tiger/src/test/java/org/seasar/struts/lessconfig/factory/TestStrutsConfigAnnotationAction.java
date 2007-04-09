package org.seasar.struts.lessconfig.factory;

import org.seasar.struts.annotation.tiger.BoolType;
import org.seasar.struts.annotation.tiger.ScopeType;
import org.seasar.struts.annotation.tiger.StrutsAction;
import org.seasar.struts.annotation.tiger.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(path = "testpath", scope = ScopeType.REQUEST, name = "testname",
        validate = BoolType.TRUE, input = "testinput",
        parameter = "testparameter", attribute = "testattribute",
        forward = "testforward", include = "testinclude",
        prefix = "testprefix", suffix = "testsuffix", roles = "testroles",
        unknown = BoolType.FALSE, cancellable=BoolType.TRUE,
        catalog = "testcatalog", command = "testcommand", inherit = "testinherit")
public interface TestStrutsConfigAnnotationAction {

    @StrutsActionForward(path = "/test.jsp", redirect = BoolType.FALSE)
    String SUCCESS = "success";

    @StrutsActionForward(path = "/fail.jsp", redirect = BoolType.TRUE)
    String FAIL = "fail";

    String CONST = "const";

    public String exe();

}
