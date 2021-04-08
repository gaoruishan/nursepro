package com.dhcc.module.nurse.bloodsugar;

import com.base.commlibs.NurseAPI;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.nurse.bloodsugar.bean.BloodSugarNotelistBean;
import com.dhcc.module.nurse.bloodsugar.bean.BloodSugarPatsBean;
import com.dhcc.module.nurse.bloodsugar.bean.BloodSugarValueAndItemBean;

import java.util.HashMap;

/**
 * com.dhcc.module.nurse.bloodsugar
 * <p>
 * author Q
 * Date: 2020/8/19
 * Time:17:23
 */
public class BloodSugarApiManager extends NurseAPI {
    /**
     * Description:患者列表
     *
     */
    public static void getBloodSugarPatsList(String date,final CommonCallBack<BloodSugarPatsBean> callBack) {
        HashMap<String, String> properties = CommWebService.addWardId(null);
        CommWebService.addLocId(properties);
        CommWebService.addUserId(properties);
        properties.put("date", date);

        CommWebService.call(getSugarPatList, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<BloodSugarPatsBean> parserUtil = new ParserUtil<>();
                BloodSugarPatsBean bean = parserUtil.parserResult(jsonStr, callBack, BloodSugarPatsBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description:录入界面数据
     *
     */
    public static void getBloodSugarValueAndItem(String date,String episodeId, final CommonCallBack<BloodSugarValueAndItemBean> callBack) {
        HashMap<String, String> properties = CommWebService.addLocId(null);
        properties.put("date", date);
        properties.put("episodeId", episodeId);

        CommWebService.call(getSugarValueAndItem, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<BloodSugarValueAndItemBean> parserUtil = new ParserUtil<>();
                BloodSugarValueAndItemBean bean = parserUtil.parserResult(jsonStr, callBack, BloodSugarValueAndItemBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description:保存血糖
     *
     */
    public static void getSaveBloodSugarResult(String sugarInfo,final CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("sugarInfo", sugarInfo);

        CommWebService.call(saveSugarInfo, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<CommResult> parserUtil = new ParserUtil<>();
                CommResult bean = parserUtil.parserResult(jsonStr, callBack, CommResult.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description:获取血糖列表
     *
     */
    public static void getSugarValueByDate(String episodeId,String startDate,String endDate,final CommonCallBack<BloodSugarNotelistBean> callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("episodeId", episodeId);
        properties.put("startDate", startDate);
        properties.put("endDate", endDate);

        CommWebService.call(getSugarValueByDate, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<BloodSugarNotelistBean> parserUtil = new ParserUtil<>();
                BloodSugarNotelistBean bean = parserUtil.parserResult(jsonStr, callBack, BloodSugarNotelistBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
}
