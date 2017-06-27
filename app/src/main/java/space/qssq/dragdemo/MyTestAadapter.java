package space.qssq.dragdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by qssq on 2017/6/27 qssq666@foxmail.com
 */

public class MyTestAadapter extends RecyclerView.Adapter<TestViewHolder> {
        ArrayList<String> strings=new ArrayList<>();

    public MyTestAadapter() {
        int count=50;
        for (int i = 0; i < count; i++) {
        strings.add("測試數據"+i);

        }
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false));
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {
                holder.textView.setText("你好世界!"+strings.get(position));

    }

    @Override
    public int getItemCount() {
        return strings.size();
    }
}
