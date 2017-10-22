package com.example.shenjiaqi.httpshiyong;

/**
 * Created by shenjiaqi on 2017/10/22.
 */

public interface IFeedProtocol {
    /**
     * 错误码
     */
    String ERROR = "errno";
    /**
     * 时间戳
     */
    String TIMESTAMP = "timestamp";
    /**
     * data数据
     */
    String DATA = "data";
    /**
     * list结构--feed流特有字段
     */
    String ITEM_LIST = "itemlist";
    /**
     * item列表
     */
    String ITEMS = "items";

    /**
     * 获取Feed的CMD字段名称
     */
    String obtainCmdFlow();
}

