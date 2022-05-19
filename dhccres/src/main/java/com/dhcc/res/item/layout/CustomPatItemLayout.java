package com.dhcc.res.item.layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dhcc.res.BaseView;
import com.grs.dhcc_res.R;

/**
 * @author:gaoruishan
 * @date:202020/12/9/17:47
 * @email:grs0515@163.com
 */
public class CustomPatItemLayout extends BaseView {

    private TextView tvBedno;
    private TextView tvCarelevel;
    private TextView tvName;
    private ImageView imgSex;
    private View line;
    private LinearLayout llOther;
    private TextView tvAge;

    public CustomPatItemLayout(Context context) {
        this(context, null);
    }

    public CustomPatItemLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomPatItemLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvBedno = findViewById(R.id.tv_bedno);
        tvCarelevel = findViewById(R.id.tv_carelevel);
        tvName = findViewById(R.id.tv_name);
        tvAge = findViewById(R.id.tv_age);
        imgSex = findViewById(R.id.img_sex);
        line = findViewById(R.id.line_other);
        line.setVisibility(GONE);
        llOther = findViewById(R.id.ll_other);
        llOther.setVisibility(GONE);
    }

    public CustomPatItemLayout setBedno(String s) {
        setText(this.tvBedno, s);
        return this;
    }

    public CustomPatItemLayout setCarelevel(String s) {
        setText(this.tvCarelevel, s);
        return this;
    }

    public CustomPatItemLayout setName(String s) {
        setText(this.tvName, s);
        return this;
    }

    public CustomPatItemLayout setAge(String s) {
        setText(this.tvAge, s);
        return this;
    }

    public CustomPatItemLayout setImgSex(String imgSex) {
        setImageResource(this.imgSex, getPatSexDrawable(imgSex));
        return this;
    }

    /**
     * 添加其他View
     * @param view
     */
    public void addOtherView(View view) {
        if (view != null) {
            this.line.setVisibility(VISIBLE);
            this.llOther.setVisibility(VISIBLE);
            this.llOther.addView(view);
        }
    }



    @Override
    protected int setContentView() {
        return R.layout.custom_pat_item_layout;
    }
}
