package cn.qssq666.slidemenu.util;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.AbsListView;

/**
 * Created by qssq on 2017/6/28 qssq666@foxmail.com
 */

public class ViewUtils {
    /**
     * 是否到底部了 能否上拉
     * @param view
     * @return
     */
    public static boolean canChildScrollUp(View view) {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (view instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) view;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return view.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(view, -1);
        }
    }


    //    public boolean canChildScrollUp() {
//        if (android.os.Build.VERSION.SDK_INT < 14) {
//            if (mTarget instanceof AbsListView) {
//                final AbsListView absListView = (AbsListView) mTarget;
//                if (absListView.getLastVisiblePosition() + 1 == absListView.getCount()) {
//                    int lastIndex = absListView.getLastVisiblePosition() - absListView.getFirstVisiblePosition();
//
//                    boolean res = absListView.getChildAt(lastIndex).getBottom() == absListView.getPaddingBottom();
//
//                    return res;
//                }
//                return true;
//            } else {
//                return mTarget.getScrollY() > 0;
//            }
//        } else {
//            return ViewCompat.canScrollVertically(mTarget, 1);
//        }
//    }

    /**
     * 是否手指能往上滾 也就是 內部是否能往下滾
     * @param view
     * @return
     */
    public static boolean canChildScrollDown(View view) {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (view instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) view;
                try {
                    if (absListView.getCount() > 0) {
                        if (absListView.getLastVisiblePosition() + 1 == absListView.getCount()) {
                            int lastIndex = absListView.getLastVisiblePosition() - absListView.getFirstVisiblePosition();
                            return absListView.getChildAt(lastIndex).getBottom() == absListView.getPaddingBottom();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            } else {
                return true;
            }
        } else {
            return ViewCompat.canScrollVertically(view, 1);
        }
    }
}
