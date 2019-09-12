package com.dhcc.module.infusion.workarea.dosing.api;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.workarea.dosing.bean.DosingBean;
import com.dhcc.module.infusion.workarea.comm.bean.PatDetailBean;

/**
 * @author:gaoruishan
 * @date:202019-04-24/14:53
 * @email:grs0515@163.com
 */
public class DosingApiManager {

    /**
     * Description:  按接单号查询医嘱
     * Input：       regNo:登记号,oeoreId:执行记录ID,userId:用户ID,locId:科室ID
     * other:		  w ##class(Nur.OPPDA.Despensing).getOrdList("0000000435","","1","")
     * note: 		  如果走接单流程:查询接单任务医嘱;如果不走:查询当日医嘱
     */
    public static void getOrdList(String regNo, String oeoreId, final CommonCallBack<DosingBean> callback) {
        DosingApiService.getOrdList(regNo, oeoreId, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<DosingBean> parser = new ParserUtil<>();
                DosingBean bean = parser.parserResult(jsonStr, callback, DosingBean.class);
                if (bean == null) return;
                parser.parserStatus(bean, callback);
            }
        });
    }

    /**
     * Description:  配液/复核
     * Input：       oeoreId:执行记录ID,userId:用户ID,type:操作类型(Despensing,Audit),locId:科室Id
     * other:		  w ##class(Nur.OPPDA.Despensing).despensingOrd("568-3-3","1","Audit",1)
     */
    public static void despensingOrd(String oeoreId, String type, String userCode, String password, final CommonCallBack<CommResult> callback) {
        DosingApiService.despensingOrd(oeoreId, type, userCode, password, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callback,true);
            }
        });
    }

    /**
     * CreatDate:    2019-05-09
     * Description:  获取患者信息
     * Input：       regNo:登记号
     * other:		  w ##class(Nur.OPPDA.Patient).getPatInfo("0000000164")
     */
    public static void getPatInfo(String regNo, final CommonCallBack<PatDetailBean> callback) {
        DosingApiService.getPatInfo(regNo, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<PatDetailBean> parser = new ParserUtil<>();
                PatDetailBean bean = parser.parserResult(jsonStr, callback, PatDetailBean.class);
                if (bean == null) return;
                parser.parserStatus(bean, callback);
            }
        });
    }

}
