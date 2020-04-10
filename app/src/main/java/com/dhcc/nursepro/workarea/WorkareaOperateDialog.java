package com.dhcc.nursepro.workarea;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.workareaadapter.OrderExecuteOrderDialogAdapter;
import com.dhcc.nursepro.workarea.workareabean.ScanResultBean;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderExecOrderDialog
 * 创建自定义的dialog
 *
 * @author Devlix126
 * created at 2018/12/25 10:00
 */
public class WorkareaOperateDialog extends Dialog {
    private Context context;
    private TextView tvPopupPatinfo;
    private TextView tvJp;
    private RecyclerView recyPopupChildOrderInfo;
    private TextView tvPopupOrderinfoex;
    private LinearLayout llRemainingliquid;
    private EditText edPopupRemainingliquid;
    private LinearLayout llRemarkinfo;
    private EditText edPopupRemarkinfo;
    private ImageView imgRemarkinfoDrop;
    private TextView tvPopupSure;
    private TextView tvPopupOrdercancel;
    private ImageView imgCancle;

    private String patInfo = "";
    private List<ScanResultBean.OrdersBean> childOrders = new ArrayList<>();
    private String orderInfoEx = "";
    private String orderId = "";
    private String ifState = "";
    //view显隐控制
    private int ll1 = View.VISIBLE;
    private int ll2 = View.VISIBLE;
    private int llSpeed = View.GONE;
    private Boolean ifSpeedEdit = true;
    private int ifImgReasonShow = View.GONE;
    private EditText etSpeed;

    private OrderExecuteOrderDialogAdapter adapter;

    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;

    private LinearLayout llSpiSpeed;
    private Spinner spiSpeed,spiReason;
    private List spiList = new ArrayList<String>();
    private List spiReList = new ArrayList<String>();
    private String speedUnit = "";
    private String speed = "";
    private int ifJpShow = View.GONE;

    public void setJp(int ifJpShow){
        this.ifJpShow = ifJpShow;
        if (tvJp != null){
            tvJp.setVisibility(ifJpShow);
        }
    }
    public int getJp() {
        return ifJpShow;
    }

    public String getSpeed() {
        if (etSpeed != null){
            return etSpeed.getText().toString();
        }else {
            return speed;
        }
    }

    public void setSpeed(String speed) {
        this.speed = speed;
        if (etSpeed != null){
            etSpeed.setText(speed);
        }

    }

    public String getSpeedUnit() {
        return speedUnit;
    }

    public void setSpeedUnit(String speed) {
        this.speedUnit = speed;
    }

    public List getSpiList() {
        return spiList;
    }

    public void setSpiList(List spiList) {
        this.spiList = spiList;
    }

    public List getSpiReList() {
        return spiReList;
    }

    public void setSpiReList(List spiReList) {
        this.spiReList = spiReList;
        spiReList.add("删除");
    }

    public void setIfImgReasonShow(int ifImgReasonShow){
        this.ifImgReasonShow = ifImgReasonShow;
    }

    public WorkareaOperateDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getIfState() {
        return ifState;
    }

    public void setIfState(String ifState) {
        this.ifState = ifState;
    }

    public void setChildOrders(List<ScanResultBean.OrdersBean> childOrders) {
        this.childOrders = childOrders;
        if (childOrders != null && childOrders.size() > 0 && recyPopupChildOrderInfo != null && adapter != null) {
            adapter.setNewData(childOrders);
        }
    }

    public void setPatInfo(String patInfo) {
        this.patInfo = patInfo;
        if (patInfo != null && tvPopupPatinfo != null) {
            tvPopupPatinfo.setText(patInfo);
        }
    }

    public void setOrderInfoEx(String orderInfoEx) {
        this.orderInfoEx = orderInfoEx;
        if (orderInfoEx != null && tvPopupOrderinfoex != null) {
            tvPopupOrderinfoex.setText(orderInfoEx);
        }
    }

    public String getRemainingliquid() {
        if (edPopupRemainingliquid != null) {
            return edPopupRemainingliquid.getText().toString();
        }
        return "";
    }

    public String getRemarkinfo() {
        if (edPopupRemarkinfo != null) {
            return edPopupRemarkinfo.getText().toString();
        }
        return "";
    }

