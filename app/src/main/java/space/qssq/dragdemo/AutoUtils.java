package space.qssq.dragdemo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

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
}
