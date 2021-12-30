package com.dhcc.nursepro.workarea.workareautils;

import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.orderexecute.api.OrderExecuteApiManager;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecResultBean;
import com.dhcc.nursepro.workarea.workareaapi.WorkareaApiManager;
import com.dhcc.nursepro.workarea.workareabean.OperateResultBean;
import com.dhcc.nursepro.workarea.workareabean.PreparedVerifyOrdBean;
import com.dhcc.nursepro.workarea.workareabean.ScanResultBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea.workareautils
 * <p>
 * author Q
 * Date: 2020/8/5
 * Time:16:58
 */
public class WorkareaOrdExeUtil {
    public static final int INT_8 = 8;
    private Context mContext;
    //功能扩展 医嘱执行相关数据
    private SPUtils spUtils = SPUtils.getInstance();
    private String episodeId = "";
    private String regNo = "";
    private String scanInfo = "";
    private String barCode = "";
    private String patInfo = "";
    private String patSaveInfo = "";
    private String scanPat = "";
    private String curOeordId = "";
    private ScanResultBean scanResultBean = new ScanResultBean();

    //医嘱执行弹窗
    private WorkareaOrderDialog orderDialog;
    private WorkareaOperateDialog operateDialog;
    private WorkareaResultDialog resultDialog;

    //医嘱执行提示音
    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundPoolMap = new HashMap<Integer, Integer>();
    private String ordSpeed = "";
    private String reason = "";
    private String regNoByPat = "";
    private String regNoByOrd = "";
    private String wayNo = "";
    private String deviceNo;


    public WorkareaOrdExeUtil(Context context) {
        this.mContext = context;
        //提示音集合
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap.put(1, soundPool.load(mContext, R.raw.notice22, 1));
    }

    public void setScanInfo(String scanInfo) {
        this.scanInfo = scanInfo;
    }

