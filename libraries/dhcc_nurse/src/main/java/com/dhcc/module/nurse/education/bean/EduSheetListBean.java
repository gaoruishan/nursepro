package com.dhcc.module.nurse.education.bean;


import com.base.commlibs.bean.PatientListBean;
import com.dhcc.res.infusion.bean.SheetListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-21/10:30
 * @email:grs0515@163.com
 */
public class EduSheetListBean extends SheetListBean {

    private String regNo;


    public static List<EduSheetListBean> getSheetList(List<PatientListBean.PatInfoListBean> patInfoList) {
        List<EduSheetListBean> listBeans = new ArrayList<>();
        for (PatientListBean.PatInfoListBean patInfoListBean : patInfoList) {
            EduSheetListBean sheetListBean = new EduSheetListBean();
            //code 代替EpisodeId就诊号
            sheetListBean.setCode(patInfoListBean.getEpisodeId());
            sheetListBean.setRegNo(patInfoListBean.getRegNo());
            sheetListBean.setDesc(patInfoListBean.getBedCode()+" "+patInfoListBean.getName());
            listBeans.add(sheetListBean);
        }
        return listBeans;
    }

    public static String getCurrentSheetDesc(List<EduSheetListBean> eduSheetList, String episodeId) {
        for (EduSheetListBean bean : eduSheetList) {
            if (bean.getCode().equals(episodeId)) {
                return bean.getDesc();
            }
        }
        return "";
    }

    public String getRegNo() {
        return regNo == null ? "" : regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
}
