package org.seasar.struts.pojo.util;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.beans.impl.BeanDescImpl;
import org.seasar.struts.mock.MockActionMapping;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class BindingUtilBindComponentTest extends S2TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void setUpImportRegisterdComponent() {
        include("BindingUtilBindComponentTest.dicon");
    }

    public void testImportRegisterdComponent() {
        TestSimpleComponentForm simpleForm = new TestSimpleComponentForm();
        TestPackageComponentOneForm packageOneForm = new TestPackageComponentOneForm();
        TestPackageComponentTwoForm packageTwoForm = new TestPackageComponentTwoForm();

        getRequest().setAttribute("simpleComponentForm", simpleForm);
        getRequest().setAttribute("package_packageComponentOneForm", packageOneForm);
        getRequest().setAttribute("package_packageComponentTwoForm", packageTwoForm);

        TestBindingComponentActionImpl action = new TestBindingComponentActionImpl();
        BindingUtil.importProperties(action, getContainer(), new BeanDescImpl(TestBindingComponentActionImpl.class),
                new MockActionMapping());

        assertEquals(simpleForm, action.getSimpleComponentForm());
        assertEquals(packageOneForm, action.getPackageComponentOneForm());
        assertEquals(packageTwoForm, action.getPackage_packageComponentTwoForm());
    }

    public void testImportNotRegisteredComponent() {
        TestSimpleComponentForm simpleForm = new TestSimpleComponentForm();
        TestPackageComponentOneForm packageOneForm = new TestPackageComponentOneForm();
        TestPackageComponentTwoForm packageTwoForm = new TestPackageComponentTwoForm();

        getRequest().setAttribute("simpleComponentForm", simpleForm);
        getRequest().setAttribute("package_packageComponentOneForm", packageOneForm);
        getRequest().setAttribute("package_packageComponentTwoForm", packageTwoForm);

        TestBindingComponentActionImpl action = new TestBindingComponentActionImpl();
        BindingUtil.importProperties(action, getContainer(), new BeanDescImpl(TestBindingComponentActionImpl.class),
                new MockActionMapping());

        assertEquals(simpleForm, action.getSimpleComponentForm());
        assertNull(action.getPackageComponentOneForm());
        assertEquals(packageTwoForm, action.getPackage_packageComponentTwoForm());
    }

    public void setUpExportRegisterdComponent() {
        include("BindingUtilBindComponentTest.dicon");
    }

    public void testExportRegisterdComponent() {
        TestSimpleComponentForm simpleForm = new TestSimpleComponentForm();
        TestPackageComponentOneForm packageOneForm = new TestPackageComponentOneForm();
        TestPackageComponentTwoForm packageTwoForm = new TestPackageComponentTwoForm();

        TestBindingComponentActionImpl action = new TestBindingComponentActionImpl();
        action.setSimpleComponentForm(simpleForm);
        action.setPackageComponentOneForm(packageOneForm);
        action.setPackage_packageComponentTwoForm(packageTwoForm);

        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(TestBindingComponentActionImpl.class),
                new MockActionMapping());

        assertEquals(simpleForm, getRequest().getAttribute("simpleComponentForm"));
        assertEquals(packageOneForm, getRequest().getAttribute("package_packageComponentOneForm"));
        assertEquals(packageTwoForm, getRequest().getAttribute("package_packageComponentTwoForm"));
    }

    public void testExportNotRegisterdComponent() {
        TestSimpleComponentForm simpleForm = new TestSimpleComponentForm();
        TestPackageComponentOneForm packageOneForm = new TestPackageComponentOneForm();
        TestPackageComponentTwoForm packageTwoForm = new TestPackageComponentTwoForm();

        TestBindingComponentActionImpl action = new TestBindingComponentActionImpl();
        action.setSimpleComponentForm(simpleForm);
        action.setPackageComponentOneForm(packageOneForm);
        action.setPackage_packageComponentTwoForm(packageTwoForm);

        BindingUtil.exportProperties(action, getContainer(), new BeanDescImpl(TestBindingComponentActionImpl.class),
                new MockActionMapping());

        assertEquals(simpleForm, getRequest().getAttribute("simpleComponentForm"));
        assertNull(getRequest().getAttribute("package_packageComponentOneForm"));
        assertEquals(packageTwoForm, getRequest().getAttribute("package_packageComponentTwoForm"));
    }

}
