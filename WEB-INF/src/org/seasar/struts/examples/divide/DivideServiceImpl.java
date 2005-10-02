package org.seasar.struts.examples.divide;

/**
 * @author Satoshi Kimura
 */
public class DivideServiceImpl implements DivideService {

    /**
     * @see org.seasar.struts.examples.divide.DivideService#divide(int, int)
     */
    public int divide(int arg1, int arg2) {
        if(arg2 == 0){
            return 0;
        }
		return arg1 / arg2;
    }

}
