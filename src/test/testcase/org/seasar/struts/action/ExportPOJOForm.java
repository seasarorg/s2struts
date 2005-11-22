package org.seasar.struts.action;

import java.io.Serializable;

/**
 * @author Katsuhiko Nagashima
 */
public class ExportPOJOForm implements Serializable {

	private String message;
    
    public ExportPOJOForm() {
        
    }

	public ExportPOJOForm(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
