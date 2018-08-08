package com.dhcc.nursepro.WsTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.WsTest.adapter.LeftAdapter;
import com.dhcc.nursepro.WsTest.adapter.RightAdapter;
import com.dhcc.nursepro.WsTest.bean.LeftItemBean;
import com.dhcc.nursepro.WsTest.bean.RightItemBean;

import java.util.ArrayList;
import java.util.List;

public class ClassificationActivity extends AppCompatActivity {

    private RecyclerView classificationRecyleft;
    private RecyclerView classificationRecyright;

    private List<LeftItemBean> leftList;
    private List<RightItemBean> rightList;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification);

        initView();
        initAdapter();
        initData();
    }

    private void initView() {
        classificationRecyleft = findViewById(R.id.classification_recyleft);
        classificationRecyright = findViewById(R.id.classification_recyright);
        //提高展示效率
        classificationRecyleft.setHasFixedSize(true);
        //设置的布局管理
        classificationRecyleft.setLayoutManager(new LinearLayoutManager(this));

        //提高展示效率
        classificationRecyright.setHasFixedSize(true);
        //设置的布局管理
        classificationRecyright.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initAdapter() {
        leftAdapter = new LeftAdapter(new ArrayList<LeftItemBean>());
        leftAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.itemleft_textview) {
                    leftAdapter.setSelectedPostion(position);
                    leftAdapter.notifyDataSetChanged();
                    switch (position) {
                        case 0:
                            rightAdapter.setNewData(rightList);
                            break;
                        case 1:
                            List<RightItemBean> displayList1 = new ArrayList<>();
                            displayList1.add(rightList.get(0));
                            displayList1.add(rightList.get(1));
                            displayList1.add(rightList.get(2));
                            rightAdapter.setNewData(displayList1);

                            break;
                        case 2:
                            List<RightItemBean> displayList2 = new ArrayList<>();
                            displayList2.add(rightList.get(2));
                            displayList2.add(rightList.get(4));
                            displayList2.add(rightList.get(6));
                            rightAdapter.setNewData(displayList2);
                            break;
                        case 3:
                            List<RightItemBean> displayList3 = new ArrayList<>();
                            displayList3.add(rightList.get(7));
                            displayList3.add(rightList.get(8));
                            displayList3.add(rightList.get(9));
                            rightAdapter.setNewData(displayList3);
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        classificationRecyleft.setAdapter(leftAdapter);

        rightAdapter = new RightAdapter(new ArrayList<RightItemBean>());
        rightAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RightItemBean rightItemBean = (RightItemBean) adapter.getItem(position);
                if (view.getId() == R.id.itemright_textview) {
                    Toast.makeText(ClassificationActivity.this, rightItemBean != null ? rightItemBean.getDetail() : "null", Toast.LENGTH_SHORT).show();
                }
            }
        });

        classificationRecyright.setAdapter(rightAdapter);
    }

    private void initData() {
        leftList = new ArrayList<>();
        rightList = new ArrayList<>();

        leftList.add(new LeftItemBean("全部"));
        leftList.add(new LeftItemBean("新入院"));
        leftList.add(new LeftItemBean("手术"));
        leftList.add(new LeftItemBean("发热"));

        leftAdapter.setNewData(leftList);

        rightList.add(new RightItemBean("患者1"));
        rightList.add(new RightItemBean("患者2"));
        rightList.add(new RightItemBean("患者3"));
        rightList.add(new RightItemBean("患者4"));
        rightList.add(new RightItemBean("患者5"));
        rightList.add(new RightItemBean("患者6"));
        rightList.add(new RightItemBean("患者7"));
        rightList.add(new RightItemBean("患者8"));
        rightList.add(new RightItemBean("患者9"));
        rightList.add(new RightItemBean("患者10"));

        rightAdapter.setNewData(rightList);


    }
}
