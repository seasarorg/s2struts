package org.seasar.httpunit.mock;

import javax.servlet.http.HttpSession;

import org.seasar.framework.util.ClassUtil;
/**
 * @author Satoshi Kimura
 */
public interface MockHttpSession extends HttpSession {
    String METHOD_NAME_IS_VALID = ClassUtil.getMethod(MockHttpSession.class, "isValid", null).getName();
    String METHOD_NAME_SET_VALID = ClassUtil.getMethod(MockHttpSession.class, "setValid", new Class[]{Boolean.TYPE})
            .getName();

    /**
     * {@link MockHttpSession#invalidate()}が呼ばれたか確認します。
     * 
     * @return 呼ばれた場合：true、呼ばれていない場合：false
     */
    boolean isValid();

    /**
     * {@link MockHttpSession#invalidate()}が呼ばれたときに、内部的に、このメソッドを呼びます。
     * 
     * @param valid {@link MockHttpSession#invalidate()}が呼ばれたときに、true
     */
    void setValid(boolean valid);
}