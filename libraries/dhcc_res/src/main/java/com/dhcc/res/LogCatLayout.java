package com.dhcc.res;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.grs.dhcc_res.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:gaoruishan
 * @date:202020-04-29/09:40
 * @email:grs0515@163.com
 */
public class LogCatLayout extends LinearLayout implements View.OnClickListener {
    private final String TAG;
    private final int mPId;
    private final String mPID;
    private final String DIR = Environment.getExternalStorageDirectory().getPath() + "/dhc/log";
    private String cmds;
    private Context mContext;
    private TextView tvlog;
    private Button btnStop;
    private Button btnSave;
    private Button btnClear;
    private Process exec;
    private BufferedReader mReader;
    private RandomAccessFile randomFile = null;
    private boolean mRunning = false;
    private float touchDownX;
    private boolean mScrolling;
    private MyHandler mhandler = new MyHandler(this);
    private Thread thread;
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

    public LogCatLayout(Context context) {
        this(context, null);
    }

    public LogCatLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LogCatLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TAG = this.getClass().getSimpleName();
        setOrientation(VERTICAL);
        setBackgroundColor(ContextCompat.getColor(context, R.color.dhcc_black_half_transprent));

        View view = LayoutInflater.from(context).inflate(R.layout.logcat_layout, this, false);
        addView(view);

        mPId = android.os.Process.myPid();
        mPID = String.valueOf(mPId);
        //        cmds = "logcat  *:e *:d | grep \"(" + mPID + ")\"";
        cmds = "logcat  *:e | grep \"(" + mPID + ")\"";

        tvlog = findViewById(R.id.tv_logs);
        tvlog.setFocusable(false);
        tvlog.setFocusableInTouchMode(false);
        tvlog.setClickable(false);
        btnStop = findViewById(R.id.btn_stop);
        btnStop.setOnClickListener(this);
        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
        btnClear = findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(this);


        // ????????????????????? ?????? ????????? setOnTouchListener ??????
//        setOnTouchListener(new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            //???????????????mGestureDetector???onTouchEvent??????????????????????????????
//            return new GestureDetector(mContext, new GestureDetector.OnGestureListener() {
//                @Override
//                public boolean onDown(MotionEvent e) {
//                    Log.e(TAG,"(LogCatLayout.java:97) onDown");
//                    // ?????????????????????
//                    //????????? ?????????????????????????????????????????????true
//                    return false;
//                }
//
//                @Override
//                public void onShowPress(MotionEvent e) {
//                    // ????????????????????????????????????????????????????????????????????????????????????
//                    Log.e(TAG,"(LogCatLayout.java:97) onShowPress");
//                }
//
//                @Override
//                public boolean onSingleTapUp(MotionEvent e) {
//                    Log.e(TAG,"(LogCatLayout.java:97) onSingleTapUp");
//                    // ?????????????????????????????????(??????????????????????????????????????????????????????)
//
//                    //??????????????????????????????????????????????????????  ?????????????????????????????????
//                    //????????????????????????????????????????????????
//                    float x=e.getX();                   //?????????????????????x
//                    //width????????????1/3???  ????????????????????????????????? ????????? ????????????
////                    if(x>0&&x<=width){
////                        DownLeft();     //??? ?????????????????????????????????
////                    }else if(x>width&&x<=width*2){
////                        DownHit();   //???  ?????????????????????????????????
////                    }else{
////                        DownRight();   //???  ?????????????????????????????????
////                    }
//
//                    return false;
//                }
//
//                @Override
//                public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//                    Log.e(TAG,"(LogCatLayout.java:97) onScroll");
//                    // ???????????????????????????????????????
//                    return false;
//                }
//
//                @Override
//                public void onLongPress(MotionEvent e) {
//                    Log.e(TAG,"(LogCatLayout.java:97) onLongPress");
//                    // ??????????????????????????????????????????????????????????????????????????????
//                }
//
//                @Override
//                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                    Log.e(TAG,"(LogCatLayout.java:97) onFling");
//                    // ??????????????????????????????????????????????????????
//                    //?????????????????????????????????   ?????????????????????????????????
////                    if(e1.getX()>e2.getX()){  //??????????????????????????????????????????????????????
////                        DownRight();    //???????????????   ??????
////                    }else{
////                        DownLeft();      //???????????????   ??????
////                    }
//                    return false;
//                }
//            }).onTouchEvent(event);
//        }
//    });
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        Log.e(TAG, "(LogCatLayout.java:92) onVisibilityChanged=" + visibility);
    }

    public void startRunThread(String cmds, boolean mRunning) {
        if (!TextUtils.isEmpty(cmds)) {
            this.cmds = cmds;
            startRunThread(mRunning);
        }
    }

    public void startRunThread(boolean mRunning) {
        try {
            exec = Runtime.getRuntime().exec(cmds);
            mReader = new BufferedReader(new InputStreamReader(exec.getInputStream()), 1024);
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    doBackground(mRunning);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doBackground(boolean mRunning) {
        String line = "";
        try {
            while (mRunning && (line = mReader.readLine()) != null) {
                if (!mRunning) {
                    break;
                }
                if (line.length() == 0) {
                    continue;
                }
                final Message msg = Message.obtain();
                msg.what = 2;
                msg.obj = line + "\n";
                mhandler.sendMessage(msg);
                Thread.sleep(100);
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        } finally {
            Log.e(TAG, "finally");
            Log.e(TAG, "" + mRunning);
            if (line == null) {
                Log.e(TAG, "line is null");
            }
            if (exec != null) {
                exec.destroy();
            }
            if (mReader != null) {
                try {
                    mReader.close();
                    mReader = null;
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * View?????????
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e(TAG, "(LogCatLayout.java:165) onDetachedFromWindow");
        mRunning = false;
        if (exec != null) {
            exec.destroy();
        }
        if (mReader != null) {
            try {
                mReader.close();
                mReader = null;
            } catch (IOException e) {
            }
        }
        //????????????????????????
        if (mhandler != null) {
            mhandler.removeCallbacksAndMessages(null);
        }
        fixedThreadPool.shutdown();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_stop) {
            mRunning = !mRunning;
            startRunThread(mRunning);
            btnStop.setText(mRunning ? "??????" : "??????");
        } else if (id == R.id.btn_save) {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-ddHHmmss", Locale.CHINA);
            String date = format1.format(new Date(System.currentTimeMillis()));
            try {
                File fileDir = new File(DIR);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                String path = DIR + "/" + date + ".log";
                randomFile = new RandomAccessFile(path, "rw");
                randomFile.write(tvlog.getText().toString().getBytes());
                Toast.makeText(mContext, "??????????????????,path=" + path, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(mContext, "??????????????????:??????????????????", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else if (id == R.id.btn_clear) {
            tvlog.setText("");
        }
    }

    private static class MyHandler extends Handler {

        WeakReference<LogCatLayout> weakReference;

        MyHandler(LogCatLayout testActivity) {
            this.weakReference = new WeakReference<LogCatLayout>(testActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 2:
                    //????????????
                    TextView tvlog = weakReference.get().tvlog;
                    tvlog.setMovementMethod(ScrollingMovementMethod.getInstance());
//                    etlog.setSelection(etlog.getText().length(), etlog.getText().length());
                    tvlog.setText(tvlog.getText() + msg.obj.toString());
                    break;
                default:
                    break;
            }

        }
    }
}
