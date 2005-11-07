package org.seasar.struts.examples.multiply;

/**
 * @author Satoshi Kimura
 */
public class MultiplyServiceImpl implements MultiplyService {

	/**
	 * @see org.seasar.struts.examples.multiply.MultiplyService#multiply(int, int)
	 */
	public int multiply(int arg1, int arg2) {
		return arg1 * arg2;
	}

}
