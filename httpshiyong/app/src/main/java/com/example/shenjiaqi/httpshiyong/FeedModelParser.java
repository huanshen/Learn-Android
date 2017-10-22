package com.example.shenjiaqi.httpshiyong;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by shenjiaqi on 2017/10/22.
 */

public class FeedModelParser {
    /** Feed协议 */
    private IFeedProtocol mFeedProtocol;

    public FeedModelParser() {
        mFeedProtocol = new FeedProtocol();
    }

    /**
     * 解析数据
     */
    public FeedModel parseResponse(String response) {
        FeedModel feedModel = new FeedModel();
        try {
            JSONObject jsonObject = new JSONObject(response);
            feedModel.error = jsonObject.optString(IFeedProtocol.ERROR);
            feedModel.timestamp = jsonObject.optString(IFeedProtocol.TIMESTAMP);
            JSONObject dataJsonObj = jsonObject.optJSONObject(IFeedProtocol.DATA);
            if (dataJsonObj == null) {
                return feedModel;
            }
            JSONObject codeObj = dataJsonObj.optJSONObject(mFeedProtocol.obtainCmdFlow());
            if (codeObj == null) {
                return feedModel;
            }
            JSONObject itemListObj = codeObj.optJSONObject(IFeedProtocol.ITEM_LIST);
            if (itemListObj == null) {
                return feedModel;
            }
            JSONArray itemsArray = itemListObj.optJSONArray(IFeedProtocol.ITEMS);
            if (itemsArray == null) {
                return feedModel;
            }
            ArrayList<FeedBaseModel> feedBaseModelList = new ArrayList<FeedBaseModel>();
            int arraySize = itemsArray.length();

            for (int i = 0; i < arraySize; i++) {
                JSONObject itemObj = itemsArray.getJSONObject(i);
                FeedBaseModel feedBaseModel = FeedBaseModel.getModelByJson(itemObj);
                feedBaseModelList.add(feedBaseModel);
            }
            feedModel.feedBaseModelList = feedBaseModelList;

        } catch (JSONException e) {
            Log.i(MainActivity.TAG, "解析错误");
        }
        return feedModel;

    }
}
