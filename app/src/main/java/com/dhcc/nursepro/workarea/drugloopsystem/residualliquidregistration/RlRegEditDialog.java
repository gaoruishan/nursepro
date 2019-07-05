package com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.adapter.RLRegPatOrderChildAdapter;
import com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.bean.RLPatOrdBean;

import java.util.ArrayList;
import java.util.List;

/**
 * RlRegEditDialog
 *
 * @author Devlix126
 * created at 2019/6/3 11:41
 */
public class RlRegEditDialog extends Dialog {
    private Context mContext;
    private TextView tvRlregeditBedcode;
    private TextView tvRlregeditPatname;
    private RecyclerView recyRlregeditOrders;
    private TextView tvRlregeditOrderexinfo;
    private EditText etRlregeditRledit;
    private TextView tvRlregeditRlunit;
    private TextView tvRlregeditType1;
    private TextView tvRlregeditType2;
    private TextView tvRlregeditSure;
    private TextView tvRlregeditCancel;

    private String bedCode;
    private String patName;
    private List<RLPatOrdBean.PatOrdListBean.PatOrdsBean.OeoreGroupBean> oeoreGroupBeanList;
    private String orderexinfo;
    private String rlCount;
    private String rlUnit;
    private String whereGo;
    private String type;
    private String[] rlWhereGo;

    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;
    private onUnitOnclickListener unitOnclickListener;

    private RLRegPatOrderChildAdapter patOrderChildAdapter;

    public RlRegEditDialog(Context context) {
        super(context, R.style.MyDialog);
        mContext = context;
    }

    public String getBedCode() {
        return bedCode;
    }

    public void setBedCode(String bedCode) {
        this.bedCode = bedCode;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public List<RLPatOrdBean.PatOrdListBean.PatOrdsBean.OeoreGroupBean> getOeoreGroupBeanList() {
        return oeoreGroupBeanList;
    }

    public void setOeoreGroupBeanList(List<RLPatOrdBean.PatOrdListBean.PatOrdsBean.OeoreGroupBean> oeoreGroupBeanList) {
        this.oeoreGroupBeanList = oeoreGroupBeanList;
    }

    public String getOrderexinfo() {
        return orderexinfo;
    }

    public void setOrderexinfo(String orderexinfo) {
        this.orderexinfo = orderexinfo;
    }

    public String getRlCount() {
        rlCount = etRlregeditRledit.getText().toString();
        return rlCount;
    }

    public void setRlCount(String rlCount) {
        this.rlCount = rlCount;
    }

    public String getRlUnit() {
        return rlUnit;
    }

    public void setRlUnit(String rlUnit) {
        this.rlUnit = rlUnit;
        if (tvRlregeditRlunit != null) {
            tvRlregeditRlunit.setText(rlUnit);
        }

    }

    public void setWhereGo(String whereGo) {
        this.whereGo = whereGo;
        rlWhereGo = whereGo.split("\\^");
    }

    public String getType() {
        return type;
    }

    /**
     * 设置确定按钮的显示内容和监听
     */
    public void setSureOnclickListener(onSureOnclickListener onSureOnclickListener) {

        this.sureOnclickListener = onSureOnclickListener;
    }

    /**
     * 设置取消按钮的显示内容和监听
     */
    public void setCancelOnclickListener(onCancelOnclickListener onCancelOnclickListener) {

        this.cancelOnclickListener = onCancelOnclickListener;
    }

    /**
     * 设置单位的显示内容和监听
     */
    public void setUnitOnclickListener(onUnitOnclickListener onUnitOnclickListener) {

        this.unitOnclickListener = onUnitOnclickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugrlregedit_dialog_layout);
        //按空白处不能取消动画
        //            setCanceledOnTouchOutside(false);
        setCanceledOnTouchOutside(true);
        //初始化界面控件
        initView();
        //初始化界面数据
        initAdapter();
        //初始化适配器
        initData();
        //初始化界面控件的事件
        initEvent();
    }

