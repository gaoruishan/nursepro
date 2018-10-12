package com.dhcc.nursepro.workarea.bedmap;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.adapter.BedMapPatInfoDetailAdapter;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.jzxiang.pickerview.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BedMapPatInfoFragment
 * 个人信息
 *
 * @author DevLix126
 * created at 2018/10/11 16:07
 */
public class BedMapPatInfoFragment extends BaseFragment {

    private BedMapBean.PatInfoListBean patInfoListBean;
    private BedMapBean.PatInfoListBean.PatInfoDetailBean patInfoDetailBean;
    private HashMap<String,String> paMap = new HashMap<String, String>();
    private List<HashMap<String,String>> list = new ArrayList<>();
    private RecyclerView recPatInfo;
    private BedMapPatInfoDetailAdapter bedMapPatInfoDetailAdapter;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bed_map_pat_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        patInfoDetailBean = (BedMapBean.PatInfoListBean.PatInfoDetailBean) bundle.getSerializable("patinfo");
        //状态栏 背景 默认蓝色
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        //toolbar type
        setToolbarType(BaseActivity.ToolbarType.TOP);
        //toolbar 背景 默认蓝色
        setToolbarBackground(new ColorDrawable(0xffffffff));
        //toolbar 导航按钮隐藏
        //        hideToolbarNavigationIcon();
        //toolbar 导航按钮显示 默认白色返回按钮
        //        showToolbarNavigationIcon();
        //返回按钮img设置
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        //toolbar 中间标题 默认黑色
        String bedinfo = patInfoDetailBean.getBedCode().isEmpty()?"未分"+ "床  "+patInfoDetailBean.getName():patInfoDetailBean.getBedCode()+"床  "+patInfoDetailBean.getName();
        setToolbarCenterTitle(bedinfo);
        //toolbar 中间标题 内容 颜色 字号默认17
        //        setToolbarCenterTitle(getString(R.string.title_bedmap),0xffffffff,17);
        //toolbar 分割线
        setToolbarBottomLineVisibility(true);


        initView(view);
        initData();


    }

    private void initView(View view) {
        recPatInfo = view.findViewById(R.id.recy_bedmap_patientdetail);

        //提高展示效率
        recPatInfo.setHasFixedSize(true);
        //设置的布局管理
        recPatInfo.setLayoutManager(new LinearLayoutManager(getActivity()));
        bedMapPatInfoDetailAdapter = new BedMapPatInfoDetailAdapter(new ArrayList<HashMap<String, String>>());
        recPatInfo.setAdapter(bedMapPatInfoDetailAdapter);

    }

    private void initData() {
        paMap = new HashMap<>();
        paMap.put("title","费别");
        paMap.put("content",patInfoDetailBean.getAdmReason());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","年龄");
        paMap.put("content",patInfoDetailBean.getAge());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","押金余额");
        paMap.put("content",patInfoDetailBean.getBalance());
        list.add(paMap);

//        paMap = new HashMap<>();
//        paMap.put("title","床号");
//        paMap.put("content",patInfoDetailBean.getBedCode());
//        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","护理级别");
        paMap.put("content",patInfoDetailBean.getCareLevel());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","科室");
        paMap.put("content",patInfoDetailBean.getCtLocDesc());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","当前病区ID");
        paMap.put("content",patInfoDetailBean.getCurrWardID());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","诊断");
        paMap.put("content",patInfoDetailBean.getDiagnosis());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","出院状态");
        paMap.put("content",patInfoDetailBean.getDischargeStatus());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","就诊号");
        paMap.put("content",patInfoDetailBean.getEpisodeID());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","住址");
        paMap.put("content",patInfoDetailBean.getHomeAddres());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","新生儿标记");
        paMap.put("content",patInfoDetailBean.getIfNewBaby());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","病情");
        paMap.put("content",patInfoDetailBean.getIllState());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","住院天数");
        paMap.put("content",patInfoDetailBean.getInDays());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","入科时间");
        paMap.put("content",patInfoDetailBean.getInDeptDateTime());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","入院时间");
        paMap.put("content",patInfoDetailBean.getInHospDateTime());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","入保号");
        paMap.put("content",patInfoDetailBean.getInsuranceCard());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","主管医师");
        paMap.put("content",patInfoDetailBean.getMainDoctor());
        list.add(paMap);

//        paMap.put("title","主管医师ID");
//        paMap.put("content",patInfoDetailBean.getMainDoctorID());

        paMap = new HashMap<>();
        paMap.put("title","主管护士");
        paMap.put("content",patInfoDetailBean.getMainNurse());
        list.add(paMap);

//        paMap.put("title","主管护士ID");
//        paMap.put("content",patInfoDetailBean.getAge());

        paMap = new HashMap<>();
        paMap.put("title","住院号");
        paMap.put("content",patInfoDetailBean.getMedicareNo());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","母亲转科");
        paMap.put("content",patInfoDetailBean.getMotherTransLoc());
        list.add(paMap);

//        paMap = new HashMap<>();
//        paMap.put("title","姓名");
//        paMap.put("content",patInfoDetailBean.getName());
//        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","民族");
        paMap.put("content",patInfoDetailBean.getNation());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","手术");
        paMap.put("content",patInfoDetailBean.getOperation());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","医嘱ID");
        paMap.put("content",patInfoDetailBean.getOrderID());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","联系人");
        paMap.put("content",patInfoDetailBean.getPatLinkman());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","病人编号");
        paMap.put("content",patInfoDetailBean.getPatientID());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","身份证好");
        paMap.put("content",patInfoDetailBean.getPersonID());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","登记时间");
        paMap.put("content",patInfoDetailBean.getRegDateTime());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","登记号");
        paMap.put("content",patInfoDetailBean.getRegNo());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","病室");
        paMap.put("content",patInfoDetailBean.getRoomDesc());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","性别");
        paMap.put("content",patInfoDetailBean.getSex());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","电话");
        paMap.put("content",patInfoDetailBean.getTelphone());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","总费用");
        paMap.put("content",patInfoDetailBean.getTotalCost());
        list.add(paMap);

        paMap = new HashMap<>();
        paMap.put("title","病区");
        paMap.put("content",patInfoDetailBean.getWardDesc());
        list.add(paMap);

        bedMapPatInfoDetailAdapter.setNewData(list);
        bedMapPatInfoDetailAdapter.notifyDataSetChanged();



//        View itemView = View.inflate(getActivity(), R.layout.item_patinfodetail, null);
//        llPatInfo.addView(itemView);
    }

}
