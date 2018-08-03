package com.dhcc.nursepro.utils;

/**
 * Created by levis on 2018/5/21.
 */

public class Value {
    //public static boolean s_printLog = BuildConfig.SJ_DEBUG; //开启Debug模式，打印Log，开启各种和Debug相关的开关
    public static boolean s_printLog = true; //开启Debug模式，打印Log，开启各种和Debug相关的开关
    public static final boolean LEAK_CANARY_SWITCH = false;

    public static final String RELEASE_NET_URL = "http://control.07faka.com/"; //线上地址
    //	public static final String COORDINATE_NET_URL = "https://dev.api.sjzhvip.com"; //联调地址
    public static final String COORDINATE_NET_URL = "http://control.07faka.com/"; //联调地址


    public final static int RELEASE_URL_SETTING = 1; //使用线上外网地址
    public final static int COORDINATE_URL_SETTING = 2; //使用联调地址
//	public final static int TEST_URL_SETTING = 3;//使用测试环境

    public final static String SERVER_ERROR_CODE = "XXXXXX";

    public final static String SERVER_ERROR_MSG = "服务器错误";

    private static String VersionName = "";
    private static String VersionNameForHttpHeader = "";

    public final static String VERSION_NAME_FOR_HEADER = VersionNameForHttpHeader; //应用版本号，用于在请求头中显示
    public final static String VERSION_NAME = VersionName; //应用版本号，显示在“关于”界面

}

