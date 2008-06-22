package org.seasar.struts.glue;

import javax.servlet.http.HttpServletRequest;

public interface MethodNameExtracter {

    String extract(HttpServletRequest request);
}
