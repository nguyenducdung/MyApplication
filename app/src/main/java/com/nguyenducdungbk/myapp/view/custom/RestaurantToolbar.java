package com.nguyenducdungbk.myapp.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nguyenducdungbk.myapp.R;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RestaurantToolbar extends RelativeLayout{

    @BindView(R.id.ivBackToolbar)
    ImageView ivBackToolbar;
    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.ivRightIconToolbar)
    ImageView ivRightIconToolbar;
    @BindView(R.id.rl_toolbar)
    RelativeLayout rlToolbar;

    @BindView(R.id.rlSearch)
    RelativeLayout rlSearch;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.rlSearchDisable)
    RelativeLayout rlSearchDisable;
    @BindView(R.id.llSearchDisable)
    LinearLayout llSearchDisable;
    @BindView(R.id.tvSearch)
    TextView tvSearch;
    @BindView(R.id.ivCloseDisable)
    ImageView ivCloseDisable;

    private Context mContext;

    public RestaurantToolbar(Context context) {
        super(context);
        init(context);
    }

    public RestaurantToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setParams(attrs);
    }

    public RestaurantToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setParams(attrs);
    }

    public RestaurantToolbar setTextSearch(String key) {
        if (!TextUtils.isEmpty(key)) {
            etSearch.setText(key);
            etSearch.setSelection(key.length());
        }
        return this;
    }

    public RestaurantToolbar setImeActionSearch(TextView.OnEditorActionListener listener) {
        if (listener != null) {
            etSearch.setOnEditorActionListener(listener);
        }
        return this;
    }

    public String getTextSearch() {
        return etSearch.getText().toString();
    }

    private void init(Context context) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this, true);
        ButterKnife.bind(this, view);
    }

    private void setParams(AttributeSet attrs) {
        TypedArray a = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.RestaurantToolbar, 0, 0);

        boolean backVisible = a.getBoolean(R.styleable.RestaurantToolbar_tvtb_visible_back_button, true);
        ivBackToolbar.setVisibility(backVisible ? VISIBLE : GONE);

        String title = a.getString(R.styleable.RestaurantToolbar_tvtb_title);
        if (title != null) {
            title = title.trim();
        }
        tvTitleToolbar.setText(title);

        int rightIconSrc = a.getResourceId(R.styleable.RestaurantToolbar_tvtb_right_icon_src, -1);
        if (rightIconSrc != -1) {
            ivRightIconToolbar.setImageResource(rightIconSrc);
        }

        int leftIconSrc = a.getResourceId(R.styleable.RestaurantToolbar_tvtb_left_icon_src, -1);
        if (leftIconSrc != -1) {
            ivBackToolbar.setImageResource(leftIconSrc);
        }

        int rightIconPadding = a.getDimensionPixelSize(R.styleable.RestaurantToolbar_tvtb_right_icon_padding, -1);
        if (rightIconPadding != -1) {
            ivRightIconToolbar.setPadding(0, rightIconPadding, 0, rightIconPadding);
        }
        boolean titlePositionLeft = a.getBoolean(R.styleable.RestaurantToolbar_tvtb_title_left, false);
        if (titlePositionLeft) {
            tvTitleToolbar.setGravity(Gravity.LEFT);
        }
        int fontRes = a.getResourceId(R.styleable.RestaurantToolbar_tvtb_title_font, -1);
        if (fontRes != -1) {
            Typeface typeface = ResourcesCompat.getFont(getContext(), fontRes);
            tvTitleToolbar.setTypeface(typeface);
        }
        boolean isVisibleSearch = a.getBoolean(R.styleable.RestaurantToolbar_tvtb_visible_search, false);
        if (isVisibleSearch) {
            tvTitleToolbar.setVisibility(GONE);
            ivRightIconToolbar.setVisibility(GONE);
            rlSearch.setVisibility(VISIBLE);
        }
        a.recycle();
    }

    public RestaurantToolbar hideSearchLayout() {
        ivRightIconToolbar.setVisibility(VISIBLE);
        tvTitleToolbar.setVisibility(VISIBLE);
        rlSearch.setVisibility(GONE);
        rlSearchDisable.setVisibility(GONE);
        return this;
    }

    public RestaurantToolbar showSearchLayout(@Nullable OnEditTextSearchChange onEditTextSearchChange) {
        ivRightIconToolbar.setVisibility(GONE);
        tvTitleToolbar.setVisibility(INVISIBLE);
        rlSearch.setVisibility(VISIBLE);
        rlSearchDisable.setVisibility(GONE);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {
                    ivClose.setVisibility(GONE);
                } else {
                    ivClose.setVisibility(VISIBLE);
                }
                if (onEditTextSearchChange != null) {
                    onEditTextSearchChange.onEditTextSearchChange(s.toString());
                }
            }
        });
        return this;
    }

    @OnClick(R.id.ivClose)
    public void onClearTextSearch() {
        etSearch.setText("");
    }

    public RestaurantToolbar showSearchLayoutDisable(String keySearch) {
        ivRightIconToolbar.setVisibility(GONE);
        tvTitleToolbar.setVisibility(INVISIBLE);
        rlSearch.setVisibility(GONE);
        rlSearchDisable.setVisibility(VISIBLE);
        if (!TextUtils.isEmpty(keySearch)) {
            tvSearch.setText(keySearch);
        }
        return this;
    }

    public RestaurantToolbar setIvCloseDisableClickListener(View.OnClickListener listener) {
        if (listener != null) {
            llSearchDisable.setOnClickListener(listener);
        }
        return this;
    }

    public RestaurantToolbar setLayoutSearchDisableClickListener(View.OnClickListener listener) {
        if (listener != null) {
            ivCloseDisable.setOnClickListener(listener);
        }
        return this;
    }

    public RestaurantToolbar setOnBackClickListener(View.OnClickListener listener) {
        ivBackToolbar.setOnClickListener(listener);
        return this;
    }

    public RestaurantToolbar setOnRightIconClickListener(View.OnClickListener listener) {
        ivRightIconToolbar.setOnClickListener(listener);
        return this;
    }

    public RestaurantToolbar setTextTitleToobar(String text) {
        if (text != null) {
            text = text.trim();
        }
        tvTitleToolbar.setText(text);
        return this;
    }

    public RestaurantToolbar setBackgroundToolbar(int color) {
        rlToolbar.setBackgroundResource(color);
        return this;
    }

    public void setIconCloseBlack() {
        ivBackToolbar.setImageResource(R.drawable.icon_close);
        ivBackToolbar.setPadding(0, 8, 0, 8);
    }

    public void setWhiteToolbar(Context context) {
        tvTitleToolbar.setTextColor(context.getResources().getColor(R.color.white));
        ivBackToolbar.setImageResource(R.drawable.ic_back);
    }

    public void setIconBackWhite() {
        ivBackToolbar.setColorFilter(ContextCompat.getColor(mContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    public void setTitleWhite() {
        tvTitleToolbar.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
    }

    public void hideIconRight() {
        ivRightIconToolbar.setVisibility(INVISIBLE);
        ivRightIconToolbar.setEnabled(false);
    }

    public interface OnEditTextSearchChange {
        void onEditTextSearchChange(String keySearch);
    }
}
