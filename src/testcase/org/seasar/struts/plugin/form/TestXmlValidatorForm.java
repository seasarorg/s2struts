package org.seasar.struts.plugin.form;

import java.io.Serializable;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.annotation.StrutsActionForm(name="testXmlValidatorFormName")
 */
public class TestXmlValidatorForm implements Serializable {

    /**
     * @org.seasar.struts.validator.annotation.ValidatorField(validators=
     * { @org.seasar.struts.validator.annotation.Validator(name="intRange", vars=
     *   { @org.seasar.struts.validator.annotation.Variable(name="min", value="10")
     *   , @org.seasar.struts.validator.annotation.Variable(name="max", value="100")
     *   })
     * , @org.seasar.struts.validator.Validator(name="maxlength", value="3")
     * })
     * @org.seasar.struts.validator.annotation.Required
     * @org.seasar.struts.validator.annotation.Args(keys="form.message1")
     */
    public void setMessage1(String message2) {
        
    }

}
