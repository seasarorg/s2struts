package org.seasar.struts.util;

public class TestBindingComponentActionImpl {

    private TestSimpleComponentForm simpleComponentForm;

    private TestPackageComponentOneForm packageComponentOneForm;

    private TestPackageComponentTwoForm package_packageComponentTwoForm;

    public TestSimpleComponentForm getSimpleComponentForm() {
        return simpleComponentForm;
    }

    public void setSimpleComponentForm(
            TestSimpleComponentForm simpleConponentForm) {
        this.simpleComponentForm = simpleConponentForm;
    }

    public TestPackageComponentOneForm getPackageComponentOneForm() {
        return packageComponentOneForm;
    }

    public void setPackageComponentOneForm(
            TestPackageComponentOneForm packageComponentOneForm) {
        this.packageComponentOneForm = packageComponentOneForm;
    }

    public TestPackageComponentTwoForm getPackage_packageComponentTwoForm() {
        return package_packageComponentTwoForm;
    }

    public void setPackage_packageComponentTwoForm(
            TestPackageComponentTwoForm package_packageComponentTwoForm) {
        this.package_packageComponentTwoForm = package_packageComponentTwoForm;
    }

}
