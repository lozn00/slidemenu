package space.qssq.dragdemo.nested;

import android.support.v4.view.NestedScrollingChild;
import android.util.Log;

/**
 * Created by qssq on 2017/6/27 qssq666@foxmail.com
 */

public class ChildImpl implements NestedScrollingChild {
    private static final String TAG = "ChildImpl";

    @Override

    public void setNestedScrollingEnabled(boolean enabled) {
        Log.w(TAG,"setNestedScrollingEnabled enabled "+enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        Log.w(TAG,"isNestedScrollingEnabled  ");
        return false;
    }

    @Override
    public boolean startNestedScroll(int axes) {
        Log.w(TAG,"startNestedScroll axes "+axes);
        return false;
    }

    @Override
    public void stopNestedScroll() {
        Log.w(TAG,"stopNestedScroll  ");
    }

    @Override
    public boolean hasNestedScrollingParent() {

        Log.w(TAG,"hasNestedScrollingParent");
        return false;
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        Log.w(TAG,"dispatchNestedScroll dxConsumed "+dxConsumed+" dyConsumed:"+dyConsumed+","+" dxUnconsumed:"+dxUnconsumed+",dyUnconsumed "+dyUnconsumed+","
                +" offsetInWindow:" +offsetInWindow[0]+","+offsetInWindow[1]);
        return false;
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        Log.w(TAG,"dispatchNestedPreScroll dx "+dx+" consumed:"+consumed   +" offsetInWindow:" +offsetInWindow[0]+","+offsetInWindow[1]);
        return false;

    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        Log.w(TAG,"dispatchNestedFling velocityX "+velocityX+" velocityY:"+velocityY+" velocityY:"+velocityY+" consumed:"+consumed);
        return false;
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        Log.w(TAG,"dispatchNestedPreFling velocityX "+velocityX+" velocityY:"+velocityY);
        return false;
    }
}
