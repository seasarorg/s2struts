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
