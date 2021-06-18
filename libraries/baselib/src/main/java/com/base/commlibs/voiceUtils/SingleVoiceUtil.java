package com.base.commlibs.voiceUtils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.R;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.voiceUtils.bean.BedMapBean;
import com.base.commlibs.voiceUtils.bean.VoiceBean;
import com.base.commlibs.voiceUtils.bean.VoiceVisalBean;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.raisound.speech.AsrResult;
import com.raisound.speech.SpeechError;
import com.raisound.speech.SpeechRecognizerManager;
import com.raisound.speech.http.callback.RequestCallback;
import com.raisound.speech.http.response.Scene;
import com.raisound.speech.listener.RecognizerListener;
import com.raisound.speech.listener.SceneListResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * com.base.commlibs.voiceUtils
 * <p>
 * author Q
 * Date: 2021/6/15
 * Time:16:27
 */
public class SingleVoiceUtil {

    private Context mContext;
    public SingleVoiceUtil(Context context, Button btnVoice){
        this.mContext = context;
        this.btnVoice = btnVoice;
    }

    private String TAG="VoiceUtil.class";
    public Button btnVoice;
    //第一次点击不用计时
    public Boolean firstClick=true;
    public Long curTime = System.currentTimeMillis();

    public AsrDialog mAsrDialog;
    //通过标题或setScene判断当前场景
    public String centerTitle = "";
    //备忘录用，设置当前语音id
    public String meetingId = "";
    //备忘录用，startRecord只调用一次
    public Boolean isStart = true;
    //服务端生成x的id。通过id获取音频地址
    public String fileId = "";
    public Boolean isActionUp = true;


    public String bedNoByVoice = "";



    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public void  initVoice(){
        btnVoice.setOnTouchListener(btnVoiceTouchListener);

    }

