package space.qssq.dragdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
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
public class VerticalLPager extends FrameLayout  implements NestedScrollingParent{

    private static final String TAG = "VerticalLPager";
    private float criticalVel;
    private ViewDragHelper mDragger;
    private View mViewBg;
    private View mCoverView;
    private int mSize;
    private int mPointerId;
    private NestedScrollingParentHelper parentHelper;

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

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    /*

        *    在目标视图消耗卷的一部分之前，对正在进行中的嵌套滚动作出响应。
        *
        *    当使用嵌套滚动时，父视图通常需要一个机会。
        *    在嵌套滚动子操作之前消耗滚动条。这是一个例子
        *    抽屉里包含一个可滚动列表。用户希望能够滚动列表。
        *    在列表本身开始滚动之前完全进入视图。
        *
        *     <BR> <代码> > < / onnestedprescroll代码时调用嵌套调用滚动的孩子
        *     {@链接查看# dispatchnestedprescroll（int，int，int，int []，[]）}。实施应
        *    报告DX中所报告的滚动条的像素是如何被消耗的
        *     <代码>消耗<代码>数组。指标0对应DX和指数1对应了。
        *    这个参数永远不会为空。消耗[ 0 ]和消耗[ 1 ]的初始值
        *    永远是0。
        *
        *     @param目标视图启动嵌套滚动
        *     @param DX水平滚动的距离，以像素为单位
        *     @param DY垂直滚动的距离的像素
        *     @param消耗输出。水平和垂直滚动的距离
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Log.w(TAG,"onNestedPreScroll dx "+dx+", dy:"+dy+",consumed:");
        super.onNestedPreScroll(target, dx, dy, consumed);
    }
    /**
     请求从嵌套滚动抛出。
     *
     *此方法表示嵌套滚动子已检测到合适的条件。
     *一时冲动。一般来说，这意味着一个触摸滚动已经结束了
     * { } velocitytracker速度的方向滚动，达到或超过
     * viewconfiguration # getscaledminimumflingvelocity()最小投掷速度}
     *沿滚动轴。</P >
     *
     *如果嵌套滚动子视图通常抛出，但它位于
     *它自己的内容，它可以使用这个方法委派抛出到它的嵌套滚动。
     *父母代替。家长可随意选择掷球或观察儿童投掷。
     *
     * @param目标视图启动嵌套滚动
     * @param隙气速的水平速度在每秒的像素
     * @paramvelocityy垂直速度在每秒的像素
     * @param消耗如果孩子消耗的放纵，否则为假
     *
     * @param target View that initiated the nested scroll
     * @param velocityX Horizontal velocity in pixels per second
     * @param velocityY Vertical velocity in pixels per second
     * @param consumed true if the child consumed the fling, false otherwise
     * @return true if this parent consumed or otherwise reacted to the fling
     */

    /**
     *在目标视图使用它之前对嵌套的抛出做出反应。
     *
     * <BR>这种方法siginfies，嵌套滚动的孩子已经检测到一个扔了
     *沿每个轴的速度。一般来说，这意味着一个触摸滚动已经结束了
     * {@链接velocitytracker速度}的方向滚动，达到或超过
     * {@链接viewconfiguration # getscaledminimumflingvelocity()最小投掷速度}
     *沿滚动轴。</P >
     *
     *如果嵌套滚动的父进程正在消耗运动作为
     * {@链接# onnestedprescroll（int，int，int，[]）预滚动}，它可能是适当的
     *它也消耗前投掷完成相同的议案。通过返回
     *此方法中的代码<真<代码>，父指示子不应该
     *也有自己的内部内容。
     *
     * @param目标视图启动嵌套滚动
     * @param隙气速的水平速度在每秒的像素
     * @paramvelocityy垂直速度在每秒的像素
     *如果该父项消耗了目标视图前面的抛出，则返回true。
     *
     * @param target View that initiated the nested scroll
     * @param velocityX Horizontal velocity in pixels per second
     * @param velocityY Vertical velocity in pixels per second
     * @return true if this parent consumed the fling ahead of the target view
     */

    /**
     *返回当前轴嵌套滚动这nestedscrollingparent。
     *
     * <BR>一nestedscrollingparent返回其他东西{@链接viewcompat # scroll_axis_none }
     *当前作为一个或多个后代视图的嵌套滚动母函数。
     *层次结构。
     *
     *表示当前嵌套滚动轴的返回标志
     *“看viewcompat # scroll_axis_horizontal
     *“看viewcompat # scroll_axis_vertical
     *“看viewcompat # scroll_axis_none
     * @see ViewCompat#SCROLL_AXIS_HORIZONTAL
     * @see ViewCompat#SCROLL_AXIS_VERTICAL
     * @see ViewCompat#SCROLL_AXIS_NONE
     */
    public int getNestedScrollAxes(){
        return parentHelper.getNestedScrollAxes();
    }



    //    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float dx, float dy) {
//            return Math.abs(dy) <= Math.abs(dx);
//        }
//    }


