package com.dhcc.nursepro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MenuItem;

/**
 * Created by levis on 2018/6/5.
 */

public class UniversalActivity extends BaseActivity {


    public static final String RootFragmentClassName = "__RootFragmentClassName__";
    public static final String RootFragmentArgs = "__Args__";
    private BaseFragment mRootFragment = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 分析启动Fragment
        String rootFragmentClassName = getIntent().getStringExtra(RootFragmentClassName);
        if (!TextUtils.isEmpty(rootFragmentClassName)) {
            Bundle args = getIntent().getBundleExtra(RootFragmentArgs);
            try {
                Class cls = Class.forName(rootFragmentClassName);
                if (BaseFragment.class.isAssignableFrom(cls)) {
                    mRootFragment = (BaseFragment) cls.newInstance();
                    mRootFragment.setArguments(args);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // 调用onPreActivityCreate
        if (mRootFragment != null) {
            mRootFragment.onPreActivityCreate(this, savedInstanceState);
        }

        // OnCreate
        super.onCreate(savedInstanceState);
        setToolbarType(ToolbarType.TOP);
        setContentView(R.layout.activity_universal);


        // 装配Fragment
        if (mRootFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, mRootFragment, "child_fragment")
                    .commitAllowingStateLoss();
        } else {
            finish();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        boolean res = super.onMenuItemClick(item);
        if (mRootFragment != null) {
            mRootFragment.onMenuItemClick(item);
        }
        return res;
    }

    @Override
    public void finish() {
        if (mRootFragment != null) {
            mRootFragment.onPreFinish(this);
        }
        super.finish();
    }
}