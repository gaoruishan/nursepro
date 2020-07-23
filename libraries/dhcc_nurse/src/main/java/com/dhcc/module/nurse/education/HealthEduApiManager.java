package com.dhcc.module.nurse.education;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.module.nurse.education.bean.HealthEduBean;
import com.dhcc.module.nurse.education.bean.HealthEduContentBean;
import com.dhcc.module.nurse.params.SaveEduParams;

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

    public static void getEduContents(String subjectIds, CommonCallBack<HealthEduContentBean> callBack) {
        HashMap<String, String>  properties = new HashMap<String, String>();
        properties.put("subjectIds", subjectIds);

        CommWebService.call("getEduContents", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<HealthEduContentBean> parserUtil = new ParserUtil<>();
                HealthEduContentBean bean = parserUtil.parserResult(jsonStr, callBack, HealthEduContentBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * /// Description:   保存宣教数据
     * /// Input：        episodeId 就诊号
     * /// Output：
     * /// Others： 	   w ##class(Nur.PDA.Education).saveEdu
     * ClassMethod saveEdu(episodeId, Subject, Content, EducationDate, EducationTime, EduItemList, UserId, LocId, WardId, id = "", SubjectIds = "", eduTaskIds = "[]")
     */
    public static void saveEdu(SaveEduParams params,CommonCallBack<CommResult> callBack) {
        CommWebService.call("saveEdu", params.getProperties(), new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr,callBack);
            }
        });
    }
}
