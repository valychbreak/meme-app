package com.int20h.task.memeapp.dto;

import java.util.List;

public class PageOrientedData<T> {
    private int page;
    private List<T> items;
    private int totalPages;
    private int totalItems;

    public PageOrientedData(int page, List<T> items, int totalPages, int totalItems) {
        this.page = page;
        this.items = items;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
