package com.dhcc.module.nurse.outmanage.bean;

import com.base.commlibs.http.CommResult;
import com.dhcc.module.nurse.accompany.bean.ConfigSheetBean;

import java.util.List;
import java.util.Map;

/**
 * @author:gaoruishan
 * @date:202021/8/19/15:56
 * @email:grs0515@163.com
 */
public class OutManageSubBean extends CommResult {


    /**
     * filed : index
     * title : 序号
     */

    private List<ConfigSheetBean> configList;
    /**
     * bedCode : 01
     * entourage : 测试
     * episodeID : 2887
     * id : 1
     * index : 1
     * name : 市医保15
     * outDateTime : 2021-08-18 10:23
     * outRecordDateTime : 2021-08-18 10:23
     * outRecorder : 护士01
     * regNo : 0000001271
     * remarks : 哈哈哈
     * returnDateTime :
     * returnRecordDateTime :
     * returnRecorder :
     * typeDR : 入院
     */

    private List<Map> outManageList;

    public List<ConfigSheetBean> getConfigList() {
        return configList;
    }

    public void setConfigList(List<ConfigSheetBean> configList) {
        this.configList = configList;
    }

    public List<Map> getOutManageList() {
        return outManageList;
    }

    public void setOutManageList(List<Map> outManageList) {
        this.outManageList = outManageList;
    }


}
