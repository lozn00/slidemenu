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
 * 把封面往下面拖将会隐藏 隐藏之后往上面拉又会被拉出来
 */
public class VerticalLPager extends FrameLayout {

    private static final String TAG = "VerticalLPager";
    private float criticalVel;
    private ViewDragHelper mDragger;
    private View mViewBg;
    private View mCoverView;
    private int mSize;
    private int mPointerId;

    public VerticalLPager(Context context) {
        super(context);
        init(context);
    }

    public VerticalLPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public VerticalLPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


//    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float dx, float dy) {
//            return Math.abs(dy) <= Math.abs(dx);
//        }
//    }


    public void init(Context context) {
        criticalVel = getResources().getDisplayMetrics().density * 120;
//        gestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
//            @Override
//            public boolean onDown(MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public void onShowPress(MotionEvent e) {
//
//            }
//
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//                return false;
//            }
//
//            @Override
//            public void onLongPress(MotionEvent e) {
//
//            }
//
//            @Override
//            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                return false;
//            }
//        });
        criticalVel = getResources().getDisplayMetrics().density * 200;
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
                    @Override
                    public boolean tryCaptureView(View child, int pointerId) {
                        return true;
                    }

                    @Override
                    public int clampViewPositionHorizontal(View child, int left, int dx) {
                        return 0;
                    }


                    @Override
                    public int clampViewPositionVertical(View child, int top, int dy) {
                        Log.i(TAG, "clampViewPositionVertical->" + top + "," + dy);
                        if (child == mCoverView) {//不能向左边滑动只能向右边滑动 右边滑动之后就把直播展示出来了
                            if (top < 0) {
                                return 0;
                            } else if (top > mSize) {//往右边滑动不可超过直播的宽度
                                return mSize;
                            } else {
                                return top;
                            }
                        } else if (child == mViewBg) {//底层
                            if (top < -mSize) {
                                return -mSize;
                            } else if (top > mSize) {//往右边滑动不可超过直播的宽度
                                return mSize;
                            } else {
                                return top;
                            }
                        }
                        return 0;
                    }

                    @Override
                    public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                        super.onViewPositionChanged(changedView, left, top, dx, dy);
                        if (changedView == mViewBg) {
                            mViewBg.offsetTopAndBottom(-dy);//自己撤销回去
                            int tmpXTop = mCoverView.getTop() + dy;
                            if (tmpXTop <= mSize) {
                                mCoverView.offsetTopAndBottom(dy);
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
                        if (Math.abs(yvel) >= criticalVel) {
                            if (yvel > 0) {//从上到下惯性滑动速度
                                hidden();
                                return;
                            } else {
                                show();
                                return;
                            }
                        }

                        if (mCoverView.getTop() < mSize / 2) {//表明 往右边拉 还没拉到1半 所以回弹
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
            Log.i(TAG, "拦截了,");
            return true;
        } else {
            Log.i(TAG, "onInterceptTouchEvent 交给子类处理了.并没有拦截");
            return showuldGiveChild(event);
        }

    }
    /**
     * http://my.oschina.net/fengheju/blog/196608
     *
     * @return
     */
    float downX = 0;
    float downY = 0;
    float x = 0;
    float y = 0;

    public boolean showuldGiveChild(MotionEvent ev) {
        final int action = ev.getActionMasked();
        if (BuildConfig.DEBUG)
            Log.d("onInterceptTouchEvent", "action: " + action);

        if (action == MotionEvent.ACTION_DOWN && ev.getEdgeFlags() != 0) {
            // 该事件可能不是我们的
            return false;
        }
        boolean isIntercept = false;

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 将会需要这些初始信息（因为我们的onTouchEvent将可能接收不到ACTION_DOWN事件）
                downX = x = ev.getX();
                downY = y = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float mx = ev.getX();
                float my = ev.getY();
                if (BuildConfig.DEBUG) {
                    Log.d("onInterceptTouchEvent", "action_move [touchSlop: ");
                }
                // 根据方向进行拦截，（其实这样，如果我们的方向是水平的，里面有一个ScrollView，那么我们是支持嵌套的）
                // we get a move event for ourself
                float distanceX = Math.abs(mx - x);
                float distanceY = Math.abs(my - y);
                if (distanceY > distanceX) {
                    isIntercept = true;
                } else {
                    isIntercept = false;
                }
                Log.i(TAG, "x距离" + distanceX + "y距离:" + distanceY);
                //如果不拦截的话，我们不会更新位置，这样可以通过累积小的移动距离来判断是否达到可以认为是Move的阈值。
                //这里当产生拦截的话，会更新位置（这样相当于损失了mTouchSlop的移动距离，如果不更新，可能会有一点点跳的感觉）
                if (isIntercept) {
                    x = mx;
                    y = my;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                // 这是触摸的最后一个事件，无论如何都不会拦截
                isIntercept = false;
                break;
        }
        return isIntercept;
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
                mSize = getHeight();
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
        if (mDragger.smoothSlideViewTo(mCoverView, 0, mSize)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

}
