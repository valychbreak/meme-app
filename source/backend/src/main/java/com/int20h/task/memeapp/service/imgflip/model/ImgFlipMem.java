package com.int20h.task.memeapp.service.imgflip.model;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public class ImgFlipMem {
    private String name;
    private String url;

    public ImgFlipMem() {
    }

    public ImgFlipMem(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImgFlipMem that = (ImgFlipMem) o;

        if (!name.equals(that.name)) return false;
        return url.equals(that.url);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
