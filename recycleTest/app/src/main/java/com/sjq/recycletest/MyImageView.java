package com.sjq.recycletest;

/**
 * Created by shenjiaqi on 2018/3/27.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MyImageView extends ImageView implements OnClickListener, OnTouchListener {
    private static final String TAG = "Event";

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        Log.d(TAG, "MyImageView init");
        setOnClickListener(this);
        setOnTouchListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyImageView dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyImageView onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {
        // TODO Auto-generated method stub
        Log.d(TAG, "MyImageView onTouch");
        return false;
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        Log.d(TAG, "MyImageView onClick");
    }

}


