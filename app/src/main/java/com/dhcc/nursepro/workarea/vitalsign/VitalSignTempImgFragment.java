package com.dhcc.nursepro.workarea.vitalsign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.dhcc.nursepro.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * VitalSignTempImgFragment
 * 体温单预览图片
 *
 * @author DevLix126
 * created at 2021/4/14 15:05
 */
public class VitalSignTempImgFragment extends BaseFragment {
    private ViewPager vpVitalsignTempimg;

    private List<String> imgUrls = new ArrayList<>();


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            imgUrls = bundle.getStringArrayList("IMGURLS");
        }
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_vitalsigntempimg), 0xffffffff, 17);
        initView(view);

    }

    private void initView(View view) {
        vpVitalsignTempimg = view.findViewById(R.id.vp_vitalsign_tempimg);
        vpVitalsignTempimg.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgUrls.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {

                FrameLayout frameLayout = new FrameLayout(Objects.requireNonNull(getActivity()));
                FrameLayout.LayoutParams flparams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                frameLayout.setLayoutParams(flparams);

                PhotoView photoView = new PhotoView(getActivity());
                photoView.enable();
                photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                Glide.with(getActivity())
                        .load(imgUrls.get(position))
                        .into(photoView);
                frameLayout.addView(photoView);

                TextView textView = new TextView(getActivity());
                textView.setText("    " + (position + 1) + "  /  " + imgUrls.size());
                frameLayout.addView(textView);

                container.addView(frameLayout);

                return frameLayout;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }
        });
    }


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vital_sign_temp_img, container, false);
    }
}