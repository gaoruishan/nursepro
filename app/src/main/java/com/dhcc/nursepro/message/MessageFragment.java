package com.dhcc.nursepro.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.message.api.MessageApiManager;
import com.dhcc.nursepro.message.bean.MessageBean;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class MessageFragment extends BaseFragment {
    private List<MessageBean.AbnormalPatListBean> abnormalPatList;
    private List<MessageBean.ConPatListBean> conPatList;
    private List<MessageBean.NewOrdPatListBean> newOrdPatList;
    private RecyclerView messageRecy;
    private SectionedRecyclerViewAdapter sectionAdapter;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        hideToolbarNavigationIcon();


        initView(view);
        initAdapter();
        initData();

    }

    private void initView(View view) {
        messageRecy = view.findViewById(R.id.message_recy);

        //提高展示效率
        messageRecy.setHasFixedSize(true);
        //设置的布局管理
        messageRecy.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


    private void initAdapter() {
        sectionAdapter = new SectionedRecyclerViewAdapter();


        messageRecy.setAdapter(sectionAdapter);
    }

    private void initData() {
        MessageApiManager.getMessage(new MessageApiManager.GetMessageCallback() {
            @Override
            public void onSuccess(MessageBean msgs) {
                newOrdPatList = msgs.getNewOrdPatList();
                abnormalPatList = msgs.getAbnormalPatList();
                conPatList = msgs.getConPatList();
                //以NewsSection为item
                sectionAdapter.addSection(new NewsSection(NewsSection.NEWORDER));
                sectionAdapter.addSection(new NewsSection(NewsSection.ABNORMAL));
                sectionAdapter.addSection(new NewsSection(NewsSection.CONSULTATION));
                sectionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String code, String msg) {
                Toast.makeText(getActivity(), code + ":" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * list item
     * <p>
     * header
     * item
     * footer
     */

    public class NewsSection extends StatelessSection {

        final static int NEWORDER = 0;
        final static int ABNORMAL = 1;
        final static int CONSULTATION = 2;

        final int topic;

        String title;
        List<MessageBean.NewOrdPatListBean> list1;
        List<MessageBean.AbnormalPatListBean> list2;
        List<MessageBean.ConPatListBean> list3;

        NewsSection(int topic) {
            super(SectionParameters.builder()
                    .itemResourceId(R.layout.item_message)
                    .headerResourceId(R.layout.item_message_header)
                    .build());

            this.topic = topic;

            switch (topic) {
                case NEWORDER:
                    this.title = "新医嘱";
                    this.list1 = newOrdPatList;
                    break;
                case ABNORMAL:
                    this.title = "危急值";
                    this.list2 = abnormalPatList;
                    break;
                case CONSULTATION:
                    this.title = "会诊";
                    this.list3 = conPatList;
                    break;
                default:
                    break;
            }
        }

        //        private List<String> getNews(int arrayResource) {
        //            return new ArrayList<>(Arrays.asList(getResources().getStringArray(arrayResource)));
        //        }

        @Override
        public int getContentItemsTotal() {
            if (topic == 0) {
                return list1.size();
            } else if (topic == 1) {
                return list2.size();
            } else {
                return list3.size();
            }
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            ItemViewHolder itemHolder = (ItemViewHolder) holder;

            if (topic == 0) {
                String patientName = list1.get(position).getPatName();
                String bedNo = list1.get(position).getBedCode();
                String patientSex = list1.get(position).getSex();

                itemHolder.tvMessageBedNo.setText(bedNo + "床");
                itemHolder.tvMessagePatientName.setText(patientName);
                if (patientSex.equals("M")) {
                    itemHolder.imgMessagePatientSex.setImageResource(R.drawable.sex_male);
                } else {
                    itemHolder.imgMessagePatientSex.setImageResource(R.drawable.sex_female);

                }
                itemHolder.tvMessageDoctorName.setVisibility(View.GONE);
                itemHolder.tvPatientLoc.setVisibility(View.GONE);

                itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(getActivity(), title + "---1", Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (topic == 1) {
                String patientName = list2.get(position).getPatName();
                String bedNo = list2.get(position).getBedCode();
                String patientSex = list2.get(position).getSex();

                itemHolder.tvMessageBedNo.setText(bedNo + "床");
                itemHolder.tvMessagePatientName.setText(patientName);
                if (patientSex.equals("M")) {
                    itemHolder.imgMessagePatientSex.setImageResource(R.drawable.sex_male);
                } else {
                    itemHolder.imgMessagePatientSex.setImageResource(R.drawable.sex_female);

                }
                itemHolder.tvMessageDoctorName.setVisibility(View.GONE);
                itemHolder.tvPatientLoc.setVisibility(View.GONE);

                itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(getActivity(), title + "---2", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                String patientName = list3.get(position).getPatName();
                String bedNo = list3.get(position).getBedCode();
                String patientSex = list3.get(position).getSex();

                itemHolder.tvMessageBedNo.setText(bedNo + "床");
                itemHolder.tvMessagePatientName.setText(patientName);
                if (patientSex.equals("M")) {
                    itemHolder.imgMessagePatientSex.setImageResource(R.drawable.sex_male);
                } else {
                    itemHolder.imgMessagePatientSex.setImageResource(R.drawable.sex_female);

                }

                if (list3.get(position).getConDocdesc() != null) {
                    itemHolder.tvMessageDoctorName.setVisibility(View.VISIBLE);
                    String patientLoc = list3.get(position).getPatLoc();
                    String doctorName = list3.get(position).getConDocdesc();
                    itemHolder.tvPatientLoc.setText(patientLoc);
                    itemHolder.tvMessageDoctorName.setText(doctorName);


                } else {
                    itemHolder.tvMessageDoctorName.setVisibility(View.GONE);
                }


                itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(getActivity(), title + "---3", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvMessageTitle.setText(title);
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView tvMessageTitle;

        HeaderViewHolder(View view) {
            super(view);
            tvMessageTitle = view.findViewById(R.id.tv_message_title);
        }
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private View rootView;
        private TextView tvMessageBedNo;
        private TextView tvMessagePatientName;
        private ImageView imgMessagePatientSex;
        private TextView tvPatientLoc;
        private TextView tvMessageDoctorName;

        ItemViewHolder(View view) {
            super(view);

            rootView = view;
            tvMessageBedNo = view.findViewById(R.id.tv_message_bedno);
            tvMessagePatientName = view.findViewById(R.id.tv_message_patientname);
            imgMessagePatientSex = view.findViewById(R.id.img_message_patientsex);
            tvPatientLoc = view.findViewById(R.id.tv_message_patientloc);
            tvMessageDoctorName = view.findViewById(R.id.tv_message_doctorname);
        }
    }


}
