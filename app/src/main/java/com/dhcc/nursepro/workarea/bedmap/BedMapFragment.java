package com.dhcc.nursepro.workarea.bedmap;


import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.Activity.SingleMainActivity;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.view.DhcHeadRefreshView;
import com.dhcc.nursepro.view.ReFreshParent;
import com.dhcc.nursepro.workarea.bedmap.adapter.BedMapPatientAdapter;
import com.dhcc.nursepro.workarea.bedmap.adapter.BedMapPatientTypeAdapter;
import com.dhcc.nursepro.workarea.bedmap.api.BedMapApiManager;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.dhcc.nursepro.workarea.bedmap.bean.ScanResultBean;
import com.google.gson.Gson;
import com.ufo.dwrefresh.view.DWRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * BedMapFragment
 * 床位图
 *
 * @author DevLix126
 * @date 2018/8/11
 */
public class BedMapFragment extends BaseFragment implements View.OnClickListener {
//    private SwipeRefreshLayout swipe_labout;
    private DWRefreshLayout refresh;
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

    private List<BedMapBean.LeftFilterBean> leftFilterBeanList =new ArrayList<>();
    private List<BedMapBean.LeftFilterBean> leftFilterBeanListFirst =new ArrayList<>();
    private List<BedMapBean.PatInfoListBean> patInfoListBeanList =new ArrayList<>();
    private List<BedMapBean.TopFilterBean> topFilterBeanList =new ArrayList<>();

    //所有患者信息
    private List<Map<String, String>> patInfoMapList =new ArrayList<>();
    //展示的患者信息
    private List<Map<String, String>> patInfoMapListShow =new ArrayList<>();

    private String bedCode = "";
    private String topFilterStr = "inBedAll";
    private String leftFilterStr = "allPat";
    private String leftFilterStrFirst = "allPat";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.title_bedmap), 0xffffffff, 17);

        //右上角保存按钮
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   刷新   ");
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncInitData();

            }
        });
//        setToolbarRightCustomView(viewright);


        initView(view);
        initAdapter();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                asyncInitData();
            }
        }, 300);


    }

    private void initView(View view) {
//        swipe_labout = view.findViewById(R.id.swipe_labout);
//        swipe_labout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                showToast("sssss");
//                swipe_labout.setRefreshing(false);
//            }
//        });
        refresh = view.findViewById(R.id.refresh);
        DhcHeadRefreshView viewHead =new DhcHeadRefreshView(getActivity());
        refresh.setHeadView(viewHead);
        //禁止加载更多功能，默认是允许的
        refresh.lockLoadMore(true);
        refresh.setOnRefreshListener(new DWRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新回调
                asyncInitData();
                refresh.setRefresh(false);
            }

            @Override
            public void onLoadMore() {
                //加载更多回调
            }
        });

        //控制图标大小
        etBedmapBedno = view.findViewById(R.id.et_bedmap_bedno);
        Drawable drawable1 = getResources().getDrawable(R.drawable.search); //获取图片
        drawable1.setBounds(0, 0, SizeUtils.dp2px(18), SizeUtils.dp2px(18));  //设置图片参数
        etBedmapBedno.setCompoundDrawables(drawable1, null, null, null);

        //变更即搜
        etBedmapBedno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                bedCode = s.toString();
                setData(bedCode, topFilterStr, leftFilterStr);
            }
        });


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

        setTopFilterSelect(tvBedmapAllarea);

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
        bedMapPatientTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                leftFilterStr = ((BedMapBean.LeftFilterBean) adapter.getItem(position)).getCode();
                setData(bedCode, topFilterStr, leftFilterStr);
                //左侧刷新分类选中状态显示
                bedMapPatientTypeAdapter.setSelectedPostion(position);
                bedMapPatientTypeAdapter.notifyDataSetChanged();
            }
        });

        recyBedmapPatienttype.setAdapter(bedMapPatientTypeAdapter);


        bedMapPatientAdapter = new BedMapPatientAdapter(new ArrayList<BedMapBean.PatInfoListBean>());
