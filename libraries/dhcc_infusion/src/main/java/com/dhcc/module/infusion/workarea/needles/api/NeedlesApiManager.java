package com.dhcc.module.infusion.workarea.needles.api;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.workarea.needles.NeedlesBean;

/**
 * @author:gaoruishan
 * @date:202019-04-29/10:12
 * @email:grs0515@163.com
 */
public class NeedlesApiManager {


    /**
     * Description:  按接单号查询医嘱
     * Input：       regNo:登记号,oeoreId:执行记录ID,userId:用户ID,locId:科室ID,barCode:条码号
     * other:		  w ##class(Nur.OPPDA.Change).getOrdList("","","1","","568-3-1")
     * note: 		  如果走接单流程:查询接单任务医嘱;如果不走:查询当日医嘱
     */
    public static void getFinishOrdList(String curRegNo, String curOeoreId, String barCode, final CommonCallBack<NeedlesBean> callBack) {
        NeedlesApiService.getFinishOrdList(curRegNo, curOeoreId, barCode, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NeedlesBean> parserUtil = new ParserUtil<>();
                NeedlesBean bean = parserUtil.parserResult(jsonStr, callBack, NeedlesBean.class);
                if (bean==null) return;
                parserUtil.parserStatus(bean,callBack);
            }
        });
    }
    /**
     * Description:  执行
     * Input：       oeoreId:执行记录ID,userId:用户ID,locId:科室Id,distantTime:预计完成时间,ifSpeed:滴速,puncturePart:穿刺部位
     * other:		  w ##class(Nur.OPPDA.Change).changeOrd("568-3-1","4636",266,"2019-04-28 17:05:00",45,"脑后")
     */
    public static void extractOrd(String oeoreId, final CommonCallBack<CommResult> callBack) {
        NeedlesApiService.extractOrd(oeoreId, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }

}
