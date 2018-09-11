package com.dhcc.nursepro.workarea.allotbed;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.allotbed.adapter.EmptyBedListAdapter;
import com.dhcc.nursepro.workarea.allotbed.api.AllotBedApiManager;
import com.dhcc.nursepro.workarea.allotbed.bean.AllotBedInfoBean;
import com.dhcc.nursepro.workarea.allotbed.bean.AllotBedResultBean;
import com.dhcc.nursepro.workarea.allotbed.bean.GetScanPatsBean;
import com.dhcc.nursepro.workarea.patevents.PatEventsFragment;
import com.dhcc.nursepro.workarea.patevents.api.PatEventsApiManager;
import com.dhcc.nursepro.workarea.patevents.bean.ScanGetUserMsgBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.qqtheme.framework.picker.DoublePicker;
import cn.qqtheme.framework.widget.WheelView;

/**
 * com.dhcc.nursepro.workarea.beddistribution
 * <p>
 * author Q
 * Date: 2018/9/6
 * Time:9:41
 */
public class AllotBedFragment extends BaseFragment implements View.OnClickListener{

    private RecyclerView recAllotBed;
    private EmptyBedListAdapter emptyBedListAdapter;
    private List<AllotBedInfoBean.EmptyBedListBean> listBeans;
    private List<AllotBedInfoBean.DoctorListBean> listDocBeans;
    private List<AllotBedInfoBean.NurseListBean> listNurBeans;
    private String selectBedId = "",selectDoc = "",selectNur = "";
    private int selectNurIndex = 0,selectDocIndex = 0,selectPosition = -1;
    private TextView tvMainDoc,tvMainNur,tvPatInfo,tvRegNo;
    private ImageView imgSex;
    private RelativeLayout rlAllotBedScan;

    private SPUtils spUtils = SPUtils.getInstance();
    private String episodeIdNow = null;

    private IntentFilter intentFilter;
    private DataReceiver dataReceiver = null;

    private String regNoNow = "";

    private View viewright;

