package com.base.commlibs.utils;

import android.text.TextUtils;
import android.util.Log;

import com.base.commlibs.bean.ConfigBean;
import com.base.commlibs.bean.LoginBean;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SPUtils 保存用户信息
 * @author:gaoruishan
 * @date:202019-06-22/14:33
 * @email:grs0515@163.com
 */
public class UserUtil {

    public static final String LOGONLOCTYPE_HEALTH = "W";
    private static final String TAG = UserUtil.class.getSimpleName();
    // 登陆类型:E 是门诊输液
    private static final String LOGONLOCTYPE = "E";

    /**
     * 是否存在UserID
     * @return
     */
    public static boolean isExistUserId() {
        String userId = SPStaticUtils.getString(SharedPreference.USERID);
        return !TextUtils.isEmpty(userId);
    }

    /**
     * 获取科室窗口
     * @return
     */
    public static String getWindowName() {
        String windName = SPStaticUtils.getString(SharedPreference.WINDOWNAME);
        if (TextUtils.isEmpty(windName)) {//如果为空从WINSLISTJSON中获取
            String locdesc = SPStaticUtils.getString(SharedPreference.LOCDESC);
            java.lang.reflect.Type typeWin = new TypeToken<HashMap<String, List>>() {
            }.getType();
            String winjson = SPStaticUtils.getString(SharedPreference.WINSLISTJSON);
            if (!TextUtils.isEmpty(winjson)) {
                Map<String, List> mapWins = new Gson().fromJson(winjson, typeWin);
                //默认第一个窗口
                if (mapWins.get(locdesc) != null && mapWins.get(locdesc).size() > 0) {
                    return mapWins.get(locdesc).get(0).toString();
                }
            }
        }
        return windName;
    }

    /**
     * 设置科室窗口名称
     * @param name
     */
    public static void setWindowName(String name) {
        if (!TextUtils.isEmpty(name)) {
            SPStaticUtils.put(SharedPreference.WINDOWNAME, name);
        } else {
            SPStaticUtils.put(SharedPreference.WINDOWNAME, "");
        }
    }

    /**
     * 设置科室/窗口名称
     * @param locDesc
     */
    public static void setLocWindowName(String locDesc, String name) {
        if (!TextUtils.isEmpty(locDesc)) {
            SPStaticUtils.put(SharedPreference.LOCDESC, locDesc);
        }
        setWindowName(name);
    }

    /**
     * 检查IP
     * @return
     */
    public static String checkWebIp() {
        //检查sd中是否有
        CommFile.read(SharedPreference.WEBIP, new SimpleCallBack<String>() {
            @Override
            public void call(String result, int type) {
                Log.e(TAG, "(UserUtil.java:73) " + result);
                if (!TextUtils.isEmpty(result)) {
                    SPStaticUtils.put(SharedPreference.WEBIP, result);
                }
            }
        });
        //检查sp中是否有
        String ipstr = SPStaticUtils.getString(SharedPreference.WEBIP, "noIp");
        if ("noIp".equals(ipstr)) {
            ipstr = BaseWebServiceUtils.DEFAULT_IP;
            SPStaticUtils.put(SharedPreference.WEBIP, ipstr);
        }
        return ipstr;
    }


    /**
     * 检查日志版本
     */
    public static void checkLogVersion() {

        String code = SPStaticUtils.getString(SharedPreference.APP_VERSION_CODE);
        if (!TextUtils.isEmpty(code)) {
            CommFile.deleteLog(code);
        } else {
            //检查sd中是否有
            CommFile.read(SharedPreference.APP_VERSION_CODE, new SimpleCallBack<String>() {
                @Override
                public void call(String result, int type) {
                    CommFile.deleteLog(result);
                }
            });
        }
    }

    /**
     * 检查web路径
     * @return
     */
    public static String checkWebPath() {
        //检查sd中是否有
        CommFile.read(SharedPreference.WEBPATH, new SimpleCallBack<String>() {
            @Override
            public void call(String result, int type) {
                Log.e(TAG, "(UserUtil.java:100) " + result);
                if (!TextUtils.isEmpty(result)) {
                    SPStaticUtils.put(SharedPreference.WEBPATH, result);
                }
            }
        });
        String webPath = SPStaticUtils.getString(SharedPreference.WEBPATH);
        if (TextUtils.isEmpty(webPath)) {
            webPath = BaseWebServiceUtils.DTHEALTH_WEB;
            SPStaticUtils.put(SharedPreference.WEBPATH, webPath);
        }
        return webPath;
    }

