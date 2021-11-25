package com.example.dhcc_nurlink.Utils;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import android.util.Log;

import com.base.commlibs.constant.Action;
import com.blankj.utilcode.util.ToastUtils;
import com.example.dhcc_nurlink.R;

import java.io.IOException;
import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.workareautils
 * <p>
 * author Q
 * Date: 2020/8/5
 * Time:16:58
 */
public class RingPlayUtil {
    private Context mContext;

    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundPoolMap = new HashMap<Integer, Integer>();
    MediaPlayer mMediaPlayer;
    Ringtone mRingtone;
    public RingPlayUtil(Context context){
        this.mContext=context;
    }

    public void playSoundByPool(int sound, int loop) {
        //提示音集合
        soundPool=new  SoundPool(100,AudioManager.STREAM_MUSIC,5);//构建对象
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(sampleId,1,1,1,0,1);//播放
            }
        });
        soundPool.load(mContext,R.raw.ring,1);//加载资源

        //参数：1、Map中取值   2、当前音量     3、最大音量  4、优先级   5、重播次数   6、播放速度

    }
    public void playSoundByMediaPlayer() {
        if (mMediaPlayer !=null &&mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
//            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE) ;
//            mMediaPlayer=MediaPlayer.create(mContext, uri);
            mMediaPlayer=MediaPlayer.create(mContext, R.raw.nurlink_ring_callin);
            mMediaPlayer.start();
            Log.e("11111111", "playSoundByMediaPlayer:2222 " );
        }else {
//            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE) ;
//            mMediaPlayer=MediaPlayer.create(mContext, uri);
            mMediaPlayer=MediaPlayer.create(mContext, R.raw.nurlink_ring_callin);
            mMediaPlayer.start();
            Log.e("11111111", "playSoundByMediaPlayer:2222111 "+mContext );
        }

    }

    public void sendSoundByMediaPlayer(Context context) {
        MediaPlayer mediaPlayer1 = new MediaPlayer();

//        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE) ;
//        mediaPlayer1=MediaPlayer.create(mContext, uri);
        mediaPlayer1=MediaPlayer.create(mContext, R.raw.wakeup);
        mediaPlayer1.start();
        mediaPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                ToastUtils.showLong("开始留言");

                Intent i = new Intent();
                i.setAction(Action.CALL_RING_SENDMSG);
                context.sendBroadcast(i);

            }
        });
//        if (mMediaPlayer !=null &&mMediaPlayer.isPlaying()){
//            mMediaPlayer.stop();
////            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE) ;
////            mMediaPlayer=MediaPlayer.create(mContext, uri);
//            mMediaPlayer=MediaPlayer.create(mContext, R.raw.ring);
//            mMediaPlayer.start();
//            Log.e("11111111", "playSoundByMediaPlayer:2222 " );
//            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    ToastUtils.showLong("开始留言");
//                }
//            });
//            Log.e("11111111", "playSoundByMediaPlayer:2222111 "+mContext );
//        }else {
////            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE) ;
////            mMediaPlayer=MediaPlayer.create(mContext, uri);
//            mMediaPlayer=MediaPlayer.create(mContext, R.raw.ring);
//            mMediaPlayer.start();
//            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    ToastUtils.showLong("开始留言");
//                }
//            });
//            Log.e("11111111", "playSoundByMediaPlayer:2222111 "+mContext );
//        }

    }

    public void msgRingByMediaPlayer(Context context) {

        if (mMediaPlayer !=null &&mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
            mMediaPlayer=MediaPlayer.create(mContext, R.raw.msg_ring);
            mMediaPlayer.start();
        }else {
            mMediaPlayer=MediaPlayer.create(mContext, R.raw.msg_ring);
            mMediaPlayer.start();
        }
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
//                ToastUtils.showLong("危急值消息播放结束");
                stopPlay();
            }
        });

    }

    public void ringByRaw(Context context,int url) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, 6, AudioManager.FLAG_PLAY_SOUND);
//                audioManager.setRingerMode(2);
        if (mMediaPlayer !=null &&mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
            mMediaPlayer=new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
            mMediaPlayer=MediaPlayer.create(mContext,url);
            mMediaPlayer.start();
        }else {
            mMediaPlayer=new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
            mMediaPlayer=MediaPlayer.create(mContext,url);
            mMediaPlayer.start();
        }
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
//                ToastUtils.showLong("危急值消息播放结束");
                stopPlay();
                if (ringStopListner!=null){
                    ringStopListner.ringStopCallback();
                }
            }
        });

    }
    RingStopListner ringStopListner;

    public void setRingStopListner(RingStopListner ringStopListner) {
        this.ringStopListner = ringStopListner;
    }

    public interface RingStopListner{
        void ringStopCallback();
    }

    /**
     * 播放来电铃声的默认音乐
     */
    public void playRingtoneDefault(){
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE) ;
        Log.e("11111ring", "playRingtoneDefault: "+uri );
        mRingtone = RingtoneManager.getRingtone(mContext,uri);
        mRingtone.play();
    }
    public void stopPlay(){
        if (mRingtone!=null){
            mRingtone.stop();
        }
        if (mMediaPlayer!=null&&mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
        }
    }

    public void playMp3ByUrl(String url){
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mMediaPlayer.setOnBufferingUpdateListener(mContext);
//            mMediaPlayer.setOnPreparedListener(mContext);
//            mMediaPlayer.setOnCompletionListener(mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepareAsync();
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

    /**
     * 随机播放一个Ringtone(有可能是提示音、铃声等)
     */
    private void ShufflePlayback(){
        RingtoneManager manager = new RingtoneManager(mContext) ;
        Cursor cursor = manager.getCursor();
        int count = cursor.getCount() ;
        int position = (int)(Math.random()*count) ;
        Ringtone mRingtone = manager.getRingtone(position) ;
        mRingtone.play();
    }
}
