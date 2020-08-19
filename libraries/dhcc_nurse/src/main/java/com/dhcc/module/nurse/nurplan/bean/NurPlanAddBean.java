package com.dhcc.module.nurse.nurplan.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-08-18/16:26
 * @email:grs0515@163.com
 */
public class NurPlanAddBean extends CommResult {

    private List<QuestionAddListBean> questionList;
    private List<QuestionAddTypeBean> questionTypeList;

    public List<QuestionAddListBean> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionAddListBean> questionList) {
        this.questionList = questionList;
    }

    public List<QuestionAddTypeBean> getQuestionTypeList() {
        return questionTypeList;
    }

    public void setQuestionTypeList(List<QuestionAddTypeBean> questionTypeList) {
        this.questionTypeList = questionTypeList;
    }

    /**
     * 获取选择数据-保存
     * @param data
     * @return
     */
    public static String getSaveQuestionRecordData(List<QuestionAddListBean> data) {
        String dataArr = "";
        for (QuestionAddListBean datum :data ) {
            if (datum.getChilds() != null && datum.getChilds().size() > 0) {
                for (QuestionAddListBean.ChildsBean child : datum.getChilds()) {
                    if (child.isSelect()) {
                        dataArr += child.getQueRowID()+"|";
                    }
                }
            }else {
                if (datum.isSelect()) {
                    dataArr += datum.getQueRowID()+"|";
                }
            }
        }
        return dataArr;
    }

    /**
     * 获取原始数据
     * @param data
     * @return
     */
    public static List<QuestionAddListBean>  getOriginData(List<QuestionAddListBean> data) {
        for (QuestionAddListBean datum : data) {
            datum.setSelect(false);
            if (datum.getChilds() != null && datum.getChilds().size() > 0) {
                for (QuestionAddListBean.ChildsBean child : datum.getChilds()) {
                    child.setSelect(false);
                }
            }
        }
        return data;
    }
}
