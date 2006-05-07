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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IterationListDto implements Serializable {

	private static final long serialVersionUID = 3371889267212256454L;
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

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("[");
		buf.append(iterations).append("]");
		return buf.toString();
	}

}
