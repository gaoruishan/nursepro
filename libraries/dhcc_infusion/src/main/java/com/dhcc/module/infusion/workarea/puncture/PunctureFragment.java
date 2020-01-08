package com.dhcc.module.infusion.workarea.puncture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.BaseHelper;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.dosing.adapter.CommDosingAdapter;
import com.dhcc.module.infusion.workarea.puncture.api.PunctureApiManager;
import com.dhcc.res.infusion.CustomOnOffView;
import com.dhcc.res.infusion.CustomPatView;
import com.dhcc.res.infusion.CustomScanView;
import com.dhcc.res.infusion.CustomSelectView;
import com.dhcc.res.infusion.CustomSpeedView;

import java.util.ArrayList;
import java.util.List;

/**
 * com.dhcc.infusion.workarea.infusion_puncture
 * <p>
 * author Q
 * Date: 2019/3/7
 * Time:9:25
 */
public class PunctureFragment extends BaseInfusionFragment implements View.OnClickListener {

    public static final String STR_WAY_NO = "通道";
    private PunctureBean mBean;
    private RecyclerView rvPuncture;
    private CommDosingAdapter punctureAdapter;
    private BaseHelper helper;
    private String scanInfo1;
    private CustomSpeedView csvSpeed;
    private CustomSelectView csvSelect;
    private CustomPatView cpvPat;
    private CustomSelectView csvSelectParts;
    private CustomSelectView csvSelectTools;
    private CustomSelectView customSelectChannel;
    private CustomOnOffView customOnOff;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        helper = new BaseHelper(mContext);
        rvPuncture = RecyclerViewHelper.get(mContext, R.id.rv_puncture);
        CustomScanView customScan = mContainerChild.findViewById(R.id.custom_scan);
        csvSpeed = mContainerChild.findViewById(R.id.csv_speed);
        csvSelect = mContainerChild.findViewById(R.id.csv_select);
        cpvPat = mContainerChild.findViewById(R.id.cpv_pat);
        csvSelectParts = mContainerChild.findViewById(R.id.csv_select_parts);
        csvSelectTools = mContainerChild.findViewById(R.id.csv_select_tools);
        customSelectChannel = mContainerChild.findViewById(R.id.custom_select_channel);
        customOnOff = mContainerChild.findViewById(R.id.custom_on_off);
        customOnOff.setSelect(false);
        customOnOff.setShowSelectText("开启新通道", "开启新通道");
        helper.setOnClickListener(R.id.tv_ok, this);
        punctureAdapter = AdapterFactory.getCommDosingOrdList();
        rvPuncture.setAdapter(punctureAdapter);
        customScan.setTitle("请扫描瓶签/信息卡").setWarning("请您使用扫码设备，扫描药品瓶签/信息卡");
    }


    @Override
    protected void getScanOrdList() {

        getOrdList(scanInfo);
    }

    private void getOrdList(final String scanInfo) {
        String regNo = "";
        String curOeoreId = "";
        if (mBean != null) {
            regNo = mBean.getCurRegNo();
            curOeoreId = mBean.getCurOeoreId();
        }
        PunctureApiManager.getOrdList(regNo, curOeoreId, scanInfo, new CommonCallBack<PunctureBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
                helper.setVisible(R.id.ll_puncture_status, false);
            }

            @Override
            public void onSuccess(PunctureBean bean, String type) {
                if (checkListOeoreId(bean.getOrdList(), PROMPT_NO_ORD)) {
//                    return;
                }
                punctureAdapter.replaceData(bean.getOrdList());
                punctureAdapter.setCurrentScanInfo(scanInfo);
                // 隐藏扫码页
                helper.setVisible(R.id.custom_scan, false);
                setCustomPatViewData(cpvPat, bean.getPatInfo());

                csvSpeed.setSpeed(bean.getDefautSpeed());
                // 第一次扫码
                mBean = bean;
                if (scanInfo1 == null) {
                    scanInfo1 = scanInfo;
                    helper.setVisible(R.id.ll_puncture_status, false);
                } else if (!TextUtils.isEmpty(bean.getCurRegNo())
                        && !TextUtils.isEmpty(bean.getCurOeoreId())) {
                    //再次检查
                    if (!forIsContain(bean.getOrdList(), bean.getCurOeoreId())) {
                        return;
                    }
                    //显示穿刺情况
                    setToolbarCenterTitle("穿刺情况");
                    csvSelectParts.setTitle("穿刺部位").setSelectData(mContext, bean.getPunturePartListString(), null);
                    helper.setVisible(R.id.ll_puncture_status, true);
                    csvSelect.setTitle("预计结束时间");
                    csvSelect.setSelectTime(PunctureFragment.this.getFragmentManager(), bean.getDistantDate(), bean.getDistantTime(), null);
                    //穿刺工具
                    csvSelectTools.setVisibility(View.GONE);
                    if (bean.getPuntureToolList() != null) {
                        List<String> list = new ArrayList<>();
                        for (PunctureBean.PuntureToolListBean listBean : bean.getPuntureToolList()) {
                            list.add(listBean.getPuntureTool());
                        }
                        csvSelectTools.setVisibility(list.size() > 0 ? View.VISIBLE : View.GONE);
                        csvSelectTools.setTitle("穿刺工具").setSelectData(mContext, list, null);
                    }
                    if (f(R.id.rl_way) != null) {
                        f(R.id.rl_way).setVisibility(bean.getWayListString().size() > 0 ? View.VISIBLE : View.GONE);
                    }
                    customSelectChannel.setSelectData(mContext, bean.getWayListString(), null);
                    //customSelectChannel.setDisEnable(!bean.getIsCurrentWayNo(), "当前输液为通道1");
                    customOnOff.setDisEnable(!bean.getIsCurrentWayNo());
                }
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_puncture_infusion;
    }


    @Override
    public void onClick(View v) {
        // 保存
        if (v.getId() == R.id.tv_ok) {
            String part = csvSelectParts.getSelect();
            if (TextUtils.isEmpty(part)) {
                ToastUtils.showShort("请选择穿刺部位");
                return;
            }
            int speed = csvSpeed.getSpeed();
            if (speed <= 0) {
                ToastUtils.showShort("请调节滴速");
                return;
            }
            //穿刺工具
            String tool = csvSelectTools.getSelect();
            String select = csvSelect.getSelect();
            //通道
            String wayNo = customSelectChannel.getSelect().replace(STR_WAY_NO,"");
            String newWayFlag = customOnOff.isSelect() ? "1" : "";
            punctureOrd(part, tool, speed + "", select,wayNo,newWayFlag);
        }
    }

    private void punctureOrd(String part, String tool, String speed, String select,String wayNo,String newWayFlag) {
        String curOeoreId = "";
        if (mBean != null) {
            curOeoreId = mBean.getCurOeoreId();
        }
        PunctureApiManager.punctureOrd(curOeoreId, select, speed, part, tool,wayNo,newWayFlag, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                scanInfo1 = null;// 置空
                if (scanInfo != null) {
                    getOrdList(scanInfo);
                }
                setToolbarCenterTitle("穿刺");
                DialogFactory.showCommDialog(getActivity(), "穿刺成功", "", 0, null, true);
                onSuccessThings();
            }
        });
    }
}
