package com.dhcc.nursepro.workarea.rfid;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.setting.SettingExitDialog;
import com.dhcc.nursepro.setting.adapter.SettingBedsGroupAdapter;
import com.dhcc.nursepro.workarea.rfid.adapter.RfidPatAdapter;
import com.dhcc.nursepro.workarea.rfid.api.RfidBindApiManager;
import com.dhcc.nursepro.workarea.rfid.bean.RfidPatBean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.rfid
 * <p>
 * author Q
 * Date: 2021/6/23
 * Time:9:39
 */
public class PatBindRfidFragment extends BaseFragment {
    private RecyclerView recPat;
    private RfidPatAdapter rfidPatAdapter;
    private RfidBindDialog rfidBindDialog;
    private String bindRegNo="";
    private Boolean isBind=false;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rfid_bind_pat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
//        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle("Rfid绑定患者");
        setToolbarBottomLineVisibility(false);
        //右上角保存按钮
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   保存   ");
        textView.setTextColor(getResources().getColor(R.color.blue_dark));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
//        setToolbarRightCustomViewSingleShow(viewright);
        if (isSingleModel) {
            hindMap();
        }

        initView(view);
        initAdapter();
        initData();
    }
    private void initView(View view){

        recPat = view.findViewById(R.id.recy_bind_rfid);
        //提高展示效率
        recPat.setHasFixedSize(true);
        //设置的布局管理
        recPat.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    }
    private void initAdapter(){
        rfidPatAdapter=new RfidPatAdapter(new ArrayList<>());
        recPat.setAdapter(rfidPatAdapter);
        rfidPatAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                rfidBindDialog = new RfidBindDialog(getActivity());
                rfidBindDialog.show();
                bindRegNo = rfidPatAdapter.getItem(position).getRegNo();
                isBind=rfidPatAdapter.getItem(position).getIfBind().equals("1");
                if (isBind){
                    rfidBindDialog.setTvMsg("当前患者已绑定:"+rfidPatAdapter.getItem(position).getName());
                    rfidBindDialog.setbtnShow(View.VISIBLE,View.VISIBLE);
                    rfidBindDialog.setSureOnclickListener(new SettingExitDialog.onSureOnclickListener() {
                        @Override
                        public void onSureClick() {
                            unbindRfid();
                        }

                        @Override
                        public void onCancleClick() {
                            rfidBindDialog.dismiss();
                        }
                    });
                }else {
                    rfidBindDialog.setTvMsg("请操作pda绑定当前患者:"+rfidPatAdapter.getItem(position).getName());
                    rfidBindDialog.setbtnShow(View.GONE,View.GONE);
                }
                rfidBindDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        bindRegNo="";
                        isBind=false;
                    }
                });
            }
        });
    }
    private void initData(){

        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap map = new HashMap();
        map.put("wardId", SPUtils.getInstance().getString(SharedPreference.WARDID));
        RfidBindApiManager.getRfidPatList(map, "GetRfidPatList", new RfidBindApiManager.getRfidPatListCallBack() {
            @Override
            public void onSuccess(RfidPatBean rfidPatBean) {
                hideLoadingTip();
                rfidPatAdapter.setNewData(rfidPatBean.getPatInfoList());
            }

            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
                hideLoadingTip();
            }
        });

    }
    private void bindRfid(String rfid){

        HashMap map = new HashMap();
        map.put("rfidId", rfid);
        map.put("regNo", bindRegNo);
        RfidBindApiManager.getRfidPatList(map, "BindRegNo", new RfidBindApiManager.getRfidPatListCallBack() {
            @Override
            public void onSuccess(RfidPatBean rfidPatBean) {
                showToast("绑定成功");
                if (rfidBindDialog!=null&&rfidBindDialog.isShowing()){
                    rfidBindDialog.dismiss();
                }
                initData();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
            }
        });

    }
    private void unbindRfid(){

        HashMap map = new HashMap();
        map.put("regNo", bindRegNo);
        RfidBindApiManager.getRfidPatList(map, "UnBindRegNo", new RfidBindApiManager.getRfidPatListCallBack() {
            @Override
            public void onSuccess(RfidPatBean rfidPatBean) {
                showToast("解绑成功");
                initData();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
            }
        });

    }


    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            String rfid = bundle.getString("data");
            if (rfidBindDialog!=null&&rfidBindDialog.isShowing()&&!bindRegNo.isEmpty()){
                if (isBind){

                }else {
                    bindRfid(rfid);
                }
            }else {
                showToast("请先选择用户再绑定");
            }
        }

    }
}