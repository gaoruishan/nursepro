package com.base.commlibs.constant;

import com.blankj.utilcode.util.AppUtils;

/**
 * Action
 * 广播标识
 *
 * @author DevLix126
 * @date 2018/9/4
 */
public class Action {

    private static final String appName = AppUtils.getAppPackageName();
    /**
     * MainActivity
     */
    public static final String MainActivity = appName+".main";
    /**
     * LoginActivity
     */
    public static final String LoginActivity = appName+".login";

    //扫码
    public static final String DEVICE_SCAN_CODE = appName+"broadcast";

    //医嘱处理
    public static final String ORDER_HANDLE_TYPE= appName+".order.handle.handletype.Action";
//    //接受
//    public static final String ORDER_HANDLE_ACCEPT = appName+".order.handle.accept.Action";
//    //拒绝
//    public static final String ORDER_HANDLE_REFUSE = appName+".order.handle.refuse.Action";
//    //完成
//    public static final String ORDER_HANDLE_COMPLETE = appName+".order.handle.complete.Action";

    //皮试结果
    //阳性
    public static final String SKIN_TEST_YANG = appName+".skin.test.positive.Action";
    //阴性
    public static final String SKIN_TEST_YIN = appName+".skin.test.negative.Action";

    //配液复核
    public static final String DOSING_REVIEW = appName+".dosingreview.Action";

    //新消息更新
    public static final String NEWMESSAGE_SERVICE = appName+".newmessage.Action";

    //护理巡视巡视记录点击
    public static final String TOUR_DOSINGID = appName+".tour_dosing";

    //余液登记
    public static final String DRUG_RLREG = "com.dhcc.nursepro.drug_rlreg.Action";

    //护理病历
    //View XML
    public static final String NUR_RECORD_XML_VIEW = "com.dhcc.nursepro.nurrecord.xmlview.Action";


    //单人模式导航切换模块广播
    public static final String SINGLEMAP = appName+".siglemap.Action";
    public static final String SETSINGLEMSG = appName+".siglemsg.Action";

}
