package com.example.dhcc_nurlink.voiceplayutil.upload;

import android.content.Context;
import android.util.Log;


import com.example.dhcc_nurlink.voiceplayutil.upload.parser.BaseResponseParser;
import com.example.dhcc_nurlink.voiceplayutil.upload.uploader.BaseUploader;

import java.util.concurrent.Executor;

/**
 * Created by hjy on 7/8/15.<br>
 */
public class FileUploadConfiguration {

    private static final String TAG = "FileUploadConfiguration";

    private final Context mContext;
    private final Executor mTaskExecutor;
    private final boolean mIsCustomExecutor;
    private final BaseUploader mFileUploader;
    private final BaseResponseParser mResponseProcessor;

    private FileUploadConfiguration(Builder builder) {
        mContext = builder.context;
        mTaskExecutor = builder.taskExecutor;
        mIsCustomExecutor = builder.isCustomExecutor;
        mFileUploader = builder.fileUploader;
        mResponseProcessor = builder.responseProcessor;
    }

    public Context getContext() {
        return mContext;
    }

    public Executor getTaskExecutor() {
        return mTaskExecutor;
    }

    public boolean isCustomExecutor() {
        return mIsCustomExecutor;
    }

    public BaseUploader getFileUploader() {
        return mFileUploader;
    }

    public BaseResponseParser getResponseProcessor() {
        return mResponseProcessor;
    }

    /**
     * 构造器
     */
    public static class Builder {

        public static final int DEFAULT_THREAD_POOL_SIZE = 3;
        public static final int DEFAULT_THREAD_PRIORITY = Thread.NORM_PRIORITY - 1;

        private Context context;
        private Executor taskExecutor;
        private boolean isCustomExecutor;
        private BaseUploader fileUploader;
        private BaseResponseParser responseProcessor;

        private int threadPoolSize = DEFAULT_THREAD_POOL_SIZE;
        private int threadPriority = DEFAULT_THREAD_PRIORITY;

        public Builder(Context context) {
            this.context = context.getApplicationContext();
        }

        public Builder setTaskExecutor(Executor executor) {
            this.taskExecutor = executor;
            return this;
        }

        public Builder setThreadPoolSize(int size) {
            if(taskExecutor != null) {
                Log.d(TAG, "Call this no use because taskExecutor is not null.");
            }
            this.threadPoolSize = size;
            return this;
        }

        public Builder setThreadPriority(int priority) {
            if(taskExecutor != null) {
                Log.d(TAG, "Call this no use because taskExecutor is not null.");
            }

            if(priority < Thread.MIN_PRIORITY)
                this.threadPriority = Thread.MIN_PRIORITY;
            else {
                if(priority > Thread.MAX_PRIORITY)
                    this.threadPriority = Thread.MAX_PRIORITY;
                else
                    this.threadPriority = priority;
            }
            return this;
        }

        public Builder setFileUploader(BaseUploader uploader) {
            this.fileUploader = uploader;
            return this;
        }

        public Builder setResponseProcessor(BaseResponseParser responseProcessor) {
            this.responseProcessor = responseProcessor;
            return this;
        }

        /**
         * 构建FileUploadConfiguration
         *
         * @return
         */
        public FileUploadConfiguration build() {
            initEmptyFieldsWithDefaultValues();
            return new FileUploadConfiguration(this);
        }

        /**
         * 用默认值初始化所有没设置的参数
         */
        private void initEmptyFieldsWithDefaultValues() {
            if(taskExecutor == null) {
                taskExecutor = DefaultConfigurationFactory.createExecutor(threadPoolSize, threadPriority);
            } else {
                isCustomExecutor = true;
            }
            if(fileUploader == null) {
                fileUploader = DefaultConfigurationFactory.createDefaultUploader();
            }
            if(responseProcessor == null) {
                responseProcessor = DefaultConfigurationFactory.createDefaultResponseProcessor();
            }
        }

    }
}
