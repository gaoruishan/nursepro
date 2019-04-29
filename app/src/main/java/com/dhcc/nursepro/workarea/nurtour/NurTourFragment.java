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
import com.dhcc.nursepro.uiplugs.OptionView;
import com.dhcc.nursepro.utils.DateUtils;
import com.dhcc.nursepro.workarea.nurrecord.FlowRadioGroup;
import com.dhcc.nursepro.workarea.nurrecord.ItemValueDialog;
import com.dhcc.nursepro.workarea.nurrecord.bean.NurRecordBean;
import com.dhcc.nursepro.workarea.nurtour.adapter.DosingTourListAdapter;
import com.dhcc.nursepro.workarea.nurtour.adapter.DosingTourTypeAdapter;
import com.dhcc.nursepro.workarea.nurtour.adapter.NurPatTypeAdapter;
import com.dhcc.nursepro.workarea.nurtour.adapter.NurTourListAdapter;
import com.dhcc.nursepro.workarea.nurtour.adapter.NurTourTypeAdapter;
import com.dhcc.nursepro.workarea.nurtour.adapter.TourAllAdapter;
import com.dhcc.nursepro.workarea.nurtour.adapter.TourPatTypeAdapter;
import com.dhcc.nursepro.workarea.nurtour.adapter.TourPatslistAdapter;
import com.dhcc.nursepro.workarea.nurtour.api.TourApiManager;
import com.dhcc.nursepro.workarea.nurtour.bean.AllTourListBean;
import com.dhcc.nursepro.workarea.nurtour.bean.DosingListBean;
import com.dhcc.nursepro.workarea.nurtour.bean.GradeModelBean;
import com.dhcc.nursepro.workarea.nurtour.bean.GradeTourListBean;
import com.dhcc.nursepro.workarea.nurtour.bean.ModelDataBean;
import com.dhcc.nursepro.workarea.nurtour.bean.TourSaveBean;
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

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.util.CompatUtils;
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
public class NurTourFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout llType;
    private LinearLayout llTourlist;
    private RelativeLayout llTourSend;

    private RecyclerView recPatlist;
    private RecyclerView recPatType;
    private RecyclerView recAll;
    private RecyclerView recTimeout;

    private LinearLayout llMainAll;
    private LinearLayout llTopTip;

    private LinearLayout llMainNur;
    private LinearLayout llMainSave;

    private TextView tvCancle,tvSave;

    private TextView tvAll,tvNur,tvDosing,tvBlood;
    private View show1, show2, show3, show4;
    private TextView tvSendPatinfo,tvSendTourLevel,tvSendTourType;
    private LinearLayout llSendDosingList;

    private TourAllAdapter tourAllAdapter;
    private TourPatslistAdapter patsAdapter;
    private TourPatTypeAdapter tourPatTypeAdapter;
    private NurTourTypeAdapter nurTourTypeAdapter;
    private NurPatTypeAdapter nurPatTypeAdapter;
    private NurTourListAdapter nurTourListAdapter;
    private DosingTourTypeAdapter dosingTourTypeAdapter;
    private DosingTourListAdapter dosingTourListAdapter;


    private SPUtils spUtils = SPUtils.getInstance();

    private List<AllTourListBean.PatInfoListBean> patsListBeanList =new ArrayList<>();
    private List<AllTourListBean.PatInfoListBean> patsListBeanFilter =new ArrayList<>();
    private List<AllTourListBean.TopFilterBean> topFilterBeans =new ArrayList<>();

    private List<AllTourListBean.TourDataListBeanX> tourDataListBeans =new ArrayList<>();
    private List<GradeTourListBean.LeftFilterBean> gradeTourTypeList = new ArrayList<>();
    private List<GradeTourListBean.TopFilterBean> gradePatTypeList = new ArrayList<>();
    private List<GradeTourListBean.PatInfoListBean> gradePatTourList = new ArrayList<>();
    private List<GradeTourListBean.PatInfoListBean> gradePatTourListFilter = new ArrayList<>();

    private List<DosingListBean.PatInfoListBean> dosingPatsListBeanList =new ArrayList<>();
    private List<DosingListBean.PatInfoListBean> dosingPastBeanFilter =new ArrayList<>();
    private List<DosingListBean.TopFilterBean> dosingTopFilterBeans =new ArrayList<>();

    private String topTypeSelected = "nur";
    private String tempTopTypeSelected = "";
    private String gradeTourType = "一级";
    private String gradePatType = "inBedAll";
    private String regNo = "";
    private String episodeId = "";
    private String modelType="grade";
    private String sendpisodeId = "";


    private List<ModelDataBean.ModelListBean> modelListBeans =new ArrayList<>();
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
        initDataInfusion();

    }

    private void initView(View view){

        viewItemMap = new HashMap<>();

        llType = view.findViewById(R.id.ll_alltype);
        llTourlist = view.findViewById(R.id.ll_tour_list);
        llTourSend = view.findViewById(R.id.ll_tour_send);

        recPatlist = view.findViewById(R.id.recy_tour_patlist);
        recPatType = view.findViewById(R.id.recy_tour_pattype);
        recAll = view.findViewById(R.id.recy_tour_listall);

        llMainAll = view.findViewById(R.id.ll_tour_main_all);
        llTopTip = view.findViewById(R.id.ll_alltour_toptip);
        llMainNur = view.findViewById(R.id.ll_tourmain_nur);
        llMainSave = view.findViewById(R.id.ll_tourmain_save);
        tvSendPatinfo = view.findViewById(R.id.tv_nurtour_patinfo);
        tvSendTourLevel = view.findViewById(R.id.tv_nurtour_level);
        tvSendTourType = view.findViewById(R.id.tv_tourall_type);
        llSendDosingList = view.findViewById(R.id.ll_tour_sendorderlist);

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

        tvCancle = view.findViewById(R.id.tv_tour_cancle);
        tvCancle.setOnClickListener(this);
        tvSave = view.findViewById(R.id.tv_tour_save);
        tvSave.setOnClickListener(this);

        recordContentView = view.findViewById(R.id.fl_modeldetail);

        llTourlist.setVisibility(View.VISIBLE);
        llTourSend.setVisibility(View.GONE);
        setTopFilterSelect();

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

    }

    private void initAdapter() {

        tourAllAdapter = new TourAllAdapter(new ArrayList<AllTourListBean.TourDataListBeanX>(),getActivity());
        patsAdapter = new TourPatslistAdapter(new ArrayList<AllTourListBean.PatInfoListBean>());
        tourPatTypeAdapter = new TourPatTypeAdapter(new ArrayList<AllTourListBean.TopFilterBean>());
        nurTourTypeAdapter = new NurTourTypeAdapter(new ArrayList<GradeTourListBean.LeftFilterBean>());
        nurPatTypeAdapter = new NurPatTypeAdapter(new ArrayList<GradeTourListBean.TopFilterBean>());
        nurTourListAdapter= new NurTourListAdapter(new ArrayList<GradeTourListBean.PatInfoListBean>(), getActivity());
        dosingTourTypeAdapter = new DosingTourTypeAdapter(new ArrayList<DosingListBean.TopFilterBean>());
        dosingTourListAdapter = new DosingTourListAdapter(new ArrayList<DosingListBean.PatInfoListBean>(),getActivity());

        recAll.setAdapter(nurTourListAdapter);


        //全部巡视病人分类列表
        recPatType.setAdapter(nurPatTypeAdapter);
        nurPatTypeAdapter.setSelectItem(0);
        nurPatTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                nurPatTypeAdapter.setSelectItem(position);
                nurPatTypeAdapter.notifyDataSetChanged();
                gradePatType = topFilterBeans.get(position).getCode();
                gradeTourFilter();
            }
        });
        //分级巡视左侧分类列表
        recPatlist.setAdapter(nurTourTypeAdapter);
        nurTourTypeAdapter.setSelectItem(0);
        nurTourTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                nurTourTypeAdapter.setSelectItem(position);
                nurTourTypeAdapter.notifyDataSetChanged();
                gradeTourType = gradeTourTypeList.get(position).getDesc();
                gradeTourFilter();
                showToast(gradeTourTypeList.get(position).getDesc());
            }
        });

        ///输液巡视左侧分类列表
        dosingTourTypeAdapter.setSelectItem(0);
        dosingTourTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dosingTourTypeAdapter.setSelectItem(position);
                dosingTourTypeAdapter.notifyDataSetChanged();
                dosingPatFilter(dosingTopFilterBeans.get(position).getCode());
