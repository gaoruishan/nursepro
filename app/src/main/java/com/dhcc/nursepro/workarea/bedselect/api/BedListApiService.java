package com.dhcc.nursepro.workarea.bedselect.api;

import com.base.commlibs.NurseAPI;
import com.blankj.utilcode.util.SPUtils;
import com.base.commlibs.constant.SharedPreference;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * BedListApiService
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class BedListApiService extends NurseAPI {

    public static void getBedList(final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));


        WebServiceUtils.callWebService(getBedListSelected, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }
}
