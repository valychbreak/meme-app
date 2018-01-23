package com.int20h.task.memeapp.dto;

import java.util.ArrayList;
import java.util.List;

public class PageOrientedDataBuilder<T> {
    private int page;
    private List<T> items = new ArrayList<>();
    private int totalPages;
    private int totalItems;

    public PageOrientedDataBuilder setPage(int page) {
        this.page = page;
        return this;
    }

    public PageOrientedDataBuilder setItems(List<T> items) {
        this.items = items;
        return this;
    }

    public PageOrientedDataBuilder addItem(T item) {
        this.items.add(item);
        return this;
    }

    public PageOrientedDataBuilder setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public PageOrientedDataBuilder setTotalItems(int totalItems) {
        this.totalItems = totalItems;
        return this;
    }

    public PageOrientedData build() {
        return new PageOrientedData(page, items, totalPages, totalItems);
    }
}