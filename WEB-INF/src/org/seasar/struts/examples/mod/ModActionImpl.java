package org.seasar.struts.examples.mod;

/**
 * @author Katsuhiko Nagashima
 */
public class ModActionImpl implements ModAction {

	private ModService modService;

	private ModDto modForm;

	public ModActionImpl(ModService modService) {
		this.modService = modService;
	}

	public String mod() {
		int result = modService.mod(modForm.getIntArg1(), modForm.getIntArg2());
		modForm.setResult(result);
		return SUCCESS;
	}

	public ModDto getModForm() {
		return modForm;
	}

	public void setModForm(ModDto modForm) {
		this.modForm = modForm;
	}

}