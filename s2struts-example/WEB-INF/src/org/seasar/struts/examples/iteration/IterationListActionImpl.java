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
package org.seasar.struts.examples.iteration;

public class IterationListActionImpl implements IterationListAction {

	private IterationService iterationService;

	private IterationListDto iterationListDto;

	private IterationDto iterationDto;

	public void setIterationService(IterationService iterationService) {
		this.iterationService = iterationService;
	}

	public IterationListDto getIterationListDto() {
		return iterationListDto;
	}

	public void setIterationListDto(IterationListDto iterationListDto) {
		this.iterationListDto = iterationListDto;
	}

	public IterationDto getIterationDto() {
		return iterationDto;
	}

	public String goCreate() {
		return CREATE;
	}

	public String goEdit(int index) {
		iterationDto = iterationService.getIteration(iterationListDto
				.getIterations(index));
		return EDIT;
	}

	public String goDelete(int index) {
		iterationDto = iterationService.getIteration(iterationListDto
				.getIterations(index));
		return DELETE;
	}

}
