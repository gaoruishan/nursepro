package com.dhcc.module.infusion.workarea.puncture;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.BaseHelper;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.dosing.adapter.CommDosingAdapter;
import com.dhcc.module.infusion.workarea.puncture.api.PunctureApiManager;
import com.dhcc.res.infusion.CustomPatView;
import com.dhcc.res.infusion.CustomSelectView;
import com.dhcc.res.infusion.CustomSpeedView;

import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

/**
 * com.dhcc.infusion.workarea.infusion_puncture
 * <p>
 * author Q
 * Date: 2019/3/7
 * Time:9:25
 */
public class PunctureFragment extends BaseInfusionFragment implements View.OnClickListener {

    private PunctureBean mBean;
    private RecyclerView rvPuncture;
    private CommDosingAdapter punctureAdapter;
    private BaseHelper helper;
    private String scanInfo1;
    private CustomSpeedView csvSpeed;
    private CustomSelectView csvSelect;
    private CustomPatView cpvPat;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarCenterTitle("穿刺");

        helper = new BaseHelper(mContext);
        rvPuncture = RecyclerViewHelper.get(mContext, R.id.rv_puncture);
        csvSpeed = mContainerChild.findViewById(R.id.csv_speed);
        csvSelect = mContainerChild.findViewById(R.id.csv_select);
        cpvPat = mContainerChild.findViewById(R.id.cpv_pat);
        helper.setOnClickListener(R.id.rl_punct_part, this);
        helper.setOnClickListener(R.id.tv_ok, this);
        punctureAdapter = AdapterFactory.getCommDosingOrdList();
        rvPuncture.setAdapter(punctureAdapter);
        f(R.id.tv_user, TextView.class).setText(SPUtils.getInstance().getString(SharedPreference.USERNAME));
    }


    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (scanInfo != null) {
            getOrdList(scanInfo);
        }
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
                helper.setVisible(R.id.csv, false);
                if (bean.getPatInfo() != null) {
                    cpvPat.setPatName(bean.getPatInfo().getPatName())
                            .setRegNo(bean.getPatInfo().getPatRegNo())
                            .setAge(bean.getPatInfo().getPatAge())
                            .setImgSexResource(bean.getPatInfo().getPatSexDrawable());
                }
                csvSpeed.setSpeed(bean.getDefautSpeed());
                // 第一次扫码
                mBean = bean;
                if (scanInfo1 == null) {
                    scanInfo1 = scanInfo;
                    helper.setVisible(R.id.ll_puncture_status, false);
                } else if (!TextUtils.isEmpty(bean.getCurRegNo())
                        && !TextUtils.isEmpty(bean.getCurOeoreId())) {
                    //再次检查
                    if (!forIsContain(bean.getOrdList(),scanInfo1)){
                       return;
                    }
                    //显示穿刺情况
                    setToolbarCenterTitle("穿刺情况");
                    if (bean.getPunturePartList() != null) {
                        helper.setTextData(R.id.tv_part, bean.getPunturePartList().get(0).getPunturePart());
                    }
                    helper.setVisible(R.id.ll_puncture_status, true);
                    csvSelect.setTitle("预计结束时间");
                    csvSelect.setSelectTime(PunctureFragment.this.getFragmentManager(), bean.getDistantDate(), bean.getDistantTime(), null);
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
            String part = helper.getTextData(R.id.tv_part);
            if (TextUtils.isEmpty(part)) {
                ToastUtils.showShort("请选择穿刺部位");
                return;
            }
            int speed = csvSpeed.getSpeed();
            if (speed <= 0) {
                ToastUtils.showShort("请调节滴速");
                return;
            }
            String select = csvSelect.getSelect();
            punctureOrd(part, speed + "", select);
        }
        // 选择部位
        if (v.getId() == R.id.rl_punct_part) {
            if (mBean == null) {
                return;
            }
            List<PunctureBean.PunturePartListBean> partList = mBean.getPunturePartList();
            String[] locDesc = new String[partList.size()];
            for (int i = 0; i < partList.size(); i++) {
                locDesc[i] = partList.get(i).getPunturePart();
            }
            OptionPicker picker = new OptionPicker(this.getActivity(), locDesc);
            picker.setCanceledOnTouchOutside(false);
            picker.setDividerRatio(WheelView.DividerConfig.FILL);
            picker.setSelectedIndex(0);
            picker.setCycleDisable(true);
            picker.setTextSize(20);
            picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                @Override
                public void onOptionPicked(int index, String item) {
                    helper.setTextData(R.id.tv_part, item);
                }
            });
            picker.show();
        }
    }

    private void punctureOrd(String part, String speed, String select) {
        String curOeoreId = "";
        if (mBean != null) {
            curOeoreId = mBean.getCurOeoreId();
        }
        PunctureApiManager.punctureOrd(curOeoreId, select, speed, part, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
                DialogFactory.showCommDialog(getActivity(), msg, "确定", R.drawable.icon_popup_error_patient, null);
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
//                helper.setVisible(R.id.csv, true);
                scanInfo1 = null;// 置空
                if (scanInfo != null) {
                    getOrdList(scanInfo);
                }
                setToolbarCenterTitle("穿刺");
                DialogFactory.showCommDialog(getActivity(), "穿刺成功", "", 0, null, true);
            }
        });
    }
}
