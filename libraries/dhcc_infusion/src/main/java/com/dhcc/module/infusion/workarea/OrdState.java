package com.dhcc.module.infusion.workarea;


import com.base.commlibs.utils.UserUtil;
import com.dhcc.module.infusion.R;

import java.util.Arrays;

/**
 * 门诊输液状态
 * @author:gaoruishan
 * @date:202019-10-12/11:39
 * @email:grs0515@163.com
 */
public class OrdState {

    //=================================门诊输液===================================
    //配液界面
    public final static String STATE_1 = "未配液";
    public final static String[] STATE_2 = {"已配液", "已复核"};

    //穿刺界面-未穿刺
    public final static String STATE_3_PUNCTURE_PRE = "待输液";
    //拔针界面-已穿刺
    public final static String STATE_3_NEEDLES_PRE = "待续液";

    //穿刺后
    public final static String STATE_3 = "输液中";
    //拔针后
    public final static String[] STATE_4 = {"已完成", "已输完", "已执行"};
    public final static String[] STATE_5 = {"异常结束", "异常"};


    public static final String State_Pause = "暂停";
    public static final String State_Finsh = "结束";


    /**
     * '未配液'颜色不变;
     * @param ordState
     * @return
     */
    public static int getOrdStateColor(String ordState) {
        //配置
        if (!UserUtil.isOrdStateFlag()) {
            return 0;
        }

        return getBgColor(ordState);
    }

    public static int getBgColor(String ordState) {
        if (STATE_1.equals(ordState)) {
            return R.color.white;
        }
        //未执行-绿色
        if (OrdState.STATE_3_NEEDLES_PRE.equals(ordState)
                || OrdState.STATE_3_PUNCTURE_PRE.equals(ordState)
                || Arrays.asList(OrdState.STATE_2).contains(ordState)) {
            return R.color.state_green;
        }
        //已完成-灰色
        if (Arrays.asList(OrdState.STATE_4).contains(ordState)) {
            return R.color.state_gray;
        }
        //执行中-红
        if (STATE_3.equals(ordState)) {
            return R.color.state_red;
        }
        return 0;
    }

    public static boolean checkFinish( String state) {
        if (OrdState.STATE_3_NEEDLES_PRE.equals(state)
                || OrdState.STATE_1.equals(state)
                || Arrays.asList(OrdState.STATE_2).contains(state)) {
            return true;
        }
        return false;
    }
}

