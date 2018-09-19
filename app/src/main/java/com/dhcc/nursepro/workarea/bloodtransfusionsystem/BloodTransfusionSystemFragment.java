package com.dhcc.nursepro.workarea.bloodtransfusionsystem;

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
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodbagrecycling.BloodBagRecyclingFragment;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodsign.BloodSignFragment;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.BloodTransfusionFragment;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusionend.BloodTransfusionEndFragment;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusiontour.BloodTransfusionTourFragment;

/**
 * BloodTransfusionSystemFragment
 * 输血系统
 *
 * @author DevLix126
 * created at 2018/9/18 09:52
 */
public class BloodTransfusionSystemFragment extends BaseFragment implements View.OnClickListener {
    private TextView tvBloodtsSign;
    private TextView tvBloodtsTrans;
    private TextView tvBloodtsTranstour;
    private TextView tvBloodtsTransend;
    private TextView tvBloodtsBagrecycling;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blood_transfusion_system, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle(getString(R.string.title_bloodtransfusionsystem));
        setToolbarBottomLineVisibility(false);


        initView(view);


    }

    private void initView(View view) {
        tvBloodtsSign = view.findViewById(R.id.tv_bloodts_sign);
        tvBloodtsSign.setOnClickListener(this);
        tvBloodtsTrans = view.findViewById(R.id.tv_bloodts_trans);
        tvBloodtsTrans.setOnClickListener(this);
        tvBloodtsTranstour = view.findViewById(R.id.tv_bloodts_transtour);
        tvBloodtsTranstour.setOnClickListener(this);
        tvBloodtsTransend = view.findViewById(R.id.tv_bloodts_transend);
        tvBloodtsTransend.setOnClickListener(this);
        tvBloodtsBagrecycling = view.findViewById(R.id.tv_bloodts_bagrecycling);
        tvBloodtsBagrecycling.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bloodts_sign:
                startFragment(BloodSignFragment.class);
                break;
            case R.id.tv_bloodts_trans:
                startFragment(BloodTransfusionFragment.class);
                break;
            case R.id.tv_bloodts_transtour:
                startFragment(BloodTransfusionTourFragment.class);
                break;
            case R.id.tv_bloodts_transend:
                startFragment(BloodTransfusionEndFragment.class);
                break;
            case R.id.tv_bloodts_bagrecycling:
                startFragment(BloodBagRecyclingFragment.class);
                break;
            default:
                break;

        }
    }
}