    /**
     * 设置默认登陆类型
     */
    public static void setLoginLocType() {
        SPStaticUtils.put(SharedPreference.LOGONLOCTYPE, LOGONLOCTYPE);
    }

    public static void setLoginLocType(String logonloctype) {
        if (logonloctype != null) {
            SPStaticUtils.put(SharedPreference.LOGONLOCTYPE, logonloctype);
        }
    }

    /**
     * 设置IP和Path
     * @param ip
     * @param path
     */
    public static void setWebIpAndPath(String ip, String path) {
        if (!TextUtils.isEmpty(ip)) {
            SPStaticUtils.put(SharedPreference.WEBIP, ip);
            CommFile.write(SharedPreference.WEBIP, ip);

            String ips = SPStaticUtils.getString(SharedPreference.WEBIPS, "");
            if (!ips.contains(ip)) {
                ips = ips + "," + ip;
            }
            SPStaticUtils.put(SharedPreference.WEBIPS, ips);
        }
        if (!TextUtils.isEmpty(path)) {
            SPStaticUtils.put(SharedPreference.WEBPATH, path);
            CommFile.write(SharedPreference.WEBPATH, path);
        }
    }

    /**
     * 设置科室/窗口
     * @param locJson
     * @param winJson
     */
    public static void setLocWinListJson(String locJson, String winJson) {
        if (!TextUtils.isEmpty(locJson)) {
            SPStaticUtils.put(SharedPreference.LOCSLISTJSON, locJson);
        }
        if (!TextUtils.isEmpty(winJson)) {
            SPStaticUtils.put(SharedPreference.WINSLISTJSON, winJson);
        }
    }

    /**
     * 记住我,用户工号
     * @param b
     * @param userCode
     */
    public static void setRememberUserCode(boolean b, String userCode) {
        SPStaticUtils.put(SharedPreference.REMEM, b);
        if (userCode != null) {//可以为 ""
            SPStaticUtils.put(SharedPreference.REMEM_USERCODE, userCode);
        }
    }

    /**
     * 保存用户医院/科室相关数据
     * @param userCode
     * @param hospitalRowId
     * @param groupId
     * @param groupDesc
     * @param linkLoc
     * @param locId
     * @param locDesc
     * @param wardId
     */
    public static void setLocsUserInfo(String userCode, String hospitalRowId, String groupId, String groupDesc, String linkLoc, String locId, String locDesc, String wardId) {
        SPStaticUtils.put(SharedPreference.USERCODE, userCode);
        SPStaticUtils.put(SharedPreference.HOSPITALROWID, hospitalRowId);
        SPStaticUtils.put(SharedPreference.GROUPID, groupId);
        SPStaticUtils.put(SharedPreference.GROUPDESC, groupDesc);
        SPStaticUtils.put(SharedPreference.LINKLOC, linkLoc);
        SPStaticUtils.put(SharedPreference.LOCID, locId);
        SPStaticUtils.put(SharedPreference.LOCDESC, locDesc);
        SPStaticUtils.put(SharedPreference.WARDID, wardId);
    }

    /**
     * 保存用户信息
     * @param loginBean
     */
    public static void setUserInfo(LoginBean loginBean) {
        List<LoginBean.LocsBean> locs = loginBean.getLocs();
        setUserConfig(loginBean);
        SPStaticUtils.put(SharedPreference.USERID, loginBean.getUserId());
        SPStaticUtils.put(SharedPreference.USERNAME, loginBean.getUserName());
        SchDateTimeUtil.putSchStartEndDateTime(loginBean.getSchStDateTime(),loginBean.getSchEnDateTime());
        //配置
        SPStaticUtils.put(SharedPreference.APP_VERSION_CODE, AppUtils.getAppVersionCode() + "");
        CommFile.write(SharedPreference.APP_VERSION_CODE, AppUtils.getAppVersionCode() + "");
    }

