package com.base.commlibs.constant;

/**
 * Action
 * 广播标识
 *
 * @author DevLix126
 * @date 2018/9/4
 */
public class Action {
    /**
     * MainActivity
     */
    public static final String MainActivity = "com.dhcc.intent.action.main";

    //扫码
    public static final String DEVICE_SCAN_CODE = "com.dhcc.infusionbroadcast";

    //医嘱处理
    //接受
    public static final String ORDER_HANDLE_ACCEPT = "com.dhcc.infusion.order.handle.accept.Action";
    //拒绝
    public static final String ORDER_HANDLE_REFUSE = "com.dhcc.infusion.order.handle.refuse.Action";
    //完成
    public static final String ORDER_HANDLE_COMPLETE = "com.dhcc.infusion.order.handle.complete.Action";

    //皮试结果
    //阳性
    public static final String SKIN_TEST_YANG = "com.dhcc.infusion.skin.test.positive.Action";
    //阴性
    public static final String SKIN_TEST_YIN = "com.dhcc.infusion.skin.test.negative.Action";


    //配液复核
    public static final String DOSING_REVIEW = "com.dhcc.infusion.dosingreview.Action";

    //新消息更新
    public static final String NEWMESSAGE_SERVICE = "com.dhcc.infusion.newmessage.Action";


}
