package com.example.medicalservice.domain;

/**
 * @DESCRIPTION:
 * @USER: 11364
 * @DATE: 2021/6/20 20:21
 */
public class Page {
    int currentPage;;

    int pageSize;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
