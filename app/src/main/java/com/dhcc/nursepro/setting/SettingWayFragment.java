package com.dhcc.nursepro.setting;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.dhcc.nursepro.R;
import com.base.commlibs.constant.SharedPreference;

/**
 * com.dhcc.nursepro.setting
 * <p>
 * author Q
 * Date: 2018/9/13
 * Time:14:36
 */
public class SettingWayFragment extends BaseFragment implements View.OnClickListener{
    private SPUtils spUtils = SPUtils.getInstance();
    private RelativeLayout rlLight;
    private RelativeLayout rlSound;
    private RelativeLayout rlVibrator;
    private Boolean bLight,bSound,bVibrator;
    private ImageView imgLight,imgSound,imgVibrator;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting_way, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle("消息提醒");
        setToolbarBottomLineVisibility(false);
        //右上角保存按钮
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   保存   ");
        textView.setTextColor(getResources().getColor(R.color.blue_dark));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spUtils.put(SharedPreference.LIGHT,bLight);
                spUtils.put(SharedPreference.SOUND,bSound);
                spUtils.put(SharedPreference.VIBRATOR,bVibrator);
                finish();
            }
        });
        setToolbarRightCustomView(viewright);
        initView(view);

    }
    private void initView(View view){
        rlLight = view.findViewById(R.id.rl_setting_light);
        rlLight.setOnClickListener(this);
        rlSound = view.findViewById(R.id.rl_setting_sound);
        rlSound.setOnClickListener(this);
        rlVibrator = view.findViewById(R.id.rl_setting_vibrator);
        rlVibrator.setOnClickListener(this);
        imgLight = view.findViewById(R.id.img_setting_light);
        imgSound = view.findViewById(R.id.img_setting_sound);
        imgVibrator =view.findViewById(R.id.img_setting_vibrator);


        bLight = spUtils.getBoolean(SharedPreference.LIGHT,true);
        bSound = spUtils.getBoolean(SharedPreference.SOUND,true);
       bVibrator =  spUtils.getBoolean(SharedPreference.VIBRATOR,true);

        if (bLight){
            imgLight.setImageResource(R.drawable.icon_selected);
        }else {
            imgLight.setImageResource(R.drawable.icon_unselected);
        }
        if (bSound){
            imgSound.setImageResource(R.drawable.icon_selected);
        }else {
            imgSound.setImageResource(R.drawable.icon_unselected);
        }

        if (bVibrator){
            imgVibrator.setImageResource(R.drawable.icon_selected);
        }else {
            imgVibrator.setImageResource(R.drawable.icon_unselected);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.rl_setting_light:
                 if (bLight){
                     bLight = false;
                     imgLight.setImageResource(R.drawable.icon_unselected);
                 }else {
                     bLight = true;
                     imgLight.setImageResource(R.drawable.icon_selected);
                 }
                break;
            case R.id.rl_setting_sound:
                if (bSound){
                    bSound = false;
                    imgSound.setImageResource(R.drawable.icon_unselected);
                }else {
                    bSound = true;
                    imgSound.setImageResource(R.drawable.icon_selected);
                }
                break;
            case R.id.rl_setting_vibrator:
                if (bVibrator){
                    bVibrator = false;
                    imgVibrator.setImageResource(R.drawable.icon_unselected);
                }else {
                    bVibrator = true;
                    imgVibrator.setImageResource(R.drawable.icon_selected);
                }
                break;
            default:
                break;
        }
    }
}
