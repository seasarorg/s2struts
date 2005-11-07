package org.seasar.struts.validator.config;

import java.util.Map;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Msg;
import org.apache.commons.validator.Var;

/**
 * @author Satoshi Kimura
 */
public class MaskConfigRegisterImpl implements ConfigRegister {

    public void regist(Field field, Map parameter) {
        String pattern = (String) parameter.get("pattern");
        String messageKey = (String) parameter.get("messageKey");
        
        Var var = new Var();
        var.setName("mask");
        var.setValue(pattern);
        field.addVar(var);

        Msg message = new Msg();
        message.setName("mask");
        message.setKey(messageKey);
        field.addMsg(message);
    }

}
