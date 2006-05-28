package org.seasar.struts.pojo.commands;

/**
 * @author Katsuhiko Nagashima
 */
public class TestExportPOJOActionImpl implements TestExportPOJOAction {

	private TestExportPOJOForm exportPOJOForm;

	public TestExportPOJOForm getExportPOJOForm() {
		return exportPOJOForm;
	}

	public void setExportPOJOForm(TestExportPOJOForm exportPOJOForm) {
		this.exportPOJOForm = exportPOJOForm;
	}

	public String exe() {
        if (exportPOJOForm == null) {
            return "error";
        }
        exportPOJOForm = new TestExportPOJOForm("updated");
		return "success";
	}

}
