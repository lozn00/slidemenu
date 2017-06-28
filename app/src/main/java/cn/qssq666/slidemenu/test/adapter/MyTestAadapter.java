package cn.qssq666.slidemenu.test.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import cn.qssq666.slidemenu.R;
import cn.qssq666.slidemenu.test.viewholder.TestViewHolder;

/**
 * Created by qssq on 2017/6/27 qssq666@foxmail.com
 */

public class MyTestAadapter extends RecyclerView.Adapter<TestViewHolder> {
        ArrayList<String> strings=new ArrayList<>();

    public MyTestAadapter() {
        int count=50;
        for (int i = 0; i < count; i++) {
        strings.add("测试数据"+i);

        }
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_text,parent,false));
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, final int position) {
                holder.textView.setText(""+strings.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "你点击了"+strings.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return strings.size();
    }
}
