package com.dhcc.nursepro.workarea.plyout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.dhcc.nursepro.workarea.labout.LabOutResultDialog;
import com.dhcc.nursepro.workarea.labout.LabOutVerifyDialog;
import com.dhcc.nursepro.workarea.plyout.adapter.PlyOutDetailAdapter;
import com.dhcc.nursepro.workarea.plyout.api.PlyOutApiManager;
import com.dhcc.nursepro.workarea.plyout.bean.DelOrderBean;
import com.dhcc.nursepro.workarea.plyout.bean.PlyOutDetailBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PlyOutDetailFragment extends BaseFragment {

    private View viewright;
    private TextView textView;
    private TextView tvLaboutScan;
    private TextView tvLaboutSend;
    private EditText etLaboutContainer;

    private RecyclerView recaddLabOut;
    private PlyOutDetailAdapter labOutDetailAdapter;
    private List<PlyOutDetailBean.DetailListBean> listBeans = new ArrayList<>();

    private String carryNo = "", carryLocDr = "", carryLabNo = "", DelSend = "", saveFlag = "", carryFlag = "";
    private SPUtils spUtils = SPUtils.getInstance();

    private LabOutVerifyDialog labOutVerifyDialog;
    private LabOutResultDialog labOutResultDialog;
    private String HGUserCode, HGPW;
    private String type = "0";


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_laboutdetail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);

        Bundle bundle = getArguments();
        if (bundle != null) {
            carryNo = bundle.getString("CarryNo");
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
                delOrExchange();
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

        labOutDetailAdapter = new PlyOutDetailAdapter(new ArrayList<PlyOutDetailBean.DetailListBean>());
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

        HashMap<String, String> map = new HashMap<>();
        map.put("carryNo", carryNo);
        map.put("transNo", carryNo);
        if (!saveFlag.equals("")) {
            map.put("labNo", carryLabNo);
            map.put("locDr", carryLocDr);
            map.put("saveFlag", saveFlag);
        }
        PlyOutApiManager.getLabOutDetailMsg(map, NurseAPI.getPlyOutDetail, new PlyOutApiManager.getLabOutDetailCallBack() {
            @Override
            public void onSuccess(PlyOutDetailBean labOutDetailBean) {
                //                setToolbarCenterTitle("检验打包",0xffffffff,17);
                saveFlag = "";
                carryFlag = labOutDetailBean.getCarryFlag();
                if (carryFlag.equals("1")) {
                    tvLaboutSend.setText("  撤销发送  ");
                } else {
                    tvLaboutSend.setText("  发送    ");
                }
                etLaboutContainer.setText(labOutDetailBean.getTransContainer());

                listBeans = labOutDetailBean.getDetailList();
                tvLaboutScan.setText("已扫描 " + listBeans.size() + " 个");
                labOutDetailAdapter.setNewData(listBeans);
            }

            @Override
            public void onFail(String code, String msg) {
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

        PlyOutApiManager.delOrdMsg(map, NurseAPI.delOrExchangePly, new PlyOutApiManager.delOrdCallBack() {
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
                saveFlag = "1";
                initData();
            }
        }

    }
}
