package example.hope.mvpwithrecyclerview.view;

import java.util.List;

import example.hope.mvpwithrecyclerview.model.Picture;

/**
 * Created by hope on 15-11-16.
 */
public interface PictureView extends {
    void showProgressBar();
    void hideProgressBar();
    void showMsg(String msg);
    void showPictures(List<Picture> pictures);
}
