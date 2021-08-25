package com.dhcc.module.nurse.outmanage;

import com.base.commlibs.NurseAPI;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.nurse.outmanage.bean.OutManageBean;
import com.dhcc.module.nurse.outmanage.bean.OutManageSubBean;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202021/8/16/10:56
 * @email:grs0515@163.com
 */
public class OutManageApiManager extends NurseAPI {

    //获取患者列表
    public static void getOutManageList(CommonCallBack<OutManageBean> callBack) {
        HashMap<String, String> hashMap = CommWebService.addWardId(null);
        CommWebService.addLocId(hashMap);
        CommWebService.addUserId(hashMap);
        CommWebService.addHospitalId(hashMap);
        CommWebService.call(GetOutManageList, hashMap, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<OutManageBean> parserUtil = new ParserUtil<>();
                OutManageBean bean = parserUtil.parserResult(jsonStr, callBack, OutManageBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    //获取外出列表
    public static void getOutManageListSub(String episodeID, String startDate, String endDate, CommonCallBack<OutManageSubBean> callBack) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("episodeID", episodeID);
        hashMap.put("startDate", startDate);
        hashMap.put("startTime", "00:00");
        hashMap.put("endDate", endDate);
        hashMap.put("endTime", "23:59");
        CommWebService.call(GetOutManageListSub, hashMap, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<OutManageSubBean> parserUtil = new ParserUtil<>();
                OutManageSubBean bean = parserUtil.parserResult(jsonStr, callBack, OutManageSubBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }


//    Parameter GetOutManageList = "Nur.MNIS.Service.OutManage:GetOutManageList";
//
//    Parameter GetOutManageListSub = "Nur.MNIS.Service.OutManage:GetOutManageListSub";
//
//    Parameter SaveOutManageInfo = "Nur.MNIS.Service.OutManage:SaveOutManageInfo";

    // "{""id"":"""",""episodeID"":""1624"",""typeDR"":""1"",""outDateTime"":""2021-08-23 14:51"",""entourage"":""sss"",""returnDateTime"":""2021-08-23 15:55"",""remarks"":""aaaa""}"
    //"{""id"":""3"",""episodeID"":""1624"",""typeDR"":""1"",""outDateTime"":""2021-08-23 14:51"",""entourage"":""dddd"",""returnDateTime"":""2021-08-23 15:55"",""remarks"":""aaaa""}"
//保存
public static void SaveOutManageInfo(String data, CommonCallBack<CommResult> callBack) {
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("data", data);
    CommWebService.call(SaveOutManageInfo, hashMap, new ServiceCallBack() {
        @Override
        public void onResult(String jsonStr) {
            CommWebService.parserCommResult(jsonStr,callBack);
        }
    });
}
}
