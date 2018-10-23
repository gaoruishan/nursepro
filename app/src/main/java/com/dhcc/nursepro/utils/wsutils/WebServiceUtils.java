package com.dhcc.nursepro.utils.wsutils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

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


public class WebServiceUtils {

//    public static final String WEB_SERVER_URL = "http://10.1.5.87/dthealth/web/Nur.PDA.WebService.cls";


    // 含有3个线程的线程池
    private static final ExecutorService executorService = Executors
            .newFixedThreadPool(5);

    // 命名空间
    private static final String NAMESPACE = "http://www.dhcc.com.cn";

    /**
     *
     *            WebService服务器地址
     * @param methodName
     *            WebService的调用方法名
     * @param properties
     *            WebService的参数
     * @param webServiceCallBack
     *            回调接口
     */
    public static void callWebService(final String methodName,
                                      HashMap<String, String> properties,
                                      final WebServiceCallBack webServiceCallBack) {
        // 创建HttpTransportSE对象，传递WebService服务器地址

        final HttpTransportSE httpTransportSE = new HttpTransportSE("http://"+SPUtils.getInstance().getString("IP")+"/dthealth/web/Nur.PDA.WebService.cls");


        // 创建SoapObject对象
        SoapObject soapObject = new SoapObject(NAMESPACE, methodName);

        // SoapObject添加参数
        if (properties != null) {
            for (Iterator<Map.Entry<String, String>> it = properties.entrySet()
                    .iterator(); it.hasNext();) {
                Map.Entry<String, String> entry = it.next();
                soapObject.addProperty(entry.getKey(), entry.getValue());
            }
        }

        // 实例化SoapSerializationEnvelope，传入WebService的SOAP协议的版本号
        final SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        soapEnvelope.bodyOut = soapObject;


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
                webServiceCallBack.callBack((String) msg.obj);
            }

        };

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

                        Log.v("json result",jsonstr);
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
     *
     *
     * @author xiaanming
     *
     */
    public interface WebServiceCallBack {
        public void callBack(String result);
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

        String userNamestr="dhwebservice";
        String passWordstr="dhwebservice";
        Element[] header = new Element[1];
        header[0] = new Element().createElement("", "Security");
        header[0].setAttribute("","xmlns", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        Element UsernameToken = new Element().createElement("", "UsernameToken");
        Element userName = new Element().createElement("", "Username");
        userName.addChild(Node.TEXT, userNamestr);
        Element passWord = new Element().createElement("", "Password");
        passWord.addChild(Node.TEXT, passWordstr);
        passWord.setAttribute("","Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
        UsernameToken.addChild(Node.ELEMENT, userName);
        UsernameToken.addChild(Node.ELEMENT, passWord);
        header[0].addChild(Node.ELEMENT, UsernameToken);
        serializationEnvelope.headerOut=header;
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
                Log.v("1112222ret",retData.toString()+"1211");

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


}
