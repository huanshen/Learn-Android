package com.example.shenjiaqi.fragmenttabhost.tab;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shenjiaqi.fragmenttabhost.FragmentPage5;
import com.example.shenjiaqi.fragmenttabhost.R;
import com.example.shenjiaqi.fragmenttabhost.tab.FragmentPage1;
import com.example.shenjiaqi.fragmenttabhost.tab.FragmentPage2;
import com.example.shenjiaqi.fragmenttabhost.tab.FragmentPage3;
import com.example.shenjiaqi.fragmenttabhost.tab.FragmentPage4;

/**
 * Created by shenjiaqi on 2017/12/1.
 */

public class TabHost {
    private Context mContext;
    private String texts[] = { "首页", "通讯录", "电影", "我的" };
    private int imageButton[] = { R.drawable.bt_home_selector,
            R.drawable.bt_home_selector, R.drawable.bt_home_selector,R.drawable.bt_home_selector};
    private Class fragmentArray[] = {FragmentPage1.class,FragmentPage2.class,FragmentPage3.class,FragmentPage4.class};

    public TabHost (Context context){
        mContext = context;
    }

    public void onCreate(FragmentTabHost fragmentTabHost, FragmentManager mFragmentManager) {

        fragmentTabHost.setup(mContext, mFragmentManager,
                R.id.maincontent);

        for (int i = 0; i < texts.length; i++) {
            android.widget.TabHost.TabSpec spec=fragmentTabHost.newTabSpec(texts[i]).setIndicator(getView(i));

            fragmentTabHost.addTab(spec, fragmentArray[i], null);

            //设置背景(必须在addTab之后，由于需要子节点（底部菜单按钮）否则会出现空指针异常)
            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.bt_selector);
        }

    }

    private View getView(int i) {
        //取得布局实例
        View view=View.inflate(mContext, R.layout.tab_item_view, null);

        //取得布局对象
        ImageView imageView=(ImageView) view.findViewById(R.id.image);
        TextView textView=(TextView) view.findViewById(R.id.text);

        //设置图标
        imageView.setImageResource(imageButton[i]);
        //设置标题
        textView.setText(texts[i]);
        return view;
    }


}