    public void init(Context context) {
        criticalVel = getResources().getDisplayMetrics().density * 120;
        parentHelper = new NestedScrollingParentHelper(this);
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
                        Log.i(TAG, "tryCaptureView:" + child.getClass().getSimpleName());
//                        =DRAG_STATE.DRAGING;
                        return true;
                    }

                    @Override
                    public int clampViewPositionHorizontal(View child, int left, int dx) {
                        Log.i(TAG, "clampViewPositionHorizontal:" + child.getClass().getSimpleName());
                        return 0;
                    }

                    //写上垂直和水平 可以解决不能响应点击事件问题
                    @Override
                    public int getViewHorizontalDragRange(View child)
                    {
                        return getMeasuredWidth()-child.getMeasuredWidth();
                    }

                    @Override
                    public int getViewVerticalDragRange(View child)
                    {
                        return getMeasuredHeight()-child.getMeasuredHeight();
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
                        Log.d(TAG, " changedView.getClass().getSimpleName()" + String.format("changedView " + changedView.getClass().getSimpleName() + " ,left %s ,top %s ,dx %s ,dy %s", changedView.getTag(), left, top, dx, dy));
                        if (changedView == mViewBg) {
                            mViewBg.offsetTopAndBottom(-dy);//自己撤销回去
                            int top1 = mCoverView.getTop();
                            int tmpXTop = top1 + dy;
                            if (tmpXTop < mSize) {
                                mCoverView.offsetTopAndBottom(dy);
                                Log.i(TAG, "mCoverView.offsetTopAndBottom:" + dy);
                            }
                        } else {

                        }
                    }

                    @Override
                    public void onViewDragStateChanged(int state) {
                        super.onViewDragStateChanged(state);

                        Log.i(TAG, "onViewDragStateChanged" + state);
                        switch (state) {
                            case ViewDragHelper.STATE_IDLE:
                                Log.i(TAG, "STATE_IDLE");
                            case ViewDragHelper.STATE_SETTLING:
                                Log.i(TAG, "STATE_SETTLING");
                                if (onPageChangeListener != null) {
                                    if (mCoverView.getTop() == 0) {
                                        onPageChangeListener.onShow();
                                    } else {
                                        onPageChangeListener.onHidden();
                                    }
                                }
                                //稍微的点击都会出发state_draging，另外发现写在computeScroll 的false不准确 所以移动到这里来
                                break;

                        }
                    }

                    @Override
                    public void onViewReleased(View releasedChild, float xvel, float yvel) {
                        Log.w(TAG, "onViewReleased" + ",TOP:" + mCoverView.getTop() + "," + releasedChild.getClass().getSimpleName() + "" + String.format("releasedChild %s,xvel %s,yvel %s", releasedChild.getTag(), xvel, yvel));
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
//            return allowVerticalScroll(event);
            return super.onInterceptTouchEvent(event);
        }

    }

    public boolean allowVerticalScroll(MotionEvent event) {
        boolean intercepted = false;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                if (mDragger.continueSettling(true)) {//自己这边的动画都没处理完毕肯定自还是交给自己处理咯!
//                    intercepted = true;
//                } else {
                intercepted = false;
//                }
//                getParent().requestDisallowInterceptTouchEvent(true);//不允许父亲拦截
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {//水平滑动
                    intercepted = false;
//                    getParent().requestDisallowInterceptTouchEvent(false);//交给父亲处理
                }
//                else if (Math.abs(DensityUtil.px2dip(getContext(), deltaX)) < 5 && Math.abs(DensityUtil.px2dip(getContext(), deltaY)) < 5) {
//                    //点击事件
//                    Log.i(TAG, "小距离的滑动不进行拦截");
//                    intercepted = false;
//                }
                else {
                    //自己就是垂直的
                    intercepted = true;
                }
                Log.i(TAG, "deltaX:" + deltaX + ",dy:" + deltaY);
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
        }
      /*  else {

            Log.i(TAG, "滑动未必完毕");
        }*/
    }


    public void show() {
        Log.i(TAG, "show");
        //租后的左边和定边
        if (mDragger.smoothSlideViewTo(mCoverView, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            if (onPageChangeListener != null) {
                onPageChangeListener.onShow();
            }
        }
    }

    public void hidden() {
        Log.i(TAG, "hidden");
        if (mDragger.smoothSlideViewTo(mCoverView, 0, mSize)) {
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            if (onPageChangeListener != null) {
                onPageChangeListener.onHidden();
            }
        }
    }

    public interface OnPageChangeListener {
        void onShow();

        void onHidden();
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    public boolean isShow() {
        return mCoverView.getTop() < mSize;
    }

    OnPageChangeListener onPageChangeListener = null;

    enum SHOW_STATE {
        STATE_OPEN, STATE_CLOSE
    }

    enum DRAG_STATE {
        DRAGING, WAIT_DRAG
    }

    DRAG_STATE dragstate = DRAG_STATE.WAIT_DRAG;
}
