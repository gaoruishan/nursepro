package com.dhcc.nursepro.WsTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dhcc.nursepro.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * SectionActivity
 * 分段列表显示
 *
 * @author DevLix126
 * @date 2018/8/8
 */
public class SectionActivity extends AppCompatActivity {

    private RecyclerView sectionRecyleft;
    private RecyclerView sectionRecyright;

    //特定adapter
    private SectionedRecyclerViewAdapter sectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        initView();
        initAdapter();
        initData();
    }

    private void initView() {
        sectionRecyleft = findViewById(R.id.section_recyleft);
        //提高展示效率
        sectionRecyleft.setHasFixedSize(true);
        //设置的布局管理
        sectionRecyleft.setLayoutManager(new LinearLayoutManager(this));
        sectionRecyright = findViewById(R.id.section_recyright);
        //提高展示效率
        sectionRecyright.setHasFixedSize(true);
        //设置的布局管理
        sectionRecyright.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initAdapter() {
        sectionAdapter = new SectionedRecyclerViewAdapter();

        //以NewsSection为item
        sectionAdapter.addSection(new NewsSection(NewsSection.PATIENT));
        sectionAdapter.addSection(new NewsSection(NewsSection.RIORITY));
        sectionAdapter.addSection(new NewsSection(NewsSection.STATUS));
        sectionAdapter.addSection(new NewsSection(NewsSection.CATEGORY));
        sectionRecyleft.setAdapter(sectionAdapter);
    }

    private void initData() {

    }

    /**
     * list item
     * <p>
     * header
     * item
     * footer
     */

    public class NewsSection extends StatelessSection {

        final static int PATIENT = 0;
        final static int RIORITY = 1;
        final static int STATUS = 2;
        final static int CATEGORY = 3;

        final int topic;

        String title;
        List<String> list;

        NewsSection(int topic) {
            super(SectionParameters.builder()
                    .itemResourceId(R.layout.item_section)
                    .headerResourceId(R.layout.item_section_header)
                    //                    .footerResourceId(R.layout.section_ex2_footer)
                    .build());

            this.topic = topic;

            switch (topic) {
                case PATIENT:
                    this.title = "病人列表";
                    this.list = getNews(R.array.patient_list);
                    break;
                case RIORITY:
                    this.title = "医嘱优先级";
                    this.list = getNews(R.array.medical_order_priority);
                    break;
                case STATUS:
                    this.title = "医嘱状态";
                    this.list = getNews(R.array.medical_order_status);
                    break;
                case CATEGORY:
                    this.title = "医嘱类别";
                    this.list = getNews(R.array.medical_order_category);
                    break;
                default:
                    break;
            }
        }

        private List<String> getNews(int arrayResource) {
            return new ArrayList<>(Arrays.asList(getResources().getStringArray(arrayResource)));
        }

        @Override
        public int getContentItemsTotal() {
            return list.size();
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;


            final String content = list.get(position);
            itemHolder.tvSection.setText(content);

            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //                    Toast.makeText(SectionActivity.this,
                    //                            String.format("Clicked on position #%s of Section %s",
                    //                                    sectionAdapter.getPositionInSection(itemHolder.getAdapterPosition()),
                    //                                    title),
                    //                            Toast.LENGTH_SHORT).show();
                    Toast.makeText(SectionActivity.this, title + "---" + content, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvSectionTitle.setText(title);
        }

        //        @Override
        //        public RecyclerView.ViewHolder getFooterViewHolder(View view) {
        //            return new FooterViewHolder(view);
        //        }

        //        @Override
        //        public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
        //            FooterViewHolder footerHolder = (FooterViewHolder) holder;
        //
        //            footerHolder.rootView.setOnClickListener(new View.OnClickListener() {
        //                @Override
        //                public void onClick(View v) {
        //                    Toast.makeText(getContext(), String.format("Clicked on footer of Section %s", title), Toast.LENGTH_SHORT).show();
        //                }
        //            });
        //        }


    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvSectionTitle;

        HeaderViewHolder(View view) {
            super(view);

            tvSectionTitle = (TextView) view.findViewById(R.id.itemsectionheader_title);
        }
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final TextView tvSection;

        ItemViewHolder(View view) {
            super(view);

            rootView = view;
            tvSection = (TextView) view.findViewById(R.id.itemsection_textview);
        }
    }
}
