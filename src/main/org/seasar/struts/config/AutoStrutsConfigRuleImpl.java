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

    private String[] viewExtention = { "jsp", "html" };

    public String getActionClassPattern() {
        return actionClassPattern;
    }

    public void setActionClassPattern(String actionClassPattern) {
        this.actionClassPattern = actionClassPattern;
    }

    public String getFormClassPattern() {
        return formClassPattern;
    }

    public void setFormClassPattern(String formClassPattern) {
        this.formClassPattern = formClassPattern;
    }

    public String getDocRoot() {
        return docRoot;
    }

    public void setDocRoot(String docRoot) {
        this.docRoot = docRoot.replaceFirst("/$", "");
    }

    public String[] getViewExtention() {
        return viewExtention;
    }

    public void setViewExtention(String viewExtention) {
        StringTokenizer tokenizer = new StringTokenizer(viewExtention, ",");
        List list = new ArrayList();
        while (tokenizer.hasMoreElements()) {
            list.add(tokenizer.nextElement().toString().trim());
        }
        this.viewExtention = (String[]) list.toArray(new String[list.size()]);
    }
}
