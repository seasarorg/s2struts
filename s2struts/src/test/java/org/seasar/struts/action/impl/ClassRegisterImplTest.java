package org.seasar.struts.action.impl;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.action.ClassRegister;

/**
 * @author Satoshi Kimura
 */
public class ClassRegisterImplTest extends S2TestCase {

    // public void testGetClassString() {
    // long startTime = new Date().getTime();
    // long endTime = 0;
    // for (int i = 0; i < 1000; i++) {
    // ClassRegister classRegister = new ClassRegisterImpl();
    // classRegister.getClass(String.class.getName());
    // classRegister.getClass(ArrayList.class.getName());
    // classRegister.getClass(HashSet.class.getName());
    // classRegister.getClass(Hashtable.class.getName());
    // classRegister.getClass(HashMap.class.getName());
    // classRegister.destroy();
    // }
    // endTime = new Date().getTime();
    // long firstTime = endTime - startTime;
    //
    // startTime = new Date().getTime();
    // for (int i = 0; i < 1000; i++) {
    // ClassRegister classRegister = new ClassRegisterImpl();
    // classRegister.getClass(String.class.getName());
    // classRegister.getClass(ArrayList.class.getName());
    // classRegister.getClass(HashSet.class.getName());
    // classRegister.getClass(Hashtable.class.getName());
    // classRegister.getClass(HashMap.class.getName());
    // classRegister.destroy();
    // }
    // endTime = new Date().getTime();
    // long secondTime = endTime - startTime;
    //
    // assertTrue("firstTime =" + firstTime + " secondTime =" + secondTime, secondTime < firstTime);
    // }

    public void testDestroy() {
        ClassRegister classRegister = new ClassRegisterImpl();
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