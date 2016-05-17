package space.qssq.dragdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
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
 * 逻辑写了好多 貌似写完了花了 1小时 ，但是昨天那个花的时间太多了
 */
public class LoopHorizontalPager extends FrameLayout {

    private static final String TAG = "LoopHorizontalPager";
    private float criticalVel;
    private ViewDragHelper mDragger;
    private View mViewBg;
    private View mCoverView;
    private int mSize;

    public LoopHorizontalPager(Context context) {
        super(context);
        init(context);
    }

    public LoopHorizontalPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public LoopHorizontalPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }
//
//    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float dx, float dy) {
//            return true;
////            return Math.abs(dy) <= Math.abs(dx);
//        }
//    }

    public void init(Context context) {
        criticalVel = getResources().getDisplayMetrics().density * 120;
//        gestureDetector = new GestureDetectorCompat(context, new YScrollDetector());
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
                            if (left < -mSize) {
                                return -mSize;
                            } else if (left > mSize) {//往右边滑动不可超过直播的宽度
                                return mSize;
                            } else {
                                return left;
                            }
                        } else if (child == mViewBg) {//底层
                            if (left < -(mSize * 2)) {
                                return -(mSize * 2);
                            } else if (left > mSize) {//往右边滑动不可超过直播的宽度
                                return mSize;
                            } else {
                                return left;
                            }
                        }
                        return 0;
                    }


                    @Override
                    public int clampViewPositionVertical(View child, int top, int dy) {

                        return 0;
                    }

                    @Override
                    public int getViewVerticalDragRange(View child) {
//                        return super.getViewVerticalDragRange(child);
                        return 0;
                    }

                    @Override
                    public int getViewHorizontalDragRange(View child) {
                        Log.i(TAG, "getViewHorizontalDragRange:" + child.getClass().getSimpleName());
                        return 0;
//                        if (child instanceof VerticalLPager) {
//                            return 0;
//                        }
//                        return super.getViewHorizontalDragRange(child);
                    }

                    @Override
                    public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                        super.onViewPositionChanged(changedView, left, top, dx, dy);
                        lastDx = dx;
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

                    int lastDx = 0;

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
                                if (mCoverView.getLeft() > 0) {//表明 往右边拉 还没拉到1半 所以回弹
                                    if (mCoverView.getLeft() > mSize / 2) {
                                        //还在屏幕右边  判断速度
                                        if (lastDx > 0) {
                                            hidden();
                                        } else {
                                            show();
                                        }
                                    } else {
                                        if (lastDx > 0) {
                                            hidden();
                                        } else {
                                            hiddenToLeft();
                                        }
                                    }
                                    Log.i(TAG, "速度显示");
                                } else {
                                    if (lastDx > 0) {
                                        show();
                                        Log.i(TAG, "速度显示->");
                                    } else {
                                        hiddenToLeft();
                                        Log.i(TAG, "速度隐藏");
                                    }
                                }
                                return;
                            } else {
                                if (mCoverView.getLeft() < 0) {//表明 往右边往左边拉 还没拉到1半 所以回弹
                                    hiddenToLeft(); //由于速度是
                                    Log.i(TAG, "速度隐藏 从 屏幕左边看不见到地方->操作 快速从右边拉红色块");
                                } else {
                                    show();
                                    Log.i(TAG, "速度显示隐藏 从 屏幕左边看不见的地方快速拖拽");
                                }
                                return;
                            }
                        }

                        if (mCoverView.getLeft() > 0) {//表明 往右边拉 还没拉到1半 所以回弹
                            if (mCoverView.getLeft() < mSize / 2) {
                                show();
                            } else {
                                hidden();
                            }
                        } else {
                            if (Math.abs(mCoverView.getLeft()) > mSize / 2) { //屏幕中显示的范围越小 其复数越大 越大取绝对值 正数大于屏幕一半了
                                hiddenToLeft();
                            } else {
                                show();
                            }
                        }

                    }
                }

        );
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mDragger.shouldInterceptTouchEvent(event)) {
            return true;
        }
//            Log.i(TAG, "onInterceptTouchEvent 已收到");
        return allowVerticalScroll(event);
//            return super.onInterceptTouchEvent(event);
//            return super.onInterceptTouchEvent(event);

    }


    public boolean allowVerticalScroll(MotionEvent event) {
        boolean intercepted = false;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mDragger.continueSettling(true)) {//自己这边的动画都没处理完毕肯定自还是交给自己处理咯!
                    mDragger.shouldInterceptTouchEvent(event);
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {//水平滑动
                    //自己就是水平的拦截
                    mDragger.shouldInterceptTouchEvent(event);
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
            default:
                break;
        }
        Log.i(TAG, "是否进行拦截:" + intercepted + ",x:" + x + ",y:" + y);
        mLastX = x;
        mLastY = y;
        return intercepted;
    }

    public int mLastX;
    public int mLastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        Log.i(TAG, "onTouchEvent" + mDragger.getViewDragState());
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

//            if (mCoverView.getLeft() == mSize) {
//                mCoverView.offsetLeftAndRight(-mSize);//移动回去 从右边移动到最其实屏幕左边
//            } else if (mCoverView.getLeft() == -mSize) {
//                mCoverView.offsetLeftAndRight(mSize);//移动回去 左  屏幕 左边-宽度 移动回来
//            }
            Log.i(TAG, "执行完毕" + mCoverView.getLeft());
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

    public void hiddenToLeft() {
        if (mDragger.smoothSlideViewTo(mCoverView, -mSize, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public interface OnPageChangeListener {
        public void onPageNext();

        public void onPagePre();

        public void onViewShow();

        public void onViewHidden();
    }

}