    public void onFragmentDestroy(){
        speechStop();
        finishRecord();
        handler.removeCallbacksAndMessages(null);
        handler1.removeCallbacksAndMessages(null);
        SpeechRecognizerManager.setListener(null);
    }
    public void actionDown() {
//        getBedMap();
        VoiceWebDataUtil.getBedMapData();
        VoiceWebDataUtil.getVisData();
        if (firstClick) {
            firstClick = false;
        } else {
            if (System.currentTimeMillis() - curTime < 1000) {
                ToastUtils.cancel();
                ToastUtils.showShort("不可频繁点击");
                return;
            } else {
                curTime = System.currentTimeMillis();
            }
        }
        isActionUp = false;
        SpeechRecognizerManager.switchScene(getScene());
        SpeechRecognizerManager.setListener(speechListener);
        Log.e(TAG, "actionDown: 111111" + getScene());

        if (centerTitle.equals("转写场景") && !meetingId.isEmpty() && isStart) {
            SpeechRecognizerManager.switchScene(getScene());
            Log.e(TAG, "actionDown: " + getScene());
            SpeechRecognizerManager.startRecord(meetingId, new RequestCallback() {
                @Override
                public void onSuccess(int i, Object o) {
                    fileId = o.toString();
                    Log.e("mp3", meetingId + "startRecord fileId:" + fileId + getScene());
                }

                @Override
                public void onFailure(int i, String s) {
                    Log.e("mp3", meetingId + "startRecord error:" + s + getScene());
                }
            });
            isStart = false;
        }


        //开始转写
        if (centerTitle.equals("转写场景")) {
            SpeechRecognizerManager.start(meetingId);
        } else {
            SpeechRecognizerManager.startOnPush();
        }


        if (mAsrDialog == null) {
            mAsrDialog = new AsrDialog(mContext);
            mAsrDialog.show();
            mAsrDialog.setCanceledOnTouchOutside(false);
            mAsrDialog.setCancelable(false);
            mAsrDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    actionUp();
                }
            });
        }
    }
    public void actionUp(){
        isActionUp = true;
        //stop,等待转写结果，默认3s，设置请参考设置部分的代码
        speechStop();
    }

    public void setScene(String scene){
        centerTitle = scene;
        Log.e(TAG, "setScene: "+scene );
    }
    public String getScene(){
        String speechScene = "donghua_1_1";
        if (SharedPreference.SCENE_LIST!=null){
            if (SharedPreference.SCENE_LIST.size()<1){
                ToastUtils.showLong("未获取场景");
            }else {
                speechScene = SharedPreference.SCENE_LIST.get(0).getSceneId();
                for (int i = 0; i < SharedPreference.SCENE_LIST.size(); i++) {
                    if (centerTitle.equals(SharedPreference.SCENE_LIST.get(i).getSceneName())){
                        speechScene = SharedPreference.SCENE_LIST.get(i).getSceneId();
                    }
                }
            }
        }else {
//            showToastByVoice("未获取场景");
            SpeechRecognizerManager.getSceneList(new SceneListResponse() {
                @Override
                public void onResponse(List<Scene> list, int i, String s) {
                    if (list.size()>0){
                        SharedPreference.SCENE_LIST = list;
                    }
                }
            });

        }
        Log.e(TAG, "getScene: "+centerTitle+"--"+speechScene );
        return speechScene;
    }

    public static <T> byte[] concat(byte[] first, byte[] second) {
        byte[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
    private Handler handler=new Handler();
    private List<byte[]> fileList = new ArrayList<>();

    public int getFileListSize(){
        return fileList.size();
    }
    private byte[] bytesAll;
    private int testInt=0;
    private View.OnTouchListener btnVoiceTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int ea = event.getAction();
            switch (ea) {
                case MotionEvent.ACTION_DOWN:
                    btnVoice.setSelected(true);
                    testInt = 1;
                    actionDown();
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    btnVoice.setSelected(false);
                    if (mAsrDialog!=null){
                        mAsrDialog.setTipText("正在获取结果，请稍后...");
                    }
                    actionUp();
                    handler1.postDelayed(runnable, 3000);
                    break;
            }
            return false;
        }
    };
    public RecognizerListener speechListener = new RecognizerListener() {
        @Override
        public void onRecordData(byte[] bytes) {
            //fileList.add(bytes);//音频保存到本地
        }

        @Override
        public void onVolumeChanged(int i) {
            //返回音量变化，自定义界面展示录音动画
            showWave(i);
        }

        @Override
        public void onBeginOfSpeech() {
            Log.i(TAG+"json", "开始录音");
            new Thread(new Runnable() {//创建异步线程环境
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mAsrDialog != null) {
                                mAsrDialog.setTipText("正在录音,请讲话...");
                            }
                        }
                    });
                }
            }).start();

        }

        @Override
        public void onEndOfSpeech() {
            Log.i(TAG+"json5", "录音结束");
            new Thread(new Runnable() {//创建异步线程环境
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String ss = "/sdcard/" + meetingId+".wav";
                            File outFile = new File(ss);
                            if (mAsrDialog != null) {
                                mAsrDialog.setTipText("正在获取结果，请稍后...");
//                                mAsrDialog.dismiss();
                            }
                        }
                    });
                }
            }).start();

        }

        @Override
        public void onResult(AsrResult asrResult, boolean isLast) {
            voicResult(asrResult,isLast);
        }

        @Override
        public void onError(final SpeechError speechError) {
            Log.e("onError",speechError.getErrorDescription());
            //SpeechError speechError.getErrorCode() 错误码请参考接入文档
            Message message = new Message();
            message.what = 99;
            message.obj = speechError.getErrorDescription();
            speechStop();
            if (mAsrDialog != null) {
                Log.e(TAG, "run: 11111"+"3" );
                mAsrDialog.dismiss();
                handler1.removeCallbacks(runnable);
                mAsrDialog = null;
                ToastUtils.showLong(speechError.getErrorDescription());
            }
            return;
        }
    };

    public Handler handler1=new Handler();
    public  Runnable runnable=new Runnable() {
        @Override
        public void run() {
            Log.e(TAG, "run: 11111"+"2111" );
            if (mAsrDialog!=null
                    &&mAsrDialog.isShowing()){
//                SpeechRecognizerManager.stopNow();
                Log.e(TAG, "run: 11111"+"2" );
                mAsrDialog.dismiss();
                handler1.removeCallbacks(runnable);
                mAsrDialog=null;
            }
        }
    };

    public void hindBtn(){
        btnVoice.setVisibility(View.GONE);
    }
    public void showBtn(){
        btnVoice.setVisibility(View.VISIBLE);
    }

    public void  speechStop(){
        Log.e(TAG, "speechStop: "+centerTitle );
        if (centerTitle.equals("转写场景")){
            SpeechRecognizerManager.stop();
        }else {
            SpeechRecognizerManager.stopOnPush();
        }
    }
    public void finishRecord(){
        if (centerTitle.equals("转写场景")&& !meetingId.isEmpty()){
            SpeechRecognizerManager.finishRecord(fileId, new RequestCallback() {
                @Override
                public void onSuccess(int i, Object o) {
                    Log.e("mp3","finishRecord fileId:"+fileId);
//                    saveSoundByNurlink();
                }

                @Override
                public void onFailure(int i, String s) {
                    Log.e("mp3","finishRecord error:"+s);
                }
            });

        }
    }

    public void showWave(int value) {
        //Log.i(TAG, value+"");
        if (mAsrDialog != null){
            new Thread(new Runnable() {//创建异步线程环境
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("onResult jsonwave","jsonResult:"+value);
                            if (mAsrDialog!=null){
                                if (value < 1500) {
                                    mAsrDialog.setEnergy(R.mipmap.mic0);
                                } else if (value < 2500) {
                                    mAsrDialog.setEnergy(R.mipmap.mic1);
                                } else if (value < 4500) {
                                    mAsrDialog.setEnergy(R.mipmap.mic2);
                                } else {
                                    mAsrDialog.setEnergy(R.mipmap.mic3);
                                }
                            }
                        }
                    });
                }
            }).start();
        }
    }


    public void voicResult(AsrResult asrResult, boolean isLast){

        Log.i("onResult json1"+centerTitle,asrResult.isResult+"jsonResult:"+asrResult.jsonResult+",isLast:"+isLast);
        Boolean ifUseResult = asrResult.isResult;
        if (centerTitle.equals("体征录入")||centerTitle.equals("费用补录")){
            ifUseResult = true;
        }
        //判断是否有结果
        if (ifUseResult) {
            try {
                Message message = new Message();
                message.what = 100;
                Bundle bundle = new Bundle();
                bundle.putString("results", asrResult.results);
                Log.i("onResult json11"+centerTitle,"results:"+asrResult.results+",isLast:"+isLast);
                bundle.putString("json", new JSONObject(asrResult.jsonResult).toString());
                //bundle.putString("json", "{\"command\":{\"action\":\"生命体征\",\"code\":2001},\"form\":{\"code\":200}}");
                bundle.putBoolean("isLast", isLast);
//                    message.setData(bundle);
                new Thread(new Runnable() {//创建异步线程环境
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                getTempResultByVoice(bundle);
                                testInt++;
//                                try {
//                                    etTest.setText(centerTitle+"--"+getScene()+etTest.getText().toString()+"\n"+testInt+"."+asrResult.results+".jsonResult:"
//                                            +new JSONObject(asrResult.jsonResult).toString()
//                                            +",isLast:"+isLast);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
                            }
                        });
                    }
                }).start();

                new Thread(new Runnable() {//创建异步线程环境
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (isLast&&isActionUp){
                                    if (mAsrDialog != null) {
                                        mAsrDialog.dismiss();
                                        handler1.removeCallbacks(runnable);
                                        Log.e(TAG, "run: 11111"+"4" );
                                        mAsrDialog = null;
                                    }
                                    Gson gson = new Gson();
                                    VoiceBean voiceBean = gson.fromJson(bundle.getString("json"),  VoiceBean.class);
                                    if (voiceBean==null){
                                        voiceBean=new VoiceBean();
                                    }else {
                                        if (voiceBean.getCommand()==null){
                                            voiceBean.setCommand(new VoiceBean.CommandBean());
                                        }
                                        if (voiceBean.getForm()==null){
                                            voiceBean.setForm(new VoiceBean.FormBean());
                                        }else {
                                            if (voiceBean.getForm().getData()==null){
                                                voiceBean.getForm().setData(new ArrayList<VoiceBean.FormBean.DataBean>());
                                            }
                                        }
                                    }
                                    voiceBean.setLast(isLast);
                                    Log.i("onResult json3","jsonResult:"+isLast);
                                    getVoiceResult(voiceBean);
                                }else if (needTempScene()){
                                    Gson gson = new Gson();
                                    VoiceBean voiceBean = gson.fromJson(bundle.getString("json"),  VoiceBean.class);
                                    if (voiceBean==null){
                                        voiceBean=new VoiceBean();
                                    }else {
                                        if (voiceBean.getCommand()==null){
                                            voiceBean.setCommand(new VoiceBean.CommandBean());
                                        }
                                        if (voiceBean.getForm()==null){
                                            voiceBean.setForm(new VoiceBean.FormBean());
                                        }else {
                                            if (voiceBean.getForm().getData()==null){
                                                voiceBean.getForm().setData(new ArrayList<VoiceBean.FormBean.DataBean>());
                                            }
                                        }
                                    }
                                    voiceBean.setLast(isLast);
                                    Log.i("onResult json3","jsonResult:"+isLast);
                                    getVoiceResult(voiceBean);
                                }
                            }
                        });
                    }
                }).start();
                Log.i("onResult json2","jsonResult:"+new JSONObject(asrResult.jsonResult).toString()+",isLast:"+isLast);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (isLast){
            new Thread(new Runnable() {//创建异步线程环境
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (isLast&&isActionUp){
                                if (mAsrDialog != null) {
                                    mAsrDialog.dismiss();
                                    handler1.removeCallbacks(runnable);
                                    Log.e(TAG, "run: 11111"+"5" );
                                    mAsrDialog = null;
                                }
//                                   showToast(centerTitle+"语音获取结果为空");
                            }
                        }
                    });
                }
            }).start();

        }
    }

    // public Boolean called = false;
    public void getTempResultByVoice(Bundle bundle){

    }
    //判断当前场景下是否需要获取语音中间返回数据
    public Boolean needTempScene(){

        return centerTitle.equals("体征录入")
                ||centerTitle.equals("日常生活能力")
                ||centerTitle.equals("Barden压力性皮肤损伤评估录入")
                ||centerTitle.equals("跌倒评估录入")
                ||centerTitle.equals("坠床评估录入")
                ||centerTitle.equals("肌力受损评估录入")
                ||centerTitle.equals("导管滑落风险评估录入")
                ||centerTitle.equals("Autar深静脉血栓录入")
                ||centerTitle.equals("CPOT疼痛评估录入")
                ||centerTitle.equals("血压监测记录单录入")
                ||centerTitle.equals("手术科室护理记录单录入")
                ||centerTitle.equals("费用补录");
    }

    public void getVoiceResult(VoiceBean voiceBean){

        //录入页面需要在本页面处理数据，
        if (centerTitle.equals("体征录入")){
            if (tempVoiceCallBack!=null){
                tempVoiceCallBack.getTempVoice(voiceBean);
            }
        }else {
            String bedNoStr = "";
            if (voiceBean.getCommand().getBedNo() != null){
                bedNoStr = voiceBean.getCommand().getBedNo();
            }
            String actionStr = "";
            if (voiceBean.getCommand().getAction() != null){
                actionStr = voiceBean.getCommand().getAction();
            }
            startFragmentByVoice(bedNoStr,actionStr);
        }
    }
    private void startFragmentByClassName(String className){
        Class<? extends BaseFragment> aClass = null;
        try {
            aClass = (Class<? extends BaseFragment>) Class.forName(className);
//            baseFragment.startFragment(aClass);
            singleVoiceStartFragment(className,null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    private void startFragmentByClassNameWithBed(String name,String className,String bedNo){
        Class<? extends BaseFragment> aClass = null;
//        VoiceBedMapBean voiceBedMapBean = new VoiceBedMapBean();
        BedMapBean bedMapBean = new BedMapBean();
        String episodeId = "";
        String patInfo = "";
        String inHosDate = "";
        String jsonMap = "";
        Bundle bundle = new Bundle();
        Boolean isBedExist = false;
        if (bedNo.isEmpty()){
//            showToastByVoice("未读床位，无法跳转到"+name);
            ToastUtils.showLong("未读床位，无法跳转到"+name);
            return;
        }
        try {
            aClass = (Class<? extends BaseFragment>) Class.forName(className);
            Gson gson = new Gson();
//            voiceBedMapBean = gson.fromJson(SPUtils.getInstance().getString(SharedPreference.VOICE_PAT_LIST),VoiceBedMapBean.class);
            bedMapBean = gson.fromJson(SPUtils.getInstance().getString(SharedPreference.VOICE_PAT_LIST),BedMapBean.class);
//            for (int i = 0; i < voiceBedMapBean.getPatInfoList().size(); i++) {
//                if (bedNo.equals(voiceBedMapBean.getPatInfoList().get(i).getBedCode())&&voiceBedMapBean.getPatInfoList().get(i).getInBedAll().equals("1")){
//                    isBedExist = true;
//                    episodeId = voiceBedMapBean.getPatInfoList().get(i).getEpisodeId();
//                    patInfo = voiceBedMapBean.getPatInfoList().get(i).getBedCode()+" "+voiceBedMapBean.getPatInfoList().get(i).getName();
//                    inHosDate = voiceBedMapBean.getPatInfoList().get(i).getRQDDate();
//
//                    bundle.putString("EpisodeID", episodeId);
//                    bundle.putString("BedNo", bedNo);
//                    bundle.putString("PatName", voiceBedMapBean.getPatInfoList().get(i).getName());
//                    bundle.putString("RecID", "");
            for (int i = 0; i < bedMapBean.getPatInfoList().size(); i++) {
                if (bedNo.equals(bedMapBean.getPatInfoList().get(i).getBedCode())){
                    isBedExist = true;
                    episodeId = bedMapBean.getPatInfoList().get(i).getEpisodeId();
                    patInfo = bedMapBean.getPatInfoList().get(i).getBedCode()+" "+bedMapBean.getPatInfoList().get(i).getName();
//                    inHosDate = voiceBedMapBean.getPatList().get(i).getRQDDate();

                    bundle.putString("EpisodeID", episodeId);
                    bundle.putString("BedNo", bedNo);
                    bundle.putString("PatName", bedMapBean.getPatInfoList().get(i).getName());
                    bundle.putString("RecID", "");



                    if (name.equals("基本信息")||name.equals("床位图")){
                        List<Map<String, String>> patInfoMapList =new ArrayList<>();
                        Map bedMapMap = gson.fromJson(SPUtils.getInstance().getString(SharedPreference.VOICE_PATINFO_JSON), Map.class);
                        patInfoMapList = (List<Map<String, String>>) bedMapMap.get("patInfoList");
                        Map map = patInfoMapList.get(i);
                        if (bedNo.equals(map.get("bedCode"))){
                            jsonMap = gson.toJson(map);
                            bundle.putString("jsonmap",jsonMap);
                        }
                        if (name.equals("床位图")){
                            BedMapBean bedMapBean1 = gson.fromJson(SPUtils.getInstance().getString(SharedPreference.VOICE_PAT_LIST),BedMapBean.class);
                            BedMapBean.PatInfoListBean patInfoListBean = bedMapBean1.getPatInfoList().get(i);
                            bundle.putString("patinfo", gson.toJson(patInfoListBean));
                        }
                    } else
                    if (name.equals("日常生活能力评估")){
                        bundle.putString("EMRCode", "DHCNURBARLR");
                        bundle.putString("GUID", "bc1e90b31bb44d8bbc2bbd54a2e03a11");
                    } else if (name.equals("Barden压力性皮肤损伤评估录入")){
                        bundle.putString("EMRCode", "DHCNURBRADENLR");
                        bundle.putString("GUID", "8a5c0674abf943ad8e8fd88ccc0a4865");
                    }else if (name.equals("跌倒评估录入")){
                        bundle.putString("EMRCode", "DHCNURDDPGLR");
                        bundle.putString("GUID", "06675c370a7b42d6b2fd03feaafc8086");
                    }else if (name.equals("坠床评估录入")){
                        bundle.putString("EMRCode", "DHCNURZCPGLR");
                        bundle.putString("GUID", "ffb19e3d0eb54ce4b137db8feb68b8e1");
                    }else if (name.equals("肌力受损评估录入")){
                        bundle.putString("EMRCode", "DHCNURJLPGLR");
                        bundle.putString("GUID", "a87e6c19262748d7a9cac9fce20e7e3b");
                    }else if (name.equals("导管滑落风险评估录入")){
                        bundle.putString("EMRCode", "DHCNURDGHTPGLR");
                        bundle.putString("GUID", "c565fe567705418f8f286fecd6322466");
                    }else if (name.equals("Autar深静脉血栓录入")){
                        bundle.putString("EMRCode", "DHCNURAUTARLR");
                        bundle.putString("GUID", "3cb84f4e8dcf4c46b23de30714230588");
                    }else if (name.equals("CPOT疼痛评估录入")){
                        bundle.putString("EMRCode", "DHCNURCOPTLR");
                        bundle.putString("GUID", "2686bd7d0f024855a9364298625a33e7");
                    }else if (name.equals("血压监测记录单录入")){
                        bundle.putString("EMRCode", "DHCNURxyjcjldlr");
                        bundle.putString("GUID", "f98c9904a8e3426daac4fa26d33609b7");
                    }else if (name.equals("手术科室护理记录单录入")){
                        bundle.putString("EMRCode", "DHCNURSSKSLR");
                        bundle.putString("GUID", "e1baca1f0bb24e90bdec4610cef7f980");
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {

        }

        bundle.putSerializable("episodeId", episodeId);
        bundle.putString("patInfo", patInfo);
        bundle.putString("inHosDate",inHosDate);
        bundle.putString("patmsg", patInfo);
        if (isBedExist){
//            baseFragment.startFragment(aClass,bundle);
            singleVoiceStartFragment(aClass.getName(),bundle);
        }else {
            ToastUtils.showLong(bedNo.replaceAll("床","")+"床不存在，请确认床号");
//            showToastByVoice(bedNo.replaceAll("床","")+"床不存在，请确认床号");
        }


    }
    private void startFrag(String startype){
        for (int i = 0; i < SharedPreference.FRAGMENTARY.size(); i++) {
            Map map = (Map) SharedPreference.FRAGMENTARY.get(i);
            if (map.get("fragName").toString().contains(startype)){
                Class<? extends BaseFragment> aClass = null;
                try {
                    aClass = (Class<? extends BaseFragment>) Class.forName(map.get("fragName").toString());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (!aClass.getName().contains("WorkareaFragment")){
                    String regNo = "";
                    if (bedNoByVoice.isEmpty()){
//                        baseFragment.startFragment(aClass);
                        singleVoiceStartFragment(aClass.getName(),null);
                    }else {
                        Bundle bundle = new Bundle();
                        Gson gson = new Gson();

                        VoiceVisalBean voiceVisalBean = gson.fromJson(SPUtils.getInstance().getString(SharedPreference.VOICE_VISAL_LIST),VoiceVisalBean.class);
                        int index = 0;
                        List timeFilterList = (ArrayList) voiceVisalBean.getMapAll().get("timeSelect");
                        String dateFilterStr = voiceVisalBean.getDatePoint();
                        String timeFilterStr = voiceVisalBean.getTimePoint();
                        if (!bedNoByVoice.isEmpty()){
                            for (int j = 0; j < voiceVisalBean.getPatInfoList().size(); j++) {
                                String tempBedCode = voiceVisalBean.getPatInfoList().get(j).getBedCode();
                                if (bedNoByVoice.equals(tempBedCode)){
                                    index = j;
                                }
                            }
                        }

                        String patInfo = "";
                        String episodeId = "";
//                        VoiceBedMapBean voiceBedMapBean = new VoiceBedMapBean();
                        BedMapBean bedMapBean = new BedMapBean();
                        bedMapBean = gson.fromJson(SPUtils.getInstance().getString(SharedPreference.VOICE_PAT_LIST),BedMapBean.class);
                        for (int j = 0; j < bedMapBean.getPatInfoList().size(); j++) {
                            if (bedNoByVoice.equals(bedMapBean.getPatInfoList().get(j).getBedCode())){
                                regNo = bedMapBean.getPatInfoList().get(j).getRegNo();
                                episodeId = bedMapBean.getPatInfoList().get(j).getEpisodeId();
                            }
                        }

                        bundle.putSerializable("info", (Serializable) "");
                        bundle.putString("time", timeFilterStr);
                        bundle.putString("date", dateFilterStr);
                        bundle.putInt("index", index);
                        bundle.putString("regNo", regNo);
                        bundle.putString("episodeId", episodeId);
                        bundle.putSerializable("timeList", (Serializable) timeFilterList);
                        if (regNo.isEmpty()){
                            ToastUtils.showLong(bedNoByVoice.replaceAll("床","")+"床不存在，请确认床号");
//                            showToastByVoice(bedNoByVoice.replaceAll("床","")+"床不存在，请确认床号");
                        }else {
//                            baseFragment.startFragment(aClass,bundle);
                            singleVoiceStartFragment(aClass.getName(),bundle);
                        }
                    }

                }else {
                    Class<? extends BaseActivity> activityClass = null;
                    try {
                        activityClass = (Class<? extends BaseActivity>) Class.forName("com.dhcc.nursepro.Activity.MainActivity");
//                        baseFragment.startActivity(new Intent(mContext, activityClass));
                        singleVoiceStartFragment(aClass.getName(),null);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
//        if (startype.equals("VitalSignRecordFragment")){
//            Class<? extends BaseFragment> aClass = null;
//            try {
//                aClass = (Class<? extends BaseFragment>) Class.forName("com.dhcc.nursepro.workarea.vitalsign.VitalSignRecordFragment");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            if (!aClass.getName().contains("WorkareaFragment")){
//                startFragment(aClass);
//            }
//        }
    }
    public void startFragmentByVoice(String beNo, String startype){
        bedNoByVoice = beNo;
        if (isActionUp){
            switch (startype){
                case "床位图":
                    if (bedNoByVoice.isEmpty()){
                        startFrag("BedMapFragment");
                    }else {
                        startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.bedmap.BedMapPatFragment",bedNoByVoice);
                    }
                    break;
                case "医嘱查询":
                    startFrag("OrderSearchFragment");
                    break;
                case "医嘱执行":
                    startFrag("OrderExecuteFragment");
                    break;
                case "入院分床":
                    startFrag("AllotBedFragment");
                    break;
                case "生命体征":
                    if (bedNoByVoice.isEmpty()){
                        startFrag("VitalSignFragment");
                    }else {
                        startFrag("VitalSignRecordFragment");
                    }
                    break;
                case "事件登记":
                    startFrag("PatEventsFragment");
                    break;
                case "检查报告":
                    if (bedNoByVoice.isEmpty()){
                        startFrag("CheckPatsFragment");
                    }else {
                        startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.checkresult.CheckResultListFragment",bedNoByVoice);
                    }
                    break;
                case "医嘱单":
                    startFrag("DocOrderListFragment");
                    break;
                case "手术申请":
                    startFrag("OperationFragment");
                    break;
                case "检验结果":
                    if (bedNoByVoice.isEmpty()){
                        startFrag("LabPatsFragment");
                    }else {
                        startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.labresult.LabResultListFragment",bedNoByVoice);
                    }
                    break;
                case "检验打包":
                    startFrag("LabOutListFragment");
                    break;
                case "输血系统":
                    startFrag("BloodTransfusionSystemFragment");
                    break;
                case "输液复核":
                    startFrag("DosingReviewFragment");
                    break;
                case "母乳闭环":
                    startFrag("MilkLoopSystemFragment");
                    break;
                case "护理病历":
                    startFrag("PatNurRecordFragment");
                    break;
                case "日常生活能力评估":
                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
                    break;
                case "Barden压力性皮肤损伤评估录入":
                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
                    break;
                case "跌倒评估录入":
                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
                    break;
                case "坠床评估录入":
                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
                    break;
                case "导管滑落风险评估录入":
                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
                    break;
                case "Autar深静脉血栓录入":
                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
                    break;
                case "肌力受损评估录入":
                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
                    break;
                case "CPOT疼痛评估录入":
                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
                    break;
                case "血压监测记录单录入":
                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
                    break;
                case "手术科室护理记录单录入":
                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
                    break;
                case "护理巡视":
                    startFrag("NurTourFragment");
                    break;
                case "静配接收":
                    startFrag("DrugReceiveFragment");
                    break;
                case "主页":
                    startFrag("WorkareaFragment");
                    break;
                case "血液签收":
                    startFragmentByClassName("com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodsign.BloodSignFragment");
                    break;
                case "语音备忘":
                    startFragmentByClassName("com.dhcc.nursepro.setting.NoteBookWriteFragment");
                    break;
                case "血液输注":
                    startFragmentByClassName("com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.BloodTransfusionFragment");
                    break;
                case "输血巡视":
                    startFragmentByClassName("com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusiontour.BloodTransfusionTourFragment");
                    break;
                case "输血结束":
                    startFragmentByClassName("com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusionend.BloodTransfusionEndFragment");
                    break;
                case "血液回收":
                    startFragmentByClassName("com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodbagrecycling.BloodBagRecyclingFragment");
                    break;
                case "血液查询":
                    startFragmentByClassName("com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodOperationListFragment");
                    break;
                case "病理打包":
                    startFragmentByClassName("com.dhcc.nursepro.workarea.plyout.PlyOutListFragment");
                    break;
                case "声纹管理":
//                    Class<? extends BaseActivity> activityClass = null;
//                    try {
//                        activityClass = (Class<? extends BaseActivity>) Class.forName("voice.raisound_vp.VoiceprintManagerActivity");
//                        startActivity(new Intent(getActivity(), activityClass));
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
                    break;
                case "所管床设置":
                    startFragmentByClassName("com.dhcc.nursepro.setting.SettingBedsFragment");
                    break;
                case "查询时间":
                    startFragmentByClassName("com.dhcc.nursepro.setting.SettingDateTimeFragment");
                    break;
                case "消息设置":
                    startFragmentByClassName("com.dhcc.nursepro.setting.SettingWayFragment");
                    break;
                case "体征曲线图":
                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.vitalsigndetail.VitalSignChartsDetailFragment",bedNoByVoice);
                    break;
                case "住院日清单":
                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.bedmap.DayPayListFragment",bedNoByVoice);
                    break;
                case "基本信息":
                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.bedmap.BedMapPatInfoFragment",bedNoByVoice);
                    break;
                case "费用补录":
                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.bedmap.AfterPayFragment",bedNoByVoice);
                    break;
                case "返回":
                    finishCurFragment();
                    break;
                default:
                    if (startype.contains("体征录入")){
                        startFrag("VitalSignRecordFragment");
                    }else {
                        if (bedNoByVoice.equals("")){
//                            showToastByVoice("语音识别失败");
//                            showToast("语音识别失败");
                        }else {
                            ToastUtils.showLong(bedNoByVoice);
                        }

                    }
                    break;
            }

        }
    }


    public void finishCurFragment(){
//        baseFragment.finish();
    }

    public void singleVoiceStartFragment(String fragClassName,Bundle bundle){
        if (singleVoiceListner!=null){
            singleVoiceListner.startFragmentByvoice(fragClassName,bundle);
        }
    }

    TempVoiceCallBack tempVoiceCallBack;
    public void setTemVoiceListener(TempVoiceCallBack tempVoiceCallBack){
        this.tempVoiceCallBack = tempVoiceCallBack;
    }
    public interface TempVoiceCallBack{
       void getTempVoice(VoiceBean voiceBean);
    }

    SingleVoiceListner singleVoiceListner;

    public void setSingleVoiceListner(SingleVoiceListner singleVoiceListner) {
        this.singleVoiceListner = singleVoiceListner;
    }

    public interface SingleVoiceListner{
        void startFragmentByvoice(String className,Bundle bundle);
    }
}
