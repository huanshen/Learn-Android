package com.sjq.myviewtest.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shenjiaqi on 2017/8/22.
 * 使用 view 的 scrollBy 来移动 View 的内容，手指移动多少，就移动多少
 */

public class MyView2 extends ViewGroup {
    private int mStart;
    private int mLastX, mLastY;

    public  MyView2(Context context){
        this(context, null);
    }
    public  MyView2(Context context, AttributeSet attributeSet){
        this(context,attributeSet, 0);
    }
    public MyView2(Context context, AttributeSet attributeSet, int defStyle){
        super(context, attributeSet,defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        //return super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mStart = getScrollY();

                break;
            case MotionEvent.ACTION_MOVE:
                //this.scrollBy(0,100);
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.i("scroll", "x   "+deltaX);
                Log.i("scroll", "y   "+deltaY);
                this.scrollBy(0,deltaY);
                break;
            case MotionEvent.ACTION_UP:
                mStart = getScrollY();

                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    // 还得考虑 padding
    // 不过根据实践得知，padding 是已经计算在内呢。
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
                final int childPaddingTop = childView.getPaddingTop();
                int padding = childPaddingBottom+childPaddingTop;
                childView.layout( 0, childLeft, childWidth,childHeight+childLeft+padding);
                childLeft += childHeight+padding;
                Log.i("padding", "top: "+childPaddingTop+"; bottom : "+ childPaddingBottom+ "; height: "+childHeight);

            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

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
}
