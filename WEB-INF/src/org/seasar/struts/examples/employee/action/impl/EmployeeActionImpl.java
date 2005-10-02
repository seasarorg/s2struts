package org.seasar.struts.examples.employee.action.impl;

import org.seasar.struts.examples.common.Constants;
import org.seasar.struts.examples.employee.action.EmployeeAction;
import org.seasar.struts.examples.employee.dto.ProcessModeDto;


/**
 * @author Katsuhiko Nagashima
 */
public class EmployeeActionImpl implements EmployeeAction {

    private ProcessModeDto processModeDto = new ProcessModeDto();

    /**
     * @org.seasar.struts.annotation.ExportToSession()
     */
    public ProcessModeDto getProcessModeDto() {
        return processModeDto;
    }

    public void setProcessModeDto(ProcessModeDto processModeDto) {
        this.processModeDto = processModeDto;
    }

    public String goError() {
        return ERROR;
    }

    public String goSearch() {
        return SEARCH;
    }

    public String goList() {
        return LIST;
    }

    public String goEditForCreate() {
        processModeDto.setProcessMode(Constants.CREATE_MODE);
        return EDIT;
    }

    public String goEditForUpdate() {
        processModeDto.setProcessMode(Constants.UPDATE_MODE);
        return EDIT;
    }

    public String goDelete() {
        processModeDto.setProcessMode(Constants.DELETE_MODE);
        return CONFIRM;
    }

    public String goInquire() {
        processModeDto.setProcessMode(Constants.REFER_MODE);
        return CONFIRM;
    }
    
    public String goConfirm() {
        return EDIT_CONFIRM;
    }
    
    public String goPreviousFromEdit() {
        switch (processModeDto.getProcessMode()) {
        case Constants.CREATE_MODE:
            return SEARCH;
        case Constants.UPDATE_MODE:
            return BACK_LIST;
        default:
            return ERROR;
        }
    }

    public String goPreviousFromConfirm() {
        switch (processModeDto.getProcessMode()) {
        case Constants.CREATE_MODE:
        case Constants.UPDATE_MODE:
            return EDIT;
        case Constants.DELETE_MODE:
        case Constants.REFER_MODE:
            return BACK_LIST;
        default:
            return ERROR;
        }
    }

    public String goStore() {
        return STORE;
    }
    
    public String goStoreToNext() {
        switch (processModeDto.getProcessMode()) {
        case Constants.CREATE_MODE:
            return SEARCH;
        case Constants.UPDATE_MODE:
        case Constants.DELETE_MODE:
            return BACK_LIST;
        default:
            return ERROR;
        }
    }

}