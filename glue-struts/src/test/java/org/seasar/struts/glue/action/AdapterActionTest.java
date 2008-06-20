package org.seasar.struts.glue.action;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.glue.action.AdapterAction;
import org.seasar.struts.glue.annotation.Form;

public class AdapterActionTest extends S2TestCase {

    public void testExecute() throws Exception {
        MyAction action = new MyAction();
        Method method = MyAction.class.getDeclaredMethod("execute");
        AdapterAction adapterAction = new AdapterAction(action, method);
        Field field = MyAction.class.getDeclaredField("form");
        field.setAccessible(true);
        adapterAction.setActionFormField(field);

        ActionMapping mapping = new ActionMapping() {
            public ActionForward findForward(String name) {
                ActionForward forward = new ActionForward();
                forward.setName(name);
                return forward;
            }
        };
        MyActionForm form = new MyActionForm();
        ActionForward forward = adapterAction.execute(mapping, form,
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
    }
}
