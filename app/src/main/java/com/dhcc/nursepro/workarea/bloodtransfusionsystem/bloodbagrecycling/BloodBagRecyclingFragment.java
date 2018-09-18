package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodbagrecycling;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;

/**
 * BloodBagRecyclingFragment
 * 血袋回收
 *
 * @author DevLix126
 * created at 2018/9/18 10:28
 */
public class BloodBagRecyclingFragment extends BaseFragment {

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blood_bag_recycling, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));

        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle(getString(R.string.title_bloodbagrecycling));
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