//                showToast(gradeTourTypeList.get(position).getDesc());
            }
        });

        //全部巡视左侧病人列表
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
        //全部巡视病人分类列表
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

    /**
     * 获取分级巡视详情
     */
    private void initDataGrade(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
            HashMap<String,String> map = new HashMap<>();
            map.put("locId",spUtils.getString(SharedPreference.LOCID));
            map.put("userId",spUtils.getString(SharedPreference.USERID));
            TourApiManager.getGradeTourList(map, "getGradeTourList", new TourApiManager.getGradeTourListcall() {
                @Override
                public void onSuccess(GradeTourListBean gradeTourListBean) {
                    gradeTourTypeList = gradeTourListBean.getLeftFilter();
                    nurTourTypeAdapter.setNewData(gradeTourTypeList);
                    nurTourTypeAdapter.notifyDataSetChanged();

                    gradePatTypeList =gradeTourListBean.getTopFilter();
                    nurPatTypeAdapter.setNewData(gradePatTypeList);
                    nurPatTypeAdapter.notifyDataSetChanged();

                    gradePatTourList = gradeTourListBean.getPatInfoList();
                    gradePatTourListFilter = gradePatTourList;

                    if (gradeTourTypeList.size()>0) {
                        gradeTourType = gradeTourTypeList.get(0).getDesc();
                        gradeTourFilter();
                    }
                    gradePatType = gradeTourListBean.getTopFilter().get(0).getCode();

                    hideLoadingTip();
                }
                @Override
                public void onFail(String code, String msg) {
                    showToast("error" + code + ":" + msg);
                    hideLoadingTip();
                }
            });
    }

    /**
     * 获取全部巡视详情;默认获取第一个人的记录，输入episodeId获取对应患者记录
     */
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
    /**
     * 获取输液巡视详情；
     */
    private void initDataInfusion(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap<String,String> map = new HashMap<>();
        map.put("locId",spUtils.getString(SharedPreference.LOCID));
        map.put("userId",spUtils.getString(SharedPreference.USERID));
        TourApiManager.getInfusionList(map, "getInfusionTourList", new TourApiManager.getInfusionlcall() {
            @Override
            public void onSuccess(DosingListBean infusionListBean) {
                dosingPatsListBeanList = infusionListBean.getPatInfoList();
                dosingPastBeanFilter = dosingPatsListBeanList;
                dosingTourListAdapter.setNewData(dosingPastBeanFilter);
                dosingTourListAdapter.notifyDataSetChanged();

                dosingTopFilterBeans = infusionListBean.getTopFilter();
                dosingTourTypeAdapter.setNewData(dosingTopFilterBeans);
                dosingTourTypeAdapter.notifyDataSetChanged();

                hideLoadingTip();

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
            }
        });



    }

    /**
     * 获取输血巡视详情
     */
    private void initDataInBlood(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap<String,String> map = new HashMap<>();
        map.put("locId",spUtils.getString(SharedPreference.LOCID));
        map.put("userId",spUtils.getString(SharedPreference.USERID));


    }
    /**
     * 获取巡视模板，modelType为返回的模板类型，根据类型加载对应控件
     */
    private void initDataModel() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap<String, String> map = new HashMap<>();
        map.put("locId", "197");
        map.put("episodeId", "94");
        map.put("barCode", "94");
        TourApiManager.getModelData(map, "getModelData", new TourApiManager.getModelDatacall() {
            @Override
            public void onSuccess(ModelDataBean modelDataBean) {

                llTourlist.setVisibility(View.GONE);
                llTourSend.setVisibility(View.VISIBLE);
                seToptEnable(false);
                llSendDosingList.setVisibility(View.GONE);
                modelListBeans = modelDataBean.getModelList();
                sendpisodeId = modelDataBean.getPatInfo().getEpisodeID();
                modelType = modelDataBean.getModelType();
                switch (modelDataBean.getModelType()){
                    case "Grade":
                        topTypeSelected = "nur";
                        tvSendTourType.setText("护理巡视");
                        break;
                    case "Infusion":
                        llSendDosingList.setVisibility(View.VISIBLE);
                        topTypeSelected = "dosing";
                        tvSendTourType.setText("输液巡视");
                        break;
                    case "Blood":
                        topTypeSelected = "blood";
                        tvSendTourType.setText("输血巡视");
                        break;
                    default:
                        break;
                }
                tvSendPatinfo.setText(modelDataBean.getPatInfo().getBedCode()+"  "+modelDataBean.getPatInfo().getName());
                setTopFilterSelect();

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

    /**
     * 保存巡视状况，保存完回到对应界面刷新
     */
    private void initTourSave(String send){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap<String,String> map = new HashMap<>();
        map.put("parr",send);
        map.put("id",spUtils.getString(SharedPreference.USERID));
        map.put("tourType",spUtils.getString(SharedPreference.LOCID));
        map.put("userId",spUtils.getString(SharedPreference.USERID));
        TourApiManager.getTourSaveMsg(map, "saveTour", new TourApiManager.getTourSavecall() {
            @Override
            public void onSuccess(TourSaveBean tourSaveBean) {
                seToptEnable(true);
                setTopFilterSelect();
                hideLoadFailTip();

                llTourSend.setVisibility(View.GONE);
                llTourlist.setVisibility(View.VISIBLE);
                switch (topTypeSelected){
                    case "all":
                        onClick(tvAll);
                        break;
                    case "nur":
                        onClick(tvNur);
                        break;
                    case "dosing":
                        onClick(tvDosing);
                        break;
                    case "blood":
                        onClick(tvBlood);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFail(String code, String msg) {
                    showToast(code+"--"+msg);
                    hideLoadFailTip();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tour_all:
                episodeId = "";
                patsAdapter.setSelectItem(0);
                patsAdapter.notifyDataSetChanged();
                tourPatTypeAdapter.setSelectItem(0);
                tourPatTypeAdapter.notifyDataSetChanged();

                initDataAll();
                topTypeSelected = "all";
                setTopFilterSelect();
                break;
            case R.id.tv_tour_nur:
                nurPatTypeAdapter.setSelectItem(0);
                nurPatTypeAdapter.notifyDataSetChanged();
                nurTourTypeAdapter.setSelectItem(0);
                nurTourTypeAdapter.notifyDataSetChanged();

                initDataGrade();
                topTypeSelected = "nur";
                setTopFilterSelect();
                break;
            case R.id.tv_tour_dosing:
                dosingTourTypeAdapter.setSelectItem(0);
                dosingTourTypeAdapter.notifyDataSetChanged();

                initDataInfusion();
                topTypeSelected = "dosing";
                setTopFilterSelect();
                break;
            case R.id.tv_tour_blood:
                topTypeSelected = "blood";
                setTopFilterSelect();
                break;
            case R.id.tv_tour_cancle:
                topTypeSelected = tempTopTypeSelected;
                seToptEnable(true);
                setTopFilterSelect();

                llTourSend.setVisibility(View.GONE);
                llTourlist.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_tour_save:

                String strSendHead = "DHCNurTourAdmDR|"+sendpisodeId+"^DHCNurTourCtlocDR|"+spUtils.getString(SharedPreference.LOCID)+
                        "^DHCNurTourPatBed|^DHCNurTourCareDR|^DHCNurTourCareDesc|^DHCNurTourUser|"+spUtils.getString(SharedPreference.USERCODE)+
                        "^DHCNurTourType|"+modelType;
                String  strSend = "";
                for (int i = 0; i < modelListBeans.size(); i++) {
                    if (modelListBeans.get(i).getItemCode().startsWith("DHC")) {
                        strSend = strSend + "^" + modelListBeans.get(i).getSendValue();
                    }
                    if ("1".equals(modelListBeans.get(i).getMustFill())) {
                        if (StringUtils.isEmpty(modelListBeans.get(i).getSendValue())) {
                            showToast(modelListBeans.get(i).getItemDesc() + "--未填写");
                            return;
                        }
                    }
                    if (i == modelListBeans.size() - 1) {

                        showToast(strSendHead+strSend);
                        Log.v("111send", strSend);
                    }
                }
                llType.setVisibility(View.VISIBLE);
                initTourSave(strSendHead+strSend);
                break;
            default:
                break;
        }
    }

    /**
     * 顶部筛选，点击分类展示对应界面
     */
    private void setTopFilterSelect() {
        tvAll.setSelected(false);
        tvNur.setSelected(false);
        tvDosing.setSelected(false);
        tvBlood.setSelected(false);

        llTopTip.setVisibility(View.GONE);

        show1.setBackgroundColor(getResources().getColor(R.color.blue_light));
        show2.setBackgroundColor(getResources().getColor(R.color.blue_light));
        show3.setBackgroundColor(getResources().getColor(R.color.blue_light));
        show4.setBackgroundColor(getResources().getColor(R.color.blue_light));

        recPatType.setVisibility(View.VISIBLE);

        switch (topTypeSelected){
            case "all":
                recPatlist.setAdapter(patsAdapter);
                recPatType.setAdapter(tourPatTypeAdapter);
                tvAll.setSelected(true);
                show1.setBackgroundColor(getResources().getColor(R.color.blue));
                llTopTip.setVisibility(View.VISIBLE);
                recAll.setAdapter(tourAllAdapter);
                break;
            case "nur":
                recPatType.setAdapter(nurPatTypeAdapter);
                tvNur.setSelected(true);
                show2.setBackgroundColor(getResources().getColor(R.color.blue));
                recAll.setAdapter(nurTourListAdapter);
                recPatlist.setAdapter(nurTourTypeAdapter);
                break;
            case "dosing":
                tvDosing.setSelected(true);
                show3.setBackgroundColor(getResources().getColor(R.color.blue));
                recAll.setAdapter(dosingTourListAdapter);
                recPatlist.setAdapter(dosingTourTypeAdapter);
                recPatType.setVisibility(View.GONE);
                break;
            case "blood":
                tvBlood.setSelected(true);
                show4.setBackgroundColor(getResources().getColor(R.color.blue));
                recPatType.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    /**
     * 进入巡视录入界面，顶部按钮变为不可点击
     */
    private void seToptEnable(Boolean b){
        tvAll.setEnabled(b);
        tvNur.setEnabled(b);
        tvDosing.setEnabled(b);
        tvBlood.setEnabled(b);
    }

    /**
     * 筛选患者
     */
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

    /**
     * 顶部分级巡视列表，根据左侧级别分类筛选
     */
    private void gradeTourFilter(){
        gradePatTourListFilter = new ArrayList<>();
        if (gradeTourType.equals("全部")){
            gradePatTourListFilter = gradePatTourList;
        }else if (gradeTourType.equals("需巡")){
            for (int i = 0; i < gradePatTourList.size(); i++) {
                if (gradePatTourList.get(i).getNeedFlag().equals("1")) {
                    gradePatTourListFilter.add(gradePatTourList.get(i));
                }
            }
        }else {
            for (int i = 0; i < gradePatTourList.size(); i++) {
                if (gradePatTourList.get(i).getCareLevel().equals(gradeTourType)) {
                    gradePatTourListFilter.add(gradePatTourList.get(i));
                }
            }
        }

        List<GradeTourListBean.PatInfoListBean> gradePatTourListFilterShow = new ArrayList<>();
        for (int i = 0; i < gradePatTourListFilter.size(); i++) {
            switch (gradePatType) {
                case "inBedAll":
                    if (gradePatTourListFilter.get(i).getInBedAll().equals("1")){
                        gradePatTourListFilterShow.add(gradePatTourListFilter.get(i));
                    }
                    break;
                case "manageInBed":
                    if (gradePatTourListFilter.get(i).getManageInBed().equals("1")){
                        gradePatTourListFilterShow.add(gradePatTourListFilter.get(i));
                    }
                    break;
                case "todayOut":
                    if (gradePatTourListFilter.get(i).getTodayOut().equals("1")){
                        gradePatTourListFilterShow.add(gradePatTourListFilter.get(i));
                    }
                    break;
                case "allOut":
                    if (gradePatTourListFilter.get(i).getAllOut().equals("1")){
                        gradePatTourListFilterShow.add(gradePatTourListFilter.get(i));
                    }
                    break;
                case "wait":
                    if (gradePatTourListFilter.get(i).getWait().equals("1")){
                        gradePatTourListFilterShow.add(gradePatTourListFilter.get(i));
                    }
                    break;
                default:
                    break;
            }

        }

        nurTourListAdapter.setNewData(gradePatTourListFilterShow);
        nurTourListAdapter.notifyDataSetChanged();
    }

    /**
     * 输液巡视列表筛选，根据患者类型筛选
     */
    private void dosingPatFilter(String pattype){
        dosingPastBeanFilter = new ArrayList<>();
        for (int i = 0;i<dosingPatsListBeanList.size();i++){

            switch (pattype) {
                case "inBedAll":
                    if (dosingPatsListBeanList.get(i).getInBedAll().equals("1")){
                        dosingPastBeanFilter.add(dosingPatsListBeanList.get(i));
                    }
                    break;
                case "manageInBed":
                    if (dosingPatsListBeanList.get(i).getManageInBed().equals("1")){
                        dosingPastBeanFilter.add(dosingPatsListBeanList.get(i));
                    }
                    break;
                case "todayOut":
                    if (dosingPatsListBeanList.get(i).getTodayOut().equals("1")){
                        dosingPastBeanFilter.add(dosingPatsListBeanList.get(i));
                    }
                    break;
                case "allOut":
                    if (dosingPatsListBeanList.get(i).getAllOut().equals("1")){
                        dosingPastBeanFilter.add(dosingPatsListBeanList.get(i));
                    }
                    break;
                case "wait":
                    if (dosingPatsListBeanList.get(i).getWait().equals("1")){
                        dosingPastBeanFilter.add(dosingPatsListBeanList.get(i));
                    }
                    break;
                default:
                    break;
            }

        }
        dosingTourListAdapter.setNewData(dosingPastBeanFilter);
        dosingTourListAdapter.notifyDataSetChanged();
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Objects.requireNonNull(intent.getAction()).equals(Action.DEVICE_SCAN_CODE)) {
            tempTopTypeSelected =topTypeSelected;
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            showToast(bundle.getString("data"));

            initDataModel();
        }
        if (Objects.requireNonNull(intent.getAction()).equals(Action.TOUR_DOSINGID)) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            showToast(bundle.getString("data"));
        }

    }


    /**
     * 画控件.
     */
    public void drawInputItems() {
        recordContentView.removeAllViews();
        for (int i = 0; i < modelListBeans.size(); i++) {
            ModelDataBean.ModelListBean config = modelListBeans.get(i);
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
    private LinearLayout drawItem(ModelDataBean.ModelListBean config) {

        config.setSendValue(config.getItemCode()+"|");

        int height = ConvertUtils.dp2px(Float.parseFloat(config.getHeight()));
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
        } else if ("R1".equals(config.getItemType())) {
            //单选
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(params1);
            FlowRadioGroup radioGroup = new FlowRadioGroup(getContext());
            radioGroup.setOrientation(LinearLayout.HORIZONTAL);
            radioGroup.setLayoutParams(params1);


            if (!StringUtils.isEmpty(config.getPatInfo())) {
                config.setSendValue(config.getItemCode()+(patInfoMap.get(config.getPatInfo()) + ""));
                config.setItemdeValue((patInfoMap.get(config.getPatInfo()) + ""));
            } else {
                config.setSendValue(config.getItemCode()+config.getItemdeValue() + "");
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
                            config.setSendValue(config.getItemCode()+"|"+rb.getText() + "");
                            if (config.getLinkInfo().size()>0) {
//                                linkView(config.getLinkInfo(), rb.getText() + "",rb.isChecked(),"isR");
                            }
                        }
                    });}
                radioGroup.addView(rb);
                if (split[i].equals(patInfoMap.get(config.getPatInfo()))){
                    radioGroup.check(rb.getId());
                    config.setSendValue(config.getItemCode()+"|"+config.getItemdeValue() + "");
                }else if (split[i].equals(config.getItemdeValue())) {
                    radioGroup.check(rb.getId());
                    config.setSendValue(config.getItemCode()+"|"+config.getItemdeValue() + "");
                }

            }


            layout.addView(radioGroup);
            viewItemMap.put(config.getItemCode(), radioGroup);


        } else if ("T".equals(config.getItemType())){
            //textview额外设置
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
//            if (StringUtils.isEmpty(config.getPatInfo())) {
//                tvalue.setText(config.getItemdeValue());
//                config.setSendValue(config.getItemCode()+"|"+config.getItemdeValue() + "");
//            } else {
//                tvalue.setText((patInfoMap.get(config.getPatInfo()) + ""));
//                config.setSendValue(config.getItemCode()+"|"+(patInfoMap.get(config.getPatInfo()) + ""));
//            }
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
                        DateUtils.chooseDate(getActivity(), getFragmentManager(), new OnDateSetListener() {
                            @Override
                            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                                tvalue.setText(DateUtils.getDateByMillisecond(millseconds));
                            }
                        });
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
            tvalue.setText(config.getItemdeValue() + "11:11：11");
//            if (StringUtils.isEmpty(config.getPatInfo())) {
//                tvalue.setText(config.getItemdeValue());
//                config.setSendValue(config.getItemCode()+"|"+config.getItemdeValue() + "");
//            } else if (!StringUtils.isEmpty(patInfoMap.get(config.getPatInfo())+"")){
//                tvalue.setText((patInfoMap.get(config.getPatInfo()) + ""));
//                config.setSendValue(config.getItemCode()+"|"+(patInfoMap.get(config.getPatInfo()) + ""));
//            }
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

                        DateUtils.chooseTime(getActivity(), getFragmentManager(), new OnDateSetListener() {
                            @Override
                            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                                tvalue.setText(DateUtils.getTimeByMillisecond(millseconds));
                            }
                        });
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
        } else if ("R".equals(config.getItemType())){
//            showToast("出现未知类型控件，请联系后台进行数据修复或更新应用");
            //选择框
            List ll = new ArrayList();
            String[] split = config.getItemValue().split("!");
            for (String str:split){
                ll.add(str);
            }

            final OptionView optionView = new OptionView(getActivity(), ll);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 50);
            optionView.setLayoutParams(layoutParams);
            optionView.setTextSize(16);
            optionView.setBackgroundResource(R.drawable.vital_sign_input_bg);
            optionView.setGravity(Gravity.CENTER);

            optionView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    optionView.picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                        @Override
                        public void onOptionPicked(int index, String item) {
                            config.setSendValue(config.getItemCode()+"|"+item);
                            optionView.setText(item);
                        }
                    });
                    optionView.showPicker();
                }
            });


            config.setSendValue(config.getItemCode()+"|"+optionView.getText().toString());
            layout.addView(optionView);
            viewItemMap.put(config.getItemCode(), optionView);
        }

        //判断是否有图片，有的话加载
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

    public void downImage(ImageView view,String strUrl) {
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
        StringBuilder stringBuilder = new StringBuilder();
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
}