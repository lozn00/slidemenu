package space.qssq.dragdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by luozheng on 2016/5/16.  qssq.space
 */
public class LiveViewGroup extends FrameLayout {

    private static final String TAG = "LiveViewGroup";
    private ViewDragHelper mDragger;
    private View mCurrentLiveUi;
    /**
     * 下一个直播的封面图
     */
    private View mNextLiveUi;
    /**
     * 聊天在线人数 送花交互view
     */
    private View mInteractiveUi;
    private int mWidth;
    private GestureDetectorCompat gestureDetector;
    private float criticalVel;

    public LiveViewGroup(Context context) {
        super(context);
        init(context);
    }

    public LiveViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public LiveViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 不允许垂直 y方向滚动 所以 y的最大值必须 小于x的大小
     */
    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float dx, float dy) {
            return Math.abs(dy) <= Math.abs(dx);
        }
    }

    public void init(Context context) {
        criticalVel = getResources().getDisplayMetrics().density * 200;
        gestureDetector = new GestureDetectorCompat(context, new YScrollDetector());
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
                    @Override
                    public boolean tryCaptureView(View child, int pointerId) {
                        return true;
                    }

                    @Override
                    public int clampViewPositionHorizontal(View child, int left, int dx) {
                        //dx表示滑动的距离大于0表示右滑动  小于0 表示向左边滑动 left表示距离屏幕左边的距离
//                        Log.i("clampViewPositionHorizontal", "left:" + left + ",dx:" + dx);
                        if (child == mInteractiveUi) {//不能向左边滑动只能向右边滑动 右边滑动之后就把直播展示出来了
                            if (left < 0) {
                                return 0;
                            } else if (left > mWidth) {//往右边滑动不可超过直播的宽度
                                return mWidth;
                            }
                        } else if (child == mCurrentLiveUi) { //
                            if (left < -mWidth) {//拉得太过了肯定不行
                                return -mWidth;
                            } else if (left > mWidth) {//往右边滑动不可超过直播的宽度  直播ui往右边拉可以，但是在下一步要撤销回去。
                                return mWidth;
                            }
                        } else if (child == mNextLiveUi) {//自己不动 不能右边滑动 了, 另外左边滑动其实是把 直播view滑动出来
                            if (left > 0) {
                                return 0;
                            } else if (left < -mWidth) {//往右边滑动不可超过直播的宽度
                                return -mWidth;
                            }
                        }
                        return left;
                    }

                    @Override
                    public void onViewPositionChanged(View changedView, int left, int top, int dx,
                                                      int dy) {
                        super.onViewPositionChanged(changedView, left, top, dx, dy);

//                        Log.d("3 onViewPositionChanged", String.format("changedView %s ,left %s ,top %s ,dx %s ,dy %s", changedView.getTag(), left, top, dx, dy));
                        if (changedView == mCurrentLiveUi) {
//                            // 验证移动的范围对不对，如果不对就啥都不做
                            if (dx < 0) {//往右边 那么左边变成复数 拉自己不动
                                if (left < 0) {
                                    if (mCurrentLiveUi.getLeft() != 0) {
                                        mCurrentLiveUi.offsetLeftAndRight(-dx);//自己撤销回去？拉多少撤销多少 除非自己都没考左边
                                    } else {
                                        mCurrentLiveUi.offsetLeftAndRight(-dx);//自己撤销回去？拉多少撤销多少 除非自己都没考左边
                                        int tmpXLeft = mInteractiveUi.getLeft() + dx;
                                        if (tmpXLeft < mWidth && mInteractiveUi.getLeft() != mWidth) {
                                            mInteractiveUi.offsetLeftAndRight(dx);
                                        }

                                    }


                                } else {
                                    int tmpXLeft = mInteractiveUi.getLeft() + dx;
                                    if (tmpXLeft < mWidth) {
                                        if (left < 0) {//自己都没滑动回来
                                            mInteractiveUi.offsetLeftAndRight(dx);
                                        }
                                    }
                                }
                            } else if (dx > 0) {
//                                   //自己还是不能往右边滑动 除非自己距离左边有距离
                                if (mInteractiveUi.getLeft() != mWidth) {
                                    int tmpXLeft = mInteractiveUi.getLeft() + dx;
                                    if (tmpXLeft < mWidth) {
                                        if (left < 0) {//自己都没滑动回来
                                            mInteractiveUi.offsetLeftAndRight(dx);
                                        }
                                    }
                                    mCurrentLiveUi.offsetLeftAndRight(-dx);//自己撤销回去？拉多少撤销多少 除非自己都没考左边
                                } else if (mCurrentLiveUi.getLeft() > 0) {
                                    mCurrentLiveUi.offsetLeftAndRight(-dx);//自己撤销回去？拉多少撤销多少 除非自己都没考左边
                                }
                            }
                        } else if (changedView == mNextLiveUi) {
                            int tmpXLeft = mCurrentLiveUi.getLeft() + dx;
                            if (dx < 0) {
                                if (tmpXLeft > 0) {
                                    mCurrentLiveUi.offsetLeftAndRight(dx);//这里应该是负数
                                }
                            } else if (dx < 0) {
                                if (tmpXLeft <= mWidth) {
                                    mCurrentLiveUi.offsetLeftAndRight(dx);//这里应该是正数
                                }
                            }
                            mNextLiveUi.offsetLeftAndRight(-dx);//自己撤销回去？拉多少撤销多少
                        }
                    }
//                        if (changedView == mCurrentLiveUi) {
//                            // 验证移动的范围对不对，如果不对就啥都不做
//                            int tmpXLeft = mInteractiveUi.getLeft() + dx;
//                            if (tmpXLeft < mWidth) {
//
//                                mInteractiveUi.offsetLeftAndRight(dx);
//                            }
//                            mCurrentLiveUi.offsetLeftAndRight(-dx);//撤销回去？
//
//                        }

                    @Override
                    public int clampViewPositionVertical(View child, int top, int dy) {
                        Log.i("clampViewPositionVertical", "left:" + top + ",dx:" + dy);
                        return 0;
                    }

                    /*！ 一般用来进行动画效果
                         * 当view的位置发生变化的时候会调用此方法
                         * changedView 位置发生变化的view
                         * left 当前changedView的left边的位置
                         * top  当前changedView的top边的位置
                         * dx   left边在  移动  前后的变化值
                         * dy   top边在  移动  前后的变化值
                         *
                         */
                    @Override
                    public void onViewDragStateChanged(int state) {
                        super.onViewDragStateChanged(state);
                        Log.i(TAG, "onViewDragStateChanged");
                        switch (state) {
                            case ViewDragHelper.STATE_IDLE:
//                                if (mInteractiveUi.getLeft() > mWidth / 2) {
//                                    currentView = CURRENT_VIEW.VIEW_TWO;
//                                } else if (mInteractiveUi.getLeft() > 0) {
//                                    currentView = CURRENT_VIEW.VIEW_THERE;
//                                } else if (mNextLiveUi.getLeft() > -mWidth / 2) {
//                                    currentView = CURRENT_VIEW.VIEW_TWO;
//                                } else if (mInteractiveUi.getLeft() < 0) {
//                                    currentView = CURRENT_VIEW.VIEW_THERE;
//                                    break;
//                                } else {
//                                    currentView = CURRENT_VIEW.VIEW_TWO;
//                                }
//                                Log.i(TAG, "CURRENT state:" + currentView);
//                                break;

                        }
                    }


                    /*
                     *
                     * 当手指抬起的时候，会被调用，表示view被释放
                     * releasedChild
                     * 速度的值是由记速器VelocityTracker对象提供的
                     * xvel 释放时水平方向的速度，单位是像素/秒
                     * yvel 释放时垂直方向的速度，单位是像素/秒
                     */
                    @Override
                    public void onViewReleased(View releasedChild, float xvel, float yvel) {
                        Log.i(TAG, "onViewReleased");
                        Log.w("onViewReleased", String.format("releasedChild %s,xvel %s,yvel %s", releasedChild.getTag(), xvel, yvel));
                        judgeState(xvel);
                    }
                }

        );
    }

    public void judgeState(float xvel) {
//        if (true) {
//            return;
//        }
        if (mInteractiveUi.getLeft() != mWidth) {//如果原来是 显示 互动界面的
            if (Math.abs(xvel) >= criticalVel) {
                if (xvel > 0) {//从左往右边滑动
                    hiddenInteractiveUi();
                    currentView = CURRENT_VIEW.VIEW_TWO;
                } else {
                    showInteractiveUi();
                    currentView = CURRENT_VIEW.VIEW_THERE;
                }
                return;
            }

            if (mInteractiveUi.getLeft() < mWidth / 2) {//表明 往右边拉 还没拉到1半 所以回弹
                showInteractiveUi();
            } else if (mInteractiveUi.getLeft() > mWidth / 2) {//表明 往右边拉 拉到1半了
                currentView = CURRENT_VIEW.VIEW_TWO;
                hiddenInteractiveUi();
            }

        } else if (mCurrentLiveUi.getLeft() != 0) {
            if (Math.abs(xvel) >= criticalVel) {
                if (xvel > 0) {//从左往右边滑动
                    showCurrentUi();
                    currentView = CURRENT_VIEW.VIEW_TWO;
                } else {
                    hiddenCurrentUi();
                    currentView = CURRENT_VIEW.VIEW_ONE;
                }
                return;
            }

            int value = mCurrentLiveUi.getLeft();
            if (Math.abs(value) > mWidth / 2) {//说明负数很大了
                hiddenCurrentUi();
                currentView = CURRENT_VIEW.VIEW_ONE;
            } else {
                showCurrentUi();
                currentView = CURRENT_VIEW.VIEW_TWO;
            }
        /*
            value永远不可能大于0
        解决ui问题但是现在 改变判断条件了不需要再这里做了
         if (value == 0) {//dengyu 0
                if (mInteractiveUi.getLeft() != mWidth) {
                    if (mInteractiveUi.getLeft() < mWidth / 2) {
                        hiddenInteractiveUi();
                    } else {
                        hiddenCurrentUi();
                        showInteractiveUi();
                    }
                } else {
                }
            }
            */
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mDragger.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.i(TAG, "onFinishInflate");
        mNextLiveUi = getChildAt(0);

        mCurrentLiveUi = getChildAt(1);
        mInteractiveUi = getChildAt(2);
        mCurrentLiveUi.post(new Runnable() {
            @Override
            public void run() {
                mWidth = mCurrentLiveUi.getWidth();
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
            if (onPageChangeListener != null)
                if (mInteractiveUi.getLeft() == 0) {
                    onPageChangeListener.onPageNext();
                } else if (mCurrentLiveUi.getLeft() == mWidth) {
                    onPageChangeListener.onPagePre();
                } else {
                    onPageChangeListener.onPageCurrent();
                }
            Log.i(TAG, "执行完毕");
        }
    }

    public interface onPageChangeListener {
        public void onPageNext();

        public void onPagePre();

        public void onPageCurrent();
    }

    public void setOnPageChangeListener(LiveViewGroup.onPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    onPageChangeListener onPageChangeListener = null;


    public void showInteractiveUi() {
        //租后的左边和定边
        if (mDragger.smoothSlideViewTo(mInteractiveUi, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void hiddenInteractiveUi() {
        if (mDragger.smoothSlideViewTo(mInteractiveUi, mWidth, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void showCurrentUi() {
        //租后的左边和定边
        if (mDragger.smoothSlideViewTo(mCurrentLiveUi, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void hiddenCurrentUi() {
        if (mDragger.smoothSlideViewTo(mCurrentLiveUi, mWidth, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }


//    public void showNextUi() {
//        //租后的左边和定边
//        if (mDragger.smoothSlideViewTo(mNextLiveUi, 0, 0)) {
//            ViewCompat.postInvalidateOnAnimation(this);
//        }
//    }
//
//    public void hiddenNextUi() {
//        if (mDragger.smoothSlideViewTo(mNextLiveUi, -mWidth, 0)) {
//            ViewCompat.postInvalidateOnAnimation(this);
//        }
//    }

    // 使用估值器和属性动画（nineoldAndroid）(ViewHelper)
    protected void dispatchAnimation(float percent) {

        // 主界面有缩放动画
//		mainView.setScaleX(0.5f);
//		ViewHelper.setScaleX(mainView, 0.5f);


        // 菜单界面位移、缩放、透明
        //位移
        // 单位是像素
//        ViewHelper.setTranslationX(menuView, floatEvaluator.evaluate(percent, - leftWidth /2, 0));
////		缩放
//        Float menuScale = floatEvaluator.evaluate(percent, menuMinScale, 1);
//        ViewHelper.setScaleX(menuView, menuScale);
//        ViewHelper.setScaleY(menuView, menuScale);
////		透明
//        ViewHelper.setAlpha(menuView, percent);

    }

    /**
     * ONE 就是 往TWO也就是没有交互的页面往右边滑动显示的界面
     */
    public enum CURRENT_VIEW {
        VIEW_ONE, VIEW_TWO, VIEW_THERE;
    }

    public CURRENT_VIEW currentView = CURRENT_VIEW.VIEW_THERE;//默认显示 交互view

    public void setCurrentView(CURRENT_VIEW currentView, boolean needAnim) {
        switch (currentView) {
            case VIEW_ONE:
                if (needAnim) {
                    if (mInteractiveUi.getLeft() != mWidth) {
                        hiddenCurrentUi();
                    } else if (mCurrentLiveUi.getLeft() != 0) {
                        hiddenCurrentUi();
                    }

                } else {
                    mInteractiveUi.offsetLeftAndRight(mWidth);//这里应该是负数
                    mCurrentLiveUi.offsetLeftAndRight(mWidth);//这里应该是负数

                }
                break;
            case VIEW_TWO:
                if (needAnim) {
                    if (mInteractiveUi.getLeft() != mWidth) {
                        hiddenCurrentUi();
                    }
                    showCurrentUi();

                } else {
                    mInteractiveUi.offsetLeftAndRight(mWidth);//这里应该是负数
                    mCurrentLiveUi.offsetLeftAndRight(0);//这里应该是负数

                }
                break;
            case VIEW_THERE:
                if (needAnim) {
                    if (mCurrentLiveUi.getLeft() != 0) {
                        showCurrentUi();
                    }
                    showInteractiveUi();

                } else {
                    mCurrentLiveUi.offsetLeftAndRight(0);
                    mInteractiveUi.offsetLeftAndRight(0);
                }
                break;
            default:
                return;
        }
        this.currentView = currentView;
    }
}
