package com.dhcc.nursepro.workarea.nurrecordold.bean;

public class ScoreStatisticsBean {
    /**
     * ScoreStatisticsBean
     * 护理病例分值统计相关数据存储
     *
     * @author Devlix126
     * created at 2019/8/22 16:50
     */
    private String viewCode = "";
    private String resultViewCode = "";
    private String selectedItem = "";
    private String scoreOld = "";
    private String scoreNew = "";

    public ScoreStatisticsBean(String viewCode, String resultViewCode) {
        this.viewCode = viewCode;
        this.resultViewCode = resultViewCode;
        this.selectedItem = "";
        this.scoreOld = "0";
        this.scoreNew = "0";
    }

    public String getViewCode() {
        return viewCode;
    }

    public void setViewCode(String viewCode) {
        this.viewCode = viewCode;
    }

    public String getResultViewCode() {
        return resultViewCode;
    }

    public void setResultViewCode(String resultViewCode) {
        this.resultViewCode = resultViewCode;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getScoreOld() {
        return scoreOld;
    }

    public void setScoreOld(String scoreOld) {
        this.scoreOld = scoreOld;
    }

    public String getScoreNew() {
        return scoreNew;
    }

    public void setScoreNew(String scoreNew) {
        this.scoreNew = scoreNew;
    }

    @Override
    public String toString() {
        return "viewCode = " + viewCode + "\nresultViewCode = " + resultViewCode + "\nselectedItem = " + selectedItem + "\nscoreOld = " + scoreOld + "\nscoreNew = " + scoreNew + "\n";
    }
}
