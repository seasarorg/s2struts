package org.seasar.struts.context;

import org.seasar.extension.unit.S2TestCase;

public class S2StrutsContextTest extends S2TestCase {
    
    private S2StrutsContext context = new S2StrutsContextImpl();
    
    public void testGetMethodBindingExpression() {
        getRequest().setParameter("1234567890", "TEST");
        context.setMethodBindingExpression("1234567890", "TEST", "#{action.execute}");
        
        String expression = context.getMethodBindingExpression();
        assertEquals("#{action.execute}", expression);
    }
    
    public void testExistMethodBindingExpression() {
        getRequest().setParameter("1234567890", "TEST");
        context.setMethodBindingExpression("1234567890", "TEST", "#{action.execute}");
        
        assertTrue(context.existMethodBindingExpression());
    }
    
    public void testNotExistMethodBindingExpression() {
        getRequest().setParameter("9999999999", "TEST");
        context.setMethodBindingExpression("1234567890", "TEST", "#{action.execute}");
        
        assertFalse(context.existMethodBindingExpression());
    }

}
