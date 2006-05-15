package org.seasar.struts.servlet.http;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.seasar.framework.util.EnumerationAdapter;

/**
 * @author Satoshi Kimura
 */
public class S2ServletRequestWrapper extends HttpServletRequestWrapper {
	private Map parameters = new HashMap();

	public S2ServletRequestWrapper(HttpServletRequest request) {
		super(request);
		init(request);
	}

	private void init(ServletRequest request) {
		Map map = request.getParameterMap();
		parameters.putAll(map);
	}

	public void addParameterValue(String name, String value) {
		addParameterValues(name, new String[] {value});
	}

	public void addParameterValues(String name, String[] values) {
		parameters.put(name, values);
	}

	public String getParameter(String name) {
		String[] val = (String[]) parameters.get(name);
		if (val == null || val.length == 0) {
			return null;
		}
		return val[0];
	}

	public Enumeration getParameterNames() {
		return new EnumerationAdapter(parameters.keySet().iterator());
	}

	public String[] getParameterValues(String name) {
		return (String[]) parameters.get(name);
	}

	public Map getParameterMap() {
		return parameters;
	}

}
