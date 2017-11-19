package example.hope.mvpwithrecyclerview.model;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;

import example.hope.mvpwithrecyclerview.R;

/**
 * Created by hope on 15-11-16.
 */
public class PictureInteractorImpl implements PictureInteractor {

    private final static String[] pictureNames = {
            "Rocket in the universe",
            "A scene in London",
            "Moon over mountains",
            "A simple moon",
            "Sun and volcano",
            "A collection of mountains",
            "River between mountains",
            "Some pine trees",
            "On Small Town",
            "Volcanos reflection"
    };


    private final static int  pictureImages[] = {
            R.drawable.cohete_flat,
            R.drawable.london_flat,
            R.drawable.material_flat,
            R.drawable.moon_flat,
            R.drawable.mountain_flat,
            R.drawable.mountain_mo_flat,
            R.drawable.moutain_go_flat,
            R.drawable.pine_flat,
            R.drawable.towers_flat,
            R.drawable.vulcan_flat
    };

    @Override
    public void loadPictures(final LoaderListener listener) {
        new Handler(Looper.getMainLooper())
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFinish(createPictures());
                    }
                }, 2000);
    }

    private List<Picture> createPictures() {
        ArrayList<Picture> pictures = new ArrayList<>();
        for (int i = 0; i < pictureNames.length; i++) {
            pictures.add(new Picture(pictureNames[i], pictureImages[i]));
        }
        return pictures;
    }
}
