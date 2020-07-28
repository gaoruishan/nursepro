package com.dhcc.module.nurse.education.fragment;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.MessageEvent;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.CommDialog;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.BundleData;
import com.dhcc.module.nurse.education.HealthEduApiManager;
import com.dhcc.module.nurse.education.adapter.HealthEduDrugAdapter;
import com.dhcc.module.nurse.education.bean.EduOrdListBean;
import com.dhcc.module.nurse.education.bean.HealthEduBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomSelectView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-17/17:41
 * @email:grs0515@163.com
 */
public class HealthEduDrugFragment extends BaseNurseFragment {

    private String desc;
    private String episodeId;
    private TextView tvName;
    private TextView tvOk;
    private CustomSelectView customSelectDate;
    private HealthEduDrugAdapter eduDrugAdapter;
    private String selectDesc;

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("用药情况");
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HealthEduBean json = DataCache.getJson(HealthEduBean.class, HealthEduBean.class.getName());
        if (json != null) {
            //重组数据
            String eduDateTime = json.getCurDate();
            tvName.setText(desc + "");

            setSelectDateTime(customSelectDate, eduDateTime);
        }
        getDrugOrdList();
    }

    private void getDrugOrdList() {
        String endDate = customSelectDate.getSelect();
        long l = TimeUtils.string2Millis(endDate, YYYY_MM_DD);
        String startDate = TimeUtils.millis2String(l - CustomDateTimeView.ONE_DAY * 7, YYYY_MM_DD);
        HealthEduApiManager.getEduOrdList(episodeId, startDate, endDate, new CommonCallBack<EduOrdListBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                onFailThings();
            }

            @Override
            public void onSuccess(EduOrdListBean bean, String type) {
                hideLoadingTip();
                eduDrugAdapter.setNewData(bean.getOrdList());
            }
        });
    }

    @Override
    protected void initViews() {
        super.initViews();
        desc = new BundleData(bundle).getDesc();
        episodeId = new BundleData(bundle).getEpisodeId();

        tvName = f(R.id.tv_name, TextView.class);
        tvOk = f(R.id.tv_ok, TextView.class);
        tvOk.setOnClickListener(this);
        customSelectDate = f(R.id.custom_select_date, CustomSelectView.class);

        RecyclerView rvItem = RecyclerViewHelper.get(mContext, R.id.rv_item);
        eduDrugAdapter = AdapterFactory.getHealthEduDrugAdapter();
        rvItem.setAdapter(eduDrugAdapter);

        eduDrugAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                refreshNeedData(adapter, position);
            }
        });
        eduDrugAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                refreshNeedData(adapter, position);
            }
        });
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSaveDialog();
            }
        });
    }

    private void refreshNeedData(BaseQuickAdapter adapter, int position) {
        List<EduOrdListBean.OrdListBean> data = adapter.getData();
        selectDesc = "";
        for (int i = 0; i < data.size(); i++) {
            EduOrdListBean.OrdListBean bean = data.get(i);
            if (i == position) {
                bean.setSelect(!bean.isSelect());
            }
            if (bean.isSelect()) {
                selectDesc += bean.getArcimDesc() + "\n";
            }
        }
        eduDrugAdapter.notifyItemChanged(position);
    }

    private void showSaveDialog() {
        if (TextUtils.isEmpty(selectDesc)) {
            finish();
        }
        CommDialog.showCommDialog(mContext, "编辑内容是否需要保存", "取消", "保存", R.drawable.icon_popup_error_patient, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishFragment();
            }
        });
    }

    private void finishFragment() {
        EventBus.getDefault().post(new MessageEvent(selectDesc, MessageEvent.MessageType.HEALTH_EDU_SELECT_ORD));
        finish();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_ok) {
            if (TextUtils.isEmpty(selectDesc) || "\n".equals(selectDesc)) {
                ToastUtils.showShort("请选择医嘱");
                return;
            }
            finishFragment();
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_health_education_drug;
    }
}
