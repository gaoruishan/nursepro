package com.dhcc.nursepro.workarea.workareaadapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.constant.SharedPreference;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.education.HealthEduFragment;
import com.dhcc.module.nurse.task.TaskOverviewFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.allotbed.AllotBedFragment;
import com.dhcc.nursepro.workarea.bedmap.BedMapFragment;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodTransfusionSystemFragment;
import com.dhcc.nursepro.workarea.checkresult.CheckPatsFragment;
import com.dhcc.nursepro.workarea.docorderlist.DocOrderListFragment;
import com.dhcc.nursepro.workarea.dosingreview.DosingReviewFragment;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.DrugHandoverFragment;
import com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.RLRegFragment;
import com.dhcc.nursepro.workarea.infusiondrugreceive.DrugReceiveFragment;
import com.dhcc.nursepro.workarea.labout.LabOutListFragment;
import com.dhcc.nursepro.workarea.labresult.LabPatsFragment;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.MilkLoopSystemFragment;
import com.dhcc.nursepro.workarea.motherbabylink.MotherBabyLinkFragment;
import com.dhcc.nursepro.workarea.nurrecordnew.PatNurRecordFragment;
import com.dhcc.nursepro.workarea.nurtour.NurTourFragment;
import com.dhcc.nursepro.workarea.operation.OperationFragment;
import com.dhcc.nursepro.workarea.orderexecute.OrderExecuteFragment;
import com.dhcc.nursepro.workarea.orderexecute.OrderSearchAndExecuteFragment;
import com.dhcc.nursepro.workarea.ordersearch.OrderSearchFragment;
import com.dhcc.nursepro.workarea.patevents.PatEventsFragment;
import com.dhcc.nursepro.workarea.plyout.PlyOutListFragment;
import com.dhcc.nursepro.workarea.rjorder.RjOrderFragment;
import com.dhcc.nursepro.workarea.shift.ShiftFragment;
import com.dhcc.nursepro.workarea.taskmanage.TaskManageFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignFragment;
import com.dhcc.nursepro.workarea.workareabean.MainConfigBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.dhcc.nursepro.workarea
 * <p>
 * author Q
 * Date: 2020/8/5
 * Time:15:39
 */
public class WorkAreaAdapter extends BaseQuickAdapter<HashMap, BaseViewHolder> {

    public WorkAreaAdapter(@Nullable List<HashMap> data) {
        super(R.layout.item_workarea, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HashMap item) {
        TextView tvItem = helper.getView(R.id.tv_workarea);
        ImageView imageView = helper.getView(R.id.icon_workarea);
        tvItem.setText(item.get("desc").toString());
        try{
            int iconPath = (int) item.get("fragicon");
            imageView.setImageResource(iconPath);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}