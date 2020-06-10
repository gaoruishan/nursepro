package com.dhcc.nursepro.workarea.plyout;

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
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.DateUtils;
import com.dhcc.nursepro.workarea.plyout.adapter.PlyOutAdapter;
import com.dhcc.nursepro.workarea.plyout.api.PlyOutApiManager;
import com.dhcc.nursepro.workarea.plyout.bean.PlyOutListAllBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PlyOutListFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener {

    private RecyclerView recLabOut;
    private TextView tvType1, tvType2, tvType3, tvType4, tvStartDate, tvEndDate;
    private LinearLayout llEmpty;
    private View show1, show2, show3, show4;
    private PlyOutAdapter labOutAdapter;
    private List<PlyOutListAllBean.LabOutListBean> listLabAll =new ArrayList<>(), listLabNow =new ArrayList<>();
    private String dateStr, CarrayCerate = "No", CarrayDel = "No", CarrayNo = "", TypeStr = "Type1";
    private List<PlyOutListAllBean.TypeListBean> listType =new ArrayList<>();

    private SPUtils spUtils = SPUtils.getInstance();
    private Long timeNow = System.currentTimeMillis();

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_laboutlist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_plybout), 0xffffffff, 17);
        //右上角按钮"新建"
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("  新建   ");
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                startFragment(PatEventsDetailFragment.class);
                if (System.currentTimeMillis()-timeNow > 1500){
                    CarrayCerate = "Yes";
                    initData();
                    setTopFilterSelect(tvType1);
                    showgone(show1);
                    TypeStr = "Type1";
                    getLabOutList();
                    timeNow =  System.currentTimeMillis();
                }else {
                    showToast("不可频繁建单，请稍后建单");
                }

            }
        });
        setToolbarRightCustomView(viewright);
        initview(view);
        initAdapter();
        initData();

    }

    private void initData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> map = new HashMap<>();
        map.put("stdate", tvStartDate.getText().toString());
        map.put("enddate", tvEndDate.getText().toString());
        map.put("locId", spUtils.getString(SharedPreference.LOCID));
        if (CarrayCerate.equals("Yes")) {
            map.put("userId", spUtils.getString(SharedPreference.USERID));
            if (CarrayDel.equals("Yes")) {
                map.put("carryNo", CarrayNo);
            }
        }
        PlyOutApiManager.getLabOutListMsg(map, "getPlyOutList", new PlyOutApiManager.getLabOutCallBack() {
            @Override
            public void onSuccess(PlyOutListAllBean labOutListAllBean) {
                hideLoadFailTip();
                CarrayCerate = "No";
                CarrayDel = "No";
                if (listType.size() == 0) {
                    listType = labOutListAllBean.getTypeList();
                    tvType1.setText(listType.get(0).getDesc());
                    tvType2.setText(listType.get(1).getDesc());
                    tvType3.setText(listType.get(2).getDesc());
                    tvType4.setText(listType.get(3).getDesc());
                }

                listLabAll = labOutListAllBean.getLabOutList();
                getLabOutList();
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                CarrayCerate = "No";
                CarrayDel = "No";
                showToast("error" + code + ":" + msg);
            }
        });

    }

    private void setTopFilterSelect(View view) {
        tvType1.setSelected(view == tvType1);
        tvType2.setSelected(view == tvType2);
        tvType3.setSelected(view == tvType3);
        tvType4.setSelected(view == tvType4);
    }

    private void showgone(View view) {
        show1.setBackgroundColor(getResources().getColor(R.color.blue_light));
        show2.setBackgroundColor(getResources().getColor(R.color.blue_light));
        show3.setBackgroundColor(getResources().getColor(R.color.blue_light));
        show4.setBackgroundColor(getResources().getColor(R.color.blue_light));
        view.setBackgroundColor(getResources().getColor(R.color.blue));
    }

    private void getLabOutList() {
        listLabNow = new ArrayList<>();
        for (int i = 0; i < listLabAll.size(); i++) {
            switch (TypeStr) {
                case "Type1":
                    if (listLabAll.get(i).getStatus().equals(listType.get(0).getDesc())) {
                        listLabNow.add(listLabAll.get(i));
                    }
                    break;
                case "Type2":
                    if (listLabAll.get(i).getStatus().equals(listType.get(1).getDesc())) {
                        listLabNow.add(listLabAll.get(i));
                    }
                    break;
                case "Type3":
                    if (listLabAll.get(i).getStatus().equals(listType.get(2).getDesc())) {
                        listLabNow.add(listLabAll.get(i));
                    }
                    break;
                case "Type4":
                    if (listLabAll.get(i).getStatus().equals(listType.get(3).getDesc())) {
                        listLabNow.add(listLabAll.get(i));
                    }
                    break;
                default:
                    break;
            }
        }
        labOutAdapter.setNewData(listLabNow);
        if (listLabNow.size() < 1) {
            llEmpty.setVisibility(View.VISIBLE);
        } else {
            llEmpty.setVisibility(View.GONE);
        }
        labOutAdapter.notifyDataSetChanged();
    }

    private void initview(View view) {

        llEmpty = view.findViewById(R.id.ll_laboout_empty);
        tvType1 = view.findViewById(R.id.tv_labout_type1);
        tvType1.setOnClickListener(this);
        tvType2 = view.findViewById(R.id.tv_labout_type2);
        tvType2.setOnClickListener(this);
        tvType3 = view.findViewById(R.id.tv_labout_type3);
        tvType3.setOnClickListener(this);
        tvType4 = view.findViewById(R.id.tv_labout_type4);
        tvType4.setOnClickListener(this);
        tvStartDate = view.findViewById(R.id.tv_labout_startdate);
        tvStartDate.setOnClickListener(this);
        tvEndDate = view.findViewById(R.id.tv_labout_enddate);
        tvEndDate.setOnClickListener(this);

        tvStartDate.setText(DateUtils.getDateTimeAgo(spUtils.getString(SharedPreference.CURDATETIME),1).substring(0, 10));
        tvEndDate.setText(spUtils.getString(SharedPreference.CURDATETIME).substring(0, 10));

        show1 = view.findViewById(R.id.view_labout_show1);
        show2 = view.findViewById(R.id.view_labout_show2);
        show3 = view.findViewById(R.id.view_labout_show3);
        show4 = view.findViewById(R.id.view_labout_show4);

        setTopFilterSelect(tvType1);
        showgone(show1);

        recLabOut = view.findViewById(R.id.recy_labout);
        //提高展示效率
        recLabOut.setHasFixedSize(true);
        //设置的布局管理
        recLabOut.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {

        listType = new ArrayList<PlyOutListAllBean.TypeListBean>();
        labOutAdapter = new PlyOutAdapter(new ArrayList<PlyOutListAllBean.LabOutListBean>());
        recLabOut.setAdapter(labOutAdapter);

        labOutAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_lapack_del) {
                    CarrayCerate = "Yes";
                    CarrayDel = "Yes";
                    CarrayNo = listLabNow.get(position).getCarryNo();
                    initData();
                } else if (view.getId() == R.id.messagecontentll) {
                    String carryNodetail = listLabNow.get(position).getCarryNo();
                    Bundle bundle = new Bundle();
                    bundle.putString("CarryNo", carryNodetail);
                    startFragment(PlyOutDetailFragment.class, bundle);
                }

            }
        });

    }    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_labout_type1:
                TypeStr = "Type1";
                setTopFilterSelect(tvType1);
                showgone(show1);
                getLabOutList();
                break;
            case R.id.tv_labout_type2:
                TypeStr = "Type2";
                setTopFilterSelect(tvType2);
                showgone(show2);
                getLabOutList();
                break;
            case R.id.tv_labout_type3:
                TypeStr = "Type3";
                setTopFilterSelect(tvType3);
                showgone(show3);
                getLabOutList();
                break;
            case R.id.tv_labout_type4:
                TypeStr = "Type4";
                setTopFilterSelect(tvType4);
                showgone(show4);
                getLabOutList();
                break;
            case R.id.tv_labout_startdate:
                dateStr = "start";
                chooseTime(TimeUtils.string2Millis(tvStartDate.getText().toString() + " 00:00:00"));
                break;
            case R.id.tv_labout_enddate:
                dateStr = "end";
                chooseTime(TimeUtils.string2Millis(tvEndDate.getText().toString() + " 00:00:00"));
                break;
            default:
                break;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date date = new Date(millseconds);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        if (dateStr.equals("start")) {
            tvStartDate.setText(time);
        } else {
            tvEndDate.setText(time);
        }
        initData();
    }



    private void chooseTime(long currentTimeMillis) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("选择日期")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(true)
                .setMinMillseconds(currentTimeMillis - tenYears)
                .setMaxMillseconds(currentTimeMillis + tenYears)
                .setCurrentMillseconds(currentTimeMillis)
                .setThemeColor(getResources().getColor(R.color.blue))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.blue))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(getFragmentManager(), "ALL");

    }


}
