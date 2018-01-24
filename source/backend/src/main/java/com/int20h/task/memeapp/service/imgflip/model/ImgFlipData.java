package com.int20h.task.memeapp.service.imgflip.model;

import java.util.List;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public class ImgFlipData {
    private List<ImgFlipMem> memes;

    public ImgFlipData() {

    }

    public ImgFlipData(List<ImgFlipMem> memes) {
        this.memes = memes;
    }

    public List<ImgFlipMem> getMemes() {
        return memes;
    }

    public void setMemes(List<ImgFlipMem> memes) {
        this.memes = memes;
    }
}