    /**
     * 保存配置
     * @param loginBean
     */
    public static void setUserConfig(ConfigBean loginBean) {
        SPStaticUtils.put(SharedPreference.WARNING_TIME, loginBean.getWarningTime());
        SPStaticUtils.put(SharedPreference.IS_SHOW_DIALOG_TIME, loginBean.getShowDialogTime());
        SPStaticUtils.put(SharedPreference.WEBSOCKET_FLAG, loginBean.getWebSocketFlag());
        SPStaticUtils.put(SharedPreference.NET_LOG, loginBean.getIsNetLog());
        SPStaticUtils.put(SharedPreference.IS_SHOW_CUR_USER_WORKLOAD, loginBean.getShowCurUserWorkload());
        SPStaticUtils.put(SharedPreference.IS_SHOW_LOGCAT, loginBean.getGlobalLogcatFlag());
        SPStaticUtils.put(SharedPreference.IS_HTTP, loginBean.getHttpUpdateFlag());
        SPStaticUtils.put(SharedPreference.IS_HAND_INPUT, loginBean.getHandInputFlag());
        SPStaticUtils.put(SharedPreference.UPDATE_URL, loginBean.getUpdateUrl());
        SPStaticUtils.put(SharedPreference.LOG_FLAG, loginBean.getLogFlag());
        SPStaticUtils.put(SharedPreference.GLOBAL_VIEW_FLAG, loginBean.getGlobalViewFlag());
        SPStaticUtils.put(SharedPreference.MSG_NOTICE_FLAG, loginBean.getMsgNoticeFlag());
        SPStaticUtils.put(SharedPreference.MSG_SKIN_FLAG, loginBean.getMsgSkinFlag());
        SPStaticUtils.put(SharedPreference.ORD_STATE_FLAG, loginBean.getOrdStateFlag());
        SPStaticUtils.put(SharedPreference.BLOOD_CHECK_FLAG, loginBean.getBloodCheckFlag());
        SPStaticUtils.put(SharedPreference.SKIN_DATE_OFFSET, loginBean.getSkinDateOffset());
        SPStaticUtils.put(SharedPreference.SKIN_DATE_OFFSET, loginBean.getSkinDateOffset());
    }

    public static int getShowDialogTime() {
        if (isEmpty(SharedPreference.IS_SHOW_DIALOG_TIME)) {
            return Integer.valueOf(SPStaticUtils.getString(SharedPreference.IS_SHOW_DIALOG_TIME));
        }
        return 1500;
    }

    /**
     * 是否采血复核多次扫码
     * @return
     */
    public static boolean isBloodCheckFlag() {
        return isEmpty(SharedPreference.BLOOD_CHECK_FLAG);
    }

    /**
     * 是否开启log
     * @return
     */
    public static boolean isMsgNoticeFlag() {
        return isEmpty(SharedPreference.MSG_NOTICE_FLAG);
    }

    /**
     * 是否开启全局View
     * @return
     */
    public static boolean isShowGlobalView() {
        return isEmpty(SharedPreference.GLOBAL_VIEW_FLAG);
    }

    public static boolean isShowLogcatView() {
        return isEmpty(SharedPreference.IS_SHOW_LOGCAT);
    }

    public static boolean isWebSocketFlag() {
        return isEmpty(SharedPreference.WEBSOCKET_FLAG);
    }

    /**
     * 输液背景状态
     * @return
     */
    public static boolean isOrdStateFlag() {
        return isEmpty(SharedPreference.ORD_STATE_FLAG);
    }

    /**
     * 获取版本
     * @return
     */
    public static String getVersion() {
        //大于99归0
        int versionCode = AppUtils.getAppVersionCode() >= 100 ? 0 : AppUtils.getAppVersionCode();
        return "v" + AppUtils.getAppVersionName() + "." + versionCode;
    }

    /**
     * 获取皮试差值
     * @return
     */
    public static long getSkinTimeOffset() {
        String string = SPStaticUtils.getString(SharedPreference.SKIN_DATE_OFFSET);
        if (!TextUtils.isEmpty(string) && ViewUtil.isInteger(string)) {
            int i = Integer.parseInt(string);
            return i * 24 * 60 * 60 * 1000;
        }
        return 0;
    }

    /**
     * 是否开启手动输入
     * @return
     */
    public static boolean isHandInput() {
        if (LocalTestManager.isTest()) {
            return true;
        }
        return isEmpty(SharedPreference.IS_HAND_INPUT);
    }

    public static boolean showCurUserWorkload() {
        return isEmpty(SharedPreference.IS_SHOW_CUR_USER_WORKLOAD);
    }

    /**
     * 判断Sp中是否为空
     * @param key
     * @return
     */
    public static boolean isEmpty(String key) {
        return !TextUtils.isEmpty(SPStaticUtils.getString(key,""));
    }

    /**
     * 主Activity 创建时--进行公共处理
     */
    public static void createMainActivity() {
        //检查日志
        CommFile.checkLogSize();
        //保存sp
        LocalTestManager.saveSpUtils();
    }
}
