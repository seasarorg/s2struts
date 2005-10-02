package org.seasar.struts.examples.employee.action.impl;

import java.util.List;

import org.seasar.struts.examples.common.Constants;
import org.seasar.struts.examples.employee.action.EmployeeEditAction;
import org.seasar.struts.examples.employee.dto.EmployeeDto;
import org.seasar.struts.examples.employee.dto.ProcessModeDto;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;
import org.seasar.struts.examples.util.StringUtil;


/**
 * @author Katsuhiko Nagashima
 */
public class EmployeeEditActionImpl implements EmployeeEditAction {

    private EmployeeLogic employeeLogic;

    private EmployeeDto employeeForm;

    private ProcessModeDto processModeDto;

    private List departmentDtoList;

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }

    public EmployeeDto getEmployeeForm() {
        return employeeForm;
    }

    public void setEmployeeForm(EmployeeDto employeeForm) {
        this.employeeForm = employeeForm;
    }

    public void setProcessModeDto(ProcessModeDto processModeDto) {
        this.processModeDto = processModeDto;
    }

    public List getDepartmentDtoList() {
        return departmentDtoList;
    }

    public String execute() {
        departmentDtoList = employeeLogic.getAllDepartments();
        if (processModeDto.getProcessMode() == Constants.UPDATE_MODE) {
            
            // TODO ñ{ìñÇÕ if(!employeeForm.loaded()) {} ÇÃÇÊÇ§Ç»ä¥Ç∂Ç…ÇµÇΩÇ¢ÅB
            if (StringUtil.isEmpty(employeeForm.getEname())) {
                employeeForm = employeeLogic.getEmployeeDto(new Integer(
                        employeeForm.getEmpno()));
            }
        }
        return SUCCESS;
    }

}