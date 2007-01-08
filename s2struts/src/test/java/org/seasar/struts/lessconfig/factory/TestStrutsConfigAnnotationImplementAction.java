package org.seasar.struts.lessconfig.factory;

/**
 * @author Katsuhiko Nagashima
 */
public class TestStrutsConfigAnnotationImplementAction {

    public static final String ACTION = "path=testpath, name=testname, scope=request"
            + ", validate=true, input=testinput, parameter=testparameter"
            + ", attribute=testattribute, forward=testforward, include=testinclude"
            + ", prefix=testprefix, suffix=testsuffix, roles=testroles"
            + ", unknown=false, cancellable=true";

    public static final String SUCCESS_FORWARD = "/test.jsp, redirect=false";

    public static final String SUCCESS = "success";

    public static final String FAIL_FORWARD = "/fail.jsp, redirect=true";

    public static final String FAIL = "fail";

    public static final String CONST = "const";

    public String exe() {
        return SUCCESS;
    }

}
