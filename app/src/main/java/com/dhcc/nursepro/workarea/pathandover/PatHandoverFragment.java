package com.dhcc.nursepro.workarea.pathandover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.pathandover.adapter.PatHandoverAdapter;
import com.dhcc.nursepro.workarea.pathandover.api.PatHandoverApiManager;
import com.dhcc.nursepro.workarea.pathandover.bean.GetConnectAndPatBean;
import com.dhcc.nursepro.workarea.pathandover.bean.GetConnectListBean;
import com.dhcc.nursepro.workarea.pathandover.bean.GetScanConnectBean;
import com.dhcc.nursepro.workarea.pathandover.bean.SaveConnectBean;
import com.dhcc.nursepro.workarea.pathandover.dialog.ConnectTypeDialog;
import com.dhcc.nursepro.workarea.pathandover.dialog.PatHandoverFilterPresenter;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * PatHandoverFragment
 * 患者交接
 *
 * @author DevLix126
 * created at 2021/1/13 10:53
 */
public class PatHandoverFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener {
    private RelativeLayout rlPathandoverScan;
    private TextView tvPathandoverWarning;
    private TextView tvPathandoverWarningtitle;
    private TextView tvPathandoverStartdate;
    private TextView tvPathandoverEnddate;

    private LinearLayout llPathandoverList;
    private RecyclerView recyPathandover;


    private List<GetConnectListBean.ConnectAllListBean> connectAllList;
    private PatHandoverAdapter patHandoverAdapter;
    private PatHandoverFilterPresenter presenter;
    private ConnectTypeDialog connectTypeDialog;

    private SPUtils spUtils = SPUtils.getInstance();
    private String regNo;
    private String datestr;
    private String startDate, endDate;
    private List<String> typeFilterStr = new ArrayList<>();
    private String typeFilter = "";
    private ImageView imgToolbarRightFilter;
    private List<String> connectType = new ArrayList<>();

