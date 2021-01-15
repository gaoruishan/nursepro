package com.dhcc.nursepro.workarea.pathandover.dialog;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.base.commlibs.utils.BaseHelper;
import com.base.commlibs.utils.BasePopWindow;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.PopWindowUtil;
import com.dhcc.nursepro.workarea.pathandover.adapter.PatHandoverFilterAdapter;

import java.util.ArrayList;
import java.util.List;

public class PatHandoverFilterPresenter extends BaseHelper {

    private View contentView;
    private List<String> typeList;
    private OnSelectCallBackListener onSelectCallBackListener;
    private PatHandoverFilterAdapter filterAdapter;
    private RecyclerView recyFilterContent;
    private String typeCode = "";

    /**
     * 获取宿主Activity
     *
     * @param activity
     * @return
     */
    public PatHandoverFilterPresenter(Activity activity) {
        super(activity);
    }

    public void setOnSelectCallBackListener(OnSelectCallBackListener onSelectCallBackListener) {
        this.onSelectCallBackListener = onSelectCallBackListener;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
        if (filterAdapter != null) {
            filterAdapter.setNewData(typeList);
        }
    }

    @Override
    protected void initView(Activity mContext) {
        super.initView(mContext);
        contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_pathandover_typefilter, null);

        recyFilterContent = contentView.findViewById(R.id.recy_filter_content);

        RecyclerViewHelper.setGridRecyclerView(mContext, recyFilterContent, 3, 0, false);
        filterAdapter = new PatHandoverFilterAdapter(new ArrayList<>());
        filterAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.dhcc_tv) {
                    typeCode = adapter.getItem(position).toString().split(":")[1];
                    filterAdapter.setSelectPosition(position);
                    filterAdapter.notifyDataSetChanged();
                }
            }
        });
        recyFilterContent.setAdapter(filterAdapter);

        View ivFinishFilter = contentView.findViewById(R.id.iv_finish_filter);
        ivFinishFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopWindowUtil.closePopWindow();
            }
        });
        View tvFilterClearSelect = contentView.findViewById(R.id.tv_filter_clear_select);
        tvFilterClearSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeCode = "";
                filterAdapter.setSelectPosition(-1);
                filterAdapter.notifyDataSetChanged();
            }
        });
        View tvFilterOk = contentView.findViewById(R.id.tv_filter_ok);
        tvFilterOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(typeCode)) {
                    Toast.makeText(mContext, "未选择筛选类型", Toast.LENGTH_SHORT).show();
                }
                PopWindowUtil.closePopWindow();
                if (onSelectCallBackListener != null) {
                    onSelectCallBackListener.onSelect(typeCode);
                }
            }
        });

    }

    /**
     * 打开PopWindow
     */
    public void openPopWindow() {
        PopWindowUtil.setMask(mContext, View.VISIBLE);
        PopWindowUtil.initPopupWindow(mContext, BasePopWindow.EnumLocation.RIGHT, contentView);
    }

    public interface OnSelectCallBackListener {
        void onSelect(String s);
    }


}

