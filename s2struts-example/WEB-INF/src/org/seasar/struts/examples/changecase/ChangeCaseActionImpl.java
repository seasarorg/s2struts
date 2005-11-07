package org.seasar.struts.examples.changecase;

import org.seasar.struts.examples.FowardNameConstants;
import org.seasar.struts.examples.form.StringForm;

/**
 * @author Satoshi Kimura
 */
public class ChangeCaseActionImpl implements ChangeCaseAction {
    private StringForm strForm;

    public ChangeCaseActionImpl() {
    }

    public String toLowerCase() {
        strForm.setResult(strForm.getInput().toLowerCase());
        return FowardNameConstants.SUCCESS;
    }

    public String toUpperCase() {
        strForm.setResult(strForm.getInput().toUpperCase());
        return FowardNameConstants.SUCCESS;
    }

    public StringForm getStrForm() {
        return strForm;
    }
    public void setStrForm(StringForm strForm) {
        this.strForm = strForm;
    }
}