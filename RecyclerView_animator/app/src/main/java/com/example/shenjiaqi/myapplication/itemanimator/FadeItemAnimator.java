package com.example.shenjiaqi.myapplication.itemanimator;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView;

import com.example.shenjiaqi.myapplication.itemanimator.BaseItemAnimator;

/**
 * Created by shenjiaqi on 2018/2/4.
 */

public class FadeItemAnimator extends BaseItemAnimator {
    /**
     * 执行移除动画
     * @param holder 被移除的ViewHolder
     * @param animator 被移动的ViewHolder对应动画对象
     */
    @Override
    public void setRemoveAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimatorCompat animator) {
        animator.alpha(1f).rotationX(30);

    }

    /**
     * 执行添加动画初始化 这里设置透明为0添加时就会有渐变效果当然你可以在执行动画代码之前执行
     * @param holder 添加的ViewHolder
     */
    @Override
    public void addAnimationInit(RecyclerView.ViewHolder holder) {
        ViewCompat.setAlpha(holder.itemView, 0);
        ViewCompat.setTranslationX(holder.itemView, -180);


    }

    /**
     * 执行添加动画
     * @param holder 添加的ViewHolder
     * @param animator 添加的ViewHolder对应动画对象
     */
    @Override
    public void setAddAnimation(RecyclerView.ViewHolder holder,ViewPropertyAnimatorCompat animator) {
        animator.alpha(1).rotation(-180).translationX(180);
    }

    /**
     * 取消添加还原状态以复用
     * @param holder 添加的ViewHolder
     */
    @Override
    public void addAnimationCancel(RecyclerView.ViewHolder holder) {
        ViewCompat.setAlpha(holder.itemView, 1);
    }

    /**
     * 更新时旧的ViewHolder动画
     * @param holder 旧的ViewHolder
     * @param animator ViewHolder对应动画对象
     */
    @Override
    public void setOldChangeAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimatorCompat animator) {
        animator.alpha(0);
    }

    /**
     * 更新时旧的ViewHolder动画结束，执行还原
     * @param holder
     */
    @Override
    public void oldChangeAnimationEnd(RecyclerView.ViewHolder holder) {
        ViewCompat.setAlpha(holder.itemView,1);
    }

    /**
     * 更新时新的ViewHolder初始化
     * @param holder 更新时新的ViewHolder
     */
    @Override
    public void newChangeAnimationInit(RecyclerView.ViewHolder holder) {
        ViewCompat.setAlpha(holder.itemView,0);
    }

    /**
     * 更新时新的ViewHolder动画
     * @param holder 新的ViewHolder
     * @param animator ViewHolder对应动画对象
     */
    @Override
    public void setNewChangeAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimatorCompat animator) {
        animator.alpha(0).translationX(130).translationY(130);
    }

    /**
     * 更新时新的ViewHolder动画结束，执行还原
     * @param holder
     */
    @Override
    public void newChangeAnimationEnd(RecyclerView.ViewHolder holder) {
        ViewCompat.setAlpha(holder.itemView,1);
    }


}
