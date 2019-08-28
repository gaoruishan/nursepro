package com.dhcc.nursepro.workarea.nurrecordold;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;

import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecordold.api.NurRecordOldApiManager;
import com.dhcc.nursepro.workarea.nurrecordold.bean.RecDataBean;

import org.dom4j.DocumentException;

import java.util.Objects;

/**
 * NurRecordViewPagerFragment
 * 护理病历分页显示
 *
 * @author Devlix126
 * created at 2019/7/10 14:26
 */
public class NurRecordViewPagerFragment extends BaseFragment {
    private AbsoluteLayout absoluelayout;
    private String episodeID = "";
    private String emrCode = "";
    private XmlParseInterface xmlParseInterface;
    private String xml = "";

    public static NurRecordViewPagerFragment newInstance(String episodeID, String emrCode) {
        NurRecordViewPagerFragment fragment = new NurRecordViewPagerFragment();
        Bundle args = new Bundle();
        args.putString("EPISODEID", episodeID);
        args.putString("EMRCODE", emrCode);
        fragment.setArguments(args);
        return fragment;
    }

    public void setXmlParseInterface(XmlParseInterface xmlParseInterface) {
        this.xmlParseInterface = xmlParseInterface;
        initData();
    }

    private void initData() {
        NurRecordOldApiManager.getEmrXML(episodeID, emrCode, new NurRecordOldApiManager.RecDataCallback() {
            @Override
            public void onSuccess(RecDataBean recDataBean) {
                xml = recDataBean.getRetData();
                try {
                    xmlParseInterface.DocParseXml(xml, getActivity(),
                            absoluelayout);

                    Intent xmlIntent = new Intent();
                    xmlIntent.setAction(Action.NUR_RECORD_XML_VIEW);
                    xmlIntent.putExtra("xmlStr", xml);
                    xmlIntent.putExtra("viewId", absoluelayout.getId());
                    Objects.requireNonNull(getActivity()).sendBroadcast(xmlIntent);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });

    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nur_record_view_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            emrCode = getArguments().getString("EMRCODE");
        }

        initview(view);

    }

    private void initview(View view) {
        absoluelayout = view.findViewById(R.id.absoluelayout);
        //        DisplayMetrics dm = new DisplayMetrics();
        //        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        //        int widthPixels = dm.widthPixels;
        //        int heightPixels = dm.heightPixels;
        //        float density = dm.density;
        //        int screenWidth = (int) (widthPixels * density);
        //        int screenHeight = (int) (heightPixels * density);
        //
        //        Log.d(TAG, "initview: " + widthPixels + "~~~" + heightPixels);
        //        Log.d(TAG, "initview: " + screenWidth + "~~~" + screenHeight);

    }

}
