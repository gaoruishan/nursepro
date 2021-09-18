package com.dhcc.nursepro.setting;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.setting.api.SettingBedsApiManeger;
import com.dhcc.nursepro.setting.bean.NoteBean;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.bedmap
 * <p>
 * author Q
 * Date: 2020/9/29
 * Time:9:46
 */
public class NoteBookWriteFragment extends BaseFragment {

    private EditText tvNote;
    private String note = "",tempNote = "",lastNote = "";
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_write, container, false);
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
        setScene("转写场景");
        //右上角保存按钮
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   保存   ");
        textView.setTextColor(getResources().getColor(R.color.blue_dark));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (voiceUtil.getFileId().isEmpty()){
                    voiceUtil.setFileId(meetingId);
                }
                voiceUtil.saveVoice();
                saveSound();
            }
        });
        setToolbarRightCustomViewSingleShow(viewright);
        initView(view);
        setMeetingId(SPUtils.getInstance().getString(SharedPreference.USERID)+System.currentTimeMillis());
//        initData();
    }

    private void initView(View view) {
        tvNote = view.findViewById(R.id.tv_notebook);
    }

    private void saveSound() {
        if (tvNote.getText().toString().isEmpty()){
            showToast("请输入内容");
            return;
        }
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap<String ,String > map = new HashMap<>();
        map.put("soundStr",tvNote.getText().toString());
//        map.put("soundId",fileId);
        if (voiceUtil.getFileListSize()<1){
            map.put("soundId","0");
        }else {
            map.put("soundId",voiceUtil.getFileId());
        }
        map.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));
        map.put("locId",SPUtils.getInstance().getString(SharedPreference.LOCID));
        SettingBedsApiManeger.getSaveSound(map, "setSoundData", new SettingBedsApiManeger.getSoundCallBack() {
            @Override
            public void onSuccess(NoteBean noteBean) {
                hideLoadingTip();
                showToast(noteBean.getMsg());
                finish();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
                hideLoadingTip();
            }
        });
    }


    @Override
    public void getTempVoiceResult(Bundle bundle) {
        super.getTempVoiceResult(bundle);
        tempNote = bundle.getString("results")+",";
        if (bundle.getBoolean("isLast")){
            lastNote = lastNote +tempNote;
            tvNote.setText(lastNote);
            tvNote.setSelection(lastNote.length());
        }else {
            tvNote.setText(lastNote+tempNote);
            tvNote.setSelection((lastNote+tempNote).length());
        }

        Log.e(TAG, "getTempResultByVoice: json33"+bundle.toString() );
    }



//    @Override
//    public void getTempResultByVoice(Bundle bundle) {
//        super.getTempResultByVoice(bundle);
//
//
//    }


//    @Override
//    public void onTem(String temResult) {
//        super.onTem(temResult);
//        tvNote.setText(note+temResult.replace("&resultStr:",""));
//    }
//
//    @Override
//    public void onLast(String lastResult) {
//        super.onLast(lastResult);
//        note = note+(note.equals("")?"":",")+lastResult.replace("&resultStr:","");
//        tvNote.setText(note);
//    }
//
//    @Override
//    public void lastResult(Map<String, String> jsonMap) {
//        isNote = true;
//        super.lastResult(jsonMap);
//    }
}

