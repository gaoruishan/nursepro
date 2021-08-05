package com.base.commlibs.wsutils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.utils.LocalTestManager;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BaseWebServiceUtils {

    //测试库
    public static final String DEFAULT_IP = "10.1.21.123";
    //    public static final String DEFAULT_IP = "114.242.246.235";
    public static final String DTHEALTH_WEB = "/imedical/web";
    public static final String PATH_IMEDICAL_WEB = "/imedical/webservice";
    public static final String PATH_IMEDICAL = "/imedical/web";
    public static final String PATH_DTHEALTH = "/dthealth/web";
    public static final String NUR_CONFIG = "/nursepdaconfig.html";
    // 住院
    public static final String NUR_MNIS_SERVICE = "/Nur.MNIS.Service.WebService.cls";
    // 门诊
    public static final String NUR_MOES_SERVICE = "/Nur.MOES.Service.WebService.cls";
    public static final String HTTP = "http";
    public static final String HTTPS = "https";

    // 门诊输液新接口
    public static final String NUR_OPPDA_SERVICE = "/Nur.OPPDA.WebService.cls";
    public static String getOPPDAService() {
        return SPStaticUtils.getString(SharedPreference.oppdaService, NUR_OPPDA_SERVICE);
//        return  NUR_MOES_SERVICE;
    }
    // 护士站接口
    public static final String NUR_PDA_SERVICE = "/Nur.PDA.WebService.cls";
    public static String getPDAService() {
        return SPStaticUtils.getString(SharedPreference.pdaService, NUR_PDA_SERVICE);
//        return NUR_MNIS_SERVICE;
    }
    public static String userNamestr() {
        return SPStaticUtils.getString(SharedPreference.webServiceUserName, "dhwebservice");
//        return "dhsyslogin";
    }

    public static String passWordstr(){
        return SPStaticUtils.getString(SharedPreference.webServicePassword, "dhwebservice");
//        return "1q2w3e4r%T6y7u8i9o0p";
    }

    // 含有3个线程的线程池
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);
    // 命名空间
    private static final String NAMESPACE = "http://www.dhcc.com.cn";
    // 默认超时时间
    private static final int TIME_OUT = 10 * 1000;
    public static final String REQUST_METHOD = "RequestData";
    public static final String PARAMS = "params";
    public static final String VERSION = "version";
    public static final String METHOD = "method";
    public static final String LOGON_INFO = "logonInfo";

    /**
     * OPPDA门诊服务器地址
     */
    public static void callWebOPPDAService(final String methodName,
                                           HashMap<String, String> properties,
                                           final WebServiceCallBack webServiceCallBack) {
        //替换双竖杆
        replaceProperties(properties);
        // 创建HttpTransportSE对象，传递WebService服务器地址,默认Nur.PDA.WebService.cls
        String url = getServiceUrl(getOPPDAService());
        if (url.contains(NUR_MOES_SERVICE)) {
            properties = convertRequestData(methodName, properties);
            callWebService(url, REQUST_METHOD, properties, webServiceCallBack);
        } else {
            callWebService(url, methodName, properties, webServiceCallBack);
        }
    }

    /**
     * 替换双竖杆
     * @param properties
     */
    protected static void replaceProperties(HashMap<String, String> properties) {
        if (properties != null) {
            for (Iterator<Map.Entry<String, String>> it = properties.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, String> entry = it.next();
                if (entry != null && entry.getValue() != null) {
                    String value = entry.getValue().replaceAll("\\|\\|", "-");
                    entry.setValue(value);
                }
            }
        }
    }

    /**
     * 转换数据格式
     * @param methodName
     * @param properties
     * @return
     */
    protected static HashMap<String, String> convertRequestData(String methodName, HashMap<String, String> properties) {
        HashMap<String, String> propertiesTest = new HashMap<>();
        //解决=的乱码问题
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        //当前Fragment
        CommWebService.curFragment(properties);
        //设备ID
        CommWebService.curDevice(properties);
        //统一添加公共参数
        addCommProperties(properties);
        //添加logonInfo对象
        addLogonInfo(properties, gson);

        propertiesTest.put(PARAMS, gson.toJson(properties));
        propertiesTest.put(VERSION, AppUtils.getAppVersionName() + "");
        //方法首字母大写
        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        propertiesTest.put(METHOD, methodName);
        return propertiesTest;
    }

    private static void addLogonInfo(HashMap<String, String> properties, Gson gson) {
        HashMap<String, String> propertiesLogonInfo = new HashMap<>();
        String userId = SPStaticUtils.getString(SharedPreference.USERID);
        //存在用户信息
        if (!TextUtils.isEmpty(userId)) {
            addCommProperties(propertiesLogonInfo);
            properties.put(LOGON_INFO, gson.toJson(propertiesLogonInfo));
        }
    }

    private static void addCommProperties(HashMap<String, String> properties) {
        //统一添加公共参数
        CommWebService.addGroupId(properties);
        CommWebService.addHospitalId(properties);
        CommWebService.addLocId(properties);
        CommWebService.addUserId(properties);
        CommWebService.addWardId(properties);
    }

    /**
     * PDA护士站服务器地址
     */
    public static void callWebPDAService(final String methodName,
                                         HashMap<String, String> properties,
                                         final WebServiceCallBack webServiceCallBack) {
        // 创建HttpTransportSE对象，传递WebService服务器地址,默认Nur.PDA.WebService.cls
        String url = getServiceUrl(getPDAService());
        if (properties == null) {
            properties = new HashMap<>();
        }
        if (url.contains(NUR_MNIS_SERVICE)) {
            properties = convertRequestData(methodName, properties);
            callWebService(url, REQUST_METHOD, properties, webServiceCallBack);
        } else {
            callWebService(url, methodName, properties, webServiceCallBack);
        }
    }

    /**
     * 获取服务器URL
     * @param serviceCls
     * @return
     */
    public static String getServiceUrl(String serviceCls) {
        String path = SPUtils.getInstance().getString(SharedPreference.WEBPATH);
        if (TextUtils.isEmpty(path)) {
            path = DTHEALTH_WEB;
        }
        return getServiceIP() + path + serviceCls;
    }

    /**
     * WebService服务器地址
     * @param url                请求URL
     * @param methodName         WebService的调用方法名
     * @param properties         WebService的参数
     * @param webServiceCallBack 回调接口
     */
    public synchronized static void callWebService(String url, final String methodName, HashMap<String, String> properties, final WebServiceCallBack webServiceCallBack) {
        // 添加本地json测试
        String methodNameTest = getMethodName(methodName, properties);
        if (LocalTestManager.isTest(methodNameTest)) {
            if (properties != null) {
                LogUtils.e(methodNameTest + " 测试= " + properties.toString());
            }
            LocalTestManager.callLocalJson(methodNameTest, webServiceCallBack);
            return;
        }
        Log.e("TAG","(BaseWebServiceUtils.java:220) "+url);
        SharedPreference.MethodName = methodNameTest;
        //支持https
        HttpTransportSE httpTransportSE;
        if (url.contains(HTTPS)) {
            String path = SPStaticUtils.getString(SharedPreference.WEBPATH);
            String host = SPStaticUtils.getString(SharedPreference.WEBIP);
            int port = 443;
            if (host.contains(":")) {
                String[] split = host.split(":");
                host = split[0];
                if ((split.length > 1) && !TextUtils.isEmpty(split[1])) {
                    port = Integer.parseInt(split[1]);
                }
            }
            String webService = url.split(path)[1];
            String file = path + webService;

            httpTransportSE = new HttpsTransportSE(host, port, file, TIME_OUT);
            LogUtils.e(host, port, file);
        } else {
            httpTransportSE = new HttpTransportSE(url, TIME_OUT);
        }


        // 创建SoapObject对象
        SoapObject soapObject = new SoapObject(NAMESPACE, methodName);

        // SoapObject添加参数
        if (properties != null) {
            for (Iterator<Map.Entry<String, String>> it = properties.entrySet()
                    .iterator(); it.hasNext(); ) {
                Map.Entry<String, String> entry = it.next();
                if (entry != null && entry.getValue() != null) {
                    //替换双竖杆
                    //                    String value = entry.getValue().replaceAll("\\|\\|", "-");
                    String value = entry.getValue();
                    soapObject.addProperty(entry.getKey(), value);
                }
            }
        }

        // 实例化SoapSerializationEnvelope，传入WebService的SOAP协议的版本号
        final SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        soapEnvelope.bodyOut = soapObject;
        LogUtils.e(url + "\n请求方法: " + methodNameTest + "    "+SPStaticUtils.getString(SharedPreference.USERID)+ "    "+userNamestr()+"    "+passWordstr());
        LogUtils.e(soapObject.toString());


        Element[] header = new Element[1];
        header[0] = new Element().createElement("", "Security");
        header[0].setAttribute("", "xmlns", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        Element UsernameToken = new Element().createElement("", "UsernameToken");
        Element userName = new Element().createElement("", "Username");
        userName.addChild(Node.TEXT, userNamestr());
        Element passWord = new Element().createElement("", "Password");
        passWord.addChild(Node.TEXT, passWordstr());
        passWord.setAttribute("", "Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
        UsernameToken.addChild(Node.ELEMENT, userName);
        UsernameToken.addChild(Node.ELEMENT, passWord);
        header[0].addChild(Node.ELEMENT, UsernameToken);
        soapEnvelope.headerOut = header;

        // 用于子线程与主线程通信的Handler
        String finalMethodNameTest = methodNameTest;
        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // 将返回值回调到callBack的参数中
                LogUtils.json(msg.obj);
                SharedPreference.DHC_CALLBACK_JSON = SharedPreference.MethodName + "-" + msg.obj.toString();
                //重试机制-数据空,1s后再请求
                if (LocalTestManager.isRequest(finalMethodNameTest, properties, msg.obj,url)) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            callWebService(url, methodName, properties, webServiceCallBack);
                        }
                    }, 1000);
                } else {
                    webServiceCallBack.callBack((String) msg.obj);
                }
            }
        };
        Log.e("json", "submit... ");
        // 开启线程去访问WebService
        executorService.submit(new Runnable() {

            @Override
            public void run() {
                SoapObject resultSoapObject = null;
                String jsonstr = "";
                try {
                    httpTransportSE.call(NAMESPACE + "/" + methodName, soapEnvelope);
                    if (soapEnvelope.getResponse() != null) {
                        // 获取服务器响应返回的SoapObject
                        resultSoapObject = (SoapObject) soapEnvelope.bodyIn;
                        Object obj = resultSoapObject.getProperty(methodName + "Result");

                        jsonstr = obj.toString();

                    }
                } catch (Exception e) {
                    //捕获异常 保存日志
                    Log.e("json", "Exception= " + jsonstr + e.toString());
                    LocalTestManager.saveLogTest(finalMethodNameTest + "_err", url +"\n"+jsonstr + "\n Exception= \n" + e.toString());
                } finally {
                    // 将获取的消息利用Handler发送到主线程
                    mHandler.sendMessage(mHandler.obtainMessage(0, jsonstr));
                }
            }
        });
    }

    /**
     * 新版本-取请求方法
     * @param methodName
     * @param properties
     * @return
     */
    public static String getMethodName(String methodName, HashMap<String, String> properties) {
        String methodNameTest = methodName;
        if (BaseWebServiceUtils.REQUST_METHOD.equalsIgnoreCase(methodName)) {
            methodNameTest = properties.get(BaseWebServiceUtils.METHOD);
        }
        return methodNameTest;
    }

    /**
     * 获取服务IP地址
     * @return
     */
    public static String getServiceIP() {
        String ip = SPUtils.getInstance().getString(SharedPreference.WEBIP);
        if (TextUtils.isEmpty(ip)) {
            ip = DEFAULT_IP;
        }
        String http = "http";
        String string = SPStaticUtils.getString(SharedPreference.HTTP);
        if(!TextUtils.isEmpty(string)){
            http = string;
        }
        return http + "://" + ip;
    }


    /**
     * 启动浏览器
     * @param context
     */
    public static void openBrowser(Context context) {
        Intent intent = new Intent();
        String url = getServiceUrl(getOPPDAService());
        intent.setData(Uri.parse(url));
        intent.setAction(Intent.ACTION_VIEW);
        context.startActivity(intent);
    }

    public static List<String> getPathList() {
        List<String> spinnerItems = new ArrayList<>();
        spinnerItems.add(PATH_IMEDICAL);
        spinnerItems.add(PATH_IMEDICAL_WEB);
        spinnerItems.add(PATH_DTHEALTH);
        return spinnerItems;
    }

    public static List<String> getHttpList() {
        List<String> spinnerItems = new ArrayList<>();
        spinnerItems.add(HTTP);
        spinnerItems.add(HTTPS);
        return spinnerItems;
    }


    /**
     * @author xiaanming
     */
    public interface WebServiceCallBack {
        void callBack(String result);
    }


}
