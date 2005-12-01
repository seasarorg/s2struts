package org.seasar.struts.examples.download;

import org.seasar.struts.validator.annotation.tiger.Required;

public class DownloadDto {
    
    private String theText;

    public String getTheText() {
        return theText;
    }

    @Required
    public void setTheText(String theText) {
        this.theText = theText;
    }

}
