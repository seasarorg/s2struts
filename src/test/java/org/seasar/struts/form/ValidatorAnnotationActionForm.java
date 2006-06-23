package org.seasar.struts.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

public class ValidatorAnnotationActionForm extends ValidatorForm {

    private static final long serialVersionUID = 1L;

    public List nestedFormList = new ArrayList();

    public void setNestedFormList(ValidatorAnnotationNextedActionForm[] nestedFormList) {
        this.nestedFormList = Arrays.asList(nestedFormList);
    }

    public ValidatorAnnotationNextedActionForm[] getNestedFormList() {
        return (ValidatorAnnotationNextedActionForm[]) this.nestedFormList
                .toArray(new ValidatorAnnotationNextedActionForm[nestedFormList.size()]);
    }

    public ValidatorAnnotationNextedActionForm getNestedForm(int index) {
        while (nestedFormList.size() < index + 1) {
            nestedFormList.add(new ValidatorAnnotationNextedActionForm());
        }
        return (ValidatorAnnotationNextedActionForm) nestedFormList.get(index);
    }

}
