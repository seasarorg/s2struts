package org.seasar.struts.interceptors;

/**
 * @author Katsuhiko Nagashima
 */
public class TestExtendPojoForm extends TestPojoForm {

    private static final long serialVersionUID = 1L;

    public TestExtendPojoForm(String msg) {
        super("extend:" + msg);
    }

}
