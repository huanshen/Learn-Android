package com.sjq.recycletest;

/**
 * Created by shenjiaqi on 2018/3/27.
 */

import android.view.View;

/**
 * 该类模板提供给外部的回调监听类，模板触发了相应操作后，会回调相应接口。
 */
public interface OnChildViewClickListener {
    /**
     * 模板的某个按钮的点击事件
     *
     * @param view 被用户点击的视图控件
     */
    void onClick(View view);
}
