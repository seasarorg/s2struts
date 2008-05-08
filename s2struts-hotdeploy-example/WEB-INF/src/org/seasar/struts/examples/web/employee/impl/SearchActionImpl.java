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

import org.seasar.struts.examples.web.CrudType;
import org.seasar.struts.examples.web.employee.SearchAction;

/**
 * @author taedium
 * 
 */
public class SearchActionImpl implements SearchAction {

    private SearchForm searchForm;

    private char crudType;

    public void setSearchForm(SearchForm searchForm) {
        this.searchForm = searchForm;
    }

    public char getCrudType() {
        return crudType;
    }

    public String goList() {
        return LIST;
    }

    public String goEditForCreate() {
        crudType = CrudType.CREATE;
        return EDIT;
    }

}
