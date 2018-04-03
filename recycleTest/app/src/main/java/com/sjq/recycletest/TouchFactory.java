package com.sjq.recycletest;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by shenjiaqi on 2018/3/27.
 */

public class TouchFactory {

    /** 扩展垂直方向点击区域尺寸 */
    private static final int EXT_V_SIZE = 200;

    public static View.OnTouchListener creatTouchListener(){
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (expendTouchSize(v, event)) {
                    return true;
                }
                return false;
            }
        };
    }

    public static boolean expendTouchSize(View v, MotionEvent event) {
        if (v instanceof LinearLayout) {
            int count = ((LinearLayout) v).getChildCount();
            if (count < 2) {
                return false;
            }
            MyView vi = (MyView) ((LinearLayout) v).getChildAt(1);
            ImageView view = vi.getImageView();
            if (view.getVisibility() == View.VISIBLE) {
                Rect touchRect = new Rect();
                view.getGlobalVisibleRect(touchRect);

                int action = event.getAction();
                float x = event.getRawX();
                float y = event.getRawY();

                if ((y >= touchRect.top - EXT_V_SIZE) && (y <= touchRect.bottom + EXT_V_SIZE)) {
                    if (x >= touchRect.left) {
                        if (action == MotionEvent.ACTION_UP) {
                            vi.setOnChildViewClickListener(new OnChildViewClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(view.getContext(), "touch", Toast.LENGTH_SHORT).show();
                                }
                            });
                         //   Toast.makeText(view.getContext(), "touch", Toast.LENGTH_SHORT).show();

                        }
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
