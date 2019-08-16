package com.dhcc.module.infusion.setting;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.module.infusion.R;

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
    private RelativeLayout rlSettingScan;
    private Boolean bLight,bSound,bVibrator,multiScan;
    private ImageView imgLight,imgSound,imgVibrator, imgSettingScan;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting_way_infusion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle("提醒方式");
        setToolbarBottomLineVisibility(false);
        //右上角保存按钮
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right_infusion, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   保存   ");
        textView.setTextColor(getResources().getColor(R.color.blue_dark));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSP();
                finish();
            }
        });
        setToolbarRightCustomView(viewright);
        initView(view);

    }

    private void saveSP() {
        spUtils.put(SharedPreference.LIGHT,bLight);
        spUtils.put(SharedPreference.SOUND,bSound);
        spUtils.put(SharedPreference.VIBRATOR,bVibrator);
        spUtils.put(SharedPreference.MUlTISCAN,multiScan);
    }

    private void initView(View view){
        rlLight = view.findViewById(R.id.rl_setting_light);
        rlLight.setOnClickListener(this);
        rlSound = view.findViewById(R.id.rl_setting_sound);
        rlSound.setOnClickListener(this);
        rlVibrator = view.findViewById(R.id.rl_setting_vibrator);
        rlVibrator.setOnClickListener(this);
        rlSettingScan = view.findViewById(R.id.rl_setting_scan);
        rlSettingScan.setOnClickListener(this);
        imgLight = view.findViewById(R.id.img_setting_light);
        imgSound = view.findViewById(R.id.img_setting_sound);
        imgVibrator =view.findViewById(R.id.img_setting_vibrator);
        imgSettingScan =view.findViewById(R.id.img_setting_scan);


        bLight = spUtils.getBoolean(SharedPreference.LIGHT,true);
        bSound = spUtils.getBoolean(SharedPreference.SOUND,true);
        bVibrator =  spUtils.getBoolean(SharedPreference.VIBRATOR,true);
        multiScan =  spUtils.getBoolean(SharedPreference.MUlTISCAN,false);
        imgSettingScan.setImageResource(multiScan?R.drawable.icon_selected_infusion:R.drawable.icon_unselected_infusion);
        if (bLight){
            imgLight.setImageResource(R.drawable.icon_selected_infusion);
        }else {
            imgLight.setImageResource(R.drawable.icon_unselected_infusion);
        }
        if (bSound){
            imgSound.setImageResource(R.drawable.icon_selected_infusion);
        }else {
            imgSound.setImageResource(R.drawable.icon_unselected_infusion);
        }

        if (bVibrator){
            imgVibrator.setImageResource(R.drawable.icon_selected_infusion);
        }else {
            imgVibrator.setImageResource(R.drawable.icon_unselected_infusion);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_setting_light) {
            if (bLight){
                bLight = false;
                imgLight.setImageResource(R.drawable.icon_unselected_infusion);
            }else {
                bLight = true;
                imgLight.setImageResource(R.drawable.icon_selected_infusion);
            }
        }
        if (v.getId() == R.id.rl_setting_sound) {
            if (bSound){
                bSound = false;
                imgSound.setImageResource(R.drawable.icon_unselected_infusion);
            }else {
                bSound = true;
                imgSound.setImageResource(R.drawable.icon_selected_infusion);
            }
        }
        if (v.getId() == R.id.rl_setting_vibrator) {
            if (bVibrator){
                bVibrator = false;
                imgVibrator.setImageResource(R.drawable.icon_unselected_infusion);
            }else {
                bVibrator = true;
                imgVibrator.setImageResource(R.drawable.icon_selected_infusion);
            }
        }
        if (v.getId() == R.id.rl_setting_scan) {
            multiScan = !multiScan;
            imgSettingScan.setImageResource(multiScan?R.drawable.icon_selected_infusion:R.drawable.icon_unselected_infusion);
        }
    }
}
