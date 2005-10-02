package org.seasar.struts.examples.echo;

import org.seasar.struts.examples.FowardNameConstants;
import org.seasar.struts.examples.form.StringForm;

/**
 * @author Satoshi Kimura
 */
public class EchoActionImpl implements EchoAction {	
    private StringForm strForm;
    
    public EchoActionImpl() {
    }

    public String echo() {
        strForm.setResult(strForm.getInput());

        return FowardNameConstants.SUCCESS;
    }

    public StringForm getStrForm() {
        return strForm;
    }
    public void setStrForm(StringForm strForm) {
        this.strForm = strForm;
    }
}