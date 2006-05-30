package org.seasar.struts.pojo.form;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.pojo.form.S2BeanValidatorForm;
import org.seasar.struts.pojo.util.BeanValidatorFormUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class S2BeanValidatorFormTest extends S2TestCase {

    public void testCalledReset() {
        ActionMapping mapping = new MockActionMapping();
        S2BeanValidatorForm form = new S2BeanValidatorForm(
                new BeanValidatorForm(new TestResetPojoForm()));
        form.reset(mapping, getRequest());
        
        TestResetPojoForm resultForm = (TestResetPojoForm) BeanValidatorFormUtil.toBean(form);
        assertEquals("calledReset", resultForm.getMessage());
    }
    
    public void testNotDefinedRest() {
        ActionMapping mapping = new MockActionMapping();
        S2BeanValidatorForm form = new S2BeanValidatorForm(
                new BeanValidatorForm(new TestResetNotDefinedPojoForm()));
        form.reset(mapping, getRequest());
        
        // Confirm that Exception don't happen.
    }

}
