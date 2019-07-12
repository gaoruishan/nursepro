package com.base.commlibs.utils;

import android.text.TextUtils;

import com.base.commlibs.bean.LoginBean;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.SPUtils;
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

    // 登陆类型
    private static final String LOGONLOCTYPE = "E";

    /**
     * 获取科室窗口
     * @return
     */
    public static String getWindowName() {
        String windName = SPUtils.getInstance().getString(SharedPreference.WINDOWNAME);
        if (TextUtils.isEmpty(windName)) {//如果为空从WINSLISTJSON中获取
            String locdesc = SPUtils.getInstance().getString(SharedPreference.LOCDESC);
            java.lang.reflect.Type typeWin = new TypeToken<HashMap<String, List>>() {
            }.getType();
            String winjson = SPUtils.getInstance().getString(SharedPreference.WINSLISTJSON);
            Map<String, List> mapWins = new Gson().fromJson(winjson, typeWin);
            //默认第一个窗口
            return mapWins.get(locdesc).get(0).toString();
        }
        return windName;
    }

    /**
     * 设置科室窗口名称
     * @param name
     */
    public static void setWindowName(String name) {
        if (!TextUtils.isEmpty(name)) {
            SPUtils.getInstance().put(SharedPreference.WINDOWNAME, name);
        }
    }

    /**
     * 检查IP
     * @return
     */
    public static String checkWebIp() {
        String ipstr = SPUtils.getInstance().getString(SharedPreference.WEBIP, "noIp");
        if ("noIp".equals(ipstr)) {
            ipstr = BaseWebServiceUtils.DEFAULT_IP;
            SPUtils.getInstance().put(SharedPreference.WEBIP, ipstr);
        }
        return ipstr;
    }

    /**
     * 检查web路径
     * @return
     */
    public static String checkWebPath() {
        String webPath = SPUtils.getInstance().getString(SharedPreference.WEBPATH);
        if (TextUtils.isEmpty(webPath)) {
            webPath = BaseWebServiceUtils.DTHEALTH_WEB;
            SPUtils.getInstance().put(SharedPreference.WEBPATH, webPath);
        }
        return webPath;
    }

    /**
     * 设置默认登陆类型
     */
    public static void setLoginLocType() {
        SPUtils.getInstance().put(SharedPreference.LOGONLOCTYPE, LOGONLOCTYPE);
    }

    /**
     * 设置IP和Path
     * @param ip
     * @param path
     */
    public static void setWebIpAndPath(String ip, String path) {
        if (!TextUtils.isEmpty(ip)) {
            SPUtils.getInstance().put(SharedPreference.WEBIP, ip);
        }
        if (!TextUtils.isEmpty(path)) {
            SPUtils.getInstance().put(SharedPreference.WEBPATH, path);
        }
    }

    /**
     * 设置科室/窗口
     * @param locJson
     * @param winJson
     */
    public static void setLocWinListJson(String locJson, String winJson) {
        if (!TextUtils.isEmpty(locJson)) {
            SPUtils.getInstance().put(SharedPreference.LOCSLISTJSON, locJson);
        }
        if (!TextUtils.isEmpty(winJson)) {
            SPUtils.getInstance().put(SharedPreference.WINSLISTJSON, winJson);
        }
    }

    /**
     * 记住我,用户工号
     * @param b
     * @param userCode
     */
    public static void setRememberUserCode(boolean b, String userCode) {
        SPUtils.getInstance().put(SharedPreference.REMEM, b);
        if (userCode != null) {//可以为 ""
            SPUtils.getInstance().put(SharedPreference.REMEM_USERCODE, userCode);
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
        SPUtils.getInstance().put(SharedPreference.USERCODE, userCode);
        SPUtils.getInstance().put(SharedPreference.HOSPITALROWID, hospitalRowId);
        SPUtils.getInstance().put(SharedPreference.GROUPID, groupId);
        SPUtils.getInstance().put(SharedPreference.GROUPDESC, groupDesc);
        SPUtils.getInstance().put(SharedPreference.LINKLOC, linkLoc);
        SPUtils.getInstance().put(SharedPreference.LOCID, locId);
        SPUtils.getInstance().put(SharedPreference.LOCDESC,locDesc);
        SPUtils.getInstance().put(SharedPreference.WARDID, wardId);
    }

    /**
     * 保存用户信息
     * @param loginBean
     */
    public static void setUserInfo(LoginBean loginBean) {
        List<LoginBean.LocsBean> locs = loginBean.getLocs();
        SPUtils.getInstance().put(SharedPreference.USERID, loginBean.getUserId());
        SPUtils.getInstance().put(SharedPreference.USERNAME, loginBean.getUserName());
        SPUtils.getInstance().put(SharedPreference.SCHSTDATETIME, loginBean.getSchStDateTime());
        SPUtils.getInstance().put(SharedPreference.SCHENDATETIME,loginBean.getSchEnDateTime());
    }
}
