package cn.qssq666.slidemenutest.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import cn.qssq666.slidemenutest.R;


/**
 * Created by qssq on 2017/6/27 qssq666@foxmail.com
 */

public class TestVerticalPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_vertical_page);
        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestVerticalPageActivity.this, "you click me", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
