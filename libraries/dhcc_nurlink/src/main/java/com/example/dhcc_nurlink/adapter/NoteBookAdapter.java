package com.example.dhcc_nurlink.adapter;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dhcc_nurlink.R;
import com.example.dhcc_nurlink.bean.NoteBean;
import com.example.dhcc_nurlink.voiceplayutil.download.DownloadManager;
import com.example.dhcc_nurlink.voiceplayutil.download.FileDownloadTask;
import com.example.dhcc_nurlink.voiceplayutil.download.FileType;
import com.example.dhcc_nurlink.voiceplayutil.download.listener.OnDownloadingListener;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * com.dhcc.nursepro.setting.adapter
 * <p>
 * author Q
 * Date: 2020/9/29
 * Time:15:16
 */
public class NoteBookAdapter extends BaseQuickAdapter<NoteBean.SoundListBean, BaseViewHolder>   implements MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener{
    private int playPosition;

    public void setPlayPosition(int playPosition) {
        this.playPosition = playPosition;
    }

    public NoteBookAdapter(@Nullable List<NoteBean.SoundListBean> data) {
        super(R.layout.item_nurlink_note, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, NoteBean.SoundListBean item) {
        helper.setText(R.id.tv_datetime,item.getSoundDate()+" "+item.getSoundTime())
                .addOnClickListener(R.id.tv_playvoice)
                .addOnClickListener(R.id.ll_message_rightmenu)
                .addOnClickListener(R.id.patinfodetail_title)
                .addOnClickListener(R.id.ll_item_notemsg)
                .setGone(R.id.tv_playvoice,item.getSoundId().equals("0")?false:true);
        String itemCount = item.getSoundStr();
        if (itemCount.length()>10){
            itemCount = itemCount.substring(0,9)+"...";
        }
        TextView tvPositionPlay = helper.getView(R.id.tv_playvoice);
        tvPositionPlay.setSelected(false);
        if (item.getPatBed()!=null&&!item.getPatBed().isEmpty()){
            helper.setText(R.id.patinfodetail_title, item.getPatBed().replaceAll("床","")+"床--"+
                    item.getPatName()+"-"+item.getSoundType());
        }else {
            helper.setText(R.id.patinfodetail_title, itemCount);
        }
        LinearLayout llProgress = helper.getView(R.id.ll_voice_play_progress);
        if (playPosition==helper.getAdapterPosition()){
            llProgress.setVisibility(View.VISIBLE);


            seekBar = helper.getView(R.id.seekBar);
            tvCurrent = helper.getView(R.id.tv_current);
            tvDuration = helper.getView(R.id.tv_duration);
            tvPlay = helper.getView(R.id.tv_playvoice);
            if (mediaPlayer!=null&&mediaPlayer.isPlaying()) {
                stop();
            }

            tvPlay.setEnabled(false);
            tvPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
//                        tvPlay.setText("继续");
                        tvPlay.setSelected(false);
                    } else {
                        play();
//                        tvPlay.setText("暂停");
                        tvPlay.setSelected(true);
                    }
                }
            });
            seekBar.setProgress(0);//设置进度为0
            seekBar.setSecondaryProgress(0);//设置缓冲进度为0
            seekBar.setEnabled(false);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar mSeekBar, int progress, boolean fromUser) {
                    tvCurrent.setText(getTime(mSeekBar.getProgress()));
                }

                @Override
                public void onStartTrackingTouch(SeekBar mSeekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar mSeekBar) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.seekTo(mSeekBar.getProgress());
                        tvCurrent.setText(getTime(mSeekBar.getProgress()));
                    } else {
                        if (isPrepare) {
                            mediaPlayer.seekTo(mSeekBar.getProgress());
                            tvCurrent.setText(getTime(mSeekBar.getProgress()));
                            mediaPlayer.start();
                            tvPlay.setSelected(true);
                        }
                    }
                }
            });
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnBufferingUpdateListener(this);
                mediaPlayer.setOnPreparedListener(this);
                mediaPlayer.setOnCompletionListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            initTask();
            // 每一秒触发一次
            timer.schedule(timerTask, 0, 1000);

            init(PATH);
        }else {
            llProgress.setVisibility(View.GONE);
        }
    }


    public MediaPlayer mediaPlayer; // 媒体播放器
    private Timer timer = new Timer(); // 计时器
    private boolean isPrepare = false;
    private SeekBar seekBar;
    public   String PATH = "";
    private static final String PATH2 = "http://file.kuyinyun.com/group1/M00/90/B7/rBBGdFPXJNeAM-nhABeMElAM6bY151.mp3";
    private TextView tvCurrent;
    private TextView tvDuration;
    private TextView tvPlay;
    private int playTime=0;
    TimerTask timerTask;

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public void setPATH(String PATH) {
        this.PATH = PATH;
    }
    public void initTask(){
        timerTask = new TimerTask() {

            @Override
            public void run() {
                if (mediaPlayer == null) {
                    return;
                }
                if (mediaPlayer.isPlaying() && seekBar.isPressed() == false) {
                    handler.sendEmptyMessage(0);
                }
            }
        };
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (seekBar!=null && mediaPlayer !=null){
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                tvCurrent.setText(getTime(seekBar.getProgress()));
            }
        }
    };

    public void play() {
        if (isPrepare) {
            mediaPlayer.start();
        }
    }

    /**
     * @param url url地址
     */
    public void init(String url) {
        //下载和在线播放同步进行，因为音频文件受网速和大小影响，不能确定哪种方法能最快速播放，当下载完在线播放还没能开始时，就可以使用下载文件播放了
        //①下载
        DownloadManager.getInstance(mContext).downloadFile(FileType.TYPE_AUDIO, "111", url, new OnDownloadingListener() {
            @Override
            public void onDownloadFailed(FileDownloadTask task, int errorType, String msg) {
                Log.e(TAG, "ERR: " + msg);
            }

            @Override
            public void onDownloadSucc(FileDownloadTask task, File outFile) {
                Log.e(TAG, "file : " + outFile.getAbsolutePath());
                if (!isPrepare) {
                    seekBar.setSecondaryProgress(seekBar.getMax());
                    try {
                        if (mediaPlayer!=null){
                            mediaPlayer.reset();
                            mediaPlayer.setDataSource(outFile.getAbsolutePath());
                            mediaPlayer.prepareAsync();
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //②在线播放
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 停止
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // 播放准备
    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.e(TAG, "onPrepared:1111 " );
        isPrepare = true;
        seekBar.setEnabled(true);
        tvPlay.setEnabled(true);
        tvPlay.setSelected(true);
        seekBar.setMax(mp.getDuration());
        tvDuration.setText(getTime(mp.getDuration()));
        Log.e(TAG, "onPrepared");
        Log.e(TAG, "总长: " + mp.getDuration());

        if (playTime==0){
            play();
            playTime++;
        }else {
            tvPlay.setSelected(false);
        }

//        btnPlay.performClick();
    }

    // 播放完成
    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.e(TAG, "onCompletion");
        isPrepare = false;
        tvPlay.setSelected(false);
        tvCurrent.setText("00:00:00");
        seekBar.setProgress(0);
        init(PATH);
    }

    /**
     * 缓冲更新
     */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        seekBar.setSecondaryProgress(seekBar.getMax() * percent / 100);
        int currentProgress = seekBar.getMax() * mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration();
        Log.e(currentProgress + "% play", percent + " buffer");
        Log.e(TAG, "缓冲更新cur: " + mediaPlayer.getCurrentPosition() + " , dur: " + mediaPlayer.getDuration());
    }

    private String getTime(int duration) {
        int i = duration / 1000;
        int h = i / (60 * 60);
        String sh;
        if (h == 0) {
            sh = "00";
        } else {
            sh = String.valueOf(h);
        }
        int m = i / 60 % 60;
        String sm;
        if (m == 0) {
            sm = "00";
        } else {
            sm = String.valueOf(m);
            if (sm.length() == 1) {
                sm = "0" + sm;
            }
        }
        int s = i % 60;
        String ss;
        if (s == 0) {
            ss = "00";
        } else {
            ss = String.valueOf(s);
            if (ss.length() == 1) {
                ss = "0" + ss;
            }
        }
        return sh + ":" + sm + ":" + ss;
    }
}