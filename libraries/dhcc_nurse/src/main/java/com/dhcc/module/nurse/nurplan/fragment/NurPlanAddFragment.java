package com.dhcc.module.nurse.nurplan.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.KeyboardUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.BundleData;
import com.dhcc.module.nurse.nurplan.NurPlanApiManager;
import com.dhcc.module.nurse.nurplan.adapter.NurPlanAddListAdapter;
import com.dhcc.module.nurse.nurplan.bean.NurPlanAddBean;
import com.dhcc.module.nurse.nurplan.bean.QuestionAddListBean;
import com.dhcc.res.infusion.CustomSheetListView;
import com.dhcc.res.infusion.bean.SheetListBean;

import java.util.List;

/**
 * 护理计划-新增
 * @author:gaoruishan
 * @date:202020-08-18/16:25
 * @email:grs0515@163.com
 */
public class NurPlanAddFragment extends BaseNurseFragment {
    private static final int TYPE_ADD_0 = 000;
    private static final int TYPE_ADD_1 = 111;
    private CustomSheetListView customSheet;
    private String qustionName = "";
    private String qustionTypes = "";
    private NurPlanAddListAdapter addListAdapter;
    private String dataArr;

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("新增护理问题");
        getQuestionList();
    }

    @Override
    protected void initViews() {
        super.initViews();
        episodeId = new BundleData(bundle).getEpisodeId();
        RecyclerView rvListPlan = RecyclerViewHelper.get(mContext, R.id.rv_list_plan);
        f(R.id.tv_bottom_cancel).setOnClickListener(this);
        f(R.id.tv_bottom_ok).setOnClickListener(this);
        customSheet = f(R.id.custom_sheet_list, CustomSheetListView.class);
        customSheet.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<SheetListBean> data = adapter.getData();
                qustionTypes = data.get(position).getCode();
                getQuestionList();
            }
        });

        EditText etSearch = f(R.id.et_search, EditText.class);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    //点击搜索要做的操作
                    qustionName = etSearch.getText().toString();
                    getQuestionList();
                    KeyboardUtils.hideSoftInput(getActivity());
                }
                return false;
            }
        });

        addListAdapter = AdapterFactory.getNurPlanAddListAdapter();
        rvListPlan.setAdapter(addListAdapter);

    }

    private void getQuestionList() {
        NurPlanApiManager.getQuestionList(episodeId, qustionName, qustionTypes, new CommonCallBack<NurPlanAddBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(NurPlanAddBean bean, String type) {
                customSheet.setDatas(bean.getQuestionTypeList());
                addListAdapter.setNewData(bean.getQuestionList());
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_bottom_cancel) {
            List<QuestionAddListBean> data = NurPlanAddBean.getOriginData(addListAdapter.getData());
            addListAdapter.replaceData(data);
        }
        //护理问题提交后只允许停止或撤销!  p4: 6|92|93
        if (v.getId() == R.id.tv_bottom_ok) {
            dataArr = NurPlanAddBean.getSaveQuestionRecordData(addListAdapter.getData());
            saveQuestionRecord();
        }
    }

    private void saveQuestionRecord() {
        NurPlanApiManager.saveQuestionRecord(episodeId, dataArr, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                onSuccessThings(bean);
                getQuestionList();
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_nur_plan_add;
    }
}
