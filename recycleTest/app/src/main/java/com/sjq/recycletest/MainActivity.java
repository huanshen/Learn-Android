package com.sjq.recycletest;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private String[] data;
    private Handler handler;
    private boolean isClick = false;
    private static int vHeight = -1;
    private static int reHeight = -1;
    private static int position = 0;
    private static boolean isMove = false;
    private Runnable runnable;
    private  ValueAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        data = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};


       runnable = new Runnable() {
            @Override
            public void run() {
                if (isVisible()) {
                    scrollToMiddle2();
                } else {
                    mRecyclerView.scrollToPosition(position);
                    isMove = true;
                    isClick = false;
                }

            }
        };

        // 具有点击功能的 1
        mAdapter = new MyAdapter(data, new MyAdapter.onRecyclerViewItemClick() {
            @Override
            public void onItemClick(View v, int pos) {
                Toast.makeText(MainActivity.this, "第" + pos + "行", Toast.LENGTH_SHORT).show();
                position = pos;
                vHeight = v.getHeight();

                Rect rect = new Rect();
                mRecyclerView.getGlobalVisibleRect(rect);
                // 减去当前点击 view 的高度
                reHeight = rect.bottom - rect.top - vHeight;

                // handler.removeCallbacksAndMessages(null);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 4000);
                isClick = true;

            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d(TAG, "" + newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING && !isMove) {
                    handler.removeCallbacks(runnable);
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (isClick) {
                        handler.postDelayed(runnable, 4000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isMove) {
                    if (vHeight < 0) {
                        isMove = false;
                        return;
                    }
                    if (isVisible()) {
                        scrollToMiddle2();
                    }
                }
            }
        });

        // 具有点击功能的 2
       /* mAdapter = new AnotherAdapte r(data, new AnotherAdapter.OnItemClickListener(){
            @Override
            public void onItemClick (View view , int position){
                Toast.makeText(MainActivity.this, data[position],Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setAdapter(mAdapter);*/

      /*  mAdapter = new TwoAdapter(data);
        mRecyclerView.setAdapter(mAdapter);*/
    }

    public void scrollToMiddle() {
        final int firstPosition = mLayoutManager.findFirstVisibleItemPosition();
        int top = mRecyclerView.getChildAt(position - firstPosition).getTop();
        Log.d(TAG, " top" + top + " scrollX" + mRecyclerView.getScrollY());
        int half = reHeight / 2;
        mRecyclerView.smoothScrollBy(0, top - half);
        isMove = false;

    }

    public void scrollToMiddle2() {
            animator = ValueAnimator.ofInt(0, 1).setDuration(1000);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float f = animator.getAnimatedFraction();
                    final int firstPosition = mLayoutManager.findFirstVisibleItemPosition();
                    final int top = mRecyclerView.getChildAt(position - firstPosition).getTop();
                    final int half = reHeight / 2;
                    mRecyclerView.scrollBy(0, (int) ((top - half) * f));
                    Log.d(TAG, " top" + top + " scrollX   f      " + f);
                }
            });
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                  //  animator.end();
                    isMove = false;



                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

            animator.start();



    }

    public boolean isVisible() {
        final int firstPosition = mLayoutManager.findFirstVisibleItemPosition();
        final int lastPosition = mLayoutManager.findLastVisibleItemPosition();
        return position <= lastPosition && position >= firstPosition;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        handler = null;
    }
}