    private AllotBedResultDialog allotBedResultDialog;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bed_allot, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle("入院分床");
        setToolbarBottomLineVisibility(false);
        //右上角保存按钮
        viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   确认   ");
        textView.setTextColor(getResources().getColor(R.color.blue_dark));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectBedId == ""){
                    showToast("请选择床位再进行分床");
                }else {
                    allotBed();
                }
            }
        });


        initView(view);
        getEmptyBed();


        //扫描广播
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.scanner.broadcast");
        dataReceiver = new DataReceiver();
        getActivity().registerReceiver(dataReceiver,intentFilter);

    }

    private void initView(View view){
        tvMainDoc = view.findViewById(R.id.tv_allotbed_maindoc);
        tvMainDoc.setOnClickListener(this);
        tvMainNur = view.findViewById(R.id.tv_allotbed_mainnur);
        tvMainNur.setOnClickListener(this);
        tvPatInfo = view.findViewById(R.id.tv_allotbed_pat);
        imgSex = view.findViewById(R.id.img_allotbed_sex);
        tvRegNo = view.findViewById(R.id.tv_allotbed_regno);
        rlAllotBedScan = view.findViewById(R.id.rl_allotbed_scan);


        recAllotBed = view.findViewById(R.id.recy_allotbed);
        //提高展示效率
        recAllotBed.setHasFixedSize(true);
        //设置的布局管理
//        recAllotBed.setLayoutManager(new LinearLayoutManager(getActivity()));
        recAllotBed.setLayoutManager(new GridLayoutManager(getActivity(),3));

        emptyBedListAdapter = new EmptyBedListAdapter(new ArrayList<AllotBedInfoBean.EmptyBedListBean>());
        emptyBedListAdapter.setSelectItem(-1);
        recAllotBed.setAdapter(emptyBedListAdapter);

        emptyBedListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TextView textView = view.findViewById(R.id.tv_bed_allot);

//              选中后可选其他，不可取消
                emptyBedListAdapter.setSelectItem(position);
                selectBedId = listBeans.get(position).getBedId();
                emptyBedListAdapter.notifyDataSetChanged();

                //选择后再次点击可取消选择
//                if (position == selectPosition){
//                    if (textView.isSelected()){
//                        emptyBedListAdapter.setSelectItem(-1);
//                        selectBedId = "";
//                    }else {
//                        emptyBedListAdapter.setSelectItem(position);
//                        selectPosition = position;
//                        selectBedId = listBeans.get(position).getBedId();
//                    }
//                }else {
//                    emptyBedListAdapter.setSelectItem(position);
//                    selectPosition = position;
//                    selectBedId = listBeans.get(position).getBedId();
//                }
//                emptyBedListAdapter.notifyDataSetChanged();

            }
        });

    }

    private void getEmptyBed(){
        HashMap<String,String> map = new HashMap<>();
        map.put("wardId",spUtils.getString(SharedPreference.WARDID));
        map.put("locId",spUtils.getString(SharedPreference.LOCID));
        map.put("linkLocId",spUtils.getString(SharedPreference.LINKLOC));
        AllotBedApiManager.getAllotBed(map, "getAllotInfo", new AllotBedApiManager.getAllotBedCallBack() {
            @Override
            public void onSuccess(AllotBedInfoBean allotBedInfoBean) {

                listBeans = allotBedInfoBean.getEmptyBedList();
                listDocBeans = allotBedInfoBean.getDoctorList();
                listNurBeans = allotBedInfoBean.getNurseList();

                emptyBedListAdapter.setNewData(listBeans);

            }

            @Override
            public void onFail(String code, String msg) {
                showToast(code+":"+msg);
            }
        });

    }


    private void allotBed(){
        HashMap<String,String> map = new HashMap<>();
        map.put("wardId",spUtils.getString(SharedPreference.WARDID));
        map.put("locId",spUtils.getString(SharedPreference.LOCID));
        map.put("linkLocId",spUtils.getString(SharedPreference.LINKLOC));
        map.put("episodeId",episodeIdNow);
        map.put("bedId",selectBedId);
        map.put("userId",spUtils.getString(SharedPreference.USERID));
        map.put("mainDoc",selectDoc);
        map.put("mainNurse",selectNur);


        AllotBedApiManager.getAllotBedResult(map, "allotBed", new AllotBedApiManager.getAllotBedResultCallBack() {
            @Override
            public void onSuccess(AllotBedInfoBean allotBedResultBean) {
                listBeans = allotBedResultBean.getEmptyBedList();
                listDocBeans = allotBedResultBean.getDoctorList();
                listNurBeans = allotBedResultBean.getNurseList();
                emptyBedListAdapter.setNewData(listBeans);
                emptyBedListAdapter.notifyDataSetChanged();
                initScanMsg(regNoNow);
                emptyBedListAdapter.setSelectItem(-1);
                emptyBedListAdapter.notifyDataSetChanged();
                selectBedId = "";
//                showToast(allotBedResultBean.getMsg());
                if (allotBedResultDialog != null && allotBedResultDialog.isShowing()) {
                    allotBedResultDialog.dismiss();
                }
                allotBedResultDialog = new AllotBedResultDialog(getActivity());
                allotBedResultDialog.setExecresult("分床成功");
                allotBedResultDialog.setImgId(R.drawable.icon_popup_sucess);
                allotBedResultDialog.setSureVisible(View.GONE);
                allotBedResultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        allotBedResultDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                showToast(code+":"+msg);
            }
        });

    }


    private void initScanMsg(String regNo){

        String wardId = spUtils.getString(SharedPreference.WARDID);
        HashMap<String,String> mapmsg = new HashMap<String, String>();
        mapmsg.put("regNo",regNo);
        mapmsg.put("wardId",wardId);
        regNoNow = regNo;
        AllotBedApiManager.GetUserMsg(mapmsg,"getPatWristInfo", new AllotBedApiManager.GetUserMsgCallBack() {
            @Override
            public void onSuccess(GetScanPatsBean getScanPatsBean) {
                rlAllotBedScan.setVisibility(View.GONE);
                setToolbarRightCustomView(viewright);

                ScanGetUserMsgBean.PatInfoBean patInfoBean = getScanPatsBean.getPatInfo();
                episodeIdNow = patInfoBean.getEpisodeID();
                tvPatInfo.setText("".equals(patInfoBean.getBedCode()) ? "未分"+ "床  " + patInfoBean.getName() : patInfoBean.getBedCode() + "床  " + patInfoBean.getName());
                tvMainDoc.setText("".equals(patInfoBean.getMainDoctor()) ? "请选择":patInfoBean.getMainDoctor());
                tvMainNur.setText("".equals(patInfoBean.getMainNurse()) ? "请选择":patInfoBean.getMainNurse());
                tvRegNo.setText(getScanPatsBean.getPatInfo().getRegNo());

                if (patInfoBean.getMainDoctorID().size()>0) {
                    selectDoc = patInfoBean.getMainDoctorID().get(0);
                }else {
                    selectDoc = "";
                }
                if (patInfoBean.getMainNurseID().size()>0) {
                    selectNur = patInfoBean.getMainNurseID().get(0);
                }else {
                    selectNur = "";
                }
                if (patInfoBean.getSex().equals("男")){
                    imgSex.setImageDrawable(getResources().getDrawable(R.drawable.sex_male));
                }else {
                    imgSex.setImageDrawable(getResources().getDrawable(R.drawable.sex_female));
                }

            }

            @Override
            public void onFail(String code, String msg) {
                showToast(code+":"+msg);
            }
        });

    }

    private void selectDocAndNur(){
//        双选护士医生,显示姓名，对应index的编号
        final List<String> listDocName = new ArrayList<>();
        final List<String> listDocCode = new ArrayList<>();
        final List<String> listNurName = new ArrayList<>();
        final List<String> listNurCode = new ArrayList<>();

        listDocName.add("请选择");
        listDocCode.add("");
        listNurName.add("请选择");
        listNurCode.add("");
//      遍历listDocBeans,获取医生姓名list，根据已分医生的id（selectDoc)获取当前显示值（selectDocIndex)
        for (int i = 0; i < listDocBeans.size(); i++) {
            listDocName.add(listDocBeans.get(i).getName());
            if (selectDoc.equals(listDocBeans.get(i).getID())){
                selectDocIndex = i+1;
            }
            listDocCode.add(listDocBeans.get(i).getID());
        }
        for (int i = 0;i <listNurBeans.size();i++){
            listNurName.add(listNurBeans.get(i).getName());
            if (selectNur.equals(listNurBeans.get(i).getID())){
                selectNurIndex = i+1;
            }
            listNurCode.add(listNurBeans.get(i).getID());
        }

        final DoublePicker picker = new DoublePicker(getActivity(), listDocName,listNurName);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setSelectedIndex(selectDocIndex,selectNurIndex);
        picker.setCycleDisable(true);
        picker.setTextSize(18);
        picker.setTextColor(Color.parseColor("#FF62ABFF"));
        picker.setSubmitTextSize(12);
        picker.setSubmitTextColor(Color.parseColor("#FF62ABFF"));
        picker.setDividerColor(Color.parseColor("#FF62ABFF"));
        picker.setCancelTextSize(12);
        picker.setCancelTextColor(Color.parseColor("#FFC8C8C8"));
        picker.setLineSpaceMultiplier(3);
        picker.setOffset(2);
        picker.setOnPickListener(new DoublePicker.OnPickListener() {
            @Override
            public void onPicked(int selectedFirstIndex, int selectedSecondIndex) {
                selectDoc = listDocCode.get(selectedFirstIndex);
                selectNur = listNurCode.get(selectedSecondIndex);
                tvMainDoc.setText(listDocName.get(selectedFirstIndex));
                tvMainNur.setText(listNurName.get(selectedSecondIndex));
            }
        });
        picker.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_allotbed_maindoc:
                selectDocAndNur();
                break;
            case R.id.tv_allotbed_mainnur:
                selectDocAndNur();
                break;
            default:
                break;
        }
    }


    //扫描腕带获取regNo、wardId
    public class DataReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.scanner.broadcast")){
                Bundle bundle = new Bundle();
                bundle = intent.getExtras();
                initScanMsg(bundle.getString("data"));

            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(dataReceiver);
    }
}
