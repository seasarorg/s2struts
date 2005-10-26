package org.seasar.struts.validator.annotation.backport175;

/**
 * @author Satoshi Kimura
 * @org.seasar.struts.validator.annotation.backport175.ValidatorTarget
 */
public interface UrlType {

    /**
     * @org.codehaus.backport175.DefaultValue (false)
     */
    boolean allowallschemes();

    /**
     * @org.codehaus.backport175.DefaultValue (false)
     */
    boolean allow2slashes();

    /**
     * @org.codehaus.backport175.DefaultValue (false)
     */
    boolean nofragments();

    /**
     * @org.codehaus.backport175.DefaultValue ("http,https,ftp")
     */
    String schemes();
}
