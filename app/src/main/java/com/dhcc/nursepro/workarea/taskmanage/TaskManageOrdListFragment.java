package com.dhcc.nursepro.workarea.taskmanage;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.MessageEvent;
import com.base.commlibs.comm.BaseCommFragment;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.base.commlibs.utils.SimpleCallBack;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.DialogFactory;
import com.dhcc.nursepro.utils.MainConfigUtil;
import com.dhcc.nursepro.workarea.orderexecute.SkinResultOrderDialog;
import com.dhcc.nursepro.workarea.orderexecute.adapter.OrderExecutePatientOrderAdapter;
import com.dhcc.nursepro.workarea.orderexecute.api.OrderExecuteApiManager;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecResultBean;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecuteBean;
import com.dhcc.nursepro.workarea.taskmanage.bean.SheetPatListBean;
import com.dhcc.nursepro.workarea.taskmanage.bean.TaskManageRequest;
import com.dhcc.res.infusion.CustomOrdExeBottomView;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-05-12/17:22
 * @email:grs0515@163.com
 */
public class TaskManageOrdListFragment extends BaseCommFragment {

    private OrderExecutePatientOrderAdapter patientOrderAdapter;
    private String sheetCode = "";
    private StringBuffer sbOeoreId;
    private List<List<OrderExecuteBean.OrdersBean.PatOrdsBean>> patOrders;
    private String sheetDesc;
    private CustomOrdExeBottomView bottomView;
    private List<OrderExecuteBean.ButtonsBean> buttons;
    private TaskManageRequest.GetOrder paramGetOrder;
    private TaskManageRequest.ExecOrSeeOrder paramExecOrSeeOrder;
    private SkinResultOrderDialog skinResultOrderDialog;
    private String patInfo = "";

    @Override
    protected void initDatas() {
        paramGetOrder = new TaskManageRequest.GetOrder();
        paramExecOrSeeOrder = new TaskManageRequest.ExecOrSeeOrder();

        setToolbarCenterTitle(MainConfigUtil.getToolBarTitle(TaskManageFragment.class));
        String json = getArguments().getString("json");
        if (json == null) return;
        SheetPatListBean bean = new Gson().fromJson(json, SheetPatListBean.class);
        paramGetOrder.regNo = bean.getPatRegNo();
        paramGetOrder.sheetCode = bean.getSheetCode();
        sheetCode = bean.getSheetCode();
        sheetDesc = bean.getSheetDesc();

        getOrdList();

    }

