package com.dhcc.module.nurse.record;

import android.util.Log;

import com.base.commlibs.utils.CommRes;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.dhcc.module.nurse.BuildConfig;
import com.thoughtworks.xstream.XStream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author:gaoruishan
 * @date:202022/3/10/09:01
 * @email:grs0515@163.com
 */
public class XmlTest {
    private static XmlTest mConfigManager;
    private static XStream xStream;
    public Document document;

    public static XmlTest getInstance() {
        synchronized (XmlTest.class) {
            if (mConfigManager == null) {
                mConfigManager = new XmlTest();
                xStream = new XStreamEx();
            }
        }
        return mConfigManager;
    }


    public void readXmlDocument() {
        if (BuildConfig.DEBUG) {
            try {
                xStream.alias("Document", Document.class);
                xStream.autodetectAnnotations(true);
                InputStream inputStream = Utils.getApp().getAssets().open("Document1.xml");
                document = (Document) xStream.fromXML(inputStream);
                //输出
                LogUtils.json(LogUtils.E, GsonUtils.toJson(document));
            } catch (Exception e) {
                Log.e("TAG", "(XmlTest:68) readXmlDocument:" + e.toString());
            }
        }
    }

    public void readXmlDocumentJson() {
        if (BuildConfig.DEBUG) {
            try {
                xStream.alias("Document", Document.class);
                xStream.autodetectAnnotations(true);
                CommRes.readJson("getXmlDocument.json", new CommRes.CallRes<String>() {
                    @Override
                    public void call(String s, String json) {
                        XmlDocument xmlDocument = GsonUtils.fromJson(s, XmlDocument.class);
                        InputStream inputStream = getStringStream(xmlDocument.data);
                        document = (Document) xStream.fromXML(inputStream);
                        //输出
                        LogUtils.json(LogUtils.E, GsonUtils.toJson(document));
                    }
                });
            } catch (Exception e) {
                Log.e("TAG", "(XmlTest:68) readXmlDocument:" + e.toString());
            }
        }
    }

    public static InputStream getStringStream(String sInputString) {
        if (sInputString != null && !sInputString.trim().equals("")) {
            try {
                ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
                return tInputStringStream;
            } catch (Exception ex) {
                Log.e("TAG", "(XmlTest:81) getStringStream:" + ex.toString());
            }

        }
        return null;
    }

    public static class XmlDocument {

        /**
         * msg : err:请勿重复配液
         * msgcode : 199992
         * status : -1
         */
        public String msg;
        public String msgcode;
        public String status;
        public String data;

    }


}
