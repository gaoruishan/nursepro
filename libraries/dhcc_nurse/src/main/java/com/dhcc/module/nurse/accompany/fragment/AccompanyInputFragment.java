package com.dhcc.module.nurse.accompany.fragment;

import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.SchDateTimeUtil;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.accompany.AccompanyApiManager;
import com.dhcc.module.nurse.accompany.bean.AccompanyBean;
import com.dhcc.module.nurse.accompany.bean.AccompanyConfigBean;
import com.dhcc.module.nurse.education.BundleData;
import com.dhcc.res.infusion.CustomRadioGroupView;
import com.dhcc.res.infusion.CustomSelectView;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.OptionPicker;

/**
 * 体温录入
 * @author:gaoruishan
 * @date:202021/8/16/14:20
 * @email:grs0515@163.com
 */
public class AccompanyInputFragment extends BaseNurseFragment {

    private String position;
    private List<AccompanyConfigBean> configTEMPList;
    private List<AccompanyBean.AccompanyListBean> accompanyList;
    private CustomSelectView customSelectDate;
    private Integer pst;
    private LinearLayout llConfigList;

    @Override
    protected int setLayout() {
        return R.layout.fragment_accompany_input;
    }

    @Override
    protected void initViews() {
        super.initViews();
        position = new BundleData(bundle).getPosition();
        configTEMPList = new BundleData(bundle).getConfigTEMPList();
        accompanyList = new BundleData(bundle).getAccompanyList();
        customSelectDate = f(R.id.custom_select_date, CustomSelectView.class);
        llConfigList = f(R.id.ll_config_list, LinearLayout.class);
        f(R.id.tv_pre).setOnClickListener(this);
        f(R.id.tv_next).setOnClickListener(this);

    }

    @Override
    protected void initDatas() {

        super.initDatas();
        try {
            if (position != null) {
                pst = Integer.valueOf(position);
            }
        } catch (Exception e) {

        }
        addToolBarRightTextView("保存", R.color.white);
        customSelectDate.setTitle("时间选择");
        customSelectDate.setSelectTime(getFragmentManager(), SchDateTimeUtil.getCurDateTime(), new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
            }
        });
        LayoutInflater inflater = LayoutInflater.from(mContext);
        //添加组件
        for (AccompanyConfigBean configBean : configTEMPList) {
            if (!TextUtils.isEmpty(configBean.getType())) {
                //去掉 日期时间
                if (AccompanyConfigBean.TYPE_6.equals(configBean.getType()) || AccompanyConfigBean.TYPE_7.equals(configBean.getType())) {
                    continue;
                }
                //单行
                if (AccompanyConfigBean.TYPE_1.equals(configBean.getType())) {
                    View view = inflater.inflate(R.layout.cmp_type_1, null);
                    TextView tv = view.findViewById(R.id.tv_title);
                    EditText et = view.findViewById(R.id.et_value);

                    setEditCommConfig(configBean, et);
                    tv.setText(configBean.getTitle());
                    llConfigList.addView(view);
                }
                //多行
                if (AccompanyConfigBean.TYPE_2.equals(configBean.getType())) {
                    View view = inflater.inflate(R.layout.cmp_type_2, null);
                    TextView tv = view.findViewById(R.id.tv_title);
                    EditText et = view.findViewById(R.id.et_value);

                    setEditCommConfig(configBean, et);
                    tv.setText(configBean.getTitle());
                    llConfigList.addView(view);
                }
                //单选
                if (AccompanyConfigBean.TYPE_4.equals(configBean.getType())) {
                    View view = inflater.inflate(R.layout.cmp_type_4, null);
                    TextView tv = view.findViewById(R.id.tv_title);
                    CustomRadioGroupView customRadio = view.findViewById(R.id.custom_radio_group);
                    customRadio.setGroupData(configBean.getBuildSheetList());
                    tv.setText(configBean.getTitle());
                    llConfigList.addView(view);
                }
            }
        }

        //刷新
        refreshInfo();
    }

    private void setEditCommConfig(AccompanyConfigBean configBean, EditText et) {
        if ("N".equals(configBean.getInputType())) {
            et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if ("T".equals(configBean.getInputType())) {
            et.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    configBean.setInputValue(s.toString());
                }
            }
        });
    }

    private void refreshInfo() {
        for (int i = 0; i < accompanyList.size(); i++) {
            if (pst == i) {
                setToolbarCenterTitle(accompanyList.get(i).getBedCode() + "  " + accompanyList.get(i).getNCPAInfo1());
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        //保存
        if (v.getId() == R.id.tv_toolbar_right) {
            saveAccompanyData();
            return;
        }

        //上一位
        if (v.getId() == R.id.tv_pre) {
            if (pst == 0) {
                ToastUtils.showShort("已经是第一个!");
                return;
            }
            pst--;
        }
        //下一位
        if (v.getId() == R.id.tv_next) {
            if (pst == (accompanyList.size() + 1)) {
                ToastUtils.showShort("已经是最后一个!");
                return;
            }
            pst++;
        }
        //刷新
        refreshInfo();
    }

    private void saveAccompanyData() {
        Map<String, String> map = new HashMap<>();
        //循环取数据
        for (AccompanyConfigBean configBean : configTEMPList) {
            if (!TextUtils.isEmpty(configBean.getType())) {
                Log.e(TAG,"(AccompanyInputFragment.java:183) "+configBean.toString());
                //时间
                if (AccompanyConfigBean.TYPE_6.equals(configBean.getType())) {
                    map.put(configBean.getField(), customSelectDate.getSelectTime());
                }
                //日期
                if (AccompanyConfigBean.TYPE_7.equals(configBean.getType())) {
                    map.put(configBean.getField(), customSelectDate.getSelectDate());
                }
                //单行输入
                if (AccompanyConfigBean.TYPE_1.equals(configBean.getType())) {
                    map.put(configBean.getField(), configBean.getInputValue());
                }
                //单选
                if (AccompanyConfigBean.TYPE_4.equals(configBean.getType())) {
                    for (SheetListBean sheetListBean : configBean.getSheetList()) {
                        if (sheetListBean.isSelect()) {
                            map.put(configBean.getField(), sheetListBean.getDesc());
                            break;
                        }
                    }
                }
            }
        }


        String dataArr = new Gson().toJson(map);
        Log.e(TAG,"(AccompanyInputFragment.java:205) "+dataArr);
        String rowId = accompanyList.get(pst).getNCPARRowID();
        AccompanyApiManager.saveNCPAccompany(rowId, "", dataArr, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                onSuccessThings(bean);
            }
        });
    }


}
