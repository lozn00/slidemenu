package cn.qssq666.slidemenutest.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import cn.qssq666.slidemenutest.R;
import cn.qssq666.slidemenutest.test.utils.AutoUtils;

public class VerticalSrollNestedVerticalConfictActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_sroll_nested_vertical_confict);
        AutoUtils.initTestVerticalData((RecyclerView) findViewById(R.id.recycler_view));
        AutoUtils.initTestHorzontalData((RecyclerView) findViewById(R.id.recycler_view_horzontal));
        AutoUtils.initTestVerticalData((RecyclerView) findViewById(R.id.recycler_view_vertical_top));
        AutoUtils.initTestClick(findViewById(R.id.btn_click));
    }
    //return 0则 全屏点击view 无法滑动 设置为1则recyclerview 无法滚动。

}
