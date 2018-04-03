package com.example.shenjiaqi.myapplication.HScroller;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.shenjiaqi.myapplication.R;

/**
 * Created by shenjiaqi on 2018/2/9.
 */

public class SlidingTabLayout extends HorizontalScrollView {

    private HorizontalScrollView horizontalScrollView;

    private static final String TAG = "SlidingTabLayout";

    /** ViewPager */
    private ViewPager mViewPager;

    private  LinearLayout mTabStrip;

    private int mSelectedPosition;

    private float mSelectionOffset;

    private int offsetdp;
    private int mCurrPos = 4;

    public SlidingTabLayout(Context context) {
        this(context, null);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // 不显示ScrollBar
        setHorizontalScrollBarEnabled(false);
        // Make sure that the Tab Strips fills this View
        setFillViewport(true);

        offsetdp = (int) getResources().getDimension(R.dimen.offsetdp);

        horizontalScrollView = (HorizontalScrollView) LayoutInflater.from(context).inflate(R.layout.activity_main_hscroller, this);

        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) getChildAt(0);
        mTabStrip = (LinearLayout) horizontalScrollView.getChildAt(0);

        scrollToTab(mCurrPos, 0);

        horizontalScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
               // scrollToTab(mCurrPos, 0);

            }
        });


    }

    public void scrollToTab(int tabIndex, int positionOffset) {

        final int tabStripChildCount = mTabStrip.getChildCount();
        if (tabStripChildCount == 0 || tabIndex < 0 || tabIndex >= tabStripChildCount) {
            return;
        }

        View selectedChild = mTabStrip.getChildAt(tabIndex);
        if (selectedChild != null) {
            int targetScrollX = selectedChild.getLeft() + positionOffset;



          //  scrollTo(targetScrollX, 0);
            smoothScrollTo(targetScrollX, 0);


            Log.d(TAG, "scrollToTab tabIndex "+tabIndex + "    targetScrollX "+targetScrollX + " ");

        }
    }

    /**
     * 关联ViewPager
     */
    public void setViewPager(ViewPager viewPager) {

        mViewPager = viewPager;
        if (viewPager != null) {
            viewPager.clearOnPageChangeListeners();
            viewPager.addOnPageChangeListener(new InternalViewPagerListener());
        }
    }


    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        private int mScrollState;
        /** 当前viewpager的SETTLING状态是否由用户拖拽松手后触发*/
        private boolean mIsUserDrag = false;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int tabStripChildCount = mTabStrip.getChildCount();
            if ((tabStripChildCount == 0) || (position < 0) || (position >= tabStripChildCount)) {
                return;
            }
           // mTabStrip.onViewPagerPageChanged(position, positionOffset);
            // [RN-Bug-1388] 偶现添加频道, Tablayout自动切换到新增频道后,存在多频道tab文字均变大的情况
            // 只有用户拖拽行为才对左右两个tab做动效
            if (mIsUserDrag) {

            }
            scrollToTab(position, 0);
            Log.d(TAG, "onPageScrolled position "+position + "");
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            mIsUserDrag = (state == ViewPager.SCROLL_STATE_DRAGGING)
                    || (mScrollState == ViewPager.SCROLL_STATE_DRAGGING && state == ViewPager.SCROLL_STATE_SETTLING);
            mScrollState = state;
        }

        @Override
        public void onPageSelected(int position) {
            if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                scrollToTab(position, 0);
                onViewPagerPageChanged(position, 0);
            }

            Log.d(TAG, "onPageSelected                    position "+position + "");
            scrollToTab(position, 0);


            // 快速切换，同时点击熊掌返回首页，有可能多个tab都变大，和viewpager的回调机制相关
            // 在选中某个tab之后，不再触发tab变大的动画（去掉尾部动画），解决多个tab变大的问题
            // http://newicafe.baidu.com/issue/BaiduSearchAndroid-19150/show?from=page
            mIsUserDrag = false;
        }

    }

    public void onViewPagerPageChanged(int position, float positionOffset) {
        mSelectedPosition = position;
        mSelectionOffset = positionOffset;
        invalidate();
    }


    private boolean isViewVisible(View view) {
       /* initIconsAreaViewIfNeed();
        if (mIconsAreaView != null) {
            // 1.获取自身在屏幕中的坐标
            int[] selfPosition = new int[2];
            getLocationOnScreen(selfPosition);
            // 2.获取右侧图标在屏幕中的坐标
            int[] iconsAreaViewPosition = new int[2];
            mIconsAreaView.getLocationOnScreen(iconsAreaViewPosition);
            // 3.获取图标在此TabLayout中的坐标
            int[] iconRelativePosition = new int[2];
            iconRelativePosition[0] = iconsAreaViewPosition[0] - selfPosition[0];
            iconRelativePosition[1] = iconsAreaViewPosition[1] - selfPosition[1];
            // 4.获取tab Item在屏幕中的坐标
            int[] tabPosition = new int[2];
            view.getLocationOnScreen(tabPosition);
            // 5.获取tab在tabLayout中的坐标
            int[] tabRelativePosition = new int[2];
            tabRelativePosition[0] = tabPosition[0] - selfPosition[0];
            tabRelativePosition[1] = tabPosition[1] - selfPosition[1];

            return tabRelativePosition[0] < iconRelativePosition[0]
                    && tabRelativePosition[1] < getMeasuredHeight();
        }
        // 兼容原来的策略
        else {
            Rect scrollBounds = new Rect();
            getHitRect(scrollBounds);
            return view.getGlobalVisibleRect(scrollBounds);
        }*/

       return false;
    }





}
