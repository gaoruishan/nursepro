package com.dhcc.module.nurse.education.fragment;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commlibs.MessageEvent;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.BundleData;
import com.dhcc.module.nurse.education.HealthEduApiManager;
import com.dhcc.module.nurse.education.adapter.HealthEduItemAdapter;
import com.dhcc.module.nurse.education.bean.EduItemListBean;
import com.dhcc.module.nurse.education.bean.HealthEduBean;
import com.dhcc.module.nurse.education.SaveEduParams;
import com.dhcc.res.infusion.CustomSelectView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-17/17:41
 * @email:grs0515@163.com
 */
public class HealthEduExecuteFragment extends BaseNurseFragment {

    private CustomSelectView customSelectDate;
    private TextView tvName;
    private TextView tvOk;
    private HealthEduItemAdapter eduItemAdapter;
    private EditText etOther;

    @Override
    protected void initDatas() {
        super.initDatas();
        //注册事件总线
        EventBus.getDefault().register(this);

        setToolbarCenterTitle("宣教执行");
        tvName.setText(desc + "");

        setSelectDateTime(customSelectDate, eduDateTime);

        HealthEduBean json = DataCache.getJson(HealthEduBean.class, HealthEduBean.class.getName());
        if (json != null) {
            //重组数据
            List<EduItemListBean> eduItemList = json.getEduItemList();
            for (EduItemListBean bean : eduItemList) {
                bean.setSheetList(bean.getOptionListBeans());
            }
            eduItemAdapter.setNewData(eduItemList);
        }
    }

    @Override
    protected void initViews() {
        super.initViews();
        desc = new BundleData(bundle).getDesc();
        taskIds = new BundleData(bundle).getTaskIds();
        eduDateTime = new BundleData(bundle).getDateTime();
        eduRecordId = new BundleData(bundle).getEduRecordId();

        tvName = f(R.id.tv_name, TextView.class);
        tvOk = f(R.id.tv_ok, TextView.class);
        etOther = f(R.id.bl_et_other, EditText.class);
        tvOk.setOnClickListener(this);
        f(R.id.tv_drug).setOnClickListener(this);
        customSelectDate = f(R.id.custom_select_date, CustomSelectView.class);

        RecyclerView rvItem = RecyclerViewHelper.get(mContext, R.id.rv_item);
        eduItemAdapter = AdapterFactory.getHealthEduItemAdapter();
        rvItem.setAdapter(eduItemAdapter);
    }

    /**
     * 接收事件- 更新数据
     */
    @Override
    public void updateMessageEvent(MessageEvent event) {
        super.updateMessageEvent(event);
        if (event.getType() == MessageEvent.MessageType.HEALTH_EDU_SELECT_ORD) {
            String selectOrd = event.getMessage();
            etOther.setText(selectOrd);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        //保存
        if (v.getId() == R.id.tv_ok) {
            SaveEduParams.getInstance().EducationDate = customSelectDate.getSelect().substring(0, 10);
            SaveEduParams.getInstance().EducationTime = customSelectDate.getSelect().substring(11, 16);
            if (TextUtils.isEmpty(taskIds)) {
                taskIds = "[]";
            }
            SaveEduParams.getInstance().eduTaskIds = taskIds;
            SaveEduParams.getInstance().id = eduRecordId;

            //[{"id":"1","option":"手足/其他/本人/配偶\f444"},{"id":"4","option":"盲文/方言/身体语言"},{"id":"5","option":"其他\f6666"},{"id":"2","option":"不了解"},{"id":"6","option":"高中"}]
            List<SaveEduParams.EduTaskIdBean> eduTaskIds = new ArrayList<>();
            for (EduItemListBean datum : eduItemAdapter.getData()) {
                //选"其他"  必填
                if (datum.checkOtherData()) {
                    ToastUtils.showShort(datum.getName() + " 勾选'其他'不可为空");
                    return;
                }
                //将options 转为EduTaskIdBean对象
                SaveEduParams.EduTaskIdBean bean = datum.getEduTaskBean();
                if (TextUtils.isEmpty(bean.option)) {
                    ToastUtils.showShort(datum.getName() + " 不可为空");
                    return;
                }
                eduTaskIds.add(bean);
            }
            SaveEduParams.getInstance().EduItemList = new Gson().toJson(eduTaskIds);
            saveEdu();
        }
        //用药情况
        if (v.getId() == R.id.tv_drug) {
            BundleData bundle = new BundleData()
                    .setDesc(desc)
                    .setEpisodeId(SaveEduParams.getInstance().episodeId);
            startFragment(HealthEduDrugFragment.class, bundle.build());
        }
    }

    /**
     * HealthEduContentFragment 和 HealthEduExecuteFragment 都有保存参数
     */
    private void saveEdu() {
        SaveEduParams.getInstance().Content += "\n"+etOther.getText().toString();
        HealthEduApiManager.saveEdu(SaveEduParams.getInstance(), new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                onSuccessThings(bean);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //依次结束Fragment
                        finishFragment(HealthEduAddFragment.class);
                        finishFragment(HealthEduContentFragment.class);
                        finishFragment(HealthEduExecuteFragment.class);
                    }
                },3000);

            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_health_education_execute;
    }
}
