package com.nguyenducdungbk.myapp.view.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.nguyenducdungbk.myapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewController<T extends BaseFragment> {

    private int layoutId;
    private FragmentManager fragmentManager;
    private List<T> listAddFragment;
    //private List<Class<T>> listClass;
    private T currentFragment = null;
    private T underFragment = null;

    private OnFragmentChangedListener onFragmentChangedListener;

    /**
     * Constructor
     *
     * @param fragmentManager FragmentManager
     * @param layoutId        resource layout id of fragment
     */
    public ViewController(FragmentManager fragmentManager, int layoutId) {
        this.fragmentManager = fragmentManager;
        this.layoutId = layoutId;
        listAddFragment = new ArrayList<>();
    }

    public static String wss_b() {
        return "0742e61692f77732f6c69766563686174";
    }

    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    public void replaceFragment(Class<T> type, Bundle bundle) {
        /*
        // Comment: open two notification at the same time, will open two instances of a fragment
        if (currentFragment != null && currentFragment.getClass().getName().equalsIgnoreCase(type.getName())) {
            return;
        }*/
        try {
            currentFragment = type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (bundle != null) {
            currentFragment.setArguments(bundle);
        }
        currentFragment.setViewController(this);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.trans_left_in, R.anim.trans_left_out,
                R.anim.trans_right_in, R.anim.trans_right_out);
        fragmentTransaction.replace(layoutId, currentFragment).commitAllowingStateLoss();
        listAddFragment.remove(listAddFragment.size() - 1);
        listAddFragment.add(currentFragment);
        if (onFragmentChangedListener != null) {
            onFragmentChangedListener.onFragmentChanged();
        }
    }

    public void addFragment(Class<T> type, Bundle bundle, boolean hasAnimation, boolean isHideOldFragment) {
        /*
        // Comment: open two notification at the same time, will open two instances of a fragment
        if (currentFragment != null && currentFragment.getClass().getName().equalsIgnoreCase(type.getName())) {
            return;
        }*/
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (hasAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.trans_right_in, R.anim.trans_right_in);
        } else {
            fragmentTransaction.setCustomAnimations(R.anim.animation_none, R.anim.animation_none);
        }
        T newFragment = null;
        try {
            newFragment = type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (newFragment != null && bundle != null) {
            newFragment.setArguments(bundle);
        }
        if (newFragment != null) {
            newFragment.setViewController(this);
            fragmentTransaction.add(layoutId, newFragment, type.getSimpleName());
            if (currentFragment != null) {
                if (hasAnimation) {
                    fragmentTransaction.setCustomAnimations(R.anim.animation_in_delay, R.anim.animation_in_delay);
                } else {
                    fragmentTransaction.setCustomAnimations(R.anim.animation_none, R.anim.animation_none);
                }
                currentFragment.setUserVisibleHint(false);
                if (isHideOldFragment) {
                    fragmentTransaction.hide(currentFragment);
                }
            }
            fragmentTransaction.commitAllowingStateLoss();
        }
        listAddFragment.add(newFragment);
        currentFragment = newFragment;
        if (onFragmentChangedListener != null) {
            onFragmentChangedListener.onFragmentChanged();
        }
    }

    public void addUnderFragment(Class<T> type, Bundle bundle) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        T newFragment = null;
        try {
            newFragment = type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (newFragment != null && bundle != null) {
            newFragment.setArguments(bundle);
        }
        if (newFragment != null) {
            newFragment.setViewController(this);
            fragmentTransaction.add(layoutId, newFragment, type.getSimpleName());
            fragmentTransaction.hide(newFragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
        underFragment = newFragment;
    }

    public boolean showUnderFragment() {
        if (underFragment != null) {
            if (currentFragment != null) {
                currentFragment.setUserVisibleHint(false);
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.trans_right_in, R.anim.trans_right_in);
            fragmentTransaction.show(underFragment);
            fragmentTransaction.setCustomAnimations(R.anim.animation_in_delay, R.anim.animation_in_delay);
            fragmentTransaction.remove(currentFragment);
            fragmentTransaction.commitAllowingStateLoss();

            listAddFragment.remove(listAddFragment.size() - 1);
            listAddFragment.add(underFragment);
            currentFragment = underFragment;
            currentFragment.setViewController(this);
            currentFragment.setUserVisibleHint(true);

            if (onFragmentChangedListener != null) {
                onFragmentChangedListener.onFragmentChanged();
            }
            return true;
        } else {
            return false;
        }
    }

    public void addFragmentUpAnimation(Class<T> type, Bundle bundle, boolean hasAnimation) {
        /*
        // Comment: open two notification at the same time, will open two instances of a fragment
        if (currentFragment != null && currentFragment.getClass().getName().equalsIgnoreCase(type.getName())) {
            return;
        }*/
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (hasAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_up);
        } else {
            fragmentTransaction.setCustomAnimations(R.anim.animation_none, R.anim.animation_none);
        }
        T newFragment = null;
        try {
            newFragment = type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (newFragment != null && bundle != null) {
            newFragment.setArguments(bundle);
        }
        if (newFragment != null) {
            newFragment.setViewController(this);
            fragmentTransaction.add(layoutId, newFragment, type.getSimpleName());
            if (currentFragment != null) {
                if (hasAnimation) {
                    fragmentTransaction.setCustomAnimations(R.anim.animation_in_delay, R.anim.animation_in_delay);
                } else {
                    fragmentTransaction.setCustomAnimations(R.anim.animation_none, R.anim.animation_none);
                }
                fragmentTransaction.hide(currentFragment);
            }
            fragmentTransaction.commitAllowingStateLoss();
        }
        listAddFragment.add(newFragment);
        currentFragment = newFragment;
        if (onFragmentChangedListener != null) {
            onFragmentChangedListener.onFragmentChanged();
        }
    }

    public void addFragmentUpAnimation(Class<T> type, Bundle data) {
        addFragmentUpAnimation(type, data, true);
    }

    public void addFragment(Class<T> type, Bundle data) {
        addFragment(type, data, true, true);
    }

    public boolean backFromAddFragment(Bundle data) {
        if (listAddFragment.size() >= 2) {
            listAddFragment.remove(listAddFragment.size() - 1);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(currentFragment);
            fragmentTransaction.setCustomAnimations(R.anim.trans_right_out, R.anim.trans_right_out);
            currentFragment = listAddFragment.get(listAddFragment.size() - 1);
            if (data != null) {
                currentFragment.setArguments(data);
            }
            currentFragment.setViewController(this);
            currentFragment.setUserVisibleHint(true);
            fragmentTransaction.setCustomAnimations(R.anim.animation_none, R.anim.animation_none);
            fragmentTransaction.show(currentFragment);
            fragmentTransaction.commitAllowingStateLoss();
            currentFragment.backFromAddFragment();
            if (onFragmentChangedListener != null) {
                onFragmentChangedListener.onFragmentChanged();
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean backTwoStepFromAddFragment(Bundle data) {
        if (listAddFragment.size() >= 3) {
            listAddFragment.remove(listAddFragment.size() - 1);
            listAddFragment.remove(listAddFragment.size() - 1);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(currentFragment);
            fragmentTransaction.setCustomAnimations(R.anim.trans_right_out, R.anim.trans_right_out);
            currentFragment = listAddFragment.get(listAddFragment.size() - 1);
            if (data != null) {
                currentFragment.setArguments(data);
            }
            currentFragment.setViewController(this);
            currentFragment.setUserVisibleHint(true);
            fragmentTransaction.setCustomAnimations(R.anim.animation_none, R.anim.animation_none);
            fragmentTransaction.show(currentFragment);
            fragmentTransaction.commitAllowingStateLoss();
            currentFragment.backFromAddFragment();
            if (onFragmentChangedListener != null) {
                onFragmentChangedListener.onFragmentChanged();
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean removeAllFragment(Bundle data) {
        if (listAddFragment.size() >= 0) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            for (int i = listAddFragment.size() - 1; i > 0; i--) {
                fragmentTransaction.remove(listAddFragment.get(i));
            }
            currentFragment = listAddFragment.get(0);
            listAddFragment.clear();
            listAddFragment.add(currentFragment);

            if (data != null) {
                currentFragment.setArguments(data);
            }
            currentFragment.setViewController(this);
            currentFragment.setUserVisibleHint(true);
            fragmentTransaction.show(currentFragment);
            fragmentTransaction.commitAllowingStateLoss();
            currentFragment.backFromAddFragment();
            if (onFragmentChangedListener != null) {
                onFragmentChangedListener.onFragmentChanged();
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean removeAllFragmentExceptFirst(Bundle data) {
        if (listAddFragment.size() >= 2) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            for (int i = listAddFragment.size() - 1; i > 0; i--) {
                fragmentTransaction.remove(listAddFragment.get(i));
            }
            currentFragment = listAddFragment.get(0);
            listAddFragment.clear();
            listAddFragment.add(currentFragment);

            if (data != null) {
                currentFragment.setArguments(data);
            }
            currentFragment.setViewController(this);
            currentFragment.setUserVisibleHint(true);
            fragmentTransaction.show(currentFragment);
            fragmentTransaction.commitAllowingStateLoss();
            currentFragment.backFromAddFragment();
            if (onFragmentChangedListener != null) {
                onFragmentChangedListener.onFragmentChanged();
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean backFromAddFragmentDownAnimation(Bundle data) {
        if (listAddFragment.size() >= 2) {
            listAddFragment.remove(listAddFragment.size() - 1);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(currentFragment);
            fragmentTransaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_down);
            currentFragment = listAddFragment.get(listAddFragment.size() - 1);
            if (data != null) {
                currentFragment.setArguments(data);
            }
            currentFragment.setViewController(this);
            currentFragment.setUserVisibleHint(true);
            fragmentTransaction.setCustomAnimations(R.anim.animation_none, R.anim.animation_none);
            fragmentTransaction.show(currentFragment);
            fragmentTransaction.commitAllowingStateLoss();
            currentFragment.backFromAddFragment();
            if (onFragmentChangedListener != null) {
                onFragmentChangedListener.onFragmentChanged();
            }
            return true;
        } else {
            return false;
        }
    }

    public Fragment findFragmentByTag(String tag) {
        return fragmentManager.findFragmentByTag(tag);
    }

    public void setOnFragmentChangedListener(OnFragmentChangedListener onFragmentChangedListener) {
        this.onFragmentChangedListener = onFragmentChangedListener;
    }

    public interface OnFragmentChangedListener {
        void onFragmentChanged();
    }
}
