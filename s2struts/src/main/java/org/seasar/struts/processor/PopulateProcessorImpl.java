package org.seasar.struts.processor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.seasar.struts.Constants;
import org.seasar.struts.servlet.http.S2ServletRequestWrapper;

/**
 * @author Satoshi Kimura
 */
public class PopulateProcessorImpl implements PopulateProcessor {
	private ExternalRequestProcessor requestProcessor;

	public void processPopulate(HttpServletRequest request, HttpServletResponse response, ActionForm form, ActionMapping mapping)
			throws ServletException {
		request = new S2ServletRequestWrapper(request);
		addParameterForCheckBox(request);
		this.requestProcessor.processPopulate(request, response, form, mapping);
	}

	private void addParameterForCheckBox(HttpServletRequest request) {
		Set paramNameSet = new HashSet();
		paramNameSet.addAll(request.getParameterMap().keySet());
		for (Iterator paramNames = paramNameSet.iterator(); paramNames.hasNext();) {
			String paramName = (String) paramNames.next();
			if (paramName.startsWith(Constants.CHECKBOX_NAME)) {
				String checkboxParamName = paramName.substring(Constants.CHECKBOX_NAME.length());
				String checkboxValue = request.getParameter(checkboxParamName);
				if (checkboxValue == null) {
					addParam(request, checkboxParamName, Boolean.FALSE.toString());
				}
			}
		}
	}

	private void addParam(HttpServletRequest request, String paramName, String value) {
		if (request instanceof S2ServletRequestWrapper) {
			S2ServletRequestWrapper requestWrapper = (S2ServletRequestWrapper) request;
			requestWrapper.addParameterValue(paramName, value);
		}
	}

	public void setRequestProcessor(ExternalRequestProcessor requestProcessor) {
		this.requestProcessor = requestProcessor;
	}

}
