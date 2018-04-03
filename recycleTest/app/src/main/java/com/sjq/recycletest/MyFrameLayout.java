package com.sjq.recycletest;

/**
 * Created by shenjiaqi on 2018/3/27.
 */


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MyFrameLayout extends FrameLayout implements OnClickListener, OnTouchListener {

    private static final String TAG = "Event";

    public MyFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        Log.d(TAG, "MyFrameLayout init");
        setOnClickListener(this);
        setOnTouchListener(this);

        ImageView imageView = new ImageView(getContext());
        imageView.setPadding(15, 15, 0, 15);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setTag(R.id.myView_my);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));

        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "imageView onClick");
            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "imageView onTouch");

                return false;
            }
        });




        addView(imageView);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyFrameLayout dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyFrameLayout onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        Log.d(TAG, "MyFrameLayout onClick");
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // TODO Auto-generated method stub
        Log.d(TAG, "MyFrameLayout onTouch");
        return true;
    }
}
