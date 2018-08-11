package com.dhcc.nursepro.workarea.bedmap;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.adapter.BedMapPatientAdapter;
import com.dhcc.nursepro.workarea.bedmap.adapter.BedMapPatientTypeAdapter;
import com.dhcc.nursepro.workarea.bedmap.api.BedMapApiManager;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;

import java.util.ArrayList;
import java.util.List;

/**
 * BedMapFragment
 * 床位图
 *
 * @author DevLix126
 * @date 2018/8/11
 */
public class BedMapFragment extends BaseFragment implements View.OnClickListener {
    private EditText etBedmapBedno;
    private TextView tvBedmapAllarea;
    private TextView tvBedmapAdminarea;
    private TextView tvBedmapNowoutarea;
    private TextView tvBedmapAlloutarea;
    private TextView tvBedmapWaitarea;
    private RecyclerView recyBedmapPatienttype;
    private RecyclerView recyBedmapPatient;

    private BedMapPatientTypeAdapter bedMapPatientTypeAdapter;
    private BedMapPatientAdapter bedMapPatientAdapter;

    private List<BedMapBean.LeftFilterBean> leftFilterBeanList;
    private List<BedMapBean.PatInfoListBean> patInfoListBeanList;
    private List<BedMapBean.TopFilterBean> topFilterBeanList;

    private String topFilterStr = "inBedAll";
    private String leftFilterStr = "allPat";


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bed_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.title_bedmap));


        initView(view);
        initAdapter();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                asyncInitData();
            }
        },300);



    }


    private void initView(View view) {


        //控制登录用户名图标大小
        etBedmapBedno = view.findViewById(R.id.et_bedmap_bedno);
        //        Drawable drawable1 = getResources().getDrawable(R.drawable.search);
        //        drawable1.setBounds(0, 0, 4, 4);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        //        etBedmapBedno.setCompoundDrawables(drawable1, null, null, null);//只放左边

        tvBedmapAllarea = view.findViewById(R.id.tv_bedmap_allarea);
        tvBedmapAllarea.setOnClickListener(this);
        tvBedmapAdminarea = view.findViewById(R.id.tv_bedmap_adminarea);
        tvBedmapAdminarea.setOnClickListener(this);
        tvBedmapNowoutarea = view.findViewById(R.id.tv_bedmap_nowoutarea);
        tvBedmapNowoutarea.setOnClickListener(this);
        tvBedmapAlloutarea = view.findViewById(R.id.tv_bedmap_alloutarea);
        tvBedmapAlloutarea.setOnClickListener(this);
        tvBedmapWaitarea = view.findViewById(R.id.tv_bedmap_waitarea);
        tvBedmapWaitarea.setOnClickListener(this);

        recyBedmapPatienttype = view.findViewById(R.id.recy_bedmap_patienttype);
        recyBedmapPatient = view.findViewById(R.id.recy_bedmap_patient);

        //提高展示效率
        recyBedmapPatienttype.setHasFixedSize(true);
        //设置的布局管理
        recyBedmapPatienttype.setLayoutManager(new LinearLayoutManager(getActivity()));
        //提高展示效率
        recyBedmapPatient.setHasFixedSize(true);
        //设置的布局管理
        recyBedmapPatient.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {
        bedMapPatientTypeAdapter = new BedMapPatientTypeAdapter(new ArrayList<BedMapBean.LeftFilterBean>());
        bedMapPatientTypeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_bedmap_patienttype) {
                    leftFilterStr = ((BedMapBean.LeftFilterBean) adapter.getItem(position)).getCode();
                    setData(topFilterStr, leftFilterStr);
                    //左侧刷新分类选中状态显示
                    bedMapPatientTypeAdapter.setSelectedPostion(position);
                    bedMapPatientTypeAdapter.notifyDataSetChanged();
                }
            }
        });

        recyBedmapPatienttype.setAdapter(bedMapPatientTypeAdapter);


        bedMapPatientAdapter = new BedMapPatientAdapter(new ArrayList<BedMapBean.PatInfoListBean>());
