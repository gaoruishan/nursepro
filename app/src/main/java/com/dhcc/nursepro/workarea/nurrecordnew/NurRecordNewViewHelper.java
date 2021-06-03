package com.dhcc.nursepro.workarea.nurrecordnew;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.base.commlibs.BaseFragment;
import com.blankj.utilcode.util.ConvertUtils;
import com.dhcc.nursepro.R;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

/**
 * 优化NurRecordNewFragment到ViewHelper
 * @author:gaoruishan
 * @date:202020-09-02/14:17
 * @email:grs0515@163.com
 */
public class NurRecordNewViewHelper extends BaseFragment {

    public final static int DATE_DIALOG = 0;
    public final static int TIME_DIALOG = 1;
    public Calendar c = null;
    public boolean[] dcTempStatus;
    public ListView lv;



    /**
     * 是否为数字
     *
     * @param obj
     * @return
     */
    public boolean isNumber(Object obj) {
        if (obj instanceof Number) {
            return true;
        } else if (obj instanceof String) {
            try {
                Double.parseDouble((String) obj);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * 虚线分隔
     *
     * @return
     */
    public View getDashLine() {
        View view = new View(getActivity());
        LinearLayout.LayoutParams viewparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ConvertUtils.dp2px(4));
        view.setLayoutParams(viewparams);
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        view.setBackground(getResources().getDrawable(R.drawable.line_dash));
        return view;

    }
    /**
     * 时间日期选择窗口
     *
     * @param id
     * @param context
     * @param textView
     */
    public void ShowDateTime(int id, Context context, final TextView textView) {
        Dialog dialog;
        c = Calendar.getInstance();
        String DateTimeStr = textView.getText().toString();
        switch (id) {
            case DATE_DIALOG:
                if (DateTimeStr.split("-").length == 3) {
                    dialog = new DatePickerDialog(context,
                            (dp, year, month, dayOfMonth) -> {

                                DecimalFormat df = new DecimalFormat("00");

                                String datestr = year + "-" + df.format(month + 1)
                                        + "-" + df.format(dayOfMonth);
                                textView.setText(datestr);
                            }, Integer.parseInt(DateTimeStr.split("-")[0]),
                            Integer.parseInt(DateTimeStr.split("-")[1]) - 1,
                            Integer.parseInt(DateTimeStr.split("-")[2])
                    );
                } else {
                    dialog = new DatePickerDialog(context,
                            (dp, year, month, dayOfMonth) -> {

                                DecimalFormat df = new DecimalFormat("00");

                                String datestr = year + "-" + df.format(month + 1)
                                        + "-" + df.format(dayOfMonth);
                                textView.setText(datestr);
                            }, c.get(Calendar.YEAR),
                            c.get(Calendar.MONTH),
                            c.get(Calendar.DAY_OF_MONTH)
                    );
                }

                dialog.show();
                break;
            case TIME_DIALOG:
                if (DateTimeStr.split(":").length == 2) {
                    dialog = new TimePickerDialog(context,
                            (view, hourOfDay, minute) -> {
                                DecimalFormat df = new DecimalFormat("00");
                                String timeStr = df.format(hourOfDay) + ":" + df.format(minute);
                                textView.setText(timeStr);
                            }, Integer.parseInt(DateTimeStr.split(":")[0]), Integer.parseInt(DateTimeStr.split(":")[1]),
                            false);
                } else {
                    dialog = new TimePickerDialog(context,
                            (view, hourOfDay, minute) -> {
                                DecimalFormat df = new DecimalFormat("00");
                                String timeStr = df.format(hourOfDay) + ":" + df.format(minute);
                                textView.setText(timeStr);
                            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
                            false);
                }

                dialog.show();
                break;
            default:
                break;
        }

    }

}
