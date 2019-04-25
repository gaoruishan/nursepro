package com.dhcc.nursepro.workarea.nurtour;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.Action;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.nurrecord.FlowRadioGroup;
import com.dhcc.nursepro.workarea.nurrecord.ItemValueDialog;
import com.dhcc.nursepro.workarea.nurrecord.bean.NurRecordBean;
import com.dhcc.nursepro.workarea.nurtour.adapter.NurTourListAdapter;
import com.dhcc.nursepro.workarea.nurtour.adapter.NurTourTypeAdapter;
import com.dhcc.nursepro.workarea.nurtour.adapter.TourAllAdapter;
import com.dhcc.nursepro.workarea.nurtour.adapter.TourPatTypeAdapter;
import com.dhcc.nursepro.workarea.nurtour.adapter.TourPatslistAdapter;
import com.dhcc.nursepro.workarea.nurtour.api.TourApiManager;
import com.dhcc.nursepro.workarea.nurtour.bean.AllTourListBean;
import com.dhcc.nursepro.workarea.nurtour.bean.GradeModelBean;
import com.dhcc.nursepro.workarea.nurtour.bean.GradeTourListBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.nex3z.flowlayout.FlowLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * com.dhcc.nursepro.workarea.nurtour
 * <p>
 * author Q
 * Date: 2019/4/20
 * Time:9:35
 */
public class NurTourFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener {

    private LinearLayout llType;
    private LinearLayout llTourlist;
    private RelativeLayout llTourSend;

    private RecyclerView recPatlist;
    private RecyclerView recPatType;
    private RecyclerView recAll;
    private RecyclerView recTimeout;
    private RecyclerView recNurTour;
    private RecyclerView recNurList;
    private RecyclerView recBloodList;
    private RecyclerView recDosingList;

    private LinearLayout llMainAll;
    private LinearLayout llMainNurList;
    private LinearLayout llMainDosingList;
    private LinearLayout llMainBloodList;


    private LinearLayout llMainNur;
    private LinearLayout llMainDosing;
    private LinearLayout llMainBlood;
    private LinearLayout llMainSave;

    private TextView tvCancle,tvSave;

    private TextView tvAll,tvNur,tvDosing,tvBlood;
    private View show1, show2, show3, show4;

    private LinearLayout llTypeAll,llPatList,llTypeNur,llTypeDosing,llTypeBlood;

    private TourAllAdapter tourAllAdapter;
    private TourPatslistAdapter patsAdapter;
    private TourPatTypeAdapter tourPatTypeAdapter;
    private NurTourTypeAdapter nurTourTypeAdapter;
    private NurTourListAdapter nurTourListAdapter;


    private SPUtils spUtils = SPUtils.getInstance();

    private List<AllTourListBean.PatInfoListBean> patsListBeanList =new ArrayList<>();
    private List<AllTourListBean.PatInfoListBean> patsListBeanFilter =new ArrayList<>();
    private List<AllTourListBean.TopFilterBean> topFilterBeans =new ArrayList<>();
    private List<AllTourListBean.TourDataListBeanX> tourDataListBeans =new ArrayList<>();
    private List<GradeTourListBean.LeftFilterBean> gradeTourTypeList = new ArrayList<>();
    private List<GradeTourListBean.PatInfoListBean> gradePatTourList = new ArrayList<>();

    private String topTypeSelected = "nur";
    private String regNo = "";
    private String episodeId = "";


    private TextView textViewChooseDateTime;
    private String dt = "date";

    private List<GradeModelBean.ModelListBean> listBeans =new ArrayList<>();
    private Map patInfoMap = new HashMap<String, String>();

    private ItemValueDialog showDialog;

    private String itemNum = "";
    private int rbnum;;


    private FlowLayout recordContentView;
    private HashMap<String, View> viewItemMap;




    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nurtour, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle((getString(R.string.title_nurtour)), 0xffffffff, 17);

        initView(view);
        initAdapter();

