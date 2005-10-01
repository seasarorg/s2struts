package org.seasar.struts.form;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.DynaClass;
import org.apache.struts.validator.BeanValidatorForm;

/**
 * @author Satoshi Kimura
 */
public class InputValueForm extends BeanValidatorForm {
    private Map map = new HashMap();

    private boolean freeze = false;

    public InputValueForm() {
        super(new Object());
        super.dynaBean = null;
    }

    protected InputValueForm(Object bean) {
        super(bean);
    }

    public DynaClass getDynaClass() {
        return new StringDynaActionFormClass();
    }

    public synchronized Object get(String name) {
        Object ret = map.get(name);
        if (ret == null && !freeze) {
            ret = new InputValueForm();
            map.put(name, ret);
        }
        return ret;
    }

    public synchronized void set(String name, Object value) {
        map.put(name, value);
    }

    public void freeze() {
        this.freeze = true;
        for (Iterator ite = map.values().iterator(); ite.hasNext();) {
            Object value = ite.next();
            if (value instanceof InputValueForm) {
                ((InputValueForm)value).freeze();
            }
        }
    }

}
