package cn.qssq666.swipemenu.test.misc;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ccccc on 15/11/2.
 */
public abstract  class BaseFragment extends Fragment {
    protected View mView;
    protected boolean mInit;
    protected Context mContext;
    public  final String TAG =this.getClass().getSimpleName() ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        if(mView==null){
            this.mView = inflater.inflate(getViewLayout(), container,false);
        }


        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        if(!mInit){
            mInit=true;
            mContext=getActivity();
            init();
        }
    }

    public abstract  int getViewLayout();
    public abstract  void init();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mView!=null){
            ViewGroup viewGroup= ((ViewGroup) mView.getParent());
            if(viewGroup!=null){

            viewGroup.removeView(mView);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();//http://dev.umeng.com/analytics/android-doc/integration
    }
    public void addTemplete(){
        //fragment也回调用它
    }
}
