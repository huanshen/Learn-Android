package com.example.shenjiaqi.myapplication.HScroller;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shenjiaqi.myapplication.R;

import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private SlidingTabLayout mSlidingTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        ViewPager viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(new AdapterViewpager());
        mSlidingTabLayout.setViewPager(viewPager);



    }

    public class AdapterViewpager extends PagerAdapter {
        private List<View> mViewList;

        @Override
        public int getCount() {//必须实现
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {//必须实现
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {//必须实现，实例化
            TextView tv = new TextView(Main3Activity.this);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(20);
            tv.setText("我是天才" + position + "号");

            // 添加到ViewPager容器
            container.addView(tv);

            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {//必须实现，销毁
            container.removeView((View) object);
        }
    }


}
