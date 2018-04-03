package com.example.shenjiaqi.myapplication;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by shenjiaqi on 2018/2/4.
 */

public final class ViewHelper {
    public static void clear(RecyclerView.ViewHolder holder) {
        View v = holder.itemView;
        ViewCompat.setAlpha(v, 1);
        ViewCompat.setScaleY(v, 1);
        ViewCompat.setScaleX(v, 1);
        ViewCompat.setTranslationY(v, 0);
        ViewCompat.setTranslationX(v, 0);
        ViewCompat.setRotation(v, 0);
        ViewCompat.setRotationY(v, 0);
        ViewCompat.setRotationX(v, 0);
        ViewCompat.setPivotY(v, v.getMeasuredHeight() / 2);
        ViewCompat.setPivotX(v, v.getMeasuredWidth() / 2);
        ViewCompat.animate(v).setInterpolator(null).setStartDelay(0);
        ViewCompat.animate(v).setListener(null);

    }

}

