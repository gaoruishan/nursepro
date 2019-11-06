package com.dhcc.module.infusion.workarea.skin.api;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.workarea.skin.bean.SkinListBean;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202019-11-04/15:29
 * @email:grs0515@163.com
 */
public class SkinApiManager {

    /**
     * Description:  皮试医嘱查询
     * Input：       locId:科室Id
     * other:		 w ##class(Nur.OPPDA.SkinTest).getSkinOrdList("0000000020","1","2019-11-04",,"2019-11-05",0)
     */
    public static void getSkinList(String regNo, String stDate, String enDate, final CommonCallBack<SkinListBean> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addHospitalId(properties);
        properties.put("regNo", regNo);
        properties.put("startDate", stDate);
        properties.put("endDate", enDate);
        CommWebService.call("getSkinOrdList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<SkinListBean> parserUtil = new ParserUtil<>();
                SkinListBean bean = parserUtil.parserResult(jsonStr, callBack, SkinListBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description: 皮试计时
     * Input：	oeoriId 执行记录Id
     * Return： w ##class(Nur.PDA.Order).skinTime("194||57||1",1,"50分钟",149)
     */
    public static void skinTime(String oeoriId, String observeTime, String note, final com.base.commlibs.http.CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addLocId(properties);
        properties.put("oeoriId", oeoriId);
        properties.put("observeTime", observeTime);
        properties.put("note", note);
        CommWebService.call("skinTime", properties, new ServiceCallBack() {
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
}
