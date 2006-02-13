package org.seasar.struts.examples.iteration;

public class IterationListInitActionImpl implements IterationListInitAction {
	
	private IterationService iterationService;
	
	private IterationListDto iterationListDto;
	
	public void setIterationService(IterationService iterationService) {
		this.iterationService = iterationService;
	}
	
	public IterationListDto getIterationListDto() {
		return iterationListDto;
	}

	public String initialize() {
		iterationListDto = iterationService.getIterationList();
		return null;
	}

}