    public void setViewVisibility(int ll1, int ll2, int llSpeed,Boolean ifSpeedEdit) {
        this.ll1 = ll1;
        this.ll2 = ll2;
        this.llSpeed = llSpeed;
        this.ifSpeedEdit = ifSpeedEdit;
        if (llRemainingliquid != null && llRemarkinfo != null && llSpiSpeed != null) {
            llRemainingliquid.setVisibility(ll1);
            llRemarkinfo.setVisibility(ll2);
            llSpiSpeed.setVisibility(llSpeed);
            etSpeed.setEnabled(ifSpeedEdit);
            spiSpeed.setEnabled(ifSpeedEdit);
            spiSpeed.setSelected(ifSpeedEdit);
            if (ifSpeedEdit){
                etSpeed.setTextColor(Color.parseColor("#4A4A4A"));
            }else {
                etSpeed.setTextColor(Color.parseColor("#9B9B9B"));
            }
        }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workareaoperate_dialog_layout);
        //按空白处不能取消动画
        //            setCanceledOnTouchOutside(false);
        setCanceledOnTouchOutside(true);
        //初始化界面控件
        initView();
        //初始化adapter
        initAdapter();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }

    private void initView() {
        tvJp = findViewById(R.id.tv_jp);
        tvPopupPatinfo = findViewById(R.id.tv_popup_patinfo);
        recyPopupChildOrderInfo = findViewById(R.id.recy_popup_childOrderInfo);
        tvPopupOrderinfoex = findViewById(R.id.tv_popup_orderinfoex);
        llRemainingliquid = findViewById(R.id.ll_remainingliquid);
        edPopupRemainingliquid = findViewById(R.id.ed_popup_remainingliquid);
        llRemarkinfo = findViewById(R.id.ll_remarkinfo);
        edPopupRemarkinfo = findViewById(R.id.ed_popup_remarkinfo);
        imgRemarkinfoDrop = findViewById(R.id.img_remarkinfo_drop);
        tvPopupSure = findViewById(R.id.tv_popup_sure);
        tvPopupOrdercancel = findViewById(R.id.tv_popup_ordercancel);
        imgCancle = findViewById(R.id.img_cancle);
        imgCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //提高展示效率
        recyPopupChildOrderInfo.setHasFixedSize(true);
        //设置的布局管理
        recyPopupChildOrderInfo.setLayoutManager(new LinearLayoutManager(context));

        llSpiSpeed =findViewById(R.id.ll_speed);
        spiSpeed = findViewById(R.id.sp_speedunit);
        etSpeed = findViewById(R.id.et_speed);

        spiReason = findViewById(R.id.sp_reason);

    }

    private void initAdapter() {
        adapter = new OrderExecuteOrderDialogAdapter(new ArrayList<>());
        recyPopupChildOrderInfo.setAdapter(adapter);
    }

    private void initData() {

        if (patInfo != null) {
            tvPopupPatinfo.setText(patInfo);
        }

        if (childOrders != null && childOrders.size() > 0) {
            adapter.setNewData(childOrders);
        }

        if (orderInfoEx != null) {
            tvPopupOrderinfoex.setText(orderInfoEx);
        }

        llRemainingliquid.setVisibility(ll1);
        llRemarkinfo.setVisibility(ll2);
        llSpiSpeed.setVisibility(llSpeed);
        tvJp.setVisibility(ifJpShow);

        if (spiSpeed != null && spiList.size()>0){
//            List ls = new ArrayList<String>();
//            ls.add("滴/秒");
//            ls.add("滴/分");
            ArrayAdapter arr_adapter= new ArrayAdapter<String>(getContext(), R.layout.spinner_item, spiList);
            //设置样式
            arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spiSpeed.setAdapter(arr_adapter);
            if (spiList.size()>0){
                spiSpeed.setSelection(0);
            }

            for (int i = 0; i <spiList.size() ; i++) {
                if (speedUnit.equals(spiList.get(i))){
                    spiSpeed.setSelection(i);
                }
            }
            spiSpeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    spiSpeed.setSelection(position);
                    speedUnit = spiList.get(position)+"";
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if (spiReason != null && spiReList.size()>0){
//            List ls = new ArrayList<String>();
//            ls.add("滴/秒");
//            ls.add("滴/分");
            spiReason.setSelected(true);
            ArrayAdapter arr_adapter= new ArrayAdapter<String>(getContext(), R.layout.spinner_reason_item, spiReList);
            //设置样式
            arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spiReason.setAdapter(arr_adapter);
            if (spiReList.size()>0){
                spiReason.setSelection(spiReList.size()-1);
            }

//            for (int i = 0; i <spiReList.size() ; i++) {
//                if (speedUnit.equals(spiReList.get(i))){
//                    spiReason.setSelection(i);
//                }
//            }
            spiReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    edPopupRemarkinfo.setText(spiReList.get(position).toString());
                    if (spiReList.get(position).toString().equals("删除")){
                        edPopupRemarkinfo.setText("");
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }



        if (etSpeed != null){
            etSpeed.setText(speed);
            etSpeed.setEnabled(ifSpeedEdit);
            spiSpeed.setEnabled(ifSpeedEdit);
            spiSpeed.setSelected(ifSpeedEdit);
            if (ifSpeedEdit){
                etSpeed.setTextColor(Color.parseColor("#4A4A4A"));
            }else {
                etSpeed.setTextColor(Color.parseColor("#9B9B9B"));
            }
        }

        spiReason.setVisibility(ifImgReasonShow);

    }

    private void initEvent() {
        tvPopupSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
                }
            }
        });
        tvPopupOrdercancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelOnclickListener != null) {
                    cancelOnclickListener.onCancelClick();
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

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(false);
    }
}