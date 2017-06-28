package cn.qssq666.slidemenu.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import cn.qssq666.slidemenu.test.utils.AutoUtils;
import cn.qssq666.slidemenu.R;

public class VerticalSrollNestedVerticalConfictActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_sroll_nested_vertical_confict);
        AutoUtils.initTestVerticalData((RecyclerView) findViewById(R.id.recycler_view));
        AutoUtils.initTestHorzontalData((RecyclerView) findViewById(R.id.recycler_view_horzontal));
    }

}
