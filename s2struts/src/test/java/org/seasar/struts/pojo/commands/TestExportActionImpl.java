package org.seasar.struts.pojo.commands;

/**
 * @author Katsuhiko Nagashima
 */
public class TestExportActionImpl implements TestExportAction {

	private TestExportForm exportForm;

	public TestExportForm getExportForm() {
		return exportForm;
	}

	public void setExportForm(TestExportForm exportForm) {
		this.exportForm = exportForm;
	}

	public String exe() {
        if (exportForm == null) {
            return null;
        }
        exportForm = new TestExportForm("updated");
		return "success";
	}

}
