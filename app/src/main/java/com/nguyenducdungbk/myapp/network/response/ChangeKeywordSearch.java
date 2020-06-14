package com.nguyenducdungbk.myapp.network.response;

public class ChangeKeywordSearch {
    private String keyword;

    public ChangeKeywordSearch(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
