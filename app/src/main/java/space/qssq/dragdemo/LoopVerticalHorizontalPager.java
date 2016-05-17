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
 * <p>
 * 从下往上滑动 又需要处理另外一个问题。。
 */
public class LoopVerticalHorizontalPager extends FrameLayout {

    private static final String TAG = "TwoPager";
    private float criticalVel;
    private ViewDragHelper mDragger;
    /**
     * 最底层的view
     */
    private View mViewBg;
    /**
     * 左右滑动的上一页和下一页
     */
    private View mPreNextView;
    /**
     * 从下往上滑动的的控件view 在最顶层
     */
    private View mButtomView;
    private int mWidth;
    private int mHeight;

    public LoopVerticalHorizontalPager(Context context) {
        super(context);
        init(context);
    }

    public LoopVerticalHorizontalPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public LoopVerticalHorizontalPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    enum DRAG_STATE {
        VERTICAL_DRAG, HORIZONTAL_DRAG, STOP;
    }

    DRAG_STATE currentDragState = DRAG_STATE.STOP;

    public void init(Context context) {
        criticalVel = getResources().getDisplayMetrics().density * 120;
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
                    @Override
                    public boolean tryCaptureView(View child, int pointerId) {
//                        mPreNextView.setVisibility(VISIBLE);
//                        mPreNextView.offsetLeftAndRight(0);

                        return true;
                    }

                    @Override
                    public int clampViewPositionHorizontal(View child, int left, int dx) {
                        if (currentDragState == DRAG_STATE.STOP) {
                            currentDragState = DRAG_STATE.HORIZONTAL_DRAG;
                        } else if (currentDragState == DRAG_STATE.VERTICAL_DRAG) {
                            Log.e(TAG, "当前正在垂直滑动 禁止进行水平滑动");
                            return 0;
                        }
                        Log.i(TAG, "clampViewPositionVertical->" + left + "," + dx);
                        if (child == mPreNextView) {//不能向左边滑动只能向右边滑动 右边滑动之后就把直播展示出来了
                            if (left < -mWidth) {
                                return -mWidth;
                            } else if (left > mWidth) {//往右边滑动不可超过直播的宽度
                                return mWidth;
                            } else {
                                return left;
                            }
                        } else if (child == mViewBg) {//底层
                            if (left < -(mWidth * 2)) {
                                return -(mWidth * 2);
                            } else if (left > mWidth) {//往右边滑动不可超过直播的宽度
                                return mWidth;
                            } else {
                                return left;
                            }
                        }
                        return 0;
                    }


                    @Override
                    public int clampViewPositionVertical(View child, int top, int dy) {
                        if (currentDragState == DRAG_STATE.STOP) {
                            currentDragState = DRAG_STATE.VERTICAL_DRAG;
                        } else if (currentDragState == DRAG_STATE.HORIZONTAL_DRAG) {
                            Log.e(TAG, "当前正在水平滑动 禁止进行垂直滑动");
                            return 0;
                        }
                        Log.i(TAG, "clampViewPositionVertical->" + top + "," + dy);
                        if (child == mButtomView) {//不能向左边滑动只能向右边滑动 右边滑动之后就把直播展示出来了
                            if (top < 0) {
                                return 0;
                            } else if (top > mHeight) {//往右边滑动不可超过直播的宽度
                                return mHeight;
                            } else {
                                return top;
                            }
                        } else if (child == mViewBg) {//底层
                            if (top < -mHeight) {
                                return -mHeight;
                            } else if (top > mHeight) {//往右边滑动不可超过直播的宽度
                                return mHeight;
                            } else {
                                return top;
                            }
                        }
                        return 0;
                    }

                    @Override
                    public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                        super.onViewPositionChanged(changedView, left, top, dx, dy);
                        if (currentDragState == DRAG_STATE.VERTICAL_DRAG) {
                            if (changedView == mViewBg) {
                                mViewBg.offsetTopAndBottom(-dy);//自己撤销回去
                                int tmpXTop = mButtomView.getTop() + dy;
                                if (tmpXTop <= mHeight) {
                                    mButtomView.offsetTopAndBottom(dy);
                                }
                            } else {

                            }
                        } else if (currentDragState == DRAG_STATE.HORIZONTAL_DRAG) {
                            lastDx = dx;
                            if (changedView == mViewBg) {
                                mViewBg.offsetLeftAndRight(-dx);//自己撤销回去
                                int tempValue = mPreNextView.getLeft() + dx;
                                if (tempValue <= mWidth) {
                                    mPreNextView.offsetLeftAndRight(dx);
                                }
                            } else {

                            }
                            Log.d("3 onViewPositionChanged", String.format("changedView " + changedView + " ,left %s ,top %s ,dx %s ,dy %s", changedView.getTag(), left, top, dx, dy));
                        }
                    }

