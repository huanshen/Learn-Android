package example.hope.mvpwithrecyclerview.presenter;

import java.util.List;

import example.hope.mvpwithrecyclerview.model.LoaderListener;
import example.hope.mvpwithrecyclerview.model.Picture;
import example.hope.mvpwithrecyclerview.model.PictureInteractor;
import example.hope.mvpwithrecyclerview.model.PictureInteractorImpl;
import example.hope.mvpwithrecyclerview.view.PictureView;

/**
 * Created by hope on 15-11-16.
 */
public class PicturePresenterImpl implements PicturePresenter, LoaderListener {

    private PictureView mPictureView;
    private PictureInteractor mInteractor;

    public PicturePresenterImpl(PictureView pictureView) {
        this.mPictureView = pictureView;
        mInteractor = new PictureInteractorImpl();
    }

    @Override
    public void onResume() {
        mPictureView.showProgressBar();
        mInteractor.loadPictures(this);
    }

    @Override
    public void onDestroy() {
        mPictureView = null;
    }

    @Override
    public void onItemClick(int pos) {
        mPictureView.showMsg(String.valueOf(pos));
    }

    @Override
    public void onFinish(List<Picture> pictures) {
        mPictureView.hideProgressBar();
        mPictureView.showPictures(pictures);
    }
}
