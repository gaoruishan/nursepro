package com.dhcc.nursepro.workarea.nurrecordnew;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecordnew.adapter.VSDataAdapter;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.VitalSignDataBean;

import java.util.ArrayList;
import java.util.List;

public class NurRecordVSDialog extends Dialog {
    private Context mContext;
    private LinearLayout llVsdataTitle;
    private RecyclerView recyVsdata;
    private TextView tvPopupSure;

    private List<List<VitalSignDataBean>> vsDataList;
    private VSDataAdapter vsDataAdapter;
    private int vsDataNum;

    private onSureOnclickListener sureOnclickListener;


    public NurRecordVSDialog(Context context) {
        super(context, R.style.MyDialog);
        mContext = context;
    }

    public void setVsDataList(List<List<VitalSignDataBean>> vsDataList) {
        this.vsDataList = vsDataList;
    }

    public int getVsDataNum() {
        return vsDataNum;
    }

    /**
     * 设置确定按钮的显示内容和监听
     */
    public void setSureOnclickListener(onSureOnclickListener onSureOnclickListener) {

        this.sureOnclickListener = onSureOnclickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nurrecordvs_dialog_layout);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
        initAdapter();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }

    private void initView() {
        llVsdataTitle = findViewById(R.id.ll_vsdata_title);
        recyVsdata = findViewById(R.id.recy_vsdata);
        recyVsdata.setHasFixedSize(true);
        recyVsdata.setLayoutManager(new LinearLayoutManager(mContext));
        tvPopupSure = findViewById(R.id.tv_popup_sure);

    }

    private void initAdapter() {
        vsDataAdapter = new VSDataAdapter(new ArrayList<>());
        vsDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                vsDataAdapter.setSelectNum(position);
                vsDataAdapter.notifyDataSetChanged();
                vsDataNum = position;
            }
        });
        recyVsdata.setAdapter(vsDataAdapter);
    }

    private void initData() {
        if (vsDataList != null && vsDataList.size() > 0) {
            initTitle(vsDataList.get(0));
            vsDataAdapter.setNewData(vsDataList);
        }
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
    }

    private void initTitle(List<VitalSignDataBean> vitalSignDataBeans) {
        llVsdataTitle.removeAllViews();
        int width = ConvertUtils.dp2px(80);
        int height = ConvertUtils.dp2px(60);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        for (int i = 0; i < vitalSignDataBeans.size(); i++) {
            TextView textView = new TextView(getContext());
            textView.setText(vitalSignDataBeans.get(i).getDesc());
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(mContext.getResources().getColor(R.color.white));
            textView.setTextSize(14);
            llVsdataTitle.addView(textView);
        }
    }

    /**
     * 设置确定按钮被点击的接口
     */
    public interface onSureOnclickListener {
        void onSureClick();
    }
}
