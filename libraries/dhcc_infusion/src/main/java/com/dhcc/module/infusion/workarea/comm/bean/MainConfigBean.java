package com.dhcc.module.infusion.workarea.comm.bean;

import com.base.commlibs.http.CommResult;
import com.dhcc.res.nurse.bean.ConfigBean;

import java.util.List;

/**
 * 主页菜单配置
 * @author:gaoruishan
 * @date:202019-11-07/16:29
 * @email:grs0515@163.com
 */
public class MainConfigBean extends CommResult {

    /**
     * mainList : [{"fragment":"com.dhcc.module.infusion.workarea.dosing.DosingFragment","id":"100","image":"infusion_main_py","name":"配液"},{"fragment":"com.dhcc.module.infusion.workarea.puncture.PunctureFragment","id":"101","image":"infusion_main_cc","name":"穿刺"},{"fragment":"com.dhcc.module.infusion.workarea.patrol.PatrolFragment","id":"102","image":"infusion_main_xs","name":"巡视"},{"fragment":"com.dhcc.module.infusion.workarea.continues.ContinueFragment","id":"103","image":"infusion_main_xy","name":"续液"},{"fragment":"com.dhcc.module.infusion.workarea.needles.NeedlesFragment","id":"104","image":"infusion_main_bz","name":"拔针"},{"fragment":"com.dhcc.module.infusion.workarea.skin.SkinFragment","id":"105","image":"infusion_main_skin","name":"皮试"}]
     * msg :
     * msgcode : 999999
     * status : 0
     */

    private List<ConfigBean> mainList;

    public List<ConfigBean> getMainList() {
        return mainList;
    }

    public void setMainList(List<ConfigBean> mainList) {
        this.mainList = mainList;
    }


}
