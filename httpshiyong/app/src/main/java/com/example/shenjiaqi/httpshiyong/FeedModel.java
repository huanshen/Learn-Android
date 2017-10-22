package com.example.shenjiaqi.httpshiyong;

import java.util.ArrayList;

/**
 * 网络请求过来的外层数据
 * Created by shenjiaqi on 2017/10/22.
 */

public class FeedModel {
    /** 错误码 */
    public String error;
    /** 时间戳 */
    public String timestamp;

    /** FeedBaseModel的列表 */
    public ArrayList<FeedBaseModel> feedBaseModelList;

    @Override
    public String toString() {
        return error;
    }
}
