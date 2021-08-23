package com.dhcc.module.nurse.outmanage.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.SchDateTimeUtil;
import com.blankj.utilcode.util.SPStaticUtils;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.BundleData;
import com.dhcc.module.nurse.outmanage.OutManageApiManager;
import com.dhcc.module.nurse.outmanage.bean.ConfigTraceBean;
import com.dhcc.module.nurse.outmanage.bean.OutManageBean;
import com.dhcc.module.nurse.outmanage.bean.OutManageReqParams;
import com.dhcc.res.util.SelectUtil;
import com.google.gson.Gson;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;

/**
 * 出/回病区,编辑
 * @author:gaoruishan
 * @date:202021/8/18/18:00
 * @email:grs0515@163.com
 */
public class OutManageInputFragment extends BaseNurseFragment {

    private String type;
    private List<ConfigTraceBean> configTraceList;
    private TextView tvTitle;
    private TextView tvDatetimeIn;
    private TextView tvDatetimeOut;
    private TextView tvType;
    private EditText etUser;
    private String outDateTime;
    private String inDateTime;
    private String user;
    private String id="";
    private String episodeID="";
    private String typeDR="";
    private EditText etRemark;

    @Override
    protected void initViews() {
        super.initViews();
        tvTitle = f(R.id.tv_title, TextView.class);
        etUser = f(R.id.et_user, EditText.class);
        etRemark = f(R.id.et_remark, EditText.class);
        tvDatetimeIn = f(R.id.tv_datetime_in, TextView.class);
        tvDatetimeIn.setOnClickListener(this);
        tvDatetimeOut = f(R.id.tv_datetime_out, TextView.class);
        tvDatetimeOut.setOnClickListener(this);
        tvType = f(R.id.tv_type, TextView.class);
        tvType.setOnClickListener(this);
        f(R.id.tv_ok).setOnClickListener(this);
        type = new BundleData(bundle).getType();
        desc = new BundleData(bundle).getDesc();
        id = new BundleData(bundle).getId();
        episodeID = new BundleData(bundle).getEpisodeId();
        String dateTime = new BundleData(bundle).getDateTime();
        String[] split = dateTime.split(",");
        if (split.length > 0) {
            outDateTime = split[0];
            if (split.length > 1) {
                inDateTime = split[1];
            }
        }
        configTraceList = DataCache.getJsonList(ConfigTraceBean.class, ConfigTraceBean.class.getSimpleName());
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        if (OutManageBean.TYPE_IN.equals(type)) {
            setToolbarCenterTitle("回病区");
        }
        if (OutManageBean.TYPE_OUT.equals(type)) {
            setToolbarCenterTitle("出病区");
            f(R.id.ll_in).setVisibility(View.GONE);
        }
        if (OutManageBean.TYPE_EDIT.equals(type)) {
            setToolbarCenterTitle("外出记录");
        }
        tvTitle.setText(desc);
        if(!TextUtils.isEmpty(user)){
            etUser.setText(user);
        }else {
            etUser.setText(SPStaticUtils.getString(SharedPreference.USERNAME));
        }
        if (configTraceList != null && configTraceList.size() > 0) {
            tvType.setText("" + configTraceList.get(0).getType());
            typeDR = configTraceList.get(0).getEvent();
        }
        if (!TextUtils.isEmpty(outDateTime)) {
            tvDatetimeOut.setText(outDateTime);
        }
        if (!TextUtils.isEmpty(inDateTime)) {
            tvDatetimeIn.setText(inDateTime);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        //出/回病区
        if (v.getId() == R.id.tv_datetime_in || v.getId() == R.id.tv_datetime_out) {
            selectDateTime(v);
        }
        //类型
        if (v.getId() == R.id.tv_type) {
            selectText();
        }
        //保存
        if (v.getId() == R.id.tv_ok) {
            OutManageReqParams params = new OutManageReqParams();
            params.id = id;
            params.episodeID = episodeID;
            params.typeDR = typeDR;
            params.outDateTime = tvDatetimeOut.getText().toString();
            params.entourage = etUser.getText().toString();
            params.returnDateTime = tvDatetimeIn.getText().toString();
            params.remarks = etRemark.getText().toString();
            String data = new Gson().toJson(params);
            OutManageApiManager.SaveOutManageInfo(data, getCommCallBack());
        }
    }


    private void selectDateTime(View v) {
        new SelectUtil().setSelectDateTime(getFragmentManager(), SchDateTimeUtil.getCurDateTime(), new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                if (v instanceof TextView) {
                    ((TextView) v).setText(SelectUtil.getFormatYYYY_MM_DD_HH_MM(millseconds));
                }
            }
        });
    }

    private void selectText() {
        if (configTraceList == null || configTraceList.size() == 0) {
            return;
        }

        List<String> mStringList = new ArrayList<>();
        for (ConfigTraceBean traceBean : configTraceList) {
            mStringList.add(traceBean.getType());
        }
        new SelectUtil().setSelectData(mContext, mStringList, new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                typeDR = configTraceList.get(index).getEvent();
                tvType.setText("" + item);
            }
        });
    }


    @Override
    protected int setLayout() {
        return R.layout.fragment_outmanage_input;
    }
}
