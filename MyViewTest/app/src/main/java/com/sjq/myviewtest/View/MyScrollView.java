package com.sjq.myviewtest.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by shenjiaqi on 2017/8/25.
 */

public class MyScrollView extends ViewGroup {

    private Scroller scroller;
    // 屏幕高度
    private int mScreenHeight;
    private int mLastY;
    private int mStart;
    private int mEnd;

    public MyScrollView (Context context){
        this(context,null);
    }
    public MyScrollView(Context context, AttributeSet attributeSet){
        this(context, attributeSet, 0);
    }
    public MyScrollView(Context context, AttributeSet attributeSet, int defStyle){
        super(context,attributeSet,defStyle);
        initView(context);
    }

    // 进行初始化
    public void initView (Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
        scroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int childLeft = 0;
        final int childCount = getChildCount();

        for (int j = 0; j < childCount; j++){

            final View childView = getChildAt(j);
            if(childView.getVisibility()!=View.GONE){

                final int childWidth = childView.getMeasuredWidth();
                final int childHeight = childView.getMeasuredHeight();
                childView.layout( 0, childLeft, childWidth,childHeight+childLeft);
                childLeft += childHeight;

            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int childCount = getChildCount();

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
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 获取点击的位置和上一次的偏移量
                mLastY = y;
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!scroller.isFinished()){
                    scroller.abortAnimation();
                }
                int dy = mLastY - y;

                // 如果小于0，就表示往上滑，但是，第一条位置不能超过，所以必须为0；
                if(getScrollY() < 0){
                    dy = 0;
                }
                // 当屏幕上的 view 小于 屏幕的高度的时候，就不能在滑动了。
                if (getScrollY() > getHeight() - mScreenHeight){
                    dy = 0;
                }
                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();
                // 得到的就是滑动的距离；
                // 还有一种方式是获取点击按钮按下和离开时候的事件；
                int dScrollY = mEnd - mStart;
                // 往下滑动
                if (dScrollY > 0){
                    // 这个场景是适合每次需要滑动一整页的地方，当滑动距离小于 1/3 的时候，就将原来滑动的滚回去。
                    if (dScrollY < mScreenHeight / 3){
                        // 所以这里是负数，就是要把之前滑动的，滚回去
                        scroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    }else{
                        // 当它超过之后，我们还要让他再继续滚动超过一屏距离即可
                        scroller.startScroll(0, getScrollY(), 0, mScreenHeight-dScrollY);
                    }
                }


        }
    }
}
