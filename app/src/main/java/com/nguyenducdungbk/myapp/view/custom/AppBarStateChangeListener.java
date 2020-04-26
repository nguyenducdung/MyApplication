package com.nguyenducdungbk.myapp.view.custom;

import android.support.design.widget.AppBarLayout;

public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private float currentOffset = 0;
    private State mCurrentState = State.IDLE;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED, 0);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED, 1);
            }
            mCurrentState = State.COLLAPSED;
        } else {
//            if (mCurrentState != State.IDLE) {
            if (currentOffset != i) {
                currentOffset = i;
                onStateChanged(appBarLayout, State.IDLE, (float) Math.abs(i) / (float) appBarLayout.getTotalScrollRange());
            }
//            }
            mCurrentState = State.IDLE;
        }
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state, float percent);
}
