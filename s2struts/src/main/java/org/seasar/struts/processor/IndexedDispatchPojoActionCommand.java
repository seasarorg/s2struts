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
package org.seasar.struts.processor;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.util.MethodUtil;
import org.seasar.struts.util.IndexedUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class IndexedDispatchPojoActionCommand implements PojoActionCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response, Class actionInterface, Object action,
			Object form, ActionMapping mapping) {

		String param = mapping.getParameter();
		if (param == null) {
			return NOT_EXECUTE;
		}

		Method[] methods = actionInterface.getMethods();
		if (methods.length < 2) {
			return NOT_EXECUTE;
		}

		for (Enumeration paramNames = request.getParameterNames(); paramNames.hasMoreElements();) {
			String key = (String) paramNames.nextElement();
			String methodName = request.getParameter(key);
			if (IndexedUtil.isIndexedParameter(key)) {
				String indexedParam = IndexedUtil.getParameter(key);
				if (param.equals(indexedParam)) {
					int index = IndexedUtil.getIndex(key);
					Method method = getMethod(methods, methodName);
					if (method != null) {
						return (String) MethodUtil.invoke(method, action,
								new Object[] { new Integer(index) });
					}
				}
			}
		}

		return NOT_EXECUTE;
	}
	
	private Method getMethod(Method[] methods, String methodName) {
    	for (int i = 0; i < methods.length; i++) {
    		Method method = methods[i];
    		if (method.getName().equals(methodName)
					&& method.getParameterTypes().length == 1
					&& method.getParameterTypes()[0].equals(Integer.TYPE)) {
				return method;
			}
    	}
		return null;
	}

}
