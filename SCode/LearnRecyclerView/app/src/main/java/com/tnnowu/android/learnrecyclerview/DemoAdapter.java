package com.tnnowu.android.learnrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tnno Wu on 2017/04/24.
 */

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {

    private onRecyclerViewItemClick mOnRvItemClick;

    private Context mContext;

    String[] strings;

    public DemoAdapter(Context ctx, String[] strings, onRecyclerViewItemClick onRvItemClick) {
        mContext = ctx;
        this.mOnRvItemClick = onRvItemClick;
        this.strings = strings;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_main_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.textView)
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setData(int position) {
            textView.setText("第" + position + "行");
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
