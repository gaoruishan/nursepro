package com.dhcc.module.nurse.bloodsugar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dhcc.module.nurse.R;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * com.dhcc.nursepro.view
 * <p>
 * author Q
 * Date: 2020/6/1
 * Time:9:58
 */
public class ChartLabelView extends FlowLayout {
    private LinearLayout llView;
    private Context mContext;
    private ArrayList<String> listCode = new ArrayList<>();
    public ChartLabelView(Context context) {
        super(context,null);
    }
    public ChartLabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

    }
    public void setData(ArrayList<String> listCode,HashMap<String, Integer> map){
        for (int i = 0; i < listCode.size(); i++) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chart_label_view, this, false);
            View view1 = view.findViewById(R.id.view_color);
            View view2 = view.findViewById(R.id.view_ck);
            TextView textView = view.findViewById(R.id.tv_label);
            view1.setBackgroundColor(map.get(listCode.get(i)));
            textView.setText(listCode.get(i));
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (view2.isSelected()){
                        view2.setSelected(false);
                        changeData(textView.getText().toString(),false);
                    }else{
                        view2.setSelected(true);
                        changeData(textView.getText().toString(),true);
                    }

                }
            });
            addView(view);
        }
    }


    public void changeData(String code,Boolean sel){
        if (sel){
            listCode.add(code);
        }else {
            listCode.remove(code);
        }
        onChartClicListner.sure(listCode);

    }
    private OnChartClicListner onChartClicListner;
    public void setOnChartClicListner(OnChartClicListner onChartClicListner){
        this.onChartClicListner = onChartClicListner;
    }
    public interface OnChartClicListner{
        void sure(ArrayList<String> listCode1);
    }


}
