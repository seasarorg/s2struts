package org.seasar.struts.pojo.processor.commands;

import org.apache.struts.action.ActionForm;
import org.apache.struts.chain.commands.servlet.SelectAction;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.seasar.struts.lessconfig.config.rule.ActionPathNamingRule;
import org.seasar.struts.processor.support.ActionPathResolver;
import org.seasar.struts.taglib.html.ImageTag;
import org.seasar.struts.taglib.html.SubmitTag;

/**
 * {@link SubmitTag#setAction(String)}や{@link ImageTag#setAction(String)}で式が指定された場合、
 * パスをBINDING_METHODアノテーションや{@link ActionPathNamingRule}から求めます。
 * <p>
 * こうするとことで、バリデーション対象の{@link ActionForm}を適切に選択できるようになります。
 * </p>
 * 
 * @author taedium
 */
public class S2SelectAction extends SelectAction {

    protected String getPath(ActionContext context) {
        String path = super.getPath(context);
        ServletActionContext saContext = (ServletActionContext) context;
        return ActionPathResolver.resolve(saContext.getRequest(), path);
    }
}
