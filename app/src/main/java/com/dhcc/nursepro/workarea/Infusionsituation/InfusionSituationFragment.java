package com.dhcc.nursepro.workarea.Infusionsituation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.NurseAPI;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.Infusionsituation.adapter.InfusionSituationPatAdapter;
import com.dhcc.nursepro.workarea.Infusionsituation.api.InfusionSituationApiManager;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.GetInfusionByWardBean;
import com.dhcc.nursepro.workarea.Infusionsituation.infusionutils.InfusionOrdExeUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * InfusionSituationFragment
 * 病区输液信息总览
 *
 * @author DevLix126
 * created at 2021/4/19 14:20
 */
public class InfusionSituationFragment extends BaseFragment {
    private RecyclerView recyInfusionsituation;

    private InfusionSituationPatAdapter patAdapter;
    private InfusionOrdExeUtil infusionOrdExeUtil;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_infusionsituation), 0xffffffff, 17);
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
        initView(view);
        initAdapter();
        infusionOrdExeUtil = new InfusionOrdExeUtil(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initView(View view) {
        recyInfusionsituation = view.findViewById(R.id.recy_infusionsituation);
        recyInfusionsituation.setHasFixedSize(true);
        recyInfusionsituation.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {
        patAdapter = new InfusionSituationPatAdapter(new ArrayList<>());
        patAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GetInfusionByWardBean.PatInfusionListBean patInfusionListBean = (GetInfusionByWardBean.PatInfusionListBean) adapter.getItem(position);
                String episodeId = patInfusionListBean.getPatInfo().getEpisodeID();
                Bundle bundle = new Bundle();
                bundle.putString("episodeId", episodeId);
                startFragment(InfusionDetailFragment.class, bundle);
            }
        });

        recyInfusionsituation.setAdapter(patAdapter);
    }

    private void initData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> map = new HashMap<>();
        map.put("wardId", spUtils.getString(SharedPreference.WARDID));
        map.put("userId", spUtils.getString(SharedPreference.USERID));
        InfusionSituationApiManager.GetInfusionByWard(map, NurseAPI.GetInfusionByWard, new InfusionSituationApiManager.GetInfusionByWardCallback() {
            @Override
            public void onSuccess(GetInfusionByWardBean getInfusionByWardBean) {
                hideLoadingTip();
                patAdapter.setNewData(getInfusionByWardBean.getPatInfusionList());
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
        return inflater.inflate(R.layout.fragment_infusion_situation, container, false);
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            infusionOrdExeUtil.setScanInfo(bundle.getString("data"));
            infusionOrdExeUtil.getScanInfo();
        }
    }
}