        initDataAll();
        initDataGrade();

    }

    private void initView(View view){

        viewItemMap = new HashMap<>();

        llType = view.findViewById(R.id.ll_alltype);
        llTourlist = view.findViewById(R.id.ll_tour_list);
        llTourSend = view.findViewById(R.id.ll_tour_send);

        recPatlist = view.findViewById(R.id.recy_tour_patlist);
        recPatType = view.findViewById(R.id.recy_tour_pattype);
        recAll = view.findViewById(R.id.recy_tour_listall);
        recNurTour = view.findViewById(R.id.recy_tour_nur);
        recNurList = view.findViewById(R.id.recy_tour_nurlist);
        recBloodList = view.findViewById(R.id.recy_tour_blood);
        recDosingList = view.findViewById(R.id.recy_tour_dosing);

        llMainAll = view.findViewById(R.id.ll_tour_main_all);
        llMainNurList = view.findViewById(R.id.ll_tour_main_nurlist);
        llMainBloodList = view.findViewById(R.id.ll_tourmain_bloodlist);
        llMainDosingList = view.findViewById(R.id.ll_tourmain_dosinglist);
        llMainNur = view.findViewById(R.id.ll_tourmain_nur);
        llMainDosing = view.findViewById(R.id.ll_tourmain_dosing);
        llMainBlood = view.findViewById(R.id.ll_tourmain_blood);
        llMainSave = view.findViewById(R.id.ll_tourmain_save);

        tvAll = view.findViewById(R.id.tv_tour_all);
        tvAll.setOnClickListener(this);
        tvNur = view.findViewById(R.id.tv_tour_nur);
        tvNur.setOnClickListener(this);
        tvBlood = view.findViewById(R.id.tv_tour_blood);
        tvBlood.setOnClickListener(this);
        tvDosing = view.findViewById(R.id.tv_tour_dosing);
        tvDosing.setOnClickListener(this);

        show1 = view.findViewById(R.id.view_tour_show1);
        show2 = view.findViewById(R.id.view_tour_show2);
        show3 = view.findViewById(R.id.view_tour_show3);
        show4 = view.findViewById(R.id.view_tour_show4);

        llTypeAll = view.findViewById(R.id.ll_tourtype_all);
        llPatList = view.findViewById(R.id.ll_tourtype_patlist);
        llTypeNur = view.findViewById(R.id.ll_tourtype_nur);
        llTypeDosing = view.findViewById(R.id.ll_tourtype_dosing);
        llTypeBlood = view.findViewById(R.id.ll_tourtype_blood);

        tvCancle = view.findViewById(R.id.tv_tour_cancle);
        tvCancle.setOnClickListener(this);
        tvSave = view.findViewById(R.id.tv_tour_save);
        tvSave.setOnClickListener(this);

        recordContentView = view.findViewById(R.id.fl_modeldetail);

        llTourlist.setVisibility(View.VISIBLE);
        llTourSend.setVisibility(View.GONE);
        setTopFilterSelect(tvNur);
        showgone(show2);
        insectionTypeGone(llTypeNur);
        mainAreaGone(llMainNurList);

        //提高展示效率
        recAll.setHasFixedSize(true);
        //设置的布局管理
        recAll.setLayoutManager(new LinearLayoutManager(getActivity()));

        //提高展示效率
        recPatlist.setHasFixedSize(true);
        //设置的布局管理
        recPatlist.setLayoutManager(new LinearLayoutManager(getActivity()));

        //提高展示效率
        recPatType.setHasFixedSize(true);
        //设置的布局管理
        recPatType.setLayoutManager(new LinearLayoutManager(getActivity()));

        //提高展示效率
        recNurTour.setHasFixedSize(true);
        //设置的布局管理
        recNurTour.setLayoutManager(new LinearLayoutManager(getActivity()));

        //提高展示效率
        recNurList.setHasFixedSize(true);
        //设置的布局管理
        recNurList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {

        tourAllAdapter = new TourAllAdapter(new ArrayList<AllTourListBean.TourDataListBeanX>(),getActivity());
        recAll.setAdapter(tourAllAdapter);

        nurTourTypeAdapter = new NurTourTypeAdapter(new ArrayList<GradeTourListBean.LeftFilterBean>());
        recNurList.setAdapter(nurTourTypeAdapter);
        nurTourTypeAdapter.setSelectItem(0);
        nurTourTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                nurTourTypeAdapter.setSelectItem(position);
                nurTourTypeAdapter.notifyDataSetChanged();
                showToast(gradeTourTypeList.get(position).getDesc());
            }
        });
        nurTourListAdapter = new NurTourListAdapter(new ArrayList<GradeTourListBean.PatInfoListBean>(), getContext());
        recNurTour.setAdapter(nurTourListAdapter);

        patsAdapter = new TourPatslistAdapter(new ArrayList<AllTourListBean.PatInfoListBean>());
        recPatlist.setAdapter(patsAdapter);
        patsAdapter.setSelectItem(0);
        patsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                patsAdapter.setSelectItem(position);
                patsAdapter.notifyDataSetChanged();
                episodeId = patsListBeanFilter.get(position).getEpisodeId();
                initDataAll();
            }
        });

        tourPatTypeAdapter = new TourPatTypeAdapter(new ArrayList<AllTourListBean.TopFilterBean>());
        recPatType.setAdapter(tourPatTypeAdapter);
        tourPatTypeAdapter.setSelectItem(0);
        tourPatTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                tourPatTypeAdapter.setSelectItem(position);
                tourPatTypeAdapter.notifyDataSetChanged();
                patFilter(topFilterBeans.get(position).getCode());
            }
        });
    }

    private void initDataGrade(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
            HashMap<String,String> map = new HashMap<>();
            map.put("locId",spUtils.getString(SharedPreference.LOCID));
            map.put("userId",spUtils.getString(SharedPreference.USERID));
//        map.put("adm",episodeId);
            TourApiManager.getGradeTourList(map, "getGradeTourList", new TourApiManager.getGradeTourListcall() {
                @Override
                public void onSuccess(GradeTourListBean gradeTourListBean) {
                    gradeTourTypeList = gradeTourListBean.getLeftFilter();
                    nurTourTypeAdapter.setNewData(gradeTourTypeList);
                    nurTourTypeAdapter.notifyDataSetChanged();

                    gradePatTourList = gradeTourListBean.getPatInfoList();
                    nurTourListAdapter.setNewData(gradePatTourList);
                    nurTourListAdapter.notifyDataSetChanged();

                    hideLoadingTip();
                }
                @Override
                public void onFail(String code, String msg) {
                    showToast("error" + code + ":" + msg);
                    hideLoadingTip();
                }
            });
    }

    private void initDataAll(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap<String,String> map = new HashMap<>();
        map.put("locId",spUtils.getString(SharedPreference.LOCID));
        map.put("userId",spUtils.getString(SharedPreference.USERID));
        if (!(episodeId.equals(""))){
            map.put("episodeId",episodeId);
        }
        TourApiManager.getPatsList(map, "getSumTourList", new TourApiManager.getPatsListCallback() {
            @Override
            public void onSuccess(AllTourListBean tourPatsListBean) {
                if ((episodeId.equals(""))) {
                    patsListBeanList = tourPatsListBean.getPatInfoList();
                    patsListBeanFilter = patsListBeanList;
                    patsAdapter.setNewData(patsListBeanFilter);
                    patsAdapter.notifyDataSetChanged();

                    topFilterBeans = tourPatsListBean.getTopFilter();
                    tourPatTypeAdapter.setNewData(topFilterBeans);
                    tourPatTypeAdapter.notifyDataSetChanged();
                }

                tourDataListBeans = tourPatsListBean.getTourDataList();
                tourAllAdapter.setNewData(tourDataListBeans);
                tourAllAdapter.notifyDataSetChanged();

                hideLoadingTip();
            }
            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
                hideLoadingTip();
            }
        });
    }

    private void initDataInfusion(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap<String,String> map = new HashMap<>();
        map.put("locId",spUtils.getString(SharedPreference.LOCID));
        map.put("userId",spUtils.getString(SharedPreference.USERID));



    }

    private void initDataInBlood(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap<String,String> map = new HashMap<>();
        map.put("locId",spUtils.getString(SharedPreference.LOCID));
        map.put("userId",spUtils.getString(SharedPreference.USERID));


    }



    private void initDataGradeModel() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap<String, String> map = new HashMap<>();
        map.put("locId", "197");
        map.put("episodeId", "94");
        TourApiManager.getGradeModel(map, "getGradeModelData", new TourApiManager.getGradeModelcall() {
            @Override
            public void onSuccess(GradeModelBean gradeModelBean) {
//                nurRecordBean = modelDetailBean;
                listBeans = gradeModelBean.getModelList();
//                Gson gson = new Gson();
//                String result = gson.toJson(nurRecordBean.getPatInfo());
//                patInfoMap = gson.fromJson(result, HashMap.class);
                drawInputItems();

                hideLoadFailTip();
            }
            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
                hideLoadFailTip();
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tour_all:
                topTypeSelected = "all";
                setTopFilterSelect(tvAll);
                showgone(show1);
                insectionTypeGone(llPatList);
                mainAreaGone(llMainAll);
                break;
            case R.id.tv_tour_nur:
                topTypeSelected = "nur";
                setTopFilterSelect(tvNur);
                showgone(show2);
                insectionTypeGone(llTypeNur);
                mainAreaGone(llMainNurList);
                break;
            case R.id.tv_tour_dosing:
                topTypeSelected = "dosing";
                setTopFilterSelect(tvDosing);
                showgone(show3);
                insectionTypeGone(llTypeDosing);
                mainAreaGone(llMainDosingList);
                break;
            case R.id.tv_tour_blood:
                topTypeSelected = "blood";
                setTopFilterSelect(tvBlood);
                showgone(show4);
                insectionTypeGone(llTypeBlood);
                mainAreaGone(llMainBloodList);
                break;
            case R.id.tv_tour_cancle:
                seToptEnable(true);
                if (topTypeSelected.equals("all")){
                    onClick(tvAll);
                }else if (topTypeSelected.equals("nur")){
                    onClick(tvNur);
                } else if (topTypeSelected.equals("dosing")){
                    onClick(tvDosing);
                }else if (topTypeSelected.equals("blood")){
                    onClick(tvBlood);
                }
                llTourSend.setVisibility(View.GONE);
                llTourlist.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_tour_save:

                String  strSend = "";
                for (int i = 0; i < listBeans.size(); i++) {


                    if (listBeans.get(i).getItemCode().startsWith("DHC")) {
                        strSend = strSend + "^" + listBeans.get(i).getSendValue();
                    }
                    if ("1".equals(listBeans.get(i).getMustFill())) {
                        if (StringUtils.isEmpty(listBeans.get(i).getSendValue())) {
                            showToast(listBeans.get(i).getItemDesc() + "--未填写");
                            return;
                        }
                    }
                    if (i == listBeans.size() - 1) {

                        showToast(strSend);
                        Log.v("111send", strSend);
                    }
                }

                llType.setVisibility(View.VISIBLE);
                break;
//            case R.id.tv_labout_startdate:
//                dateStr = "start";
//                chooseTime(TimeUtils.string2Millis(tvStartDate.getText().toString() + " 00:00:00"));
//                break;
//            case R.id.tv_labout_enddate:
//                dateStr = "end";
//                chooseTime(TimeUtils.string2Millis(tvEndDate.getText().toString() + " 00:00:00"));
//                break;
            default:
                break;
        }
    }


    private void setTopFilterSelect(View view) {
        tvAll.setSelected(view == tvAll);
        tvNur.setSelected(view == tvNur);
        tvDosing.setSelected(view == tvDosing);
        tvBlood.setSelected(view == tvBlood);
    }

    private void seToptEnable(Boolean b){
        tvAll.setEnabled(b);
        tvNur.setEnabled(b);
        tvDosing.setEnabled(b);
        tvBlood.setEnabled(b);
    }

    private void showgone(View view) {
        show1.setBackgroundColor(getResources().getColor(R.color.blue_light));
        show2.setBackgroundColor(getResources().getColor(R.color.blue_light));
        show3.setBackgroundColor(getResources().getColor(R.color.blue_light));
        show4.setBackgroundColor(getResources().getColor(R.color.blue_light));
        view.setBackgroundColor(getResources().getColor(R.color.blue));
    }
    private void insectionTypeGone(View view) {
        llTypeAll.setVisibility(View.GONE);
        llPatList.setVisibility(View.GONE);
        llTypeNur.setVisibility(View.GONE);
        llTypeDosing.setVisibility(View.GONE);
        llTypeBlood.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
    }

    private void mainAreaGone(View view) {
        llMainAll.setVisibility(View.GONE);
        llMainNurList.setVisibility(View.GONE);
        llMainBloodList.setVisibility(View.GONE);
        llMainDosingList.setVisibility(View.GONE);
        llMainNur.setVisibility(View.GONE);
        llMainDosing.setVisibility(View.GONE);
        llMainBlood.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
    }

    private void patFilter(String pattype){
        patsListBeanFilter = new ArrayList<>();
        for (int i = 0;i<patsListBeanList.size();i++){

            switch (pattype) {
                case "inBedAll":
                    if (patsListBeanList.get(i).getInBedAll().equals("1")){
                        patsListBeanFilter.add(patsListBeanList.get(i));
                    }
                    break;
                case "manageInBed":
                    if (patsListBeanList.get(i).getManageInBed().equals("1")){
                        patsListBeanFilter.add(patsListBeanList.get(i));
                    }
                    break;
                case "todayOut":
                    if (patsListBeanList.get(i).getTodayOut().equals("1")){
                        patsListBeanFilter.add(patsListBeanList.get(i));
                    }
                    break;
                case "allOut":
                    if (patsListBeanList.get(i).getAllOut().equals("1")){
                        patsListBeanFilter.add(patsListBeanList.get(i));
                    }
                    break;
                case "wait":
                    if (patsListBeanList.get(i).getWait().equals("1")){
                        patsListBeanFilter.add(patsListBeanList.get(i));
                    }
                    break;
                default:
                    break;
            }

        }
        patsAdapter.setNewData(patsListBeanFilter);
        patsAdapter.setSelectItem(0);
        recPatlist.scrollToPosition(0);
        episodeId = patsListBeanFilter.get(0).getEpisodeId();
        initDataAll();
        patsAdapter.notifyDataSetChanged();
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Objects.requireNonNull(intent.getAction()).equals(Action.DEVICE_SCAN_CODE)) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            showToast(bundle.getString("data"));
            mainAreaGone(llMainNur);
            llTourlist.setVisibility(View.GONE);
            llTourSend.setVisibility(View.VISIBLE);
            seToptEnable(false);
            initDataGradeModel();
        }

    }








    /**
     * 绘制UI相关
     */
    public void drawInputItems() {
        recordContentView.removeAllViews();
        for (int i = 0; i < listBeans.size(); i++) {
            GradeModelBean.ModelListBean config = listBeans.get(i);
            recordContentView.addView(drawItem(config));
        }
    }

    private void inputItemsValue() {

        //        for (int i = 0; i < recordInfo.getTempList().size(); i ++){
        //            VitalSignRecordBean.TempListBean temp = recordInfo.getTempList().get(i);
        //            View view =  viewItemMap.get(temp.getCode());
        //            if (view instanceof EditText){
        //                EditText ed = (EditText)view;
        //                ed.setText(temp.getValue());
        //            }else{
        //                OptionView op = (OptionView)view;
        //                op.setText(temp.getValue());
        //            }
        //        }
    }

    /**
     * 区分不同View类型，添加进容器
     *
     * @param config
     * @return
     */
    private LinearLayout drawItem(GradeModelBean.ModelListBean config) {

        int height = 3* ConvertUtils.dp2px(Float.parseFloat(config.getHeight()));
        int width = ConvertUtils.dp2px(Float.parseFloat(config.getWidth()));

        LinearLayout layout = new LinearLayout(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height);

        layout.setLayoutParams(params);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setBackgroundResource(R.drawable.vital_sign_border);

        //统一名称textview
        TextView titleTV = new TextView(getContext());
        titleTV.setText(config.getItemDesc());
        titleTV.setTextSize(Float.parseFloat(config.getFontSize()));

        //判断是否必填，必填的话字体变红
        if ("1".equals(config.getMustFill())) {
            titleTV.setTextColor(ContextCompat.getColor(getActivity(), R.color.nurrecord_text_mustfill_color));
        } else {
            titleTV.setTextColor(ContextCompat.getColor(getActivity(), R.color.nurrecord_text_normal_color));
        }

        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.setMargins(ConvertUtils.dp2px(5), 0, 5, 0);
        titleTV.setLayoutParams(titleParams);
        titleTV.setGravity(Gravity.TOP);

        titleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(config.getToastStr())) {
                    showToast(config.getToastStr());
                }
            }
        });
        layout.addView(titleTV);

        if ("E".equals(config.getItemType())) {
            //输入框
            if ("0".equals(config.getTitleHiddeFlag())) {
                titleTV.setVisibility(View.GONE);
            }
            EditText edText = new EditText(getContext());
            int sw = ScreenUtils.getScreenWidth();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
            edText.setLayoutParams(layoutParams);
            edText.setBackgroundResource(R.drawable.vital_sign_input_bg);
            edText.setPadding(ConvertUtils.dp2px(5), 0, ConvertUtils.dp2px(5), 0);
            edText.setTextSize(Float.parseFloat(config.getFontSize()));
            edText.setSingleLine();
            viewItemMap.put(config.getItemCode(),edText);
            edText.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showDialog = new ItemValueDialog(getActivity(),edText.hasFocusable());
                    showDialog.setTitle(config.getItemDesc());
                    showDialog.setMessage(edText.getText()+"");
                    showDialog.setYesOnclickListener("确定", new ItemValueDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            edText.setText(showDialog.getMessage());
                            edText.setSelection(edText.getText().length());
                            showDialog.dismiss();
                        }
                    });
                    showDialog.show();
                    return false;
                }
            });
            //是否可编辑
            if ("1".equals(config.getEditFlag())) {
                edText.setTextColor(getResources().getColor(R.color.nurrecord_edit_normal_color));
            } else {
                edText.setFocusable(false);
                edText.setTextColor(getResources().getColor(R.color.nurrecord_edit_defaultvalue_color));
                edText.setText(config.getPatInfo());
            }
            //根据默认内容优先级填入默认值
            if (StringUtils.isEmpty(config.getPatInfo())) {
                edText.setText(config.getItemdeValue());
                config.setSendValue(config.getItemCode()+"|"+config.getItemdeValue() + "");
            } else {
                edText.setText((patInfoMap.get(config.getPatInfo()) + ""));
                config.setSendValue((config.getItemCode()+"|"+patInfoMap.get(config.getPatInfo()) + ""));
            }
            edText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    config.setSendValue(config.getItemCode()+"|"+edText.getText().toString() + "----");
                }
            });


            layout.addView(edText);
            viewItemMap.put(config.getItemCode(), edText);
            if (!StringUtils.isEmpty(config.getUnit())) {
                TextView tvUnit = new TextView(getContext());
                LinearLayout.LayoutParams unitParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tvUnit.setLayoutParams(unitParams);
                tvUnit.setText(config.getUnit() + "");
                layout.addView(tvUnit);
            }


        } else if ("C".equals(config.getItemType())) {
            //多选
            if ("0".equals(config.getSingleCheck())) {
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params1.setMargins(0, 0, 0, 0);
                layout.setLayoutParams(params1);
                config.setSendValue(config.getItemdeValue() + "");
                FlowLayout flowCheckGroup = new FlowLayout(getContext());
                String[] split = config.getItemValue().split(";");
                String[] devalue = config.getItemdeValue().split(",");

                ArrayList<HashMap> listdef = new ArrayList<HashMap>();
                if (config.getItemValue().length()>0) {
                    for (int i = 0; i < devalue.length; i++) {
                        HashMap<String, String> mapdef = new HashMap<String, String>();
                        mapdef.put("value", devalue[i]);
                        mapdef.put("isSel", "true");
                        listdef.add(mapdef);
                    }

                    config.setSendValue(getckvalue(listdef,config.getItemCode()));
                }
                List listCk = new ArrayList();
                for (int i = 0; i < split.length; i++) {
                    CheckBox cb = new CheckBox(getContext());
                    cb.setText(split[i] + "");
                    Map mapCk = new HashMap();
                    mapCk.put("value", cb.getText());
                    mapCk.put("isSel", "false");
                    for (int j = 0; j < devalue.length; j++) {
                        if (split[i].equals(devalue[j])) {
                            cb.setChecked(true);
                            if (cb.getText().equals(devalue[j])) {
                                mapCk.put("isSel", "true");
                            }
                        }
                    }
                    cb.setTextSize(Float.parseFloat(config.getFontSize()));
                    cb.setHeight(height);

                    listCk.add(mapCk);
                    if ("false".equals(config.getEditFlag())){
                        cb.setEnabled(false);
//                        config.setEditFlag("true");
                    }else {
                        cb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String allSel = "";
                                if (cb.isChecked()) {
                                    mapCk.put("isSel", "true");
                                } else {
                                    mapCk.put("isSel", "false");
                                }
                                config.setSendValue(getckvalue((ArrayList<HashMap>) listCk,config.getItemCode()) + "");
                                showToast(getckvalue((ArrayList<HashMap>) listCk,config.getItemCode()));
                                if (config.getLinkInfo().size()>0) {
//                                    linkView(config.getLinkInfo(), getckvalue((ArrayList<HashMap>) listCk,config.getItemCode()) + "",cb.isChecked(),"isC");
                                }
                            }
                        });
                    }
                    flowCheckGroup.addView(cb);
                }
                layout.addView(flowCheckGroup);
                viewItemMap.put(config.getItemCode(), flowCheckGroup);
                //单选
            } else {
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layout.setLayoutParams(params1);

                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                FlowRadioGroup radioGroup = new FlowRadioGroup(getContext());
                radioGroup.setOrientation(LinearLayout.HORIZONTAL);
                radioGroup.setLayoutParams(params2);

                if (!StringUtils.isEmpty(config.getPatInfo())) {
                    config.setSendValue((patInfoMap.get(config.getPatInfo()) + ""));
                    config.setItemdeValue((patInfoMap.get(config.getPatInfo()) + ""));
                } else {
                    config.setSendValue(config.getItemdeValue() + "");
                }

                config.setSendValue(config.getItemCode()+"|"+rbnum+"!"+config.getItemdeValue() + "");
                String[] split = config.getItemValue().split(";");

                for (int i = 0; i < split.length; i++) {
                    rbnum = i;
                    RadioButton rb = new RadioButton(getContext());
                    rb.setId(i);
                    rb.setTextSize(Float.parseFloat(config.getFontSize()));
                    rb.setText(split[i] + ""); if ("false".equals(config.getEditFlag())){
                        rb.setEnabled(false);
                    }else {
                        int finalI = i;
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showToast(config.getItemCode()+"|"+rb.getText() + "");
                                config.setSendValue(config.getItemCode()+"|"+rb.getId()+"!"+rb.getText() + "");
                                if (config.getLinkInfo().size()>0) {
//                                    linkView(config.getLinkInfo(), rb.getText() + "",rb.isChecked(),"isR");
                                }
                            }
                        });
                    }
                    radioGroup.addView(rb);
                    if (split[i].equals(patInfoMap.get(config.getPatInfo()))){
                        radioGroup.check(rb.getId());
                        config.setSendValue(config.getItemCode()+"|"+rbnum+"!"+config.getItemdeValue() + "");
                    }else if (split[i].equals(config.getItemdeValue())) {
                        radioGroup.check(rb.getId());
                        config.setSendValue(config.getItemCode()+"|"+rbnum+"!"+config.getItemdeValue() + "");
                    }
                }
                layout.addView(radioGroup);

                viewItemMap.put(config.getItemCode(), radioGroup);

            }
        } else if ("R".equals(config.getItemType())) {
            //单选
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(params1);
            FlowRadioGroup radioGroup = new FlowRadioGroup(getContext());
            radioGroup.setOrientation(LinearLayout.HORIZONTAL);
            radioGroup.setLayoutParams(params1);


            if (!StringUtils.isEmpty(config.getPatInfo())) {
                config.setSendValue((patInfoMap.get(config.getPatInfo()) + ""));
                config.setItemdeValue((patInfoMap.get(config.getPatInfo()) + ""));
            } else {
                config.setSendValue(config.getItemdeValue() + "");
            }

            String[] split = config.getItemValue().split("!");
            for (int i = 0; i < split.length; i++) {
                rbnum = i;
                RadioButton rb = new RadioButton(getContext());
                rb.setId(i);
                rb.setHeight(height);
                rb.setTextSize(Float.parseFloat(config.getFontSize()));
                rb.setText(split[i] + "");
                //判断是否可编辑
                if ("false".equals(config.getEditFlag())){
                    rb.setEnabled(false);
                }else {
                    rb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            showToast(rb.getText() + "----");
                            config.setSendValue(config.getItemCode()+"|"+rb.getId()+"!"+rb.getText() + "");
                            if (config.getLinkInfo().size()>0) {
//                                linkView(config.getLinkInfo(), rb.getText() + "",rb.isChecked(),"isR");
                            }
                        }
                    });}
                radioGroup.addView(rb);
                if (split[i].equals(patInfoMap.get(config.getPatInfo()))){
                    radioGroup.check(rb.getId());
                    config.setSendValue(config.getItemCode()+"|"+rbnum+"!"+config.getItemdeValue() + "");
                }else if (split[i].equals(config.getItemdeValue())) {
                    radioGroup.check(rb.getId());
                    config.setSendValue(config.getItemCode()+"|"+rbnum+"!"+config.getItemdeValue() + "");
                }

            }


            layout.addView(radioGroup);
            viewItemMap.put(config.getItemCode(), radioGroup);


        } else if ("T".equals(config.getItemType())){
            //textview额外设置

            //判断是否可点击跳转其他病例填充表格
//            if (config.getLinkInfo().size() > 0) {
//                titleTV.setTextColor(getResources().getColor(R.color.blue));
//                layout.setBackgroundColor(0);
//                titleTV.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        showToast("点击进入新的模板"+config.getLinkInfo().get(0).getLinkModel()+"给:"+config.getLinkInfo().get(0).getLinkItemCode());
//                        Bundle bundle = new Bundle();
//                        bundle.putString("EmrCode",config.getLinkInfo().get(0).getLinkModel());
//                        bundle.putString("episodeId",episodeId );
//                        startFragment(LinkModelFragment.class,bundle,1);
////                        itemNum = config.getLinkInfo().get(0).getLinkItemCode();
//                        itemNum = "Item81";
//
////                        View view = viewItemMap.get("Item81");
////                        if (view instanceof EditText){
////                            EditText ed = (EditText)view;
////                            ed.setText("from__"+config.getItemCode());
////                        }
//
//                    }
//                });
//            }
            viewItemMap.put(config.getItemCode(), titleTV);
            config.setSendValue(config.getItemCode()+"|"+config.getItemValue());

            //判断是否单行显示
            if ("0".equals(config.getTitleHiddeFlag())) {
                TextView tvalue = new TextView(getContext());
                //如果后面有图片，不单独占一行
                if (StringUtils.isEmpty(config.getImageName())) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                    titleTV.setLayoutParams(layoutParams);
                }
            } else {
//                TextView tvalue = new TextView(getContext());
//                tvalue.setText(config.getItemdeValue() + "===");
//                //        titleTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
//                tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
//                tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
//                layout.addView(tvalue);
            }
        } else if ("TN".equals(config.getItemType())){
            //textview额外设置

            //判断是否可点击跳转其他病例填充表格
//            if (config.getLinkInfo().size() > 0) {
//                titleTV.setTextColor(getResources().getColor(R.color.blue));
//                layout.setBackgroundColor(0);
//                titleTV.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        showToast("点击进入新的模板"+config.getLinkInfo().get(0).getLinkModel()+"给:"+config.getLinkInfo().get(0).getLinkItemCode());
//                        View view = viewItemMap.get("Item81");
//                        if (view instanceof EditText){
//                            EditText ed = (EditText)view;
//                            ed.setText("from__"+config.getItemCode());
//                        }
//
//                    }
//                });
//            }
            viewItemMap.put(config.getItemCode(), titleTV);

            //判断是否单行显示
            if ("0".equals(config.getTitleHiddeFlag())) {
//                TextView tvalue = new TextView(getContext());
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(height, ViewGroup.LayoutParams.MATCH_PARENT);
//                titleTV.setLayoutParams(layoutParams);
            } else {
//                TextView tvalue = new TextView(getContext());
//                tvalue.setText(config.getItemdeValue() + "===");
//                //        titleTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
//                tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
//                tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
//                layout.addView(tvalue);
            }
        }  else if ("D".equals(config.getItemType())) {
            //日期选择
            TextView tvalue = new TextView(getContext());
            tvalue.setText(config.getItemdeValue() + "2018-12-11");
            if (StringUtils.isEmpty(config.getPatInfo())) {
                tvalue.setText(config.getItemdeValue());
                config.setSendValue(config.getItemCode()+"|"+config.getItemdeValue() + "");
            } else {
                tvalue.setText((patInfoMap.get(config.getPatInfo()) + ""));
                config.setSendValue(config.getItemCode()+"|"+(patInfoMap.get(config.getPatInfo()) + ""));
            }
            tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
            tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
            config.setSendValue(config.getItemCode()+"|"+tvalue.getText() + "");
            //判断是否可编辑
            if ("false".equals(config.getEditFlag())){
                tvalue.setEnabled(false);
            }else {
                tvalue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textViewChooseDateTime = tvalue;
                        dt = "date";
                        chooseDate();
                    }
                });
            }

            tvalue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    config.setSendValue(config.getItemCode()+"|"+tvalue.getText().toString() + "----");
                }
            });


            layout.addView(tvalue);
            viewItemMap.put(config.getItemCode(), tvalue);
        } else if ("Ti".equals(config.getItemType())) {
            //时间选择
            TextView tvalue = new TextView(getContext());
            tvalue.setText(config.getItemdeValue() + "11:11");
            if (StringUtils.isEmpty(config.getPatInfo())) {
                tvalue.setText(config.getItemdeValue());
                config.setSendValue(config.getItemCode()+"|"+config.getItemdeValue() + "");
            } else if (!StringUtils.isEmpty(patInfoMap.get(config.getPatInfo())+"")){
                tvalue.setText((patInfoMap.get(config.getPatInfo()) + ""));
                config.setSendValue(config.getItemCode()+"|"+(patInfoMap.get(config.getPatInfo()) + ""));
            }
            tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
            tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
            config.setSendValue(config.getItemCode()+"|"+tvalue.getText() + "");
            //判断是否可编辑
            if ("false".equals(config.getEditFlag())){
                tvalue.setEnabled(false);
            }else {
                tvalue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textViewChooseDateTime = tvalue;
                        dt = "time";
                        chooseTime();
                    }
                });
            }

            tvalue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    config.setSendValue(config.getItemCode()+"|"+tvalue.getText().toString() + "----");
                }
            });
            layout.addView(tvalue);
            viewItemMap.put(config.getItemCode(), tvalue);
        } else {
            showToast("出现未知类型控件，请联系后台进行数据修复或更新应用");
//            //选择框
//            List ll = new ArrayList();
//            ll.add("1");
//            ll.add("2");
//            final OptionView optionView = new OptionView(getActivity(), ll);
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 50);
//            optionView.setLayoutParams(layoutParams);
//            optionView.setTextSize(16);
//            optionView.setBackgroundResource(R.drawable.vital_sign_input_bg);
//            optionView.setGravity(Gravity.CENTER);
//
//            optionView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    optionView.showPicker();
//                }
//            });
//
//            layout.addView(optionView);
//            viewItemMap.put(config.getItemCode(), optionView);
        }

        //判断是否与图片，有的话加载
        if (!StringUtils.isEmpty(config.getImageName())) {
            LinearLayout.LayoutParams paramsimg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(paramsimg);
//            downImage(imageView,config.getImageName());
            downImage(imageView,"http://10.1.5.87/dhcmg/2229.gif");
            layout.addView(imageView);
        }
        return layout;
    }


    private void linkView(List<NurRecordBean.ModelListBean.LinkInfoBean> LinkInfo, String selRadio, Boolean isSel, String isRorC){

        if (isRorC.equals("isC")) {

            //多选评分
            if (LinkInfo.size()>0){
                int num = 0;
                String[] split = selRadio.split(",");
                for (int i = 0;i<LinkInfo.size();i++){
                    if (selRadio.contains(LinkInfo.get(i).getLinkRangeCon())){
                        num = num +Integer.parseInt( LinkInfo.get(i).getReValue());
//                        showToast(num+"---");
                    }
                }
                View view = viewItemMap.get(LinkInfo.get(0).getLinkItemCode());
                if (view instanceof EditText) {
                    EditText ed = (EditText) view;
                    ed.setText(num+"");
                }
                return;
            }


            //单选判断是否可变为可编辑
            String rangcon = LinkInfo.get(0).getLinkRangeCon();
            if ((selRadio).contains("其它")) {
                View view = viewItemMap.get(LinkInfo.get(0).getLinkItemCode());


                if (view instanceof EditText) {
                    EditText ed = (EditText) view;
                    if (isSel) {
                        ed.setText("此时可编辑" + LinkInfo.size());
                        ed.setFocusable(true);
                    } else {
                        ed.setText("此时不可编辑" + LinkInfo.size());
                        ed.setFocusable(false);
                    }
                }
            }
//单选判断点击的值是否关联值，再判断是否点击，都符合的话关联成功
        }else if (isRorC.equals("isR")){
            String rangcon = LinkInfo.get(0).getLinkRangeCon();
            View view = viewItemMap.get(LinkInfo.get(0).getLinkItemCode());
            if (view instanceof EditText) {
                EditText ed = (EditText) view;
                if ("其它".equals(selRadio) && isSel) {
                    ed.setText("此时可编辑" + LinkInfo.size());
                    ed.setFocusable(true);
                    ed.setFocusableInTouchMode(true);
                } else {
                    ed.setText("此时不可编辑" + LinkInfo.size());
                    ed.setFocusable(false);
                    ed.setFocusableInTouchMode(false);
                }
            }
            //赋值判断关联项如何编辑
        }else if (isRorC.equals("isE")){

        }
    }

    private void changeView(View view){
        if (view instanceof EditText) {
            EditText ed = (EditText) view;
        }else if (view instanceof RadioGroup){

        }else if (view instanceof TextView){

            //判断后面多选框是否可编辑，更改可编辑状态
        }else if (view instanceof FlowLayout){
            int cknum = ((FlowLayout) view).getChildCount();
            for (int i = 0;i<cknum;i++){
                if ((((FlowLayout) view).getChildAt(i)) instanceof CheckBox){
                    CheckBox checkBox = (CheckBox) ((FlowLayout) view).getChildAt(i);
                    Log.v("11111ck",checkBox.getText().toString());
                }
            }

        }
    }
    //加载图片
    private void downImage(ImageView view,String strUrl) {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request
                .Builder()
                .get()
                .url(strUrl)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showToast( "下载图片失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                //将图片显示到ImageView中
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.setImageBitmap(bitmap);
                        }
                    });
                }
            }
        });
    }

    private String getckvalue(ArrayList<HashMap> list,String itemNum) {
        String strck = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).get("isSel").equals("true")) {
                if ("".equals(strck)){
                    strck = itemNum+"_"+i+"|"+list.get(i).get("value") + "";
                }else {
                    strck = strck + "^" + itemNum+"_"+i+"|"+list.get(i).get("value") + "";
                }
            }
        }
        return strck;
    }

    /**
     * 选择日期---年月日
     */
    private void chooseDate() {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("日期")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setCurrentMillseconds(calendar.getTimeInMillis())
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(getFragmentManager(), "ALL");

    }

    /**
     * 选择时间---时分
     */
    private void chooseTime() {
        Calendar calendar = Calendar.getInstance();

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(true)
                .setCurrentMillseconds(calendar.getTimeInMillis())
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.HOURS_MINS)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(getFragmentManager(), "ALL");
    }



    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

    }


}