    public void getScanInfo() {
        //判断8位监护仪
        if (orderDialog != null && !TextUtils.isEmpty(scanInfo)) {
            if (scanInfo.length() == INT_8 && !scanInfo.contains("-")) {
                orderDialog.setDevicNo(scanInfo);
                return;
            } else {
                boolean barCode = scanInfo.contains("REG") || scanInfo.contains("-");
                if (!barCode) {
                    ToastUtils.showShort("默认监护仪8位格式!");
                    return;
                }
            }
        }
        WorkareaApiManager.getScanMsgByMain(episodeId, curOeordId, scanInfo, new WorkareaApiManager.GetScanCallBack() {
            @Override
            public void onSuccess(ScanResultBean scanResultBeanFromWS) {
                scanResultBean = scanResultBeanFromWS;
                //提示信息
                String msg = scanResultBean.getMsg();

                //提示框
                if (orderDialog == null) {
                    orderDialog = new WorkareaOrderDialog(mContext);

                    orderDialog.setSureOnclickListener(new WorkareaOrderDialog.onSureOnclickListener() {
                        @Override
                        public void onSureClick() {
                            if ("exe".equals(orderDialog.getBtnType())) {
                                ordSpeed = orderDialog.getSpeed() + " " + orderDialog.getSpeedUnit();
                                wayNo = orderDialog.getWayNo();
                                deviceNo = orderDialog.getDeviceNo();
                                execOrSeeOrderScan(patSaveInfo, orderDialog.getSttDateTime(), orderDialog.getArcimDesc(), orderDialog.getOrderId(), "F", orderDialog.getBedCode());
                                orderDialog.dismiss();
                            } else {
                                preparedVerifyOrd(orderDialog.getOrderId());
                                orderDialog.dismiss();
                            }

                        }
                    });

                    orderDialog.setCancelOnclickListener(new WorkareaOrderDialog.onCancelOnclickListener() {
                        @Override
                        public void onCancelClick() {
                            orderDialog.dismiss();
                        }
                    });

                    orderDialog.setTourOnclickListener(new WorkareaOrderDialog.onTourOnclickListener() {
                        @Override
                        public void onTourClick() {
                            operateDialog = new WorkareaOperateDialog(mContext);
                            operateDialog.setIfImgReasonShow(View.GONE);
                            operateDialog.setPatInfo(orderDialog.getPatInfo());
                            operateDialog.setChildOrders(orderDialog.getChildOrders());
                            operateDialog.setOrderInfoEx(orderDialog.getOrderInfoEx());
                            operateDialog.setViewVisibility(View.GONE, View.VISIBLE, View.VISIBLE, true);
                            operateDialog.setSpeedUnit(scanResultBean.getFlowSpeedUnit() + "");
                            operateDialog.setSpeed(scanResultBean.getFlowSpeed() + "");
                            ordSpeed = scanResultBean.getFlowSpeed() + "";
                            List ls = new ArrayList<String>();
                            if (scanResultBean.getSpeedUnitList() != null) {
                                for (int i = 0; i < scanResultBean.getSpeedUnitList().size(); i++) {
                                    ls.add(scanResultBean.getSpeedUnitList().get(i).getUnitDesc());
                                }
                            }
                            operateDialog.setSpiList(ls);
                            List lsReason = new ArrayList<String>();
                            if (scanResultBean.getTourNoteList() != null) {
                                for (int i = 0; i < scanResultBean.getTourNoteList().size(); i++) {
                                    lsReason.add(scanResultBean.getTourNoteList().get(i).getNoteData());
                                }
                            }
                            operateDialog.setSpiReList(lsReason);
                            operateDialog.setIfImgReasonShow(View.VISIBLE);
                            operateDialog.setOrderId(orderDialog.getOrderId());
                            operateDialog.setIfState(orderDialog.getIfState());

                            operateDialog.setSureOnclickListener(new WorkareaOperateDialog.onSureOnclickListener() {
                                @Override
                                public void onSureClick() {
                                    //                                        suspendOrd(operateDialog.getOrderId(), operateDialog.getIfState(), operateDialog.getRemarkinfo());
                                    //                                        operateDialog.dismiss();
                                    ordSpeed = operateDialog.getSpeed() + " " + operateDialog.getSpeedUnit();
                                    reason = operateDialog.getRemarkinfo();
                                    tourOrd(operateDialog.getOrderId());
                                    operateDialog.dismiss();
                                }
                            });
                            operateDialog.setCancelOnclickListener(new WorkareaOperateDialog.onCancelOnclickListener() {
                                @Override
                                public void onCancelClick() {
                                    operateDialog.dismiss();
                                }
                            });
                            operateDialog.setJp(orderDialog.getJp());
                            operateDialog.show();
                            orderDialog.dismiss();
                        }
                        //                            tourOrd(orderDialog.getOrderId());
                        //                            orderDialog.dismiss();
                    });

                    orderDialog.setSuspendContinueclickListenerOnclickListener(new WorkareaOrderDialog.onSuspendContinueclickListener() {
                        @Override
                        public void onSuspendContinueClick() {
                            if ("Suspend".equals(orderDialog.getIfState())) {
                                continueOrd(orderDialog.getOrderId());
                                orderDialog.dismiss();
                            } else {
                                operateDialog = new WorkareaOperateDialog(mContext);
                                operateDialog.setPatInfo(orderDialog.getPatInfo());
                                operateDialog.setChildOrders(orderDialog.getChildOrders());
                                operateDialog.setOrderInfoEx(orderDialog.getOrderInfoEx());
                                operateDialog.setSpeedUnit(scanResultBean.getFlowSpeedUnit() + "");
                                operateDialog.setSpeed(scanResultBean.getFlowSpeed() + "");
                                ordSpeed = scanResultBean.getFlowSpeed() + "";
                                List ls = new ArrayList<String>();
                                if (scanResultBean.getSpeedUnitList() != null) {
                                    for (int i = 0; i < scanResultBean.getSpeedUnitList().size(); i++) {
                                        ls.add(scanResultBean.getSpeedUnitList().get(i).getUnitDesc());
                                    }
                                }
                                operateDialog.setSpiList(ls);
                                List lsReason = new ArrayList<String>();
                                if (scanResultBean.getSuspendNoteList() != null) {
                                    for (int i = 0; i < scanResultBean.getSuspendNoteList().size(); i++) {
                                        lsReason.add(scanResultBean.getSuspendNoteList().get(i).getNoteData());
                                    }
                                }
                                operateDialog.setSpiReList(lsReason);
                                operateDialog.setIfImgReasonShow(View.VISIBLE);
                                operateDialog.setViewVisibility(View.GONE, View.VISIBLE, View.VISIBLE, true);
                                operateDialog.setOrderId(orderDialog.getOrderId());
                                operateDialog.setIfState(orderDialog.getIfState());

                                operateDialog.setSureOnclickListener(new WorkareaOperateDialog.onSureOnclickListener() {
                                    @Override
                                    public void onSureClick() {
                                        ordSpeed = operateDialog.getSpeed() + " " + operateDialog.getSpeedUnit();
                                        suspendOrd(operateDialog.getOrderId(), operateDialog.getIfState(), operateDialog.getRemarkinfo());
                                        operateDialog.dismiss();
                                    }
                                });
                                operateDialog.setCancelOnclickListener(new WorkareaOperateDialog.onCancelOnclickListener() {
                                    @Override
                                    public void onCancelClick() {
                                        operateDialog.dismiss();
                                    }
                                });
                                operateDialog.setJp(orderDialog.getJp());
                                operateDialog.show();
                                orderDialog.dismiss();
                            }
                        }
                    });


                    orderDialog.setStopOnclickListener(new WorkareaOrderDialog.onStopOnclickListener() {
                        @Override
                        public void onStopClick() {
                            operateDialog = new WorkareaOperateDialog(mContext);
                            operateDialog.setPatInfo(orderDialog.getPatInfo());
                            operateDialog.setChildOrders(orderDialog.getChildOrders());
                            operateDialog.setOrderInfoEx(orderDialog.getOrderInfoEx());
                            operateDialog.setSpeedUnit(scanResultBean.getFlowSpeedUnit() + "");
                            operateDialog.setSpeed(scanResultBean.getFlowSpeed() + "");
                            operateDialog.setRemainder(scanResultBean.getRemainder());
                            ordSpeed = scanResultBean.getFlowSpeed() + "";
                            List ls = new ArrayList<String>();
                            if (scanResultBean.getSpeedUnitList() != null) {
                                for (int i = 0; i < scanResultBean.getSpeedUnitList().size(); i++) {
                                    ls.add(scanResultBean.getSpeedUnitList().get(i).getUnitDesc());
                                }
                            }
                            operateDialog.setSpiList(ls);

                            List lsReason = new ArrayList<String>();
                            if (scanResultBean.getStopNoteList() != null) {
                                for (int i = 0; i < scanResultBean.getStopNoteList().size(); i++) {
                                    lsReason.add(scanResultBean.getStopNoteList().get(i).getNoteData());
                                }
                            }
                            operateDialog.setSpiReList(lsReason);
                            operateDialog.setIfImgReasonShow(View.VISIBLE);
                            operateDialog.setViewVisibility(View.VISIBLE, View.VISIBLE, View.VISIBLE, true);
                            operateDialog.setOrderId(orderDialog.getOrderId());
                            operateDialog.setIfState(orderDialog.getIfState());
                            operateDialog.setSureOnclickListener(new WorkareaOperateDialog.onSureOnclickListener() {
                                @Override
                                public void onSureClick() {
                                    ordSpeed = operateDialog.getSpeed() + " " + operateDialog.getSpeedUnit();
                                    stopOrd(operateDialog.getOrderId(), operateDialog.getIfState(), operateDialog.getRemainingliquid(), operateDialog.getRemarkinfo());
                                    operateDialog.dismiss();
                                }
                            });
                            operateDialog.setCancelOnclickListener(new WorkareaOperateDialog.onCancelOnclickListener() {
                                @Override
                                public void onCancelClick() {
                                    operateDialog.dismiss();
                                }
                            });
                            operateDialog.setJp(orderDialog.getJp());
                            operateDialog.show();
                            orderDialog.dismiss();
                        }
                    });

                    orderDialog.setEndOnclickListener(new WorkareaOrderDialog.onEndOnclickListener() {
                        @Override
                        public void onEndClick() {
                            operateDialog = new WorkareaOperateDialog(mContext);
                            operateDialog.setPatInfo(orderDialog.getPatInfo());
                            operateDialog.setChildOrders(orderDialog.getChildOrders());
                            operateDialog.setOrderInfoEx(orderDialog.getOrderInfoEx());
                            operateDialog.setIfImgReasonShow(View.GONE);
                            operateDialog.setViewVisibility(View.GONE, View.VISIBLE, View.VISIBLE, true);
                            operateDialog.setSpeedUnit(scanResultBean.getFlowSpeedUnit() + "");
                            operateDialog.setSpeed(scanResultBean.getFlowSpeed() + "");
                            ordSpeed = scanResultBean.getFlowSpeed() + "";
                            List ls = new ArrayList<String>();
                            if (scanResultBean.getSpeedUnitList() != null) {
                                for (int i = 0; i < scanResultBean.getSpeedUnitList().size(); i++) {
                                    ls.add(scanResultBean.getSpeedUnitList().get(i).getUnitDesc());
                                }
                            }
                            operateDialog.setSpiList(ls);
                            operateDialog.setOrderId(orderDialog.getOrderId());
                            operateDialog.setIfState(orderDialog.getIfState());

                            operateDialog.setSureOnclickListener(new WorkareaOperateDialog.onSureOnclickListener() {
                                @Override
                                public void onSureClick() {
                                    //                                        suspendOrd(operateDialog.getOrderId(), operateDialog.getIfState(), operateDialog.getRemarkinfo());
                                    //                                        operateDialog.dismiss();
                                    ordSpeed = operateDialog.getSpeed() + " " + operateDialog.getSpeedUnit();
                                    reason = operateDialog.getRemarkinfo();
                                    endOrd(operateDialog.getOrderId());
                                    operateDialog.dismiss();
                                }
                            });
                            operateDialog.setCancelOnclickListener(new WorkareaOperateDialog.onCancelOnclickListener() {
                                @Override
                                public void onCancelClick() {
                                    operateDialog.dismiss();
                                }
                            });
                            operateDialog.setJp(orderDialog.getJp());
                            operateDialog.show();
                            orderDialog.dismiss();
                        }
                    });

                    orderDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            episodeId = "";
                            scanInfo = "";
                            scanPat = "";
                            patSaveInfo = "";
                            curOeordId = "";
                            orderDialog = null;
                        }
                    });
                }

                //PAT 扫腕带返回患者信息
                //ORD 扫医嘱条码返回医嘱信息
                if ("PAT".equals(scanResultBean.getFlag())) {
                    scanPat = "1";
                    regNoByPat = scanResultBeanFromWS.getPatInfo().getRegNo();
                    if (orderDialog != null) {
                        orderDialog.setScanPat(scanPat);
                        orderDialog.setPopMsgInfo(scanResultBean.getMsg());
                    }
                    if ("104999".equals(scanResultBean.getMsgcode())) {

                        if (resultDialog != null && resultDialog.isShowing()) {
                            resultDialog.dismiss();
                        }
                        resultDialog = new WorkareaResultDialog(mContext);
                        resultDialog.setExecresult(scanResultBean.getMsg() + "是否继续执行医嘱？");
                        resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                        resultDialog.setSureVisible(View.VISIBLE);
                        resultDialog.setCancleVisible(View.VISIBLE);
                        resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                            @Override
                            public void onSureClick() {
                                resultDialog.dismiss();
                                ScanResultBean.PatInfoBean patInfoBean = scanResultBean.getPatInfo();
                                episodeId = patInfoBean.getEpisodeID();
                                regNo = patInfoBean.getRegNo();

                                patInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge();
                                patSaveInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName();
                                orderDialog.setPatInfo(patInfo);
                                orderDialog.setPopMsgInfo(scanResultBean.getMsg());
                                orderDialog.setJp(View.GONE);
                                if (orderDialog != null && !orderDialog.isShowing()) {
                                    orderDialog.show();
                                }
                            }
                        });
                        resultDialog.setCancelOnclickListener(new WorkareaResultDialog.onCancelOnclickListener() {
                            @Override
                            public void onCancelClick() {
                                resultDialog.dismiss();
                            }
                        });
                        resultDialog.show();

                    } else {
                        ScanResultBean.PatInfoBean patInfoBean = scanResultBean.getPatInfo();
                        episodeId = patInfoBean.getEpisodeID();
                        regNo = patInfoBean.getRegNo();
                        patInfo = "".equals(patInfoBean.getBedCode()) ? "未分床-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge() : patInfoBean.getBedCode().replace("床", "") + "床-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge();
                        patSaveInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName();
                        orderDialog.setPatInfo(patInfo);
                        orderDialog.setJp(View.GONE);
                        if (orderDialog != null && !orderDialog.isShowing()) {
                            orderDialog.show();
                        }
                    }

                } else {
                    barCode = scanInfo;
                    regNoByOrd = scanResultBean.getOrders().get(0).getRegNo();
                    if (resultDialog != null && resultDialog.isShowing()) {
                        resultDialog.dismiss();
                    }

                    if ("1".equals(scanResultBean.getDiagFlag())) {

                        List<ScanResultBean.OrdersBean> ordersBeanList = scanResultBean.getOrders();
                        ScanResultBean.PatInfoBean patInfoBean = scanResultBean.getPatInfo();
                        orderDialog.setPatInfo(patInfoBean.getBedCode() + "-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge());
                        ScanResultBean.OrdersBean ordersBean = ordersBeanList.get(0);
                        curOeordId = ordersBean.getID();
                        orderDialog.setChildOrders(ordersBeanList);
                        orderDialog.setPopMsgInfo(msg);
                        orderDialog.setCanExeFlag(scanResultBean.getCanExeFlag());

                        String orderInfoEx = ordersBean.getSttDateTime() + " " + ordersBean.getPhcinDesc() + " " + ordersBean.getCtcpDesc();
                        if ("Y".equals(ordersBean.getPreparedFlag())) {
                            orderInfoEx = orderInfoEx + "\n" + "配液： " + ordersBean.getPeiYeDate() + " " + ordersBean.getPeiYeTime() + " " + ordersBean.getPeiYeUser();
                        }
                        if ("Y".equals(ordersBean.getVerifyFlag())) {
                            orderInfoEx = orderInfoEx + "\n" + "复核： " + ordersBean.getFuHeDate() + " " + ordersBean.getFuHeTime() + " " + ordersBean.getFuHeUser();
                        }
                        if (ordersBean.getExecDateTime() != null && !ordersBean.getExecDateTime().equals("") && ordersBean.getExecCtcpDesc() != null && !ordersBean.getExecCtcpDesc().equals("")) {
                            orderInfoEx = orderInfoEx + "\n" + "执行： " + ordersBean.getExecDateTime() + " " + ordersBean.getExecCtcpDesc();
                        }
                        orderDialog.setOrderInfoEx(orderInfoEx);

                        //功能区扫码 附加数据
                        orderDialog.setSttDateTime(ordersBean.getSttDateTime());
                        orderDialog.setArcimDesc(ordersBean.getArcimDesc());
                        orderDialog.setOrderId(ordersBean.getID());
                        orderDialog.setBedCode(ordersBean.getBedCode());
                        orderDialog.setSpeedUnit(scanResultBean.getFlowSpeedUnit() + "");
                        orderDialog.setSpeed(scanResultBean.getFlowSpeed() + "");
                        //执行添加设备
                        if ("exe".equals(scanResultBean.getBtnType())) {
                            if (!TextUtils.isEmpty(scanResultBean.getDevicNo())) {
                                orderDialog.setDevicNo(scanResultBean.getDevicNo() + "");
                            } else {
                                ToastUtils.showShort("请扫码绑定监护仪设备!");
                            }
                        }
                        orderDialog.setRemainder(scanResultBean.getRemainder() + "");
                        ordSpeed = scanResultBean.getFlowSpeed() + "";
                        List ls = new ArrayList<String>();
                        if (scanResultBean.getSpeedUnitList() != null) {
                            for (int i = 0; i < scanResultBean.getSpeedUnitList().size(); i++) {
                                ls.add(scanResultBean.getSpeedUnitList().get(i).getUnitDesc());
                            }
                        }
                        orderDialog.setSpiList(ls);
                        orderDialog.setWayNoList(scanResultBean.getWayNoList());
                        orderDialog.setBtnExecText(scanResultBean.getBtnDesc());
                        orderDialog.setBtnType(scanResultBean.getBtnType());
                        orderDialog.setIfState(scanResultBean.getIfState());

                        if (ordersBean.getFilteFlagExtend() != null && ordersBean.getFilteFlagExtend().equals("JP")) {
                            orderDialog.setJp(View.VISIBLE);
                        }
                        if (orderDialog != null && !orderDialog.isShowing()) {
                            orderDialog.show();
                        }

                    } else {
                        resultDialog = new WorkareaResultDialog(mContext);
                        resultDialog.setExecresult("扫码执行成功");
                        resultDialog.setImgId(R.drawable.icon_popup_sucess);
                        resultDialog.setSureVisible(View.GONE);
                        resultDialog.setCancleVisible(View.GONE);
                        resultDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                resultDialog.dismiss();
                            }
                        }, 1000);
                    }


                }
            }

            @Override
            public void onFail(String code, String msg) {

                playSound(1, 0);
                if (orderDialog != null && orderDialog.isShowing()) {
                    orderDialog.dismiss();
                }
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                resultDialog = new WorkareaResultDialog(mContext);
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });
        if (resultDialog != null && resultDialog.isShowing()) {
            resultDialog.dismiss();
        }
    }

    /**
     * 扫码执行
     */
    private void execOrSeeOrderScan(String execpatInfo, String creattime, String order, String oeoreIdScan, String execStatusCodeScan, String bedCode) {
        if (StringUtils.isEmpty(execpatInfo)) {
            if (resultDialog != null && resultDialog.isShowing()) {
                resultDialog.dismiss();
            }
            playSound(1, 0);
            resultDialog = new WorkareaResultDialog(mContext);
            resultDialog.setExecresult("请扫描患者腕带");
            resultDialog.setImgId(R.drawable.icon_popup_error_patient);
            resultDialog.setSureVisible(View.VISIBLE);
            resultDialog.setCancleVisible(View.GONE);
            resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                @Override
                public void onSureClick() {
                    resultDialog.dismiss();
                }
            });
            resultDialog.show();
            return;
        } else if (!(regNoByOrd.replaceAll(" ", "")).equals((regNoByPat.replaceAll(" ", "")))) {
            regNoByOrd = "";
            regNoByPat = "";
            if (resultDialog != null && resultDialog.isShowing()) {
                resultDialog.dismiss();
            }
            playSound(1, 0);
            resultDialog = new WorkareaResultDialog(mContext);
            resultDialog.setExecresult("非此患者医嘱，请检查患者与医嘱对应关系");
            resultDialog.setImgId(R.drawable.icon_popup_error_patient);
            resultDialog.setSureVisible(View.VISIBLE);
            resultDialog.setCancleVisible(View.GONE);
            resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                @Override
                public void onSureClick() {
                    resultDialog.dismiss();
                }
            });
            resultDialog.show();
            return;
        }

        OrderExecuteApiManager.execOrSeeOrder(ordSpeed, barCode, creattime, order, execpatInfo, "1", "", "", "", oeoreIdScan, execStatusCodeScan, wayNo, deviceNo, new OrderExecuteApiManager.ExecOrSeeOrderCallback() {
            @Override
            public void onSuccess(OrderExecResultBean orderExecResultBean) {
                barCode = "";
                regNoByOrd = "";
                regNoByPat = "";
                wayNo = "";

                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }

                resultDialog = new WorkareaResultDialog(mContext);
                resultDialog.setExecresult("扫码执行成功");
                resultDialog.setImgId(R.drawable.icon_popup_sucess);
                resultDialog.setSureVisible(View.GONE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                regNoByOrd = "";
                regNoByPat = "";
                playSound(1, 0);
                resultDialog = new WorkareaResultDialog(mContext);
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });
    }

    private void preparedVerifyOrd(String orderId) {
        WorkareaApiManager.preparedVerifyOrd(orderId, "1", new WorkareaApiManager.PrepareVeriftyCallback() {
            @Override
            public void onSuccess(PreparedVerifyOrdBean preparedVerifyOrdBean) {

                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                resultDialog = new WorkareaResultDialog(mContext);
                resultDialog.setExecresult(preparedVerifyOrdBean.getMsg());
                resultDialog.setImgId(R.drawable.icon_popup_sucess);
                resultDialog.setSureVisible(View.GONE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                resultDialog = new WorkareaResultDialog(mContext);
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });
    }

    private void tourOrd(String orderId) {
        WorkareaApiManager.tourOrd(ordSpeed, reason, orderId, new WorkareaApiManager.OperateResultCallBack() {
            @Override
            public void onSuccess(OperateResultBean operateResultBean) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }

                resultDialog = new WorkareaResultDialog(mContext);
                resultDialog.setExecresult("巡视成功");
                resultDialog.setImgId(R.drawable.icon_popup_sucess);
                resultDialog.setSureVisible(View.GONE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                playSound(1, 0);
                resultDialog = new WorkareaResultDialog(mContext);
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });
    }

    private void continueOrd(String orderId) {
        WorkareaApiManager.continueOrd(ordSpeed, orderId, new WorkareaApiManager.OperateResultCallBack() {
            @Override
            public void onSuccess(OperateResultBean operateResultBean) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }

                resultDialog = new WorkareaResultDialog(mContext);
                resultDialog.setExecresult("继续成功");
                resultDialog.setImgId(R.drawable.icon_popup_sucess);
                resultDialog.setSureVisible(View.GONE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                playSound(1, 0);
                resultDialog = new WorkareaResultDialog(mContext);
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });
    }

    private void suspendOrd(String orderId, String infusionState, String infusionReason) {
        WorkareaApiManager.suspendOrd(ordSpeed, orderId, infusionState, infusionReason, new WorkareaApiManager.OperateResultCallBack() {
            @Override
            public void onSuccess(OperateResultBean operateResultBean) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }

                resultDialog = new WorkareaResultDialog(mContext);
                resultDialog.setExecresult("暂停成功");
                resultDialog.setImgId(R.drawable.icon_popup_sucess);
                resultDialog.setSureVisible(View.GONE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                playSound(1, 0);
                resultDialog = new WorkareaResultDialog(mContext);
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });
    }

    private void stopOrd(String orderId, String infusionState, String ResidualQty, String infusionReason) {
        WorkareaApiManager.stopOrd(ordSpeed, orderId, infusionState, ResidualQty, infusionReason, new WorkareaApiManager.OperateResultCallBack() {
            @Override
            public void onSuccess(OperateResultBean operateResultBean) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }

                resultDialog = new WorkareaResultDialog(mContext);
                resultDialog.setExecresult("停止成功");
                resultDialog.setImgId(R.drawable.icon_popup_sucess);
                resultDialog.setSureVisible(View.GONE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                playSound(1, 0);
                resultDialog = new WorkareaResultDialog(mContext);
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });
    }

    private void endOrd(String orderId) {
        WorkareaApiManager.endOrd(ordSpeed, reason, orderId, new WorkareaApiManager.OperateResultCallBack() {
            @Override
            public void onSuccess(OperateResultBean operateResultBean) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }

                resultDialog = new WorkareaResultDialog(mContext);
                resultDialog.setExecresult("结束成功");
                resultDialog.setImgId(R.drawable.icon_popup_sucess);
                resultDialog.setSureVisible(View.GONE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                playSound(1, 0);
                resultDialog = new WorkareaResultDialog(mContext);
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new WorkareaResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });
    }

    public void playSound(int sound, int loop) {
        if (mContext == null) {
            return;
        }
        AudioManager mgr = (AudioManager) mContext.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);

        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        float volume = streamVolumeCurrent / streamVolumeMax;

        soundPool.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);

        //参数：1、Map中取值   2、当前音量     3、最大音量  4、优先级   5、重播次数   6、播放速度

    }
}
