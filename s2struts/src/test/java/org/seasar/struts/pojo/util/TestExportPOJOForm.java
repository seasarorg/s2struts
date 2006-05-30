package org.seasar.struts.pojo.util;

import java.io.Serializable;

/**
 * @author Katsuhiko Nagashima
 */
public class TestExportPOJOForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;

    public TestExportPOJOForm() {

    }

    public TestExportPOJOForm(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
