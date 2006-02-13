package org.seasar.struts.examples.iteration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IterationServiceImpl implements IterationService {

	private Map<String, IterationDto> iterations = new HashMap<String, IterationDto>();

	public IterationServiceImpl() {
		iterations.put("10", new IterationDto("10", "dog"));
		iterations.put("11", new IterationDto("11", "cat"));
		iterations.put("12", new IterationDto("12", "bird"));
	}

	public IterationListDto getIterationList() {
		IterationListDto result = new IterationListDto(
				new ArrayList<IterationDto>(iterations.values()));
		return result;
	}

	public IterationDto getIteration(IterationDto dto) {
		return iterations.get(dto.getId());
	}

}
