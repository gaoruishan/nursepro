package com.dhcc.res.nurse;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.base.commlibs.utils.ViewUtil;
import com.blankj.utilcode.util.FragmentUtils;
import com.grs.dhcc_res.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 底部导航
 * @author:gaoruishan
 * @date:202019-10-22/16:41
 * @email:grs0515@163.com
 */
public class CustomBottomBarLayout extends LinearLayout implements CompoundButton.OnCheckedChangeListener {

    private static final int TAB_WORKAREA = 9001;
    private static final int TAB_MESSAGE = 9002;
    private static final int TAB_SETTING = 9003;
    private Context mContext;
    private LinearLayout bottomGroup;
    private RadioButton rbWorkarea;
    private RadioButton rbMessage;
    private RadioButton rbSetting;
    private Fragment[] mFragments;
    private List<Fragment> mFragmentsList;
    private List<View> mTabViews;
    private List<String> mTabNames;
    private FragmentTransaction ft;
    private FragmentManager mFragmentManager;

    public CustomBottomBarLayout(Context context) {
        this(context, null);
    }

    public CustomBottomBarLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomBottomBarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        View view = View.inflate(context, R.layout.custom_bottom_bar_layout, null);
        bottomGroup = view.findViewById(R.id.llBottomGroup);
        addView(view);
        setOrientation(VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }


    public CustomBottomBarLayout addTab(Fragment fragment, String name,int idNormal, int idPress) {
        if (mFragmentsList == null) {
            mFragmentsList = new ArrayList<>();
        }
        if (mTabViews == null) {
            mTabViews = new ArrayList<>();
        }
        if (mTabNames == null) {
            mTabNames = new ArrayList<>();
        }
        mTabNames.add(name);
        mFragmentsList.add(fragment);
        View tabView = View.inflate(mContext, R.layout.custom_bottom_bar_tab, null);
        LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        tabView.setLayoutParams(layoutParams);
        RadioButton bottomBarTab =tabView.findViewById(R.id.bottom_bar_tab);
        addSelectorFromDrawable(mContext,idNormal,idPress,bottomBarTab);
        mTabViews.add(tabView);
        return this;
    }

    public CustomBottomBarLayout init(FragmentManager mFragmentManager,int selectIndex) {
        this.mFragmentManager = mFragmentManager;

        for (int i = mFragmentsList.size() - 1; i >= 0; i--) {
            FragmentUtils.add(mFragmentManager, mFragmentsList.get(i), R.id.fragment);
        }
        for (int i = 0; i < mTabViews.size(); i++) {
            bottomGroup.addView(mTabViews.get(i));
            RadioButton bottomBarTab = mTabViews.get(i).findViewById(R.id.bottom_bar_tab);
            //添加序号
            bottomBarTab.setTag(i);
            //添加名称
            bottomBarTab.setText(mTabNames.get(i));
            bottomBarTab.setOnCheckedChangeListener(this);
        }
        switchFragment(selectIndex);
        return this;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            int tag = (int) buttonView.getTag();
            switchFragment(tag);
        }
    }


    public CustomBottomBarLayout switchFragment(int tag) {
        if (mFragmentsList == null || mTabViews == null) {
            return this;
        }
        if (tag < 0 || tag >= mFragmentsList.size()) {
            return this;
        }
        for (int i = 0; i < mFragmentsList.size(); i++) {
            RadioButton bottomBarTab = mTabViews.get(i).findViewById(R.id.bottom_bar_tab);
            bottomBarTab.setChecked(i == tag);
            bottomBarTab.setSelected(i == tag);
        }
        FragmentUtils.hide(mFragmentManager);
        FragmentUtils.show(mFragmentsList.get(tag));
        return this;
    }

    /**
     * 从 drawable 获取图片 id 给 RadioButton 添加 selector
     * @param context  调用方法的 Activity
     * @param idNormal 默认图片的 id
     * @param idPress  点击图片的 id
     * @param radioButton       点击的 view
     */
    public void addSelectorFromDrawable(Context context, int idNormal, int idPress, RadioButton radioButton) {
        Drawable[] compoundDrawables = radioButton.getCompoundDrawables();
        Drawable drawableTop = compoundDrawables[1];
        Rect bounds = drawableTop.getBounds();
        Drawable selectorDrawable = ViewUtil.getSelectorDrawable(context, idNormal, idPress);
        selectorDrawable.setBounds(bounds);
        radioButton.setCompoundDrawables(null, selectorDrawable, null, null);
    }


}
