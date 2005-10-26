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