package com.dhcc.module.nurse.ca;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.SchDateTimeUtil;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.ca.bean.GetLoginQRBean;
import com.dhcc.module.nurse.ca.bean.GetLoginQRResultBean;
import com.dhcc.module.nurse.ca.bean.Login2Bean;
import com.dhcc.module.nurse.ca.bean.ScanLogonBean;
import com.dhcc.module.nurse.utils.DialogFactory;

/**
 * @author:gaoruishan
 * @date:202021/10/29/15:20
 * @email:grs0515@163.com
 */
public class CaLoginActivity extends BaseActivity {
    public static final int WHAT = 1;
    public boolean isCa = true;
    private GetLoginQRBean mLoginQRBean;
    protected Activity mContext;
    protected View vPin;
    protected LinearLayout llPin;
    protected String pinFlag;
    private EditText etLoginPin;
    private String pin;
    private boolean onlyCaLogin;
    private Dialog commDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_nurse);
        vPin = findViewById(R.id.v_pin);
        llPin = findViewById(R.id.ll_pin);
        etLoginPin = findViewById(R.id.et_login_pin);
        mContext = this;
        //清除ca数据
        GetLoginQRResultBean.clearInfo();
        mLoginQRBean = null;
        pin = null;
        pinFlag = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeMessages(WHAT);
        }
    }

    public void setPinFlag(String caFlag, String pinFlag) {
        this.pinFlag = pinFlag;
        if ("1".equals(caFlag)) {
            if ("1".equals(pinFlag)) {
                vPin.setVisibility(View.VISIBLE);
                llPin.setVisibility(View.VISIBLE);
            } else {
                //预加载图片
                GetLoginQR(false);
            }
        }

    }

    public boolean checkCA() {
        if (llPin != null && llPin.getVisibility() == View.VISIBLE) {
            if (etLoginPin != null) {
                pin = etLoginPin.getText().toString();
            }
            if (!TextUtils.isEmpty(pin)) {
                return false;
            }
            ToastUtils.showShort("请输入PIN码!");
            return true;
        }
        return false;
    }

    public void startCa() {
        if ("1".equals(pinFlag)) {
            PhonePinLogin();
        } else {
            if (mLoginQRBean != null) {
                showCaLoginDialog();
            } else {
                GetLoginQR(true);
            }
        }

    }

    public void showCaLoginDialog() {
        if (commDialog != null) {
            commDialog.cancel();
            commDialog = null;
        }
        commDialog = DialogFactory.showCaLogin(this, mLoginQRBean.getQrCode(), "CA登陆",null);
    }

    /**
     * PIN码登陆
     */
    private void PhonePinLogin() {
        String userCertCode = SPStaticUtils.getString(SharedPreference.USERCODE);
        if (TextUtils.isEmpty(pin)) {
            return;
        }
        CaAPIManager.PhonePinLogin(userCertCode, pin, new CommonCallBack<GetLoginQRResultBean>() {
            @Override
            public void onFail(String code, String msg) {


            }

            @Override
            public void onSuccess(GetLoginQRResultBean bean, String type) {
                bean.saveInfo();
                //保存pin标志
                SPStaticUtils.put(SharedPreference.CA_LOGIN_PIN_FLAG,"1");
                caLoginSuccess();
            }
        });
    }

    /**
     * 开启扫码
     */
    public void GetLoginQR(boolean openDialog) {
        CaAPIManager.GetLoginQR(new CommonCallBack<GetLoginQRBean>() {
            @Override
            public void onFail(String code, String msg) {
                //失败 不能CA登陆
                onlyCaLogin = false;
            }

            @Override
            public void onSuccess(GetLoginQRBean bean, String type) {
                mLoginQRBean = bean;
                SPStaticUtils.put(SharedPreference.CA_SIGN_GUID, bean.getSignGUID());
                //开启轮询
                handler.sendEmptyMessageDelayed(WHAT, CaSignUtil.DELAY_MILLIS);
                if (openDialog) {
                    showCaLoginDialog();
                }
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == WHAT) {
                refreshLogin();
            }
        }
    };

    private void refreshLogin() {
        Log.e(TAG, "(CaLoginActivity.java:63) refreshLogin");
        String signGuid = SPStaticUtils.getString(SharedPreference.CA_SIGN_GUID);
        CaAPIManager.GetLoginQRResult(signGuid,new CommonCallBack<GetLoginQRResultBean>() {
            @Override
            public void onFail(String code, String msg) {
                //失败 不能CA登陆
                onlyCaLogin = false;
            }

            @Override
            public void onSuccess(GetLoginQRResultBean bean, String type) {
                //授权状态，值域：FINISH 完成，REFUSE 拒绝，EXPIRE 超时，TOSIGN 待授权
                if ("FINISH".equals(bean.getSignStatus())) {
                    bean.saveInfo();
                    ToastUtils.showShort("扫码认证成功!");
                    //证书登陆
                    Login2();
                }
                if ("REFUSE".equals(bean.getSignStatus())) {
                    ToastUtils.showLong("用户拒绝登陆!");
                }
                if ("EXPIRE".equals(bean.getSignStatus())) {
                    ToastUtils.showLong("扫码超时!");
                }
                //每隔1.5s请求一次
                if ("TOSIGN".equals(bean.getSignStatus())) {
                    handler.sendEmptyMessageDelayed(WHAT, CaSignUtil.DELAY_MILLIS);
                }

            }
        });

    }

    private void Login2() {
        CaAPIManager.Login2(new CommonCallBack<Login2Bean>() {
            @Override
            public void onFail(String code, String msg) {
                //失败 不能CA登陆
                onlyCaLogin = false;
            }

            @Override
            public void onSuccess(Login2Bean bean, String type) {
                //保存用户ID
                if(!TextUtils.isEmpty(bean.getHisUserID())){
                    SPStaticUtils.put(SharedPreference.USERID, bean.getHisUserID());
                }
                SPStaticUtils.put(SharedPreference.CA_hisUserID, bean.getHisUserID());
                SPStaticUtils.put(SharedPreference.CA_hisUserName, bean.getHisUserName());
                if (onlyCaLogin) {
                    ScanLogon(bean.getHisUserName(), bean.getHisUserID());
                }else {
                    caLoginSuccess();
                }
            }
        });
    }

    private void ScanLogon(String hisUserName, String hisUserID) {
        CaAPIManager.ScanLogon(hisUserName, hisUserID, new CommonCallBack<ScanLogonBean>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(ScanLogonBean bean, String type) {
                SPStaticUtils.put(SharedPreference.USERCODE, bean.getUserCode());
                SPStaticUtils.put(SharedPreference.USERID, hisUserID);
                SPStaticUtils.put(SharedPreference.USERNAME, bean.getUserName());
                SchDateTimeUtil.putSchStartEndDateTime(bean.getSchStDateTime(),bean.getSchEnDateTime());
                if (bean.getLocs() != null && bean.getLocs().size() > 0) {
                    ScanLogonBean.LocsBean loginNurseInfo = bean.getLocs().get(0);
                    SPStaticUtils.put(SharedPreference.HOSPITALROWID, loginNurseInfo.getHospitalRowId());
                    SPStaticUtils.put(SharedPreference.GROUPID, loginNurseInfo.getGroupId());
                    SPStaticUtils.put(SharedPreference.GROUPDESC, loginNurseInfo.getGroupDesc());
                    SPStaticUtils.put(SharedPreference.LINKLOC, loginNurseInfo.getLinkLoc());
                    SPStaticUtils.put(SharedPreference.LOCID, loginNurseInfo.getLocId());
                    SPStaticUtils.put(SharedPreference.LOCDESC, loginNurseInfo.getLocDesc());
                    SPStaticUtils.put(SharedPreference.WARDID, loginNurseInfo.getWardId());
                }
                //成功
                caLoginSuccess();
            }
        });
    }

    public void caLoginSuccess() {
        ToastUtils.showShort("CA登陆成功!");
        //销毁登陆
        if (commDialog != null) {
            commDialog.cancel();
            commDialog = null;
        }
        ActivityUtils.startActivity(new Intent(Action.MainActivity));
        finish();
    }


    /**
     * CA登陆
     * @param view
     */
    public void caLogin(View view) {
        onlyCaLogin = true;
        GetLoginQR(true);
    }
}
