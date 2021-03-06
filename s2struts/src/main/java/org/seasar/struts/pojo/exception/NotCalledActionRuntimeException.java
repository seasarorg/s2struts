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
package org.seasar.struts.pojo.exception;

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.exception.SRuntimeException;

/**
 * Actionのメソッドが実行されなかった場合にスローされる実行時例外です。
 * 
 * @author Katsuhiko Nagashima
 */
public class NotCalledActionRuntimeException extends SRuntimeException {

    private static final long serialVersionUID = 7963592157999151321L;

    /**
     * インスタンスを構築します。
     */
    public NotCalledActionRuntimeException(ActionMapping actionMapping) {
        super("ESTR0001", new Object[] { actionMapping });
    }

}
