package com.sjq.recycletest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
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
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        data = new String[]{"1231","432","523","45","234","2342", "2342", "324","234","234234"};

        //创建并设置Adapter
        mAdapter = new MyAdapter(data);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setClickListener(new MyAdapter.OnItemClickListener() {
            boolean flag = false;
            @Override
            public void onClick(View view, int position) {
                TextView tvName = (TextView) view.findViewById(R.id.text);
                String name = flag ? "hunao" : "china";
                tvName.setText(name + position);
                flag = !flag;
            }
        });




    }




}
