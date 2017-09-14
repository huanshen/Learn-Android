package com.example.shenjiaqi.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * 小说漫画推荐模板使用的item
 *
 * @author  shenjiaqi
 * @version v9.3.5
 * @since 2017/9/12
 */

public class FeedMultipleImageItemView extends RelativeLayout implements View.OnClickListener {

    /** 小说漫画的标题 */
    private TextView mTitle;
    /** 小说漫画的具体描述 */
    private TextView mDesc;
    /** 小说漫画的标题 */
    public ImageView mImage;

    /** 图片宽度 */
    private int mImageWidth = 800;
    /** 图片高度 */
    private int mImageHeight = 800;
    /** 视频的播放图标 */
    public ImageView mVideoPlayIcon;



    public FeedMultipleImageItemView(Context context) {
        this(context, null);
    }

    public FeedMultipleImageItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FeedMultipleImageItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        this.setOnClickListener(this);
        View view = LayoutInflater.from(this.getContext()).inflate(R.layout.feed_item_multiple_image, this);
        mImage = (ImageView) view.findViewById(R.id.feed_item_multiple_image_img);
        mDesc = (TextView) view.findViewById(R.id.feed_item_multiple_image_desc);
        mTitle = (TextView) view.findViewById(R.id.feed_item_multiple_image_title);


        // 设置播放图标
        mVideoPlayIcon = (ImageView) findViewById(R.id.feed_item_multiple_image_video_icon_id);
        mVideoPlayIcon.setImageResource(R.drawable.feed_video_play);

        Resources resources = context.getResources();


        // 设置图片的高度和宽度
        LayoutParams mImageLP = (LayoutParams) mImage.getLayoutParams();
        mImageLP.width = 800;
        mImageLP.height = 800;
        mImage.setLayoutParams(mImageLP);

        LayoutParams mTitleLP = (LayoutParams) mTitle.getLayoutParams();
        LayoutParams  mVideoPlayIconLP = (LayoutParams) mVideoPlayIcon.getLayoutParams();

        mVideoPlayIconLP.bottomMargin =  (mImageHeight - mVideoPlayIcon.getDrawable().getIntrinsicHeight()) / 2 ;
        mVideoPlayIconLP.topMargin =  (mImageHeight - mVideoPlayIcon.getDrawable().getIntrinsicHeight()) / 2 ;
        mVideoPlayIcon.setLayoutParams(mVideoPlayIconLP);
        Log.i("LP++++", mVideoPlayIconLP.height + "");
        Log.i("LP++++", mVideoPlayIconLP.topMargin + "");
        Log.i("LP++++", mImageLP.toString());
        Log.i("LP+++", mVideoPlayIcon.getDrawable().getIntrinsicHeight() + "");
    }




    @Override
    public void onClick(View view) {


    }
}