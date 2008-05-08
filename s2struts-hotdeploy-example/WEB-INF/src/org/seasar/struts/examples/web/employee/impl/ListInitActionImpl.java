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
package org.seasar.struts.examples.web.employee.impl;

import java.util.List;

import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.struts.examples.dto.EmployeeSearchDto;
import org.seasar.struts.examples.entity.Employee;
import org.seasar.struts.examples.web.employee.EmployeeLogic;
import org.seasar.struts.examples.web.employee.ListInitAction;

/**
 * @author taedium
 * 
 */
public class ListInitActionImpl implements ListInitAction {

    private EmployeeLogic employeeLogic;

    private SearchForm searchForm;

    private List<Employee> empItems;

    @Binding(bindingType = BindingType.MUST)
    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }

    public void setSearchForm(SearchForm searchForm) {
        this.searchForm = searchForm;
    }

    public List<Employee> getEmpItems() {
        return empItems;
    }

    public void initialize() {
        EmployeeSearchDto searchDto = new EmployeeSearchDto();
        Beans.copy(searchForm, searchDto).excludesNull().excludesWhitespace()
                .execute();
        empItems = employeeLogic.getEmployees(searchDto);
    }

}
