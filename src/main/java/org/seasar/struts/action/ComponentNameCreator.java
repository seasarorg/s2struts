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

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.container.S2Container;

/**
 * ActionMapping����A�R���|�[�g����쐬���邽�߂̃C���^�[�t�F�[�X�B
 * 
 * @author Satoshi Kimura
 */
public interface ComponentNameCreator {
    /**
     * ActionMapping����R���|�[�l���g����A�쐬���A���̃R���|�[�l���g�����g�p�Ώۂ̃R���e�i�Ɍ�����Ȃ���΁A
     * path��R���|�[�l���g���Ƃ��ĕԂ��B
     * 
     * @param container
     *            ����Ώۂ̃R���e�i
     * @param mapping
     *            �R���e�i����쐬����̂Ɏg�p����}�b�s���O
     * @return �R���e�i��
     */
    String createComponentName(S2Container container, ActionMapping mapping);
}