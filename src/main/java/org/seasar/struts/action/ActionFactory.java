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
     * S2�R���e�i����Action�N���X�̃C���X�^���X��擾���܂��B <br>
     * S2�R���e�i�Ɏ擾�Ώۂ̃N���X���o�^����Ă��Ȃ��ꍇ�A�C���X�^���X�擾�̑O�ɁA
     * S2�R���e�i��Action�N���X�̓o�^��s���܂��B
     * 
     * @param className
     *            �擾�Ώۂ̃N���X��
     * @param servlet
     *            Action�ɃZ�b�g����ActionServlet�Bnull�̏ꍇ�́Anull�̓Z�b�g����܂���B
     * @return S2�R���e�i����擾����Action�N���X�̃C���X�^���X
     */
    Action getActionWithClassName(String className, ActionServlet servlet);

    /**
     * S2�R���e�i����Action�N���X�̃C���X�^���X��擾���܂��B <br>
     * 
     * @param componentName
     *            �擾�Ώۂ̃R���|�[�l���g��
     * @param servlet
     *            Action�ɃZ�b�g����ActionServlet�Bnull�̏ꍇ�́Anull�̓Z�b�g����܂���B
     * @return S2�R���e�i����擾����Action�N���X�̃C���X�^���X
     * @throws ComponentNotFoundRuntimeException
     *             �R���|�[�l���g��������Ȃ��ꍇ
     */
    Action getActionWithComponentName(String componentName,
            ActionServlet servlet) throws ComponentNotFoundRuntimeException;

    /**
     * ActionMapping��Q�Ƃ��āA�R���|�[�l���g������C���X�^���X�𐶐�����̂��A�N���X������C���X�^���X�𐶐�����̂��𔻒f���āA
     * Action�̃C���X�^���X�𐶐�����B
     * 
     * @param request
     *            �������Ă���HTTP���N�G�X�g
     * @param response
     *            ��������HTTP���X�|���X
     * @param mapping
     *            Action�̃C���X�^���X�𐶐����邽�߂Ɏg�p����
     * @param log
     *            ���O
     * @param internal
     *            ���b�Z�[�W���\�[�X
     * @param servlet
     *            ���������Action�Ɋ֘A�Â����Ă���Servlet�̃C���X�^���X
     * @return S2�R���e�i����擾����Action�N���X�̃C���X�^���X
     * @throws IOException
     *             �C���X�^���X�����Ɏ��s�����ꍇ�ɁA���X�|���X�𑀍쎞�ɔ��������O
     */
    Action processActionCreate(HttpServletRequest request,
            HttpServletResponse response, ActionMapping mapping, Log log,
            MessageResources internal, ActionServlet servlet)
            throws IOException;

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
    Object getActionInstance(HttpServletRequest request,
            HttpServletResponse response, ActionMapping mapping, Log log,
            MessageResources internal, ActionServlet servlet)
            throws IOException;
}