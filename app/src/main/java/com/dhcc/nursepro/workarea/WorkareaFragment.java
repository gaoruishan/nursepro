package com.dhcc.nursepro.workarea;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.allotbed.AllotBedFragment;
import com.dhcc.nursepro.workarea.bedmap.BedMapFragment;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodTransfusionSystemFragment;
import com.dhcc.nursepro.workarea.checkresult.CheckPatsFragment;
import com.dhcc.nursepro.workarea.docorderlist.DocOrderListFragment;
import com.dhcc.nursepro.workarea.dosingreview.DosingReviewFragment;
import com.dhcc.nursepro.workarea.labout.LabOutListFragment;
import com.dhcc.nursepro.workarea.labresult.LabPatsFragment;
import com.dhcc.nursepro.workarea.milkloopsystem.MilkLoopSystemFragment;
import com.dhcc.nursepro.workarea.operation.OperationFragment;
import com.dhcc.nursepro.workarea.orderexecute.OrderExecuteFragment;
import com.dhcc.nursepro.workarea.ordersearch.OrderSearchFragment;
import com.dhcc.nursepro.workarea.patevents.PatEventsFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * WorkareaFragment
 * 主页
 */
public class WorkareaFragment extends BaseFragment {
    private List ItemNameList = new ArrayList<String>();


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

        initData(view);
    }
    private void initData(View view) {

        ItemNameList.add("BEDMAP");//床位图
        ItemNameList.add("VITALSIGN");//生命体征
        ItemNameList.add("EVENTS");//事件管理
        ItemNameList.add("ORDERSEARCH");//医嘱查询
        ItemNameList.add("ORDEREXECUTE");//医嘱执行
        ItemNameList.add("CHECK");//检查报告
        ItemNameList.add("LAB");//检验报告
        ItemNameList.add("OPERATION");//手术申请
        ItemNameList.add("LABOUT");//检验打包
        ItemNameList.add("DOSINGREVIEW");//输液复合
        ItemNameList.add("ALLOTBED");//入院分床
        ItemNameList.add("DOCORDERLIST");//医嘱单
        ItemNameList.add("BLOOD");//输血系统
        ItemNameList.add("MILK");//母乳闭环

        initView(view);
    }
    private void initView(View view) {

        RecyclerView recyPatevents = view.findViewById(R.id.recy_workarea);
        recyPatevents.setHasFixedSize(true);
//        recyPatevents.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyPatevents.setLayoutManager(new GridLayoutManager(getActivity(),3));
        WorkAreaAdapter patEventsAdapter = new WorkAreaAdapter(new ArrayList<String>());
        recyPatevents.setAdapter(patEventsAdapter);
        patEventsAdapter.setNewData(ItemNameList);
        patEventsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                itemClick(ItemNameList.get(position)+"");
            }
        });
    }

    public void itemClick(String itemName){
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
            default:
//                showToast("正在开发");
                break;
        }
    }

    public class WorkAreaAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
        public WorkAreaAdapter(@Nullable List<String> data) {
            super(R.layout.item_workarea,data);
        }
        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView tvItem = helper.getView(R.id.tv_workarea);
            ImageView imageView = helper.getView(R.id.icon_workarea);
            switch (item) {
                case "BEDMAP":
//                    helper.setText(R.id.tv_workarea,item)
//                    .setImageResource(R.id.icon_workarea,R.drawable.icon_add);
                    tvItem.setText("床位图");
                    imageView.setImageResource(R.drawable.icon_bedmap);
                    break;
                case "VITALSIGN":
                    tvItem.setText("生命体征");
                    imageView.setImageResource(R.drawable.icon_vitalsign);
                    break;
                case "EVENTS":
                    tvItem.setText("事件录入");
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
                    tvItem.setText("检验报告");
                    imageView.setImageResource(R.drawable.icon_lab);
                    break;
                case "OPERATION":
                    tvItem.setText("手术申请");
                    imageView.setImageResource(R.drawable.icon_operation);
                    break;
                case "LABOUT":
                    tvItem.setText("检验打包");
                    imageView.setImageResource(R.drawable.icon_labout);
                    break;
                case "DOSINGREVIEW":
                    tvItem.setText("配液复合");
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
                default:
                    break;
            }
        }
    }
}
