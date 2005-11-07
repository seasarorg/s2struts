package org.seasar.struts.examples.add;

/**
 * @author higa
 * @author Satoshi Kimura
 */
public class AddServiceImpl implements AddService {

	/**
	 * @see org.seasar.struts.examples.add.AddService#add(int, int)
	 */
	public int add(int arg1, int arg2) {
		return arg1 + arg2;
	}

}
