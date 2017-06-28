package cn.qssq666.swipemenu.test.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by qssq on 2017/6/27 qssq666@foxmail.com
 */

public class TestViewHolder extends RecyclerView.ViewHolder
{

    public final TextView textView;

    public TestViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(android.R.id.text1);
    }
}
