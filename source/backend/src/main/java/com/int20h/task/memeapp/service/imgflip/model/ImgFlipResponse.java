package com.int20h.task.memeapp.service.imgflip.model;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public class ImgFlipResponse {
    private boolean success;
    private ImgFlipData data;

    public ImgFlipResponse() {
    }

    public ImgFlipResponse(boolean success, ImgFlipData data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ImgFlipData getData() {
        return data;
    }

    public void setData(ImgFlipData data) {
        this.data = data;
    }
}
