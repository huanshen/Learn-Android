package example.hope.mvpwithrecyclerview.model;

/**
 * Created by hope on 15-11-16.
 */
public class Picture {
    private String name;
    private int imgSrc;

    public Picture(String name, int imgSrc) {
        this.name = name;
        this.imgSrc = imgSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }
}
