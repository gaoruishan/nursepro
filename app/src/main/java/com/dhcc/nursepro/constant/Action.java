package com.dhcc.nursepro.constant;

/**
 * Action
 * 广播标识
 *
 * @author DevLix126
 * @date 2018/9/4
 */
public class Action {

    //扫码
    public static final String DEVICE_SCAN_CODE = "com.dhcc.nurseprobroadcast";

    //医嘱处理
    //接受
    public static final String ORDER_HANDLE_ACCEPT = "com.dhcc.nursepro.order.handle.accept.Action";
    //拒绝
    public static final String ORDER_HANDLE_REFUSE = "com.dhcc.nursepro.order.handle.refuse.Action";
    //完成
    public static final String ORDER_HANDLE_COMPLETE = "com.dhcc.nursepro.order.handle.complete.Action";

    //皮试结果
    //阳性
    public static final String SKIN_TEST_YANG = "com.dhcc.nursepro.skin.test.positive.Action";
    //阴性
    public static final String SKIN_TEST_YIN = "com.dhcc.nursepro.skin.test.negative.Action";


    //配液复核
    public static final String DOSING_REVIEW = "com.dhcc.nursepro.dosingreview.Action";

    //新消息更新
    public static final String NEWMESSAGE_SERVICE = "com.dhcc.nursepro.newmessage.Action";

    //护理巡视巡视记录点击
    public static final String TOUR_DOSINGID = "com.dhcc.nursepro.tour_dosing";


}
