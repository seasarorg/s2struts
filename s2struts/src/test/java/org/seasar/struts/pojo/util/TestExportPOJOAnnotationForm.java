package org.seasar.struts.pojo.util;

import java.io.Serializable;

/**
 * @author Katsuhiko Nagashima
 */
public class TestExportPOJOAnnotationForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;

    public TestExportPOJOAnnotationForm() {

    }

    public TestExportPOJOAnnotationForm(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
