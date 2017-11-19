package example.hope.mvpwithrecyclerview.presenter;

/**
 * Created by hope on 15-11-16.
 */
public interface PicturePresenter {
    void onResume();
    void onDestroy();
    void onItemClick(int pos);
}
