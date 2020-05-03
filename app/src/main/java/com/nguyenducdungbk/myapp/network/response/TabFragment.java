package com.nguyenducdungbk.myapp.network.response;

import android.support.v4.app.Fragment;

public class TabFragment {

    private String title;
    private Fragment fragment;
    private int icRes;

    public TabFragment(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public TabFragment(String title, Fragment fragment, int icRes) {
        this.title = title;
        this.fragment = fragment;
        this.icRes = icRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public int getIcRes() {
        return icRes;
    }
}
