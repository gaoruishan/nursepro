package com.dhcc.module.infusion.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dhcc.module.infusion.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-04-23/09:47
 * @email:grs0515@163.com
 */
public class CustomBarChart extends LinearLayout {
    private static final String TAG = CustomBarChart.class.getSimpleName();
    private View view;
    private VerticalProgressBar vpb;
    private int MAX = 100;
    private int measuredHeight;
    private LinearLayout llBar;
    private TextView tvSpliceLine;
    private LinearLayout llTemp;

    public CustomBarChart(Context context) {
        this(context, null);
    }

    public CustomBarChart(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomBarChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = LayoutInflater.from(context).inflate(R.layout.custom_bar_chart, null);
        addView(view);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 为了计算参考高度
        vpb = view.findViewById(R.id.vpb);
        llBar = view.findViewById(R.id.ll_bar);
        llTemp = view.findViewById(R.id.ll_temp);
        tvSpliceLine = view.findViewById(R.id.tv_splice_line);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (vpb == null) {
            return;
        }
        measuredHeight = vpb.getMeasuredHeight();
        int measuredWidth = vpb.getMeasuredWidth();
    }

    /***
     * 更新图表
     * @param name
     * @param progress
     */
    public void updateChart(final String name, final int progress) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                LinearLayout item = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.custom_bar_chart_item, null);
                item.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
                setProgressBar((VerticalProgressBar) item.findViewById(R.id.vpb), progress);
                ((TextView) item.findViewById(R.id.tv_title)).setText(name);
                ((TextView) item.findViewById(R.id.tv_tag)).setText(progress + "");
                // 添加到第一个
                llBar.addView(item, 0);
            }
        }, 200);
    }

    /**
     * 设置进度条
     * @param vpb
     * @param progress
     */
    private void setProgressBar(VerticalProgressBar vpb, int progress) {
        if (measuredHeight > 0) {
            int mHeight = (measuredHeight / MAX) * progress;
            Object tag = vpb.getTag();
            if (mHeight > 0 && tag == null) {
                LayoutParams layoutParams = (LayoutParams) vpb.getLayoutParams();
                layoutParams.height = mHeight;
                vpb.setLayoutParams(layoutParams);
                vpb.setTag(true);
            }
        }
    }

    public void updateAllChart(List<String> nameList, List<Integer> progressList) {
        if (nameList == null || progressList == null
                || nameList.size() == 0 || progressList.size() == 0) {
            return;
        }
        Integer[] numbers = new Integer[progressList.size()];
        int size = progressList.size();
        for (int i = 0; i < size; i++) {
            numbers[i] = progressList.get(i);
        }
        int min = Collections.min(Arrays.asList(numbers));
        int max = Collections.max(Arrays.asList(numbers));
        this.setMax(max + min / 2);
        this.removeAll();
        for (int i = 0; i < size; i++) {
            updateChart(nameList.get(i),progressList.get(i));
        }
    }

    /**
     * 设置最大值
     * @param max
     */
    public void setMax(int max) {
        this.MAX = max;
        String i = max > 1 ? "" + (int) max / 2 : "0.5";
        tvSpliceLine.setText(i);
    }

    /**
     * 移除所有视图
     */
    public void removeAll() {
        if (llBar != null) {
            llBar.removeAllViews();
        }
    }
}
