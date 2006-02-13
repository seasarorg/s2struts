package org.seasar.struts.examples.iteration;

import org.seasar.struts.annotation.tiger.StrutsActionForward;

public interface IterationListAction {

	@StrutsActionForward(path="/pages/iterationCreate.html")
	String CREATE = "create";

	@StrutsActionForward(path="/pages/iterationEdit.html")
	String EDIT = "edit";

	@StrutsActionForward(path="/pages/iterationDelete.html")
	String DELETE = "delete";

	String goCreate();

	String goEdit(int index);

	String goDelete(int index);

}
