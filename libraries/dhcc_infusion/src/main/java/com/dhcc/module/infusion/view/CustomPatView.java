package com.dhcc.module.infusion.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseFragment;
import com.base.commlibs.UniversalActivity;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.PatInfoFragment;

/**
 * 患者简介
 * @author:gaoruishan
 * @date:202019-04-27/16:26
 * @email:grs0515@163.com
 */
public class CustomPatView extends LinearLayout {

    private static final String TAG = CustomPatView.class.getSimpleName();
    private View view;
    private TextView tvPat;
    private TextView tvRegno;
    private TextView tvAge;
    private ImageView imgSex;

    public CustomPatView(Context context) {
        this(context, null);
    }

    public CustomPatView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomPatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = View.inflate(context, R.layout.custom_pat_view, null);
        addView(view);
    }

    /**
     * PatName : lh041101
     * PatRegNo : 0000000435
     * patSex : 女
     */
    public static int getPatSexDrawable(String patSex) {
        if ("男".equals(patSex)) {
            return R.drawable.infusion_sex_male;
        } else if ("女".equals(patSex)) {
            return R.drawable.infusion_sex_female;
        } else {
            return R.drawable.infusion_sex_male;
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvPat = view.findViewById(R.id.tv_pat);
        tvRegno = view.findViewById(R.id.tv_regno);
        tvAge = view.findViewById(R.id.tv_age);
        imgSex = view.findViewById(R.id.img_sex);
        // 点击事件
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id", tvRegno.getText().toString());
                startPatInfoFragment(getContext(), PatInfoFragment.class, bundle);
            }
        });
    }

    /**
     * 使用UniversalActivity启动给定的Fragment
     * @param fragCls 待启动Fragment
     * @param args    传递给Fragment的参数,可空
     */
    public static void startPatInfoFragment(Context context, @NonNull Class<? extends BaseFragment> fragCls, @Nullable Bundle args) {
        Intent intent = new Intent(context, UniversalActivity.class);
        intent.putExtra(UniversalActivity.RootFragmentClassName, fragCls.getName());
        intent.putExtra(UniversalActivity.RootFragmentArgs, args);
        context.startActivity(intent);
    }

    /**
     * 设置姓名
     * @param patName
     */
    public CustomPatView setPatName(String patName) {
        if (!TextUtils.isEmpty(patName)) {
            this.tvPat.setVisibility(VISIBLE);
            this.tvPat.setText(patName);
        }
        return this;
    }

    /**
     * 设置登记号
     * @param regNo
     */
    public CustomPatView setRegNo(String regNo) {
        if (!TextUtils.isEmpty(regNo)) {
            this.tvRegno.setVisibility(VISIBLE);
            this.tvRegno.setText(regNo);
        }
        return this;
    }
    public CustomPatView setAge(String tvAge) {
        if (!TextUtils.isEmpty(tvAge)) {
            this.tvAge.setVisibility(VISIBLE);
            this.tvAge.setText(tvAge);
        }
        return this;
    }

    /**
     * 设置性别图标
     * @param resId
     */
    public CustomPatView setImgSexResource(@DrawableRes int resId) {
        if (resId != 0) {
            this.imgSex.setImageResource(resId);
        }
        return this;
    }
}
