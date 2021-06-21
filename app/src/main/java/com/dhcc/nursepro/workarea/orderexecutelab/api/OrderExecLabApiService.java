package com.dhcc.nursepro.workarea.orderexecutelab.api;

import android.text.TextUtils;
import android.util.Log;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class OrderExecLabApiService {

    public static void getOrder(String bedStr, String regNo, String sheetCode, String pageNo, String startDate, String startTime, String endDate, String endTime, String screenParts, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("hospitalId", spUtils.getString(SharedPreference.HOSPITALROWID));
        properties.put("groupId", spUtils.getString(SharedPreference.GROUPID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));

        properties.put("bedStr", bedStr);
        properties.put("regNo", regNo);
        properties.put("sheetCode", sheetCode);
        properties.put("pageNo", pageNo);

        properties.put("startDate", startDate);
        properties.put("startTime", startTime);
        properties.put("endDate", endDate);
        properties.put("endTime", endTime);
        properties.put("screenParts", screenParts);

        WebServiceUtils.callWebService("getOrders", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
//                result = "{\"buttons\":[{\"code\":\"excuteOrder\",\"desc\":\"执行医嘱\",\"execode\":\"F\",\"singleFlag\":\"N\"}],\"detailColums\":[{\"code\":\"regNo\",\"name\":\"登记号\"},{\"code\":\"bedCode\",\"name\":\"床号\"},{\"code\":\"patName\",\"name\":\"病人姓名\"},{\"code\":\"age\",\"name\":\"年龄\"},{\"code\":\"orcatDesc\",\"name\":\"医嘱大类\"},{\"code\":\"arcimDesc\",\"name\":\"医嘱名称\"},{\"code\":\"ordStatDesc\",\"name\":\"医嘱状态\"},{\"code\":\"labNo\",\"name\":\"标本号\"},{\"code\":\"notes\",\"name\":\"备注\"},{\"code\":\"ctcpDesc\",\"name\":\"开医嘱人\"},{\"code\":\"reclocDesc\",\"name\":\"接收科室\"},{\"code\":\"createDateTime\",\"name\":\"开医嘱时间\"},{\"code\":\"sttDateTime\",\"name\":\"要求执行时间\"},{\"code\":\"specDesc\",\"name\":\"标本名称\"},{\"code\":\"seeOrderUserName\",\"name\":\"处理医嘱人\"},{\"code\":\"seeOrderUserDateTime\",\"name\":\"处理时间\"},{\"code\":\"ordDep\",\"name\":\"开医嘱科室\"},{\"code\":\"notifyClinician\",\"name\":\"加急标记\"},{\"code\":\"tubeColor\",\"name\":\"采血管颜色代码\"},{\"code\":\"tubeColorDesc\",\"name\":\"采血管颜色\"},{\"code\":\"ordID\",\"name\":\"医嘱ID\"}],\"msg\":\"\",\"msgcode\":\"999999\",\"orders\":[{\"bedCode\":\"03床\",\"name\":\"吴红芳\",\"patOrds\":[[{\"orderInfo\":{\"ArcimProductDesc\":\"淀粉酶(生化)\",\"DspStat\":\"\",\"Durat\":\"\",\"EncryptLevel\":\"\",\"FuHeDate\":\"12-31\",\"FuHeTime\":\"00:00\",\"FuHeUser\":\"\",\"ID\":\"105087737||249||1\",\"OldPatNoStr\":\"11968522\",\"PatLevel\":\"\",\"PeiYeDate\":\"12-31\",\"PeiYeTime\":\"00:00\",\"PeiYeUser\":\"\",\"PreDisDateTime\":\"\",\"addOrdBtn\":\"补费用\",\"admLoc\":\"肝脏外科\",\"age\":\"56岁\",\"arcimDesc\":\"淀粉酶(生化)\",\"bedCode\":\"03\",\"cancelReason\":\"\",\"containerInfo\":{\"ID\":\"20\",\"barCodeNumber\":\"0\",\"code\":\"Y01\",\"color\":\"#ff9900\",\"desc\":\"黄色分离胶试管\",\"imageUrl\":\"../image/labcontainerimage/Y01.PNG\",\"notes\":\"黄色\",\"notice\":\"\",\"requireNotes\":\"false\",\"volumn\":\"0\"},\"createDateTime\":\"2021-04-21 08:27:36\",\"ctcpDesc\":\"余继海\",\"disposeStatCode\":\"已执行^#b4a89a\",\"disposeStatdesc\":\"Exec\",\"doseQtyUnit\":\"\",\"examInfo\":{},\"excutenurse\":\"乔晓斐\",\"exeStColor\":\"#7B7B7B\",\"exeStatus\":\"执\",\"execCtcpDesc\":\"乔晓斐\",\"execDateTime\":\"05-23 15:54\",\"execID\":\"105087737||249||1\",\"execXDateTime\":\"\",\"execXUserDesc\":\"\",\"filteFlagExtend\":\"\",\"flowSpeed\":\"\",\"labNo\":\"1004098766\",\"labNote\":\"采集后1小时内送检\",\"medCareNo\":\"60135377\",\"no\":\"4\",\"notes\":\"\",\"notifyClinician\":\"N\",\"oecprDesc\":\"临时医嘱\",\"orcatDesc\":\"检验\",\"ordDep\":\"肝脏外科\",\"ordID\":\"105087737||249\",\"ordStatDesc\":\"核实\",\"ordTyp\":\"L\",\"patIdentity\":\"异地城乡居民医保\",\"patName\":\"吴红芳\",\"phOrdQtyUnit\":\"1 \",\"phcfrCode\":\"\",\"phcinDesc\":\"\",\"placerNo\":\"\",\"preparedFlag\":\"N\",\"prescNo\":\"\",\"price\":\"10.00\",\"printFlag\":\"\",\"printflag\":\"\",\"reclocDesc\":\"检验科生化室\",\"regNo\":\"0010510980 \",\"seeOrderUserDateTime\":\"2021-04-21 09:13:00\",\"seeOrderUserName\":\"乔晓斐\",\"seqNo\":\"105087737||249\",\"skinTestInfo\":\"\",\"skinTestNumber\":\"\",\"specCollDateTime\":\"\",\"specDesc\":\"血清\",\"sttDate\":\"04-21\",\"sttDateTime\":\"2021-04-21 08:27\",\"sttTime\":\"08:27\",\"totalAmount\":\"10.00\",\"tubeColor\":\"Yellow\",\"tubeColorCode\":\"#ff9900\",\"tubeColorDesc\":\"黄色分离胶试管\",\"verifyFlag\":\"N\",\"xCtcpDesc\":\"\",\"xDateTime\":\"\"},\"type\":\"main\"}],[{\"orderInfo\":{\"ArcimProductDesc\":\"尿淀粉酶(生化)\",\"DspStat\":\"\",\"Durat\":\"\",\"EncryptLevel\":\"\",\"FuHeDate\":\"12-31\",\"FuHeTime\":\"00:00\",\"FuHeUser\":\"\",\"ID\":\"105087737||250||1\",\"OldPatNoStr\":\"11968522\",\"PatLevel\":\"\",\"PeiYeDate\":\"12-31\",\"PeiYeTime\":\"00:00\",\"PeiYeUser\":\"\",\"PreDisDateTime\":\"\",\"addOrdBtn\":\"补费用\",\"admLoc\":\"肝脏外科\",\"age\":\"56岁\",\"arcimDesc\":\"尿淀粉酶(生化)\",\"bedCode\":\"03\",\"cancelReason\":\"\",\"containerInfo\":{\"ID\":\"21\",\"barCodeNumber\":\"0\",\"code\":\"Y04\",\"color\":\"#ff9900\",\"desc\":\"黄色普通试管\",\"imageUrl\":\"../image/labcontainerimage/Y04.PNG\",\"notes\":\"黄色\",\"notice\":\"\",\"requireNotes\":\"false\",\"volumn\":\"0\"},\"createDateTime\":\"2021-04-21 08:28:12\",\"ctcpDesc\":\"余继海\",\"disposeStatCode\":\"已执行^#b4a89a\",\"disposeStatdesc\":\"Exec\",\"doseQtyUnit\":\"\",\"examInfo\":{},\"excutenurse\":\"乔晓斐\",\"exeStColor\":\"#7B7B7B\",\"exeStatus\":\"执\",\"execCtcpDesc\":\"乔晓斐\",\"execDateTime\":\"05-23 15:54\",\"execID\":\"105087737||250||1\",\"execXDateTime\":\"\",\"execXUserDesc\":\"\",\"filteFlagExtend\":\"\",\"flowSpeed\":\"\",\"labNo\":\"1004098767\",\"labNote\":\"采集后1小时内送检\",\"medCareNo\":\"60135377\",\"no\":\"5\",\"notes\":\"\",\"notifyClinician\":\"N\",\"oecprDesc\":\"临时医嘱\",\"orcatDesc\":\"检验\",\"ordDep\":\"肝脏外科\",\"ordID\":\"105087737||250\",\"ordStatDesc\":\"核实\",\"ordTyp\":\"L\",\"patIdentity\":\"异地城乡居民医保\",\"patName\":\"吴红芳\",\"phOrdQtyUnit\":\"1 \",\"phcfrCode\":\"\",\"phcinDesc\":\"\",\"placerNo\":\"\",\"preparedFlag\":\"N\",\"prescNo\":\"\",\"price\":\"10.00\",\"printFlag\":\"\",\"printflag\":\"\",\"reclocDesc\":\"检验科生化室\",\"regNo\":\"0010510980 \",\"seeOrderUserDateTime\":\"2021-04-21 09:13:00\",\"seeOrderUserName\":\"乔晓斐\",\"seqNo\":\"105087737||250\",\"skinTestInfo\":\"\",\"skinTestNumber\":\"\",\"specCollDateTime\":\"\",\"specDesc\":\"随机尿\",\"sttDate\":\"04-21\",\"sttDateTime\":\"2021-04-21 08:28\",\"sttTime\":\"08:28\",\"totalAmount\":\"10.00\",\"tubeColor\":\"Yellow\",\"tubeColorCode\":\"#ff9900\",\"tubeColorDesc\":\"黄色普通试管\",\"verifyFlag\":\"N\",\"xCtcpDesc\":\"\",\"xDateTime\":\"\"},\"type\":\"main\"}]]}],\"sheetDefCode\":\"JYDYZX\",\"sheetList\":[{\"code\":\"YZZL\",\"desc\":\"医嘱总览\"},{\"code\":\"DefaultSee\",\"desc\":\"需处理\"},{\"code\":\"WZX\",\"desc\":\"需执行\"},{\"code\":\"JYD\",\"desc\":\"检验单\"},{\"code\":\"JYDYZX\",\"desc\":\"检验单(全)\"},{\"code\":\"CQZSD\",\"desc\":\"注射单\"},{\"code\":\"cqkfyd\",\"desc\":\"口服单\"},{\"code\":\"JMD\",\"desc\":\"静脉单(静配)\"},{\"code\":\"JMDFJP\",\"desc\":\"静脉单(非静配)\"},{\"code\":\"JMDQ\",\"desc\":\"静脉单全\"},{\"code\":\"YSHLD\",\"desc\":\"护理单\"},{\"code\":\"PSD\",\"desc\":\"皮试单\"},{\"code\":\"JCD\",\"desc\":\"检查单\"},{\"code\":\"SXD\",\"desc\":\"输血单\"},{\"code\":\"CUDY\",\"desc\":\"出院带药单\"},{\"code\":\"WHXRD\",\"desc\":\"雾化吸入单\"},{\"code\":\"QYD\",\"desc\":\"取药单\"},{\"code\":\"SQYZD\",\"desc\":\"术前医嘱单\"},{\"code\":\"CQSYDN\",\"desc\":\"静配第一天\"},{\"code\":\"HLDYK\",\"desc\":\"护理单（眼科）\"},{\"code\":\"ZCY\",\"desc\":\"中草药单\"},{\"code\":\"SSDDPQ\",\"desc\":\"手术单（打瓶签）\"},{\"code\":\"HLDKFYX\",\"desc\":\"护理单(康复医学)\"},{\"code\":\"LSYZZY\",\"desc\":\"临时医嘱（住院）\"}],\"status\":\"0\"}";
                callback.onResult(result);
            }
        });

    }

    public static void execOrSeeOrder(String speed, String scanFlag, String batch, String auditUserCode, String auditUserPass, String oeoreId, String execStatusCode, String reason, String barCode, final ServiceCallBack callBack) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("speed", speed);
        properties.put("scanFlag", scanFlag);
        properties.put("batch", batch);
        properties.put("auditUserCode ", auditUserCode);
        properties.put("auditUserPass ", auditUserPass);
        properties.put("oeoreId", oeoreId);
        properties.put("execStatusCode", execStatusCode);
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("userDeptId", spUtils.getString(SharedPreference.LOCID));
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("barCode", barCode);

        if (!TextUtils.isEmpty(reason)) {
            properties.put("reason", reason);
        }


        Log.i("OrderExecute", "execOrSeeOrder: " + properties.toString());

        WebServiceUtils.callWebService("execOrSeeOrder", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callBack.onResult(result);
            }
        });
    }


    public static void getOrdersMsg(String episodeId, String scanInfo, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        if (!"".equals(episodeId)) {
            properties.put("episodeId", episodeId);
        }
        properties.put("barcode", scanInfo);
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("userDeptId", "");

        WebServiceUtils.callWebService("getScanInfo", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }
}
