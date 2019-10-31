package com.base.commlibs.utils;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.PathUtils;

/**
 * 写入文件
 * @author:gaoruishan
 * @date:202019-09-29/09:22
 * @email:grs0515@163.com
 */
public class CommFile {

    public static final String IP = "ip";
    private static final String ROOT_PATH = PathUtils.getExternalStoragePath() + "/dhc/";

    /**
     * 写入文件 例如:CommFile.write("ip")
     * @param name 文件名称
     * @param json json数据
     */
    public static void write(String name, String json) {
        String finalName = getURLNameString(name);
        ThreadUtil.execute(new ThreadUtil.Task<String>() {
            @Override
            public String doInBackground() throws Throwable {
                writeFile(finalName, json);
                return null;
            }

            @Override
            public void onSuccess(String result) {

            }
        });
    }

    public static String getURLNameString(String name) {
        if (name.contains("http")) {
            String[] split = name.split("/");
            name = split[split.length - 1];
        }
        return name;
    }

    private static boolean writeFile(String finalName, String json) {
        String dhc = ROOT_PATH + finalName;
        return FileIOUtils.writeFileFromString(dhc, json + "");
    }

    /**
     * 读取文件
     * @param name 文件名
     * @param callBack 回调
     */
    public static void read(String name, SimpleCallBack<String> callBack) {
        ThreadUtil.execute(new ThreadUtil.Task<String>() {
            @Override
            public String doInBackground() throws Throwable {
                String dhc = ROOT_PATH + name;
                return FileIOUtils.readFile2String(dhc);
            }

            @Override
            public void onSuccess(String result) {
                if (callBack != null) {
                    callBack.call(result, 0);
                }
            }
        });
    }


    /**
     * 获取带有时间戳的.log文件
     * @param finalName
     * @return
     */
    public static String getLogName(String finalName) {
        return finalName + "_" + System.currentTimeMillis() + ".log";
    }

    /**
     * 获取json文件名
     * @param finalName
     * @param addTimeMillis
     * @return
     */
    public static String getNameJson(String finalName, boolean... addTimeMillis) {
        if (addTimeMillis != null && addTimeMillis.length > 0) {
            return finalName + "_" + System.currentTimeMillis() + ".json";
        }
        return finalName + ".json";
    }
}
