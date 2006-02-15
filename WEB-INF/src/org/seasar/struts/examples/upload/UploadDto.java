/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
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
