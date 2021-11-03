package com.dhcc.module.nurse.ca;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.SimpleCallBack;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.nurse.ca.bean.AuthSignBean;
import com.dhcc.module.nurse.ca.bean.GetLoginQRBean;
import com.dhcc.module.nurse.ca.bean.GetLoginQRResultBean;
import com.dhcc.module.nurse.ca.bean.GetTokenIsValidBean;
import com.dhcc.module.nurse.ca.bean.HashDataBean;
import com.dhcc.module.nurse.ca.bean.Login2Bean;
import com.dhcc.module.nurse.ca.bean.SignBean;
import com.dhcc.module.nurse.utils.DialogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务签名工具类
 * @author:gaoruishan
 * @date:202021/11/1/14:30
 * @email:grs0515@163.com
 */
public class CaSignUtil {
    //重新登录,再执行
    private static final String RE_LOGIN_TO_EXEC = "reLoginToExec";

    public static final int DELAY_MILLIS = 1500;
    private static String signGUID;
    //回调集合
    private static List<SimpleCallBack<String>> callBackList = new ArrayList<>();
    private static Map<String, SimpleCallBack<String>> callBackMap = new HashMap<>();
    private static Dialog commDialog;

    /**
     * 医嘱执行签名
     * @param activity
     * @param barCode
     * @param starttime
     * @param orderDesc
     * @param patInfo
     * @param scanFlag
     * @param oeoreId
     * @param execStatusCode
     */
    public static void execCaSign(Activity activity, String barCode, String starttime, String orderDesc, String patInfo, String scanFlag, String oeoreId, String execStatusCode, String auditUserCode) {
        String intData = "barCode=" + barCode + "^starttime=" + starttime
                + "^orderDesc=" + orderDesc + "^patInfo=" + patInfo
                + "^scanFlag=" + scanFlag + "^oeoreId=" + oeoreId
                + "^execStatusCode=" + execStatusCode;

        //单签
        String userId = SPStaticUtils.getString(SharedPreference.USERID);
        String mainSignCert = SPStaticUtils.getString(SharedPreference.CA_LOGIN_signCert);
        String certContainer = SPStaticUtils.getString(SharedPreference.CA_LOGIN_certContainer);
        String signToken = SPStaticUtils.getString(SharedPreference.CA_LOGIN_signToken);
        String userCertCode = SPStaticUtils.getString(SharedPreference.CA_LOGIN_userCertCode);
        String certNo = SPStaticUtils.getString(SharedPreference.CA_LOGIN_certNo);

        SimpleCallBack<String[]> hashCallBack = new SimpleCallBack<String[]>() {
            @Override
            public void call(String[] result, int type) {
                String ordItemsHashVal = result[0];

                startAuthSignToHis(ordItemsHashVal, intData, certContainer, signToken, userCertCode, certNo, oeoreId, userId, mainSignCert);
                //双签
                if (!TextUtils.isEmpty(auditUserCode)) {
                    //双签
                    String userId2 = "";
                    String mainSignCert2 = "";
                    String certContainer2 = "";
                    String signToken2 = "";
                    String userCertCode2 = "";
                    String certNo2 = "";

                    startAuthSignToHis(ordItemsHashVal, intData, certContainer2, signToken2, userCertCode2, certNo2, oeoreId, userId2, mainSignCert2);
                }
            }
        };

        //注册
        callBackMap.put(RE_LOGIN_TO_EXEC, new SimpleCallBack<String>() {
            @Override
            public void call(String result, int type) {
                Log.e("TAG","(CaSignUtil.java:95) RE_LOGIN_TO_EXEC");
                if (RE_LOGIN_TO_EXEC.equals(result)) {
                    checkToken(intData, hashCallBack);
                }
            }
        });
        checkToken(intData, hashCallBack);
    }

    public static void startAuthSignToHis(String ordItemsHashVal, String intData, String certContainer, String signToken, String userCertCode, String certNo, String oeoreId, String userId, String mainSignCert) {
        startAuthSign(intData, ordItemsHashVal, certContainer, signToken, userCertCode, certNo, new SimpleCallBack<String[]>() {
            @Override
            public void call(String[] result, int type) {
                String ordItemsHashVal = result[0];
                String mainSignValue = result[1];
                String signID = result[2];
                String mainSignTimeStamp = result[3];
                CaAPIManager.ExecCaSignData(ordItemsHashVal, signID, mainSignTimeStamp, oeoreId, userId, mainSignCert, mainSignValue, null);
            }
        });
    }

