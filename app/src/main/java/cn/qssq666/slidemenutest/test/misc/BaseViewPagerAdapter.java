package cn.qssq666.slidemenutest.test.misc;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by luozheng on 15/12/30.
 */
public abstract class BaseViewPagerAdapter extends PagerAdapter {


    public BaseViewPagerAdapter(ArrayList<View> mItems) {
        this.mItems = mItems;
    }

    public BaseViewPagerAdapter() {
    }

    public ArrayList<View> getItems() {
        return mItems;
    }


    public void setItems(ArrayList<View> items) {
        mItems = items;
    }

    public ArrayList<View> mItems;

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {

        return arg0 == arg1;
    }

    @Override
    public int getCount() {

        return mItems == null ? 0 : mItems.size();
        // return items.size();//刚开始没设置所以是空指针的
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = getView(container, position);
        container.addView(view);
        return view;

    }

    public abstract View getView(ViewGroup container, int position);
}
