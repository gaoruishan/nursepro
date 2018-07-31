package com.dhcc.nurse_pro.Utils;

/**
 * 通用接口
 */

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class GetDataByWS {
	static final String SERVICE_NS = "http://tempuri.org";
	public String RetData = "";
	// 定义Web Service的命名空间
	public static void main(String[] sar) {
		// loadData();
	}
	public void ThreadServHttp(final String Cls, final String mth,
                               final String Param, final String Typ, final Context context, final Handler handler,
                               final int whatmsg) {
		//qq判断网络是否连接
//		if (MobileCom.isConnect(context) == false) {
//			Toast.makeText(context, "检查网路！", Toast.LENGTH_LONG).show();
//			return;
//		}
		String[] arr = Param.split("\\&");
		final Map<String, Object> Parrm = new HashMap<String, Object>();
		Map<String, Object> para = new HashMap<String, Object>();
		
        JSONArray jsonArray = new JSONArray();
        int j=1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(""))
				continue;
			String[] itm = arr[i].split("\\=");
			String code="parameter"+j;
			String value="";
			if(itm.length!=1){
				value=itm[1];
			}
			para.put(code,value );
			j++;
		}
		String json=JSONObject.toJSONString(para);
		//String json=JSON.toJSONString(para);
		Parrm.put("clsName",Cls);
		Parrm.put("methodName",mth);

		
		Parrm.put("parameters",json);
		//qq获取服务器地址
//		if (LoginUser.WebUrl.equals(""))
//			LoginUser.WebUrl = "http://10.3.1.121/dthealth/web/";
		//qq写入服务器地址
//		final String serviceUrl=LoginUser.WebUrl+"Nur.WebService.GetData.cls";
		final String serviceUrl="http://10.3.1.121/dthealth/web/"+"Nur.WebService.GetData.cls";
		Thread thread = new Thread() {
			public void run() {
				try {
					Thread.sleep(10);
					RetData = "";
					RetData = loadSoapObject(serviceUrl,Parrm);
//					handler.sendMessage(msg);
				} catch (Exception e) {
					RetData = "error";
				}
				Message msg = new Message();
				msg.what = whatmsg;
				msg.obj=RetData;
				handler.sendMessage(msg);
			}
		};
		thread.start();
		
	}

	/**
	 * 接收服务器返回的SoapObject数据
	 * @param serviceUrl
	 * @param methodName
	 * @param requestCode
	 * @param requestXml
	 * @return
	 * @throws Exception
	 */
	public static String loadSoapObject(String serviceUrl, Map Parrm) throws Exception {
		String retData = null;
		String methodName="PDAExcute";
		// 创建soapObject对象
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		Iterator iter = Parrm.entrySet().iterator();// 先获取这个map的set序列，再或者这个序列的迭代器
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next(); // 得到这个序列的映射项，就是set中的类型，HashMap都是Map.Entry类型（详情见map接口声明）
			soapObject.addProperty(entry.getKey().toString(), entry.getValue());

		}
		SoapSerializationEnvelope serializationEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		serializationEnvelope.bodyOut = soapObject;
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
			httpTransportSE.call(SERVICE_NS + "/" + methodName, serializationEnvelope);
			// 获取返回的结果对象
			if (serializationEnvelope.getResponse() != null) {
				SoapObject result = (SoapObject) serializationEnvelope.bodyIn;
				Object obj = result.getProperty(methodName + "Result");
				retData = obj.toString();
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
