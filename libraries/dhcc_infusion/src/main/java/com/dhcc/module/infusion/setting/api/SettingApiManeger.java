package com.dhcc.module.infusion.setting.api;

import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.setting.bean.WorkStatisticsBean;
/**
 * @author:gaoruishan
 * @date:202019-05-20/16:08
 * @email:grs0515@163.com
 */
public class SettingApiManeger {


    /**
     * Description: 工作量统计
     * Input： locId:科室ID,sttDate:开始日期,endDate:结束日期
     * other:	w ##class(Nur.OPPDA.Workload).getWorkload(266,"2019-05-01","2019-05-13")
     */
    public static void getWorkload(String sttDate, String endDate, final com.base.commlibs.http.CommonCallBack<WorkStatisticsBean> callBack) {
        SettingApiService.getWorkload(sttDate, endDate, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<WorkStatisticsBean> parserUtil = new ParserUtil<>();
                WorkStatisticsBean bean = parserUtil.parserResult(jsonStr, callBack, WorkStatisticsBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }


}
