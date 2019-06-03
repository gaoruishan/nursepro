package com.dhcc.nursepro.workarea;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.allotbed.AllotBedFragment;
import com.dhcc.nursepro.workarea.bedmap.BedMapFragment;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodTransfusionSystemFragment;
import com.dhcc.nursepro.workarea.checkresult.CheckPatsFragment;
import com.dhcc.nursepro.workarea.docorderlist.DocOrderListFragment;
import com.dhcc.nursepro.workarea.dosingreview.DosingReviewFragment;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.DrugHandoverFragment;
import com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.RLRegFragment;
import com.dhcc.nursepro.workarea.labout.LabOutListFragment;
import com.dhcc.nursepro.workarea.labresult.LabPatsFragment;
import com.dhcc.nursepro.workarea.milkloopsystem.MilkLoopSystemFragment;
import com.dhcc.nursepro.workarea.motherbabylink.MotherBabyLinkFragment;
import com.dhcc.nursepro.workarea.nurrecord.NurRecordModellistFragmen;
import com.dhcc.nursepro.workarea.nurtour.NurTourFragment;
import com.dhcc.nursepro.workarea.operation.OperationFragment;
import com.dhcc.nursepro.workarea.orderexecute.OrderExecuteFragment;
import com.dhcc.nursepro.workarea.ordersearch.OrderSearchFragment;
import com.dhcc.nursepro.workarea.patevents.PatEventsFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignFragment;
import com.dhcc.nursepro.workarea.workareaapi.WorkareaApiManager;
import com.dhcc.nursepro.workarea.workareabean.MainConfigBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * WorkareaFragment
 * 主页
 */
