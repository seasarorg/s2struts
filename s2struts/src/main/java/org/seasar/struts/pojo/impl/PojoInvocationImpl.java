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
package org.seasar.struts.pojo.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.seasar.struts.pojo.PojoCommand;
import org.seasar.struts.pojo.PojoInvocation;
import org.seasar.struts.pojo.exception.NotCalledActionRuntimeException;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class PojoInvocationImpl implements PojoInvocation {

    private HttpServletRequest request;

    private HttpServletResponse response;

    private ActionMapping mapping;

    private ActionForm form;

    private Class actionInterface;

    private Object actionInstance;

    private List pojoCommands;

    private int index = 0;

    /**
     * インスタンスを生成します。
     * 
     * @param pojoCommands
     *            POJOを処理するコマンドのリスト
     * @param mapping
     *            {@link ActionMapping}
     * @param actionInterface
     *            POJO Actionのインタフェース
     * @param actionInstance
     *            POJO Actionのインスタンス
     * @param form
     *            {@link ActionForm}
     * @param request
     *            リクエスト
     * @param response
     *            レスポンス
     */
    public PojoInvocationImpl(List pojoCommands, ActionMapping mapping, Class actionInterface, Object actionInstance,
            ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        this.pojoCommands = pojoCommands;
        this.mapping = mapping;
        this.actionInterface = actionInterface;
        this.actionInstance = actionInstance;
        this.form = form;
        this.request = request;
        this.response = response;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getResponse() {
        return this.response;
    }

    public void setActionInterface(Class actionInterface) {
        this.actionInterface = actionInterface;
    }

    public Class getActionInterface() {
        return this.actionInterface;
    }

    public void setActionInstance(Object actionInstance) {
        this.actionInstance = actionInstance;
    }

    public Object getActionInstance() {
        return this.actionInstance;
    }

    public void setActionForm(ActionForm form) {
        this.form = form;
    }

    public ActionForm getActionForm() {
        return this.form;
    }

    public void setActionMapping(ActionMapping mapping) {
        this.mapping = mapping;
    }

    public ActionMapping getActionMapping() {
        return this.mapping;
    }

    public String execute() {
        if (this.index < this.pojoCommands.size()) {
            PojoCommand command = (PojoCommand) pojoCommands.get(this.index);
            this.index++;
            return command.execute(this);
        }
        throw new NotCalledActionRuntimeException(mapping);
    }

}
