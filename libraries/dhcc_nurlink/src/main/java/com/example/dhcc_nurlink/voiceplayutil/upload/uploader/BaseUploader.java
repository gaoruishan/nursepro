package com.example.dhcc_nurlink.voiceplayutil.upload.uploader;


import com.example.dhcc_nurlink.voiceplayutil.upload.FileUploadInfo;
import com.example.dhcc_nurlink.voiceplayutil.upload.listener.OnFileTransferredListener;

import java.io.IOException;

/**
 * Created by hjy on 7/9/15.<br>
 */
public abstract class BaseUploader {

    public abstract String upload(FileUploadInfo fileUploadInfo, OnFileTransferredListener fileTransferredListener) throws IOException;

    public abstract void cancel(FileUploadInfo fileUploadInfo);

}
