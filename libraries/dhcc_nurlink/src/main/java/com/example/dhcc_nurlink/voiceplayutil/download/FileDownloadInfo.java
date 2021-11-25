package com.example.dhcc_nurlink.voiceplayutil.download;


import com.example.dhcc_nurlink.voiceplayutil.download.listener.OnDownloadProgressListener;
import com.example.dhcc_nurlink.voiceplayutil.download.listener.OnDownloadingListener;

import java.io.File;

/**
 * Created by qugengting on 8/5/15.<br>
 */
public class FileDownloadInfo {

    private String id;

    private String url;

    private File outFile;

    private OnDownloadingListener onDownloadingListener;

    private OnDownloadProgressListener onDownloadProgressListener;

    public FileDownloadInfo(String id, String url, File outFile, OnDownloadingListener onDownloadingListener, OnDownloadProgressListener onDownloadProgressListener) {
        this.id = id;
        this.url = url;
        this.outFile = outFile;
        this.onDownloadingListener = onDownloadingListener;
        this.onDownloadProgressListener = onDownloadProgressListener;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OnDownloadingListener getOnDownloadingListener() {
        return onDownloadingListener;
    }

    public void setOnDownloadingListener(OnDownloadingListener onDownloadingListener) {
        this.onDownloadingListener = onDownloadingListener;
    }

    public OnDownloadProgressListener getOnDownloadProgressListener() {
        return onDownloadProgressListener;
    }

    public void setOnDownloadProgressListener(OnDownloadProgressListener onDownloadProgressListener) {
        this.onDownloadProgressListener = onDownloadProgressListener;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public File getOutFile() {
        return outFile;
    }

    public void setOutFile(File outFile) {
        this.outFile = outFile;
    }
}