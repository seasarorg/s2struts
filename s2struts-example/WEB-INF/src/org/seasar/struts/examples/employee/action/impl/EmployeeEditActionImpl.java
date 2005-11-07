package org.seasar.struts.examples.employee.action.impl;

import java.util.List;

import org.seasar.struts.action.MessageManager;
import org.seasar.struts.examples.common.Constants;
import org.seasar.struts.examples.employee.action.EmployeeEditAction;
import org.seasar.struts.examples.employee.dto.EmployeeDto;
import org.seasar.struts.examples.employee.dto.ProcessModeDto;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;

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

    public String goConfirm() {
        if (processModeDto.getProcessMode() == Constants.CREATE_MODE
                && employeeLogic.existEmployee(new Integer(employeeForm.getEmpno()))) {
            MessageManager.addError("errors.employee.exist", employeeForm.getEmpno());
            MessageManager.saveErrors();
            return ERROR;
        }
        return CONFIRM;
    }

    public String goPrevious() {
        switch (processModeDto.getProcessMode()) {
        case Constants.CREATE_MODE:
            return SEARCH;
        case Constants.UPDATE_MODE:
            return LIST;
        default:
            return null;
        }
    }

}