                    int lastDx = 0;

                    @Override
                    public void onViewDragStateChanged(int state) {
                        super.onViewDragStateChanged(state);

                        Log.i(TAG, "onViewDragStateChanged");
                        switch (state) {
                            case ViewDragHelper.STATE_IDLE:
                                Log.i(TAG, "STATE_IDLE");
                                currentDragState = DRAG_STATE.STOP;
                                break;
                            case ViewDragHelper.STATE_DRAGGING:
                                Log.i(TAG, "STATE_DRAGGING");
                                break;
                            case ViewDragHelper.STATE_SETTLING:
                                Log.i(TAG, "STATE_SETTLING");
                                break;

                        }
                    }

                    @Override
                    public void onViewReleased(View releasedChild, float xvel, float yvel) {
                        Log.w("onViewReleased", String.format("releasedChild %s,xvel %s,yvel %s", releasedChild.getTag(), xvel, yvel));
                        if (currentDragState == DRAG_STATE.VERTICAL_DRAG) {
                            if (Math.abs(yvel) >= criticalVel) {
                                if (yvel > 0) {//从上到下惯性滑动速度
                                    hiddenButtom();
                                    return;
                                } else {
                                    showButtom();
                                    return;
                                }
                            }

                            if (mButtomView.getTop() < mHeight / 2) {//表明 往右边拉 还没拉到1半 所以回弹
                                showButtom();
                            } else {
                                hiddenButtom();
                            }
                        } else if (currentDragState == DRAG_STATE.HORIZONTAL_DRAG) {
                            if (Math.abs(xvel) >= criticalVel) {
                                if (xvel > 0) {//从左边往右边滑动
                                    if (mPreNextView.getLeft() > 0) {//表明 往右边拉 还没拉到1半 所以回弹
                                        if (mPreNextView.getLeft() > mWidth / 2) {
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
                                    if (mPreNextView.getLeft() < 0) {//表明 往右边往左边拉 还没拉到1半 所以回弹
                                        hiddenToLeft(); //由于速度是
                                        Log.i(TAG, "速度隐藏 从 屏幕左边看不见到地方->操作 快速从右边拉红色块");
                                    } else {
                                        show();
                                        Log.i(TAG, "速度显示隐藏 从 屏幕左边看不见的地方快速拖拽");
                                    }
                                    return;
                                }
                            }

                            if (mPreNextView.getLeft() > 0) {//表明 往右边拉 还没拉到1半 所以回弹
                                if (mPreNextView.getLeft() < mWidth / 2) {
                                    show();
                                } else {
                                    hidden();
                                }
                            } else {
                                if (Math.abs(mPreNextView.getLeft()) > mWidth / 2) { //屏幕中显示的范围越小 其复数越大 越大取绝对值 正数大于屏幕一半了
                                    hiddenToLeft();
                                } else {
                                    show();
                                }
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
        mPreNextView = getChildAt(1);
        mButtomView = getChildAt(2);
        post(new Runnable() {
            @Override
            public void run() {
                mWidth = getWidth();
        mPreNextView.offsetLeftAndRight(mWidth);
                mHeight = getHeight();

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

            if (mPreNextView.getLeft() == mWidth) {

                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageNext();
                }
            } else if (mPreNextView.getLeft() == -mWidth) {
                mPreNextView.offsetLeftAndRight(mWidth);//移动回去 左  屏幕 左边-宽度 移动回来
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPagePre();
                }
            } else {
                if (mButtomView.getTop() == mHeight) {
                    if (onPageChangeListener != null) {
                        onPageChangeListener.onViewHidden();
                    }
                } else {
                    if (onPageChangeListener != null) {
                        onPageChangeListener.onViewShow();
                    }
                }
            }
            Log.i(TAG, "执行完毕" + mPreNextView.getLeft());
        }
    }

    public void resetByNext() {
        mPreNextView.offsetLeftAndRight(mWidth);
    }

    public void resetByPre() {
        mPreNextView.offsetLeftAndRight(mWidth);
    }

    public void show() {
        //租后的左边和定边
        if (mDragger.smoothSlideViewTo(mPreNextView, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void hidden() {
        if (mDragger.smoothSlideViewTo(mPreNextView, mWidth, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void showButtom() {
        //租后的左边和定边
        if (mDragger.smoothSlideViewTo(mButtomView, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void hiddenButtom() {
        if (mDragger.smoothSlideViewTo(mButtomView, 0, mHeight)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void hiddenToLeft() {
        if (mDragger.smoothSlideViewTo(mPreNextView, -mWidth, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public interface OnPageChangeListener {
        public void onPageNext();

        public void onPagePre();

        public void onViewShow();

        public void onViewHidden();
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    OnPageChangeListener onPageChangeListener = null;
}
