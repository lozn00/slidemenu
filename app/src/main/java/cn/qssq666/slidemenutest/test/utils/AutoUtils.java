package cn.qssq666.slidemenutest.test.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.qssq666.slidemenutest.test.adapter.MyTestAadapter;


/**
 * Created by qssq on 2017/6/27 qssq666@foxmail.com
 */

public class AutoUtils {
    public static void initTestVerticalData(RecyclerView recyclerView){
        initTestData(recyclerView, LinearLayout.VERTICAL);

    }
    public static void initTestHorzontalData(RecyclerView recyclerView){
        initTestData(recyclerView, LinearLayout.HORIZONTAL);

    }
    public static void initTestData(RecyclerView recyclerView,int orientation){
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),orientation,false));
        recyclerView.setAdapter(new MyTestAadapter());

    }

    public static void initTestClick(View viewById) {
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "你点击了我", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
