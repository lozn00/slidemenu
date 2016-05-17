package space.qssq.dragdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    //    private ViewPager mViewPager;
    private LiveViewGroup liveViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ListView listView = (ListView) findViewById(R.id.listview);
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("Nihao");
//        strings.add("Nihao");
//        strings.add("Nihao");
//        strings.add("Nihao");
//        strings.add("Nihao");
//        strings.add("Nihao");
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
//        listView.setAdapter(adapter);
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
//        });
        findViewById(R.id.tv1).setOnClickListener(this);
        findViewById(R.id.tv2).setOnClickListener(this);
        findViewById(R.id.tv3).setOnClickListener(this);
//        final LoopVerticalHorizontalPager pager = (LoopVerticalHorizontalPager) findViewById(R.id.looppager);
////        mViewPager = (ViewPager) findViewById(R.id.viewpager);
//        pager.setOnPageChangeListener(new LoopVerticalHorizontalPager.OnPageChangeListener() {
//            @Override
//            public void onPageNext() {
//                pager.resetByNext();
//            }
//
//            @Override
//            public void onPagePre() {
//                pager.resetByPre();
//            }
//
//            @Override
//            public void onViewShow() {
//
//            }
//
//            @Override
//            public void onViewHidden() {
//
//            }
//        });
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

    @Override
    public void onClick(View v) {
        value = !value;
        switch (v.getId()) {
            case R.id.tv1:
//                liveViewGroup.setCurrentView(LiveViewGroup.CURRENT_VIEW.VIEW_ONE, value);
                break;
            case R.id.tv2:
//                liveViewGroup.setCurrentView(LiveViewGroup.CURRENT_VIEW.VIEW_TWO, value);
                break;
            case R.id.tv3:
//                liveViewGroup.setCurrentView(LiveViewGroup.CURRENT_VIEW.VIEW_THERE, value);
                break;
        }
    }

    boolean value = false;
}
