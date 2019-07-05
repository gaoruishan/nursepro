package com.base.commlibs.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.base.commlibs.BaseApplication;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.R;

/**
 * Created by levis on 2018/6/5.
 */

public class BasePushDialog extends DialogFragment
        implements DialogInterface.OnKeyListener {
    private FrameLayout mContainer;
    private BaseFragment mContentFragment;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (mContentFragment != null) {
            mContentFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_push, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mContainer = view.findViewById(R.id.container);
    }

    /**
     * 关闭对话框
     */
    @Override
    public void dismiss() {
        dismissAnimation(new Runnable() {
            @Override
            public void run() {
                BasePushDialog.super.dismiss();
            }
        });
    }

    /**
     * 关闭对话框
     */
    @Override
    public void dismissAllowingStateLoss() {
        dismissAnimation(new Runnable() {
            @Override
            public void run() {
                BasePushDialog.super.dismissAllowingStateLoss();
            }
        });
    }

    @Override
    public void onStart() {
        Dialog dialog = getDialog();
        dialog.setOnKeyListener(this);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = BaseApplication.getApp().getResources().getDisplayMetrics().widthPixels;
        dialog.getWindow().setAttributes(lp);
        mContainer.removeAllViews();
        mContainer.setVisibility(View.INVISIBLE);
        if (mContentFragment != null) {
            BaseFragment saved = (BaseFragment) getChildFragmentManager()
                    .findFragmentByTag("Content");
            if (saved == null) {
                getChildFragmentManager().beginTransaction()
                        .add(mContainer.getId(), mContentFragment, "Content")
                        .commitAllowingStateLoss();
            } else {
                if (saved == mContentFragment) {
                    getChildFragmentManager().beginTransaction()
                            .show(mContentFragment)
                            .commitAllowingStateLoss();
                } else {
                    getChildFragmentManager().beginTransaction()
                            .replace(mContainer.getId(), mContentFragment, "Content")
                            .commitAllowingStateLoss();
                }
            }
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    final Animation anim = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                            Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0);
                    anim.setDuration(200);
                    anim.setInterpolator(new DecelerateInterpolator());
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mContainer.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    mContainer.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContainer.clearAnimation();
                            mContainer.startAnimation(anim);
                        }
                    }, 200);
                }
            });
            super.onStart();
        } else {
            super.onStart();
            dismiss();
        }
    }

    private void dismissAnimation(final Runnable callback) {
        if (mContentFragment == null) {
            callback.run();
        } else {
            final Animation anim = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
            anim.setDuration(200);
            anim.setInterpolator(new AccelerateInterpolator());
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mContainer.setVisibility(View.INVISIBLE);
                    callback.run();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            mContainer.clearAnimation();
            mContainer.startAnimation(anim);
        }
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP &&
                keyCode == KeyEvent.KEYCODE_BACK) {
            dismiss();
            return true;
        }
        return false;
    }

    /**
     * 显示给定的对话框
     *
     * @param manager
     * @param tag
     * @param contentFragment 内容Fragment
     */
    public void show(FragmentManager manager, String tag, BaseFragment contentFragment) {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BaseDialog);
        mContentFragment = contentFragment;
        try {
            show(manager, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置OnDismissListener
     */
    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setOnDismissListener(listener);
        }
    }
}
