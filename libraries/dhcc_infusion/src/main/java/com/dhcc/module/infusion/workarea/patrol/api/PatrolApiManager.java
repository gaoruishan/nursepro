package com.dhcc.module.infusion.workarea.patrol.api;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.workarea.patrol.bean.PatrolBean;

/**
 * @author:gaoruishan
 * @date:202019-04-28/09:08
 * @email:grs0515@163.com
 */
public class PatrolApiManager {

    /**
     * Description:  按接单号查询医嘱
     * Input：       regNo:登记号,oeoreId:执行记录ID,userId:用户ID,locId:科室ID,barCode:条码号
     * other:		  w ##class(Nur.OPPDA.Tour).getOrdList("","","1","","568-3-1")
     */
    public static void getTourOrdList(String curRegNo, String curOeoreId, String barCode, final CommonCallBack<PatrolBean> callBack) {
        PatrolApiService.getTourOrdList(curRegNo, curOeoreId, barCode, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<PatrolBean> parserUtil = new ParserUtil<>();
                PatrolBean bean = parserUtil.parserResult(jsonStr, callBack, PatrolBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description:  巡视
     * Input：       oeoreId:执行记录ID,userId:用户ID,locId:科室Id,distantTime:预计完成时间,ifSpeed:滴速,puncturePart:穿刺部位,tourContent:数据串(key|value^key|value)
     * other:		  w ##class(Nur.OPPDA.Tour).tourOrd("568-3-1","1",1)
     */
    public static void tourOrd(String oeoreId, String distantTime, String ifSpeed, String puncturePart, String tourContent, final CommonCallBack<CommResult> callBack) {
        PatrolApiService.tourOrd(oeoreId, distantTime, ifSpeed, puncturePart, tourContent, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }
}
