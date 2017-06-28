package cn.qssq666.progressbar;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by luozheng on 2016/3/4. 有一个坑 就是你不show就设置title就会出现问题，findbyid为空因为create没调用
 */
public class IOSDialogProgress extends LoadingDialog {

    private TextView tvTitle;
    private CharSequence title = "加载中";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvTitle = (TextView) IOSDialogProgress.this.findViewById(R.id.tips_loading_msg);
        tvTitle.setText(title + "");
    }

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public IOSDialogProgress(Context context) {
        super(context, R.layout.view_loading_progress_ios_style);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        getWindow().set
//        <style name="dialog" parent="@android:style/Theme.Dialog">
//        <item name="android:windowFrame">@null</item><!--边框-->
//        <item name="android:windowIsFloating">true</item><!--是否浮现在activity之上-->
//        <item name="android:windowIsTranslucent">false</item><!--半透明-->
//        <item name="android:windowNoTitle">true</item><!--无标题-->
//        <item name="android:windowBackground">@color/transparent</item><!--背景透明-->
//        <item name="android:backgroundDimEnabled">false</item><!--模糊-->
    }

    //    @Override
    public void setMessage(CharSequence message) {
        setTitle(message);
    }

    @Override
    public void setTitle(final CharSequence title) {
        IOSDialogProgress.this.title = title;
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    public void dismissDelayed(long duration) {
        dismissDelayed(duration, null);
    }

    /**
     * 默认2秒
     */
    public void dismissDelayed(){
        dismissDelayed(2000,null);
    }
    public void dismissDelayed(final CallBack callBack){
        dismissDelayed(2000,callBack);
    }

    /**
     * 47} not attached to window manager
     *
     * @param duration
     * @param callBack
     */
    public void dismissDelayed(long duration, final CallBack callBack) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
                if (callBack != null) {
                    callBack.onCallBack();
                }
            }
        }, duration);
    }

    public interface CallBack {
        void onCallBack();
    }
}