    private String type = "";
    private String typeCode = "";
    private String parentTypeCode = "";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_patienthandover), 0xffffffff, 17);
        View viewright = View.inflate(getActivity(), R.layout.view_toolbar_img_right, null);
        imgToolbarRightFilter = viewright.findViewById(R.id.img_toolbar_right_filter);
        imgToolbarRightFilter.setVisibility(View.GONE);
        imgToolbarRightFilter.setImageDrawable(getResources().getDrawable(R.drawable.dhcc_filter_big_write));
        imgToolbarRightFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openPopWindow();
            }
        });
        ImageView imgToolbarRightAdd = viewright.findViewById(R.id.img_toolbar_right);
        imgToolbarRightAdd.setImageDrawable(getResources().getDrawable(R.drawable.dhcc_icon_add));
        imgToolbarRightAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regNo = "";
                getScanConnect();
            }
        });
        setToolbarRightCustomView(viewright);
        endDate = spUtils.getString(SharedPreference.CURDATETIME).substring(0, 10);
        long enDateMillion = TimeUtils.string2Millis(endDate + "  00:00:00");
        startDate = TimeUtils.date2String(TimeUtils.getDate(enDateMillion, -7L, TimeConstants.DAY)).substring(0, 10);

        initView(view);
        initAdapter();

        //添加筛选
        presenter = new PatHandoverFilterPresenter(getActivity());
        presenter.setTypeList(typeFilterStr);
        presenter.setOnSelectCallBackListener(new PatHandoverFilterPresenter.OnSelectCallBackListener() {
            @Override
            public void onSelect(String s) {
                typeFilter = s;
                initData();
            }
        });
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);

        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            regNo = bundle.getString("data");

            getScanConnect();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pat_handover, container, false);
    }

    private void getScanConnect() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        PatHandoverApiManager.getScanConnect(regNo, new PatHandoverApiManager.GetScanConnectCallback() {
            @Override
            public void onSuccess(GetScanConnectBean getScanConnectBean) {
                hideLoadingTip();
                String[] typeStr = getScanConnectBean.getConnectType().split("\\^");
                connectType.clear();
                connectType.addAll(Arrays.asList(typeStr));
                if (connectTypeDialog != null && connectTypeDialog.isShowing()) {
                    connectTypeDialog.dismiss();
                }
                connectTypeDialog = new ConnectTypeDialog(getActivity());
                connectTypeDialog.setTitleStr("请选择交接类型");
                if ("".equals(regNo)) {
                    connectTypeDialog.setRegNo("");
                    connectTypeDialog.setRegNoVisible(View.VISIBLE);
                    connectTypeDialog.setDialogType(1);
                    connectTypeDialog.setSure(View.VISIBLE, "确认", true);
                    connectTypeDialog.setCancelVisible(View.GONE);
                } else {
                    connectTypeDialog.setRegNo(regNo);
                    connectTypeDialog.setRegNoVisible(View.GONE);

                    if (StringUtils.isEmpty(getScanConnectBean.getNowNode())) {
                        connectTypeDialog.setDialogType(1);
                        connectTypeDialog.setSure(View.VISIBLE, "确认", true);
                        connectTypeDialog.setCancelVisible(View.GONE);
                    } else {
                        connectTypeDialog.setDialogType(2);
                        parentTypeCode = getScanConnectBean.getParentRec();
                        connectTypeDialog.setTitleStr("是否继续未完成交接？");
                        connectTypeDialog.setSelectType(getScanConnectBean.getNowNode().split(":")[0]);
                        connectTypeDialog.setSure(View.VISIBLE, "新建", false);
                        connectTypeDialog.setCancel(View.VISIBLE, "继续", true);
                    }
                }


                connectTypeDialog.setTypeList(connectType);

                connectTypeDialog.setSureOnclickListener(new ConnectTypeDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        if (StringUtils.isEmpty(connectTypeDialog.getRegNo())) {
                            showToast("请输入患者登记号");
                            return;
                        }

                        if (StringUtils.isEmpty(connectTypeDialog.getTypeCode())) {
                            showToast("请选择交接类型");
                            return;
                        }

                        regNo = connectTypeDialog.getRegNo();
                        type = connectTypeDialog.getSelectType();
                        typeCode = connectTypeDialog.getTypeCode();

                        saveConnect();
                        connectTypeDialog.dismiss();

                    }
                });

                connectTypeDialog.setCancelOnclickListener(new ConnectTypeDialog.onCancelOnclickListener() {
                    @Override
                    public void onCancelClick() {
                        regNo = connectTypeDialog.getRegNo();
                        type = connectTypeDialog.getSelectType();
                        typeCode = connectTypeDialog.getTypeCode();
                        getConnectAndPat();
                        connectTypeDialog.dismiss();
                    }
                });

                connectTypeDialog.show();

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void initView(View view) {
        rlPathandoverScan = view.findViewById(R.id.rl_pathandover_scan);
        tvPathandoverWarning = view.findViewById(R.id.tv_pathandover_warning);
        tvPathandoverWarningtitle = view.findViewById(R.id.tv_pathandover_warningtitle);
        tvPathandoverStartdate = view.findViewById(R.id.tv_pathandover_startdate);
        tvPathandoverEnddate = view.findViewById(R.id.tv_pathandover_enddate);
        tvPathandoverStartdate.setText(startDate);
        tvPathandoverEnddate.setText(endDate);
        tvPathandoverStartdate.setOnClickListener(this);
        tvPathandoverEnddate.setOnClickListener(this);
        llPathandoverList = view.findViewById(R.id.ll_pathandover_list);
        recyPathandover = view.findViewById(R.id.recy_pathandover);
        //提高展示效率
        recyPathandover.setHasFixedSize(true);
        //设置的布局管理
        recyPathandover.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {
        patHandoverAdapter = new PatHandoverAdapter(new ArrayList<>());
        patHandoverAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

//                if (view.getId() == R.id.tv_item_pathandover_type || view.getId() == R.id.ll_item_pathandover_patinfo) {
//                    regNo = "0000000089";
//                    typeCode = "1";
//                    getConnectAndPat();
//                } else
                if (view.getId() == R.id.ll_item_pathandover_record) {
                    if (patHandoverAdapter.getItem(position).getRecordShow() == 0) {
                        patHandoverAdapter.getItem(position).setRecordShow(1);
                    } else {
                        patHandoverAdapter.getItem(position).setRecordShow(0);
                    }
                    patHandoverAdapter.notifyDataSetChanged();
                } else if (view.getId() == R.id.ll_item_pathandover_rightmenu) {
                    ConnectTypeDialog delDialog = new ConnectTypeDialog(getActivity());
                    delDialog.setDialogType(3);
                    delDialog.setTitleStr("确定删除整张文书吗？");
                    delDialog.setRegNoVisible(View.GONE);
                    delDialog.setSure(View.VISIBLE, "确定", true);
                    delDialog.setCancel(View.VISIBLE, "取消", true);
                    delDialog.setSureOnclickListener(new ConnectTypeDialog.onSureOnclickListener() {
                        @Override
                        public void onSureClick() {
                            delDialog.dismiss();
                            showToast("请求后台");
                            showToast("删除成功");
                            showToast("刷新页面");
                            initData();
                        }
                    });
                    delDialog.setCancelOnclickListener(new ConnectTypeDialog.onCancelOnclickListener() {
                        @Override
                        public void onCancelClick() {
                            showToast("取消");
                            delDialog.dismiss();
                        }
                    });
                    delDialog.show();

                }
            }

        });
        recyPathandover.setAdapter(patHandoverAdapter);
    }

    private void initData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        PatHandoverApiManager.getConnectList(startDate, endDate, typeFilter, new PatHandoverApiManager.GetConnectListCallback() {
            @Override
            public void onSuccess(GetConnectListBean getConnectListBean) {
                hideLoadingTip();
                String[] typeStr = getConnectListBean.getConnectType().split("\\^");
                typeFilterStr.clear();
                typeFilterStr.addAll(Arrays.asList(typeStr));
                connectAllList = getConnectListBean.getConnectAllList();
                if (connectAllList == null || connectAllList.size() == 0) {
                    rlPathandoverScan.setVisibility(View.VISIBLE);
                    llPathandoverList.setVisibility(View.GONE);
                } else {
                    imgToolbarRightFilter.setVisibility(View.VISIBLE);
                    llPathandoverList.setVisibility(View.VISIBLE);
                    rlPathandoverScan.setVisibility(View.GONE);
                    patHandoverAdapter.setNewData(connectAllList);
                }
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void saveConnect() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        PatHandoverApiManager.saveConnect(regNo, typeCode, new PatHandoverApiManager.SaveConnectCallback() {
            @Override
            public void onSuccess(SaveConnectBean saveConnectBean) {
                hideLoadingTip();
                showToast(saveConnectBean.getMsg());
                parentTypeCode = saveConnectBean.getRecData();
                getConnectAndPat();
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void getConnectAndPat() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        PatHandoverApiManager.getConnectAndPat(regNo, typeCode, new PatHandoverApiManager.GetConnectAndPatCallback() {
            @Override
            public void onSuccess(GetConnectAndPatBean getConnectAndPatBean) {
                hideLoadingTip();
                Bundle bundle = new Bundle();
                bundle.putString("RegNo", regNo);
                bundle.putString("Type", type);
                bundle.putString("TypeCode", typeCode);
                bundle.putString("ParentTypeCode", parentTypeCode);
                bundle.putSerializable("GetConnectAndPatBean", getConnectAndPatBean);

                startFragment(PatHandoverCheckFragment.class, bundle);
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_pathandover_startdate:
                datestr = "start";
                pickTime(TimeUtils.string2Millis(tvPathandoverStartdate.getText().toString() + " 00:00:00"));
                break;
            case R.id.tv_pathandover_enddate:
                datestr = "end";
                pickTime(TimeUtils.string2Millis(tvPathandoverEnddate.getText().toString() + " 00:00:00"));
                break;
            default:
                break;
        }
    }


    private void pickTime(long currentTimeMillis) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setCyclic(false)
                .setMinMillseconds(currentTimeMillis - tenYears)
                .setMaxMillseconds(currentTimeMillis + tenYears)
                .setCurrentMillseconds(currentTimeMillis)
                .setThemeColor(getResources().getColor(R.color.blue))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.blue))
                .setWheelItemTextSize(12)
                .build();

        mDialogAll.show(getActivity().getSupportFragmentManager(), "ALL");
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date date = new Date(millseconds);
        //精确到day
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        if (datestr.equals("start")) {
            tvPathandoverStartdate.setText(time);
            startDate = time;
        } else {
            tvPathandoverEnddate.setText(time);
            endDate = time;
        }
        initData();
    }
}