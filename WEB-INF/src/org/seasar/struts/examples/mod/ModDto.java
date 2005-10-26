package org.seasar.struts.examples.mod;

import java.io.Serializable;

import org.seasar.struts.annotation.tiger.StrutsActionForm;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsActionForm(name = "modForm")
public class ModDto implements Serializable {

    private String arg1;

    private String arg2;

    private int result;

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public int getIntArg1() {
        try {
            return Integer.parseInt(arg1);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public int getIntArg2() {
        try {
            return Integer.parseInt(arg2);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

}