package com.dhcc.custom.bean;

import com.base.commlibs.http.CommResult;
import com.dhcc.res.custom.bean.ActionBarBean;
import com.dhcc.res.custom.bean.BottomViewBean;
import com.dhcc.res.custom.bean.DateTimeViewBean;
import com.dhcc.res.custom.bean.LeftSheetViewBean;
import com.dhcc.res.custom.bean.ListViewBean;
import com.dhcc.res.custom.bean.ScanViewBean;
import com.dhcc.res.custom.bean.TopTabViewBean;

/**
 * @author:gaoruishan
 * @date:202022/1/28/14:51
 * @email:grs0515@163.com
 */
public class CustomUiConfigBean extends CommResult {

    /**
     * rules : 字段为空,则不显示UI,优先级高的,覆盖优先级低
     * actionBar : {"title":"测试标题","rightText1":"刷新","rightText2":"保存","rightImage1":"","rightImage2":"","rightText1Action":"refresh","rightText2Action":"saveEdu","desc":"actionBar控制顶部标题栏,右侧文本或图片"}
     * scanView : {"icon":"","title":"扫腕带","content":"这是提示内容","desc":"scanView控制扫码页,icon图片默认腕带,标题和提示"}
     * topTabView : {"list":[{"title":"测试tab1","code":"tab1"},{"title":"测试tab2","code":"tab2"}],"param":"tabCode","desc":"topTabView控制顶部Tab页,list列表,param参数名"}
     * leftSheetView : {"list":[{"title":"测试sheet1","code":"sheet1"},{"title":"测试sheet2","code":"sheet2"},{"title":"测试sheet3","code":"sheet3"}],"param":"SheetCode","desc":"leftSheetView控制左侧Sheet页,list列表,param参数名"}
     * dateTimeView : {"start":"2022-01-27 00:00","end":"2022-01-27 23:59","param":"dateTime","desc":"dateTimeView控制日期时间,start或end开始结束时间,param参数名"}
     * listView : {"dateTimeView":{"start":"2022-01-27 00:00","end":"2022-01-27 23:59","param":"dateTime","desc":"dateTimeView控制日期时间,start或end开始结束时间,param参数名;会覆盖掉上层dateTimeView"},"list":{"header":{"content":"这是头部"},"content":[{"type":"text","line1Content":{"title":"测试list内容1111","style":{"color":"#DEFECC","size":"20"}},"line2Content":{"title":"测试list内容1111"},"line3Content":{"title":"日期时间:2022-01-27 12:12"}},{"type":"rich","richContent":"<h2 style=\"background-color:red;\">这是一个标题<\/h2>"}],"footer":{"content":"这是尾部"}}}
     * bottomView : {"type":"2","list":[{"title":"保存","code":"S"},{"title":"取消","code":"C"}],"method":"saveEdu"}
     */

    private String rules;
    /**
     * title : 测试标题
     * rightText1 : 刷新
     * rightText2 : 保存
     * rightImage1 :
     * rightImage2 :
     * rightText1Action : refresh
     * rightText2Action : saveEdu
     * desc : actionBar控制顶部标题栏,右侧文本或图片
     */

    private ActionBarBean actionBar;
    /**
     * icon :
     * title : 扫腕带
     * content : 这是提示内容
     * desc : scanView控制扫码页,icon图片默认腕带,标题和提示
     */

    private ScanViewBean scanView;
    /**
     * list : [{"title":"测试tab1","code":"tab1"},{"title":"测试tab2","code":"tab2"}]
     * param : tabCode
     * desc : topTabView控制顶部Tab页,list列表,param参数名
     */

    private TopTabViewBean topTabView;
    /**
     * list : [{"title":"测试sheet1","code":"sheet1"},{"title":"测试sheet2","code":"sheet2"},{"title":"测试sheet3","code":"sheet3"}]
     * param : SheetCode
     * desc : leftSheetView控制左侧Sheet页,list列表,param参数名
     */

    private LeftSheetViewBean leftSheetView;
    /**
     * start : 2022-01-27 00:00
     * end : 2022-01-27 23:59
     * param : dateTime
     * desc : dateTimeView控制日期时间,start或end开始结束时间,param参数名
     */

    private DateTimeViewBean dateTimeView;
    /**
     * dateTimeView : {"start":"2022-01-27 00:00","end":"2022-01-27 23:59","param":"dateTime","desc":"dateTimeView控制日期时间,start或end开始结束时间,param参数名;会覆盖掉上层dateTimeView"}
     * list : {"header":{"content":"这是头部"},"content":[{"type":"text","line1Content":{"title":"测试list内容1111","style":{"color":"#DEFECC","size":"20"}},"line2Content":{"title":"测试list内容1111"},"line3Content":{"title":"日期时间:2022-01-27 12:12"}},{"type":"rich","richContent":"<h2 style=\"background-color:red;\">这是一个标题<\/h2>"}],"footer":{"content":"这是尾部"}}
     */

    private ListViewBean listView;
    /**
     * type : 2
     * list : [{"title":"保存","code":"S"},{"title":"取消","code":"C"}]
     * method : saveEdu
     */

    private BottomViewBean bottomView;

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public ActionBarBean getActionBar() {
        return actionBar;
    }

    public void setActionBar(ActionBarBean actionBar) {
        this.actionBar = actionBar;
    }

    public ScanViewBean getScanView() {
        return scanView;
    }

    public void setScanView(ScanViewBean scanView) {
        this.scanView = scanView;
    }

    public TopTabViewBean getTopTabView() {
        return topTabView;
    }

    public void setTopTabView(TopTabViewBean topTabView) {
        this.topTabView = topTabView;
    }

    public LeftSheetViewBean getLeftSheetView() {
        return leftSheetView;
    }

    public void setLeftSheetView(LeftSheetViewBean leftSheetView) {
        this.leftSheetView = leftSheetView;
    }

    public DateTimeViewBean getDateTimeView() {
        return dateTimeView;
    }

    public void setDateTimeView(DateTimeViewBean dateTimeView) {
        this.dateTimeView = dateTimeView;
    }

    public ListViewBean getListView() {
        return listView;
    }

    public void setListView(ListViewBean listView) {
        this.listView = listView;
    }

    public BottomViewBean getBottomView() {
        return bottomView;
    }

    public void setBottomView(BottomViewBean bottomView) {
        this.bottomView = bottomView;
    }








}
