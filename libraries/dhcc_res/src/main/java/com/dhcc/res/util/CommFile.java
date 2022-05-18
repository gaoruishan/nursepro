package com.dhcc.res.util;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import com.base.commlibs.utils.SimpleCallBack;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.PathUtils;

import java.io.File;
import java.util.List;

/**
 * 写入文件
 * @author:gaoruishan
 * @date:202019-09-29/09:22
 * @email:grs0515@163.com
 */
public class CommFile {

    public static final String IP = "ip";
    public static final String TAG = "CommFile";
    public static final String ROOT_PATH = PathUtils.getExternalStoragePath() + "/dhc/";
    public static final int SIZE_100 = 100;

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

    public static boolean writeFile(String url, Bitmap bitmap) {
        String name = getURLNameString(url);
        Bitmap.CompressFormat format = Bitmap.CompressFormat.PNG;
        if (name.contains(".jpg") || name.contains(".jpeg")) {
            format = Bitmap.CompressFormat.JPEG;
        }
        String dhc = ROOT_PATH + name;
        return ImageUtils.save(bitmap, dhc, format);
    }
    public static boolean writeFile(String finalName, String json) {
        String dhc = ROOT_PATH + finalName;
        Log.e(TAG,"(CommFile.java:54) writeFile  name="+dhc);
        return FileIOUtils.writeFileFromString(dhc, json + "");
    }

    public static Bitmap readBitmap(String url) {
        String dhc = ROOT_PATH + getURLNameString(url);
        return ImageUtils.getBitmap(dhc);
    }

    /**
     * 是否是文件夹
     * @param path
     * @return
     */
    public static boolean isDir(String path) {
        String dhc = ROOT_PATH + path;
        return FileUtils.isDir(dhc);
    }
    public static boolean isFileExists(String path) {
        String dhc = ROOT_PATH + path;
        return FileUtils.isFileExists(dhc);
    }
    /**
     * 读取文件
     * @param name     文件名
     * @param callBack 回调
     */
    public static void read(String name, SimpleCallBack<String> callBack) {
        readFile2String(ROOT_PATH + name, callBack);
    }
    public static void readFile2String(String path, SimpleCallBack<String> callBack) {
        ThreadUtil.execute(new ThreadUtil.Task<String>() {
            @Override
            public String doInBackground() throws Throwable {
                return FileIOUtils.readFile2String(path);
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
     * 读取文件夹下的文件列表
     * @param path
     * @param callBack
     */
    public static void readFilesInDir(String path, SimpleCallBack<List<File>> callBack) {
        listFilesInDir(ROOT_PATH + path, callBack);
    }

    public static void listFilesInDir(String rootPath, SimpleCallBack<List<File>> callBack) {
        ThreadUtil.execute(new ThreadUtil.Task<List<File>>() {
            @Override
            public List<File> doInBackground() throws Throwable {
                return FileUtils.listFilesInDir(rootPath);
            }

            @Override
            public void onSuccess(List<File> result) {
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

    /**
     * 删除日志
     * @param code 版本号
     */
    public static void deleteLog(String code) {
        if (!TextUtils.isEmpty(code)) {
            try {
                int i = Integer.parseInt(code);
                if (AppUtils.getAppVersionCode() > i) {
                    CommFile.delete(ROOT_PATH);
                }
            } catch (Exception e) {

            }
        }
    }

    /**
     * 删除日志
     */
    public static void delete(String path) {
        Log.e(TAG, "(CommFile.java:103) delete");
        FileUtils.delete(path);
    }

    /**
     * 检查并删除
     */
    private static void checkAndDelete() {

        String dirSize = FileUtils.getDirSize(ROOT_PATH);
        double size = 0;
        if (dirSize.contains("MB")) {
            dirSize = dirSize.replace("MB", "");
            try {
                 size = Double.valueOf(dirSize);
                 //超过100M
                if (size >= SIZE_100) {
                    CommFile.delete(ROOT_PATH);
                }
            } catch (Exception e) {
                Log.e(TAG,"(CommFile.java:160) "+e.toString());
            }
        }
        if (dirSize.contains("GB")) {
            CommFile.delete(ROOT_PATH);
        }

    }
    /**
     * 检查日志过大
     */
    public static void checkLogSize() {
        ThreadUtil.execute(new ThreadUtil.Task<String>() {
            @Override
            public String doInBackground() throws Throwable {
                checkAndDelete();
                return null;
            }

            @Override
            public void onSuccess(String result) {

            }
        });
    }
}
