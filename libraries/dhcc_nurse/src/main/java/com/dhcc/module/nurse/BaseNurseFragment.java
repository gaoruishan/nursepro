package com.dhcc.module.nurse;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.comm.BaseCommFragment;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.res.infusion.CustomSelectView;

/**
 * 智护相关一个父类
 * @author:gaoruishan
 * @date:202020-07-17/17:08
 * @email:grs0515@163.com
 */
public abstract class BaseNurseFragment extends BaseCommFragment {


    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

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
        setCommToolBar2();
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

    /**
     * 括号
     * @param s
     * @return
     */
    public String replaceBrackets(String s) {
        return  s.replace("[", "")
                .replace("]", "");
    }

    /**
     * 替换\r 为\n
     * @param s
     * @return
     */
    public String replaceRN(String s) {
        return s.replaceAll("\r", "\n");
    }

    protected void setSelectDateTime(CustomSelectView customSelectDate, String time) {
        customSelectDate.setTitle("时间选择").setSelect(time);
        long millis = TimeUtils.string2Millis(time, YYYY_MM_DD_HH_MM);
        customSelectDate.setSelectTime(this.getFragmentManager(), millis, null);
    }
}