//        bedMapPatientAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                BedMapBean.PatInfoListBean patInfoListBean = (BedMapBean.PatInfoListBean) adapter.getItem(position);
//
//            }
//        });

        recyBedmapPatient.setAdapter(bedMapPatientAdapter);

    }

    private void asyncInitData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);

        BedMapApiManager.getBedMap(new BedMapApiManager.GetBedMapCallback() {
            @Override
            public void onSuccess(BedMapBean bedMapBean) {


                topFilterBeanList = bedMapBean.getTopFilter();
                leftFilterBeanList = bedMapBean.getLeftFilter();
                patInfoListBeanList = bedMapBean.getPatInfoList();

                bedMapPatientTypeAdapter.setNewData(leftFilterBeanList);

                for (int i = 0; i < topFilterBeanList.size(); i++) {
                    BedMapBean.TopFilterBean topFilterBean = topFilterBeanList.get(i);
                    if ("".equals(topFilterBean.getCode())) {
                        switch (topFilterBean.getCode()) {
                            case "inBedAll":
                                tvBedmapAllarea.setVisibility(View.GONE);
                                break;
                            case "manageInBed":
                                tvBedmapAdminarea.setVisibility(View.GONE);
                                break;
                            case "todayOut":
                                tvBedmapNowoutarea.setVisibility(View.GONE);
                                break;
                            case "allOut":
                                tvBedmapAlloutarea.setVisibility(View.GONE);
                                break;
                            case "wait":
                                tvBedmapWaitarea.setVisibility(View.GONE);
                                break;
                            default:
                                break;
                        }
                    }
                }

                hideLoadFailTip();
                setData("inBedAll", "allPat");
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                Toast.makeText(getActivity(), code + ":" + msg, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setData(String topFilterStr, String leftFilterStr) {
        List<BedMapBean.PatInfoListBean> displayList = new ArrayList<>();

        if (leftFilterStr.equals("allPat")) {
            if (topFilterStr.equals("inBedAll")) {
                displayList = patInfoListBeanList;
            } else {
                for (int i = 0; i < patInfoListBeanList.size(); i++) {
                    BedMapBean.PatInfoListBean patInfoListBean = patInfoListBeanList.get(i);

                    boolean topmatch = false;

                    // {"code":"inBedAll","desc":"全区"},
                    // {"code":"manageInBed","desc":"管辖"},
                    // {"code":"todayOut","desc":"今出"},
                    // {"code":"allOut","desc":"全出"},
                    // {"code":"wait","desc":"等候"}
                    switch (topFilterStr) {
                        case "inBedAll":
                            if ("1".equals(patInfoListBean.getInBedAll())) {
                                topmatch = true;
                            }
                            break;
                        case "manageInBed":
                            if ("1".equals(patInfoListBean.getManageInBed())) {
                                topmatch = true;
                            }
                            break;
                        case "todayOut":
                            if ("1".equals(patInfoListBean.getTodayOut())) {
                                topmatch = true;
                            }
                            break;
                        case "allOut":
                            if ("1".equals(patInfoListBean.getAllOut())) {
                                topmatch = true;
                            }
                            break;
                        case "wait":
                            if ("1".equals(patInfoListBean.getWait())) {
                                topmatch = true;
                            }
                            break;
                        default:
                            break;
                    }
                    if (topmatch) {
                        displayList.add(patInfoListBean);
                    }
                }
            }
        } else {
            for (int i = 0; i < patInfoListBeanList.size(); i++) {
                BedMapBean.PatInfoListBean patInfoListBean = patInfoListBeanList.get(i);

                boolean topmatch = false;
                boolean leftmatch = false;

                // {"code":"inBedAll","desc":"全区"},
                // {"code":"manageInBed","desc":"管辖"},
                // {"code":"todayOut","desc":"今出"},
                // {"code":"allOut","desc":"全出"},
                // {"code":"wait","desc":"等候"}
                switch (topFilterStr) {
                    case "inBedAll":
                        if ("1".equals(patInfoListBean.getInBedAll())) {
                            topmatch = true;
                        }
                        break;
                    case "manageInBed":
                        if ("1".equals(patInfoListBean.getManageInBed())) {
                            topmatch = true;
                        }
                        break;
                    case "todayOut":
                        if ("1".equals(patInfoListBean.getTodayOut())) {
                            topmatch = true;
                        }
                        break;
                    case "allOut":
                        if ("1".equals(patInfoListBean.getAllOut())) {
                            topmatch = true;
                        }
                        break;
                    case "wait":
                        if ("1".equals(patInfoListBean.getWait())) {
                            topmatch = true;
                        }
                        break;
                    default:
                        break;
                }

                // {"code":"newPatient","desc":"新入"},
                // {"code":"operation","desc":"手术"},
                // {"code":"fever","desc":"发烧"},
                // {"code":"gotAllergy","desc":"过敏"}
                switch (leftFilterStr) {
                    case "newPatient":
                        if ("1".equals(patInfoListBean.getNewPatient())) {
                            leftmatch = true;
                        }
                        break;
                    case "operation":
                        if ("1".equals(patInfoListBean.getOperation())) {
                            leftmatch = true;
                        }
                        break;
                    case "fever":
                        if ("1".equals(patInfoListBean.getFever())) {
                            leftmatch = true;
                        }
                        break;
                    case "gotAllergy":
                        if ("1".equals(patInfoListBean.getGotAllergy())) {
                            leftmatch = true;
                        }
                        break;
                    default:
                        break;
                }

                if (topmatch && leftmatch) {
                    displayList.add(patInfoListBean);
                }
            }
        }

        bedMapPatientAdapter.setNewData(displayList);

    }

    /**
     * tvBedmapAllarea = view.findViewById(R.id.tv_bedmap_allarea);
     * tvBedmapAdminarea = view.findViewById(R.id.tv_bedmap_adminarea);
     * tvBedmapNowoutarea = view.findViewById(R.id.tv_bedmap_nowoutarea);
     * tvBedmapAlloutarea = view.findViewById(R.id.tv_bedmap_alloutarea);
     * tvBedmapWaitarea = view.findViewById(R.id.tv_bedmap_waitarea);
     *
     * @param v
     */

    // {"code":"inBedAll","desc":"全区"},
    // {"code":"manageInBed","desc":"管辖"},
    // {"code":"todayOut","desc":"今出"},
    // {"code":"allOut","desc":"全出"},
    // {"code":"wait","desc":"等候"}
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bedmap_allarea:
                topFilterStr = "inBedAll";
                setData(topFilterStr, leftFilterStr);
                break;
            case R.id.tv_bedmap_adminarea:
                topFilterStr = "manageInBed";
                setData(topFilterStr, leftFilterStr);
                break;
            case R.id.tv_bedmap_nowoutarea:
                topFilterStr = "todayOut";
                setData(topFilterStr, leftFilterStr);
                break;
            case R.id.tv_bedmap_alloutarea:
                topFilterStr = "allOut";
                setData(topFilterStr, leftFilterStr);
                break;
            case R.id.tv_bedmap_waitarea:
                topFilterStr = "wait";
                setData(topFilterStr, leftFilterStr);
                break;
            default:
                break;
        }
    }
}
