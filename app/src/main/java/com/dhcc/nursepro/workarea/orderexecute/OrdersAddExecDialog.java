package com.dhcc.nursepro.workarea.orderexecute;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.orderexecute.adapter.OrderAddDialogAdapter;
import com.dhcc.nursepro.workarea.orderexecute.bean.ScanResultBean;

import java.util.ArrayList;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea.orderexecute
 * <p>
 * author Q
 * Date: 2020/6/15
 * Time:17:03
 */
public class OrdersAddExecDialog extends Dialog {
    private Context context;
    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;
    private String patInfo;
    private TextView tvPopupPatinfo;
    private TextView tvPopupOrderexec;
    private TextView tvPopupOrdercancel;
    private TextView tvAddNum;
    private RecyclerView recyPopupChildOrderInfo;
    private OrderAddDialogAdapter adapter;
    private List<ScanResultBean> addList = new ArrayList<ScanResultBean>();
    public void setPatInfo(String patInfo) {
        this.patInfo = patInfo;
        if (tvPopupPatinfo != null) {
            tvPopupPatinfo.setText(patInfo);
        }
    }
    public void setAddString(ScanResultBean addString) {
        if (addList.contains(addString)){

        }else {
            addList.add(addString);
        }
        if (adapter!=null){
            adapter.setNewData(addList);
        }
        if (tvAddNum!=null){
            tvAddNum.setText("已扫描"+addList.size()+"条医嘱");
        }
    }

    public List<ScanResultBean> getAddList() {
        return addList;
    }

    public OrdersAddExecDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordersaddexec_dialog_layout);
        //按空白处不能取消动画
        //            setCanceledOnTouchOutside(false);
        setCanceledOnTouchOutside(true);
        //初始化界面控件
        initView();
//        //初始化adapter
        initAdapter();
//        //初始化界面数据
        initData();
//        //初始化界面控件的事件
        initEvent();
    }
    private void initView() {
        tvPopupPatinfo = findViewById(R.id.tv_popup_patinfo);
        tvPopupOrderexec = findViewById(R.id.tv_popup_orderexec);
        tvPopupOrdercancel = findViewById(R.id.tv_popup_ordercancel);
        recyPopupChildOrderInfo = findViewById(R.id.recy_popup_childOrderInfo);
        tvAddNum = findViewById(R.id.tv_scan_ordnum);
        //提高展示效率
        recyPopupChildOrderInfo.setHasFixedSize(true);
        //设置的布局管理
        recyPopupChildOrderInfo.setLayoutManager(new LinearLayoutManager(context));
    }

    private void initAdapter() {
        adapter = new OrderAddDialogAdapter(new ArrayList<>());
        recyPopupChildOrderInfo.setAdapter(adapter);
    }
    private void initData() {
        if (patInfo != null) {
            tvPopupPatinfo.setText(patInfo);
        }
        if (addList != null && addList.size() > 0) {
            adapter.setNewData(addList);
        }
        if (tvAddNum!=null){
            tvAddNum.setText("已扫描"+addList.size()+"条医嘱");
        }
    }

    private void initEvent() {
        tvPopupOrderexec.setOnClickListener(new View.OnClickListener() {
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
    public void dismiss() {
        super.dismiss();
        addList = new ArrayList();
    }
}
