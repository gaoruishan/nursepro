package com.dhcc.nursepro.utils;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.DataCache;
import com.dhcc.nursepro.workarea.workareabean.MainConfigBean;

/**
 * @author:gaoruishan
 * @date:202020-05-11/16:47
 * @email:grs0515@163.com
 */
public class MainConfigUtil {

    private static final String TAG = MainConfigUtil.class.getSimpleName();

    /**
     * 获取标题
     *
     * @param cls
     * @return
     */
    public static String getToolBarTitle(Class<?> cls) {
        MainConfigBean json = DataCache.getJson(MainConfigBean.class, SharedPreference.DATA_MAIN_CONFIG);
        if (json != null && json.getMainList() != null) {
            for (int i = 0; i < json.getMainList().size(); i++) {
                for (MainConfigBean.MainListBean.MainSubListBean bean : json.getMainList().get(i).getMainSubList()) {
                    if (cls.getSimpleName().contains(bean.getModuleCode())) {
                        return bean.getModuleDesc();
                    }
                }
            }
        }
        return "";
    }
}
