package cn.qssq666.slidemenu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by qssq on 2017/8/7 qssq666@foxmail.com
 */

public class ClickLayout extends FrameLayout {
    public ClickLayout(Context context) {
        super(context);
    }

    public ClickLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClickLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

/*
    @Override
    public void setOnClickListener(@Nullable OnClickListener clickListener) {
        this.clickListener = clickListener;
    }
*/

    public boolean onInterceptTouchEvent(MotionEvent event) {
            return super.onInterceptTouchEvent(event);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
   /*     if (event.getAction() == MotionEvent.ACTION_UP) {
            if (clickListener != null) {
                clickListener.onClick(this);
            }
        }
        return super.dispatchTouchEvent(event);*/
    }


}
