package example.hope.mvpwithrecyclerview.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import example.hope.mvpwithrecyclerview.R;
import example.hope.mvpwithrecyclerview.presenter.OnRecyclerItemClickListener;
import example.hope.mvpwithrecyclerview.adapter.PictureAdapter;
import example.hope.mvpwithrecyclerview.model.Picture;
import example.hope.mvpwithrecyclerview.presenter.PicturePresenter;
import example.hope.mvpwithrecyclerview.presenter.PicturePresenterImpl;
import example.hope.mvpwithrecyclerview.view.PictureView;

public class PictureFragment extends Fragment implements PictureView{

    private RecyclerView mRecyclerView;
    private ProgressBar mProgress;

    private PictureAdapter mAdapter;
    private PicturePresenter mPresenter;

    public static PictureFragment newInstance() {
        return new PictureFragment();
    }

    public PictureFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture, container, false);
        initView(view);

        mPresenter = new PicturePresenterImpl(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mProgress = (ProgressBar) view.findViewById(R.id.progress_bar);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPictures(List<Picture> pictures) {
        mAdapter = new PictureAdapter(pictures);
        mAdapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                mPresenter.onItemClick(pos);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    ON
}
