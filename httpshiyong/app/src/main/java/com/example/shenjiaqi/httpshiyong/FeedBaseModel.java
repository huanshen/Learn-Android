package com.example.shenjiaqi.httpshiyong;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Feed流中数据结构的基类。
 * Created by shenjiaqi on 2017/10/22.
 */
public class FeedBaseModel {
    /** id key 常量 */
    public static final String FEED_ID = "id";
    /** layout key 常量 */
    public static final String FEED_LAYOUT = "layout";
    /** 内容唯一id */
    public String id;
    /** 客户端渲染模板 */
    public String layout;

    /**
     * json转成model对象
     *
     * @param itemObj json对象
     * @return model对象
     */
    public static FeedBaseModel getModelByJson(JSONObject itemObj) {
        if (itemObj == null) {
            return null;
        }

        FeedBaseModel feedBaseModel = new FeedBaseModel();
        feedBaseModel.id = itemObj.optString(FEED_ID);
        feedBaseModel.layout = itemObj.optString(FEED_LAYOUT);
        return feedBaseModel;
    }

    /**
     * 将FeedBaseModel转换为json数据对象
     *
     * @param feedBaseModel model对象
     * @return json数据对象
     */
    public static JSONObject getJsonByModel(FeedBaseModel feedBaseModel) {
        if (feedBaseModel == null) {
            return new JSONObject();
        }
        JSONObject result = new JSONObject();
        try {
            result.put(FEED_ID, feedBaseModel.id);
            result.put(FEED_LAYOUT, feedBaseModel.layout);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

}
