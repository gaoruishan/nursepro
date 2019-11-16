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

    private PunctureBean mBean;
    private RecyclerView rvPuncture;
    private CommDosingAdapter punctureAdapter;
    private BaseHelper helper;
    private String scanInfo1;
    private CustomSpeedView csvSpeed;
    private CustomSelectView csvSelect;
    private CustomPatView cpvPat;
    private CustomSelectView csvSelectParts;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        helper = new BaseHelper(mContext);
        rvPuncture = RecyclerViewHelper.get(mContext, R.id.rv_puncture);
        csvSpeed = mContainerChild.findViewById(R.id.csv_speed);
        csvSelect = mContainerChild.findViewById(R.id.csv_select);
        cpvPat = mContainerChild.findViewById(R.id.cpv_pat);
        csvSelectParts = mContainerChild.findViewById(R.id.csv_select_parts);
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
                helper.setVisible(R.id.custom_scan, false);
                setCustomPatViewData(cpvPat,bean.getPatInfo());

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
                        List<String> list = new ArrayList<>();
                        for (PunctureBean.PunturePartListBean listBean : bean.getPunturePartList()) {
                            list.add(listBean.getPunturePart());
                        }
                        csvSelectParts.setTitle("穿刺部位").setSelectData(mContext, list, null);
                    }
                    helper.setVisible(R.id.ll_puncture_status, true);
                    csvSelect.setTitle("预计结束时间");
                    csvSelect.setSelectTime(PunctureFragment.this.getFragmentManager(), bean.getDistantDate(), bean.getDistantTime(), null);
//                    List<SheetListBean> listBeans = new ArrayList<>();
//                    SheetListBean sheetListBean = new SheetListBean();
//                    sheetListBean.setDesc("通道");
//                    SheetListBean sheetListBean1 = new SheetListBean();
//                    sheetListBean1.setDesc("通道1");
//                    SheetListBean sheetListBean2 = new SheetListBean();
//                    sheetListBean2.setDesc("通道2");
//                    listBeans.add(sheetListBean);
//                    listBeans.add(sheetListBean1);
//                    listBeans.add(sheetListBean2);
//                    csvSpeed.setChannelList(listBeans, null);
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
            String select = csvSelect.getSelect();
            punctureOrd(part, speed + "", select);
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
                onSuccessThings();
            }
        });
    }
}
