package com.dhcc.module.infusion.login.bean;

import com.base.commlibs.bean.BroadcastListBean;
import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-06-21/14:41
 * @email:grs0515@163.com
 */
public class ScanCodeBean extends CommResult {


    /**
     * broadcastList : [{"Action":"ACTION_CONTENT_NOTIFY_MOTO","Decode":"com.motorolasolutions.emdk.datawedge.data_string","Name":"摩托"},{"Action":"com.ge.action.bar  scan","Decode":"value","Name":"易迈海"},{"Action":"com.scanner.broadcast","Decod   e":"data","Name":"成为"},{"Action":"lachesis_barcode_value_notice_broadcast","De  code":"lachesis_barcode_value_notice_broadcast_data_string","Name":"新联"}]
     * msg   :
     * msgcode : 999999
     * status : 0
     */
    private List<BroadcastListBean> broadcastList;

    public List<BroadcastListBean> getBroadcastList() {
        return broadcastList;
    }

    public void setBroadcastList(List<BroadcastListBean> broadcastList) {
        this.broadcastList = broadcastList;
    }

}
