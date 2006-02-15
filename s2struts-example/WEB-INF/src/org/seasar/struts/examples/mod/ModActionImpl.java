/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
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