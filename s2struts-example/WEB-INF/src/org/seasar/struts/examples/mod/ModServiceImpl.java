package org.seasar.struts.examples.mod;

/**
 * @author Katsuhiko Nagashima
 */
public class ModServiceImpl implements ModService {

	public int mod(int arg1, int arg2) {
        if(arg2 == 0){
            return 0;
        }
		return arg1 % arg2;
	}

}
