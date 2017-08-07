package cn.qssq666.slidemenutest.test.nested;

import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by qssq on 2017/6/27 qssq666@foxmail.com
 *
 * 事件分发是这样的：子View首先得到事件处理权，处理过程中，父View可以对其拦截，但是拦截了以后就无法再还给子View（本次手势内）。
 NestedScrolling机制是这样的：内部View在滚动的时候，首先将dx,dy交给NestedScrollingParent，NestedScrollingParent可对其进行部分消耗，剩余的部分还给内部View
 */

public class ParentImpl implements NestedScrollingParent {
    private static final String TAG = "ParentImpl";
    private NestedScrollingParentHelper parentHelper;

    public ParentImpl(ViewGroup group) {
        parentHelper=new NestedScrollingParentHelper(group);
    }

    @Override
    /**
     * 一定要按照自己的需求返回true，该方法决定了当前控件是否能接收到其内部View(非并非是直接子View)滑动时的参数；
     * 假设你只涉及到纵向滑动，这里可以根据nestedScrollAxes这个参数，进行纵向判断
     * 我们判断了如果是纵向返回true，这个一般是需要内部的View去传入的，你要是不确定，或者担心内部View编写的不规范，你可以直接return true；
     */

    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.w(TAG,"onStartNestedScroll target "+target+"child " +child+" nestedScrollAxes:"+nestedScrollAxes);
        return false;
//        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0; //垂直滚动
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        Log.w(TAG,"onNestedScrollAccepted target "+target+" nestedScrollAxes:"+nestedScrollAxes);


    }

    @Override
    public void onStopNestedScroll(View target) {
        Log.w(TAG,"onStopNestedScroll target "+target);

    }


    /**
     * 你可以捕获对内部View的fling事件，如果return true则表示拦截掉内部View的事件。
     * @param target
     * @param velocityX
     * @param velocityY
     * @param consumed
     * @return
     */
    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.w(TAG,"onNestedFling target "+target+", velocityX:"+velocityX+",velocityY "+velocityY+",consumed："+consumed);
        return false;
    }
    /*

     */
    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.w(TAG, "onNestedPreFling target " + target + ", velocityX:" + velocityX + ",velocityY " + velocityY);

        return false;

 /*       if (getScrollY() >= mTopViewHeight) return false;
        fling((int) velocityY);
        return true;
    }*/
    }



    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.w(TAG,"onNestedScroll target "+target+", dxConsumed:"+dxConsumed+",dyConsumed "+dyConsumed+", dxUnconsumed:"+dxUnconsumed+",dyUnconsumed:"+dxUnconsumed);

    }

    /*

     onNestedPreScroll该方法的会传入内部View移动的dx,dy，如果你需要消耗一定的dx,dy，就通过最后一个参数consumed进行指定，例如我要消耗一半的dy，就可以写consumed[1]=dy/2

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
        Log.w(TAG,"onNestedPreScroll dx "+dx+", dy:"+dy+",consumed x:"+consumed[0]+", y:"+consumed[1]);
  /*      boolean hiddenTop = dy > 0 && getScrollY() < mTopViewHeight;
        boolean showTop = dy < 0 && getScrollY() > 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop || showTop)
        {
            scrollBy(0, dy);
            consumed[1] = dy;
            我们判断，如果是上滑且顶部控件未完全隐藏，则消耗掉dy，即consumed[1]=dy;如果是下滑且内部View已经无法继续下拉，则消耗掉dy，即consumed[1]=dy，消耗掉的意思，就是自己去执行scrollBy，实际上就是我们的StickNavLayout滑动。
        }*/
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
     * @seeViewCompat#SCROLL_AXIS_HORIZONTAL
     * @seeViewCompat#SCROLL_AXIS_VERTICAL
     * @seeViewCompat#SCROLL_AXIS_NONE
     */
    @Override
    public int getNestedScrollAxes(){
        return parentHelper.getNestedScrollAxes();
    }

}
