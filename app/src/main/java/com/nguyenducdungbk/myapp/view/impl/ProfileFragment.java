package com.nguyenducdungbk.myapp.view.impl;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.adapter.ProfileAdapter;
import com.nguyenducdungbk.myapp.databinding.FragmentProfileBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerProfileViewComponent;
import com.nguyenducdungbk.myapp.injection.ProfileViewModule;
import com.nguyenducdungbk.myapp.network.response.VoucherResponse;
import com.nguyenducdungbk.myapp.presenter.ProfilePresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.utils.UIUtil;
import com.nguyenducdungbk.myapp.view.ProfileView;
import com.nguyenducdungbk.myapp.view.custom.AppBarStateChangeListener;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

public final class ProfileFragment extends BaseFragment<ProfilePresenter, ProfileView, FragmentProfileBinding> implements ProfileView {
    @Inject
    PresenterFactory<ProfilePresenter> mPresenterFactory;

    public static final int AVATAR_EXPAND_SIZE = 74;
    public static final int AVATAR_COLLAPSED_SIZE = 48;
    public static final int AVATAR_EXPAND_TOP = 24;
    public static final int AVATAR_COLLAPSED_TOP = 4;
    public static final int TITLE_EXPAND_TOP = 105;
    public static final int TITLE_COLLAPSED_TOP = 20;
    public static final int TITLE_COLLAPSED_MARGIN_LEFT = 68;
    public static final int SUB_TITLE_EXPAND_TOP = 129;
    public static final int SUB_TITLE_COLLAPED_TOP = 28;
    public static final int MARGIN_DEFAULT = 16;
    public static final int ANIMATION_DURATION = 200;
    public static final int FAVORITE_EXPAND_TOP = 47;
    public static final float TEXT_SIZE_20 = 20f;
    public static final float TEXT_SIZE_16 = 16f;
    public static final float TEXT_SIZE_14 = 14f;

    private int[] favoriteLocation = new int[2];
    private int[] editLocation = new int[2];
    private int mWidthScreen;
    private int initR, initG, initB, initAlpha, endR, endG, endB, endAlpha, finalR, finalG, finalB, finalAlpha;
    private float mXFavoriteStart, mXFavoriteEnd;
    private float mXEditStart, mXEditEnd;
    private boolean isGoneText;
    private Context mContext;
    private ProfileAdapter profileAdapter;

