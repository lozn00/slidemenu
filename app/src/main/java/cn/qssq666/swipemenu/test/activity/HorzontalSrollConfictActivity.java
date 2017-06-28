package cn.qssq666.swipemenu.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import cn.qssq666.swipemenu.test.utils.AutoUtils;
import cn.qssq666.swipemenu.R;

public class HorzontalSrollConfictActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horzontal_sroll_confict);
        AutoUtils.initTestVerticalData((RecyclerView) findViewById(R.id.recycler_view));
    }
}
