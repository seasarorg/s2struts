package org.seasar.struts.examples.mod;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.annotation.StrutsAction(path="/modInput", name="modForm",
 *                                                          scope="session",
 *                                                          validate=false)
 */
public interface ModInputAction {

	/**
	 * @org.seasar.struts.annotation.StrutsActionForward(path="/pages/modInput.jsp")
	 */
	String SUCCESS = "success";

	String input();

}