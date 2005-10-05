package org.seasar.struts.action;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.annotation.StrutsAction(path="testpath", name="testname",
 *                                                             input="testinput",
 *                                                             parameter="testparameter",
 *                                                             attribute="testattribute",
 *                                                             forward="testforward",
 *                                                             include="testinclude",
 *                                                             prefix="testprefix",
 *                                                             suffix="testsuffix",
 *                                                             roles="testroles")
 */
public interface TestAction {

    /**
     * @org.seasar.struts.annotation.StrutsActionForward(path="/test.jsp")
     */
    String SUCCESS = "success";

    /**
     * @org.seasar.struts.annotation.StrutsActionForward(path="/fail.jsp", redirect=true)
     */
    String FAIL = "fail";

    String CONST = "const";

    public String exe();

}
