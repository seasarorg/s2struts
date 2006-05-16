/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
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
