package org.seasar.struts.lessconfig.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class TestValidatorAnnotationActionForm extends ValidatorForm {

    private static final long serialVersionUID = 1L;

    public List nestedFormList = new ArrayList();

    public void setNestedFormList(TestValidatorAnnotationNextedActionForm[] nestedFormList) {
        this.nestedFormList = Arrays.asList(nestedFormList);
    }

    public TestValidatorAnnotationNextedActionForm[] getNestedFormList() {
        return (TestValidatorAnnotationNextedActionForm[]) this.nestedFormList
                .toArray(new TestValidatorAnnotationNextedActionForm[nestedFormList.size()]);
    }

    public TestValidatorAnnotationNextedActionForm getNestedForm(int index) {
        while (nestedFormList.size() < index + 1) {
            nestedFormList.add(new TestValidatorAnnotationNextedActionForm());
        }
        return (TestValidatorAnnotationNextedActionForm) nestedFormList.get(index);
    }

}
