
package com.example.dhcc_nurlink.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.dhcc_nurlink.R;
import com.example.dhcc_nurlink.bean.PermissionBean;
import com.example.dhcc_nurlink.bean.SetDutyBean;
import com.example.dhcc_nurlink.webserviceapi.WebApiManager;


/**
 * com.dhcc.nursepro.workarea.orderexecute
 * <p>
 * author Q
 * Date: 2018/12/28
 * Time:14:32
 */
public class SettingDialog extends Dialog {
    private Context context;

    private View viewCancle,viewDeleteVoice,viewNobother,viewBed,viewVersion,viewWork,viewWake;



    public SettingDialog(Context context) {
        super(context, R.style.MyDialog2);
        this.context = context;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_dialog);
        //按空白处不能取消动画
        //            setCanceledOnTouchOutside(false);
        setCanceledOnTouchOutside(true);
        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }

    private void initView() {
        findViewById(R.id.rl_cancle).setOnClickListener(v -> dismiss());
        viewDeleteVoice = findViewById(R.id.view_deletevoice);
        viewDeleteVoice.setOnClickListener(v -> deleteVoiceCallback.clickVoice());
        viewNobother = findViewById(R.id.view_nobother);
        if (SPUtils.getInstance().getString(SharedPreference.NUR_LINK_NOBOTHERMODEL,"0").equals("1")){
            viewNobother.setSelected(true);
        }else {
            viewNobother.setSelected(false);
        }
        viewNobother.setOnClickListener(v -> setNobotherModel());

        viewBed = findViewById(R.id.view_setting_bed);
        viewBed.setOnClickListener(v -> deleteVoiceCallback.clickBed());
        viewVersion = findViewById(R.id.view_newversion);
        viewVersion.setOnClickListener(v -> deleteVoiceCallback.clickVersion());

        String dutyString = SPUtils.getInstance().getString(SharedPreference.NUR_LINK_WORKTYPE,"1");
        viewWork = findViewById(R.id.view_work);
        if (dutyString.equals("1")){
            viewWork.setSelected(true);
        }else {
            viewWork.setSelected(false);
        }
        viewWork.setOnClickListener(v -> setWork());
        getDutyinfo();

        viewWake=findViewById(R.id.view_wakevoice);
        viewWake.setSelected(SPUtils.getInstance().getBoolean(SharedPreference.VOICE_TO_VOIP_WAKEUP,true));
        viewWake.setOnClickListener(v -> setVoicWakeup());
    }
    private void setVoicWakeup(){
        SPUtils.getInstance().put(SharedPreference.VOICE_TO_VOIP_WAKEUP,!viewWake.isSelected());
        viewWake.setSelected(SPUtils.getInstance().getBoolean(SharedPreference.VOICE_TO_VOIP_WAKEUP));
    }
    private void getDutyinfo(){
        WebApiManager.getDutyinfo(SPUtils.getInstance().getString(SharedPreference.USERCODE),new CommonCallBack<SetDutyBean>() {
            @Override
            public void onFail(String code, String msg) {
                ToastUtils.showLong(msg);
            }

            @Override
            public void onSuccess(SetDutyBean bean, String type) {
                SPUtils.getInstance().put(SharedPreference.NUR_LINK_WORKTYPE,bean.getDutyFlag());
                if (bean.getDutyFlag().equals("1")){
                    viewWork.setSelected(true);
                }else {
                    viewWork.setSelected(false);
                }
            }
        });
    }

    private void setWork(){
        WebApiManager.setDutyUser(SPUtils.getInstance().getString(SharedPreference.USERCODE), viewWork.isSelected() ? "0" : "1", new CommonCallBack<PermissionBean>() {
            @Override
            public void onFail(String code, String msg) {
                ToastUtils.showLong(msg);
            }

            @Override
            public void onSuccess(PermissionBean bean, String type) {
//                if (viewWork.isSelected()){
//                    viewWork.setSelected(false);
//                    SPUtils.getInstance().put(SharedPreference.NUR_LINK_WORKTYPE,"0");
//                }else {
//                    viewWork.setSelected(true);
//                    SPUtils.getInstance().put(SharedPreference.NUR_LINK_WORKTYPE,"1");
//                }
                getDutyinfo();
            }
        });
    }
    private void initData() {
    }

    private void initEvent() {
    }

    private void setNobotherModel(){
        if (viewNobother.isSelected()){
            viewNobother.setSelected(false);
            SPUtils.getInstance().put(SharedPreference.NUR_LINK_NOBOTHERMODEL,"0");
        }else {
            viewNobother.setSelected(true);
            SPUtils.getInstance().put(SharedPreference.NUR_LINK_NOBOTHERMODEL,"1");
        }
    }

    public deleteVoiceCallback deleteVoiceCallback;
    public void setDeleteVoiceListner(deleteVoiceCallback deleteVoiceCallback){
        this.deleteVoiceCallback = deleteVoiceCallback;
    }
    public interface deleteVoiceCallback{
        void clickVoice();
        void clickBed();
        void clickVersion();
    }

}