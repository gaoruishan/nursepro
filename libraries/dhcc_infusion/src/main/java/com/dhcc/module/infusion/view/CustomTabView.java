package com.dhcc.module.infusion.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dhcc.module.infusion.R;


/**
 * @author:gaoruishan
 * @date:202019-04-23/11:46
 * @email:grs0515@163.com
 */
public class CustomTabView extends LinearLayout implements View.OnClickListener {
    private View ll_pats_show;
    private TextView[] tv_tabs;
    private TextView[] tv_tab_reds;
    private View[] view_pats_shows;
    private RelativeLayout[] rls;
    private int mSelectPosition;
    private OnTabClickLisenter lisenter;

    public CustomTabView(Context context) {
        this(context, null);
    }

    public CustomTabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = ViewGroup.inflate(context, R.layout.custom_tab_view, null);
        addView(view);
        tv_tabs = new TextView[]{
                view.findViewById(R.id.tv_tab1),
                view.findViewById(R.id.tv_tab2)
        };
        tv_tab_reds = new TextView[]{
                view.findViewById(R.id.tv_tab1_red),
                view.findViewById(R.id.tv_tab2_red)
        };
        ll_pats_show = view.findViewById(R.id.ll_pats_show);
        view_pats_shows = new View[]{
                view.findViewById(R.id.view_pats_show1),
                view.findViewById(R.id.view_pats_show2)
        };
        rls = new RelativeLayout[]{
                view.findViewById(R.id.rl1),
                view.findViewById(R.id.rl2)
        };
        for (int i = 0; i < rls.length; i++) {
            rls[i].setOnClickListener(this);
            // 默认隐藏小红点
            tv_tab_reds[i].setVisibility(GONE);
        }
    }

    /**
     * 设置小红点
     * @param num
     * @param pst
     */
    public void setTabRedSpot(String num, int pst) {
        if (pst < tv_tab_reds.length && !TextUtils.isEmpty(num)) {
            tv_tab_reds[pst].setVisibility(VISIBLE);
            tv_tab_reds[pst].setText(num + "");
        }
    }

    /**
     * 设置隐藏Tab
     * @param pst
     * @return
     */
    public CustomTabView setPositionTabGone(int pst) {
        if (pst < rls.length) {
            rls[pst].setVisibility(GONE);
        }
        if (rls.length == 2) {//都隐藏
            ll_pats_show.setVisibility(GONE);
        }
        return this;
    }
    public CustomTabView setTabText(String[] data) {
        for (int i = 0; i < tv_tabs.length; i++) {
            tv_tabs[i].setText(data[i]);
        }

        return this;
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < tv_tabs.length; i++) {
            selected(tv_tabs[i], R.color.black, Typeface.NORMAL);
            view_pats_shows[i].setVisibility(INVISIBLE);
        }
        for (int i = 0; i < rls.length; i++) {
            if (v.getId() == rls[i].getId()) {
                selected(tv_tabs[i], R.color.blue_dark, Typeface.BOLD);
                view_pats_shows[i].setVisibility(VISIBLE);
                if (lisenter != null) {
                    lisenter.onTabClick(i, tv_tabs[i]);
                }
            }
        }
    }

    private void selected(TextView textView, int p, int bold) {
        textView.setTextColor(ContextCompat.getColor(getContext(), p));
        textView.setTypeface(Typeface.defaultFromStyle(bold));
    }

    public CustomTabView setOnTabClickLisenter(OnTabClickLisenter lisenter) {
        this.lisenter = lisenter;
        return this;
    }

    public interface OnTabClickLisenter {
        void onTabClick(int pst, View v);
    }

}
