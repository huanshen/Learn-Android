/*
package com.example.customview01.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

*/
/**
 * Created by shenjiaqi on 2017/8/11.
 *//*


public class MyView extends LinearLayout {

    private Button button;

    public MyView(final Context context) {
        super(context);
        button = new Button(context);
        */
/**
         * 方式1：
         *//*

//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300,100);
//        button.setLayoutParams(layoutParams);
        */
/**
         * 方式2：
         *//*

        addView(button);
        LinearLayout.LayoutParams layoutParams = (LayoutParams) button.getLayoutParams();
        layoutParams.weight = 300;
        layoutParams.height = 200;
        button.setLayoutParams(layoutParams);
        button.setText("我是一个按钮");

    }
}*/
