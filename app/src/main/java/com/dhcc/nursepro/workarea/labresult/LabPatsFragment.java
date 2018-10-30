package com.dhcc.nursepro.workarea.labresult;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.labresult.adapter.PatListAdapter;
import com.dhcc.nursepro.workarea.labresult.api.LabApiManager;
import com.dhcc.nursepro.workarea.labresult.bean.PatsListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LabPatsFragment extends BaseFragment implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    private TextView tvmanage, tvall, tvwait;
    private RecyclerView recall;
    private LinearLayout llEmpty;
    private PatListAdapter patListAdapterAll, patListAdapterManage, patListAdapterWait;
    private List<PatsListBean.PatInfoListBean> listBeansAll, listBeansInBed, listBeansManage, listBeansWait;
    private String showListNow = "all";
    private View showview1, showview2, showview3;
    private SPUtils spUtils = SPUtils.getInstance();

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_patlist_lab, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_labpats), 0xffffffff, 17);
        initView(view);
        initAdapter();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                iniData();
            }
        }, 300);


    }

    private void initView(View view) {


        showview1 = view.findViewById(R.id.view_pats_show1);
        showview2 = view.findViewById(R.id.view_pats_show2);
        showview3 = view.findViewById(R.id.view_pats_show3);
        showgone(showview1);
        llEmpty = view.findViewById(R.id.ll_lab_empty);

        tvall = view.findViewById(R.id.tv_labpats_all);
        tvmanage = view.findViewById(R.id.tv_labpats_manage);
        tvwait = view.findViewById(R.id.tv_labpats_wait);
        tvall.setSelected(true);
        tvall.setOnClickListener(this);
        tvmanage.setOnClickListener(this);
        tvwait.setOnClickListener(this);
        recall = view.findViewById(R.id.recy_checklab_onbedarea);

        recall.setHasFixedSize(true);
        recall.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {
        listBeansAll = new ArrayList<PatsListBean.PatInfoListBean>();
        listBeansInBed = new ArrayList<PatsListBean.PatInfoListBean>();
        listBeansManage = new ArrayList<PatsListBean.PatInfoListBean>();
        listBeansWait = new ArrayList<PatsListBean.PatInfoListBean>();

        patListAdapterAll = new PatListAdapter(new ArrayList<PatsListBean.PatInfoListBean>());
        patListAdapterAll.setOnItemClickListener(this);
        recall.setAdapter(patListAdapterAll);
    }

    private void iniData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("wardId", spUtils.getString("WARDID"));
        map.put("userId", spUtils.getString("USERID"));
        LabApiManager.getPatsList(map, "getInWardPatList", new LabApiManager.GetCheckResultCallback() {
            @Override
            public void onSuccess(PatsListBean patsListBean) {
                hideLoadFailTip();
                //获取所有病人，再分类
                listBeansAll = patsListBean.getPatInfoList();
                for (int i = 0; i < listBeansAll.size(); i++) {
                    if (listBeansAll.get(i).getWait().equals("1")) {
                        listBeansWait.add(listBeansAll.get(i));
                    }
                    if (listBeansAll.get(i).getInBedAll().equals("1")) {
                        listBeansInBed.add(listBeansAll.get(i));
                    }
                    if (listBeansAll.get(i).getManageInBed().equals("1")) {
                        listBeansManage.add(listBeansAll.get(i));
                    }
                }
                showSelectedPats(listBeansInBed);

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void showgone(View view) {
        showview1.setBackgroundColor(getResources().getColor(R.color.white));
        showview2.setBackgroundColor(getResources().getColor(R.color.white));
        showview3.setBackgroundColor(getResources().getColor(R.color.white));
        view.setBackgroundColor(getResources().getColor(R.color.blue));
    }

    private void showSelectedPats(List<PatsListBean.PatInfoListBean> listPats) {
        patListAdapterAll.setNewData(listPats);
        patListAdapterAll.notifyDataSetChanged();
        if (listPats.size() < 1) {
            llEmpty.setVisibility(View.VISIBLE);
        } else {
            llEmpty.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String episodeId = null;
        String patmsg = null;
        if (showListNow.equals("all")) {
            episodeId = listBeansInBed.get(position).getEpisodeId();
            patmsg = listBeansInBed.get(position).getBedCode() + "  " + listBeansInBed.get(position).getName();
        } else if (showListNow.equals("wait")) {
            episodeId = listBeansWait.get(position).getEpisodeId();
            patmsg = "未分床  " + listBeansWait.get(position).getName();
        } else if (showListNow.equals("manage")) {
            episodeId = listBeansManage.get(position).getEpisodeId();
            patmsg = listBeansManage.get(position).getBedCode() + "  " + listBeansManage.get(position).getName();
        }

        Bundle bundle = new Bundle();
        bundle.putString("episodeId", episodeId);
        bundle.putString("patmsg", patmsg);
        startFragment(LabResultListFragment.class, bundle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_labpats_all:
                showSelectedPats(listBeansInBed);
                showListNow = "all";
                setTopFilterSelect(tvall);
                showgone(showview1);
                break;
            case R.id.tv_labpats_manage:
                showSelectedPats(listBeansManage);
                showListNow = "manage";
                setTopFilterSelect(tvmanage);
                showgone(showview2);
                break;
            case R.id.tv_labpats_wait:
                showSelectedPats(listBeansWait);
                showListNow = "wait";
                setTopFilterSelect(tvwait);
                showgone(showview3);
                break;
            default:
                break;
        }

    }

    private void setTopFilterSelect(View view) {
        tvall.setSelected(view == tvall);
        tvmanage.setSelected(view == tvmanage);
        tvwait.setSelected(view == tvwait);
    }
}
