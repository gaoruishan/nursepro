package com.dhcc.module.nurse.test;

import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.dhcc.module.nurse.BuildConfig;
import com.thoughtworks.xstream.XStream;

import java.io.InputStream;

/**
 * @author:gaoruishan
 * @date:202022/3/10/09:01
 * @email:grs0515@163.com
 */
public class XmlTest {
    private static XmlTest mConfigManager;
    private static XStream xStream;

    public static XmlTest getInstance() {
        synchronized (XmlTest.class) {
            if (mConfigManager == null) {
                mConfigManager = new XmlTest();
                xStream = new XStream();
            }
        }
        return mConfigManager;
    }

    public void readXmlProjectConfig() {
        if (BuildConfig.DEBUG) {
            try {
                xStream.alias("ConfigTest", ConfigTest.class);
                xStream.autodetectAnnotations(true);
                InputStream inputStream = Utils.getApp().getAssets().open("ConfigTest.xml");
                ConfigTest config = (ConfigTest) xStream.fromXML(inputStream);
                Log.e("TAG", "(XmlTest:42) readXmlProjectConfig:" + config.toString());

            } catch (Exception e) {
            }
        }
    }

    public void readXmlConfigSystems() {
        if (BuildConfig.DEBUG) {
            try {
                xStream.alias("ConfigSystems", ConfigSystems.class);
                xStream.autodetectAnnotations(true);
                InputStream inputStream= Utils.getApp().getAssets().open("ConfigSystems.xml");
                ConfigSystems config = (ConfigSystems) xStream.fromXML(inputStream);
                Log.e("TAG", "(XmlTest:42) readXmlConfigSystems:" + config.toString());
            } catch (Exception e) {
            }
        }
    }


}
