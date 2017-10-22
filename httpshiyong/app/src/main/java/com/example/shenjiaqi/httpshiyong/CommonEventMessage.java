package com.example.shenjiaqi.httpshiyong;

/**
 * Created by shenjiaqi on 2017/10/22.
 */

public class CommonEventMessage {
    /** 无效ID */
    public static final int COMMON_MESSAGE_ID_INVALID = -1;
    /** 消息ID */
    public int messageId;
    /** 快速参数1 */
    public int arg0;
    /** 快速参数2 */
    public int arg1;
    /** 快速参数3 */
    public Object obj;


    public CommonEventMessage(int messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("messageId=")
                .append(messageId)
                .append(" arg0=")
                .append(arg0)
                .append(" arg1=")
                .append(arg1);
        return strBuilder.toString();

    }
}

