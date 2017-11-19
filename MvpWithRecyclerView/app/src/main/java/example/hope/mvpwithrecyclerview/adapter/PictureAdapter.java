package example.hope.mvpwithrecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import example.hope.mvpwithrecyclerview.R;
import example.hope.mvpwithrecyclerview.model.Picture;
import example.hope.mvpwithrecyclerview.presenter.OnRecyclerItemClickListener;

/**
 * Created by hope on 15-11-16.
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureHolder> {

    private OnRecyclerItemClickListener mListener;
    private List<Picture> mPictures;

    public PictureAdapter(List<Picture> pictures) {
        this.mPictures = pictures;
    }

    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public PictureHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_picture, parent, false);
        return new PictureHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureHolder pictureHolder, int i) {
        Picture picture = mPictures.get(i);
        pictureHolder.ivPicture.setImageResource(picture.getImgSrc());
        pictureHolder.tvName.setText(picture.getName());
    }

    @Override
    public int getItemCount() {
        return mPictures.size();
    }

    public class PictureHolder extends RecyclerView.ViewHolder {
        ImageView ivPicture;
        TextView tvName;

        public PictureHolder(View itemView) {
            super(itemView);
            ivPicture = (ImageView) itemView.findViewById(R.id.img_pic);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

}
