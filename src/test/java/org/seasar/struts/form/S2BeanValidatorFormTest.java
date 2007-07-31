package org.seasar.struts.form;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.action.ResetNotDefinedPojoForm;
import org.seasar.struts.action.ResetPojoForm;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.util.BeanValidatorFormUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class S2BeanValidatorFormTest extends S2TestCase {

    public void testCalledReset() {
        ActionMapping mapping = new MockActionMapping();
        S2BeanValidatorForm form = new S2BeanValidatorForm(
                new BeanValidatorForm(new ResetPojoForm()));
        form.reset(mapping, getRequest());

        ResetPojoForm resultForm = (ResetPojoForm) BeanValidatorFormUtil
                .toBean(form);
        assertEquals("calledReset", resultForm.getMessage());
    }

    public void testNotDefinedRest() {
        ActionMapping mapping = new MockActionMapping();
        S2BeanValidatorForm form = new S2BeanValidatorForm(
                new BeanValidatorForm(new ResetNotDefinedPojoForm()));
        form.reset(mapping, getRequest());

        // Confirm that Exception don't happen.
    }

}
