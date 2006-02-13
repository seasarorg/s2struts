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
			String methodName = (String) request.getParameter(key);
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
