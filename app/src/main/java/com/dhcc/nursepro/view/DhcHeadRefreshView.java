package com.dhcc.nursepro.view;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dhcc.nursepro.R;
import com.ufo.dwrefresh.view.interf.IRefreshHead;
import com.ufo.dwrefresh.view.utils.DensityUtils;

/**
 * com.dhcc.nursepro.view
 * <p>
 * author Q
 * Date: 2020/1/6
 * Time:10:12
 */
public class DhcHeadRefreshView extends FrameLayout implements IRefreshHead {private TextView mTvStatus;
    private LayoutParams mLayoutParams =
            new LayoutParams(LayoutParams.MATCH_PARENT, (int) DensityUtils.dipToPx(getContext(), 60));

    public DhcHeadRefreshView(Context context) {
        this(context, null);
    }

    public DhcHeadRefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DhcHeadRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.default_headview, null);
        mTvStatus = (TextView) view.findViewById(R.id.tv_status);
        mTvStatus.setText("自定义view");
        this.addView(view, mLayoutParams);
    }

    /**
     * 设置刷新头的状态颜色
     * @param textColor
     */
    public void setTextColor(@ColorRes int textColor) {
        mTvStatus.setTextColor(getResources().getColor(textColor));
    }

    /**
     * 设置刷新头的状态文字大小
     * @param textSize
     */
    public void setTextSize(int textSize) {
        mTvStatus.setTextSize(textSize);
    }

    @Override
    public void onStart() {
        mTvStatus.setText("开始下拉");
    }

    @Override
    public void onPullDown(int distance) {
        mTvStatus.setText("释放刷新");
    }

    @Override
    public void onBound() {
        mTvStatus.setText("释放刷新");
    }

    @Override
    public void onFingerUp(int distance) {
        mTvStatus.setText("刷新中...");
    }

    @Override
    public void onStop() {

    }

    @Override
    public int headViewHeight() {
        return (int) DensityUtils.dipToPx(getContext(), 60);
    }
}
