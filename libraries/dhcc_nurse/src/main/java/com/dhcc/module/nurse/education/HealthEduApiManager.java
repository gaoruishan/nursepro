package com.dhcc.module.nurse.education;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.module.nurse.education.bean.HealthEduAddBean;
import com.dhcc.module.nurse.education.bean.HealthEduBean;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202020-07-17/17:47
 * @email:grs0515@163.com
 */
public class HealthEduApiManager {
    /**
     * Description:   获取宣教数据
     * Input：        wardId, episodeId, hospID, startDate, endDate
     * Output：
     * Others： 	   w ##class(Nur.PDA.Education).getEducationList()
     * @param callBack
     */
    public static void getEducationList(String episodeId,String startDate,String endDate ,final CommonCallBack<HealthEduBean> callBack) {
        HashMap<String, String> properties = CommWebService.addWardId(null);
        properties.put("hospID", SPUtils.getInstance().getString(SharedPreference.HOSPITALROWID));
        properties.put("episodeId", episodeId);
        properties.put("startDate", startDate);
        properties.put("endDate", endDate);

        CommWebService.call("getEducationList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<HealthEduBean> parserUtil = new ParserUtil<>();
                HealthEduBean bean = parserUtil.parserResult(jsonStr, callBack, HealthEduBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    public static void getHealthAddList(final CommonCallBack<HealthEduAddBean> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addWardId(properties);

        CommWebService.call("getHealthAddList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<HealthEduAddBean> parserUtil = new ParserUtil<>();
                HealthEduAddBean bean = parserUtil.parserResult(jsonStr, callBack, HealthEduAddBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
}