public class WorkareaFragment extends BaseFragment {
    private RecyclerView recConfig;
    private List<String> ItemNameList = new ArrayList<String>();
    private WorkAreaAdapter patEventsAdapter;


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workarea, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);
        //        setToolbarType(BaseActivity.ToolbarType.TOP);
        //        setToolbarBottomLineVisibility(true);
        //        hideToolbarNavigationIcon();

        initView(view);
        initData();
    }

    private void initView(View view) {

        recConfig = view.findViewById(R.id.recy_workarea);
        recConfig.setHasFixedSize(true);
        recConfig.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        patEventsAdapter = new WorkAreaAdapter(new ArrayList<String>());
        recConfig.setAdapter(patEventsAdapter);
        patEventsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                itemClick(ItemNameList.get(position) + "");
            }
        });
    }

    private void initData() {
        HashMap<String, String> properties = new HashMap<String, String>();
        WorkareaApiManager.getMainConfig(properties, "getMainConfig", new WorkareaApiManager.GetMainconfigCallback() {
            @Override
            public void onSuccess(MainConfigBean mainConfigBean) {
                ItemNameList = mainConfigBean.getMainConfig();
//                ItemNameList.add("DRUGHANDOVER");
//                ItemNameList.add("RLREG");
                patEventsAdapter.setNewData(ItemNameList);
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    /**
     * MainConfig
     * ("BEDMAP");//床位图
     * ("VITALSIGN");//生命体征
     * ("EVENTS");//事件管理
     * ("ORDERSEARCH");//医嘱查询
     * ("ORDEREXECUTE");//医嘱执行
     * ("CHECK");//检查报告
     * ("LAB");//检验报告
     * ("OPERATION");//手术申请
     * ("LABOUT");//检验打包
     * ("DOSINGREVIEW");//输液复核
     * ("ALLOTBED");//入院分床
     * ("DOCORDERLIST");//医嘱单
     * ("BLOOD");//输血系统
     * ("MILK");//母乳闭环
     * ("MOTHERBABYLINK");//母婴关联
     * ("MODELDETAIL");//护理病历
     * ("NURTOUR");//巡视
     */
    public void itemClick(String itemName) {
        switch (itemName) {
            case "BEDMAP":
                startFragment(BedMapFragment.class);
                break;
            case "VITALSIGN":
                startFragment(VitalSignFragment.class);
                break;
            case "EVENTS":
                startFragment(PatEventsFragment.class);
                break;
            case "ORDERSEARCH":
                startFragment(OrderSearchFragment.class);
                break;
            case "ORDEREXECUTE":
                startFragment(OrderExecuteFragment.class);
                break;
            case "CHECK":
                startFragment(CheckPatsFragment.class);
                break;
            case "LAB":
                startFragment(LabPatsFragment.class);
                break;
            case "OPERATION":
                startFragment(OperationFragment.class);
                break;
            case "LABOUT":
                startFragment(LabOutListFragment.class);
                break;
            case "DOSINGREVIEW":
                startFragment(DosingReviewFragment.class);
                break;
            case "ALLOTBED":
                startFragment(AllotBedFragment.class);
                break;
            case "DOCORDERLIST":
                startFragment(DocOrderListFragment.class);
                break;
            case "BLOOD":
                startFragment(BloodTransfusionSystemFragment.class);
                break;
            case "MILK":
                startFragment(MilkLoopSystemFragment.class);
                break;
            case "MOTHERBABYLINK":
                startFragment(MotherBabyLinkFragment.class);
                break;
            case "MODELDETAIL":
                startFragment(NurRecordModellistFragmen.class);
                break;
            case "NURTOUR":
                startFragment(NurTourFragment.class);
                break;
            case "DRUGHANDOVER":
                startFragment(DrugHandoverFragment.class);
                break;
            case "RLREG":
                startFragment(RLRegFragment.class);
                break;
            default:

                break;
        }
    }

    public class WorkAreaAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public WorkAreaAdapter(@Nullable List<String> data) {
            super(R.layout.item_workarea,
                    data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView tvItem = helper.getView(R.id.tv_workarea);
            ImageView imageView = helper.getView(R.id.icon_workarea);
            switch (item) {
                case "BEDMAP":
                    tvItem.setText("床位图");
                    imageView.setImageResource(R.drawable.icon_bedmap);
                    break;
                case "VITALSIGN":
                    tvItem.setText("生命体征");
                    imageView.setImageResource(R.drawable.icon_vitalsign);
                    break;
                case "EVENTS":
                    tvItem.setText("事件登记");
                    imageView.setImageResource(R.drawable.icon_events);
                    break;
                case "ORDERSEARCH":
                    tvItem.setText("医嘱查询");
                    imageView.setImageResource(R.drawable.icon_orderserarch);
                    break;
                case "ORDEREXECUTE":
                    tvItem.setText("医嘱执行");
                    imageView.setImageResource(R.drawable.icon_orderexcute);
                    break;
                case "CHECK":
                    tvItem.setText("检查报告");
                    imageView.setImageResource(R.drawable.icon_check);
                    break;
                case "LAB":
                    tvItem.setText("检验结果");
                    imageView.setImageResource(R.drawable.icon_lab);
                    break;
                case "OPERATION":
                    tvItem.setText("手术查询");
                    imageView.setImageResource(R.drawable.icon_operation);
                    break;
                case "LABOUT":
                    tvItem.setText("检验打包");
                    imageView.setImageResource(R.drawable.icon_labout);
                    break;
                case "DOSINGREVIEW":
                    tvItem.setText("配液复核");
                    imageView.setImageResource(R.drawable.icon_dosingreview);
                    break;
                case "ALLOTBED":
                    tvItem.setText("入院分床");
                    imageView.setImageResource(R.drawable.icon_allotbed);
                    break;
                case "DOCORDERLIST":
                    tvItem.setText("医嘱单");
                    imageView.setImageResource(R.drawable.icon_docorderlist);
                    break;
                case "BLOOD":
                    tvItem.setText("输血系统");
                    imageView.setImageResource(R.drawable.icon_blood);
                    break;
                case "MILK":
                    tvItem.setText("母乳闭环");
                    imageView.setImageResource(R.drawable.icon_milk);
                    break;
                case "MOTHERBABYLINK":
                    tvItem.setText("母婴关联");
                    imageView.setImageResource(R.drawable.icon_motherbabylink);
                    break;
                case "MODELDETAIL":
                    tvItem.setText("护理病历");
                    imageView.setImageResource(R.drawable.icon_motherbabylink);
                    break;
                case "NURTOUR":
                    tvItem.setText("巡视");
                    imageView.setImageResource(R.drawable.icon_tour);
                    break;
                case "DRUGHANDOVER":
                    tvItem.setText("药品交接");
                    imageView.setImageResource(R.drawable.icon_tour);
                    break;
                case "RLREG":
                    tvItem.setText("余液登记");
                    imageView.setImageResource(R.drawable.icon_tour);
                    break;
                default:
                    break;
            }
        }
    }
}
