package com.dhcc.module.infusion.workarea.comm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;

import com.base.commlibs.BaseFragment;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.dosing.bean.OrdListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-06-20/16:38
 * @email:grs0515@163.com
 */
public class BaseInfusionFragment extends BaseFragment {

    protected String scanInfo;
    protected Activity mContext;
    protected List<String> listId;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = this.getActivity();
        setCommToolBar();
    }

    private void setCommToolBar() {
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        setToolbarBottomLineVisibility(true);
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        mContext.findViewById(R.id.toolbar_bottom_line).setBackgroundColor(ContextCompat.getColor(mContext, R.color.blue_dark2));
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        scanInfo = doScanInfo(intent);
    }

    protected boolean checkListOeoreId(List<OrdListBean> list, String s) {
        //检验
        boolean isContain = false;
        listId = new ArrayList<>();
        for (OrdListBean b : list) {
            listId.add(b.getOeoreId());
            String bOeoreId = b.getOeoreId();
            if (bOeoreId.contains("-")) {
                bOeoreId = bOeoreId.replaceAll("-", "\\|\\|");
            }
            if (bOeoreId.equals(scanInfo)) {
                isContain = true;
            }
        }
        if (!isContain) {
            ToastUtils.showShort(s);
            return true;
        }
        return false;
    }

    /**
     * 设置ToolBar 右侧-患者列表
     */
    protected void addPatListToToolbarRight() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tv_custom_view, null);
        setToolbarRightCustomView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startFragment(PatListFragment.class);
            }
        });
    }

}
