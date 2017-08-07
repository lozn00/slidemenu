package cn.qssq666.slidemenutest.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import cn.qssq666.slidemenutest.R;
import cn.qssq666.slidemenutest.test.utils.AutoUtils;

public class HorzontalSrollConfictActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horzontal_sroll_confict);
        AutoUtils.initTestVerticalData((RecyclerView) findViewById(R.id.recycler_view));
    }
}
