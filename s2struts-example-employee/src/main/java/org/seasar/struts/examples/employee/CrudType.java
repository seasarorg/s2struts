package org.seasar.struts.examples.employee;

import java.io.Serializable;

public class CrudType implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String CREATE_MODE = "create";

    public static final String UPDATE_MODE = "update";

    public static final String DELETE_MODE = "delete";

    public static final String REFER_MODE = "refer";

    private String mode;

    public void setMode(String mode) {
        this.mode = mode.toLowerCase();
    }

    public boolean isCreateMode() {
        return CrudType.CREATE_MODE.equals(this.mode);
    }

    public boolean isUpdateMode() {
        return CrudType.UPDATE_MODE.equals(this.mode);
    }

    public boolean isDeleteMode() {
        return CrudType.DELETE_MODE.equals(this.mode);
    }

    public boolean isReferMode() {
        return CrudType.REFER_MODE.equals(this.mode);
    }

    public String toString() {
        return "[" + this.mode + "]";
    }

}
