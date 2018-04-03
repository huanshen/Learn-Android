package com.sjq.recycletest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by shenjiaqi on 2017/8/10.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String[] datas = null;
    private final int EMPTY_VIEW = 1;
    private final int PROGRESS_VIEW = 2;
    private onRecyclerViewItemClick mOnRvItemClick;
    private OnChildViewClickListener mClickListener;

    private MyView myView;


    public MyAdapter(String[] data, onRecyclerViewItemClick onRvItemClick) {
        datas = data;
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
            ((ImageHolder)viewHolder).mTextView.setText(datas[position]);
           // ((ImageHolder)viewHolder).myView1.update();

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
    public  class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;
        public MyView myView1;
        public ImageHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
            myView1 = (MyView) view.findViewById(R.id.myView_my);
         //   view.setOnTouchListener(TouchFactory.creatTouchListener());
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Toast.makeText(v.getContext(), "第  view" + "行", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
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

    /**
     * 该类模板提供给外部的回调监听类，模板触发了相应操作后，会回调相应接口。
     */
    public interface OnChildViewClickListener {
        /**
         * 模板的某个按钮的点击事件
         *
         * @param view 被用户点击的视图控件
         */
        void onClick(View view);
    }

    public void setOnChildViewClickListener(OnChildViewClickListener listener) {
        mClickListener = listener;
    }


}