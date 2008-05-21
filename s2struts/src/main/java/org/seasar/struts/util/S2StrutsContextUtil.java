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
package org.seasar.struts.util;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ForwardConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.Constants;
import org.seasar.struts.context.ContentsType;
import org.seasar.struts.context.S2StrutsApplContext;
import org.seasar.struts.context.S2StrutsContext;
import org.seasar.struts.pojo.util.IndexedUtil;

/**
 * {@link S2StrutsContext}のためのユーティリティクラスです。
 * 
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public abstract class S2StrutsContextUtil {

    /**
     * <code>type</code>に対応するコンテキスト情報をクリアします。
     * 
     * @param type
     */
    public static void clear(ContentsType type) {
        getApplContext().clear(type);
    }

    /**
     * フォワード先のパスを設定します。
     * 
     * @param forward
     */
    public static void setPath(ForwardConfig forward) {
        getContext().setPath(forward.getPath());
    }

    /**
     * パスを設定します。
     * 
     * @param path
     */
    public static void setPath(String path) {
        getContext().setPath(path);
    }

    /**
     * 現在の入力パスを設定します。
     * 
     * @return
     */
    public static String getCurrentInputPath() {
        return getContext().getCurrentInputPath();
    }

    /**
     * 前回の入力パスを設定します。
     * 
     * @return
     */
    public static String getPreviousInputPath() {
        return getContext().getPreviousInputPath();
    }

    /**
     * ページ名をクリアします。
     */
    public static void clearPageNameElementValue() {
        getContext().clearPageNameElementValue();
    }

    /**
     * メソッドバインディング式を設定します。
     * 
     * @param mappingName
     * @param key
     * @param value
     * @param methodBindingExpression
     */
    public static void setMethodBindingExpression(String mappingName, String key, String value,
            String methodBindingExpression) {
        getApplContext().setMethodBindingExpression(mappingName, key, value, methodBindingExpression);
    }

    /**
     * メソッドバインディング式を返します。
     * 
     * @param mappingName
     * @param key
     * @param value
     * @return
     */
    public static String getMethodBindingExpression(String mappingName, String key, String value) {
        return getApplContext().getMethodBindingExpression(mappingName, key, value);
    }

    /**
     * 検証がキャンセルされたかどうかを返します。
     * 
     * @param request
     * @param mapping
     * @return
     */
    public static boolean isCancel(HttpServletRequest request, ActionMapping mapping) {
        if (request.getAttribute(Constants.CANCEL_KEY) != null) {
            return true;
        }
        for (Enumeration paramNames = request.getParameterNames(); paramNames.hasMoreElements();) {
            String key = (String) paramNames.nextElement();
            String value = request.getParameter(key);
            Boolean cancel = S2StrutsContextUtil.isCancelAction(mapping.getPath(), key, value);
            if (cancel != null) {
                return cancel.booleanValue();
            }

            // image tag
            String imageKey = key.replaceFirst("(\\.x$)|(\\.y$)", "");
            cancel = S2StrutsContextUtil.isCancelAction(mapping.getPath(), imageKey, null);
            if (cancel != null) {
                return cancel.booleanValue();
            }

            // indexed
            if (IndexedUtil.isIndexedParameter(key)) {
                String indexedKey = IndexedUtil.getParameter(key);
                cancel = S2StrutsContextUtil.isCancelAction(mapping.getPath(), indexedKey, value);
                if (cancel != null) {
                    return cancel.booleanValue();
                }
            }
        }
        return false;
    }

    /**
     * 検証がキャンセルされたかどうかを返します。
     * 
     * @param mappingName
     * @param key
     * @param value
     * @return
     */
    public static Boolean isCancelAction(String mappingName, String key, String value) {
        return getApplContext().isCancelAction(mappingName, key, value);
    }

    /**
     * 検証がキャンセルされたかどうかを設定します。
     * 
     * @param mappingName
     * @param key
     * @param value
     */
    public static void setCancelAction(String mappingName, String key, String value) {
        getApplContext().setCancelAction(mappingName, key, value);
    }

    private static S2StrutsContext getContext() {
        return (S2StrutsContext) getContainer().getComponent(S2StrutsContext.class);
    }

    private static S2StrutsApplContext getApplContext() {
        return (S2StrutsApplContext) getContainer().getComponent(S2StrutsApplContext.class);
    }

    private static S2Container getContainer() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * {@link ServletContext}を返します。
     * 
     * @return
     */
    public static ServletContext getServletContext() {
        return (ServletContext) getContainer().getComponent(ServletContext.class);
    }

    /**
     * {@link HttpServletResponse}を返します。
     * 
     * @param container
     * @return
     */
    public static HttpServletResponse getResponse(S2Container container) {
        return (HttpServletResponse) container.getExternalContext().getResponse();
    }

    /**
     * {@link HttpServletResponse}を返します。
     * 
     * @return
     */
    public static HttpServletResponse getResponse() {
        return getResponse(getContainer());
    }

    /**
     * {@link HttpServletRequest}を返します。
     * 
     * @param container
     * @return
     */
    public static HttpServletRequest getRequest(S2Container container) {
        return (HttpServletRequest) container.getExternalContext().getRequest();
    }

    /**
     * {@link HttpServletRequest}を返します。
     * 
     * @return
     */
    public static HttpServletRequest getRequest() {
        return getRequest(getContainer());
    }

    /**
     * {@link HttpSession}を返します。
     * 
     * @param container
     * @return
     */
    public static HttpSession getSession(S2Container container) {
        return (HttpSession) container.getExternalContext().getSession();
    }

    /**
     * {@link HttpSession}を返します。
     * 
     * @return
     */
    public static HttpSession getSession() {
        return getSession(getContainer());
    }

    /**
     * リクエストコンテキストを設定します。
     * 
     * @param container
     * @param request
     */
    public static void setRequest(S2Container container, Object request) {
        container.getExternalContext().setRequest(request);
    }

    /**
     * リクエストコンテキストを設定します。
     * 
     * @param request
     */
    public static void setRequest(Object request) {
        setRequest(getContainer(), request);
    }

}
