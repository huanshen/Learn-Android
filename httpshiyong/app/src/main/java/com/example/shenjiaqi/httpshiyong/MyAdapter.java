package com.example.shenjiaqi.httpshiyong;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shenjiaqi on 2017/8/10.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<FeedBaseModel> list;
    private final int EMPTY_VIEW = 1;
    private final int PROGRESS_VIEW = 2;
    private onRecyclerViewItemClick mOnRvItemClick;


    public MyAdapter(ArrayList<FeedBaseModel> feedBaseModelList, onRecyclerViewItemClick onRvItemClick) {
        list = feedBaseModelList;
        mOnRvItemClick = onRvItemClick;
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
            //Toast.makeText(MainActivity.this, datas[position], Toast.LENGTH_SHORT).show();
            ((ImageHolder)viewHolder).mTextView.setText(list.get(position).id);
            viewHolder.itemView.setTag(position);
        }else {
            ((ColorHolder)viewHolder).mTextView.setText(list.get(position).id);
            viewHolder.itemView.setTag(position);
        }
    }
    //获取数据的数量

    public int getItemCount() {
        return list.size();
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public  class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;
        public ImageHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnRvItemClick != null)
                mOnRvItemClick.onItemClick(view, getAdapterPosition());
        }
    }


    public  class ColorHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTextView;
        public ColorHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnRvItemClick != null)
                mOnRvItemClick.onItemClick(view, getAdapterPosition());
        }
    }


    /**
     * item点击接口
     */
    public interface onRecyclerViewItemClick {
        void onItemClick(View v, int position);
    }


}