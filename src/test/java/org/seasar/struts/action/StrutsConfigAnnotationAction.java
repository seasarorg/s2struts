package org.seasar.struts.action;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.annotation.backport175.StrutsAction(path="testpath", name="testname",
 *                                                             input="testinput",
 *                                                             parameter="testparameter",
 *                                                             attribute="testattribute",
 *                                                             forward="testforward",
 *                                                             include="testinclude",
 *                                                             prefix="testprefix",
 *                                                             suffix="testsuffix",
 *                                                             roles="testroles")
 */
public interface StrutsConfigAnnotationAction {

    /**
     * @org.seasar.struts.annotation.backport175.StrutsActionForward(path="/test.jsp")
     */
    String SUCCESS = "success";

    /**
     * @org.seasar.struts.annotation.backport175.StrutsActionForward(path="/fail.jsp", redirect=true)
     */
    String FAIL = "fail";

    String CONST = "const";

    public String exe();

}
