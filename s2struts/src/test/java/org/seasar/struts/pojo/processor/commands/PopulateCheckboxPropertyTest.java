package org.seasar.struts.pojo.processor.commands;

import org.apache.struts.chain.commands.ActionCommand;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.Constants;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.pojo.processor.TestCheckboxForm;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class PopulateCheckboxPropertyTest extends S2TestCase {

    private MockActionMapping mapping;

    private ActionContext context;

    private ActionCommand command = new PopulateCheckboxProperty();

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void setUpAfterContainerInit() throws Exception {
        this.mapping = new MockActionMapping();

        this.context = new ServletActionContext(this.getServletContext(), this.getRequest(), this.getResponse());
        this.context.setActionConfig(this.mapping);
    }

    public void testUncheck() throws Exception {
        TestCheckboxForm form = new TestCheckboxForm(true);
        this.context.setActionForm(form);

        getRequest().setParameter(Constants.CHECKBOX_NAME + "check", "false");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        form = (TestCheckboxForm) this.context.getActionForm();
        assertFalse(form.isCheck());
    }
    
    public void testCheck() throws Exception {
        TestCheckboxForm form = new TestCheckboxForm(true);
        this.context.setActionForm(form);

        getRequest().setParameter("check", "true");
        getRequest().setParameter(Constants.CHECKBOX_NAME + "check", "false");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        form = (TestCheckboxForm) this.context.getActionForm();
        assertTrue(form.isCheck());
    }

    public void testKeyless() throws Exception {
        TestCheckboxForm form = new TestCheckboxForm(true);
        this.context.setActionForm(form);

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        form = (TestCheckboxForm) this.context.getActionForm();
        assertTrue(form.isCheck());
    }
    
    public void testKeyPrefixAndSuffix() throws Exception {
        this.mapping.setPrefix("pre");
        this.mapping.setSuffix("sfx");
        
        TestCheckboxForm form = new TestCheckboxForm(true);
        this.context.setActionForm(form);

        getRequest().setParameter(Constants.CHECKBOX_NAME + "prechecksfx", "false");

        boolean result = this.command.execute(this.context);

        assertFalse(result);
        form = (TestCheckboxForm) this.context.getActionForm();
        assertFalse(form.isCheck());
    }

}
