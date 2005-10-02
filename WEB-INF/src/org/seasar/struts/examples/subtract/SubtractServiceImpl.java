package org.seasar.struts.examples.subtract;

/**
 * @author Satoshi Kimura
 */
public class SubtractServiceImpl implements SubtractService {

	/**
	 * @see org.seasar.struts.examples.subtract.SubtractService#subtract(int, int)
	 */
	public int subtract(int arg1, int arg2) {
		return arg1 - arg2;
	}

}
