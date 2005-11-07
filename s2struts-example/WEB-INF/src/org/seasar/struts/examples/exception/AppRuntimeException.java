package org.seasar.struts.examples.exception;

import java.text.MessageFormat;
import java.util.ResourceBundle;


public class AppRuntimeException extends RuntimeException {

	private static final Object[] EMPTY_ARGS = new Object[0];
	private static final String BUNDLE_NAME = "application";
	
	private String messageId;
	
	private Object[] args;
	
	public AppRuntimeException(String messageId) {
		this(messageId, EMPTY_ARGS);
	}

	public AppRuntimeException(String messageId, Object[] args) {
		this(messageId, args, null);
	}

	public AppRuntimeException(String messageId, Object[] args, Throwable cause) {
		initCause(cause);
		this.messageId = messageId;
		this.args = args;
	}
	
	public Object[] getArgs() {
		return args;
	}
	
	public String getMessageId() {
		return messageId;
	}
	
	public String getMessage() {
		ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);
		String pattern = bundle.getString(messageId);
		return MessageFormat.format(pattern, args);
	}
}
