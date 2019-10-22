package com.dhcc.module.health.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.bean.BroadcastListBean;
import com.base.commlibs.bean.LoginBean;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.AppUtil;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.SimpleCallBack;
import com.base.commlibs.utils.TransBroadcastUtil;
import com.base.commlibs.utils.UserUtil;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.health.R;
import com.dhcc.module.health.login.api.LoginApiManager;
import com.dhcc.res.nurse.CustomLoginEditText;
import com.dhcc.res.nurse.CustomSelectPicker;
import com.dhcc.res.nurse.bean.WardBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.LinkagePicker;

/**
 * 登陆页
 * @author:gaoruishan
 * @date:202019-10-21/14:30
 * @email:grs0515@163.com
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    public static final String LOGIN = "login";
    public static final String WARD = "ward";
    private CustomLoginEditText cletUser;
    private CustomLoginEditText cletPsw;
    private String scanFlag = "";
    private TextView tvLoginWard;
    private String logonWardId;
    private WardBean wardBean;
    private View llLoginRememberme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.grs.dhcc_res.R.layout.dhcc_activity_login);
        setToolbarType(ToolbarType.HIDE);
        UserUtil.checkWebIp();
        UserUtil.checkWebPath();
        UserUtil.setLoginLocType();
        boolean remem = SPUtils.getInstance().getBoolean(SharedPreference.REMEM);
        final String rememUserCode = SPUtils.getInstance().getString(SharedPreference.REMEM_USERCODE);

        cletUser = findViewById(com.grs.dhcc_res.R.id.clet_user);
        cletUser.setTextName("账户").setEditInputType(InputType.TYPE_CLASS_TEXT)
                .checkRememberMe(remem, rememUserCode)
                .setTextChangedListener(new SimpleCallBack<String>() {
                    @Override
                    public void call(String result, int type) {
                        if (rememUserCode.equals(result)) {
                            String locDesc = SPUtils.getInstance().getString(SharedPreference.LOCDESC);
                            logonWardId = SPUtils.getInstance().getString(SharedPreference.WARDID);
                            if (!TextUtils.isEmpty(locDesc)) {
                                tvLoginWard.setText(locDesc + " " + UserUtil.getWindowName());
                                tvLoginWard.setTextColor(Color.parseColor("#000000"));
                            }
                        } else {
                            tvLoginWard.setText("请选择登录病区");
                            tvLoginWard.setTextColor(Color.parseColor("#9b9b9b"));
                        }
                    }
                });

        cletPsw = findViewById(com.grs.dhcc_res.R.id.clet_psw);
        cletPsw.setTextName("密码").setEditInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
                .checkRememberMe(remem, rememUserCode);

        llLoginRememberme = findViewById(com.grs.dhcc_res.R.id.ll_login_rememberme);
        llLoginRememberme.setSelected(remem);
        llLoginRememberme.setOnClickListener(this);
        findViewById(R.id.tv_login_setip).setOnClickListener(this);
        TextView tvLogin = findViewById(R.id.tv_login_login);
        tvLogin.setOnClickListener(this);
        tvLogin.setSelected(true);
        tvLoginWard = findViewById(R.id.tv_login_ward);
        tvLoginWard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_login_rememberme) {
            boolean selected = v.isSelected();
            v.setSelected(!selected);
        }
        if (v.getId() == R.id.tv_login_setip) {
            final SetIPDialog showDialog = new SetIPDialog(this);
            showDialog.setTitle("结果");
            showDialog.setMessage(SPUtils.getInstance().getString(SharedPreference.WEBIP), SPUtils.getInstance().getString(SharedPreference.WEBPATH));
            showDialog.setYesOnclickListener("确定", new SetIPDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    String ip = showDialog.getIp();
                    if (AppUtil.isIP(ip)) {
                        UserUtil.setWebIpAndPath(ip, showDialog.getAddr());
                        //重写请求
                        // getBroadcastList();
                        showDialog.dismiss();
                    } else {
                        showToast("IP格式不正确，请重新输入");
                    }
                }
            });
            showDialog.show();
        }
        if (v.getId() == R.id.tv_login_login) {
            if (checkRequst()) {
                return;
            }
            if (tvLoginWard.getText().equals("请选择登录病区")) {
                initData(WARD);
            } else {
                initData(LOGIN);
            }
        }
        if (v.getId() == R.id.tv_login_ward) {
            if (checkRequst()) {
                return;
            }
            initData(WARD);
        }
    }

    private boolean checkRequst() {
        if (TextUtils.isEmpty(cletUser.getTextString())) {
            ToastUtils.showShort("请输入账户");
            return true;
        }
        if (TextUtils.isEmpty(cletPsw.getTextString())) {
            ToastUtils.showShort("请输入密码");
            return true;
        }
        return false;
    }

    private void initData(final String action) {
        showLoadingTip(LoadingType.FULL);
        LoginApiManager.getLogin(cletUser.getTextString(), cletPsw.getTextString(), logonWardId, scanFlag, new CommonCallBack<LoginBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
            }

            @Override
            public void onSuccess(LoginBean bean, String type) {
                hideLoadingTip();

                if (WARD.equalsIgnoreCase(action)) {
                    showWardPicker(bean.getLocs());
                }
                if (LOGIN.equalsIgnoreCase(action)) {
                    //保存
                    UserUtil.setUserInfo(bean);
                    DataCache.saveJson(bean, LoginBean.class.getSimpleName());
                    UserUtil.setRememberUserCode(llLoginRememberme.isSelected(), cletUser.getTextString());
                    List<BroadcastListBean> broadcastList = bean.getBroadcastList();
                    TransBroadcastUtil.setScanActionList(broadcastList);
                    startActivity(new Intent(Action.MainActivity));
                    finish();
                }
            }
        });
    }

    private void showWardPicker(final List<LoginBean.LocsBean> locs) {
        wardBean = new WardBean();
        final List<String> firstList = new ArrayList<>();
        final Map<String, List<String>> listMap = new HashMap<>();
        for (LoginBean.LocsBean loc : locs) {
            String locDesc = loc.getLocDesc();
            firstList.add(locDesc);
            List<String> list = new ArrayList<>();
            for (LoginBean.LocsBean.WinListBean winListBean : loc.getWinList()) {
                list.add(winListBean.getWinDesc());
            }
            listMap.put(locDesc, list);
        }
        wardBean.setFirstList(firstList);
        wardBean.setListMap(listMap);
        wardBean.setFirstString(SPUtils.getInstance().getString(SharedPreference.LOCDESC));
        wardBean.setSecondString(SPUtils.getInstance().getString(SharedPreference.WINDOWNAME));

        CustomSelectPicker.onWardPicker(this, wardBean, new LinkagePicker.OnStringPickListener() {
            @Override
            public void onPicked(String locDesc, String second, String third) {
                UserUtil.setLocWindowName(locDesc, second);
                for (LoginBean.LocsBean loc : locs) {
                    if (loc.getLocDesc().equals(locDesc)) {
                        UserUtil.setLocsUserInfo(cletUser.getTextString(), loc.getHospitalRowId(), loc.getGroupId(),
                                loc.getGroupDesc(), loc.getLinkLoc(), loc.getLocId(), loc.getLocDesc(), loc.getWardId());
                        logonWardId = loc.getWardId();
                    }
                }
                initData(LOGIN);
            }
        });
    }
}
