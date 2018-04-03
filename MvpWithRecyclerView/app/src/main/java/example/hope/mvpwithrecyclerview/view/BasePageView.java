package example.hope.mvpwithrecyclerview.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import example.hope.mvpwithrecyclerview.R;
import example.hope.mvpwithrecyclerview.adapter.PictureAdapter;
import example.hope.mvpwithrecyclerview.model.Picture;
import example.hope.mvpwithrecyclerview.presenter.OnRecyclerItemClickListener;
import example.hope.mvpwithrecyclerview.presenter.PicturePresenter;

/**
 * Created by shenjiaqi on 2018/3/11.
 */

public class BasePageView extends FrameLayout implements PictureView {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgress;

    private PictureAdapter mAdapter;
    private PicturePresenter mPresenter;
    private Context mContext;

    /**
     * 构造函数。
     */
    public BasePageView(Context context){
        this(context, null);
    };

    public BasePageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BasePageView(Context context, AttributeSet attributeSet, int defStyleAttr){
        super(context, attributeSet, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     */
    public void init(Context context) {
        mContext = context;
        inflate(mContext, R.layout.base_view_layout, this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mProgress = (ProgressBar) findViewById(R.id.progress_bar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void showProgressBar() {
        mProgress.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgress.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPictures(List<Picture> pictures) {
        mAdapter = new PictureAdapter(pictures);
        mAdapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                showMsg(pos + "");
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }


}
