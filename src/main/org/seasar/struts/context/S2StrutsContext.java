package org.seasar.struts.context;

import java.io.Serializable;

/**
 * @author Satoshi Kimura
 */
public interface S2StrutsContext extends Serializable {
    void clear(ContentsType type);
    
    String getPath();

    void setPath(String path);

    void setMethodBindingExpression(String kye, String value, String methodBindingExpression);
    
    String getMethodBindingExpression();
}
