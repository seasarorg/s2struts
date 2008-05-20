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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.seasar.struts.Constants;
import org.seasar.struts.util.Base64ParameterUtil;

/**
 * {@link org.apache.struts.taglib.html.ImageTag}を拡張したS2Struts用のタグです。
 * <p>
 * {@link org.apache.struts.taglib.html.ImageTag}の属性に加え次の2つの属性に対応しています。
 * <ul>
 * <li>action</li>
 * <li>cancel</li>
 * </ul>
 * </p>
 * 
 * @author Satoshi Kimura
 */
public class ImageTag extends org.apache.struts.taglib.html.ImageTag {
    private static final long serialVersionUID = -1259226695386015865L;

    protected String action;

    protected boolean cancel = false;

    public int doEndTag() throws JspException {
        encodeParameters();
        return super.doEndTag();
    }

    public void release() {
        super.release();
        this.action = null;
    }

    /**
     * メソッドバインディング式を返します。
     * 
     * @return
     */
    public String getAction() {
        return this.action;
    }

    /**
     * メソッドバインディング式を設定します。
     * 
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
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
            property = Base64ParameterUtil.encode(params);
        }
    }
}
