package org.seasar.struts.glue.action;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.glue.annotation.Form;

public class AdapterActionTest extends S2TestCase {

    public void testExecute() throws Exception {
        final MyAction action = new MyAction();
        final Method method = MyAction.class.getDeclaredMethod("execute");
        final AdapterAction adapterAction = new AdapterAction(
                new ActionServlet(), action, method);
        final Field field = MyAction.class.getDeclaredField("form");
        field.setAccessible(true);
        adapterAction.setActionFormField(field);

        final ActionMapping mapping = new ActionMapping() {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public ActionForward findForward(String name) {
                ActionForward forward = new ActionForward();
                forward.setName(name);
                return forward;
            }
        };
        final MyActionForm form = new MyActionForm();
        final ActionForward forward = adapterAction.execute(mapping, form,
                getRequest(), getResponse());

        assertEquals("hoge", forward.getName());
        assertSame(form, action.form);
    }

    public static class MyAction {

        @Form
        private MyActionForm form;

        public String execute() {
            return "hoge";
        }

    }

    public static class MyActionForm extends ActionForm {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;
    }
}
