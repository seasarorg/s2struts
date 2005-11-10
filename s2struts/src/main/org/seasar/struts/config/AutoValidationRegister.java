package org.seasar.struts.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.validator.Form;
import org.apache.commons.validator.FormSet;
import org.apache.commons.validator.ValidatorResources;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;
import org.seasar.struts.config.rule.ZeroConfigActionFormRule;
import org.seasar.struts.factory.StrutsConfigAnnotationHandler;
import org.seasar.struts.factory.StrutsConfigAnnotationHandlerFactory;
import org.seasar.struts.validator.factory.ValidatorAnnotationHandler;
import org.seasar.struts.validator.factory.ValidatorAnnotationHandlerFactory;

/**
 * @author Katsuhiko Nagashima
 */
public class AutoValidationRegister {

    private static final Logger log = Logger.getLogger(AutoValidationRegister.class);

    private AutoValidationRegister() {
    }

    public static void regist(ValidatorResources resources, ModuleConfig config, Collection classes) {
        FormSet formSet = new FormSet();
        for (Iterator iterator = classes.iterator(); iterator.hasNext();) {
            Class clazz = (Class) iterator.next();
            addValidation(resources, config, formSet, clazz);
        }
        if (formSet.getForms().size() != 0) {
            resources.addFormSet(formSet);
            resources.process();
        }
    }

    private static boolean registedValidation(ValidatorResources resources, String formName) {
        return resources.getForm(Locale.getDefault(), formName) != null;
    }

    private static boolean registedValidation(FormSet formSet, String formName) {
        return formSet.getForm(formName) != null;
    }

    // -- new config --

    private static void addValidation(ValidatorResources resources, ModuleConfig config, FormSet formSet,
            Class formClass) {

        ValidatorAnnotationHandler annHandler = ValidatorAnnotationHandlerFactory.getAnnotationHandler();
        String[] formNames = getFormNames(formClass, config);

        for (int i = 0; i < formNames.length; i++) {
            if (registedValidation(resources, formNames[i])) {
                continue;
            }
            if (registedValidation(formSet, formNames[i])) {
                continue;
            }

            Form form = annHandler.createForm(formNames[i], formClass);
            if (form != null && form.getFields().size() != 0) {
                formSet.addForm(form);

                log.debug("auto regist " + form);
            }
        }
    }

    private static String[] getFormNames(Class formClass, ModuleConfig config) {
        List list = new ArrayList();
        String name = null;
        FormBeanConfig[] formBeanConfigs = config.findFormBeanConfigs();
        for (int i = 0; i < formBeanConfigs.length; i++) {
            if (formClass.getName().equals(formBeanConfigs[i].getType())) {
                list.add(formBeanConfigs[i].getName());
            }
        }
        
        StrutsConfigAnnotationHandler annHandler = StrutsConfigAnnotationHandlerFactory.getAnnotationHandler();
        StrutsActionFormConfig strutsActionFormConfig = annHandler.createStrutsActionFormConfig(formClass);
        if (strutsActionFormConfig != null) {
            name = strutsActionFormConfig.name();
        } else {
            name = getRule().getName(formClass, config);
        }
        list.add(name);

        return (String[]) list.toArray(new String[list.size()]);
    }

    private static ZeroConfigActionFormRule getRule() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (ZeroConfigActionFormRule) container.getComponent(ZeroConfigActionFormRule.class);
    }

}