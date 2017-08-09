package com.sjq.recycletest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

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
        //创建并设置Adapter
        mAdapter = new MyAdapter(new String[]{"1231","432","523","45","234","2342", "2342", "324","234","234234"});
        mRecyclerView.setAdapter(mAdapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public String[] datas = null;
        private final int EMPTY_VIEW = 1;
        private final int PROGRESS_VIEW = 2;
        public MyAdapter(String[] data) {
            datas = data;
        }
        //创建新View，被LayoutManager所调用
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            if (viewType == EMPTY_VIEW) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
                ImageHolder vh = new ImageHolder (view);
                return vh;
            }else{
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item1, viewGroup, false);
                ColorHolder vh = new ColorHolder(view);
                return vh;
            }
        }

        public int getItemViewType(int position) {
            if (position % 2 == 0){
                return EMPTY_VIEW;
            }
            return PROGRESS_VIEW;
        }

        //将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            if (viewHolder instanceof ImageHolder) {
                Toast.makeText(MainActivity.this, datas[position], Toast.LENGTH_SHORT).show();
                ((ImageHolder)viewHolder).mTextView.setText(datas[position]);
            }else {
                ((ColorHolder)viewHolder).mTextView.setText(datas[position]);
            }
        }
        //获取数据的数量

        public int getItemCount() {
            return datas.length;
        }
        //自定义的ViewHolder，持有每个Item的的所有界面元素
        public  class ImageHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ImageHolder(View view){
                super(view);
                mTextView = (TextView) view.findViewById(R.id.text);
            }
        }

        public  class ColorHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ColorHolder(View view){
                super(view);
                mTextView = (TextView) view.findViewById(R.id.text);
            }
        }
    }
}
