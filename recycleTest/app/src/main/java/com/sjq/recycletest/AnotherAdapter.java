package com.sjq.recycletest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by shenjiaqi on 2017/8/10.
 */

public class AnotherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private String[] datas = null;
    private final int EMPTY_VIEW = 1;
    private final int PROGRESS_VIEW = 2;
    private OnItemClickListener mOnItemClickListener = null;



    public AnotherAdapter(String[] data, OnItemClickListener listener) {
        datas = data;
        mOnItemClickListener = listener;
    }


    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }



    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }


    //创建新View，被LayoutManager所调用
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == EMPTY_VIEW) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
            ImageHolder vh = new ImageHolder (view);
            view.setOnClickListener(this);
            return vh;
        }else{
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item1, viewGroup, false);
            ColorHolder vh = new ColorHolder(view);
            view.setOnClickListener(this);
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
            //Toast.makeText(MainActivity.this, datas[position], Toast.LENGTH_SHORT).show();
            ((ImageHolder)viewHolder).mTextView.setText(datas[position]);
            viewHolder.itemView.setTag(position);
        }else {
            ((ColorHolder)viewHolder).mTextView.setText(datas[position]);
            viewHolder.itemView.setTag(position);
        }
    }
    //获取数据的数量

    public int getItemCount() {
        return datas.length;
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public  class ImageHolder extends RecyclerView.ViewHolder  {
        public TextView mTextView;
        public ImageHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);

        }

    }


    public  class ColorHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        LinearLayout rootView;
        public ColorHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);

        }


    }





}