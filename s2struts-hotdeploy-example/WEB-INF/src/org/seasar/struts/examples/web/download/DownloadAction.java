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
package org.seasar.struts.examples.web.download;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.seasar.struts.annotation.tiger.StrutsAction;

/**
 * @author taedium
 * 
 */
@StrutsAction
public class DownloadAction {

    public static final String DOWNLOAD = "/pages/add/add.jsp";

    private DownloadForm downloadForm;

    private HttpServletResponse response;

    public void setDownloadForm(DownloadForm downloadForm) {
        this.downloadForm = downloadForm;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String execute() {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"download.txt\"");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(downloadForm.getText().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            response.reset();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return null;
    }

}
