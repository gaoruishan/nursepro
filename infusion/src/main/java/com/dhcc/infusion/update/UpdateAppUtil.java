package com.dhcc.infusion.update;

import android.app.Dialog;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.TextView;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomDownloadFailedListener;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.HttpUtil;
import com.base.commlibs.utils.SimpleCallBack;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.infusion.R;

import java.util.List;

/**
 * 版本更新工具类
 * @author:gaoruishan
 * @date:202020-01-10/11:39
 * @email:grs0515@163.com
 */
public class UpdateAppUtil {
    private static final String PATH = Environment.getExternalStorageDirectory() + "/dhc/";
    private static final String TAG = UpdateAppUtil.class.getSimpleName();
    private static int canUpdate;

    private static String getUrl() {
        String defaultUrl = "http://"
                + SPUtils.getInstance().getString(SharedPreference.WEBIP)
                + "/updateApp/config.json";
        String updateUrl = SPUtils.getInstance().getString(SharedPreference.UPDATE_URL);
        return TextUtils.isEmpty(updateUrl) ? defaultUrl : updateUrl;
    }

    public static void initCanUpdate() {
        UpdateAppUtil.canUpdate = 0;
    }

    public static void onResume(Context context) {
        //只提醒一次
        if (canUpdate == 0) {
            getNewVersion(context);
        }
    }

    public static void getNewVersion(Context context) {
        String locId = SPUtils.getInstance().getString(SharedPreference.LOCID);
        String locDesc = SPUtils.getInstance().getString(SharedPreference.LOCDESC);
        HttpUtil.get(getUrl(), UpdateBean.class, new SimpleCallBack<UpdateBean>() {
            @Override
            public void call(UpdateBean result, int type) {
                List<UpdateBean.ListBean> list = result.getList();
                //全部更新
                if ("1".equals(result.getAllUpdateFlag())) {
                    checkAndStartDownload(result.getAllAppAddress(), result.getAllNewVersion(), context,false);
                    canUpdate = 1;
                    return;
                }
                for (UpdateBean.ListBean bean : list) {
                    //locDesc 科室包含; 或者locId相等
                    if (locId.equals(bean.getLocId()) || isEquals(locDesc, bean.getLocDesc())) {
                        //强制更新
                        if ("1".equals(bean.getForceFlag())) {
                            checkAndStartDownload(bean.getAppAddress(), bean.getNewVersion(), context,true);
                            return;
                        }
                        //版本号更新
                        checkAndStartDownload(bean.getAppAddress(), bean.getNewVersion(), context,false);
                        canUpdate = 1;
                        return;
                    }
                }
            }

        });
    }

    private static void checkAndStartDownload(String appUrl, String newVersion, Context context,boolean force) {
        int version = AppUtils.getAppVersionCode();
        if (Integer.valueOf(newVersion) > version) {
            startDownload(appUrl, newVersion, context, force);
        } else {
            ToastUtils.showShort("当前为最新版本");
        }
    }

    private static void startDownload(String appUrl, String newVersion, Context context, boolean force) {
        if (!TextUtils.isEmpty(appUrl)) {
            String[] split = appUrl.split("/");
            String apkName = split[split.length - 1];
            DownloadBuilder builder = AllenVersionChecker.getInstance().downloadOnly(crateUIData(appUrl));
            builder.setCustomVersionDialogListener(createCustomUpdateDialog(force));
            builder.setCustomDownloadFailedListener(createCustomDownloadFailedDialog());
            builder.setShowNotification(true);
            builder.setDownloadAPKPath(PATH);
            builder.setApkName(apkName);
            builder.setNewestVersionCode(Integer.valueOf(newVersion));
            builder.executeMission(context);
        }
    }


    private static boolean isEquals(String strParent, String strChild) {
        if (!TextUtils.isEmpty(strChild)) {
            strChild = strChild.replaceAll(" ", "");
            int length = strChild.length();
            if (strParent != null && strParent.length() >= length) {
                return strChild.equals(strParent.substring(0, length));
            }
        }
        return false;
    }

    /**
     * @return
     * @important 使用请求版本功能，可以在这里设置downloadUrl
     * 这里可以构造UI需要显示的数据
     * UIData 内部是一个Bundle
     */
    private static UIData crateUIData(String appUrl) {
        UIData uiData = UIData.create();
        uiData.setTitle("系统升级");
        uiData.setDownloadUrl(appUrl);
        uiData.setContent("检查到有新版本，请下载使用");
        return uiData;
    }

    /**
     * 务必用库传回来的context 实例化你的dialog
     * 自定义的dialog UI参数展示，使用versionBundle
     * @param force
     * @return
     */
    private static CustomVersionDialogListener createCustomUpdateDialog(boolean force) {
        return (context, versionBundle) -> {
            Dialog baseDialog = new Dialog(context, R.style.BaseDialog);
            baseDialog.setContentView(R.layout.dialog_update);
            TextView title = baseDialog.findViewById(R.id.tv_title);
            title.setText(versionBundle.getTitle());
            TextView content = baseDialog.findViewById(R.id.tv_msg);
            content.setText(versionBundle.getContent());
            if (force) {//强制
                baseDialog.setCanceledOnTouchOutside(false);
                baseDialog.setCancelable(false);
            }
            return baseDialog;
        };
    }

    /**
     * 务必用库传回来的context 实例化你的dialog
     * @return
     */
    private static CustomDownloadFailedListener createCustomDownloadFailedDialog() {
        return (context, versionBundle) -> {
            Dialog baseDialog = new Dialog(context, R.style.BaseDialog);
            baseDialog.setContentView(R.layout.dialog_update_downloadfailed);
            baseDialog.setCanceledOnTouchOutside(false);
            baseDialog.setCancelable(false);
            return baseDialog;
        };
    }
}
