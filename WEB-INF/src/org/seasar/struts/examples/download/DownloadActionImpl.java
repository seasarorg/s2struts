package org.seasar.struts.examples.download;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class DownloadActionImpl implements DownloadAction {

    private DownloadDto downloadDto;
    
    private HttpServletResponse response;

    public String execute() {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"download.txt\"");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(downloadDto.getTheText().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            response.reset();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            if (out == null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public DownloadDto getDownloadDto() {
        return downloadDto;
    }

    public void setDownloadDto(DownloadDto downloadDto) {
        this.downloadDto = downloadDto;
    }
    
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

}
