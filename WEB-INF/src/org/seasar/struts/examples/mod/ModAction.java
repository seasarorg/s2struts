package org.seasar.struts.examples.mod;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.annotation.StrutsAction(path="/mod", name="modForm",
 *                                                     scope="session",
 *                                                     validate=true,
 *                                                     input="/pages/modInput.jsp")
 */
public interface ModAction {

    /**
     * @org.seasar.struts.annotation.StrutsActionForward(path="/pages/modResult.jsp")
     */
    String SUCCESS = "success";

    String mod();

}