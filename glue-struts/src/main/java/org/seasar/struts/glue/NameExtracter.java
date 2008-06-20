package org.seasar.struts.glue;

import javax.servlet.http.HttpServletRequest;

public interface NameExtracter {

    String extractComponentName(String value);

    String extractMethodName(String value);

    String extractMethodName(HttpServletRequest request);
}
