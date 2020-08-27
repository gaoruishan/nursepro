package com.dhcc.module.nurse;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.MessageEvent;
import com.base.commlibs.bean.PatientListBean;
import com.base.commlibs.comm.BaseCommFragment;
import com.base.commlibs.http.CommHttp;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.BasePopWindow;
import com.base.commlibs.utils.CommDialog;
import com.base.commlibs.utils.SimpleCallBack;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.task.FileterPop;
import com.dhcc.module.nurse.task.adapter.StatusFilterAdapter;
import com.dhcc.module.nurse.task.adapter.TimeFilterAdapter;
import com.dhcc.module.nurse.task.bean.StatusListBean;
import com.dhcc.module.nurse.task.bean.TimesListBean;
import com.dhcc.res.infusion.CustomSelectView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;

/**
 * 智护相关一个父类
 * @author:gaoruishan
 * @date:202020-07-17/17:08
 * @email:grs0515@163.com
 */
public abstract class BaseNurseFragment extends BaseCommFragment {

    public static final String SELECT_ORD = "selectOrd";

    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * 传递数据
     */
    public String desc;
    public String subjectIds;
    public String eduDateTime;
    public String episodeId;
    public String taskIds;
    public String eduRecordId;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    // 将sp值转换为px值，保证文字大小不变
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {
        setCommToolBar2();
    }

    @Override
    public void setToolbarCenterTitle(CharSequence title) {
        //改为白色
        super.setToolbarCenterTitle(title, 0xffffffff, 17);
    }
    /**
     * task添加时间筛选框
     */
    private TimeFilterAdapter timeFilterAdapter;
    protected View contentView;
    private RecyclerView recStatus;
    private Boolean bStatus = false,bTime = true;
    StatusFilterAdapter statusFilterAdapter;
    private Boolean bCheckbox = false;

    public void setbCheckbox(Boolean bCheckbox) {
        this.bCheckbox = bCheckbox;
        if (bCheckbox){
            recStatus.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            if (statusFilterAdapter!=null){
                statusFilterAdapter.setbCheckbox(true);
                statusFilterAdapter.setSelectItem(-1);
                statusFilterAdapter.notifyDataSetChanged();
            }
        }
    }

    public void setbStatus(Boolean bStatus) {
        this.bStatus = bStatus;
        if (contentView!=null){
            contentView.findViewById(R.id.ll_statusfilter).setVisibility(bStatus?View.VISIBLE:View.GONE);
        }
    }

    public void setbTime(Boolean bTime) {
        this.bTime = bTime;
        if (contentView!=null){
            contentView.findViewById(R.id.ll_time_filter).setVisibility(bTime?View.VISIBLE:View.GONE);
        }
    }

    public void setTimeListData(List<TimesListBean> listData){
        timeFilterAdapter.setNewData(listData);
    }
    public void setStatusListData(List<StatusListBean> listData){
        if (bCheckbox){
            if (statusFilterAdapter!=null && listData.size()>0){
                statusFilterAdapter.setSelectItem(-1);
                statusFilterAdapter.notifyDataSetChanged();
            }
        }else if (listData.size()>0){
            statusFilterAdapter.setSelectItem(0);
        }
        statusFilterAdapter.setNewData(listData);
    }

