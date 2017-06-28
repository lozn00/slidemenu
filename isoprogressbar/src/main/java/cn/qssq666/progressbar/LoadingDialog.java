package cn.qssq666.progressbar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

/**
 * 加载中Dialog
 * 
 * @author 2016-3-4 17:35:21
 *
 * dialog = new LoadingDialog(this,  R.layout.view_tips_loading2);
dialog.setCancelable(false);
dialog.setCanceledOnTouchOutside(false);
dialog.show();
new Thread(new Runnable() {

@Override
public void run() {
try {
Thread.sleep(3000);
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
mHandler.sendEmptyMessage(1);
}
}).start();
break;
 */
public class LoadingDialog extends Dialog {

	private int layoutResId;

	/**
	 * 构造方法
	 * 
	 * @param context
	 *            上下文
	 * @param layoutResId
	 *            要传入的dialog布局文件的id
	 */
	public LoadingDialog(Context context, int layoutResId) {
		super(context, cn.qssq666.progressbar.R.style.dialog_ios_style);
		this.layoutResId=layoutResId;//必须在onCreate后面调用,否则报错。

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(layoutResId);
	}
}
