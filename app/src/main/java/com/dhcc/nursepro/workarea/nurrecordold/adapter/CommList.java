package com.dhcc.nursepro.workarea.nurrecordold.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dhcc.nursepro.R;

import java.util.ArrayList;
import java.util.List;


public class CommList extends BaseAdapter {
	List<CharSequence> data = new ArrayList<>();
	Context mContext;
	private LayoutInflater mInflater;

	public CommList(Context context, List list) {
		mContext = context;

		data = list;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(R.layout.comm_item, null);

		String lp = (String) data.get(position);
		TextView txt = convertView.findViewById(R.id.Desc);
		txt.setText(lp);
		/*
		 * txt.setOnTouchListener(new OnTouchListener(){
		 * 
		 * @Override public boolean onTouch(View v, MotionEvent event) { //
		 * EditText view = (EditText)v;
		 * //view.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE
		 * |InputType.TYPE_CLASS_TEXT);
		 * ((InputMethodManager)mContext.getSystemService
		 * (mContext.INPUT_METHOD_SERVICE)) .toggleSoftInput(0,
		 * InputMethodManager.HIDE_NOT_ALWAYS);
		 * 
		 * 
		 * return false; } });
		 */

		// ((TextView)
		// convertView.findViewById(R.id.LocID)).setText(lp.get("LocID").toString());

		return convertView;
	}

}
