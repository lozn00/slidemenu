package cn.qssq666.slidemenu;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by luozheng on 2016/5/17.  qssq.space
 * 默认显示2个封面
 * 把封面往右边拖将会隐藏 隐藏之后往左边面拉又会被拉出来
 */
public class HorizontalPager extends FrameLayout  implements NestedScrollingParent {

    private static final String TAG = "TwoPager";
    private float criticalVel;
    private ViewDragHelper mDragger;
    private View mViewBg;
    private View mCoverView;
    private int mSize;

    public HorizontalPager(Context context) {
        super(context);
        init(context);
    }

    public HorizontalPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public HorizontalPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        criticalVel = getResources().getDisplayMetrics().density * 200;
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
                    @Override
                    public boolean tryCaptureView(View child, int pointerId) {
//                        mCoverView.setVisibility(VISIBLE);
//                        mCoverView.offsetLeftAndRight(0);
                        return true;
                    }

                    @Override
                    public int clampViewPositionHorizontal(View child, int left, int dx) {
                        Log.i(TAG, "clampViewPositionVertical->" + left + "," + dx);
                        if (child == mCoverView) {//不能向左边滑动只能向右边滑动 右边滑动之后就把直播展示出来了
                            if (left < 0) {
                                return 0;
                            } else if (left > mSize) {//往右边滑动不可超过直播的宽度
                                return mSize;
                            } else {
                                return left;
                            }
                        } else if (child == mViewBg) {//底层
                            if (left < -mSize) {
                                return -mSize;
                            } else if (left > mSize) {//往右边滑动不可超过直播的宽度
                                return mSize;
                            } else {
                                return left;
                            }
                        }
                        return 0;
                    }

                        //写上垂直和水平 可以解决不能响应点击事件问题
                        @Override
                        public int getViewHorizontalDragRange(View child)
                        {
                            return 0;
                        }

                        @Override
                        public int getViewVerticalDragRange(View child)
                        {
                            return 0;
                        }

                    @Override
                    public int clampViewPositionVertical(View child, int top, int dy) {

                        return 0;
                    }

                    @Override
                    public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                        super.onViewPositionChanged(changedView, left, top, dx, dy);
                        if (changedView == mViewBg) {
                            mViewBg.offsetLeftAndRight(-dx);//自己撤销回去
                            int tempValue = mCoverView.getLeft() + dx;
                            if (tempValue <= mSize) {
                                mCoverView.offsetLeftAndRight(dx);
                            }
                        } else {

                        }
                        Log.d("3 onViewPositionChanged", String.format("changedView " + changedView + " ,left %s ,top %s ,dx %s ,dy %s", changedView.getTag(), left, top, dx, dy));
                    }

                    @Override
                    public void onViewDragStateChanged(int state) {
                        super.onViewDragStateChanged(state);

                        Log.i(TAG, "onViewDragStateChanged");
                        switch (state) {
                            case ViewDragHelper.STATE_IDLE:
                                Log.i(TAG, "STATE_IDLE");

                        }
                    }

                    @Override
                    public void onViewReleased(View releasedChild, float xvel, float yvel) {
                        Log.w("onViewReleased", String.format("releasedChild %s,xvel %s,yvel %s", releasedChild.getTag(), xvel, yvel));
                        if (Math.abs(xvel) >= criticalVel) {
                            if (xvel > 0) {//从左边往右边滑动
                                hidden();
                                return;
                            } else {
                                show();
                                return;
                            }
                        }

                        if (mCoverView.getLeft() < mSize / 2) {//表明 往右边拉 还没拉到1半 所以回弹
                            show();
                        } else {
                            hidden();
                        }

                    }
                }

        );
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mDragger.shouldInterceptTouchEvent(event)) {
            return true;
        } else {
            return super.onInterceptTouchEvent(event);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mViewBg = getChildAt(0);
        mCoverView = getChildAt(1);
        post(new Runnable() {
            @Override
            public void run() {
                mSize = getWidth();
            }
        });
        /**
         * 首页是这样的 最顶层 放的是直播评论  刚开始点击应该滑动的是mLiveComment
         */
    }

    /**
     * 是否还需要移动
     */
    @Override
    public void computeScroll() {

        if (mDragger.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
//            mCoverView.offsetLeftAndRight(0);
//            mCoverView.setVisibility(GONE);
            Log.i(TAG, "执行完毕");
        }
    }


    public void show() {
        //租后的左边和定边
        if (mDragger.smoothSlideViewTo(mCoverView, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void hidden() {
        if (mDragger.smoothSlideViewTo(mCoverView, mSize, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

}
