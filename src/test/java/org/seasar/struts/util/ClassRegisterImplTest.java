package org.seasar.struts.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

import org.seasar.extension.unit.S2TestCase;

/**
 * @author Satoshi Kimura
 */
public class ClassRegisterImplTest extends S2TestCase {

    private ClassRegister classRegister;

    protected void setUp() throws Exception {
        include("s2struts.dicon");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public ClassRegisterImplTest(String name) {
        super(name);
    }

    public void testGetClassString() {
        long startTime = new Date().getTime();
        long endTime = 0;
        for (int i = 0; i < 1000; i++) {
            ClassRegister classRegister = new ClassRegisterImpl();
            classRegister.getClass(String.class.getName());
            classRegister.getClass(ArrayList.class.getName());
            classRegister.getClass(HashSet.class.getName());
            classRegister.getClass(Hashtable.class.getName());
            classRegister.getClass(HashMap.class.getName());
            classRegister.destroy();
        }
        endTime = new Date().getTime();
        long firstTime = endTime - startTime;

        startTime = new Date().getTime();
        for (int i = 0; i < 1000; i++) {
            ClassRegister classRegister = new ClassRegisterImpl();
            this.classRegister.getClass(String.class.getName());
            this.classRegister.getClass(ArrayList.class.getName());
            this.classRegister.getClass(HashSet.class.getName());
            this.classRegister.getClass(Hashtable.class.getName());
            this.classRegister.getClass(HashMap.class.getName());
            classRegister.destroy();
        }
        endTime = new Date().getTime();
        long secondTime = endTime - startTime;

        assertTrue("firstTime =" + firstTime + " secondTime =" + secondTime,
                secondTime < firstTime);
    }

    public void testDestroy() {
        classRegister.getClass(String.class.getName());

        classRegister.destroy();

        try {
            classRegister.getClass(String.class.getName());
            fail();
        } catch (NullPointerException e) {
            // success
        }
    }

}