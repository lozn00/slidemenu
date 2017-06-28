package cn.qssq666.swipemenu.test.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.qssq666.swipemenu.R;

/**
 * Created by luozheng on 2016/5/18.  qssq.space
 */

public class ViewFactory {

    public static View getView1(Context context, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.view_page_item_pre, viewGroup, false);
    }

    public static View getView2(Context context, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.view_page_item_play, viewGroup, false);

    }

    public static View getView3(Context context, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.view_page_item_next, viewGroup, false);

    }
}
