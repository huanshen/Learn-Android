package com.sjq.myviewtest.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.sjq.myviewtest.R;

/**
 * Created by shenjiaqi on 2017/8/22.
 */

public class MyView extends View {
    private int color;

    public MyView (Context context){
        this(context, null);

    }

    public MyView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        color = a.getColor(R.styleable.CircleView_circle_color, Color.GRAY);
        a.recycle();

    }

    public void onDraw(Canvas canvas){
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLUE);
        canvas.drawLine(0,0,100,100,p);
        canvas.save();
        canvas.translate(100,100);
        canvas.restore();
        Rect rect = new Rect(0,0,200,200);
        canvas.drawRect(rect, p);
        p.setColor(Color.RED);

        canvas.drawCircle(100,100,100,p);
        canvas.restore();
        p.setColor(color);
        canvas.drawCircle(150,150,50,p);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);


        if(widthSpecMode == MeasureSpec.AT_MOST ){
            widthSpecSize = 200;
        }
        if(heightSpecMode == MeasureSpec.AT_MOST ){
            heightSpecSize = 200;
        }

        setMeasuredDimension(widthSpecSize, heightSpecSize);

    }
}
