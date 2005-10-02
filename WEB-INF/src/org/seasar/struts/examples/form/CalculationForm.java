package org.seasar.struts.examples.form;

import org.apache.struts.action.ActionForm;

/**
 * @author Satoshi Kimura
 */
public class CalculationForm extends ActionForm {
    private int arg1_;
    private int arg2_;
    private int result_;

    public CalculationForm() {
    }

    public int getArg1() {
        return arg1_;
    }

    public void setArg1(int arg1) {
        arg1_ = arg1;
    }

    public int getArg2() {
        return arg2_;
    }

    public void setArg2(int arg2) {
        arg2_ = arg2;
    }

    public int getResult() {
        return result_;
    }

    public void setResult(int result) {
        result_ = result;
    }
}