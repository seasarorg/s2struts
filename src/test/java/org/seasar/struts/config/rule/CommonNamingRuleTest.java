package org.seasar.struts.config.rule;

import junit.framework.TestCase;

/**
 * @author Satoshi Kimura
 */
public class CommonNamingRuleTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDecapitalizeName() {
        assertNull(CommonNamingRule.decapitalizeName(null));

        assertEquals("", CommonNamingRule.decapitalizeName(""));

        assertEquals("ID", CommonNamingRule.decapitalizeName("ID"));
        assertEquals("id", CommonNamingRule.decapitalizeName("Id"));

        assertEquals("foo", CommonNamingRule.decapitalizeName("Foo"));
        assertEquals("fooDto", CommonNamingRule.decapitalizeName("FooDto"));
        assertEquals("foo", CommonNamingRule.decapitalizeName("FooImpl"));

        assertEquals("fooImpl", CommonNamingRule
                .decapitalizeName("FooImplImpl"));
        assertEquals("fooImplBar", CommonNamingRule
                .decapitalizeName("FooImplBarImpl"));

        assertEquals("FOO", CommonNamingRule.decapitalizeName("FOO"));
    }

}
