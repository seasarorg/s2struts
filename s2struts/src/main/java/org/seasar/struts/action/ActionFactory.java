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
package org.seasar.struts.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.util.MessageResources;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;

/**
 * @author Satoshi Kimura
 */
public interface ActionFactory {
    /**
     * S2コンテナからActionクラスのインスタンスを取得します。 <br>
     * S2コンテナに取得対象のクラスが登録されていない場合、インスタンス取得の前に、 S2コンテナにActionクラスの登録を行います。
     * 
     * @param className 取得対象のクラス名
     * @param servlet ActionにセットするActionServlet。nullの場合は、nullはセットされません。
     * @return S2コンテナから取得したActionクラスのインスタンス
     */
    Action getActionWithClassName(String className, ActionServlet servlet);

    /**
     * S2コンテナからActionクラスのインスタンスを取得します。 <br>
     * 
     * @param componentName 取得対象のコンポーネント名
     * @param servlet ActionにセットするActionServlet。nullの場合は、nullはセットされません。
     * @return S2コンテナから取得したActionクラスのインスタンス
     * @throws ComponentNotFoundRuntimeException コンポーネントが見つからない場合
     */
    Action getActionWithComponentName(String componentName, ActionServlet servlet)
            throws ComponentNotFoundRuntimeException;

    /**
     * ActionMappingを参照して、コンポーネント名からインスタンスを生成するのか、クラス名からインスタンスを生成するのかを判断して、 Actionのインスタンスを生成する。
     * 
     * @param request 処理しているHTTPリクエスト
     * @param response 生成中のHTTPレスポンス
     * @param mapping Actionのインスタンスを生成するために使用する
     * @param log ログ
     * @param internal メッセージリソース
     * @param servlet 生成されるActionに関連づけられているServletのインスタンス
     * @return S2コンテナから取得したActionクラスのインスタンス
     * @throws IOException インスタンス生成に失敗した場合に、レスポンスを操作時に発生する例外
     */
    Action processActionCreate(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping,
            Log log, MessageResources internal, ActionServlet servlet) throws IOException;

    /**
     * Get action instance from S2Container.
     * 
     * @param mapping
     * @param log
     * @param internal
     * @param servlet
     * @return POJO instance or {@see Action}instance.
     * @throws IOException
     */
    Object getActionInstance(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping, Log log,
            MessageResources internal, ActionServlet servlet) throws IOException;
}