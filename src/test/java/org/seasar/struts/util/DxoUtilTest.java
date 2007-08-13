package org.seasar.struts.util;

import java.math.BigDecimal;
import java.util.Date;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.exception.ClassNotFoundRuntimeException;
import org.seasar.framework.util.ResourceNotFoundRuntimeException;

/**
 * @author Satsohi Kimura
 */
public class DxoUtilTest extends S2TestCase {

    private boolean containerVersion2_3;

    protected void setUp() throws Exception {
        try {
            include("dxo.dicon");
        } catch (ResourceNotFoundRuntimeException e) {
            containerVersion2_3 = true;
        }
    }

    public void testConvert() {
        try {
            BigDecimal val = (BigDecimal) DxoUtil
                    .convert("1", BigDecimal.class);
            assertEquals(new BigDecimal("1"), val);
        } catch (ClassNotFoundRuntimeException success) {
            assertTrue(containerVersion2_3);
        }

        try {
            DxoUtil.convert("2000/01/01", Date.class);
        } catch (ClassNotFoundRuntimeException success) {
            assertTrue(containerVersion2_3);
        } catch (NullPointerException success) {
        }
    }

}
