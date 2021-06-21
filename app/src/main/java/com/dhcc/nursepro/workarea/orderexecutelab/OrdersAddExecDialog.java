package com.dhcc.nursepro.workarea.orderexecutelab;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.orderexecutelab.adapter.OrderAddDialogAdapter;
import com.dhcc.nursepro.workarea.orderexecutelab.bean.ScanResultBean;

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
    private TextView tvPopupPatinfo;
    private TextView tvPopupOrderexec;
    private TextView tvPopupOrdercancel;
    private TextView tvAddNum;
    private RecyclerView recyPopupChildOrderInfo;

    private String patInfo = "";
    private List<ScanResultBean.OrdersBean> addList = new ArrayList<>();
    private List<Integer> childNumList = new ArrayList<>();
    private OrderAddDialogAdapter adapter;

    public OrdersAddExecDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    public void setPatInfo(String patInfo) {
        this.patInfo = patInfo;
        if (tvPopupPatinfo != null) {
            tvPopupPatinfo.setText(patInfo);
        }
    }

    public List<ScanResultBean.OrdersBean> getAddList() {
        return addList;
    }

    public void setAddList(List<ScanResultBean.OrdersBean> addOrderList) {
        if (addList.contains(addOrderList.get(0))) {

        } else {
            addList.addAll(addOrderList);
        }
        if (adapter != null) {
            adapter.setNewData(addList);
            if (!childNumList.contains(addList.size())) {
                childNumList.add(addList.size());
            }
            adapter.setChildNumList(childNumList);
        }
        if (tvAddNum != null) {
            tvAddNum.setText("已扫描" + addList.size() + "条医嘱");
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
    public void dismiss() {
        super.dismiss();
        patInfo = "";
        addList = new ArrayList();
        childNumList = new ArrayList<>();
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
            if (!childNumList.contains(addList.size())) {
                childNumList.add(addList.size());
            }
            adapter.setChildNumList(childNumList);
        }

        if (tvAddNum != null) {
            tvAddNum.setText("已扫描" + addList.size() + "条医嘱");
        }
    }

    private void initEvent() {
        tvPopupOrderexec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(patInfo)) {
                    Toast.makeText(context, "请扫描患者腕带", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (addList.size() < 1) {
                    Toast.makeText(context, "请扫描检验条码", Toast.LENGTH_SHORT).show();
                    return;
                }
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
}
