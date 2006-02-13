package org.seasar.struts.examples.iteration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IterationListDto implements Serializable {

	private List<IterationDto> iterations = new ArrayList<IterationDto>();
	
	public IterationListDto() {
	}
	
	public IterationListDto(List<IterationDto> iterations) {
		this.iterations = iterations;
	}

	public IterationDto[] getIterations() {
		return iterations.toArray(new IterationDto[iterations.size()]);
	}

	public IterationDto getIterations(int index) {
		while (iterations.size() < index + 1) {
			iterations.add(new IterationDto());
		}
		return iterations.get(index);
	}

	public String toString() {
		StringBuffer buf = new StringBuffer("[");
		buf.append(iterations).append("]");
		return buf.toString();
	}

}
