package com.base.commlibs.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.support.annotation.RawRes;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

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

    private static final String TAG = CommRes.class.getSimpleName();

    /**
     * 通过图片名称,获取drawable下
     * @param context
     * @param imageName
     * @return
     */
    public static @DrawableRes
    int getDrawableRes(Context context, String imageName) {
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
        readJson(name, new CommRes.CallRes<String>() {
            @Override
            public void call(String s, String json) {
                try {
                    T t = new Gson().fromJson(s, clz);
                    callRes.call(t, s);
                } catch (JsonSyntaxException ex) {
                    callRes.call(null, s);
                }
            }
        });
    }

    public static void readJson(String name, final CallRes<String> callRes) {
        MsgHandler handler = new MsgHandler(callRes);
        new ResThread(name, ResType.Assets, new CallResString() {
            @Override
            public void call(String s) {
                Message message = new Message();
                message.obj = s;
                handler.sendMessage(message);
            }
        }).start();
    }

    /**
     * 加载本地Drawable 或网络图片
     * @param mContext
     * @param imageView
     * @param image
     */
    public static void loadDrawableOrNetwork(Context mContext, ImageView imageView, String image) {
        if (TextUtils.isEmpty(image) || imageView == null) {
            return;
        }
        Log.e(TAG,"(CommRes.java:79) "+image);
        if (HttpUtil.isImage(image)) {
            HttpUtil.bindImage(imageView, image);
        }else {
            int drawableRes = CommRes.getDrawableRes(mContext, image);
            if (drawableRes != 0) {
                imageView.setImageResource(drawableRes);
            }
        }
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

    private static class MsgHandler extends Handler {
        private CallRes<String> callRes;

        MsgHandler(CallRes<String> callRes) {
            this.callRes = callRes;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            callRes.call((String) msg.obj, (String) msg.obj);
        }
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
