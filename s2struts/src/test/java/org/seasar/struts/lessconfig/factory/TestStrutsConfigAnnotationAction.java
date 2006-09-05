package org.seasar.struts.lessconfig.factory;

/**
 * @author Katsuhiko Nagashima
 */
public interface TestStrutsConfigAnnotationAction {

    String ACTION = "path=testpath, name=testname, input=testinput, parameter=testparameter"
            + ", attribute=testattribute, forward=testforward, include=testinclude"
            + ", prefix=testprefix, suffix=testsuffix, roles=testroles" + ", cancellable=true";

    String SUCCESS_FORWARD = "/test.jsp";

    String SUCCESS = "success";

    String FAIL_FORWARD = "/fail.jsp, redirect=true";

    String FAIL = "fail";

    String CONST = "const";

    String exe();

}
