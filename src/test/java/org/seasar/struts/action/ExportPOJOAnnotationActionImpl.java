package org.seasar.struts.action;

/**
 * @author Katsuhiko Nagashima
 */
public class ExportPOJOAnnotationActionImpl implements ExportPOJOAnnotationAction {

    public static final String exportPOJOAnnotationForm_EXPORT = "session";

    private ExportPOJOAnnotationForm exportPOJOAnnotationForm;

    public ExportPOJOAnnotationForm getExportPOJOAnnotationForm() {
        return exportPOJOAnnotationForm;
    }

    public void setExportPOJOAnnotationForm(ExportPOJOAnnotationForm exportPOJOAnnotationForm) {
        this.exportPOJOAnnotationForm = exportPOJOAnnotationForm;
    }

    public String exe() {
        if (exportPOJOAnnotationForm == null) {
            return "error";
        }
        exportPOJOAnnotationForm = new ExportPOJOAnnotationForm("updated");
        return "success";
    }

}
