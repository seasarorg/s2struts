package org.seasar.struts.config;

/**
 * @author Satoshi Kimura
 */
public interface AutoStrutsConfigRule {
    String getActionClassPattern();

    void setActionClassPattern(String actionClassPattern);

    String getFormClassPattern();

    void setFormClassPattern(String formClassPattern);

    String getDocRoot();

    void setDocRoot(String docRoot);

    String[] getViewExtention();

    void setViewExtention(String viewExtention);
}
