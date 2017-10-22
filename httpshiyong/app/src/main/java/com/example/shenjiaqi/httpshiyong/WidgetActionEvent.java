package com.example.shenjiaqi.httpshiyong;

import android.view.View;

/**
 * Created by shenjiaqi on 2017/10/22.
 */

public class WidgetActionEvent extends CommonEventMessage {

    /** test */
    public static final int ACTION_CLICK = 0x08;

    public int type;
    public int position;
    public View clickedView;
    public Object object;
    public String tabId;
    public String id;

    public WidgetActionEvent(int messageId) {
        super(messageId);
    }
}

