/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.struts.examples.web.employee;

import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.struts.examples.dto.EmployeeDto;
import org.seasar.struts.examples.web.CrudType;

/**
 * @author taedium
 * 
 */
public class ConfirmInitAction extends AbstractAction {

    private EmployeeService employeeService;

    private ConfirmForm confirmForm;

    private ListForm listForm;

    private EditForm editForm;

    @Binding(bindingType = BindingType.MUST)
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setListForm(ListForm listForm) {
        this.listForm = listForm;
    }

    public void setEditForm(EditForm editForm) {
        this.editForm = editForm;
    }

    public void setConfirmForm(ConfirmForm confirmForm) {
        this.confirmForm = confirmForm;
    }

    public void init() {
        int deptno = 0;
        if (CrudType.READ.equals(crudType) || CrudType.DELETE.equals(crudType)) {
            int empno = Integer.valueOf(listForm.getEmpno());
            EmployeeDto employeeDto = employeeService.getEmployeeDto(empno);
            Beans.copy(employeeDto, confirmForm).dateConverter("yyyy/MM/dd",
                    "hiredate").execute();
            deptno = employeeDto.getDeptno();
        } else if (CrudType.CREATE.equals(crudType)
                || CrudType.UPDATE.equals(crudType)) {
            Beans.copy(editForm, confirmForm).execute();
            deptno = Integer.valueOf(editForm.getDeptno());
        }
        String dname = employeeService.getDeptno(deptno);
        confirmForm.setDname(dname);
    }
}