    private void initView() {
        tvRlregeditBedcode = findViewById(R.id.tv_rlregedit_bedcode);
        tvRlregeditPatname = findViewById(R.id.tv_rlregedit_patname);
        recyRlregeditOrders = findViewById(R.id.recy_rlregedit_orders);
        tvRlregeditOrderexinfo = findViewById(R.id.tv_rlregedit_orderexinfo);
        etRlregeditRledit = findViewById(R.id.et_rlregedit_rledit);
        tvRlregeditRlunit = findViewById(R.id.tv_rlregedit_rlunit);
        tvRlregeditType1 = findViewById(R.id.tv_rlregedit_type1);
        tvRlregeditType2 = findViewById(R.id.tv_rlregedit_type2);

        tvRlregeditType1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvRlregeditType1.setSelected(true);
                tvRlregeditType2.setSelected(false);
                type = rlWhereGo[0];
            }
        });
        tvRlregeditType2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvRlregeditType1.setSelected(false);
                tvRlregeditType2.setSelected(true);
                type = rlWhereGo[1];
            }
        });


        tvRlregeditSure = findViewById(R.id.tv_rlregedit_sure);
        tvRlregeditCancel = findViewById(R.id.tv_rlregedit_cancel);

        //提高展示效率
        recyRlregeditOrders.setHasFixedSize(true);
        //设置的布局管理
        recyRlregeditOrders.setLayoutManager(new LinearLayoutManager(mContext));
    }

    private void initAdapter() {
        patOrderChildAdapter = new RLRegPatOrderChildAdapter(new ArrayList<>());
        recyRlregeditOrders.setAdapter(patOrderChildAdapter);
    }

    private void initData() {
        if (!StringUtils.isEmpty(bedCode)) {
            tvRlregeditBedcode.setText(bedCode);
        }

        if (!StringUtils.isEmpty(patName)) {
            tvRlregeditPatname.setText(patName);
        }

        if (oeoreGroupBeanList != null && oeoreGroupBeanList.size() > 0) {
            patOrderChildAdapter.setNewData(oeoreGroupBeanList);
        }

        if (!StringUtils.isEmpty(orderexinfo)) {
            tvRlregeditOrderexinfo.setText(orderexinfo);
        }

        if (!StringUtils.isEmpty(rlCount)) {
            rlCount = rlCount.replaceAll("[^(0-9)]", "");
            etRlregeditRledit.setText(rlCount);
        }

        if (!StringUtils.isEmpty(rlUnit)) {
            tvRlregeditRlunit.setText(rlUnit);
        }

        if (!StringUtils.isEmpty(whereGo)) {
            tvRlregeditType1.setText(rlWhereGo[0]);
            tvRlregeditType2.setText(rlWhereGo[1]);
        }

        if (!StringUtils.isEmpty(type)) {
            if (type.equals(rlWhereGo[0])) {
                tvRlregeditType1.setSelected(true);
                tvRlregeditType2.setSelected(false);
            } else {
                tvRlregeditType1.setSelected(false);
                tvRlregeditType2.setSelected(true);
            }
        }

    }

    private void initEvent() {
        tvRlregeditSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
                }
            }
        });
        tvRlregeditCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelOnclickListener != null) {
                    cancelOnclickListener.onCancelClick();
                }
            }
        });

        tvRlregeditRlunit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unitOnclickListener != null) {
                    unitOnclickListener.onUnitClick();
                }
            }
        });
    }

    /**
     * 设置确定按钮被点击的接口
     */
    public interface onSureOnclickListener {
        void onSureClick();
    }

    /**
     * 设置取消按钮被点击的接口
     */
    public interface onCancelOnclickListener {
        void onCancelClick();
    }

    /**
     * 设置单位被点击的接口
     */
    public interface onUnitOnclickListener {
        void onUnitClick();
    }
}