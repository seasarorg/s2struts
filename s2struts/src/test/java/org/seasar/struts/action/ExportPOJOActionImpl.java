package org.seasar.struts.action;

/**
 * @author Katsuhiko Nagashima
 */
public class ExportPOJOActionImpl implements ExportPOJOAction {

	private ExportPOJOForm exportPOJOForm;

	public ExportPOJOForm getExportPOJOForm() {
		return exportPOJOForm;
	}

	public void setExportPOJOForm(ExportPOJOForm exportPOJOForm) {
		this.exportPOJOForm = exportPOJOForm;
	}

	public String exe() {
        if (exportPOJOForm == null) {
            return "error";
        }
        exportPOJOForm = new ExportPOJOForm("updated");
		return "success";
	}

}
