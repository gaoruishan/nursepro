package com.base.commlibs.wsutils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.LocalTestManager;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class BaseWebServiceUtils {

    public static final String DEFAULT_IP = "10.1.5.87";
    public static final String DTHEALTH_WEB = "/imedical/web";
    //    public static final String WEB_SERVER_URL = "http://10.1.5.87/dthealth/web/Nur.PDA.WebService.cls";
    // 门诊输液新接口
    public static final String NUR_OPPDA_SERVICE = "/Nur.OPPDA.WebService.cls";
    // 护士站接口
    public static final String NUR_PDA_SERVICE = "/Nur.PDA.WebService.cls";

    //
    public static final String OLD_PDA_SERVICE1 = "/DHCNurDocOrdPda.cls";
    public static final String OLD_PDA_SERVICE2 = "/DHCNUREMRNEWOnPage.cls";

    // 含有3个线程的线程池
    private static final ExecutorService executorService = Executors
            .newFixedThreadPool(5);
    // 命名空间
    private static final String NAMESPACE = "http://www.dhcc.com.cn";
    // 默认超时时间
    private static final int TIME_OUT = 10*1000;

    /**
     * OPPDA门诊服务器地址
     */
    public static void callWebOPPDAService(final String methodName,
                                           HashMap<String, String> properties,
                                           final WebServiceCallBack webServiceCallBack) {
        // 创建HttpTransportSE对象，传递WebService服务器地址,默认Nur.PDA.WebService.cls
        String url = getServiceUrl(NUR_OPPDA_SERVICE);
        if (properties != null) {//替换双竖杆
            for (Iterator<Map.Entry<String, String>> it = properties.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, String> entry = it.next();
                if (entry != null && entry.getValue() != null) {
                    String value = entry.getValue().replaceAll("\\|\\|", "-");
                    entry.setValue(value);
                }
            }
        }
        // 添加本地json测试
        if (LocalTestManager.isTest(methodName)) {
            LocalTestManager.callLocalJson(methodName,webServiceCallBack);
            return;
        }
        callWebService(url, methodName, properties, webServiceCallBack);
    }

    /**
     * PDA护士站服务器地址
     */
    public static void callWebPDAService(final String methodName,
                                         HashMap<String, String> properties,
                                         final WebServiceCallBack webServiceCallBack) {
        // 创建HttpTransportSE对象，传递WebService服务器地址,默认Nur.PDA.WebService.cls
        String url = getServiceUrl(NUR_PDA_SERVICE);
        callWebService(url, methodName, properties, webServiceCallBack);
    }

    public static void callWebPDAService(String Cls, final String methodName,
                                         HashMap<String, String> properties,
                                         final WebServiceCallBack webServiceCallBack) {
        // 创建HttpTransportSE对象，传递WebService服务器地址,默认Nur.PDA.WebService.cls
        String url = getServiceUrl(Cls);
        callWebService(url, methodName, properties, webServiceCallBack);
    }

    /**
     * 获取服务器URL
     *
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
     *
     * @param url                请求URL
     * @param methodName         WebService的调用方法名
     * @param properties         WebService的参数
     * @param webServiceCallBack 回调接口
     */
    public static void callWebService(String url, final String methodName, HashMap<String, String> properties, final WebServiceCallBack webServiceCallBack) {

        SharedPreference.MethodName = methodName;
        final HttpTransportSE httpTransportSE = new HttpTransportSE(url,TIME_OUT);


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
        LogUtils.e(url);
        LogUtils.e(soapObject.toString());


        String userNamestr = "dhwebservice";
        String passWordstr = "dhwebservice";
        Element[] header = new Element[1];
        header[0] = new Element().createElement("", "Security");
        header[0].setAttribute("", "xmlns", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        Element UsernameToken = new Element().createElement("", "UsernameToken");
        Element userName = new Element().createElement("", "Username");
        userName.addChild(Node.TEXT, userNamestr);
        Element passWord = new Element().createElement("", "Password");
        passWord.addChild(Node.TEXT, passWordstr);
        passWord.setAttribute("", "Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
        UsernameToken.addChild(Node.ELEMENT, userName);
        UsernameToken.addChild(Node.ELEMENT, passWord);
        header[0].addChild(Node.ELEMENT, UsernameToken);
        soapEnvelope.headerOut = header;

        // 用于子线程与主线程通信的Handler
        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // 将返回值回调到callBack的参数中
                LogUtils.json(LogUtils.E, msg.obj);
                LocalTestManager.isSave(methodName,(String) msg.obj);
                webServiceCallBack.callBack((String) msg.obj);
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
                } catch (HttpResponseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } finally {
                    // 将获取的消息利用Handler发送到主线程
                    mHandler.sendMessage(mHandler.obtainMessage(0,
                            jsonstr));
                }
            }
        });
    }

    /**
     * 获取服务IP地址
     *
     * @return
     */
    public static String getServiceIP() {
        String ip = SPUtils.getInstance().getString(SharedPreference.WEBIP);
        if (TextUtils.isEmpty(ip)) {
            ip = DEFAULT_IP;
        }
        return "http://" + ip;
    }

    public static String loadSoapObject(String serviceUrl, String methodName, Map Parrm) throws Exception {
        String retData = null;

        // 创建soapObject对象
        SoapObject soapObject = new SoapObject(NAMESPACE, methodName);
        // 设置参数

        Iterator iter = Parrm.entrySet().iterator();// 先获取这个map的set序列，再或者这个序列的迭代器
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next(); // 得到这个序列的映射项，就是set中的类型，HashMap都是Map.Entry类型（详情见map接口声明）
            // Integer key = (Integer)entry.getKey(); //获得key
            soapObject.addProperty(entry.getKey().toString(), entry.getValue());

        }

        // 创建SoapSerializationEnvelope对象，并设置WebService版本号
        SoapSerializationEnvelope serializationEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        // 设置serializationEnvelope对象的badyOut属性
        serializationEnvelope.bodyOut = soapObject;
        // 创建HttpTransportSE对象,并且确定wsdl网络地址
        HttpTransportSE httpTransportSE = new HttpTransportSE(serviceUrl);//

        String userNamestr = "dhwebservice";
        String passWordstr = "dhwebservice";
        Element[] header = new Element[1];
        header[0] = new Element().createElement("", "Security");
        header[0].setAttribute("", "xmlns", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        Element UsernameToken = new Element().createElement("", "UsernameToken");
        Element userName = new Element().createElement("", "Username");
        userName.addChild(Node.TEXT, userNamestr);
        Element passWord = new Element().createElement("", "Password");
        passWord.addChild(Node.TEXT, passWordstr);
        passWord.setAttribute("", "Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
        UsernameToken.addChild(Node.ELEMENT, userName);
        UsernameToken.addChild(Node.ELEMENT, passWord);
        header[0].addChild(Node.ELEMENT, UsernameToken);
        serializationEnvelope.headerOut = header;
        try {
            // httpTransportSE调用Call方法
            httpTransportSE.call(NAMESPACE + "/" + methodName, serializationEnvelope);
            // 获取返回的结果对象
            if (serializationEnvelope.getResponse() != null) {
                SoapObject result = (SoapObject) serializationEnvelope.bodyIn;
                Object obj = result.getProperty(methodName + "Result");

                // obj:
                // <Response><ResultCode>0</ResultCode><ResultDesc></ResultDesc><ResultList><Patinfo><name>张三</name><sex>男</sex><age>20</age></Patinfo><Patinfo><name>李四</name><sex>女</sex><age>22</age></Patinfo></ResultList></Response>

                retData = obj.toString();
                Log.v("1112222ret", retData + "1211");

            }

        } catch (IOException e) {
            throw e;
        } catch (XmlPullParserException e2) {
            throw e2;
        } catch (Exception e3) {
            throw e3;
        }

        return retData;
    }

    /**
     * 启动浏览器
     * @param context
     */
    public static void openBrowser(Context context) {
        Intent intent = new Intent();
        String url = getServiceUrl(NUR_OPPDA_SERVICE);
        intent.setData(Uri.parse(url));
        intent.setAction(Intent.ACTION_VIEW);
        context.startActivity(intent);
    }


    /**
     * @author xiaanming
     */
    public interface WebServiceCallBack {
        void callBack(String result);
    }


}
