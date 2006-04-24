package org.seasar.struts.form;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.annotation.backport175.StrutsActionForm(name="testRangeFormName")
 */
public class ValidatorAnnotationRangeForm {

    /**
     * @org.seasar.struts.validator.annotation.backport175.FloatRange(min=5.0F, max=10.1F)
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="Range", resource=false)
     */
    public void setRange(String range) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.LongRange(min=5L, max=10L)
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="LongRange", resource=false)
     */
    public void setLongRange(String longRange) {
    }

}
