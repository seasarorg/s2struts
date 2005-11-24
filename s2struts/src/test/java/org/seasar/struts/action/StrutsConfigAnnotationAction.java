package org.seasar.struts.action;

/**
 * @author Katsuhiko Nagashima
 */
public interface StrutsConfigAnnotationAction {

    String ACTION = "path=testpath, name=testname, input=testinput, parameter=testparameter"
            + ", attribute=testattribute, forward=testforward, include=testinclude"
            + ", prefix=testprefix, suffix=testsuffix, roles=testroles";

    String SUCCESS_FORWARD = "/test.jsp";
    
    String SUCCESS = "success";

    String FAIL_FORWARD = "/fail.jsp, redirect=true"; 
    
    String FAIL = "fail";

    String CONST = "const";

    public String exe();

}
