package org.seasar.struts.config;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Satoshi Kimura
 */
public class AutoStrutsConfigRuleImpl implements AutoStrutsConfigRule {
    private String docRoot = "";

    private String actionClassPattern = ".*Action$";

    private String formClassPattern = "(.*Form$)|(.*Dto$)";

    private String[] viewExtension = { "jsp", "html" };

    public String getActionClassPattern() {
        return this.actionClassPattern;
    }

    public void setActionClassPattern(String actionClassPattern) {
        this.actionClassPattern = actionClassPattern;
    }

    public String getFormClassPattern() {
        return this.formClassPattern;
    }

    public void setFormClassPattern(String formClassPattern) {
        this.formClassPattern = formClassPattern;
    }

    public String getDocRoot() {
        return this.docRoot;
    }

    public void setDocRoot(String docRoot) {
        this.docRoot = docRoot.replaceFirst("/$", "");
    }

    public String[] getViewExtension() {
        return this.viewExtension;
    }

    public void setViewExtension(String viewExtension) {
        StringTokenizer tokenizer = new StringTokenizer(viewExtension, ",");
        List list = new ArrayList();
        while (tokenizer.hasMoreElements()) {
            list.add(tokenizer.nextElement().toString().trim());
        }
        this.viewExtension = (String[]) list.toArray(new String[list.size()]);
    }
}
