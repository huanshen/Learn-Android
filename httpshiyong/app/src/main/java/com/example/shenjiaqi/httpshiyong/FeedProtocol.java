package com.example.shenjiaqi.httpshiyong;

/**
 * Created by shenjiaqi on 2017/10/22.
 */

public class FeedProtocol implements IFeedProtocol {
    /** FEED列表数据的cmd */
    public static final String CMD_FLOW = "100";

    /** 错误码 */
    public String error;
    /** 时间戳 */
    public String timestamp;

    public FeedProtocol() {
    }

    @Override
    public String obtainCmdFlow() {
        return CMD_FLOW;
    }
}
