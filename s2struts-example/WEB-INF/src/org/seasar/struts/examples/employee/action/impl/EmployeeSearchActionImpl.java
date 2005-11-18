/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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
package org.seasar.struts.examples.employee.action.impl;

import org.seasar.struts.annotation.tiger.ExportToSession;
import org.seasar.struts.examples.common.Constants;
import org.seasar.struts.examples.employee.action.EmployeeSearchAction;
import org.seasar.struts.examples.employee.dto.ProcessModeDto;


/**
 * @author Katsuhiko Nagashima
 */
public class EmployeeSearchActionImpl implements EmployeeSearchAction {

    private ProcessModeDto processModeDto = new ProcessModeDto();

    @ExportToSession
    public ProcessModeDto getProcessModeDto() {
        return this.processModeDto;
    }
    
    public String goList() {
        return LIST;
    }
    
    public String goEditForCreate() {
        this.processModeDto.setProcessMode(Constants.CREATE_MODE);
        return EDIT;
    }
    
}