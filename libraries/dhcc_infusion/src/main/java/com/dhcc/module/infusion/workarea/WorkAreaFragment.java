package com.dhcc.module.infusion.workarea;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.continues.ContinueFragment;
import com.dhcc.module.infusion.workarea.dosing.DosingFragment;
import com.dhcc.module.infusion.workarea.needles.NeedlesFragment;
import com.dhcc.module.infusion.workarea.patrol.PatrolFragment;
import com.dhcc.module.infusion.workarea.puncture.PunctureFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 * @author:gaoruishan
 * @date:202019-05-22/15:16
 * @email:grs0515@163.com
 */
public class WorkAreaFragment extends BaseFragment {
    private RecyclerView recConfig;
    private List<String> ItemNameList = new ArrayList<String>();
    private WorkAreaAdapter patEventsAdapter;


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workarea_infusion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);
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
        ItemNameList.add("LIQUIDDISPENDING");
        ItemNameList.add("PUNCTURE");
        ItemNameList.add("PATRAL");
        ItemNameList.add("CONTINUE");
        ItemNameList.add("WITHDRAWALOFNEEDLES");
        patEventsAdapter.setNewData(ItemNameList);
    }

    public void itemClick(String itemName) {
        switch (itemName) {

            case "LIQUIDDISPENDING":// 配液
                startFragment(DosingFragment.class);
                break;
            case "PUNCTURE":// 穿刺
                startFragment(PunctureFragment.class);
                break;
            case "PATRAL":// 巡视
                startFragment(PatrolFragment.class);
                break;
            case "CONTINUE":// 续液
                startFragment(ContinueFragment.class);
                break;
            case "WITHDRAWALOFNEEDLES":// 拔针
                startFragment(NeedlesFragment.class);
                break;
            default:

                break;
        }
    }

    public class WorkAreaAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public WorkAreaAdapter(@Nullable List<String> data) {
            super(R.layout.item_workarea, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView tvItem = helper.getView(R.id.tv_workarea);
            ImageView imageView = helper.getView(R.id.icon_workarea);
            switch (item) {
                /**
                 * 门诊输液
                 */
                case "LIQUIDDISPENDING":
                    tvItem.setText("配液");
                    imageView.setImageResource(R.drawable.infusion_main_py);
                    break;
                case "PUNCTURE":
                    tvItem.setText("穿刺");
                    imageView.setImageResource(R.drawable.infusion_main_cc);
                    break;
                case "PATRAL":
                    tvItem.setText("巡视");

                    imageView.setImageResource(R.drawable.infusion_main_xs);
                    break;
                case "CONTINUE":
                    tvItem.setText("续液");
                    imageView.setImageResource(R.drawable.infusion_main_xy);
                    break;
                case "WITHDRAWALOFNEEDLES":
                    tvItem.setText("拔针");
                    imageView.setImageResource(R.drawable.infusion_main_bz);
                    break;
                default:
                    break;
            }
        }
    }
}
