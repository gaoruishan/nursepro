package com.base.commlibs.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.RawRes;

import com.blankj.utilcode.util.ResourceUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * 读取资源
 * @author:gaoruishan
 * @date:202019-07-04/11:02
 * @email:grs0515@163.com
 */
public class CommRes {

    /**
     * 通过图片名称,获取drawable下
     * @param context
     * @param imageName
     * @return
     */
    public static @DrawableRes int getDrawableRes(Context context, String imageName) {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }

    /**
     * 读取json
     * 注: 静态方法使用泛型，必须加static <T>   。
     * @param name
     * @param clz
     * @param callRes
     * @param <T>
     */
    public static <T> void readJson(String name, final Class<T> clz, final CallRes<T> callRes) {
        new ResThread(name, ResType.Assets, new CallResString() {
            @Override
            public void call(String s) {
                try {
                    T t = new Gson().fromJson(s, clz);
                    callRes.call(t, s);
                } catch (JsonSyntaxException ex) {
                    callRes.call(null, s);
                }
            }
        }).start();
    }

    /**
     * 资源类型
     */
    enum ResType {
        //assets目录下资源
        Assets,
        //raw目录下资源
        Raw
    }

    /**
     * 回调指定类型
     */
    public interface CallRes<T> {
        void call(T t, String s);
    }

    private interface CallResString {
        void call(String t);
    }

    private static class ResThread extends Thread {
        private String name;
        private int resId;
        private CallResString callRes;
        private ResType type;

        ResThread(String assetsFilePath, ResType type, CallResString callRes) {
            this.name = assetsFilePath;
            this.type = type;
            this.callRes = callRes;
        }

        ResThread(@RawRes int resId, ResType type, CallResString callRes) {
            this.resId = resId;
            this.type = type;
            this.callRes = callRes;
        }

        @Override
        public void run() {
            super.run();
            String s = null;
            if (type == ResType.Assets) {
                s = ResourceUtils.readAssets2String(name);
            }
            if (type == ResType.Raw) {
                s = ResourceUtils.readRaw2String(resId);
            }
            callRes.call(s);
        }
    }
}