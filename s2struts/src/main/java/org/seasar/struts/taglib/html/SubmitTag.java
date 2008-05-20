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

import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.struts.Constants;
import org.seasar.struts.util.Base64ParameterUtil;

/**
 * {@link org.apache.struts.taglib.html.SubmitTag}を拡張したS2Struts用のタグです。
 * <p>
 * {@link org.apache.struts.taglib.html.SubmitTag}の属性に加え次の3つの属性に対応しています。
 * <ul>
 * <li>indexId</li>
 * <li>action</li>
 * <li>cancel</li>
 * </ul>
 * </p>
 * 
 * @author Satoshi Kimura
 */
public class SubmitTag extends org.apache.struts.taglib.html.SubmitTag {
    private static final long serialVersionUID = 3565695013866921990L;

    protected String indexId;

    protected String action;

    protected boolean cancel;

    public int doEndTag() throws JspException {
        encodeParameters();
        int ret = super.doEndTag();
        property = null;
        return ret;
    }

    public void release() {
        super.release();
        this.indexId = null;
        this.action = null;
        this.cancel = false;
    }

    /**
     * インデックスIDを返します。
     * 
     * @return Returns the indexId.
     */
    public String getIndexId() {
        return this.indexId;
    }

    /**
     * インデックスIDを設定します。
     * 
     * @param indexId
     *            The indexName to set.
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId;

        if (indexId != null) {
            super.indexed = true;
        }
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

    protected void prepareIndex(StringBuffer handlers, String name) throws JspException {
        if (this.indexId == null) {
            super.prepareIndex(handlers, name);
            return;
        }
        Object index = super.pageContext.getAttribute(this.indexId);
        handlers.append("[");
        handlers.append(IntegerConversionUtil.toPrimitiveInt(index));
        handlers.append("]");
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