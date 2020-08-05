package com.dhcc.res.nurse;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grs.dhcc_res.R;

/**
 * com.dhcc.res.nurse
 * <p>
 * author Q
 * Date: 2020/8/5
 * Time:11:11
 */
public class ImgResetView extends LinearLayout {
    private TextView tvPat;
    private ImageView imgReset;
    public ImgResetView(Context context) {
        super(context);
    }
    public ImgResetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.dhcc_view_img_reset, this, false);
        tvPat = view.findViewById(R.id.tv_patinfo);
        imgReset = view.findViewById(R.id.img_reset);
        addView(view);
        initEvents();
    }
    private void initEvents(){
        imgReset.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        imgReset.setSelected(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        imgReset.setSelected(false);
                        touchEventListner.reset();
                        break;
                }
                return true;
            }
        });

    }

    public void setTvPatText(String string){
        tvPat.setText(string);
    }
    public String getTvPatInfo(){
        return tvPat.getText().toString();
    }

    private touchEventListner touchEventListner;
    public void setTouchListener(touchEventListner touchEventListner){
            this.touchEventListner = touchEventListner;
    }
    public interface touchEventListner{
        void reset();
    }
}
