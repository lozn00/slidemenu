package space.qssq.dragdemo;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import space.qssq.progressbar.IOSDialogProgress;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    //    private ViewPager mViewPager;
    private LiveViewGroup liveViewGroup;
    private LoopVerticalHorizontalPager mViewPager;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IOSDialogProgress iosDialogProgress = new IOSDialogProgress(this);
        iosDialogProgress.show();
        iosDialogProgress.setTitle("ffff");
        iv = ((ImageView) findViewById(R.id.iv));
        AnimationDrawable animationDrawable = (AnimationDrawable) iv.getDrawable();
        animationDrawable.start();
        TextView textView = (TextView) findViewById(R.id.tv);
        if (PhoneUtils.checkDeviceHasNavigationBar((this))) {
            Snackbar.make(iv, "您的手机是bushi", Snackbar.LENGTH_LONG).show();
            textView.setText("您的手机是bushi");

        } else {
            Snackbar.make(iv, "您的手机是虚拟导航栏,如果想想切换至完全全屏模式,请长按返回键", Toast.LENGTH_LONG).show();
            textView.setText("您的手机是虚拟导航栏,如果想想切换至完全全屏模式,请长按返回键");
        }
//        ListView listView = (ListView) findViewById(R.id.listview);
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("Nihao");
//        strings.add("Nihao");
//        strings.add("Nihao");
//        strings.add("Nihao");
//        strings.add("Nihao");
//        strings.add("Nihao");
//        strings.add("Nihao");
//        strings.add("Nihao");
//        strings.add("Nihao");
//        strings.add("Nihao");
//        strings.add("Nihao");
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
////        });
//        findViewById(R.id.tv1).setOnClickListener(this);
//        findViewById(R.id.tv2).setOnClickListener(this);
//        findViewById(R.id.tv3).setOnClickListener(this);
//        final LoopVerticalHorizontalPager pager = (LoopVerticalHorizontalPager) findViewById(R.id.looppager);
//        mViewPager = (LoopVerticalHorizontalPager) findViewById(R.id.looppager);
//        mViewPager.setOnPageChangeListener(new LoopVerticalHorizontalPager.OnPageChangeListener() {
//            @Override
//            public void onPageNext() {
//                mViewPager.resetByNext();
//                Log.i(TAG, "onPageNext");
//            }
//
//            @Override
//            public void onPagePre() {
//                mViewPager.resetByPre();
//                Log.i(TAG, "onPagePre");
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
////        final ArrayList<BaseFragment> mViewList = new ArrayList<>();
////        mViewList.add(new Fragment1());
////        mViewList.add(new Fragment2());
////        mViewList.add(new Fragment3());
//        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//        final ArrayList<View> viewList = new ArrayList<>();
//        View view1 = ViewFactory.getView1(this, viewPager);
//        viewList.add(view1);
//        View view2 = ViewFactory.getView2(this, viewPager);
//        viewList.add(view2);
//        setListAdapter(view2);
//        View view3 = ViewFactory.getView3(this, viewPager);
//        viewList.add(view3);
//
//        BaseViewPagerAdapter basePagerAdapter = new BaseViewPagerAdapter(viewList) {
//            @Override
//            public View getView(ViewGroup container, int position) {
//                return viewList.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return super.getCount();
//            }
//        };
//        viewPager.setAdapter(basePagerAdapter);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (position != 1) {
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            viewPager.setCurrentItem(1, false);
//
//                        }
//                    }, 1000);
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        viewPager.setCurrentItem(1, false);
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
//        int half = Integer.MAX_VALUE / 2;
//        int needPosition = 1;
//        int realPosition = half % mViewList.size();
//        while (realPosition != needPosition) {
//            if (realPosition > needPosition) {
//                --half;
//            } else {
//                ++half;
//            }
//            realPosition = half % mViewList.size();
//        }
//        Log.i(TAG, "real position:" + realPosition + ",position:" + half);
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
    Handler handler = new Handler();

    public void setListAdapter(View view) {
        ListView listView = (ListView) view.findViewById(R.id.listview);
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
    }
}
