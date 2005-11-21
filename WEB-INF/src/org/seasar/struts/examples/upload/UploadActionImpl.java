package org.seasar.struts.examples.upload;

public class UploadActionImpl implements UploadAction {
    
    private UploadDto uploadDto;
    
    public String execute() {
        uploadDto.setSize(uploadDto.getTheFile().getFileSize());
        return SUCCESS;
    }

    public UploadDto getUploadDto() {
        return uploadDto;
    }

    public void setUploadDto(UploadDto uploadDto) {
        this.uploadDto = uploadDto;
    }
    
}
