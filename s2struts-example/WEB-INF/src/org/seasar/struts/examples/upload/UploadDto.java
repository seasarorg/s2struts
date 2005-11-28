package org.seasar.struts.examples.upload;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;
import org.seasar.struts.validator.annotation.tiger.Required;

public class UploadDto implements Serializable {

    private String theText;

    private FormFile theFile;
    
    private int size;
    
    public String getTheText() {
        return theText;
    }

    @Required
    public void setTheText(String theText) {
        this.theText = theText;
    }

    public FormFile getTheFile() {
        return theFile;
    }

    @Required
    public void setTheFile(FormFile theFile) {
        this.theFile = theFile;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
}
