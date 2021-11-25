package com.example.dhcc_nurlink.voiceplayutil.upload.progressaware;

import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by qugengting on 7/9/15.<br>
 */
public class ProgressBarAware extends BaseViewAware {

    public ProgressBarAware(ProgressBar view) {
        super(view);
    }

    @Override
    public void setProgress(int progress, View view) {
        ProgressBar pb = ((ProgressBar) view);
        pb.setProgress(progress);
        pb.invalidate();

    }
}
