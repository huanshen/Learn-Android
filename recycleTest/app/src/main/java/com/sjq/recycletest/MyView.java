package com.sjq.recycletest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by shenjiaqi on 2018/3/26.
 */

public class MyView extends ViewGroup {
    private Context mContext;
    /** 普通文字绘制画笔 */
    private TextPaint mNormalPaint;
    /** view能绘制打最大宽度 */
    private int mMaxWidth = 0;

    private TextView textView;

    private ImageView imageView;

    private OnChildViewClickListener mClickListener;


    private static final int NORMAL_TEXT_COLOR = getColorById();


    public MyView (Context context){
        this(context, null);
    }

    public MyView(Context context, AttributeSet attributes){
        this(context, attributes, 0);
    }

    public MyView(Context context, AttributeSet attributes, int defStyle){
        super(context, attributes, defStyle);
        mContext = context;
        init();
    }

    public void init() {
        setWillNotDraw(false);
        textView = new TextView(getContext());
        textView.setText("我是aaa");
        textView.setTextSize(13);

        imageView = new ImageView(getContext());
        imageView.setPadding(15, 15, 0, 15);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setTag(R.id.myView_my);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));

        // 3.初始化画笔
        mNormalPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mNormalPaint.setAntiAlias(true);
        mNormalPaint.setTextSize(15);

        addView(textView);
        addView(imageView);

        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "textView", Toast.LENGTH_SHORT).show();
            }
        });

       imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mClickListener!=null){
                    mClickListener.onClick(v);
                    mClickListener = null;
                }
                Toast.makeText(v.getContext(), "imageView", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.save();
        drawSelf(canvas);
      //  canvas.restore();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        long startTime = 0L;

        super.dispatchDraw(canvas);
    }


    private void drawSelf(Canvas canvas) {
        // 文本
        Log.d("MyView", "  canvas" + canvas.getHeight());
        mNormalPaint.setColor(Color.BLUE);


        mNormalPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, mMaxWidth, getMaxHeight(), mNormalPaint);
        mNormalPaint.setColor(Color.YELLOW);
        mNormalPaint.setTextSize(30);
        String text = String.valueOf(0);
        Rect mBounds = new Rect();

        mNormalPaint.getTextBounds(text, 0, text.length(), mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();
        canvas.drawText(text, mMaxWidth / 2 - getMaxHeight() / 2, 300 / 2
                + textHeight / 2, mNormalPaint);

        canvas.drawText("string", 20, 15, mNormalPaint);

    }


    private float getTextBaseLineY(TextPaint paint, int maxHeight) {
        return (maxHeight - getTextHeight(paint)) / 2 - paint.getFontMetrics().top;
    }

    private float getTextHeight(TextPaint paint) {
        return paint.getFontMetrics().bottom - paint.getFontMetrics().top;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (imageView.getVisibility() != View.GONE) {
            Log.d("MyView", t+"  "+ l+" "+b + " "+r);
            imageView.layout(r - imageView.getMeasuredWidth(), 0, r, imageView.getMeasuredHeight());
        }
        Log.d("MyView", "  canvas" + l);
        textView.layout(l+700, 0, l+700 +textView.getMeasuredWidth(), textView.getMeasuredHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        mMaxWidth = maxWidth;
        setMeasuredDimension(maxWidth, getMaxHeight());
    }


    // 直接使用删除按钮的高度作为view的高度
    private int getMaxHeight() {
        return imageView.getMeasuredHeight();
    }

    private static int getColorById() {
        return Color.parseColor("#cccccc");
    }

    public void update() {
        requestLayout();
        invalidate();
    }

    public ImageView getImageView() {
        return imageView;
    }



    public void setOnChildViewClickListener(OnChildViewClickListener listener) {
        mClickListener = listener;
    }




}