    private ViewTreeObserver.OnGlobalLayoutListener llEditLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            if (binding.llEdit == null)
                return;
            binding.llEdit.getLocationOnScreen(editLocation);
            if (editLocation[0] != 0) {
                binding.llEdit.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                checkInitDone();
            }
        }
    };

    private ViewTreeObserver.OnGlobalLayoutListener llFavoriteLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            if (binding.llFavorite == null)
                return;
            binding.llFavorite.getLocationOnScreen(favoriteLocation);
            if (favoriteLocation[0] != 0) {
                binding.llFavorite.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                checkInitDone();
            }
        }
    };

    private AppBarStateChangeListener appBarStateChangeListener = new AppBarStateChangeListener() {
        @Override
        public void onStateChanged(AppBarLayout appBarLayout, State state, float percent) {
            scaleAvatar(percent);
            translationTitle(percent);
            scaleLayoutRound(percent);
            translateLayoutRight(percent);
        }
    };

    private View.OnClickListener editProfileClickListener = v -> {
        if (!avoidDuplicateClick()) {
            if (getViewController() != null) {
                getViewController().addFragment(ConfirmInfoUserFragment.class, null);
            }
        }
    };

    private View.OnClickListener favoriteClickListener = v -> {
        if (!avoidDuplicateClick()) {
            getViewController().addFragment(LoginFragment.class, null);

        }
    };

    // Your presenter is available using the mPresenter variable

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerProfileViewComponent.builder()
                .appComponent(parentComponent)
                .profileViewModule(new ProfileViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_profile;
    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @NonNull
    @Override
    protected PresenterFactory<ProfilePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.appBarLayout.setOutlineProvider(null);
        }
        mContext = getContext();
        mWidthScreen = UIUtil.widthScreenPixel(getContext());
        binding.ivFavorite.setColorFilter(ContextCompat.getColor(mContext, R.color.black_A4A6A8));
        initViewTreeLocation();
        binding.ivSetting.setColorFilter(Color.WHITE);
        profileAdapter = new ProfileAdapter(getContext());
        binding.rvVoucher.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvVoucher.setAdapter(profileAdapter);
        binding.ivFavorite.setOnClickListener(editProfileClickListener);
        binding.tvFavorite.setOnClickListener(editProfileClickListener);
        binding.ivSetting.setOnClickListener(favoriteClickListener);
        binding.tvSetting.setOnClickListener(favoriteClickListener);
        binding.llEdit.setOnClickListener(favoriteClickListener);
    }

    @Override
    public void onDestroy() {
        binding.llEdit.getViewTreeObserver().removeOnGlobalLayoutListener(llEditLayoutListener);
        binding.llFavorite.getViewTreeObserver().removeOnGlobalLayoutListener(llFavoriteLayoutListener);
        super.onDestroy();
    }

    private void initViewTreeLocation() {
        if (binding.llEdit == null || binding.llFavorite == null)
            return;
        binding.llEdit.getViewTreeObserver().addOnGlobalLayoutListener(llEditLayoutListener);
        binding.llFavorite.getViewTreeObserver().addOnGlobalLayoutListener(llFavoriteLayoutListener);
    }

    public void checkInitDone() {
        if (editLocation[0] == 0 || favoriteLocation[0] == 0)
            return;
        mXFavoriteStart = favoriteLocation[0];
        mXFavoriteEnd = mWidthScreen - UIUtil.convertDpToPixel(80, mContext);
        mXEditStart = editLocation[0];
        mXEditEnd = mWidthScreen - UIUtil.convertDpToPixel(32, mContext);
        binding.appBarLayout.addOnOffsetChangedListener(appBarStateChangeListener);
    }

    /**
     * Initialise header color while translate animation
     */
    private void initHeaderColor(int startColor, int endColor) {

        initR = Color.red(startColor);
        initG = Color.green(startColor);
        initB = Color.blue(startColor);
        initAlpha = Color.alpha(startColor);

        endR = Color.red(endColor);
        endG = Color.green(endColor);
        endB = Color.blue(endColor);
        endAlpha = Color.alpha(endColor);
    }

    private void scaleAvatar(float percent) {
        float newAvatarSize = UIUtil.convertDpToPixel(AVATAR_EXPAND_SIZE - (AVATAR_EXPAND_SIZE - AVATAR_COLLAPSED_SIZE) * percent, getContext());
        // If avatar center in vertical, just half `(expandAvatarSize - newAvatarSize)`
        float yAvatarOffset = UIUtil.convertDpToPixel(AVATAR_EXPAND_TOP - (AVATAR_EXPAND_TOP - AVATAR_COLLAPSED_TOP) * percent, mContext);
        ViewGroup.LayoutParams layoutParams = binding.ivAvatar.getLayoutParams();
        layoutParams.width = Math.round(newAvatarSize);
        layoutParams.height = Math.round(newAvatarSize);
        binding.ivAvatar.setLayoutParams(layoutParams);
        binding.ivAvatar.setY(yAvatarOffset);
    }

    private void translationTitle(float percent) {
        initHeaderColor(Color.BLACK, Color.WHITE);
        float yTitleOffset = UIUtil.convertDpToPixel(TITLE_EXPAND_TOP - (TITLE_EXPAND_TOP - TITLE_COLLAPSED_TOP) * percent, mContext);
        float xTitleOffset = UIUtil.convertDpToPixel(MARGIN_DEFAULT + (TITLE_COLLAPSED_MARGIN_LEFT - MARGIN_DEFAULT) * percent, mContext);
        binding.tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_20 - (TEXT_SIZE_20 - TEXT_SIZE_16) * percent);
        finalR = (int) (initR + (endR - initR) * percent);
        finalG = (int) (initG + (endG - initG) * percent);
        finalB = (int) (initB + (endB - initB) * percent);
        finalAlpha = (int) (initAlpha + (endAlpha - initAlpha) * percent);
        int color = Color.argb(finalAlpha, finalR, finalG, finalB);
        binding.tvTitle.setX(xTitleOffset);
        binding.tvTitle.setY(yTitleOffset);
        binding.tvTitle.setTextColor(color);
        //
        float ySubTitleOffset = UIUtil.convertDpToPixel(SUB_TITLE_EXPAND_TOP - (SUB_TITLE_EXPAND_TOP - SUB_TITLE_COLLAPED_TOP) * percent, mContext);
        float xSubTitleOffset = UIUtil.convertDpToPixel(MARGIN_DEFAULT + (TITLE_COLLAPSED_MARGIN_LEFT - MARGIN_DEFAULT) * percent, mContext);
        binding.tvSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_16 - (TEXT_SIZE_16 - TEXT_SIZE_14) * percent);
        binding.tvSubTitle.setX(xSubTitleOffset);
        binding.tvSubTitle.setY(ySubTitleOffset);
        binding.tvSubTitle.setTextColor(getColorSubTitle(percent));
    }

    private void scaleLayoutRound(float percent) {
        binding.layoutRound.setPivotY(binding.layoutRound.getHeight());
        binding.layoutRound.setScaleY(1 - percent);
    }

    private int getColorSubTitle(float percent) {
        return ColorUtils.blendARGB(Color.RED, Color.BLACK, percent);
    }

    private void translateLayoutRight(float percent) {
        initHeaderColor(R.color.black_A4A6A8, Color.WHITE);
        float yFavoriteOffset = UIUtil.convertDpToPixel(TITLE_EXPAND_TOP - (TITLE_EXPAND_TOP - MARGIN_DEFAULT) * percent, mContext);
        float xFavoriteOffset = mXFavoriteStart + (mXFavoriteEnd - mXFavoriteStart) * percent;
        binding.llFavorite.setX(xFavoriteOffset);
        binding.llFavorite.setY(yFavoriteOffset);
        float yEditOffset = UIUtil.convertDpToPixel(FAVORITE_EXPAND_TOP - (FAVORITE_EXPAND_TOP - MARGIN_DEFAULT) * percent, mContext);
        float xEditOffset = mXEditStart + (mXEditEnd - mXEditStart) * percent;
        binding.llEdit.setX(xEditOffset);
        binding.llEdit.setY(yEditOffset);
        finalR = (int) (initR + (endR - initR) * percent);
        finalG = (int) (initG + (endG - initG) * percent);
        finalB = (int) (initB + (endB - initB) * percent);
        finalAlpha = (int) (initAlpha + (endAlpha - initAlpha) * percent);
        int color = Color.argb(finalAlpha, finalR, finalG, finalB);
        binding.ivFavorite.setColorFilter(color);
        if (percent >= 0.5) {
            if (!isGoneText) {
                isGoneText = true;
                startAlphaAnimation(binding.tvFavorite, View.GONE);
                startAlphaAnimation(binding.tvSetting, View.GONE);
                binding.tvFavorite.setClickable(false);
                binding.tvSetting.setClickable(false);
            }
        } else {
            if (isGoneText) {
                isGoneText = false;
                binding.tvFavorite.setClickable(true);
                binding.tvSetting.setClickable(true);
                binding.tvFavorite.setVisibility(View.VISIBLE);
                binding.tvSetting.setVisibility(View.VISIBLE);
                startAlphaAnimation(binding.tvFavorite, View.VISIBLE);
                startAlphaAnimation(binding.tvSetting, View.VISIBLE);
            }
        }
    }

    public static void startAlphaAnimation(View v, int visibility) {
        float alpla = visibility == View.VISIBLE ? 1f : 0f;
        v.animate().alpha(alpla).setDuration(ANIMATION_DURATION).start();
    }

    @Override
    public void updateUser(String name) {
        binding.tvTitle.setText(name);
    }

    @Override
    public void initListVoucher(List<VoucherResponse> voucherResponses) {
        if (profileAdapter != null) {
            profileAdapter.setVoucherResponses(voucherResponses);
        }
    }
}
