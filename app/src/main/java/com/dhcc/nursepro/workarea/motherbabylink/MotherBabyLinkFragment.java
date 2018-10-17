package com.dhcc.nursepro.workarea.motherbabylink;

import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.Action;
import com.dhcc.nursepro.workarea.milkloopsystem.milkreceive.MilkReceiveFragment;

/**
 * com.dhcc.nursepro.workarea.motherbabylink
 * <p>
 * author Q
 * Date: 2018/10/17
 * Time:9:41
 */
public class MotherBabyLinkFragment extends BaseFragment {
    private View viewright;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_motherbaby_link, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle(getString(R.string.title_motherbaby_link));

        //右上角按钮
        viewright = View.inflate(getActivity(), R.layout.view_save_blue_right, null);
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showToast("保存");
//                saveData();
            }
        });

        setToolbarBottomLineVisibility(false);

//        initView(view);
//
//        mReceiver  = new MilkReceiveFragment.Receiver();
//        filter = new IntentFilter();
//        filter.addAction(Action.DEVICE_SCAN_CODE);
//        getActivity().registerReceiver(mReceiver, filter);

    }

}
