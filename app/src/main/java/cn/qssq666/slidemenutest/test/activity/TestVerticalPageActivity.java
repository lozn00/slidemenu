package cn.qssq666.slidemenutest.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.qssq666.slidemenutest.R;
import cn.qssq666.slidemenutest.test.utils.AutoUtils;


/**
 * Created by qssq on 2017/6/27 qssq666@foxmail.com
 */

public class TestVerticalPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_vertical_page);
        AutoUtils.initTestClick(findViewById(R.id.btn_click));
    }
}
