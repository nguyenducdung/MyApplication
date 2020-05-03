package com.nguyenducdungbk.myapp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nguyenducdungbk.myapp.network.response.TabFragment;

import java.util.List;

public class BasePagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<TabFragment> tabFragments;
    public BasePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    public void setTabFragments(List<TabFragment> tabFragments) {
        this.tabFragments = tabFragments;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabFragments.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int i) {
        return tabFragments.get(i).getFragment();
    }

    @Override
    public int getCount() {
        return tabFragments.size();
    }
}
