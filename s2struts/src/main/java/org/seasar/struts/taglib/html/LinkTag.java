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
package org.seasar.struts.taglib.html;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.taglib.TagUtils;
import org.seasar.framework.util.Base64Util;
import org.seasar.struts.util.MethodBindingUtil;
import org.seasar.struts.util.ModuleConfigUtil;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class LinkTag extends org.apache.struts.taglib.html.LinkTag {

    private static final long serialVersionUID = 5426930091211790417L;

    protected String expression = null;

    protected String encodeExpression = null;

    protected String path = null;

    protected boolean cancel = false;

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isCancel() {
        return this.cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public void release() {
        super.release();
        this.path = null;
        this.cancel = false;
    }

    public int doStartTag() throws JspException {
        this.expression = this.action;
        this.encodeExpression = Base64Util.encode(expression.getBytes());

        ActionMapping mapping = null;
        if (this.path == null) {
            String componentName = MethodBindingUtil.getComponentName(this.expression);
            mapping = (ActionMapping) ModuleConfigUtil.findActionConfigForComponentName(module, componentName);
        } else {
            mapping = (ActionMapping) ModuleConfigUtil.findActionConfig(module, this.path);
        }
        if (mapping == null) {
            throw new JspException("Not found ActionMapping.");
        }

        setMethodBindingExpression(mapping, this.encodeExpression, this.expression);
        setCancelAction(mapping, this.encodeExpression, this.expression);
        this.action = mapping.getPath();

        return super.doStartTag();
    }

    protected String calculateURL() throws JspException {
        Map params = TagUtils.getInstance().computeParameters(pageContext, paramId, paramName, paramProperty,
                paramScope, name, property, scope, transaction);

        // MethodBindigのExpressionをurlのパラメータに追加する
        if (params == null) {
            params = new HashMap();
        }
        params.put(this.encodeExpression, "");

        if (indexed) {
            int indexValue = getIndexValue();

            if (indexId != null) {
                params.put(indexId, Integer.toString(indexValue));
            } else {
                params.put("index", Integer.toString(indexValue));
            }
        }

        String url = null;

        try {
            url = TagUtils.getInstance().computeURLWithCharEncoding(pageContext, forward, href, page, action, module,
                    params, anchor, false, useLocalEncoding);
        } catch (MalformedURLException e) {
            TagUtils.getInstance().saveException(pageContext, e);
            throw new JspException(messages.getMessage("rewrite.url", e.toString()));
        }

        return (url);
    }

    protected void setMethodBindingExpression(ActionMapping mapping, String encodeExpression, String expression) {
        String mappingName = mapping.getPath();
        S2StrutsContextUtil.setMethodBindingExpression(mappingName, encodeExpression, "", expression);
    }

    protected void setCancelAction(ActionMapping mapping, String encodeExpression, String expression) {
        if (!this.cancel) {
            return;
        }
        String mappingName = mapping.getPath();
        S2StrutsContextUtil.setCancelAction(mappingName, encodeExpression, "");
    }

}
