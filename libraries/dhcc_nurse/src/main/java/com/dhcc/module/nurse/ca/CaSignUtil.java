package com.dhcc.module.nurse.ca;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.base.commlibs.MessageEvent;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommResult;
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

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
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
    //双签-第二个用户
    private static final String DOUBLE_USER_SIGN = "doubleUserSign";

    public static final int DELAY_MILLIS = 1200;
    public static final int WHAT = 1;
    public static final String STR_RE_LOGIN = "重新CA登陆";
    public static final String STR_DOUBLE_LOGIN = "第二个用户CA登陆";
    private static String signGUID;
    //回调集合
    private static Map<String, SimpleCallBack<String>> callBackMap = new HashMap<>();
    private static Dialog commDialog;
    private static final String USERID2 = "USERID2";
    private static final String CA_LOGIN2_certCN = "CA_LOGIN2_certCN";
    private static final String CA_LOGIN2_certContainer = "CA_LOGIN2_certContainer";
    private static final String CA_LOGIN2_certDN = "CA_LOGIN2_certDN";
    private static final String CA_LOGIN2_certNo = "CA_LOGIN2_certNo";
    private static final String CA_LOGIN2_expireTime = "CA_LOGIN2_expireTime";
    private static final String CA_LOGIN2_signCert = "CA_LOGIN2_signCert";
    private static final String CA_LOGIN2_signStatus = "CA_LOGIN2_signStatus";
    private static final String CA_LOGIN2_signToken = "CA_LOGIN2_signToken";
    private static final String CA_LOGIN2_userCertCode = "CA_LOGIN2_userCertCode";
    private static boolean doubleSign = false;

    public static boolean isDoubleSign() {
        return doubleSign;
    }

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
                doubleSign = !TextUtils.isEmpty(auditUserCode);
                startExecAuthSignToHis(ordItemsHashVal, intData, certContainer, signToken, userCertCode, certNo, oeoreId, userId, mainSignCert);
                //双签
                if (!TextUtils.isEmpty(auditUserCode)) {
                    GetLoginQR(true, false);
                    //注册双签
                    callBackMap.put(DOUBLE_USER_SIGN, new SimpleCallBack<String>() {
                        @Override
                        public void call(String result, int type) {
                            Log.e("TAG", "(CaSignUtil.java:95) DOUBLE_USER_SIGN");
                            String userId2 = SPStaticUtils.getString(USERID2);
                            String mainSignCert2 = SPStaticUtils.getString(CA_LOGIN2_signCert);
                            String certContainer2 = SPStaticUtils.getString(CA_LOGIN2_certContainer);
                            String signToken2 = SPStaticUtils.getString(CA_LOGIN2_signToken);
                            String userCertCode2 = SPStaticUtils.getString(CA_LOGIN2_userCertCode);
                            String certNo2 = SPStaticUtils.getString(CA_LOGIN2_certNo);
                            startExecAuthSignToHis(ordItemsHashVal, intData, certContainer2, signToken2, userCertCode2, certNo2, oeoreId, userId2, mainSignCert2);
                        }
                    });

                }
            }
        };

        //注册重新登录
        callBackMap.put(RE_LOGIN_TO_EXEC, new SimpleCallBack<String>() {
            @Override
            public void call(String result, int type) {
                Log.e("TAG", "(CaSignUtil.java:114) RE_LOGIN_TO_EXEC");
                if (RE_LOGIN_TO_EXEC.equals(result)) {
                    checkToken(intData, hashCallBack);
                }
            }
        });
        checkToken(intData, hashCallBack);
    }

    public static void startExecAuthSignToHis(String ordItemsHashVal, String intData, String certContainer, String signToken, String userCertCode, String certNo, String oeoreId, String userId, String mainSignCert) {
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

    public static void startRecordAuthSignToHis(String ordItemsHashVal, String intData, String certContainer, String signToken, String userCertCode, String certNo, String episodeID, String userId, String mainSignCert, String recId) {
        startAuthSign(intData, ordItemsHashVal, certContainer, signToken, userCertCode, certNo, new SimpleCallBack<String[]>() {
            @Override
            public void call(String[] result, int type) {
                String ordItemsHashVal = result[0];
                String mainSignValue = result[1];
                String signID = result[2];
                String mainSignTimeStamp = result[3];
                CaAPIManager.RecordCaSignData(ordItemsHashVal, signID, mainSignTimeStamp, episodeID, recId, userId, mainSignCert, mainSignValue, new CommonCallBack<CommResult>() {
                    @Override
                    public void onFail(String code, String msg) {

                    }

                    @Override
                    public void onSuccess(CommResult bean, String type) {
                        if (doubleSign) {
                            doubleSign = false;
                        } else { //单签
                            EventBus.getDefault().post(new MessageEvent(MessageEvent.MessageType.CA_CALL_BACK));
                        }
                    }
                });

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
                doubleSign = !TextUtils.isEmpty(auditUserCode);
                startRecordAuthSignToHis(ordItemsHashVal, intData, certContainer, signToken, userCertCode, certNo, episodeID, userId, mainSignCert, recId);
                //双签
                if (!TextUtils.isEmpty(auditUserCode)) {
                    GetLoginQR(true, false);
                    //注册双签
                    callBackMap.put(DOUBLE_USER_SIGN, new SimpleCallBack<String>() {
                        @Override
                        public void call(String result, int type) {
                            Log.e("TAG", "(CaSignUtil.java:182) DOUBLE_USER_SIGN");
                            String userId2 = SPStaticUtils.getString(USERID2);
                            String mainSignCert2 = SPStaticUtils.getString(CA_LOGIN2_signCert);
                            String certContainer2 = SPStaticUtils.getString(CA_LOGIN2_certContainer);
                            String signToken2 = SPStaticUtils.getString(CA_LOGIN2_signToken);
                            String userCertCode2 = SPStaticUtils.getString(CA_LOGIN2_userCertCode);
                            String certNo2 = SPStaticUtils.getString(CA_LOGIN2_certNo);
                            startRecordAuthSignToHis(ordItemsHashVal, intData, certContainer2, signToken2, userCertCode2, certNo2, episodeID, userId2, mainSignCert2, recId);
                        }
                    });

                }
            }
        };

        //注册重新登录
        callBackMap.put(RE_LOGIN_TO_EXEC, new SimpleCallBack<String>() {
            @Override
            public void call(String result, int type) {
                Log.e("TAG", "(CaSignUtil.java:201) RE_LOGIN_TO_EXEC");
                if (RE_LOGIN_TO_EXEC.equals(result)) {
                    checkToken(intData, hashCallBack);
                }
            }
        });
        checkToken(intData, hashCallBack);
    }

    /**
     * 检查token
     * @param intData
     */
    public static void checkToken(String intData, SimpleCallBack<String[]> callBack) {
        //先验证token
        boolean curUser = true;
        doubleSign = false; //初始化
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
                message.what = WHAT;
                message.obj = curUser;
                handler.sendMessageDelayed(message, DELAY_MILLIS);
                if (openDialog) {
                    showCaLoginDialog(bean.getQrCode(), curUser);
                }
            }
        });
    }

    public static void showCaLoginDialog(String qrCode, boolean curUser) {
        dismissDialog();
        String txt = STR_RE_LOGIN;
        String userCertCode = SPStaticUtils.getString(SharedPreference.USERCODE);
        if (!curUser) {
            txt = STR_DOUBLE_LOGIN;
        }
        commDialog = DialogFactory.showCaLogin(ActivityUtils.getTopActivity(), qrCode, txt, new SimpleCallBack<String[]>() {
            @Override
            public void call(String[] res, int type) {
                String mUserCertCode = userCertCode;
                if (!curUser) { //双签用户code
                    mUserCertCode = res[1];
                }
                PhonePinLogin(mUserCertCode, res[0]);
            }
        });
    }

    private static void PhonePinLogin(String userCertCode, String pin) {
        CaAPIManager.PhonePinLogin(userCertCode, pin, new CommonCallBack<GetLoginQRResultBean>() {
            @Override
            public void onFail(String code, String msg) {


            }

            @Override
            public void onSuccess(GetLoginQRResultBean bean, String type) {
                //当前用户
                if (SPStaticUtils.getString(SharedPreference.USERCODE).equals(userCertCode)) {
                    bean.saveInfo();
                    ToastUtils.showLong("重新登录成功!");
                    //回调-重新登录
                    callBackMap.get(RE_LOGIN_TO_EXEC).call(RE_LOGIN_TO_EXEC, 0);
                } else {
                    //特殊处理-工号^姓名
                    SPStaticUtils.put(USERID2, userCertCode + "^" + bean.getCertCN());
                    SPStaticUtils.put(CA_LOGIN2_certCN, bean.getCertCN());
                    SPStaticUtils.put(CA_LOGIN2_certContainer, bean.getCertContainer());
                    SPStaticUtils.put(CA_LOGIN2_certDN, bean.getCertDN());
                    SPStaticUtils.put(CA_LOGIN2_certNo, bean.getCertNo());
                    SPStaticUtils.put(CA_LOGIN2_expireTime, bean.getExpireTime());
                    SPStaticUtils.put(CA_LOGIN2_signCert, bean.getSignCert());
                    SPStaticUtils.put(CA_LOGIN2_signStatus, bean.getSignStatus());
                    SPStaticUtils.put(CA_LOGIN2_signToken, bean.getSignToken());
                    SPStaticUtils.put(CA_LOGIN2_userCertCode, bean.getUserCertCode());
                    //回调-第二签名
                    callBackMap.get(DOUBLE_USER_SIGN).call(DOUBLE_USER_SIGN, 0);
                }
                //销毁登陆
                dismissDialog();
                handler.removeMessages(WHAT);
            }
        });
    }

    public static void dismissDialog() {
        if (commDialog != null) {
            try {
                commDialog.dismiss();
            } catch (Exception e) {

            }
        }
    }

    public static void destroy() {
        if (handler != null) {
            handler.removeMessages(WHAT);
            commDialog = null;
        }

    }

    //不停刷新
    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == WHAT) {
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
                    } else {
                        SPStaticUtils.put(CA_LOGIN2_certCN, bean.getCertCN());
                        SPStaticUtils.put(CA_LOGIN2_certContainer, bean.getCertContainer());
                        SPStaticUtils.put(CA_LOGIN2_certDN, bean.getCertDN());
                        SPStaticUtils.put(CA_LOGIN2_certNo, bean.getCertNo());
                        SPStaticUtils.put(CA_LOGIN2_expireTime, bean.getExpireTime());
                        SPStaticUtils.put(CA_LOGIN2_signCert, bean.getSignCert());
                        SPStaticUtils.put(CA_LOGIN2_signStatus, bean.getSignStatus());
                        SPStaticUtils.put(CA_LOGIN2_signToken, bean.getSignToken());
                        SPStaticUtils.put(CA_LOGIN2_userCertCode, bean.getUserCertCode());
                    }
                    //销毁登陆
                    dismissDialog();
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
                    message.what = WHAT;
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
                    //回调-重新登录
                    callBackMap.get(RE_LOGIN_TO_EXEC).call(RE_LOGIN_TO_EXEC, 0);
                } else {
                    SPStaticUtils.put(USERID2, bean.getHisUserID());
                    //回调-第二签名
                    callBackMap.get(DOUBLE_USER_SIGN).call(DOUBLE_USER_SIGN, 0);
                }

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
