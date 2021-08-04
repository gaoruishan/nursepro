package com.dhcc.module.infusion.message.api;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.message.bean.MessageInfusionBean;
import com.dhcc.module.infusion.message.bean.MessageSkinBean;
import com.dhcc.module.infusion.message.bean.NotifyMessageBean;


/**
 * @author:gaoruishan
 * @date:202019-05-20/16:25
 * @email:grs0515@163.com
 */
public class MessageApiManager {
    /**
     * Description:  获取输液列表
     * Input：       locId:科室ID
     * other:		  w ##class(Nur.OPPDA.Message).getInfusionMessage("266")
     */
    public static void getInfusionMessage(final CommonCallBack<MessageInfusionBean> callBack) {
        MessageApiService.getInfusionMessage(new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<MessageInfusionBean> parserUtil = new ParserUtil<>();
                MessageInfusionBean bean = parserUtil.parserResult(jsonStr, callBack, MessageInfusionBean.class,"");
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description: 获取皮试列表  阳性:Y/+ 阴性:N/-
     * Input： locId:科室ID
     * other:	w ##class(Nur.OPPDA.Message).getSkinTestMessage("7")
     */
    public static void getSkinTestMessage(final CommonCallBack<MessageSkinBean> callBack) {
        MessageApiService.getSkinTestMessage(new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<MessageSkinBean> parserUtil = new ParserUtil<>();
                MessageSkinBean bean = parserUtil.parserResult(jsonStr, callBack, MessageSkinBean.class,"");
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description: 置皮试结果
     * Input： oeoreId:执行记录ID
     * other:	w ##class(Nur.OPPDA.Order).setSkinTestResult("656||4||1","Y",1)
     */
    public static void setSkinTestResult(String oeoreId, String skinTest,String auditUserId,String auditPassword, final CommonCallBack<CommResult> callBack) {
        MessageApiService.setSkinTestResult(oeoreId, skinTest,auditUserId,auditPassword, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr, callBack);
            }
        });
    }

    /**
     * 安医附院新增-标准版去掉
     * @param callBack
     */
    public static void getNotifyMessage( final CommonCallBack<NotifyMessageBean> callBack) {
        MessageApiService.getNotifyMessage(new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NotifyMessageBean> parserUtil = new ParserUtil<>();
                NotifyMessageBean bean = parserUtil.parserResult(jsonStr, callBack, NotifyMessageBean.class,"");
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
}
