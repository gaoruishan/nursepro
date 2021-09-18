package com.dhcc.nursepro.setting;

import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.setting.adapter.NoteBookAdapter;
import com.dhcc.nursepro.setting.api.SettingBedsApiManeger;
import com.dhcc.nursepro.setting.bean.NoteBean;
import com.dhcc.nursepro.workarea.checkresult.api.ShowMsgDialog;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * com.dhcc.nursepro.workarea.bedmap
 * <p>
 * author Q
 * Date: 2020/9/29
 * Time:9:46
 */
public class NoteBookFragment extends BaseFragment {
    private RecyclerView recNote;
    private TextView tvUser,tvCount;
    private NoteBookAdapter noteBookAdapter = new NoteBookAdapter(new ArrayList<>());
    private MediaPlayer   player;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle("备忘录",getResources().getColor(R.color.blue),17);
        setToolbarBottomLineVisibility(true);
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   增加   ");
        textView.setTextColor(getResources().getColor(R.color.blue_dark));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(NoteBookWriteFragment.class);
            }
        });
        setToolbarRightCustomViewSingleShow(viewright);

        initView(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        setScene("转写场景");
        initData();
    }

    private void initView(View view) {
        recNote = view.findViewById(R.id.recy_note);
        recNote.setHasFixedSize(true);
        recNote.setLayoutManager(new LinearLayoutManager(getActivity()));
        recNote.setAdapter(noteBookAdapter);
        noteBookAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (view.getId() == R.id.ll_message_rightmenu){
//                 showToast("删除"+noteBookAdapter.getData().get(position).getSoundId());
                    deleteVoice(noteBookAdapter.getItem(position).getRowId());
                }

                if (view.getId() == R.id.patinfodetail_title){
                    ShowMsgDialog showDialog = new ShowMsgDialog(getActivity());
                    showDialog.setTitle("备忘录");
                    showDialog.setMessage(" "+noteBookAdapter.getData().get(position).getSoundDate()+
                            " "+noteBookAdapter.getData().get(position).getSoundTime()+
                            "\n  "+"\n  "+noteBookAdapter.getData().get(position).getSoundStr());
                    showDialog.setYesOnclickListener("确定", new ShowMsgDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            showDialog.dismiss();
                        }
                    });

                    showDialog.show();
                }

                if (view.getId() == R.id.tv_playvoice){
                    if (player !=null){
                        player.stop();
                    }
                    player  =   new MediaPlayer();
                    String  path   = "/sdcard/voiceRecord/"+SPUtils.getInstance().getString(SharedPreference.USERCODE)+"/"+ noteBookAdapter.getData().get(position).getSoundId()+".wav";     //这里给一个歌曲的网络地址就行了

                    try {
                        player.setDataSource(path);
                        player.prepare();
                        player.start();
                        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                showToast("播放完毕");
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        showToast("error:播放失败");
                    }

//                    SpeechRecognizerManager.start();
//                    SpeechRecognizerManager.getRecordMp3(noteBookAdapter.getData().get(position).getSoundHead(), new RequestCallback() {
//                        @Override
//                        public void onSuccess(int i, Object o) {
//
//                            showToast("播放成功");
//                            SpeechRecognizerManager.stop();
//                        }
//
//                        @Override
//                        public void onFailure(int i, String s) {
//                            Log.e("mp3","getRecordMp3 error:"+s);
//                            showToast("播放失败");
//                            SpeechRecognizerManager.stop();
//                        }
//                    });
                }
            }
        });

        tvUser = view.findViewById(R.id.tv_user);
        tvCount = view.findViewById(R.id.note_count);
        tvUser.setText(SPUtils.getInstance().getString(SharedPreference.USERNAME));
    }

    private void initData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap<String ,String > map = new HashMap<>();
//        map.put("startDate","");
//        map.put("endDate","");
        map.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));
        SettingBedsApiManeger.getSoundList(map, "getSoundData", new SettingBedsApiManeger.getSoundCallBack() {
            @Override
            public void onSuccess(NoteBean noteBean) {
                hideLoadingTip();
                noteBookAdapter.setNewData(noteBean.getSoundList());
                tvCount.setText(noteBean.getSoundList().size()+"条");
            }

            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
                hideLoadingTip();
            }
        });

    }

    private void deleteVoice(String rowId){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap<String ,String > map = new HashMap<>();
        map.put("rowId", rowId);
        SettingBedsApiManeger.getSoundList(map, "deleteSoundData", new SettingBedsApiManeger.getSoundCallBack() {
            @Override
            public void onSuccess(NoteBean noteBean) {
                hideLoadingTip();
                if (player!=null){
                    player.stop();
                }
                showToast("删除成功");
                initData();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
                hideLoadingTip();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player!=null){
            player.stop();
        }
    }
}

