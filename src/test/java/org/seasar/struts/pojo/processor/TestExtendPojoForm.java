package org.seasar.struts.pojo.processor;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
public class TestExtendPojoForm extends TestPojoForm {

    public TestExtendPojoForm(String message) {
        super("extend:" + message);
    }
    
}
