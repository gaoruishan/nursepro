package com.dhcc.module.nurse.accompany;

import com.base.commlibs.NurseAPI;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.nurse.accompany.bean.AccompanyBean;
import com.dhcc.module.nurse.accompany.bean.AccompanyInputBean;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202021/8/16/10:56
 * @email:grs0515@163.com
 */
public class AccompanyApiManager extends NurseAPI {


    // 陪护人列表
    public static void getNCPAccompanyList(String accompanysearch, String status, CommonCallBack<AccompanyBean> callBack) {
        HashMap<String, String> hashMap = CommWebService.addWardId(null);
        CommWebService.addGroupId(hashMap);
        CommWebService.addUserId(hashMap);
        CommWebService.addHospitalId(hashMap);
        hashMap.put("AccompanySearch",accompanysearch);
        hashMap.put("Status",status);
        CommWebService.call(GetNCPAccompanyList, hashMap, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<AccompanyBean> parserUtil = new ParserUtil<>();
                AccompanyBean bean = parserUtil.parserResult(jsonStr, callBack, AccompanyBean.class);
                if (bean==null) return;
                parserUtil.parserStatus(bean,callBack);
            }
        });
    }

    /// 陪护人员体征信息数
    public static void getNCPAccompanySub(String nCPARRowIDs, String stDate, String edDate, CommonCallBack<AccompanyInputBean> callBack) {
        HashMap<String, String> hashMap = CommWebService.addHospitalId(null);
        hashMap.put("NCPARRowIDs",nCPARRowIDs);
        hashMap.put("stDate",stDate);
        hashMap.put("edDate",edDate);
        CommWebService.call(GetNCPAccompanySub, hashMap, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<AccompanyInputBean> parserUtil = new ParserUtil<>();
                AccompanyInputBean bean = parserUtil.parserResult(jsonStr, callBack, AccompanyInputBean.class);
                if (bean==null) return;
                parserUtil.parserStatus(bean,callBack);
            }
        });
    }
    /// 陪护人员体征信息数据保存
    public static void saveNCPAccompany(String recordId, String subId, String saveDataArr,CommonCallBack<CommResult> callBack) {
        HashMap<String, String> hashMap = CommWebService.addUserId(null);
        hashMap.put("RecordID",recordId);
        hashMap.put("SubId",subId);
        hashMap.put("SaveDataArr",saveDataArr);
        CommWebService.call(SaveNCPAccompany, hashMap, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }


}
