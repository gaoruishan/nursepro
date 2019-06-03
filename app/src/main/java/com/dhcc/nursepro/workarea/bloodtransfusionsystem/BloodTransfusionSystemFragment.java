package com.dhcc.nursepro.workarea.bloodtransfusionsystem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
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
    private LinearLayout LLBloodtsSign;
    private LinearLayout LLBloodtsTrans;
    private LinearLayout LLBloodtsTranstour;
    private LinearLayout LLBloodtsTransend;
    private LinearLayout LLBloodtsBagrecycling;
    private LinearLayout LLBloodtsSearch;
    private LinearLayout LLBack;


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blood_transfusion_system, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);
//        setToolbarBackground(new ColorDrawable(0xffffffff));
//        showToolbarNavigationIcon(R.drawable.icon_back_blue);
//
//        setToolbarCenterTitle(getString(R.string.title_bloodtransfusionsystem));
//        setToolbarBottomLineVisibility(false);


        initView(view);


    }

    private void initView(View view) {
        LLBloodtsSign = view.findViewById(R.id.ll_bloodts_sign);
        LLBloodtsSign.setOnClickListener(this);
        LLBloodtsTrans = view.findViewById(R.id.ll_bloodts_trans);
        LLBloodtsTrans.setOnClickListener(this);
        LLBloodtsTranstour = view.findViewById(R.id.ll_bloodts_transtour);
        LLBloodtsTranstour.setOnClickListener(this);
        LLBloodtsTransend = view.findViewById(R.id.ll_bloodts_transend);
        LLBloodtsTransend.setOnClickListener(this);
        LLBloodtsBagrecycling = view.findViewById(R.id.ll_bloodts_bagrecycling);
        LLBloodtsBagrecycling.setOnClickListener(this);
        LLBloodtsSearch = view.findViewById(R.id.ll_bloodts_search);
        LLBloodtsSearch.setOnClickListener(this);
        LLBack = view.findViewById(R.id.ll_blood_back);
        LLBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_bloodts_sign:
                startFragment(BloodSignFragment.class);
                break;
            case R.id.ll_bloodts_trans:
                startFragment(BloodTransfusionFragment.class);
                break;
            case R.id.ll_bloodts_transtour:
                startFragment(BloodTransfusionTourFragment.class);
                break;
            case R.id.ll_bloodts_transend:
                startFragment(BloodTransfusionEndFragment.class);
                break;
            case R.id.ll_bloodts_bagrecycling:
                startFragment(BloodBagRecyclingFragment.class);
                break;
            case R.id.ll_bloodts_search:
                startFragment(BloodOperationListFragment.class);
                break;
            case R.id.ll_blood_back:
                finish();
                break;
            default:
                break;

        }
    }
}
