package com.dhcc.custom.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.comm.BaseCommFragment;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.DataCache;
import com.dhcc.custom.CustomApiManager;
import com.dhcc.custom.bean.CustomUiConfigBean;
import com.dhcc.custom.bean.MainSubListBean;
import com.dhcc.module.nurse.R;

/**
 * 自定义UI的父类
 * @author:gaoruishan
 * @date:202022/1/27/16:18
 * @email:grs0515@163.com
 */
public abstract class BaseCustomFragment extends BaseCommFragment {

    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCommToolBar2();
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    // 将sp值转换为px值，保证文字大小不变
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    public void setToolbarCenterTitle(CharSequence title) {
        //改为白色
        super.setToolbarCenterTitle(title, 0xffffffff, 17);
    }

    /**
     * 添加两个图片
     * @param iv1
     * @param iv2
     */
    public void addToolBarRightImageView(@DrawableRes int iv1, @DrawableRes int iv2) {
        View viewright = View.inflate(getActivity(), R.layout.dhcc_view_toolbar_img_right, null);
        ImageView ivBed = viewright.findViewById(R.id.img_toolbar_right1);
        ivBed.setVisibility(iv1 != 0 ? View.VISIBLE : View.GONE);
        if (iv1 != 0) {
            ivBed.setImageResource(iv1);
        }
        ImageView ivAdd = viewright.findViewById(R.id.img_toolbar_right2);
        ivAdd.setVisibility(iv2 != 0 ? View.VISIBLE : View.GONE);
        if (iv2 != 0) {
            ivAdd.setImageResource(iv2);
        }
        ivAdd.setOnClickListener(this);
        ivBed.setOnClickListener(this);
        setToolbarRightCustomView(viewright);
    }

    /**
     * 添加一个文本
     * @param txt
     * @param color
     */
    public void addToolBarRightTextView(String txt, @ColorRes int color) {
        //右上角保存按钮
        View viewright = View.inflate(getActivity(), R.layout.dhcc_view_toolbar_tv_right, null);
        TextView textView = viewright.findViewById(R.id.tv_toolbar_right);
        textView.setTextSize(15);
        textView.setText("   " + txt + "   ");
        textView.setTextColor(getResources().getColor(color));
        textView.setOnClickListener(this);
        setToolbarRightCustomView(viewright);
    }

    public void getUiConfig() {
        MainSubListBean json = DataCache.getJson(MainSubListBean.class, SharedPreference.CUR_MAINSUBLISTBEAN);
        CustomApiManager.getUiConfig(json, new CommonCallBack<CustomUiConfigBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFail(code,msg);
            }

            @Override
            public void onSuccess(CustomUiConfigBean bean, String type) {
                onUiConfigCallBack(bean);
            }
        });
    }

    protected abstract void onUiConfigCallBack(CustomUiConfigBean bean);

}
