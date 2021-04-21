package com.dhcc.nursepro.workarea.Infusionsituation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.NurseAPI;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.Infusionsituation.adapter.InfusionDetailWayAdapter;
import com.dhcc.nursepro.workarea.Infusionsituation.adapter.InfusionSituationPatAdapter;
import com.dhcc.nursepro.workarea.Infusionsituation.api.InfusionSituationApiManager;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.GetInfusionByWardBean;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.GetInfusionDetailByWardBean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * InfusionDetailFragment
 * 患者输液信息明细
 *
 * @author DevLix126
 * created at 2021/4/20 10:29
 */
public class InfusionDetailFragment extends BaseFragment {

    private TextView tvInfusionDetailPatInfo;
    private RecyclerView recyInfusionDetail;
    private InfusionDetailWayAdapter wayAdapter;

    private String episodeId;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_infusiondetail), 0xffffffff, 17);
//        //右上角按钮"新建"
//        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
//        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
//        textView.setTextSize(15);
//        textView.setText("    ");
//        textView.setTextColor(getResources().getColor(R.color.white));
//        viewright.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        setToolbarRightCustomView(viewright);

        Bundle bundle = getArguments();
        episodeId = bundle.getString("episodeId", "");
        initView(view);
        initAdapter();
        initData();
    }

    private void initView(View view) {
        tvInfusionDetailPatInfo = view.findViewById(R.id.tv_infusiondetail_patinfo);
        recyInfusionDetail = view.findViewById(R.id.recy_infusiondetail);
        recyInfusionDetail.setHasFixedSize(true);
        recyInfusionDetail.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initAdapter() {
        wayAdapter = new InfusionDetailWayAdapter(new ArrayList<>());
        recyInfusionDetail.setAdapter(wayAdapter);
    }

    private void initData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> map = new HashMap<>();
        map.put("episodeId", episodeId);

        InfusionSituationApiManager.GetInfusionDetailByWard(map, NurseAPI.GetInfusionDetailByWard, new InfusionSituationApiManager.GetInfusionDetailByWardCallback() {
            @Override
            public void onSuccess(GetInfusionDetailByWardBean getInfusionDetailByWardBean) {

                hideLoadingTip();
                GetInfusionDetailByWardBean.PatInfoBean patInfoBean = getInfusionDetailByWardBean.getPatInfo();
                String patInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge();
                tvInfusionDetailPatInfo.setText(patInfo);

                wayAdapter.setNewData(getInfusionDetailByWardBean.getWayNoOrdList());
            }
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);
            }
        });


    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_infusion_detail, container, false);
    }
}