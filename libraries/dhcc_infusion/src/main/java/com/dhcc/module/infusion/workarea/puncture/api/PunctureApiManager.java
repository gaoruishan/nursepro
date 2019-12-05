package com.dhcc.module.infusion.workarea.puncture.api;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.workarea.puncture.PunctureBean;

/**
 * com.dhcc.module.infusion.workarea.puncture.api
 * <p>
 * author Q
 * Date: 2019/3/7
 * Time:9:24
 */
public class PunctureApiManager {

    /**
     * Description:  按接单号查询医嘱
     * Input：       regNo:登记号,oeoreId:执行记录ID,userId:用户ID,locId:科室ID,barCode:条码号
     * other:		 w ##class(Nur.OPPDA.Puncture).getOrdList("","","1","","568-3-1")
     * note: 		 如果走接单流程:查询接单任务医嘱;如果不走:查询当日医嘱
     */
    public static void getOrdList(String regNo, String curOeoreId, String barCode, final CommonCallBack<PunctureBean> callBack) {
        PunctureApiService.getOrdList(regNo, curOeoreId, barCode, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<PunctureBean> parserUtil = new ParserUtil<>();
                PunctureBean bean = parserUtil.parserResult(jsonStr, callBack, PunctureBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }


    /**
     * Description:  执行
     * Input：       oeoreId:执行记录ID,userId:用户ID,locId:科室Id,distantTime:预计完成时间,ifSpeed:滴速,puncturePart:穿刺部位
     * other:		 w ##class(Nur.OPPDA.Execute).punctureOrd("568-3-1","1",1)
     */
    public static void punctureOrd(String oeoreId, String distantTime, String ifSpeed, String puncturePart,String puntureTool, final CommonCallBack<CommResult> callBack) {
        PunctureApiService.punctureOrd(oeoreId, distantTime, ifSpeed, puncturePart, puntureTool,new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }

}
