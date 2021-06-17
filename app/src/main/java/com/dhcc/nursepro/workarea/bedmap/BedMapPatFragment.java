package com.dhcc.nursepro.workarea.bedmap;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.view.PatIconView;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.dhcc.nursepro.workarea.checkresult.CheckResultListFragment;
import com.dhcc.nursepro.workarea.docorderlist.DocOrderListFragment;
import com.dhcc.nursepro.workarea.labresult.LabResultListFragment;
import com.dhcc.nursepro.workarea.orderexecute.OrderExecuteFragment;
import com.dhcc.nursepro.workarea.ordersearch.OrderSearchFragment;
import com.dhcc.nursepro.workarea.patevents.PatEventsFragment;
import com.google.gson.Gson;

/**
 * BedMapPatFragment
 * 病人个人页
 *
 * @author DevLix126
 * created at 2018/8/22 11:03
 */
public class BedMapPatFragment extends BaseFragment implements View.OnClickListener {
    
    private TextView tvBedmappatBedno;
    private TextView tvBedmappatName;
    private ImageView imgBedmappatSex;
    private TextView tvBedmappatCarelevel;


    private LinearLayout llBedmappatInfo;
    private LinearLayout llBedmappatOrder;
    private LinearLayout llBedmappatOrdersearch;
    private LinearLayout llBedmappatOrderexecute;
    private LinearLayout llBedmappatTestresult;
    private LinearLayout llBedmappatInspectionreport;
    private LinearLayout llBedmappatEventsearch;
    private LinearLayout llBedmapDayPayList;

    private PatIconView patIconView;


    private BedMapBean.PatInfoListBean patInfoListBean;

    private String jsonMap = "";
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bedmap_pat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        Gson gson = new Gson();
        patInfoListBean = gson.fromJson(bundle.getString("patinfo"),BedMapBean.PatInfoListBean.class);
        jsonMap = bundle.getString("jsonmap");

        //状态栏 背景 默认蓝色
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        //toolbar type
        setToolbarType(BaseActivity.ToolbarType.TOP);
        //toolbar 背景 默认蓝色
        setToolbarBackground(new ColorDrawable(0xffffffff));
        //toolbar 导航按钮隐藏
//        hideToolbarNavigationIcon();
        //toolbar 导航按钮显示 默认白色返回按钮
//        showToolbarNavigationIcon();
        //返回按钮img设置
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        //toolbar 中间标题 默认黑色
        setToolbarCenterTitle("");
        //toolbar 中间标题 内容 颜色 字号默认17
//        setToolbarCenterTitle(getString(R.string.title_bedmap),0xffffffff,17);
        //toolbar 分割线
        setToolbarBottomLineVisibility(true);


        initView(view);
        initData();


    }

    private void initView(View view) {
        tvBedmappatBedno = view.findViewById(R.id.tv_bedmappat_bedno);
        tvBedmappatName = view.findViewById(R.id.tv_bedmappat_name);
        imgBedmappatSex = view.findViewById(R.id.img_bedmappat_sex);
        tvBedmappatCarelevel = view.findViewById(R.id.tv_bedmappat_carelevel);
        llBedmappatInfo = view.findViewById(R.id.ll_bedmappat_info);
        llBedmappatInfo.setOnClickListener(this);
        llBedmappatOrder = view.findViewById(R.id.ll_bedmappat_order);
        llBedmappatOrder.setOnClickListener(this);
        llBedmappatOrdersearch = view.findViewById(R.id.ll_bedmappat_ordersearch);
        llBedmappatOrdersearch.setOnClickListener(this);
        llBedmappatOrderexecute = view.findViewById(R.id.ll_bedmappat_orderexecute);
        llBedmappatOrderexecute.setOnClickListener(this);
        llBedmappatTestresult = view.findViewById(R.id.ll_bedmappat_testresult);
        llBedmappatTestresult.setOnClickListener(this);
        llBedmappatInspectionreport = view.findViewById(R.id.ll_bedmappat_inspectionreport);
        llBedmappatInspectionreport.setOnClickListener(this);
        llBedmappatEventsearch = view.findViewById(R.id.ll_bedmappat_eventsearch);
        llBedmappatEventsearch.setOnClickListener(this);
        llBedmapDayPayList = view.findViewById(R.id.ll_bedmappat_daypaylist);
        llBedmapDayPayList.setOnClickListener(this);

        patIconView = view.findViewById(R.id.paticon_patinfo);
        
    }

    private void initData() {
        tvBedmappatBedno.setText(patInfoListBean.getBedCode().isEmpty() ? "未分床" : patInfoListBean.getBedCode());
        tvBedmappatName.setText(patInfoListBean.getName());
        tvBedmappatCarelevel.setText(patInfoListBean.getCareLevel());
        if ("男".equals(patInfoListBean.getSex())) {
            imgBedmappatSex.setImageResource(R.drawable.sex_male);
        } else {
            imgBedmappatSex.setImageResource(R.drawable.sex_female);
        }

        patIconView.setIconShow(patInfoListBean);
        patIconView.showLeft();
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.ll_bedmappat_info://基本信息
                bundle.putString("jsonmap",jsonMap);
                startFragment(BedMapPatInfoFragment.class,bundle);
                break;
            case R.id.ll_bedmappat_daypaylist://日清单
                bundle.putString("episodeId",patInfoListBean.getEpisodeId());
                bundle.putString("inHosDate",patInfoListBean.getRQDDate());
                startFragment(DayPayListFragment.class,bundle);
                break;
            case R.id.ll_bedmappat_order://医嘱单
                bundle.putString("episodeid",patInfoListBean.getEpisodeId());
                startFragment(DocOrderListFragment.class,bundle);
                break;
            case R.id.ll_bedmappat_ordersearch://医嘱查询
                bundle.putString("regNo",patInfoListBean.getRegNo());
                startFragment(OrderSearchFragment.class,bundle);
                break;
            case R.id.ll_bedmappat_orderexecute://医嘱执行
                Bundle bundle4 = new Bundle();
                bundle4.putString("regNo",patInfoListBean.getRegNo());
                startFragment(OrderExecuteFragment.class,bundle4);
                break;
            case R.id.ll_bedmappat_testresult://检验结果
                String bedinfolab = patInfoListBean.getBedCode().isEmpty()?"未分床  "+patInfoListBean.getName():patInfoListBean.getBedCode()+"  "+patInfoListBean.getName();
                bundle.putString("episodeId",patInfoListBean.getEpisodeId());
                bundle.putString("patmsg",bedinfolab);
                startFragment(LabResultListFragment.class, bundle);
                break;
            case R.id.ll_bedmappat_inspectionreport://检查报告
                String bedinfocheck = patInfoListBean.getBedCode().isEmpty()?"未分床  "+patInfoListBean.getName():patInfoListBean.getBedCode()+"  "+patInfoListBean.getName();
                bundle.putString("episodeId",patInfoListBean.getEpisodeId());
                bundle.putString("patmsg",bedinfocheck);
                startFragment(CheckResultListFragment.class, bundle);
                break;
            case R.id.ll_bedmappat_eventsearch://事件查询
                bundle.putString("regNo",patInfoListBean.getRegNo());
                startFragment(PatEventsFragment.class, bundle);
                break;
            default:
                break;
        }
    }
}