    /**
     * 护理病历签名
     * @param activity
     * @param episodeID
     * @param patName
     * @param emrCode
     * @param recId
     */
    public static void recordCaSign(Activity activity, String episodeID, String patName, String emrCode, String recId, String auditUserCode) {
        String intData = "episodeID=" + episodeID + "^patName=" + patName
                + "^emrCode=" + emrCode + "^recId=" + recId;
        //单签
        String curUserId = SPStaticUtils.getString(SharedPreference.USERID);
        String mainSignCert = SPStaticUtils.getString(SharedPreference.CA_LOGIN_signCert);
        String certContainer = SPStaticUtils.getString(SharedPreference.CA_LOGIN_certContainer);
        String signToken = SPStaticUtils.getString(SharedPreference.CA_LOGIN_signToken);
        String userCertCode = SPStaticUtils.getString(SharedPreference.CA_LOGIN_userCertCode);
        String certNo = SPStaticUtils.getString(SharedPreference.CA_LOGIN_certNo);
        //双签
        if (!TextUtils.isEmpty(auditUserCode)) {

        }
        SimpleCallBack<String[]> hashCallBack = new SimpleCallBack<String[]>() {
            @Override
            public void call(String[] result, int type) {
                String ordItemsHashVal = result[0];

                startAuthSign(intData, ordItemsHashVal, certContainer, signToken, userCertCode, certNo, new SimpleCallBack<String[]>() {
                    @Override
                    public void call(String[] result, int type) {
                        String ordItemsHashVal = result[0];
                        String mainSignValue = result[1];
                        String signID = result[2];
                        String mainSignTimeStamp = result[3];
//                        String ordItemsHashVal, String signID, String mainSignTimeStamp, String EpisodeID, String recId, String userId, String mainSignCert, String mainSignValue,
                        CaAPIManager.RecordCaSignData(ordItemsHashVal, signID, mainSignTimeStamp, episodeID, recId, curUserId, mainSignCert, mainSignValue, null);
                    }
                });
            }
        };
        checkToken(intData, hashCallBack);
    }

    /**
     * 检查token
     * @param intData
     */
    public static void checkToken(String intData, SimpleCallBack<String[]> callBack) {
        //先验证token
        boolean curUser = true;
        CaAPIManager.GetTokenIsValid(new CommonCallBack<GetTokenIsValidBean>() {
            @Override
            public void onFail(String code, String msg) {
                GetLoginQR(true, curUser);
            }

            @Override
            public void onSuccess(GetTokenIsValidBean bean, String type) {
                if ("true".equals(bean.getIsTokenValid())) {
                    //进行hash
                    hashData(intData, callBack);
                } else {
                    ToastUtils.showShort("ca签名token失效");
                    GetLoginQR(true, curUser);
                }
            }
        });
    }

    /**
     * 1,开启扫码
     */
    public static void GetLoginQR(boolean openDialog, boolean curUser) {
        signGUID = null;
        CaAPIManager.GetLoginQR(new CommonCallBack<GetLoginQRBean>() {
            @Override
            public void onFail(String code, String msg) {
            }

            @Override
            public void onSuccess(GetLoginQRBean bean, String type) {
                signGUID = bean.getSignGUID();
                //开启轮询
                Message message = handler.obtainMessage();
                message.what = 1;
                message.obj = curUser;
                handler.sendMessageDelayed(message, DELAY_MILLIS);
                if (openDialog) {
                    showCaLoginDialog(bean.getQrCode());
                }
            }
        });
    }

    public static void showCaLoginDialog(String qrCode) {
        if (commDialog != null) {
            commDialog.cancel();
            commDialog = null;
        }
        commDialog = DialogFactory.showCaLogin(ActivityUtils.getTopActivity(), qrCode, null);
    }

