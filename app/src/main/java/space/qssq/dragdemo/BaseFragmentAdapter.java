package space.qssq.dragdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by luozheng on 15/12/30.
 */

public class BaseFragmentAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = "BaseFragmentAdapter";
    ArrayList<BaseFragment> mViewList;

    public BaseFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public BaseFragmentAdapter(FragmentManager fm, ArrayList<BaseFragment> viewList) {
        super(fm);
        this.mViewList = viewList;
    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
//    }

    public ArrayList<BaseFragment> getViewList() {
        return mViewList;
    }

    public void setViewList(ArrayList<BaseFragment> mViewList) {
        this.mViewList = mViewList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        BaseFragment baseFragment = mViewList.get(position % mViewList.size());
//        if(baseFragment.isAdded()){
//            return baseFragment;
//        }
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        int realPosition = position % mViewList.size();
        Log.i(TAG,"");
        return mViewList.get(realPosition);

    }

    @Override
    public int getCount() {
        return mViewList == null ? 0 : Integer.MAX_VALUE;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}

