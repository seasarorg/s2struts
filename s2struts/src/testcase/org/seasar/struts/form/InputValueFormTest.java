package org.seasar.struts.form;

import junit.framework.TestCase;

/**
 * @author Satoshi Kimura
 */
public class InputValueFormTest extends TestCase {

    public void testFreeze() {
        InputValueForm form = new InputValueForm();
        form.set("a", "aVal");
        InputValueForm form2 = (InputValueForm)form.get("b");
        form2.set("b", "bVal");

        assertNotNull(form2.get("c"));

        form.freeze();

        assertEquals("aVal", form.get("a"));
        assertEquals(InputValueForm.class.getName(), form.get("b").getClass().getName());
        assertNull(form2.get("d"));

    }

}
