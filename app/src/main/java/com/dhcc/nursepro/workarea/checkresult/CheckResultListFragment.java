package com.dhcc.nursepro.workarea.checkresult;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.checkresult.adapter.CheckResultListAdapter;
import com.dhcc.nursepro.workarea.checkresult.api.CheckApiManager;
import com.dhcc.nursepro.workarea.checkresult.api.ShowMsgDialog;
import com.dhcc.nursepro.workarea.checkresult.bean.CheckResultListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckResultListFragment extends BaseFragment {



    private RecyclerView recCheckList;
    private CheckResultListAdapter checkResultListAdapter;
    private List<CheckResultListBean.RisOrdListBean> listBeans;
    private String episodeId,patmsg;
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
        Bundle bundle = getArguments();
        episodeId = bundle.getString("episodeId");
        patmsg = bundle.getString("patmsg");
        setToolbarCenterTitle(patmsg,0xffffffff,17);

        initview(view);
        initData();

    }

    private void initview(View view){


        recCheckList = view.findViewById(R.id.recy_checklist);
        recCheckList.setHasFixedSize(true);
        recCheckList.setLayoutManager(new LinearLayoutManager(getActivity()));
        checkResultListAdapter = new CheckResultListAdapter(new ArrayList<CheckResultListBean.RisOrdListBean>());

        recCheckList.setAdapter(checkResultListAdapter);

        checkResultListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_checkreport){
                    String reportNote = listBeans.get(position).getReportNote();
                    if (listBeans.get(position).getReportStat().equals("Y")) {
                        Bundle bundle = new Bundle();
//                        bundle.putString("oeordId", listBeans.get(position).getOeordId());
//                        bundle.putString("orderName",listBeans.get(position).getOrderName());
//                        startFragment(LabResultDetailFragment.class, bundle);

                        showDialog = new ShowMsgDialog(getActivity());
                        showDialog.setTitle("结果");
                        showDialog.setMessage(reportNote);
                        showDialog.setYesOnclickListener("确定", new ShowMsgDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                showDialog.dismiss();
                            }
                        });
//                        selfDialog.setNoOnclickListener("取消", new ShowBedDialog.onNoOnclickListener() {
//                            @Override
//                            public void onNoClick() {
//                                Toast.makeText(getActivity(),"点击了--取消--按钮",Toast.LENGTH_LONG).show();
//                                selfDialog.dismiss();
//                            }
//                        });
                        showDialog.show();
                    }
                }
            }
        });


    }

    private void initData(){


        HashMap<String,String> map = new HashMap<String, String>();
        map.put("episodeId",episodeId);
        map.put("hospitalId",spUtils.getString(SharedPreference.HOSPITALROWID));
//        map.put("episodeId","96");
//        map.put("hospitalId","0");
        CheckApiManager.getCheckListMsg(map, "getRisOrderList", new CheckApiManager.CheckListCallback() {
            @Override
            public void onSuccess(CheckResultListBean checkResultListBean) {
                listBeans = checkResultListBean.getRisOrdList();
                if (listBeans.size() == 0){
                    showToast("该患者未进行任何检查项目");
                }
                checkResultListAdapter.setNewData(listBeans);
                checkResultListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast(code+":"+msg);
            }
        } );
    }


}













//    private RecyclerView recLabList;
//    private LabResultListAdapter resultListAdapter;
//    private List<LabResultListBean.LabOrderListBean> listBeans;
//    private String episodeId;
//    private View view1;
////    private PopupWindow popupWindow;

//    @Override
//    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_checklist, container, false);
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        setToolbarType(BaseActivity.ToolbarType.TOP);
//        setToolbarBottomLineVisibility(true);
//        setToolbarCenterTitle("检验列表");
//
//        initview(view);
//        initData();
//
//    }
//
//    private void initview(View view){
//
//
//        recLabList = view.findViewById(R.id.recy_lablist);
//        recLabList.setHasFixedSize(true);
//        recLabList.setLayoutManager(new LinearLayoutManager(getActivity()));
//        resultListAdapter = new LabResultListAdapter(new ArrayList<LabResultListBean.LabOrderListBean>());
//        recLabList.setAdapter(resultListAdapter);
//        resultListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
////                startFragment(LabResultDetailFragment.class);
//            }
//        });
//
//
//        Button button = view.findViewById(R.id.iiiii);
////        view1 = view.findViewById(R.id.ll_check);
////        View popview = getActivity().getLayoutInflater().inflate(R.layout.view_showreport,null);
////        popupWindow= new PopupWindow(popview, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,false);
////        popupWindow.setAnimationStyle(R.style.SlideAnimation);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showToast("------");
////                popupWindow.showAtLocation(view1, Gravity.CENTER,0,0);
//                selfDialog = new ShowBedDialog(getActivity());
//                selfDialog.setTitle("提示");
//                selfDialog.setMessage("确定退出应用?");
//                selfDialog.setYesOnclickListener("确定", new ShowBedDialog.onYesOnclickListener() {
//                    @Override
//                    public void onYesClick() {
//                        Toast.makeText(getActivity(),"点击了--确定--按钮",Toast.LENGTH_LONG).show();
//                        selfDialog.dismiss();
//                    }
//                });
//                selfDialog.setNoOnclickListener("取消", new ShowBedDialog.onNoOnclickListener() {
//                    @Override
//                    public void onNoClick() {
//                        Toast.makeText(getActivity(),"点击了--取消--按钮",Toast.LENGTH_LONG).show();
//                        selfDialog.dismiss();
//                    }
//                });
//                selfDialog.show();
//            }
//        });
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    }
//
//    private void initData(){
//
//        Bundle bundle = getArguments();
//        episodeId = bundle.getString("episodeId");
//        HashMap<String,String> map = new HashMap<String, String>();
//        map.put("episodeId",episodeId);
//        LabApiManager.getLabListMsg(map, "getLabOrdList", new LabApiManager.GeLabListCallback() {
//            @Override
//            public void onSuccess(LabResultListBean labResultListBean) {
//                listBeans = labResultListBean.getLabOrderList();
//                resultListAdapter.setNewData(listBeans);
//                resultListAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFail(String code, String msg) {
//                showToast(code+":"+msg);
//            }
//        });
//    }
//
//}
