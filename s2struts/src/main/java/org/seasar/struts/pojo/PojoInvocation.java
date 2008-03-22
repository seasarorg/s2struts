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
package org.seasar.struts.pojo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * POJO Actionのメソッド実行を表すインタフェースです。
 * 
 * @author Katsuhiko Nagashima
 */
public interface PojoInvocation {

    /**
     * {@link HttpServletRequest}を設定します。
     * 
     * @param request
     */
    void setRequest(HttpServletRequest request);

    /**
     * {@link HttpServletRequest}を返します。
     * 
     * @return
     */
    HttpServletRequest getRequest();

    /**
     * {@link HttpServletResponse}を設定します。
     * 
     * @param response
     */
    void setResponse(HttpServletResponse response);

    /**
     * {@link HttpServletResponse}を返します。
     * 
     * @return
     */
    HttpServletResponse getResponse();

    /**
     * POJO Actionのインタフェースを設定します。
     * 
     * @param actionInterface
     */
    void setActionInterface(Class actionInterface);

    /**
     * POJO Actionのインタフェースを返します。
     * 
     * @return
     */
    Class getActionInterface();

    /**
     * POJO Actionのインスタンスを設定します。
     * 
     * @param actionInstance
     */
    void setActionInstance(Object actionInstance);

    /**
     * POJO Actionのインスタンスを返します。
     * 
     * @return
     */
    Object getActionInstance();

    /**
     * {@link ActionForm}を設定します。
     * 
     * @param form
     */
    void setActionForm(ActionForm form);

    /**
     * {@link ActionForm}を返します。
     * 
     * @return
     */
    ActionForm getActionForm();

    /**
     * {@link ActionMapping}を設定します。
     * 
     * @param mapping
     */
    void setActionMapping(ActionMapping mapping);

    /**
     * {@link ActionMapping}を返します。
     * 
     * @return
     */
    ActionMapping getActionMapping();

    /**
     * 実行します。
     * 
     * @return 実行した結果
     */
    String execute();

}