    public void addToolBarRightPopWindow() {
        contentView = LayoutInflater.from(mContext).inflate(R.layout.dhcc_task_filter_layout, null);
        contentView.findViewById(R.id.tv_filter_clear_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeFilterAdapter.setSelectItem(-1);
                timeFilterAdapter.notifyDataSetChanged();
                statusFilterAdapter.setSelectItem(0);
                if (bCheckbox){
                    for (int i = 0; i < statusFilterAdapter.getData().size(); i++) {
                        statusFilterAdapter.getData().get(i).setSelect(false);
                    }
                }
                statusFilterAdapter.notifyDataSetChanged();
            }
        });
        contentView.findViewById(R.id.iv_finish_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMaskHind();
            }
        });
        contentView.findViewById(R.id.tv_filter_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMaskHind();
                if (timeFilterAdapter.getSelectItem()!=-1){
                    onPopClicListner.sure(timeFilterAdapter.getData().get(timeFilterAdapter.getSelectItem()).getTimeStt(),timeFilterAdapter.getData().get(timeFilterAdapter.getSelectItem()).getTimeEnd());
                }
                if (statusFilterAdapter.getSelectItem()!=-1){
                    onPopClicListner.statusSure(statusFilterAdapter.getData().get(statusFilterAdapter.getSelectItem()).getValue());
                }
                if (statusFilterAdapter.getbCheckbox()){
                    String codes = "";
                    for (int i = 0; i < statusFilterAdapter.getData().size(); i++) {
                        if (statusFilterAdapter.getData().get(i).getSelect()){
                            codes = codes+";"+statusFilterAdapter.getData().get(i).getText();
                        }
                    }
                    onPopClicListner.statusSure(codes);
                }

            }
        });

        RecyclerView recTime = contentView.findViewById(R.id.rec_task_timefilter);
        timeFilterAdapter = AdapterFactory.getTimeFilterAdapter();
        recTime.setAdapter(timeFilterAdapter);
        recTime.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        timeFilterAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                timeFilterAdapter.setSelectItem(position);
                timeFilterAdapter.notifyDataSetChanged();
            }
        });

        recStatus = contentView.findViewById(R.id.rec_task_statusfilter);
        statusFilterAdapter = AdapterFactory.getStatusFilterAdapter();
        recStatus.setAdapter(statusFilterAdapter);
        recStatus.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        statusFilterAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (bCheckbox){
                    if (statusFilterAdapter.getItem(position).getSelect()){
                        statusFilterAdapter.getItem(position).setSelect(false);
                    }else {
                        statusFilterAdapter.getItem(position).setSelect(true);
                    }
                    statusFilterAdapter.notifyDataSetChanged();
                }else {
                    statusFilterAdapter.setSelectItem(position);
                    statusFilterAdapter.notifyDataSetChanged();
                }
            }
        });

        contentView.findViewById(R.id.ll_statusfilter).setVisibility(bStatus?View.VISIBLE:View.GONE);
        contentView.findViewById(R.id.ll_time_filter).setVisibility(bTime?View.VISIBLE:View.GONE);

    }
    public void setMaskShow(){
        setMaskShow(contentView);
    }

    public void setMaskShow(View contentView){
        FileterPop.setMask(mContext, View.VISIBLE);
        FileterPop.initPopupWindow(mContext, BasePopWindow.EnumLocation.RIGHT, contentView);
    }

    /**
     * 指定弹框宽度
     * @param contentView
     * @param screenWidth
     */
    public void setMaskShow(View contentView,double screenWidth){
        FileterPop.setMask(mContext, View.VISIBLE);
        FileterPop.initPopupWindow(mContext, BasePopWindow.EnumLocation.RIGHT, contentView,screenWidth);
    }

    public void setMaskHind(){
        FileterPop.setMask(mContext, View.INVISIBLE);
        FileterPop.closePopWindow();
    }
    private OnPopClicListner onPopClicListner;
    public void setOnPopClicListner(OnPopClicListner onPopClicListner){
        this.onPopClicListner = onPopClicListner;
    }
    public interface OnPopClicListner{
        void sure(String sttDt,String endDt);
        void statusSure(String code);
    }

    /**
     * 获取筛选View布局
     * @param clearListener
     * @param okListener
     * @return
     */
    public View getPopWindowView(@NotNull View.OnClickListener clearListener, @NotNull View.OnClickListener okListener) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.dhcc_task_filter_layout, null);
        contentView.findViewById(R.id.tv_filter_clear_select).setOnClickListener(clearListener);
        contentView.findViewById(R.id.iv_finish_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMaskHind();
            }
        });
        contentView.findViewById(R.id.tv_filter_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMaskHind();
                if (okListener != null) {
                    okListener.onClick(v);
                }
            }
        });
        return contentView;
    }
    /**
     * 获取sp存储数据
     * @param s
     */
    public String getSpInfo(String s){
        return SPUtils.getInstance().getString(s);
    }


    /**
     * 添加两个图片
     * @param iv1
     * @param iv2
     */
    public void addToolBarRightImageView(@DrawableRes int iv1, @DrawableRes int iv2) {
        View viewright = View.inflate(getActivity(), R.layout.dhcc_view_toolbar_img_right, null);
        ImageView ivBed = viewright.findViewById(R.id.img_toolbar_right1);
        ivBed.setVisibility(iv1 != 0 ? View.VISIBLE : View.GONE);
        if (iv1 != 0) {
            ivBed.setImageResource(iv1);
        }
        ImageView ivAdd = viewright.findViewById(R.id.img_toolbar_right2);
        ivAdd.setVisibility(iv2 != 0 ? View.VISIBLE : View.GONE);
        if (iv2 != 0) {
            ivAdd.setImageResource(iv2);
        }
        ivAdd.setOnClickListener(this);
        ivBed.setOnClickListener(this);
        setToolbarRightCustomView(viewright);
    }

    /**
     * 添加一个文本
     * @param txt
     * @param color
     */
    public void addToolBarRightTextView(String txt, @ColorRes int color) {
        //右上角保存按钮
        View viewright = View.inflate(getActivity(), R.layout.dhcc_view_toolbar_tv_right, null);
        TextView textView = viewright.findViewById(R.id.tv_toolbar_right);
        textView.setTextSize(15);
        textView.setText("   " + txt + "   ");
        textView.setTextColor(getResources().getColor(color));
        textView.setOnClickListener(this);
        setToolbarRightCustomView(viewright);
    }

    /**
     * 括号
     * @param s
     * @return
     */
    public String replaceBrackets(String s) {
        return  s.replace("[", "")
                .replace("]", "");
    }

    /**
     * 替换\r 为\n
     * @param s
     * @return
     */
    public String replaceRN(String s) {
        return s.replaceAll("\r", "\n");
    }

    protected void setSelectDateTime(CustomSelectView customSelectDate, String time) {
        customSelectDate.setTitle("时间选择").setSelect(time);
        long millis = TimeUtils.string2Millis(time, YYYY_MM_DD_HH_MM);
        customSelectDate.setSelectTime(this.getFragmentManager(), millis, null);
    }

    /**
     * 接收事件- 更新数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateMessageEvent(MessageEvent event) {
        Log.e(getClass().getSimpleName(), "updateText:" + event.toString());
    }

    public void getInWardPatList(SimpleCallBack<PatientListBean> callBack) {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        CommHttp.getInWardPatList(new CommonCallBack<PatientListBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                onFailThings();
            }

            @Override
            public void onSuccess(PatientListBean bean, String type) {
                hideLoadingTip();
                //获取第一个
                if (bean.getPatInfoList() != null) {
                    //默认第一个
                    episodeId = bean.getPatInfoList().get(0).getEpisodeId();
                    if (callBack != null) {
                        callBack.call(bean,0);
                    }
                }
            }
        });
    }

    public void finishDelayed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, CommDialog.DELAY_MILLIS);
    }
}
