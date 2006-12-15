package org.seasar.struts.pojo.util;

/**
 * @author Katsuhiko Nagashima
 */
public class TestExportPOJOAnnotationActionImpl implements TestExportPOJOAnnotationAction {

    public static final String exportPOJOAnnotationForm_EXPORT = "session";

    private TestExportPOJOAnnotationForm exportPOJOAnnotationForm;

    public TestExportPOJOAnnotationForm getExportPOJOAnnotationForm() {
        return exportPOJOAnnotationForm;
    }

    public void setExportPOJOAnnotationForm(TestExportPOJOAnnotationForm exportPOJOAnnotationForm) {
        this.exportPOJOAnnotationForm = exportPOJOAnnotationForm;
    }

    public String exe() {
        if (exportPOJOAnnotationForm == null) {
            return "error";
        }
        exportPOJOAnnotationForm = new TestExportPOJOAnnotationForm("updated");
        return "success";
    }

}
