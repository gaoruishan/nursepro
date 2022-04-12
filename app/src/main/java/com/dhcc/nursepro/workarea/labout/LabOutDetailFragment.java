package com.dhcc.nursepro.workarea.labout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.NurseAPI;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.labout.adapter.LabOutDetailAdapter;
import com.dhcc.nursepro.workarea.labout.api.LabOutApiManager;
import com.dhcc.nursepro.workarea.labout.bean.DelOrderBean;
import com.dhcc.nursepro.workarea.labout.bean.LabOutDetailBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LabOutDetailFragment extends BaseFragment {

    private View viewright;
    private TextView textView;
    private TextView tvLaboutScan;
    private TextView tvLaboutSend;
    private EditText etLaboutContainer;

    private RecyclerView recaddLabOut;
    private LabOutDetailAdapter labOutDetailAdapter;
    private List<LabOutDetailBean.DetailListBean> listBeans = new ArrayList<>();

    private String carryNo = "", carryLocDr = "", carryLabNo = "", DelSend = "", saveFlag = "", carryFlag = "";
    private SPUtils spUtils = SPUtils.getInstance();

    private LabOutVerifyDialog labOutVerifyDialog;
    private LabOutResultDialog labOutResultDialog;
    private String HGUserCode, HGPW;
    private String type = "0";
    //
    private String saveType="0";
    private Boolean ifHedui=false;
    private int heduiNum=0;
    private String preWaybillFlag = "";


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_laboutdetail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setHindBottm(50);
        Bundle bundle = getArguments();
        if (bundle != null) {
            carryNo = bundle.getString("CarryNo");
            saveType = bundle.getString("saveType");
            ifHedui = bundle.getString("ifHedui")==null||bundle.getString("ifHedui").equals("0")?false:true;
        }
        setToolbarCenterTitle(carryNo, 0xffffffff, 17);

        initview(view);
        initAdapter();
        initData();

    }

    private void initview(View view) {

        tvLaboutScan = view.findViewById(R.id.tv_layout_scannum);
        tvLaboutSend = view.findViewById(R.id.tv_layout_send);
        tvLaboutSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listBeans.size()>heduiNum){
                    showToast("未全部核对，无法发送");
                }else {
                    delOrExchange();
                }

            }
        });
        etLaboutContainer = view.findViewById(R.id.et_labout_container);

        recaddLabOut = view.findViewById(R.id.rec_addlabout);
        //提高展示效率
        recaddLabOut.setHasFixedSize(true);
        //设置的布局管理
        recaddLabOut.setLayoutManager(new LinearLayoutManager(getActivity()));
        //提高展示效率
        recaddLabOut.setHasFixedSize(true);
        //设置的布局管理
        recaddLabOut.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initAdapter() {

        labOutDetailAdapter = new LabOutDetailAdapter(new ArrayList<LabOutDetailBean.DetailListBean>());
        labOutDetailAdapter.setIfCheck(ifHedui);
        recaddLabOut.setAdapter(labOutDetailAdapter);
        labOutDetailAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.messagerightmenu) {
                    //                    DelSend = "del";
                    saveFlag = "0";
                    carryLocDr = listBeans.get(position).getCarryLoc();
                    carryLabNo = listBeans.get(position).getCarryLabNo();
                    initData();
                }
            }
        });
    }

    private void initData() {

        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap<String, String> map = new HashMap<>();
        map.put("carryNo", carryNo);
        map.put("transNo", carryNo);
        if (!saveFlag.equals("")) {
            map.put("labNo", carryLabNo);
            map.put("locDr", carryLocDr);
            map.put("saveFlag", saveFlag);
        }
        LabOutApiManager.getLabOutDetailMsg(map, NurseAPI.getLabOutDetail, new LabOutApiManager.getLabOutDetailCallBack() {
            @Override
            public void onSuccess(LabOutDetailBean labOutDetailBean) {
                hideLoadingTip();
                //                setToolbarCenterTitle("检验打包",0xffffffff,17);
                saveFlag = "";
                carryFlag = labOutDetailBean.getCarryFlag();
                if ("1".equals(carryFlag)) {
                    tvLaboutSend.setText("  撤销发送  ");
                } else {
                    tvLaboutSend.setText("  发送    ");
                }
                etLaboutContainer.setText(labOutDetailBean.getTransContainer()+"");

                listBeans = labOutDetailBean.getDetailList();
                preWaybillFlag = labOutDetailBean.getPreWaybillFlag();
                heduiNum = 0;
                for (int i = 0; i <listBeans.size() ; i++) {
                    if (listBeans.get(i).getAuditFlag().equals("1")){
                        heduiNum++;
                    }
                }
                String labNum = "标本数目："+heduiNum+"/"+listBeans.size();
                int deviceNu = labNum.indexOf("/");
                SpannableStringBuilder style=new SpannableStringBuilder(labNum);
                //设置指定位置textview的背景颜色
//                style.setSpan(new BackgroundColorSpan(Color.RED),2,5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                //设置指定位置文字的颜色
                style.setSpan(new ForegroundColorSpan(Color.GREEN),5,deviceNu,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                style.setSpan(new AbsoluteSizeSpan(50),5,labNum.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                style.setSpan(new ForegroundColorSpan(getActivity().getResources().getColor(R.color.blue)),deviceNu+1,labNum.length(),Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                tvLaboutScan.setText(style);
                labOutDetailAdapter.setNewData(listBeans);
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                saveFlag = "";
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void delOrExchange() {
        if (listBeans.size() == 0) {
            showToast("检验包为空，无法进行操作");
            return;
        }
        if (preWaybillFlag.equals("0")) {
            HGUserCode = "";
            HGPW = "";
            labOutSend();
            return;
        }

        //检验打包添加护士工号密码验证内容
        if (labOutVerifyDialog != null && labOutVerifyDialog.isShowing()) {
            labOutVerifyDialog.dismiss();
        }
        labOutVerifyDialog = new LabOutVerifyDialog(getActivity());
        labOutVerifyDialog.setSureOnclickListener(new LabOutVerifyDialog.onSureOnclickListener() {
            @Override
            public void onSureClick() {
                HGUserCode = labOutVerifyDialog.getHGUserCode();
                HGPW = labOutVerifyDialog.getHGPW();
                if ("0".equals(type) && (StringUtils.isEmpty(HGUserCode) || StringUtils.isEmpty(HGPW))) {
                    showToast("请填写护工工号、密码后再发送");
                } else if ("1".equals(type) && StringUtils.isEmpty(HGUserCode)) {
                    showToast("未扫描到护工工号，请重试\n或者取消，重新点击发送，手动输入护工工号和密码");
                } else {
                    labOutVerifyDialog.dismiss();
                    labOutSend();
                }
            }
        });
        labOutVerifyDialog.setCancelOnclickListener(new LabOutVerifyDialog.onCancelOnclickListener() {
            @Override
            public void onCancelClick() {
                labOutVerifyDialog.dismiss();
            }
        });
        labOutVerifyDialog.show();
    }

    private void labOutSend() {
        showLoadingTip(BaseActivity.LoadingType.FULL);

        HashMap<String, String> map = new HashMap<>();
        map.put("carryNo", carryNo);
        if ("0".equals(carryFlag)) {
            map.put("containerNo", etLaboutContainer.getText().toString());
            map.put("transUserId", spUtils.getString(SharedPreference.USERID));
            map.put("HGUserCode", HGUserCode);
            map.put("HGPW", HGPW);
            map.put("Type", type);
        }

        LabOutApiManager.delOrdMsg(map, NurseAPI.delOrExchange, new LabOutApiManager.delOrdCallBack() {
            @Override
            public void onSuccess(DelOrderBean delOrderBean) {
                hideLoadFailTip();
                labOutResultDialog = new LabOutResultDialog(getActivity());
                labOutResultDialog.setExecresult(delOrderBean.getMsg());
                labOutResultDialog.setImgId(R.drawable.icon_popup_sucess);
                labOutResultDialog.setSureVisible(View.GONE);
                labOutResultDialog.setCancleVisible(View.GONE);
                labOutResultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        labOutResultDialog.dismiss();
                        initData();
                    }
                }, 1000);

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                if (labOutResultDialog != null && labOutResultDialog.isShowing()) {
                    labOutResultDialog.dismiss();
                }
                labOutResultDialog = new LabOutResultDialog(getActivity());
                labOutResultDialog.setExecresult(msg);
                labOutResultDialog.setImgId(R.drawable.icon_popup_error_patient);
                labOutResultDialog.setSureVisible(View.VISIBLE);
                labOutResultDialog.setCancleVisible(View.GONE);
                labOutResultDialog.setSureOnclickListener(new LabOutResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        labOutResultDialog.dismiss();
                    }
                });
                labOutResultDialog.show();
            }
        });

    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Objects.requireNonNull(intent.getAction()).equals(Action.DEVICE_SCAN_CODE)) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            if (labOutVerifyDialog != null && labOutVerifyDialog.isShowing()) {
                type = "1";
                labOutVerifyDialog.setHGUserCode(bundle != null ? bundle.getString("data") : "");

            } else {
                carryLocDr = spUtils.getString(SharedPreference.LOCID);
                carryLabNo = bundle != null ? bundle.getString("data") : "";
                saveFlag = saveType;
                initData();
            }
        }

    }
}
