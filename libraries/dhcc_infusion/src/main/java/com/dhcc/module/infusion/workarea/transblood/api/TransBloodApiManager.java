package com.dhcc.module.infusion.workarea.transblood.api;

import android.support.annotation.NonNull;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.base.commlibs.http.BaseRequestParams;
import com.base.commlibs.InfusionAPI;
import com.dhcc.module.infusion.workarea.transblood.bean.TransBloodDetailBean;
import com.dhcc.module.infusion.workarea.transblood.bean.TransBloodListBean;

import java.util.HashMap;

/**
 * 输血系统接口管理
 * @author:gaoruishan
 * @date:202020-03-05/14:56
 * @email:grs0515@163.com
 */
public class TransBloodApiManager extends InfusionAPI {

    /**
     * 获取输血列表
     */
    public static void getTransBloodList(@NonNull BaseRequestParams params, final CommonCallBack<TransBloodListBean> callBack) {
        HashMap<String, String> properties = BaseRequestParams.getProperties(params);
        CommWebService.call(getTransBloodList, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<TransBloodListBean> parserUtil = new ParserUtil<>();
                TransBloodListBean bean = parserUtil.parserResult(jsonStr, callBack, TransBloodListBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 输血详情 //getBloodPatrolRecord
     */
    public static void getTransBloodDetail(@NonNull BaseRequestParams params, final CommonCallBack<TransBloodDetailBean> callBack) {
        HashMap<String, String> properties = BaseRequestParams.getProperties(params);
        CommWebService.call(getTransBloodDetail, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<TransBloodDetailBean> parserUtil = new ParserUtil<>();
                TransBloodDetailBean bean = parserUtil.parserResult(jsonStr, callBack, TransBloodDetailBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 输血签收
     */
    public static void bloodReceive(@NonNull BaseRequestParams params, final CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = BaseRequestParams.getProperties(params);
        CommWebService.call(bloodReceive, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }
    /**
     * 输血复核
     */
    public static void bloodCheck(@NonNull BaseRequestParams params, final CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = BaseRequestParams.getProperties(params);
        CommWebService.call(bloodCheck, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }

    /**
     * 输注开始
     */
    public static void startTransfusion(@NonNull BaseRequestParams params, final CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = BaseRequestParams.getProperties(params);
        CommWebService.call(startTransfusion, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }

    /**
     * 输血巡视
     */
    public static void bloodPatrol(@NonNull BaseRequestParams params, final CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = BaseRequestParams.getProperties(params);
        CommWebService.call(bloodPatrol, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }
    /**
     * 输血结束
     */
    public static void endTransfusion(@NonNull BaseRequestParams params, final CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = BaseRequestParams.getProperties(params);
        CommWebService.call(endTransfusion, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }

    /**
     * 输血回收
     */
    public static void recycleBloodbag(BaseRequestParams params, final CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = BaseRequestParams.getProperties(params);
        CommWebService.call(recycleBloodbag, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }
}
