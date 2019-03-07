package com.androideasy.adapter.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.androideasy.adapter.EasyRecyclerAdapter;
import com.androideasy.adapter.OnItemClickListener;
import com.androideasy.adapter.holder.EasyViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private List<String> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView base_recycler = findViewById(R.id.base_recycler);
        base_recycler.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add("测试数据" + i);
        }

        EasyRecyclerAdapter<String> adapter = new EasyRecyclerAdapter<String>(this, mList) {
            @Override
            public int getLayoutId() {
                return R.layout.item_title;
            }

            @Override
            public void bindViewHolder(EasyViewHolder holder, String title, int position) {
                holder.setText(R.id.text_title, title);
            }
        };


        adapter.setOnItemClickListener(new OnItemClickListener<String>() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, String s, int position) {
                Log.i(TAG ,"点击了："+s);
            }
        });
        base_recycler.setAdapter(adapter);
    }
}
