package com.dhcc.module.nurse.nurplan.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.DataCache;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.nurplan.NurPlanApiManager;
import com.dhcc.module.nurse.nurplan.PlanBundleData;
import com.dhcc.module.nurse.nurplan.bean.InterveFreqBean;
import com.dhcc.module.nurse.nurplan.bean.NurPlanAddInterveBean;
import com.dhcc.res.infusion.CustomSelectView;

import java.util.List;

/**
 * 护理措施新增
 * @author:gaoruishan
 * @date:202020-08-21/17:35
 * @email:grs0515@163.com
 */
public class NurPlanAddIntervFragment extends BaseNurseFragment {

    private String title;
    private TextView tvTitle;
    private CustomSelectView customSelectDate;

    private String planId;
    private String questSub;
    private String searchName;
    private String inRowId;//措施ID
    private String freqDr;//频次ID
    private String weekStr;


    private CustomSelectView customSelectInterve;
    private CustomSelectView customSelectFre;
    private InterveFreqBean json;
    private NurPlanAddInterveBean mBean;


    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_bottom_save) {
            NurPlanAddInterveBean.AddListBean addListBean = mBean.getAddList().get(customSelectInterve.getSelectPosition());
            inRowId = addListBean.getRowID();
            if(TextUtils.isEmpty(inRowId)){
                ToastUtils.showShort("请选择护理措施");
            	return;
            }
            InterveFreqBean.FreqListBean freqListBean = json.getFreqList().get(customSelectFre.getSelectPosition());
            freqDr = freqListBean.getId();
            if(TextUtils.isEmpty(freqDr)){
                ToastUtils.showShort("请选择执行频率");
            	return;
            }
            saveNewInterventionList();
        }
    }

    private void saveNewInterventionList() {
        NurPlanApiManager.saveNewInterventionList(planId, questSub, inRowId, freqDr, weekStr, customSelectDate.getSelectDate(), customSelectDate.getSelectTime(), new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                onSuccessThings(bean);
                finishDelayed();
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_nur_plan_add_interve;
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("新增护理措施");
        tvTitle.setText(title + "");
//        searchName = title;
        getInterventionList();
        json = DataCache.getJson(InterveFreqBean.class, InterveFreqBean.class.getSimpleName());
        if (json != null) {
            customSelectFre.setSelectData(mContext, InterveFreqBean.getSelectData(json.getFreqList()), null);
        }
    }

    @Override
    protected void initViews() {
        super.initViews();
        planId = new PlanBundleData(bundle).getPlanId();
        questSub = new PlanBundleData(bundle).getQuestSub();
        title = new PlanBundleData(bundle).getTitle();

        tvTitle = f(R.id.tv_title_txt, TextView.class);
        f(R.id.tv_bottom_save).setOnClickListener(this);
        customSelectInterve = f(R.id.custom_select_interve, CustomSelectView.class);
        customSelectInterve.setTitle("护理措施");
        customSelectFre = f(R.id.custom_select_fre, CustomSelectView.class);
        customSelectFre.setTitle("执行频率");
        customSelectDate = f(R.id.custom_select_date, CustomSelectView.class);
        customSelectDate.setTitle("时间选择");
        customSelectDate.setSelectTime(this.getFragmentManager(), null);
    }

    private void getInterventionList() {
        NurPlanApiManager.getInterventionList(planId, questSub, searchName, new CommonCallBack<NurPlanAddInterveBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(NurPlanAddInterveBean bean, String type) {
                mBean = bean;
                List<String> mSelectData = NurPlanAddInterveBean.getSelectData(bean.getAddList());
                customSelectInterve.setSelectData(mContext, mSelectData, null);

            }
        });
    }
}
