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

import java.util.ArrayList;
import java.util.List;

import org.seasar.struts.examples.entity.Department;
import org.seasar.struts.examples.web.employee.SearchInitAction;

/**
 * @author taedium
 * 
 */
public class SearchInitActionImpl implements SearchInitAction {

    private List<Department> departments;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public String initialize() {
        departments = new ArrayList<Department>();
        Department department = new Department();
        department.setDeptno("10");
        department.setDname("Aaa");
        departments.add(department);
        Department department2 = new Department();
        department2.setDeptno("20");
        department2.setDname("Bbb");
        departments.add(department2);
        return null;
    }
}
