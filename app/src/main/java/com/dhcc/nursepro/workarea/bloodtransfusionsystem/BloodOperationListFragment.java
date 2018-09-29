package com.dhcc.nursepro.workarea.bloodtransfusionsystem;

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
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.adapter.BloodOperationListAdapter;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.api.BloodTSApiManager;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodOperationListBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * BloodOperationListFragment
 * 输血查询
 *
 * @author Q
 * created at 2018/9/19 09:28
 */
public class BloodOperationListFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener {
    private TextView tvBloodoplistSign;
    private TextView tvBloodoplistTrans;
    private TextView tvBloodoplistTransend;
    private TextView tvBloodoplistRecycling;
    private LinearLayout llBloodoplistSearchdate;
    private TextView tvBloodoplistSearchdate;
    private RecyclerView recyBloodoplist;
    private LinearLayout llBloodoplistEmpty;
    private TextView tvBloodoplistEmpty;


    //注:type R 查询签收列表，S 查询血液输注列表，E 查询输血结束列表， Re 查询回收列表。
    private String type = "R";
    private String searchDate;

    private List<BloodOperationListBean.BloodListBean> bloodOperationList;
    private BloodOperationListAdapter operationListAdapter;



    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blood_operationlist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle(getString(R.string.title_bloodtransfusionsearch));
        setToolbarBottomLineVisibility(false);

        searchDate = SPUtils.getInstance().getString(SharedPreference.SCHSTDATETIME).substring(0, 10);
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
        tvBloodoplistSign = view.findViewById(R.id.tv_bloodoplist_sign);
        tvBloodoplistSign.setOnClickListener(this);
        tvBloodoplistTrans = view.findViewById(R.id.tv_bloodoplist_trans);
        tvBloodoplistTrans.setOnClickListener(this);
        tvBloodoplistTransend = view.findViewById(R.id.tv_bloodoplist_transend);
        tvBloodoplistTransend.setOnClickListener(this);
        tvBloodoplistRecycling = view.findViewById(R.id.tv_bloodoplist_recycling);
        tvBloodoplistRecycling.setOnClickListener(this);
        llBloodoplistSearchdate = view.findViewById(R.id.ll_bloodoplist_searchdate);
        llBloodoplistSearchdate.setOnClickListener(this);
        setTopFilterSelect(tvBloodoplistSign);
        tvBloodoplistSearchdate = view.findViewById(R.id.tv_bloodoplist_searchdate);
        tvBloodoplistSearchdate.setText(searchDate);
        llBloodoplistEmpty = view.findViewById(R.id.ll_bloodoplist_empty);
        tvBloodoplistEmpty = view.findViewById(R.id.tv_bloodoplist_empty);

        recyBloodoplist = view.findViewById(R.id.recy_bloodoplist);
        //提高展示效率
        recyBloodoplist.setHasFixedSize(true);
        //设置的布局管理
        recyBloodoplist.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initAdapter() {
        operationListAdapter = new BloodOperationListAdapter(new ArrayList<BloodOperationListBean.BloodListBean>());
        operationListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BloodOperationListBean.BloodListBean bloodListBean = (BloodOperationListBean.BloodListBean) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("bloodRowId", bloodListBean.getBloodRowId());
                startFragment(BloodTransfusionDetailFragment.class,bundle);
            }
        });

        recyBloodoplist.setAdapter(operationListAdapter);
    }

    private void asyncInitData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        BloodTSApiManager.getBloodList(type, searchDate, new BloodTSApiManager.BloodOperationListCallback() {
            @Override
            public void onSuccess(BloodOperationListBean bloodOperationListBean) {
                hideLoadingTip();
                bloodOperationList = bloodOperationListBean.getBloodList();
                operationListAdapter.setSearchType(type);
                operationListAdapter.setNewData(bloodOperationList);
                if (bloodOperationList.size() > 0) {
                    recyBloodoplist.setVisibility(View.VISIBLE);
                    llBloodoplistEmpty.setVisibility(View.GONE);
                } else {
                    recyBloodoplist.setVisibility(View.GONE);
                    llBloodoplistEmpty.setVisibility(View.VISIBLE);
                    if (type.equals("R")) {
                        tvBloodoplistEmpty.setText("暂无签收");
                    } else if (type.equals("S")) {
                        tvBloodoplistEmpty.setText("暂无输注");
                    } else if (type.equals("E")) {
                        tvBloodoplistEmpty.setText("暂无结束");
                    } else if (type.equals("Re")) {
                        tvBloodoplistEmpty.setText("暂无回收");
                    }
                }

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                Toast.makeText(getActivity(), code + ":" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * type
     * R 查询签收列表
     * S 查询血液输注列表
     * E 查询输血结束列表
     * Re 查询回收列表
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bloodoplist_sign:
                setTopFilterSelect(tvBloodoplistSign);
                type = "R";
                asyncInitData();
                break;
            case R.id.tv_bloodoplist_trans:
                setTopFilterSelect(tvBloodoplistTrans);
                type = "S";
                asyncInitData();
                break;
            case R.id.tv_bloodoplist_transend:
                setTopFilterSelect(tvBloodoplistTransend);
                type = "E";
                asyncInitData();
                break;
            case R.id.tv_bloodoplist_recycling:
                setTopFilterSelect(tvBloodoplistRecycling);
                type = "Re";
                asyncInitData();
                break;
            case R.id.ll_bloodoplist_searchdate:
                chooseTime();
                break;
            default:
                break;
        }
    }

    private void setTopFilterSelect(View view) {
        tvBloodoplistSign.setSelected(view == tvBloodoplistSign);
        tvBloodoplistTrans.setSelected(view == tvBloodoplistTrans);
        tvBloodoplistTransend.setSelected(view == tvBloodoplistTransend);
        tvBloodoplistRecycling.setSelected(view == tvBloodoplistRecycling);
    }

    private void chooseTime() {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
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

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String date = TimeUtils.millis2String(millseconds).substring(0, 10);
        if (!date.equals(searchDate)) {
            //日期时间发生改变，需重新请求数据
            tvBloodoplistSearchdate.setText(date);
            searchDate = date;
            asyncInitData();
        }

    }
}
