package com.dhcc.nursepro.workarea.milkloopsystem;

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
import com.dhcc.nursepro.workarea.milkloopsystem.milkbottling.MilkBottlingFragment;
import com.dhcc.nursepro.workarea.milkloopsystem.milkcoldstorage.MilkColdstorageFragment;
import com.dhcc.nursepro.workarea.milkloopsystem.milkfreezing.MilkFreezingFragment;
import com.dhcc.nursepro.workarea.milkloopsystem.milkreceive.MilkReceiveFragment;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem
 * <p>
 * author Q
 * Date: 2018/9/20
 * Time:9:53
 */
public class MilkLoopSystemFragment extends BaseFragment implements View.OnClickListener{

    private TextView tvReceive,tvFreezing,tvBottling,tvCold;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_milkloop_system, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle(getString(R.string.title_milk_receive));
        setToolbarBottomLineVisibility(false);


        initView(view);


    }

    private void initView(View view){

        tvReceive = view.findViewById(R.id.tv_milk_receive);
        tvReceive.setOnClickListener(this);
        tvFreezing = view.findViewById(R.id.tv_milk_freezing);
        tvFreezing.setOnClickListener(this);
        tvBottling = view.findViewById(R.id.tv_milk_bottling);
        tvBottling.setOnClickListener(this);
        tvCold = view.findViewById(R.id.tv_milk_cold);
        tvCold.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_milk_receive:
                startFragment(MilkReceiveFragment.class);
                break;
            case R.id.tv_milk_freezing:
                startFragment(MilkFreezingFragment.class);
                break;
            case R.id.tv_milk_bottling:
                startFragment(MilkBottlingFragment.class);
                break;
            case R.id.tv_milk_cold:
                startFragment(MilkColdstorageFragment.class);
                break;
            default:
                break;
        }

    }
}

