package com.sjq.recycletest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        data = new String[]{"1231","123","123","123","123","123","123","123","123","123","123","432","523","45","234","2342", "2342", "324","234","234234"};

        // 具有点击功能的 1
       /* mAdapter = new MyAdapter(data, new MyAdapter.onRecyclerViewItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(MainActivity.this, "第" + position + "行", Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);*/

        // 具有点击功能的 2
       /* mAdapter = new AnotherAdapter(data, new AnotherAdapter.OnItemClickListener(){
            @Override
            public void onItemClick (View view , int position){
                Toast.makeText(MainActivity.this, data[position],Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setAdapter(mAdapter);*/

        mAdapter = new TwoAdapter(data);
        mRecyclerView.setAdapter(mAdapter);
    }

}
