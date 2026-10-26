package com.example.order.model.dto;

import java.util.List;

public class PaginationResponse<T> {
    private List<T> data;
    private int totalPages;
    private long totalElement;
    private int currentPage;

    public PaginationResponse() {}

    public PaginationResponse(List<T> data, int totalPages, long totalElement, int currentPage) {
        this.data = data;
        this.totalPages = totalPages;
        this.totalElement = totalElement;
        this.currentPage = currentPage;
    }

    public List<T> getData() { return data; }
    public void setData(List<T> data) { this.data = data; }

    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

    public long getTotalElement() { return totalElement; }
    public void setTotalElement(long totalElement) { this.totalElement = totalElement; }

    public int getCurrentPage() { return currentPage; }
    public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }
}
