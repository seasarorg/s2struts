package org.seasar.struts.interceptors;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.Constants;
import org.seasar.struts.processor.ExternalRequestProcessor;
import org.seasar.struts.validator.S2BeanValidatorForm;

/**
 * <component class="org.seasar.struts.processor.S2RequestProcessor">
 *     <aspect pointcut="processActionForm">
 *         <component class="org.seasar.struts.interceptors.ProcessPojoFormInterceptor"/>
 *     </aspect>
 * </component>
 * 
 * @author Katsuhiko Nagashima
 */
public class ProcessPojoFormInterceptor extends AbstractInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {
        ActionMapping mapping = getMapping(invocation);
        ModuleConfig moduleConfig = getModuleConfig(invocation);

        if (isPojoForm(mapping, moduleConfig)) {
            HttpServletRequest request = getRequest(invocation);
            ActionServlet servlet = getServlet(invocation);
            return processPojoForm(request, mapping, moduleConfig, servlet);
        } else {
            return invocation.proceed();
        }
    }

    private boolean isPojoForm(ActionMapping mapping, ModuleConfig moduleConfig)
            throws Throwable {

        FormBeanConfig formConfig = moduleConfig.findFormBeanConfig(mapping
                .getName());
        
        if(formConfig == null) {
            return false;
        }
        
        Class fromClass = RequestUtils.applicationClass(formConfig.getType());

        return !(ActionForm.class.isAssignableFrom(fromClass));
    }
    
    private ActionForm processPojoForm(HttpServletRequest request,
            ActionMapping mapping, ModuleConfig moduleConfig,
            ActionServlet servlet) {
        
        ActionForm instance = createPojoForm(request, mapping, moduleConfig, servlet);
        if (instance == null) {
            return null;
        }
        
        if (Constants.REQUEST.equals(mapping.getScope())) {
            request.setAttribute(mapping.getAttribute(), instance);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute(mapping.getAttribute(), instance);
        }
        return instance; 
        
    }

    private ActionForm createPojoForm(HttpServletRequest request,
            ActionMapping mapping, ModuleConfig moduleConfig,
            ActionServlet servlet) {

        String attribute = mapping.getAttribute();
        if (attribute == null) {
            return (null);
        }

        String name = mapping.getName();
        FormBeanConfig config = moduleConfig.findFormBeanConfig(name);
        if (config == null) {
            return (null);
        }

        ActionForm instance = lookupPojoForm(request, attribute, mapping
                .getScope());

        if (instance != null && instance instanceof SerializeBeanValidatorForm) {
            return (instance);
        }

        instance = RequestUtils.createActionForm(config, servlet);

        return new SerializeBeanValidatorForm((BeanValidatorForm) instance, servlet);
    }

    private ActionForm lookupPojoForm(HttpServletRequest request,
            String attribute, String scope) {

        ActionForm instance = null;
        HttpSession session = null;
        if ("request".equals(scope)) {
            instance = (ActionForm) request.getAttribute(attribute);
        } else {
            session = request.getSession();
            instance = (ActionForm) session.getAttribute(attribute);
        }

        return (instance);
    }

    //
    //
    //

    private HttpServletRequest getRequest(MethodInvocation invocation) {
        return (HttpServletRequest) invocation.getArguments()[0];
    }

    private ActionMapping getMapping(MethodInvocation invocation) {
        return (ActionMapping) invocation.getArguments()[2];
    }

    private ModuleConfig getModuleConfig(MethodInvocation invocation) {
    	ExternalRequestProcessor processor = (ExternalRequestProcessor) invocation
                .getThis();
        return processor.getModuleConfig();
    }

    private ActionServlet getServlet(MethodInvocation invocation) {
    	ExternalRequestProcessor processor = (ExternalRequestProcessor) invocation
                .getThis();
        return processor.getActionServlet();
    }

    //
    // serialize可能にするためのクラス再定義
    //

    public static class SerializeBeanValidatorForm extends S2BeanValidatorForm {

        protected Object bean = null;

        public SerializeBeanValidatorForm(BeanValidatorForm form,
                ActionServlet servlet) {

            // WrapDynaBeanをフィールドで持つとSerializeできなくなる。
            // それを回避するために
            // getDynaBean()のときに毎回WrapDynaBeanを生成するようにする。
            // よって、dynaBeanは利用しないので、nullに初期化する。
            super(form);
            this.dynaBean = null;

            this.bean = ((WrapDynaBean) form.getDynaBean()).getInstance();
            this.servlet = servlet;
        }

        // ------------------- Public Methods ----------------------------------
        
        public void initBean(Object beanObject) {
            this.bean = beanObject;
        }

        public DynaBean getDynaBean() {
            return new WrapDynaBean(this.bean);
        }

        public Object getInstance() {

            if (getDynaBean() instanceof WrapDynaBean) {
                return ((WrapDynaBean) getDynaBean()).getInstance();
            }

            return this.dynaBean;

        }

        public int size(String name) {

            Object value = getDynaBean().get(name);
            if (value == null) {
                return 0;
            }

            if (value instanceof Map) {
                return ((Map) value).size();
            }

            if (value instanceof List) {
                return ((List) value).size();
            }

            if ((value.getClass().isArray())) {
                return Array.getLength(value);
            }

            return 0;

        }

        // ------------------- DynaBean Methods

        public DynaClass getDynaClass() {
            return getDynaBean().getDynaClass();
        }

        public Object get(String name) {
            return getDynaBean().get(name);
        }

        public Object get(String name, int index) {
            return getDynaBean().get(name, index);
        }

        public Object get(String name, String key) {
            return getDynaBean().get(name, key);
        }

        public void set(String name, Object value) {

            // Set the page number (for validator)
            if ("page".equals(name)) {

                if (value == null) {
                    this.page = 0;
                } else if (value instanceof Integer) {
                    this.page = ((Integer) value).intValue();
                } else {
                    try {
                        this.page = ((Integer) ConvertUtils.convert(
                                value.toString(), Integer.class)).intValue();
                    } catch (RuntimeException ignore) {
                        this.page = 0;
                    }
                }
            }

            getDynaBean().set(name, value);

        }

        public void set(String name, int index, Object value) {
            getDynaBean().set(name, index, value);
        }

        public void set(String name, String key, Object value) {
            getDynaBean().set(name, key, value);
        }

        public boolean contains(String name, String key) {
            return getDynaBean().contains(name, key);
        }

        public void remove(String name, String key) {
            getDynaBean().remove(name, key);
        }

    }

}