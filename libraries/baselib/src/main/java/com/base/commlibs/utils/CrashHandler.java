package com.base.commlibs.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import com.base.commlibs.BaseApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 捕获异常工具类
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 0x100;
    //日志文件夹名
    private static final String DHC_CRASH = "dhc_crash";
    //默认异常处理器
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    //单例模式
    private static CrashHandler mInstance;
    private Context mContext;
    //应用相关信息
    private Map<String, String> mAppInfo = new HashMap<>();
    //保存文件日期格式化
    private DateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.CHINESE);

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (mInstance == null) {
            synchronized (CrashHandler.class) {
                if (mInstance == null) {
                    mInstance = new CrashHandler();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context) {
        this.mContext = context;
        //获得默认异常处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置我们自定义的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {

        //1.收集错误信息
        //2.保存错误信息
        //3.上传到服务器
        if (!handleException(throwable)) {
            //未处理,调用系统默认的处理器处理
            if (mDefaultHandler != null) {
                mDefaultHandler.uncaughtException(thread, throwable);
            }
        } else {
            //已经人为处理了
            try {
                //休眠一秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //销毁Activity
            BaseApplication.finishAll();
            //退出程序
            killAppProcess();
        }
    }

    /**
     * 手动处理异常
     *
     * @param throwable
     * @return true:已经处理 false:未处理
     */
    private synchronized boolean handleException(Throwable throwable) {

        if (throwable == null) {
            return false;
        }

        //在子线程弹出Toast
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "程序崩溃了", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        //收集错误信息
        collectErrorInfo();

        //保存错误信息
        saveErrorInfo(throwable);
        return true;
    }
    public void killAppProcess() {
        //注意：不能先杀掉主进程，否则逻辑代码无法继续执行，需先杀掉相关进程最后杀掉主进程
        ActivityManager mActivityManager = (ActivityManager) mContext.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> mList = mActivityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : mList) {
            if (runningAppProcessInfo.pid != android.os.Process.myPid()) {
                android.os.Process.killProcess(runningAppProcessInfo.pid);
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
    /**
     * 收集错误信息
     */
    private void collectErrorInfo() {
        PackageManager pm = mContext.getPackageManager();

        try {
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);

            if (pi != null) {
                String versionName = TextUtils.isEmpty(pi.versionName) ? "版本名称未设置" : pi.versionName;
                String versionCode = pi.versionCode + "";
                //存储信息到map
                mAppInfo.put("versionName", versionName);
                mAppInfo.put("versionCode", versionCode);
            }
            //通过反射获取构建信息
            Field[] fields = Build.class.getFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    mAppInfo.put(field.getName(), field.get(null).toString());
                }
            }
        } catch (PackageManager.NameNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存错误信息
     */
    private void saveErrorInfo(Throwable throwable) {

        StringBuffer errorInfo = new StringBuffer();
        for (Map.Entry<String, String> entry : mAppInfo.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            errorInfo.append(key).append("=").append(value).append("\n");
        }

        Writer writer1 = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer1);
        throwable.printStackTrace(printWriter);
        Throwable cause = throwable.getCause();
        //一直写直到写完
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }


        printWriter.close();

        String result = writer1.toString();
        errorInfo.append(result);

        //当前时间
        long curTime = System.currentTimeMillis();
        String time = mDateFormat.format(new Date());
        //组装文件名
        String fileName = "crash-" + time + "-" + curTime + ".log";

        //判断是否有SD卡
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //存在SD卡
            String filePath = Environment.getExternalStorageDirectory().getPath()
                    + File.separator + DHC_CRASH + File.separator;


            File fileDir = new File(filePath);
            if (!fileDir.exists()) {
                //文件夹不存在则创建(可创建多级文件夹)
                fileDir.mkdirs();
            }
            //写错误信息到文件
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(filePath + File.separator + fileName);

                fos.write(errorInfo.toString().getBytes());
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
