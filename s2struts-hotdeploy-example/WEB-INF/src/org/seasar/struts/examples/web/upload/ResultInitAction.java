/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.struts.examples.web.upload;

import org.apache.struts.upload.FormFile;

/**
 * @author taedium
 * 
 */
public class ResultInitAction {

    private UploadForm uploadForm;

    private ResultForm resultForm;

    public void setUploadForm(UploadForm uploadForm) {
        this.uploadForm = uploadForm;
    }

    public void setResultForm(ResultForm resultForm) {
        this.resultForm = resultForm;
    }

    public void init() {
        FormFile file = uploadForm.getFile();
        resultForm.setName(file.getFileName());
        resultForm.setSize(String.valueOf(file.getFileSize()));
    }
}
