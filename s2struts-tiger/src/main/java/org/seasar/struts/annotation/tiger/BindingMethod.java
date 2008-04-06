package org.seasar.struts.annotation.tiger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.struts.action.ActionMapping;

/**
 * JSPタグからS2Struts専用の式を使用して呼び出されるメソッドであることを示します。
 * 
 * @author taedium
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BindingMethod {

	/**
	 * 使用する{@link ActionMapping}を決定するためのパス
	 */
	String path();
}
