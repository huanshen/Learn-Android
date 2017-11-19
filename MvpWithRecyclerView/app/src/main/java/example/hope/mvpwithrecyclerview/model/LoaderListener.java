package example.hope.mvpwithrecyclerview.model;

import java.util.List;

/**
 * Created by hope on 15-11-16.
 */
public interface LoaderListener {
    void onFinish(List<Picture> pictures);
}
