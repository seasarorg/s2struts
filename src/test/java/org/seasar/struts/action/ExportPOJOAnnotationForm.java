package org.seasar.struts.action;

import java.io.Serializable;

/**
 * @author Katsuhiko Nagashima
 */
public class ExportPOJOAnnotationForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;

    public ExportPOJOAnnotationForm() {

    }

    public ExportPOJOAnnotationForm(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
