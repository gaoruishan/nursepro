package com.dhcc.nursepro.workarea.labout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.MessageEvent;
import com.base.commlibs.NurseAPI;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.DateUtils;
import com.dhcc.nursepro.workarea.labout.adapter.LabOutAdapter;
import com.dhcc.nursepro.workarea.labout.api.LabOutApiManager;
import com.dhcc.nursepro.workarea.labout.bean.LabOutListAllBean;
import com.dhcc.nursepro.workarea.labout.bean.LabUnOutListBean;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecuteBean;
import com.dhcc.nursepro.workarea.plyout.PlyOutListFragment;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class LabOutListFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener {

    private RecyclerView recLabOut;
    private TextView tvType0, tvType1, tvType2, tvType3, tvType4, tvStartDate, tvEndDate, tvBl;
    private LinearLayout llEmpty;
    private View show0, show1, show2, show3, show4;
    private LabOutAdapter labOutAdapter;
    private List<LabOutListAllBean.LabOutListBean> listLabAll = new ArrayList<>(), listLabNow = new ArrayList<>();
    private String dateStr, CarrayCerate = "No", CarrayDel = "No", CarrayNo = "", TypeStr = "Type0";
    private String saveFlag = "";
    private List<LabOutListAllBean.TypeListBean> listType = new ArrayList<>();

    private SPUtils spUtils = SPUtils.getInstance();
    private Long timeNow = System.currentTimeMillis();
    private String startDate, endDate, pageNo = "1";
    private List<OrderExecuteBean.OrdersBean> labList;
    private boolean ifLoadMore;
    private Hashtable<String, TextView> typeView = new Hashtable<String, TextView>(); // EH view列表
    private Hashtable<String, Integer> typeIndex = new Hashtable<String, Integer>(); // EH type索引


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_laboutlist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_labout), 0xffffffff, 17);
        //右上角按钮"新建"
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("  切换 >> ");
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(PlyOutListFragment.class);
                finish();
            }
        });
        setToolbarRightCustomView(viewright);
        initview(view);
        initAdapter();
        GetUnOutLabData();
        //注册事件总线
        EventBus.getDefault().register(this);
    }

    /**
     * 接收事件- 更新数据
     *
     * @param event
     */
    @Subscribe
    public void onTitleClick(MessageEvent event) {
        Log.e(getClass().getSimpleName(), "updateText:" + event.getType());
        if (event.getType() == MessageEvent.MessageType.TOORBAR_CENTER_TITLE) {
            if (labList != null) {
                LabUnOutListPresenter presenter = new LabUnOutListPresenter(mActivity);
                presenter.openPopWindow(labList);
//                presenter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//                    @Override
//                    public void onLoadMoreRequested() {
//                        ifLoadMore = true;
//                        Log.e(TAG,"(LabOutListFragment.java:111) ");
//                        pageNo = Integer.valueOf(pageNo) + 1 + "";
//                        GetUnOutLabData();
//                    }
//                });
            }
        }
    }

    private void GetUnOutLabData() {
        startDate = tvStartDate.getText().toString();
        endDate = tvEndDate.getText().toString();
        LabOutApiManager.GetUnOutLabData(startDate, endDate, pageNo, new CommonCallBack<LabUnOutListBean>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(LabUnOutListBean bean, String type) {
                setToolbarCenterTitle(getString(R.string.title_labout) + "(" + bean.getLabNum() + ")", 0xffffffff, 17);
                if ("1".equals(pageNo)) {
                    labList = bean.getLabList();
                } else {
                    labList.addAll(bean.getLabList());
                }
            }
        });
    }

    private void initData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> map = new HashMap<>();
        map.put("stdate", tvStartDate.getText().toString());
        map.put("enddate", tvEndDate.getText().toString());
        map.put("locId", spUtils.getString(SharedPreference.LOCID));
        map.put("saveFlag", saveFlag);
        if (CarrayCerate.equals("Yes")) {
            map.put("userId", spUtils.getString(SharedPreference.USERID));
            if (TypeStr.equals("Type0")) {
                map.put("preFlag", "1");
            }
            map.put("userId", spUtils.getString(SharedPreference.USERID));
            if (CarrayDel.equals("Yes")) {
                map.put("carryNo", CarrayNo);
            }
        }
        if (listType.size() == 0) {
            tvType0.setVisibility(View.GONE);
        }
        LabOutApiManager.getLabOutListMsg(map, NurseAPI.getLabOutList, new LabOutApiManager.getLabOutCallBack() {
            @Override
            public void onSuccess(LabOutListAllBean labOutListAllBean) {
                hideLoadFailTip();
                CarrayCerate = "No";
                CarrayDel = "No";
                saveFlag = "";
                List<LabOutListAllBean.TypeListBean> type = labOutListAllBean.getTypeList();
                if (listType.size() == 0 && type != null && type.size() > 0) {
                    listType = type;
                    // EH 2021-10-15 预建单和建单位置固定，其余位置数量不定
                    for (int i = 0; i < listType.size(); i++) {
                        String listCode = listType.get(i).getCode();
                        String listDesc = listType.get(i).getDesc();
                        String listName = "";
                        TextView listView = null;
                        if ("B".equals(listCode)) {
                            listView = tvType0;
                            listName = "Type0";
                        } else if ("C".equals(listCode)) {
                            listView = tvType1;
                            listName = "Type1";
                        } else {
                            for (java.util.Iterator<String> iterator = typeView.keySet().iterator(); iterator.hasNext(); ) {
                                String listNameOption = iterator.next();
                                int j = Integer.parseInt(listNameOption.replace("Type", ""));
                                if (j > 1 && !typeIndex.containsKey(listNameOption)) {
                                    listName = listNameOption;
                                    listView = typeView.get(listName);
                                }
                            }
                        }
                        if (!"".equals(listName)) {
                            listView.setText(listDesc);
                            typeIndex.put(listName, i);
                        }
                    }
                    for (java.util.Iterator<String> iterator = typeView.keySet().iterator(); iterator.hasNext(); ) {
                        String listName = iterator.next();
                        TextView listView = typeView.get(listName);
                        listView.setVisibility(!typeIndex.containsKey(listName) ? View.GONE : View.VISIBLE);
                    }
                }

                listLabAll = labOutListAllBean.getLabOutList();
                getLabOutList();
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                CarrayCerate = "No";
                CarrayDel = "No";
                saveFlag = "";
                showToast("error" + code + ":" + msg);
            }
        });

    }

    private void setTopFilterSelect(View view) {
        tvType0.setSelected(view == tvType0);
        tvType1.setSelected(view == tvType1);
        tvType2.setSelected(view == tvType2);
        tvType3.setSelected(view == tvType3);
        tvType4.setSelected(view == tvType4);
    }

    private void showgone(View view) {
        show0.setBackgroundColor(getResources().getColor(R.color.blue_light));
        show1.setBackgroundColor(getResources().getColor(R.color.blue_light));
        show2.setBackgroundColor(getResources().getColor(R.color.blue_light));
        show3.setBackgroundColor(getResources().getColor(R.color.blue_light));
        show4.setBackgroundColor(getResources().getColor(R.color.blue_light));
        view.setBackgroundColor(getResources().getColor(R.color.blue));
    }

    private void getLabOutList() {
        listLabNow = new ArrayList<>();
        if (!typeIndex.containsKey(TypeStr)) return;
        int index = typeIndex.get(TypeStr);
        String typeDesc = listType.get(index).getDesc();
        for (int i = 0; i < listLabAll.size(); i++) {
            if (listLabAll.get(i).getStatus().equals(typeDesc)) {
                listLabNow.add(listLabAll.get(i));
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
        tvType0 = view.findViewById(R.id.tv_labout_type0);
        tvType0.setOnClickListener(this);
        tvType1 = view.findViewById(R.id.tv_labout_type1);
        tvType1.setOnClickListener(this);
        tvType2 = view.findViewById(R.id.tv_labout_type2);
        tvType2.setOnClickListener(this);
        tvType3 = view.findViewById(R.id.tv_labout_type3);
        tvType3.setOnClickListener(this);
        tvType4 = view.findViewById(R.id.tv_labout_type4);
        tvType4.setOnClickListener(this);
        for (int j = 0; j <= 4; j++) {
            int id = getResources().getIdentifier("tv_labout_type" + j, "id", "com.dhcc.nursepro");
            typeView.put("Type" + j, view.findViewById(id));
        }
        tvStartDate = view.findViewById(R.id.tv_labout_startdate);
        tvStartDate.setOnClickListener(this);
        tvEndDate = view.findViewById(R.id.tv_labout_enddate);
        tvEndDate.setOnClickListener(this);

        tvStartDate.setText(DateUtils.getDateTimeAgo(spUtils.getString(SharedPreference.CURDATETIME), 1).substring(0, 10));
        tvEndDate.setText(spUtils.getString(SharedPreference.CURDATETIME).substring(0, 10));
        show0 = view.findViewById(R.id.view_labout_show0);
        show1 = view.findViewById(R.id.view_labout_show1);
        show2 = view.findViewById(R.id.view_labout_show2);
        show3 = view.findViewById(R.id.view_labout_show3);
        show4 = view.findViewById(R.id.view_labout_show4);

        setTopFilterSelect(tvType0);
        showgone(show0);

        recLabOut = view.findViewById(R.id.recy_labout);
        //提高展示效率
        recLabOut.setHasFixedSize(true);
        //设置的布局管理
        recLabOut.setLayoutManager(new LinearLayoutManager(getActivity()));
        tvBl = view.findViewById(R.id.tv_bingli);
        tvBl.setOnClickListener(this);
        tvBl.setText("检验单");
    }

    private void initAdapter() {

        listType = new ArrayList<LabOutListAllBean.TypeListBean>();
        labOutAdapter = new LabOutAdapter(new ArrayList<LabOutListAllBean.LabOutListBean>());
        recLabOut.setAdapter(labOutAdapter);

        labOutAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_lapack_del) {
                    CarrayCerate = "Yes";
                    CarrayDel = "Yes";
                    saveFlag = "0";
                    CarrayNo = listLabNow.get(position).getCarryNo();
                    initData();
                } else if (view.getId() == R.id.messagecontentll) {
                    String carryNodetail = listLabNow.get(position).getCarryNo();
                    Bundle bundle = new Bundle();
                    bundle.putString("CarryNo", carryNodetail);
                    bundle.putString("saveType", "1");
                    bundle.putString("ifHedui", typeIndex.containsKey("Type0") ? "1" : "0");
                    startFragment(LabOutDetailFragment.class, bundle);
                } else if (view.getId() == R.id.tv_lapack_hedui) {
                    String carryNodetail = listLabNow.get(position).getCarryNo();
                    Bundle bundle = new Bundle();
                    bundle.putString("CarryNo", carryNodetail);
                    bundle.putString("saveType", "2");
                    bundle.putString("ifHedui", "1");
                    startFragment(LabOutDetailFragment.class, bundle);
                }

            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_labout_type0:
                TypeStr = "Type0";
                setTopFilterSelect(tvType0);
                showgone(show0);
                getLabOutList();
                break;
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
            case R.id.tv_bingli:
                //                startFragment(PatEventsDetailFragment.class);
                if (System.currentTimeMillis() - timeNow > 1500) {
                    CarrayCerate = "Yes";
                    saveFlag = "1";
                    if (TypeStr.equals("Type0")) {
                        setTopFilterSelect(tvType0);
                        showgone(show0);
                        TypeStr = "Type0";
                    } else {
                        setTopFilterSelect(tvType1);
                        showgone(show1);
                        TypeStr = "Type1";
                    }
                    getLabOutList();
                    initData();
                    timeNow = System.currentTimeMillis();
                } else {
                    showToast("不可频繁建单，请稍后建单");
                }

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
        GetUnOutLabData();
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
