package com.dhcc.res.nurse;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dhcc.res.BaseView;
import com.dhcc.res.infusion.CustomCheckView;
import com.dhcc.res.nurse.bean.RadioBean;
import com.base.commlibs.utils.SimpleCallBack;
import com.grs.dhcc_res.R;

import java.util.List;

/**
 * 选择框
 * @author:gaoruishan
 * @date:202020-04-16/11:54
 * @email:grs0515@163.com
 */
public class CustomSelectRadioView extends BaseView {

    private TextView tvTitle;
    private LinearLayout llParent;
    private List<RadioBean> mList;
    private @ColorRes int radioColor;

    public CustomSelectRadioView(Context context) {
        this(context, null);
    }

    public CustomSelectRadioView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSelectRadioView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        llParent = findViewById(R.id.ll_parent);
        tvTitle = findViewById(R.id.tv_title);
    }

    public void setBg(@ColorRes int id) {
        radioColor = id;
        llParent.setBackgroundColor(ContextCompat.getColor(mContext,id));
    }

    public void addRadioView(String title, List<RadioBean> list) {
        tvTitle.setVisibility(!TextUtils.isEmpty(title) ? VISIBLE : GONE);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        if (list != null) {
            addItemView(list);
        }
    }

    private void addItemView(List<RadioBean> list) {
        mList = list;
        llParent.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            final RadioBean bean = list.get(i);
            LinearLayout v = inflate(R.layout.dhcc_custom_select_radio_item, llParent, LinearLayout.class);
            if (radioColor != 0) {
                v.setBackgroundColor(ContextCompat.getColor(mContext,radioColor));
                v.findViewById(R.id.ll_radio_bg).setBackgroundColor(ContextCompat.getColor(mContext,radioColor));
            }
            View vBlock = v.findViewById(R.id.v_block);
            vBlock.setVisibility(TextUtils.isEmpty(bean.getName()) ? GONE : VISIBLE);
            TextView tvName = v.findViewById(R.id.tv_name);
            tvName.setText(bean.getName());

            final CustomCheckView customCheck1 = v.findViewById(R.id.custom_check1);
            customCheck1.setVisibility(!TextUtils.isEmpty(bean.getRadioYes()) ? VISIBLE : GONE);
            customCheck1.setShowText(bean.getRadioYes());

            final CustomCheckView customCheck2 = v.findViewById(R.id.custom_check2);
            customCheck2.setVisibility(!TextUtils.isEmpty(bean.getRadioNo()) ? VISIBLE : GONE);
            customCheck2.setShowText(bean.getRadioNo());
            //两个 且互斥
            if (!TextUtils.isEmpty(bean.getRadioNo())) {
                customCheck1.setOnSelectListener(new SimpleCallBack<Boolean>() {
                    @Override
                    public void call(Boolean result, int type) {
                        bean.setSelect("1");
                        if (result) {
                            customCheck2.setSelect(false);
                        }
                    }
                });
                customCheck2.setOnSelectListener(new SimpleCallBack<Boolean>() {
                    @Override
                    public void call(Boolean result, int type) {
                        bean.setSelect("0");
                        if (result) {
                            customCheck1.setSelect(false);
                        }
                    }
                });
            } else {
                customCheck1.setOnSelectListener(new SimpleCallBack<Boolean>() {
                    @Override
                    public void call(Boolean result, int type) {
                        bean.setSelect(result ? "1" : "0");
                    }
                });
            }

            llParent.addView(v);
        }
    }

    @Override
    protected int setContentView() {
        return R.layout.custom_select_radio_view;
    }

    public void clear() {
        if (mList != null) {
            for (RadioBean radioBean : mList) {
                radioBean.setSelect(null);
            }
            addItemView(mList);
        }
    }
    /**
     * 获取选择数据
     * @return "" 表示有未选择的
     */
    public String getSelectRadio() {
        String str = "";
        if (mList != null) {
            for (RadioBean radioBean : mList) {
                if (TextUtils.isEmpty(radioBean.isSelect())) {
                    str = "";
                    break;
                }
                str += radioBean.getCode() + "=" + radioBean.isSelect() + "&";
            }
        }
        if (str.length() > 1) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}
