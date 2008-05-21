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
package org.seasar.struts.processor.support;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.MultipartRequestWrapper;
import org.seasar.struts.util.RequestUtil;

/**
 * エンコードされたリクエストパラメータをデコードします。
 * 
 * @author taedium
 */
public class ParameterDecoder {

    /**
     * リクエストパラメータをデコードします。
     * <p>
     * リクエストがmultipart形式の場合、このメソッドは何も行いません。
     * </p>
     * 
     * @param request
     */
    public static void decode(HttpServletRequest request) {
        if (!MultipartRequestWrapper.class.isInstance(request)) {
            RequestUtil.decodeBase64Parameter(request);
        }
    }
}
