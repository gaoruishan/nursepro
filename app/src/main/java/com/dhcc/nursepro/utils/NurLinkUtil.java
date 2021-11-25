package com.dhcc.nursepro.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.base.commlibs.BaseActivity;
import com.blankj.utilcode.util.ActivityUtils;
import com.dhcc.nursepro.NApplication;


/**
 * 中间管理
 * @author:gaoruishan
 * @date:202021/11/25/17:41
 * @email:grs0515@163.com
 */
public class NurLinkUtil {

    public static Intent getMLinkIntent() {
//        return new Intent(ActivityUtils.getTopActivity().getApplicationContext(), com.example.dhcc_nurlink.MLinkServiceNewOrd.class);
        return null;
    }

    public static void openFragment(View nurLink) {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity instanceof BaseActivity) {
//            nurLink.setVisibility(View.VISIBLE);
//            nurLink.setOnClickListener((v)->((BaseActivity) topActivity).startFragment(com.example.dhcc_nurlink.fragment.NurLinkFragment.class));
        }
    }

    public static void init(NApplication application) {

//        java.util.concurrent.ScheduledExecutorService executorService = new java.util.concurrent.ScheduledThreadPoolExecutor(5,
//                new org.apache.commons.lang3.concurrent.BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d")
//                        .daemon(true).build());
//        com.example.dhcc_nurlink.greendao.GreenDaoHelper.initDatabase(application);
//        com.example.dhcc_nurlink.voiceplayutil.download.DownloadConfiguration downloadConfiguration = new com.example.dhcc_nurlink.voiceplayutil.download.DownloadConfiguration.Builder(application.getApplicationContext())
//                .setCacheDir(application.getExternalCacheDir())        //设置下载缓存目录，必须设置
//                .setTaskExecutor(executorService)    //同上传类似
//                .setThreadPriority(5)  //同上传类似
//                .setThreadPoolCoreSize(5)  //同上传类似
//                .build();
//        com.example.dhcc_nurlink.voiceplayutil.download.DownloadManager.getInstance(application).init(downloadConfiguration);

    }
}
