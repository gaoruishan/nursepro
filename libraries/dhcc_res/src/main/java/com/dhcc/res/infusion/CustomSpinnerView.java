package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.dhcc.res.BaseView;
import com.grs.dhcc_res.R;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202021/2/3/16:11
 * @email:grs0515@163.com
 */
public class CustomSpinnerView extends BaseView {

    private Spinner spinner;

    public CustomSpinnerView(Context context) {
        this(context,null);
    }

    public CustomSpinnerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomSpinnerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(ContextCompat.getColor(context, R.color.transparency));
        spinner = findViewById(R.id.spinner);
    }

    public void initDataView( List<String> spinnerItems,AdapterView.OnItemSelectedListener listener) {
        if (spinnerItems == null||spinnerItems.size()==0) {
            return;
        }
        //只能是textview样式，否则报错：ArrayAdapter requires the resource ID to be a TextView
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.dhcc_spinner_item,spinnerItems){
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = inflate(getContext(), R.layout.dhcc_spinner_item_layout, null);
                TextView label = (TextView) view.findViewById(R.id.spinner_item_label);
                ImageView check = (ImageView) view.findViewById(R.id.spinner_item_checked_image);
                label.setText(spinnerItems.get(position));
                if (spinner.getSelectedItemPosition() == position) {
                    check.setImageResource(R.drawable.checkbox_pressed);
                } else {
                    check.setImageResource(R.drawable.checkbox_normal);
                }
                return view;
//                return super.getDropDownView(position, convertView, parent);
            }
        };
        //自定义下拉的字体样式
//        spinnerAdapter.setDropDownViewResource(R.layout.dhcc_spinner_item_layout);
        //这个在不同的Theme下，显示的效果是不同的
        //spinnerAdapter.setDropDownViewTheme(Theme.LIGHT);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                mPosition[0] = position;
                Log.e(TAG,"(SetIPDialog.java:115) "+position);
                if (listener != null) {
                    listener.onItemSelected(parent,view,position,id);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (listener != null) {
                    listener.onNothingSelected(parent);
                }
            }
        });
    }

    @Override
    protected int setContentView() {
        return R.layout.custom_spinner_view;
    }


}
