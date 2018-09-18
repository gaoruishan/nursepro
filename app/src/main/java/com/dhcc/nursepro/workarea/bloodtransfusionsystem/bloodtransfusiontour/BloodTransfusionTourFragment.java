package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusiontour;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;

/**
 * BloodTransfusionTourFragment
 * 输血巡视
 *
 * @author DevLix126
 * created at 2018/9/18 10:36
 */
public class BloodTransfusionTourFragment extends BaseFragment {

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blood_transfusion_tour, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));

        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle(getString(R.string.title_bloodtransfusiontour));
        //右上角按钮
        View viewright = View.inflate(getActivity(), R.layout.view_bloodtoolbar_right, null);
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "今日列表", Toast.LENGTH_SHORT).show();
            }
        });
        setToolbarRightCustomView(viewright);

        setToolbarBottomLineVisibility(false);


        initView(view);


    }

    private void initView(View view) {

    }


}
