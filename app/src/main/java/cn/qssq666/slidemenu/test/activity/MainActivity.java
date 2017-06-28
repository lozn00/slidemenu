package cn.qssq666.slidemenu.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import cn.qssq666.slidemenu.test.misc.BaseFragment;
import cn.qssq666.slidemenu.LiveViewGroup;
import cn.qssq666.slidemenu.LoopVerticalHorizontalPager;
import cn.qssq666.slidemenu.R;
import cn.qssq666.slidemenu.test.misc.Fragment1;
import cn.qssq666.slidemenu.test.misc.Fragment2;
import cn.qssq666.slidemenu.test.misc.Fragment3;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    //    private ViewPager mViewPager;
    private LiveViewGroup liveViewGroup;
    private LoopVerticalHorizontalPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listview);
        findViewById(R.id.btn_vertical_scroll).setOnClickListener(this);
        findViewById(R.id.btn_horzontal_scroll).setOnClickListener(this);
        findViewById(R.id.btn_horzontall_scoll_confict).setOnClickListener(this);
        findViewById(R.id.btn_horzontal_scoll_horzontal_confict).setOnClickListener(this);
        findViewById(R.id.btn_vertical_scoll_confict).setOnClickListener(this);
        findViewById(R.id.btn_vertical_scoll_vertical_confict).setOnClickListener(this);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Nihao");
        strings.add("Nihao");
        strings.add("Nihao");
        strings.add("Nihao");
        strings.add("Nihao");
        strings.add("Nihao");
        strings.add("Nihao");
        strings.add("Nihao");
        strings.add("Nihao");
        strings.add("Nihao");
        strings.add("Nihao");
        strings.add("Nihao");
        strings.add("Nihao");
        strings.add("Nihao");
        strings.add("Nihao");
        strings.add("Nihao");
        strings.add("Nihao");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(adapter);
//        liveViewGroup = (LiveViewGroup) findViewById(R.id.liveView);
//        liveViewGroup.setOnPageChangeListener(new LiveViewGroup.onPageChangeListener() {
//            @Override
//            public void onPageNext() {
//                liveViewGroup.setCurrentView(LiveViewGroup.CURRENT_VIEW.VIEW_TWO, false);
//            }
//
//            @Override
//            public void onPagePre() {
//                liveViewGroup.setCurrentView(LiveViewGroup.CURRENT_VIEW.VIEW_TWO, false);
//            }
//
//            @Override
//            public void onPageCurrent() {
//
//            }
////        });
//        findViewById(R.id.tv1).setOnClickListener(this);
//        findViewById(R.id.tv2).setOnClickListener(this);
//        findViewById(R.id.tv3).setOnClickListener(this);
//        final LoopVerticalHorizontalPager pager = (LoopVerticalHorizontalPager) findViewById(R.id.looppager);
        mViewPager = (LoopVerticalHorizontalPager) findViewById(R.id.viewpager);
        mViewPager.setOnPageChangeListener(new LoopVerticalHorizontalPager.OnPageChangeListener() {
            @Override
            public void onPageNext() {
                mViewPager.resetByNext();
                Log.i(TAG, "onPageNext");
            }

            @Override
            public void onPagePre() {
                mViewPager.resetByPre();
                Log.i(TAG, "onPagePre");
            }

            @Override
            public void onViewShow() {

            }

            @Override
            public void onViewHidden() {

            }
        });
        final ArrayList<BaseFragment> mViewList = new ArrayList<>();
        mViewList.add(new Fragment1());
        mViewList.add(new Fragment2());
        mViewList.add(new Fragment3());
//        mViewPager.setAdapter(new BaseFragmentAdapter(this.getSupportFragmentManager(), mViewList));
//
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//
//        });
        int half = Integer.MAX_VALUE / 2;
        int needPosition = 1;
        int realPosition = half % mViewList.size();
        while (realPosition != needPosition) {
            if (realPosition > needPosition) {
                --half;
            } else {
                ++half;
            }
            realPosition = half % mViewList.size();
        }
        Log.i(TAG, "real position:" + realPosition + ",position:" + half);
//        mViewPager.setCurrentItem(half);
    }

//    @Override
//    public void onClick(View v) {
//        value = !value;
//        switch (v.getId()) {
//            case R.id.tv1:
////                liveViewGroup.setCurrentView(LiveViewGroup.CURRENT_VIEW.VIEW_ONE, value);
//                break;
//            case R.id.tv2:
////                liveViewGroup.setCurrentView(LiveViewGroup.CURRENT_VIEW.VIEW_TWO, value);
//                break;
//            case R.id.tv3:
////                liveViewGroup.setCurrentView(LiveViewGroup.CURRENT_VIEW.VIEW_THERE, value);
//                break;
//        }
//    }

    boolean value = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_vertical_scroll:
                Intent intent=new Intent(this,TestVerticalPageActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_horzontal_scroll:
                 intent=new Intent(this,TestHorzontalPageActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_horzontall_scoll_confict:
                 intent=new Intent(this,HorzontalSrollConfictActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_horzontal_scoll_horzontal_confict:
                 intent=new Intent(this,HorzontalSrollNestedHorzontalConfictActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_vertical_scoll_confict:
                intent=new Intent(this,VerticalSrollConfictActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_vertical_scoll_vertical_confict:
                intent=new Intent(this,VerticalSrollNestedVerticalConfictActivity.class);
                startActivity(intent);
                break;
        }
    }
}
