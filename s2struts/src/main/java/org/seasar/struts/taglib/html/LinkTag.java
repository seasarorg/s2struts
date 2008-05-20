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
import org.seasar.struts.Constants;
import org.seasar.struts.util.Base64ParameterUtil;
import org.seasar.struts.util.MethodBindingUtil;
import org.seasar.struts.util.ModuleConfigUtil;

/**
 * {@link org.apache.struts.taglib.html.LinkTag}を拡張したS2Struts用のタグです。
 * <p>
 * {@link org.apache.struts.taglib.html.LinkTag}の属性に加え次の3つの属性に対応しています。
 * <ul>
 * <li>path</li>
 * <li>cancel</li>
 * <li>input</li>
 * </ul>
 * </p>
 * <p>
 * action属性にはメソッドバインディング式を設定できます。
 * </p>
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class LinkTag extends org.apache.struts.taglib.html.LinkTag {

    private static final long serialVersionUID = 5426930091211790417L;

    protected String expression = null;

    protected String base64Property = null;

    protected String path = null;

    protected boolean cancel = false;

    /**
     * パスを返します。
     * 
     * @return
     */
    public String getPath() {
        return this.path;
    }

    /**
     * パスを設定します。
     * 
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * ActionFormの検証をキャンセルする場合<code>true</code>を返します。
     * 
     * @return
     */
    public boolean isCancel() {
        return this.cancel;
    }

    /**
     * ActionFormの検証をキャンセルする場合<code>true</code>を設定します。
     * 
     * @param cancel
     */
    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public void release() {
        super.release();
        this.path = null;
        this.cancel = false;
    }

    public int doStartTag() throws JspException {
        encodeParameters();
        ActionMapping mapping = null;
        if (this.path == null) {
            String componentName = MethodBindingUtil.getComponentName(this.action);
            mapping = (ActionMapping) ModuleConfigUtil.findActionConfigForComponentName(module, componentName);
        } else {
            mapping = (ActionMapping) ModuleConfigUtil.findActionConfig(module, this.path);
        }
        if (mapping == null) {
            throw new JspException("Not found ActionMapping.");
        }
        this.action = mapping.getPath();
        return super.doStartTag();
    }

    protected String calculateURL() throws JspException {
        Map params = TagUtils.getInstance().computeParameters(pageContext, paramId, paramName, paramProperty,
                paramScope, name, property, scope, transaction);

        if (params == null) {
            params = new HashMap();
        }
        params.put(this.base64Property, "");

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

    protected void encodeParameters() throws JspException {
        Map params = new HashMap();
        if (action != null) {
            StringBuffer buf = new StringBuffer();
            buf.append(action);
            if (indexed) {
                prepareIndex(buf, null);
            }
            params.put(Constants.ACTION_EXPRESSION_KEY, buf.toString());
        }
        if (cancel) {
            params.put(Constants.CANCEL_KEY, "");
        }
        if (!params.isEmpty()) {
            base64Property = Base64ParameterUtil.encode(params);
        }
    }

}
