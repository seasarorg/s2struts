package org.seasar.struts.lessconfig.factory;

/**
 * @author Katsuhiko Nagashima
 */
public interface TestStrutsConfigAnnotationValueAction {

    String SUCCESS1_FORWARD = "path=/test.jsp?aaa=bbb, redirect=true";
    
    String SUCCESS1 = "success";

    String SUCCESS2_FORWARD = "/test.jsp, redirect=true";
    
    String SUCCESS2 = "success";

    String SUCCESS3_FORWARD = "path=, redirect=true";
    
    String SUCCESS3 = "success";

    public String exe();

}
