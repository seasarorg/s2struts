package org.seasar.struts.examples.iteration;

import java.io.Serializable;

public class IterationDto implements Serializable {

	private String id = "";

	private String name = "";

	public IterationDto() {
	}

	public IterationDto(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer("[");
		buf.append(id).append(",");
		buf.append(name).append("]");
		return buf.toString();
	}

}