//        bedMapPatientAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                BedMapBean.PatInfoListBean patInfoListBean = (BedMapBean.PatInfoListBean) adapter.getItem(position);
//                Gson gson = new Gson();
//                Map map = patInfoMapListShow.get(position);
//                String jsonMap = gson.toJson(map);
//                Bundle bundle = new Bundle();
//                bundle.putString("patInfo", gson.toJson(patInfoListBean));
//                bundle.putString("jsonmap",jsonMap);
//
////                startFragment(BedMapPatFragment.class, bundle);
//                Intent intent = new Intent(getActivity(), SingleMainActivity.class);
//                intent.putExtra("epi",patInfoListBean.getEpisodeId());
//                startActivity(intent);
//
//            }
//        });

        bedMapPatientAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId()==R.id.ll_pat_singlemodel){
                    BedMapBean.PatInfoListBean patInfoListBean = (BedMapBean.PatInfoListBean) adapter.getItem(position);
                    Gson gson = new Gson();
                    Map map = patInfoMapListShow.get(position);
                    String jsonMap = gson.toJson(map);
                    Bundle bundle = new Bundle();
                    bundle.putString("patInfo", gson.toJson(patInfoListBean));
                    bundle.putString("jsonmap",jsonMap);

//                startFragment(BedMapPatFragment.class, bundle);
                    Intent intent = new Intent(getActivity(), SingleMainActivity.class);
                    intent.putExtra("epi",patInfoListBean.getEpisodeId());
                    startActivity(intent);
                }else if (view.getId()==R.id.tv_pat_detail){
                    BedMapBean.PatInfoListBean patInfoListBean = (BedMapBean.PatInfoListBean) adapter.getItem(position);
                    Gson gson = new Gson();
                    Map map = patInfoMapListShow.get(position);
                    String jsonMap = gson.toJson(map);
                    String jsonTrans = gson.toJson(patInfoListBean);
                    Bundle bundle = new Bundle();
                    bundle.putString("patInfo", gson.toJson(patInfoListBean));
                    bundle.putString("jsonmap",jsonMap);
                    bundle.putString("jsonTrans",jsonTrans);
                    startFragment(BedMapPatInfoFragment.class, bundle);
//                    Intent intent = new Intent(getActivity(), SingleMainActivity.class);
//                    intent.putExtra("epi",patInfoListBean.getEpisodeId());
//                    startActivity(intent);
                }
            }
        });

        recyBedmapPatient.setAdapter(bedMapPatientAdapter);

    }

    private void asyncInitData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);

        BedMapApiManager.getBedMap(new BedMapApiManager.GetBedMapCallback() {
            @Override
            public void onSuccess(BedMapBean bedMapBean, Map bedMapMap) {

                topFilterBeanList = bedMapBean.getTopFilter();
                leftFilterBeanList = bedMapBean.getLeftFilter();
                if (bedMapBean.getLeftFilter().size()>0){
                    leftFilterBeanListFirst = new ArrayList<>();
                    leftFilterBeanListFirst.add(bedMapBean.getLeftFilter().get(0));
                }
                patInfoListBeanList = bedMapBean.getPatInfoList();

                patInfoMapList = (List<Map<String, String>>) bedMapMap.get("patInfoList");
                patInfoMapListShow = patInfoMapList;
                if (topFilterStr == "inBedAll" || topFilterStr == "manageInBed"){
                    bedMapPatientTypeAdapter.setNewData(leftFilterBeanList);
                }else {
                    bedMapPatientTypeAdapter.setNewData(leftFilterBeanListFirst);
                }


                for (int i = 0; i < topFilterBeanList.size(); i++) {
                    BedMapBean.TopFilterBean topFilterBean = topFilterBeanList.get(i);
                    if ("".equals(topFilterBean.getDesc())) {
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

                hideLoadingTip();
                setData(bedCode, topFilterStr, leftFilterStr);
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void setData(String bedCode, String topFilterStr, String leftFilterStr) {
        List<BedMapBean.PatInfoListBean> displayList = new ArrayList<>();

        patInfoMapListShow = new ArrayList<>();
        for (int i = 0; i < patInfoMapList.size(); i++) {
            BedMapBean.PatInfoListBean patInfoListBean = patInfoListBeanList.get(i);
            Map<String, String> patInfoMap = patInfoMapList.get(i);

            boolean bednomatch = false;
            boolean topmatch = false;
            boolean leftmatch = false;

            if ("".equals(bedCode) || patInfoMap.get("bedCode").equals(bedCode)) {
                bednomatch = true;
            }

            if ("1".equals(patInfoMap.get(topFilterStr))) {
                topmatch = true;
            }

            if ("allPat".equals(leftFilterStr) || "1".equals(patInfoMap.get(leftFilterStr))) {
                leftmatch = true;
            }

            if (bednomatch && topmatch && leftmatch) {
                displayList.add(patInfoListBean);
                patInfoMapListShow.add(patInfoMapList.get(i));
            }
        }

        bedMapPatientAdapter.setNewData(displayList);
        recyBedmapPatient.scrollToPosition(0);

    }

    private void setTopFilterSelect(View view) {
        tvBedmapAllarea.setSelected(view == tvBedmapAllarea);
        tvBedmapAdminarea.setSelected(view == tvBedmapAdminarea);
        tvBedmapNowoutarea.setSelected(view == tvBedmapNowoutarea);
        tvBedmapAlloutarea.setSelected(view == tvBedmapAlloutarea);
        tvBedmapWaitarea.setSelected(view == tvBedmapWaitarea);

        if (view == tvBedmapAllarea) {
            tvBedmapAllarea.setTypeface(Typeface.DEFAULT_BOLD);
            tvBedmapAdminarea.setTypeface(Typeface.DEFAULT);
            tvBedmapNowoutarea.setTypeface(Typeface.DEFAULT);
            tvBedmapAlloutarea.setTypeface(Typeface.DEFAULT);
            tvBedmapWaitarea.setTypeface(Typeface.DEFAULT);
        } else if (view == tvBedmapAdminarea) {
            tvBedmapAllarea.setTypeface(Typeface.DEFAULT);
            tvBedmapAdminarea.setTypeface(Typeface.DEFAULT_BOLD);
            tvBedmapNowoutarea.setTypeface(Typeface.DEFAULT);
            tvBedmapAlloutarea.setTypeface(Typeface.DEFAULT);
            tvBedmapWaitarea.setTypeface(Typeface.DEFAULT);
        } else if (view == tvBedmapNowoutarea) {
            tvBedmapAllarea.setTypeface(Typeface.DEFAULT);
            tvBedmapAdminarea.setTypeface(Typeface.DEFAULT);
            tvBedmapNowoutarea.setTypeface(Typeface.DEFAULT_BOLD);
            tvBedmapAlloutarea.setTypeface(Typeface.DEFAULT);
            tvBedmapWaitarea.setTypeface(Typeface.DEFAULT);
        } else if (view == tvBedmapAlloutarea) {
            tvBedmapAllarea.setTypeface(Typeface.DEFAULT);
            tvBedmapAdminarea.setTypeface(Typeface.DEFAULT);
            tvBedmapNowoutarea.setTypeface(Typeface.DEFAULT);
            tvBedmapAlloutarea.setTypeface(Typeface.DEFAULT_BOLD);
            tvBedmapWaitarea.setTypeface(Typeface.DEFAULT);
        } else if (view == tvBedmapWaitarea) {
            tvBedmapAllarea.setTypeface(Typeface.DEFAULT);
            tvBedmapAdminarea.setTypeface(Typeface.DEFAULT);
            tvBedmapNowoutarea.setTypeface(Typeface.DEFAULT);
            tvBedmapAlloutarea.setTypeface(Typeface.DEFAULT);
            tvBedmapWaitarea.setTypeface(Typeface.DEFAULT_BOLD);
        }
    }

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
                bedMapPatientTypeAdapter.setNewData(leftFilterBeanList);
                setTopFilterSelect(tvBedmapAllarea);
                setData(bedCode, topFilterStr, leftFilterStr);
                break;
            case R.id.tv_bedmap_adminarea:
                topFilterStr = "manageInBed";
                bedMapPatientTypeAdapter.setNewData(leftFilterBeanList);
                setTopFilterSelect(tvBedmapAdminarea);
                setData(bedCode, topFilterStr, leftFilterStr);
                break;
            case R.id.tv_bedmap_nowoutarea:
                topFilterStr = "todayOut";
                leftFilterStr = "allPat";
                bedMapPatientTypeAdapter.setNewData(leftFilterBeanListFirst);
                bedMapPatientTypeAdapter.setSelectedPostion(0);
                bedMapPatientTypeAdapter.notifyDataSetChanged();
                setTopFilterSelect(tvBedmapNowoutarea);
                setData(bedCode, topFilterStr, leftFilterStr);
                break;
            case R.id.tv_bedmap_alloutarea:
                topFilterStr = "allOut";
                leftFilterStr = "allPat";
                bedMapPatientTypeAdapter.setNewData(leftFilterBeanListFirst);
                bedMapPatientTypeAdapter.setSelectedPostion(0);
                bedMapPatientTypeAdapter.notifyDataSetChanged();
                setTopFilterSelect(tvBedmapAlloutarea);
                setData(bedCode, topFilterStr, leftFilterStr);
                break;
            case R.id.tv_bedmap_waitarea:
                topFilterStr = "wait";
                leftFilterStr = "allPat";
                bedMapPatientTypeAdapter.setNewData(leftFilterBeanListFirst);
                bedMapPatientTypeAdapter.setSelectedPostion(0);
                bedMapPatientTypeAdapter.notifyDataSetChanged();
                setTopFilterSelect(tvBedmapWaitarea);
                setData(bedCode, topFilterStr, leftFilterStr);
                break;
            default:
                break;
        }
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
                Bundle bundle = new Bundle();
                bundle = intent.getExtras();
                String scanInfo = bundle.getString("data");
                BedMapApiManager.getScanInfo(scanInfo, new BedMapApiManager.GetScanInfoCallback() {
                    @Override
                    public void onSuccess(ScanResultBean scanResultBean) {
                        bedCode = scanResultBean.getPatInfo().getBedCode();
                        setData(bedCode, topFilterStr, leftFilterStr);
                    }

                    @Override
                    public void onFail(String code, String msg) {
                        showToast("error" + code + ":" + msg);
                    }
                });
        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bedmap, container, false);
    }
}
