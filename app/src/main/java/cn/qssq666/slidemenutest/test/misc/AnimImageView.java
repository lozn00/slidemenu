package cn.qssq666.slidemenutest.test.misc;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by luozheng on 2016/5/20.  qssq.space
 */
public class AnimImageView extends ImageView {
    public AnimImageView(Context context) {
        super(context);
    }

    public AnimImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 为什么不用进度条 因为 android progress bar在不同版本产生bug.除非用不同的anmin
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Drawable drawable = this.getDrawable();
        if (drawable != null && drawable instanceof Animatable) {
            AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
            animationDrawable.start();
        }
    }
}
