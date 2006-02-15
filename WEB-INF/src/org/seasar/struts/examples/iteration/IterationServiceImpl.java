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
