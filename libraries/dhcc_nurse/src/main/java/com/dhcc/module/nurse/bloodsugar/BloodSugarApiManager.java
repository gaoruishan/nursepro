package com.dhcc.module.nurse.bloodsugar;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.nurse.bloodsugar.bean.BloodSugarNotelistBean;
import com.dhcc.module.nurse.bloodsugar.bean.BloodSugarPatsBean;
import com.dhcc.module.nurse.bloodsugar.bean.BloodSugarValueAndItemBean;
import com.dhcc.module.nurse.task.bean.TaskBean;

import java.util.HashMap;

/**
 * com.dhcc.module.nurse.bloodsugar
 * <p>
 * author Q
 * Date: 2020/8/19
 * Time:17:23
 */
public class BloodSugarApiManager {
    /**
     * Description:患者列表
     *
     */
    public static void getBloodSugarPatsList(String date,final CommonCallBack<BloodSugarPatsBean> callBack) {
        HashMap<String, String> properties = CommWebService.addWardId(null);
        CommWebService.addLocId(properties);
        CommWebService.addUserId(properties);
        properties.put("date", date);

        CommWebService.call("getSugarPatList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<BloodSugarPatsBean> parserUtil = new ParserUtil<>();
                BloodSugarPatsBean bean = parserUtil.parserResult(jsonStr, callBack, BloodSugarPatsBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description:录入界面数据
     *
     */
    public static void getBloodSugarValueAndItem(String date,String episodeId, final CommonCallBack<BloodSugarValueAndItemBean> callBack) {
        HashMap<String, String> properties = CommWebService.addLocId(null);
        properties.put("date", date);
        properties.put("episodeId", episodeId);

        CommWebService.call("getSugarValueAndItem", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<BloodSugarValueAndItemBean> parserUtil = new ParserUtil<>();
                BloodSugarValueAndItemBean bean = parserUtil.parserResult(jsonStr, callBack, BloodSugarValueAndItemBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description:保存血糖
     *
     */
    public static void getSaveBloodSugarResult(String sugarInfo,final CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("sugarInfo", sugarInfo);

        CommWebService.call("saveSugarInfo", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<CommResult> parserUtil = new ParserUtil<>();
                CommResult bean = parserUtil.parserResult(jsonStr, callBack, CommResult.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description:获取血糖列表
     *
     */
    public static void getSugarValueByDate(String episodeId,String startDate,String endDate,final CommonCallBack<BloodSugarNotelistBean> callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("episodeId", episodeId);
        properties.put("startDate", startDate);
        properties.put("endDate", endDate);

        CommWebService.call("getSugarValueByDate", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                jsonStr = "{\n" +
                        "    \"msg\":\"成功\",\n" +
                        "    \"msgcode\":\"999999\",\n" +
                        "    \"status\":\"0\",\n" +
                        "    \"sugarInfoList\":[\n" +
                        "        {\n" +
                        "            \"color\":\"#4472C4\",\n" +
                        "            \"date\":\"2020-08-15\",\n" +
                        "            \"sugarData\":[\n" +
                        "                {\n" +
                        "                    \"code\":\"午餐前\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"23\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"午餐后\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"23\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"夜间\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"12\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"早餐后\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"22\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"晚餐前\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"14\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"晚餐后\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"25\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"睡前\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"23\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"空腹血糖\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"12\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"随机\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"16\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"color\":\"#4472C4\",\n" +
                        "            \"date\":\"2020-08-16\",\n" +
                        "            \"sugarData\":[\n" +
                        "                {\n" +
                        "                    \"code\":\"午餐前\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"14\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"午餐后\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"12\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"夜间\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"12\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"早餐后\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"11\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"晚餐前\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"23\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"晚餐后\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"24\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"睡前\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"12\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"空腹血糖\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"12\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"随机\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"15\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"color\":\"#4472C4\",\n" +
                        "            \"date\":\"2020-08-17\",\n" +
                        "            \"sugarData\":[\n" +
                        "                {\n" +
                        "                    \"code\":\"午餐前\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"17\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"午餐后\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"12\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"夜间\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"15\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"早餐后\",\n" +
                        "                    \"obsNote\":\"3\",\n" +
                        "                    \"sugar\":\"13\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"晚餐前\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"12\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"晚餐后\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"12\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"睡前\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"15\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"空腹血糖\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"6\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"随机\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"4\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"color\":\"#4472C4\",\n" +
                        "            \"date\":\"2020-08-18\",\n" +
                        "            \"sugarData\":[\n" +
                        "                {\n" +
                        "                    \"code\":\"随机\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"4\",\n" +
                        "                    \"updateTime\":\"01:00\",\n" +
                        "                    \"updateUser\":\"护士01\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"随机\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"5\",\n" +
                        "                    \"updateTime\":\"02:00\",\n" +
                        "                    \"updateUser\":\"护士01\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"随机\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"6\",\n" +
                        "                    \"updateTime\":\"04:00\",\n" +
                        "                    \"updateUser\":\"护士01\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"午餐前\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"6\",\n" +
                        "                    \"updateTime\":\"14:25\",\n" +
                        "                    \"updateUser\":\"护士01\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"午餐后\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"7\",\n" +
                        "                    \"updateTime\":\"14:25\",\n" +
                        "                    \"updateUser\":\"护士01\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"早餐后\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"请假\",\n" +
                        "                    \"updateTime\":\"14:25\",\n" +
                        "                    \"updateUser\":\"护士01\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"晚餐前\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"8\",\n" +
                        "                    \"updateTime\":\"14:25\",\n" +
                        "                    \"updateUser\":\"护士01\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"夜间\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"3\",\n" +
                        "                    \"updateTime\":\"14:26\",\n" +
                        "                    \"updateUser\":\"护士01\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"晚餐后\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"3\",\n" +
                        "                    \"updateTime\":\"14:26\",\n" +
                        "                    \"updateUser\":\"护士01\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"睡前\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"3\",\n" +
                        "                    \"updateTime\":\"14:26\",\n" +
                        "                    \"updateUser\":\"护士01\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"空腹血糖\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"color\":\"#4472C4\",\n" +
                        "            \"date\":\"2020-08-19\",\n" +
                        "            \"sugarData\":[\n" +
                        "                {\n" +
                        "                    \"code\":\"午餐前\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"13\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"午餐后\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"1\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"夜间\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"7\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"早餐后\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"12\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"晚餐前\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"8\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"晚餐后\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"8\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"睡前\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"7\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"空腹血糖\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"17\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"code\":\"随机\",\n" +
                        "                    \"obsNote\":\"\",\n" +
                        "                    \"sugar\":\"14\",\n" +
                        "                    \"updateTime\":\"\",\n" +
                        "                    \"updateUser\":\"\"\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}";
                ParserUtil<BloodSugarNotelistBean> parserUtil = new ParserUtil<>();
                BloodSugarNotelistBean bean = parserUtil.parserResult(jsonStr, callBack, BloodSugarNotelistBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
}
