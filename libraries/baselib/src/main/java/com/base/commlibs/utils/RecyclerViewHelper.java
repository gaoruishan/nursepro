package com.base.commlibs.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 处理RecyclerView的一些常用操作
 */
public class RecyclerViewHelper {

    private static final String TAG = "RecyclerViewHelper";
    private static final int TAG_CHECK_SCROLL_UP = 1;
    private static final int TAG_CHECK_SCROLL_DOWN = -1;

    public static RecyclerView get(Activity mContext, @IdRes int id) {
        RecyclerView recyclerView = mContext.findViewById(id);
        setDefaultRecyclerView(mContext, recyclerView, 0, LinearLayoutManager.VERTICAL);
        return recyclerView;
    }
    /**
     * 获取默认RecyclerView配置
     * @param mContext
     * @param recyclerView
     */
    public static void setDefaultRecyclerView(Context mContext, RecyclerView recyclerView, @DrawableRes int id) {
        setDefaultRecyclerView(mContext, recyclerView, id, LinearLayoutManager.VERTICAL);
    }
    public static void setDefaultRecyclerViewScroll(Context mContext, RecyclerView recyclerView, @DrawableRes int id) {
        LinearLayoutManager linearLayoutManager = setCommDefaultOpthions(mContext, recyclerView, id, LinearLayoutManager.VERTICAL);
        setRecyclerScrollListener(recyclerView,linearLayoutManager);
    }

    /**
     * 获取指定的RecyclerView配置
     * @param mContext
     * @param recyclerView
     * @param id           ID为0 不显示
     */
    public static void setDefaultRecyclerView(Context mContext, RecyclerView recyclerView, @DrawableRes int id, @RecyclerView.Orientation int orientation) {
        setCommDefaultOpthions(mContext, recyclerView, id, orientation);
    }

    public static void setRecyclerViewFling(RecyclerView recyclerView) {
            // PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
//        pagerSnapHelper.attachToRecyclerView(recyclerView);
            //滚动监听
            recyclerView.setOnFlingListener(null);
            LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
            linearSnapHelper.attachToRecyclerView(recyclerView);
    }

    private static LinearLayoutManager setCommDefaultOpthions(Context mContext, RecyclerView recyclerView, @DrawableRes int id, @RecyclerView.Orientation int orientation) {
        //解决嵌套滚动问题
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //创建布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setOrientation(orientation);
        recyclerView.setLayoutManager(linearLayoutManager);
        //添加自定义分割线
        if (id != 0) {
            DividerItemDecoration divider = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
            divider.setDrawable(ContextCompat.getDrawable(mContext, id));
            recyclerView.addItemDecoration(divider);
        }
        return linearLayoutManager;
    }

    /**
     * 设置GridLayoutManager
     * @param mContext
     * @param recyclerView
     * @param spanCount    一行多少个
     */
    public static void setGridRecyclerView(Context mContext, RecyclerView recyclerView, int spanCount, boolean orientation) {
        setDefaultGridRecyclerView(mContext, recyclerView, spanCount, orientation);
    }
    public static void setGridRecyclerView(Context mContext, RecyclerView recyclerView, int spanCount, @DrawableRes int id, boolean orientation) {
        setDefaultGridRecyclerView(mContext, recyclerView, spanCount, orientation);
        //添加自定义分割线
        if (id != 0) {
            DividerItemDecoration divider = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
            divider.setDrawable(ContextCompat.getDrawable(mContext, id));
            recyclerView.addItemDecoration(divider);
        }
    }
    private static void setDefaultGridRecyclerView(Context mContext, RecyclerView recyclerView, int spanCount, boolean orientation) {
        //布局管理器所需参数，上下文
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, spanCount);
        //B 通过布局管理器，可以控制条目排列顺序  true：反向显示  false：正常显示(默认)
//		gridLayoutManager.setReverseLayout(isversion);
        //C 设置RecyclerView显示的方向，是水平还是垂直！！ GridLayoutManager.VERTICAL(默认) false
        gridLayoutManager.setOrientation(orientation ? GridLayoutManager.HORIZONTAL : LinearLayoutManager.VERTICAL);
        //设置布局管理器 ， 参数 linearLayout
        recyclerView.setLayoutManager(gridLayoutManager);
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL);
//        divider.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.timepicker_sel_text_item));
        recyclerView.addItemDecoration(divider);
    }

    public static void setGridRecyclerViewScroll(Context mContext, RecyclerView recyclerView, int spanCount, boolean orientation) {
        //布局管理器所需参数，上下文
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, spanCount);
        setDefaultGridRecyclerView(mContext, recyclerView, spanCount, orientation);

        //滚动监听
        if (orientation) {
            setRecyclerScrollListener(recyclerView, gridLayoutManager);
        }else {
            setRecyclerViewFling(recyclerView);
        }
    }

    /**
     * 设置滚动补偿
     * @param recyclerView
     * @param layoutManager
     */
    public static void setRecyclerScrollListener(RecyclerView recyclerView, final LinearLayoutManager layoutManager) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                canScrollVertically (int direction)direction为 -1 表示手指向下滑动（屏幕向上滑动）， 1 表示手指向上滑动（屏幕向下滑动）。
//               canScrollHorizontally (int direction) 这个方法用来判断 水平方向的滑动
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                第一个可见item的位置 + 当前可见的item个数 >= item的总个数
//                int visibleItemCount = layoutManager.getChildCount();
//                int totalItemCount = layoutManager.getItemCount();
//                int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
                /**
                 * 滚动选择的position
                 */
                View focusedChild = layoutManager.getFocusedChild();
                if (focusedChild != null) {
                    int position = layoutManager.getPosition(focusedChild);
                    recyclerView.smoothScrollToPosition(position);
                }

            }
        });
    }

    public static void setRecyclerViewTopSpace(RecyclerView recyclerView, int topSpace) {
        if (null != recyclerView) {
            recyclerView.addItemDecoration(new SpaceItemDecoration(topSpace));
        }

    }


    static class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            if (parent.getChildPosition(view) != 0) {
                outRect.left = 0;
                outRect.right = 0;
                outRect.top = space;
            }
        }
    }
}