    //不停刷新
    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //
            if (msg.what == 1) {
                boolean curUser = false;
                if (msg.obj instanceof Boolean) {
                    curUser = (boolean) msg.obj;
                }
                refreshLogin(curUser);
            }
        }
    };

    /**
     * 2,每1.5刷新一下扫码结果
     * @param curUser
     */
    private static void refreshLogin(boolean curUser) {
        CaAPIManager.GetLoginQRResult(signGUID, new CommonCallBack<GetLoginQRResultBean>() {
            @Override
            public void onFail(String code, String msg) {
            }

            @Override
            public void onSuccess(GetLoginQRResultBean bean, String type) {
                //授权状态，值域：FINISH 完成，REFUSE 拒绝，EXPIRE 超时，TOSIGN 待授权
                if ("FINISH".equals(bean.getSignStatus())) {
                    if (curUser) {
                        bean.saveInfo();
                    }
                    //销毁登陆
                    if (commDialog != null) {
                        commDialog.cancel();
                        commDialog = null;
                    }
                    //证书登陆
                    Login2(curUser);
                }
                if ("REFUSE".equals(bean.getSignStatus())) {
                    ToastUtils.showLong("用户拒绝登陆!");
                }
                if ("EXPIRE".equals(bean.getSignStatus())) {
                    ToastUtils.showLong("扫码超时!");
                }
                //每隔1.5s请求一次
                if ("TOSIGN".equals(bean.getSignStatus())) {
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    message.obj = curUser;
                    handler.sendMessageDelayed(message, DELAY_MILLIS);
                }

            }
        });

    }

    /**
     * 3,扫码后,进行登录
     * @param curUser
     */
    private static void Login2(boolean curUser) {
        CaAPIManager.Login2(new CommonCallBack<Login2Bean>() {
            @Override
            public void onFail(String code, String msg) {
            }

            @Override
            public void onSuccess(Login2Bean bean, String type) {
                //保存用户ID
                if (curUser) {
                    if (!TextUtils.isEmpty(bean.getHisUserID())) {
                        SPStaticUtils.put(SharedPreference.USERID, bean.getHisUserID());
                    }
                    SPStaticUtils.put(SharedPreference.CA_hisUserID, bean.getHisUserID());
                    SPStaticUtils.put(SharedPreference.CA_hisUserName, bean.getHisUserName());

                    ToastUtils.showLong("重新登录成功!");
                    //回调
                    callBackMap.get(RE_LOGIN_TO_EXEC).call(RE_LOGIN_TO_EXEC,0);
                }
                //销毁登陆
                DialogFactory.dismiss();

            }
        });
    }

    /**
     * Hash数据
     * @param intData
     */
    private static void hashData(String intData, SimpleCallBack<String[]> callBack) {
        CaAPIManager.HashData(intData, new CommonCallBack<HashDataBean>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(HashDataBean bean, String type) {
                if (callBack != null) {
                    callBack.call(new String[]{bean.getHashData()}, 0);
                }
            }
        });
    }

    /**
     * 静默签名
     * @param intData
     */
    private static void startAuthSign(String intData, String hashData, String certContainer, String signToken, String userCertCode, String certNo, SimpleCallBack<String[]> callBack) {
        CaAPIManager.AuthSign(intData, certContainer, signToken, new CommonCallBack<AuthSignBean>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(AuthSignBean bean, String type) {
                if (!TextUtils.isEmpty(bean.getSignResult())) {
                    //验证签名
                    sign(hashData, bean.getSignResult(), userCertCode, certNo, callBack);
                } else {
                    ToastUtils.showShort("签名结果signResult为空!" + bean.getRetMsg());
                }
            }
        });
    }

    /**
     * 验证签名
     * @param hashData
     * @param signResult
     */
    private static void sign(String hashData, String signResult, String userCertCode, String certNo, SimpleCallBack<String[]> callBack) {
        CaAPIManager.Sign(hashData, signResult, userCertCode, certNo, new CommonCallBack<SignBean>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(SignBean bean, String type) {
                ToastUtils.showShort("签名成功!");
                if (callBack != null) {
                    String[] data = new String[]{
                            hashData, signResult, bean.getDigitalSignID(), bean.getTimeStamp()
                    };
                    callBack.call(data, 0);
                }
            }
        });
    }


}
