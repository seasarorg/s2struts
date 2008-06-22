package org.seasar.struts.glue.action;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.glue.action.ActionFactoryImpl.ActionDesc;
import org.seasar.struts.glue.annotation.Form;
import org.seasar.struts.glue.annotation.RequestAttribute;
import org.seasar.struts.glue.annotation.SessionAttribute;
import org.seasar.struts.glue.exception.IllegalActionMethodSignatureException;
import org.seasar.struts.glue.exception.TooManyFormFieldException;

public class ActionFactoryImplTest extends S2TestCase {

    private ActionFactoryImpl factory = new ActionFactoryImpl();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        register(MyAction.class, "/myAction");
    }

    public void testGetAllFields() throws Exception {
        final List<Field> list = factory.getAllFields(MyAction3.class);
        assertEquals(2, list.size());
    }

    public void testGetActionFormField() throws Exception {
        final Field[] array = MyAction.class.getDeclaredFields();
        final Field f = factory.getActionFromField(MyAction.class, Arrays
                .asList(array));
        assertNotNull(f);
        f.set(new MyAction(), new MyActionForm());
    }

    public void testGetActionFormField_null() throws Exception {
        final Field[] array = MyAction2.class.getDeclaredFields();
        final Field f = factory.getActionFromField(MyAction2.class, Arrays
                .asList(array));
        assertNull(f);
    }

    public void testGetActionFormField_tooMany() throws Exception {
        final Field[] array = MyAction3.class.getDeclaredFields();
        try {
            factory.getActionFromField(MyAction3.class, Arrays.asList(array));
            fail();
        } catch (final TooManyFormFieldException expected) {
        }
    }

    public void testGetRequestAttributeFields() throws Exception {
        final Field[] array = MyAction.class.getDeclaredFields();
        final List<Field> list = factory.getRequestAttributeFields(Arrays
                .asList(array));
        assertEquals(3, list.size());
        assertTrue(list.get(0).isAnnotationPresent(RequestAttribute.class));
    }

    public void testGetRequestAttributeFields_empty() throws Exception {
        final Field[] array = MyAction2.class.getDeclaredFields();
        final List<Field> list = factory.getRequestAttributeFields(Arrays
                .asList(array));
        assertTrue(list.isEmpty());
    }

    public void testGetSessionAttributeFields() throws Exception {
        final Field[] array = MyAction.class.getDeclaredFields();
        final List<Field> list = factory.getSessionAttributeFields(Arrays
                .asList(array));
        assertEquals(2, list.size());
        assertTrue(list.get(0).isAnnotationPresent(SessionAttribute.class));
    }

    public void testGetSessionAttributeFields_empty() throws Exception {
        final Field[] array = MyAction2.class.getDeclaredFields();
        final List<Field> list = factory.getSessionAttributeFields(Arrays
                .asList(array));
        assertTrue(list.isEmpty());
    }

    public void testGetAllMethod() throws Exception {
        final Map<String, Method> methods = factory
                .getAllMethods(MyAction4.class);
        assertNotNull(methods.get("execute"));
    }

    public void testGetActionMethod() throws Exception {
        final Map<String, Method> methods = new HashMap<String, Method>();
        for (final Method m : MyAction.class.getDeclaredMethods()) {
            methods.put(m.getName(), m);
        }
        final Method method = factory.getActionMethod(MyAction.class, "method",
                methods);
        assertNotNull(method);
    }

    public void testGetActionMethod_illegalModifier() throws Exception {
        final Map<String, Method> methods = new HashMap<String, Method>();
        for (final Method m : MyAction.class.getDeclaredMethods()) {
            methods.put(m.getName(), m);
        }
        try {
            factory.getActionMethod(MyAction.class, "method2", methods);
            fail();
        } catch (final IllegalActionMethodSignatureException expected) {
        }
    }

    public void testGetAction() throws Exception {
        final Action action = factory.getAction(new ActionServlet(),
                new MyAction(), "method");
        assertNotNull(action);
    }

    public void testGetAction_bindForm() throws Exception {
        final MyAction5 myAction = new MyAction5();
        final Action action = factory.getAction(new ActionServlet(), myAction,
                "method");
        final MyActionForm form = new MyActionForm();
        action.execute(null, form, getRequest(), getResponse());
        assertSame(form, myAction.form);
    }

    public void testGetAction_exportActionForm() throws Exception {
        final MyAction5 myAction = new MyAction5();
        final MyActionForm form = new MyActionForm();
        myAction.form = form;
        final Action action = factory.getAction(new ActionServlet(), myAction,
                "method");
        action.execute(null, form, getRequest(), getResponse());
        assertSame(form, getRequest().getAttribute("form"));
    }

    public void testGetAction_exportActionForm_anotherName() throws Exception {
        final MyAction6 myAction = new MyAction6();
        final MyActionForm form = new MyActionForm();
        myAction.form = form;
        final Action action = factory.getAction(new ActionServlet(), myAction,
                "method");
        action.execute(null, form, getRequest(), getResponse());
        assertSame(form, getRequest().getAttribute("hogeForm"));
    }

    public void testGetAction_exportActionForm_noExport() throws Exception {
        final MyAction7 myAction = new MyAction7();
        final MyActionForm form = new MyActionForm();
        myAction.form = form;
        final Action action = factory.getAction(new ActionServlet(), myAction,
                "method");
        action.execute(null, form, getRequest(), getResponse());
        assertNull(getRequest().getAttribute("form"));
    }

    public void testGetAction_importFromRequest() throws Exception {
        getRequest().setAttribute("aaa", "1");
        getRequest().setAttribute("bbb", "2");
        getRequest().setAttribute("ccc", "3");
        getRequest().setAttribute("dddName", "4");
        final MyAction5 myAction = new MyAction5();
        final Action action = factory.getAction(new ActionServlet(), myAction,
                "method");
        action.execute(null, null, getRequest(), getResponse());
        assertEquals("1", myAction.aaa);
        assertNull(myAction.bbb);
        assertEquals("3", myAction.ccc);
        assertEquals("4", myAction.ddd);
    }

    public void testGetAction_exportToRequest() throws Exception {
        final MyAction5 myAction = new MyAction5();
        myAction.aaa = "1";
        myAction.bbb = "2";
        myAction.ccc = "3";
        myAction.ddd = "4";
        final Action action = factory.getAction(new ActionServlet(), myAction,
                "method");
        action.execute(null, null, getRequest(), getResponse());
        assertEquals("1", getRequest().getAttribute("aaa"));
        assertEquals("2", getRequest().getAttribute("bbb"));
        assertNull(getRequest().getAttribute("ccc"));
        assertEquals("4", getRequest().getAttribute("dddName"));
    }

    public void testGetAction_importFromSession() throws Exception {
        final HttpSession session = getRequest().getSession();
        session.setAttribute("eee", "1");
        session.setAttribute("fff", "2");
        session.setAttribute("ggg", "3");
        session.setAttribute("hhhName", "4");
        final MyAction5 myAction = new MyAction5();
        final Action action = factory.getAction(new ActionServlet(), myAction,
                "method");
        action.execute(null, null, getRequest(), getResponse());
        assertEquals("1", myAction.eee);
        assertNull(myAction.fff);
        assertEquals("3", myAction.ggg);
        assertEquals("4", myAction.hhh);
    }

    public void testGetAction_exportToSession() throws Exception {
        final MyAction5 myAction = new MyAction5();
        myAction.eee = "1";
        myAction.fff = "2";
        myAction.ggg = "3";
        myAction.hhh = "4";
        final HttpSession session = getRequest().getSession();
        final Action action = factory.getAction(new ActionServlet(), myAction,
                "method");
        action.execute(null, null, getRequest(), getResponse());
        assertEquals("1", session.getAttribute("eee"));
        assertEquals("2", session.getAttribute("fff"));
        assertNull(session.getAttribute("ggg"));
        assertEquals("4", session.getAttribute("hhhName"));
    }

    public void testGetActionDesc() throws Exception {
        final ActionDesc actionDesc = factory.getActionDesc(MyAction.class);
        assertNotNull(actionDesc);
        assertSame(actionDesc, factory.getActionDesc(MyAction.class));
    }

    public static class MyAction {

        @Form
        private MyActionForm form;

        @RequestAttribute
        private String aaa;

        @RequestAttribute
        private String bbb;

        @RequestAttribute
        private String ccc;

        @SessionAttribute
        private String ddd;

        @SessionAttribute
        private String eee;

        private String fff;

        public String method() {
            return null;
        }

        private String method2() {
            return null;
        }

        public void method3() {
        }
    }

    public static class MyAction2 {

        private MyActionForm form;
    }

    public static class MyAction3 {

        @Form
        private MyActionForm form;

        @Form
        private MyActionForm form2;
    }

    public static class MyAction4 extends Action {
    }

    public static class MyAction5 {

        @Form
        private MyActionForm form;

        @RequestAttribute
        private String aaa;

        @RequestAttribute(imports = false)
        private String bbb;

        @RequestAttribute(exports = false)
        private String ccc;

        @RequestAttribute(name = "dddName")
        private String ddd;

        @SessionAttribute
        private String eee;

        @SessionAttribute(imports = false)
        private String fff;

        @SessionAttribute(exports = false)
        private String ggg;

        @SessionAttribute(name = "hhhName")
        private String hhh;

        public String method() {
            return null;
        }
    }

    public static class MyAction6 {

        @Form(name = "hogeForm")
        private MyActionForm form;

        public String method() {
            return null;
        }
    }

    public static class MyAction7 {

        @Form(exportsToRequest = false)
        private MyActionForm form;

        public String method() {
            return null;
        }
    }

    public static class MyActionForm extends
            org.apache.struts.action.ActionForm {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

    }
}
