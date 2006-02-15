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
package org.seasar.struts.examples.employee.action.impl;

import org.seasar.struts.examples.employee.action.EmployeeConfirmInitAction;
import org.seasar.struts.examples.employee.dto.EmployeeDto;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;
import org.seasar.struts.examples.util.StringUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
public class EmployeeConfirmInitActionImpl implements EmployeeConfirmInitAction {

    private EmployeeLogic employeeLogic;
    
    private EmployeeDto employeeForm;

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }
    
    public void setEmployeeForm(EmployeeDto employeeForm) {
        this.employeeForm = employeeForm;
    }

    public String initialize() {
        if (!StringUtil.isEmpty(employeeForm.getDeptno())) {
            String dname = employeeLogic.getDname(new Integer(employeeForm.getDeptno()));
            employeeForm.setDname(dname);
        }
        return null;
    }
    
}
