package org.seasar.struts.processor.commands;

import org.apache.struts.chain.commands.ActionCommandBase;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * S2ContainerにHttpServletRequestを設定しなおす。
 * 
 * Struts内でHttpServletRequestをMultipartRequestWrapperにラップしている場合があるため。
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class SetHttpServletRequest extends ActionCommandBase {

    public boolean execute(ActionContext actionContext) throws Exception {
        ServletActionContext saContext = (ServletActionContext) actionContext;
        S2StrutsContextUtil.setRequest(saContext.getRequest());
        return false;
    }

}
