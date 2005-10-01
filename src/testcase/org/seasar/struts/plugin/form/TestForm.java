package org.seasar.struts.plugin.form;

/**
 * @author Satoshi Kimura
 * @org.seasar.struts.annotation.StrutsActionForm(name="testFormName")
 */
public class TestForm {
    
    public TestForm() {
    }
    
    /**
     * @org.seasar.struts.validator.annotation.ValidatorField(validators=
     * { @org.seasar.struts.validator.annotation.Validator(name="intRange", vars=
     *   { @org.seasar.struts.validator.annotation.Variable(name="min", value="10")
     *   , @org.seasar.struts.validator.annotation.Variable(name="max", value="100")
     *   })
     * , @org.seasar.struts.validator.annotation.Validator(name="integer")
     * , @org.seasar.struts.validator.annotation.Validator(name="maxlength", vars=
     *   { @org.seasar.struts.validator.annotation.Variable(name="maxlength", value="3")
     *   })
     * })
     * @org.seasar.struts.validator.annotation.Required
     * @org.seasar.struts.validator.annotation.Args(keys="form.message1")
     */
    public void setMessage1(String message1) {
        
    }

    /**
     * @org.seasar.struts.validator.annotation.ValidatorField(validators=
     * { @org.seasar.struts.validator.annotation.Validator(name="intRange", vars=
     *   { @org.seasar.struts.validator.annotation.Variable(name="min", value="10")
     *   , @org.seasar.struts.validator.annotation.Variable(name="max", value="100")
     *   })
     * , @org.seasar.struts.validator.annotation.Validator(name="integer")
     * , @org.seasar.struts.validator.annotation.Validator(name="maxlength", value="3")
     * })
     * @org.seasar.struts.validator.annotation.Required
     * @org.seasar.struts.validator.annotation.Args(keys="form.message2")
     */
    public void setMessage2(String message2) {
        
    }

    /**
     * @org.seasar.struts.validator.annotation.ValidatorField(validators=
     * { @org.seasar.struts.validator.annotation.Validator(name="intRange", vars=
     *   { @org.seasar.struts.validator.annotation.Variable(name="min", value="10")
     *   , @org.seasar.struts.validator.annotation.Variable(name="max", value="100")
     *   })
     * , @org.seasar.struts.validator.annotation.Validator(name="integer")
     * , @org.seasar.struts.validator.annotation.Validator(name="maxlength", value="3")
     * })
     * @org.seasar.struts.validator.annotation.Required
     * @org.seasar.struts.validator.annotation.Args(keys="form.message3")
     */
    public void setMessage3(String message2) {
        
    }

}