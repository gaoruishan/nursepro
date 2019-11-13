package com.dhcc.module.infusion.workarea.blood;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.workarea.skin.bean.SkinListBean;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202019-11-13/15:50
 * @email:grs0515@163.com
 */
public class BloodCollectManager {

    public static void getLabOrdList(String regNo, String stDate, String enDate, final CommonCallBack<BloodCollectBean> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addHospitalId(properties);
        properties.put("regNo", regNo);
        properties.put("startDate", stDate);
        properties.put("endDate", enDate);
        CommWebService.call("getLabOrdList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<BloodCollectBean> parserUtil = new ParserUtil<>();
                BloodCollectBean bean = parserUtil.parserResult(jsonStr, callBack, BloodCollectBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
}
