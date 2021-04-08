package com.dhcc.nursepro.workarea.bedmap;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.NurseAPI;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.DateUtils;
import com.dhcc.nursepro.workarea.bedmap.adapter.DayPayTypeAdapter;
import com.dhcc.nursepro.workarea.bedmap.api.BedMapApiManager;
import com.dhcc.nursepro.workarea.bedmap.bean.DayPayListBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.bedmap
 * <p>
 * author Q
 * Date: 2019/11/7
 * Time:8:54
 */
public class DayPayListFragment extends BaseFragment {

    private RecyclerView recDayPayType;
    private DayPayTypeAdapter dayPayTypeAdapter;
    private TextView tvLoc,tvPrePay,tvTotalSum,tvBalance,tvInHosTime,tvPatName,tvChoosTime,tvInFee;
    private LinearLayout llPayEmp,llPayInfo;
    private String payDate = "";
    private String episodeId = "";

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daypay_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        setToolbarCenterTitle(getString(R.string.title_daypaylist));

        Bundle bundle = getArguments();
        episodeId = bundle.getString("episodeId");
        if (bundle.getString("inHosDate") == null){
            String curTime = SPUtils.getInstance().getString(SharedPreference.SCHENDATETIME);
            payDate =curTime.substring(0,11);
        }else {
            payDate = bundle.getString("inHosDate");
        }
//        Gson gson = new Gson();
//        jsonMap= gson.fromJson(jsonStr, Map.class);
//        String bedinfo = jsonMap.get("bedCode").toString().isEmpty()?"未分床  "+jsonMap.get("name").toString():jsonMap.get("bedCode").toString()+"  "+jsonMap.get("name").toString();
//        setToolbarCenterTitle(bedinfo);
//        //toolbar 中间标题 内容 颜色 字号默认17
//        //        setToolbarCenterTitle(getString(R.string.title_bedmap),0xffffffff,17);
//        //toolbar 分割线
//        setToolbarBottomLineVisibility(true);
//
//

        initView(view);
        initData();


    }
    private void initView(View view){

        tvLoc = view.findViewById(R.id.tv_locdesc);
        tvInHosTime = view.findViewById(R.id.tv_inhostime);
        tvTotalSum = view.findViewById(R.id.tv_payed);
        tvBalance = view.findViewById(R.id.tv_balance);
        tvPrePay = view.findViewById(R.id.tv_prepay);
        tvPatName = view.findViewById(R.id.tv_patname);
        tvChoosTime = view.findViewById(R.id.tv_choosepaydate);
        tvChoosTime.setText(payDate);
        tvChoosTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long curMill = TimeUtils.string2Millis(payDate+" 00:00:00");
                DateUtils.chooseDate(curMill,getActivity(), getFragmentManager(), new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        tvChoosTime.setText(DateUtils.getDateByMillisecond(millseconds));
                        payDate = DateUtils.getDateByMillisecond(millseconds);
                        initData();
                    }
                });

            }
        });
        llPayInfo = view.findViewById(R.id.ll_pay_info);
        llPayEmp = view.findViewById(R.id.ll_paylist_empty);
        tvInFee =view.findViewById(R.id.tv_infee);

        recDayPayType = view.findViewById(R.id.recy_daypay_typelist);
        //提高展示效率
        recDayPayType.setHasFixedSize(true);
        //设置的布局管理
        recDayPayType.setLayoutManager(new LinearLayoutManager(getActivity()));
        dayPayTypeAdapter = new DayPayTypeAdapter(new ArrayList<DayPayListBean.PriceListBean>());
        recDayPayType.setAdapter(dayPayTypeAdapter);
    }

    private void initData(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("episodeId", episodeId);
        map.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));
        map.put("schDate",payDate);
        BedMapApiManager.getDayPayList(map, NurseAPI.getInfeeDetailByDate, new BedMapApiManager.GetDayPayListCallback() {
            @Override
            public void onSuccess(DayPayListBean dayPayListBean) {

                tvPatName.setText(dayPayListBean.getPatInfo().getBedCode().replace("床","")+"床  "+dayPayListBean.getPatInfo().getName());
                tvLoc.setText(dayPayListBean.getPatInfo().getCtLocDesc());
                tvInHosTime.setText(dayPayListBean.getPatInfo().getInHospDateTime());
                tvPrePay.setText(dayPayListBean.getPatInfo().getPrepay());
                tvBalance.setText(dayPayListBean.getPatInfo().getBalance());
                tvTotalSum.setText(dayPayListBean.getPatInfo().getTotalSum());
                tvInFee.setText(dayPayListBean.getPatInfo().getInfee());
                if (dayPayListBean.getPriceList().size()<1){
                    llPayEmp.setVisibility(View.VISIBLE);
                    llPayInfo.setVisibility(View.VISIBLE);
                }else {
                    llPayEmp.setVisibility(View.GONE);
                    llPayInfo.setVisibility(View.GONE);
                }
                dayPayTypeAdapter.setPayInfo(dayPayListBean.getPatInfo().getInHospDateTime(),dayPayListBean.getPatInfo().getPrepay(),dayPayListBean.getPatInfo().getBalance(),dayPayListBean.getPatInfo().getTotalSum(),dayPayListBean.getPatInfo().getInfee());
                dayPayTypeAdapter.setNewData(dayPayListBean.getPriceList());
                dayPayTypeAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error:"+code+msg);
                llPayEmp.setVisibility(View.GONE);
            }
        });

    }
}
