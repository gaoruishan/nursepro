package com.base.commlibs.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.blankj.utilcode.util.Utils;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/09/25
 *     desc  : 剪贴板相关工具类
 * </pre>
 */
public final class ClipboardUtils {


    private static final String TAG = ClipboardUtils.class.getSimpleName();

    private ClipboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 复制文本到剪贴板
     * @param text 文本
     */
    public static void copyText(final CharSequence text) {
        ClipboardManager cm = (ClipboardManager) Utils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        cm.setPrimaryClip(ClipData.newPlainText("text", text));
    }

    /**
     * 获取剪贴板的文本
     * @return 剪贴板的文本
     */
    public static CharSequence getText() {
        ClipboardManager cm = (ClipboardManager) Utils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        ClipData clip = cm.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).coerceToText(Utils.getApp());
        }
        return null;
    }

    /**
     * 清空剪贴板内容
     */
    public static void clearClipboard() {
        ClipboardManager manager = (ClipboardManager) Utils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null) {
            try {
                manager.setPrimaryClip(manager.getPrimaryClip());
                manager.setText(null);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    /**
     * 剪贴板的数据改变监听
     * @param what
     */
    public static void addPrimaryClipChangedListener(ClipboardManager.OnPrimaryClipChangedListener what) {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) Utils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        // 添加剪贴板数据改变监听器
        if (clipboard != null && what != null) {
            clipboard.addPrimaryClipChangedListener(what);
        }
        // 移除指定的剪贴板数据改变监听器
        // clipboard.removePrimaryClipChangedListener(listener);
    }

    /**
     * 移除监听器
     * @param listener
     */
    public static void removePrimaryClipChangedListener(ClipboardManager.OnPrimaryClipChangedListener listener) {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) Utils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        // 移除指定的剪贴板数据改变监听器
        if (clipboard != null) {
            clipboard.removePrimaryClipChangedListener(listener);
        }
    }

    /**
     * 复制uri到剪贴板
     * @param uri uri
     */
    public static void copyUri(final Uri uri) {
        ClipboardManager cm = (ClipboardManager) Utils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        cm.setPrimaryClip(ClipData.newUri(Utils.getApp().getContentResolver(), "uri", uri));
    }

    /**
     * 获取剪贴板的uri
     * @return 剪贴板的uri
     */
    public static Uri getUri() {
        ClipboardManager cm = (ClipboardManager) Utils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        ClipData clip = cm.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).getUri();
        }
        return null;
    }

    /**
     * 复制意图到剪贴板
     * @param intent 意图
     */
    public static void copyIntent(final Intent intent) {
        ClipboardManager cm = (ClipboardManager) Utils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        cm.setPrimaryClip(ClipData.newIntent("intent", intent));
    }

    /**
     * 获取剪贴板的意图
     * @return 剪贴板的意图
     */
    public static Intent getIntent() {
        ClipboardManager cm = (ClipboardManager) Utils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        ClipData clip = cm.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).getIntent();
        }
        return null;
    }
}