    private void getOrdList() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        TaskManageApi.getOrder(paramGetOrder, new OrderExecuteApiManager.GetOrderCallback() {

            @Override
            public void onSuccess(OrderExecuteBean orderExecuteBean) {
                hideLoadingTip();
                buttons = orderExecuteBean.getButtons();
                setBottomViewData();
                if (orderExecuteBean.getOrders() != null && orderExecuteBean.getOrders().size() > 0) {
                    OrderExecuteBean.OrdersBean patient = orderExecuteBean.getOrders().get(0);
                    patInfo = "" + patient.getBedCode() + "床  " + patient.getName() + "   " + sheetDesc;
                    f(R.id.tv_info, TextView.class).setText(patInfo);
                    patOrders = patient.getPatOrds();
                    if (patOrders != null && patOrders.size() > 0) {
                        patientOrderAdapter.setSize(patOrders.size());
                        patientOrderAdapter.setDetailColums(orderExecuteBean.getDetailColums());
                        patientOrderAdapter.setNewData(patOrders);
                    }
                    //更新之前数据
                    MessageEvent messageEvent = new MessageEvent(MessageEvent.MessageType.UPDATE_TASK_MANAGE_LIST);
                    messageEvent.setTag(patOrders.size());
                    messageEvent.setMessage(sheetCode);
                    EventBus.getDefault().post(messageEvent);
                }
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast(msg);
            }
        });
    }

    private void setBottomViewData() {
        bottomView.setSelectText("已选 0个");
        bottomView.addBottomView(buttons);

        for (OrderExecuteBean.ButtonsBean button : buttons) {
            //s execode="A|接受^S|完成^R|拒绝"
            if (button.getExecode().contains("|") && button.getExecode().contains("^")) {
                List<SheetListBean> listBeans = new ArrayList<>();
                String[] split = button.getExecode().split("\\^");
                for (String s : split) {
                    SheetListBean typeBean = new SheetListBean();
                    typeBean.setCode(s.split("\\|")[0]);
                    typeBean.setDesc(s.split("\\|")[1]);
                    listBeans.add(typeBean);
                }
                bottomView.adBottomType(listBeans);
            }
        }
    }

    @Override
    protected void initViews() {
        bottomView = f(R.id.custom_bottom, CustomOrdExeBottomView.class);
        bottomView.setBottomViewClickListener(new SimpleCallBack<View>() {
            @Override
            public void call(View v, int position) {
                if (checkSelectItem().size() == 0) {
                    ToastUtils.showShort("请选择医嘱");
                    return;
                }
                OrderExecuteBean.ButtonsBean buttonsBean = buttons.get(position);
                if (TextUtils.isEmpty(buttonsBean.getExecode())) {
                    showToast("未配置按钮，不可操作");
                    return;
                }
                //s execode="A|接受^S|完成^R|拒绝"
                if (buttonsBean.getExecode().contains("|")) {
                    paramExecOrSeeOrder.execStatusCode = bottomView.getExeCode();
                } else {
                    paramExecOrSeeOrder.execStatusCode = buttonsBean.getExecode();
                }
                if ("PSD".equals(sheetCode)) {
                    //弹框置皮试结果
                    if (paramExecOrSeeOrder.oeoreId.split("\\^").length > 1) {
                        showToast("皮试结果只能逐一设置，请选择单条医嘱执行");
                    } else {
                        showSkinResultOrderDialog(buttonsBean.getSingleFlag());
                    }
                } else {
                    execOrSeeOrder();
                }
            }
        });
        RecyclerView rvList = f(R.id.rv_list, RecyclerView.class);
        RecyclerViewHelper.setDefaultRecyclerView(mContext, rvList);
        patientOrderAdapter = new OrderExecutePatientOrderAdapter(new ArrayList<>());
        rvList.setAdapter(patientOrderAdapter);
        patientOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OrderExecuteBean.OrdersBean.PatOrdsBean patOrdsBean = patOrders.get(position).get(0);
                if (view.getId() == R.id.ll_oepat_orderselect) {
                    if (patOrdsBean.getSelect() == null || "0".equals(patOrdsBean.getSelect()) || "".equals(patOrdsBean.getSelect())) {
                        patOrdsBean.setSelect("1");
                    } else {
                        patOrdsBean.setSelect("0");
                    }
                    patientOrderAdapter.notifyItemChanged(position);
                    bottomView.setSelectText("已选 " + checkSelectItem().size() + "个");
                }
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_task_manage_detail;
    }

    private List<OrderExecuteBean.OrdersBean.PatOrdsBean> checkSelectItem() {
        sbOeoreId = new StringBuffer();
        List<OrderExecuteBean.OrdersBean.PatOrdsBean> list = new ArrayList<>();
        for (List<OrderExecuteBean.OrdersBean.PatOrdsBean> orders : patOrders) {
            for (OrderExecuteBean.OrdersBean.PatOrdsBean order : orders) {
                if ("1".equals(order.getSelect())) {
                    list.add(order);
                    sbOeoreId.append(order.getOrderInfo().getID() + "^");
                }
            }
        }
        //拼接医嘱ID
        String s = sbOeoreId.toString();
        if (!TextUtils.isEmpty(s)) {
            paramExecOrSeeOrder.oeoreId = s.substring(0, s.length() - 1);
        }
        return list;
    }

    /**
     * 皮试单-点击'皮试结果'/'皮试计时'
     */
    private void showSkinResultOrderDialog(String skinSinge) {
        if (!TextUtils.isEmpty(sheetDesc) && sheetDesc.contains("计时")) {
            // 皮试计时
            DialogFactory.showCountTime(getActivity(), new DialogFactory.CommClickListener() {
                @Override
                public void data(Object[] args) {
                    super.data(args);
                    doSkinTime((String) args[0], (String) args[1]);
                }
            });
            return;
        }
        if (skinResultOrderDialog != null && skinResultOrderDialog.isShowing()) {
            skinResultOrderDialog.dismiss();
        }
        skinResultOrderDialog = new SkinResultOrderDialog(getActivity());
        skinResultOrderDialog.setPatInfo(patInfo);
        skinResultOrderDialog.setSingleFlag(skinSinge);
        skinResultOrderDialog.show();
        skinResultOrderDialog.setSureOnclickListener(new SkinResultOrderDialog.onSureOnclickListener() {
            @Override
            public void onSureClick() {
                paramExecOrSeeOrder.execStatusCode = skinResultOrderDialog.getSkinResult();
                paramExecOrSeeOrder.batch = skinResultOrderDialog.getSkinNum();
                paramExecOrSeeOrder.auditUserCode = skinResultOrderDialog.getNurName();
                paramExecOrSeeOrder.auditUserPass = skinResultOrderDialog.getNurPass();
                String userId = SPStaticUtils.getString(SharedPreference.USERCODE);
                if (paramExecOrSeeOrder.auditUserCode.equals(userId)) {
                    showToast("请其他护士进行双签");
                } else {
                    skinResultOrderDialog.dismiss();
                    execOrSeeOrder();
                }
            }
        });

        skinResultOrderDialog.setCancelOnclickListener(new SkinResultOrderDialog.onCancelOnclickListener() {
            @Override
            public void onCancelClick() {
                skinResultOrderDialog.dismiss();
            }
        });
    }

    private void doSkinTime(String observeTime, String note) {
        OrderExecuteApiManager.skinTime(paramExecOrSeeOrder.oeoreId, observeTime, note, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                DialogFactory.showCommDialog(getActivity(), bean.getMsg(), null, 0, null, true);
                getOrdList();//刷新
            }
        });
    }

    private void execOrSeeOrder() {
        paramExecOrSeeOrder.scanFlag = "0";
        TaskManageApi.execOrSeeOrder(paramExecOrSeeOrder, new OrderExecuteApiManager.ExecOrSeeOrderCallback() {
            @Override
            public void onSuccess(OrderExecResultBean orderExecResultBean) {
                DialogFactory.showCommDialog(getActivity(), orderExecResultBean.getMsg(), null, 0, null, true);
                getOrdList();//刷新
            }

            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }
        });

    }
}
