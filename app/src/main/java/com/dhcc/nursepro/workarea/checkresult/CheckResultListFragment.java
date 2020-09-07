package com.dhcc.nursepro.workarea.checkresult;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.dhcc.nursepro.R;
import com.base.commlibs.constant.SharedPreference;
import com.dhcc.nursepro.workarea.checkresult.adapter.CheckResultListAdapter;
import com.dhcc.nursepro.workarea.checkresult.api.CheckApiManager;
import com.dhcc.nursepro.workarea.checkresult.api.ShowMsgDialog;
import com.dhcc.nursepro.workarea.checkresult.bean.CheckResultListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckResultListFragment extends BaseFragment {
    private RecyclerView recCheckList;
    private LinearLayout llEmpty;
    private CheckResultListAdapter checkResultListAdapter;
    private List<CheckResultListBean.RisOrdListBean> listBeans =new ArrayList<>();
    private String episodeId, patmsg;
    private ShowMsgDialog showDialog;
    private SPUtils spUtils = SPUtils.getInstance();

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_checklist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        if (isSingleModel){
            episodeId=singleEpisodeId;
            patmsg = "检查报告";
        }else {
            Bundle bundle = getArguments();
            episodeId = bundle.getString("episodeId");
            patmsg = bundle.getString("patmsg");
        }
        setToolbarCenterTitle(patmsg, 0xffffffff, 17);
        initview(view);
        initAdapter();
        initData();

    }

    private void initview(View view) {


        llEmpty = view.findViewById(R.id.ll_checklist_empty);
        recCheckList = view.findViewById(R.id.recy_checklist);
        recCheckList.setHasFixedSize(true);
        recCheckList.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initAdapter() {

        checkResultListAdapter = new CheckResultListAdapter(new ArrayList<CheckResultListBean.RisOrdListBean>());
        recCheckList.setAdapter(checkResultListAdapter);
        checkResultListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_checkreport) {
                    String reportNote = listBeans.get(position).getReportNote();
                    if (listBeans.get(position).getReportStat().equals("Y")) {

                        showDialog = new ShowMsgDialog(getActivity());
                        showDialog.setTitle("结果");
                        showDialog.setMessage(reportNote);
                        showDialog.setYesOnclickListener("确定", new ShowMsgDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                showDialog.dismiss();
                            }
                        });

                        showDialog.show();
                    }
                }
            }
        });

    }

    private void initData() {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("episodeId", episodeId);
        map.put("hospitalId", spUtils.getString(SharedPreference.HOSPITALROWID));
        //        map.put("episodeId","96");
        //        map.put("hospitalId","0");
        CheckApiManager.getCheckListMsg(map, "getRisOrderList", new CheckApiManager.CheckListCallback() {
            @Override
            public void onSuccess(CheckResultListBean checkResultListBean) {
                listBeans = checkResultListBean.getRisOrdList();
                if (listBeans.size() == 0) {
                    llEmpty.setVisibility(View.VISIBLE);
                } else {
                    llEmpty.setVisibility(View.GONE);
                }
                checkResultListAdapter.setNewData(listBeans);
                checkResultListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

}

