package com.sjq.myviewtest.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shenjiaqi on 2017/8/22.
 */

public class Vgroup extends ViewGroup {
    public  Vgroup(Context context){
        this(context, null);
    }
    public  Vgroup(Context context, AttributeSet attributeSet){
        this(context,attributeSet, 0);
    }
    public Vgroup(Context context, AttributeSet attributeSet, int defStyle){
        super(context, attributeSet,defStyle);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int childLeft = 0;
        final int childCount = getChildCount();
        for (int j=0; j < childCount; j++){
            final View childView = getChildAt(j);
            if(childView.getVisibility()!=View.GONE){
                final int childWidth = childView.getMeasuredWidth();
                final int childHeight = childView.getMeasuredHeight();
                final int childPaddingBottom = childView.getPaddingBottom();
                childView.layout( 0, childLeft, childWidth,childHeight+childLeft);
                childLeft += childHeight;

            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        int measuredWidth = 0;
        int measureHeight = 0;

        final int childCount = getChildCount();

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        if (childCount == 0){
            setMeasuredDimension(0, 0);
        } else {
            for (int i = 0; i < childCount; ++i) {
                View childView = getChildAt(i);
                measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        p.setColor(Color.BLUE);
        Rect rect = new Rect(0,0,200,200);
        canvas.drawRect(rect, p);
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(20);
        p.setColor(Color.GRAY);
        canvas.drawLine(0,0,100,100,p);
        canvas.save();
        canvas.translate(100,100);
        canvas.restore();

        p.setColor(Color.RED);

        canvas.drawCircle(100,100,100,p);
        canvas.restore();
        canvas.drawCircle(150,150,50,p);
    }
}
