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
import example.hope.mvpwithrecyclerview.view.BasePageView;
import example.hope.mvpwithrecyclerview.view.PictureView;

public class PictureFragment extends Fragment{

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
       BasePageView basePageView = (BasePageView) view.findViewById(R.id.baseView);

        mPresenter = new PicturePresenterImpl(basePageView);
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




}
