package org.seasar.struts.action;

/**
 * @author Katsuhiko Nagashima
 */
public class ExportActionImpl implements ExportAction {

	private ExportForm exportForm;

	public ExportForm getExportForm() {
		return exportForm;
	}

	public void setExportForm(ExportForm exportForm) {
		this.exportForm = exportForm;
	}

	public String exe() {
        if (exportForm == null) {
            return null;
        }
        exportForm = new ExportForm("updated");
		return "success";
	}

}
