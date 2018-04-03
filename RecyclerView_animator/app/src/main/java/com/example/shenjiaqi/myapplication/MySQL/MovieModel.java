package com.example.shenjiaqi.myapplication.MySQL;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenjiaqi on 2018/2/25.
 */

public class MovieModel {
    private static final String TAG = "MovieModel";
    public int count;
    public String title;
    public List<Subjects> subjects;

    public MovieModel toModel(JSONObject jsonObject) {
        count = jsonObject.optInt("count");
        title = jsonObject.optString("title");
        JSONArray jsonArray = jsonObject.optJSONArray("subjects");
        subjects = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                subjects.add(Subjects.toModel(jsonObject1));
                Log.d(TAG, subjects.size() +"");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public static List<Subjects> toSubjectsModel(JSONObject jsonObject) {
        List<Subjects> subjects = new ArrayList<>();
        JSONArray jsonArray = jsonObject.optJSONArray("subjects");

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                subjects.add(Subjects.toModel(jsonObject1));
                Log.d(TAG, subjects.size() +"");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return subjects;
    }

    public JSONObject tojson() {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();

        try {
            if (subjects != null && subjects.size() > 0) {
                int size = subjects.size();
                for (int i = 0; i < size; i++) {
                    array.put(Subjects.toJson(subjects.get(i)));
                }
            }
            object.put("subjects", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public String toString() {
        if (subjects != null && subjects.size() > 0) {
            return "count: " + count + " title: " + subjects.toString();
        } else {
            return "count: " + count + " title: " + title;
        }
    }


    public static class Subjects {
        public String title;
        public Rating rating;

        public static Subjects toModel(JSONObject jsonObject) {
            Subjects subjects = new Subjects();
            try {
                subjects.title = jsonObject.getString("title");
                subjects.rating = Rating.toModel(jsonObject.getJSONObject("rating"));
            }catch (JSONException e){
                e.printStackTrace();
            }
            return subjects;
        }

        public static JSONObject toJson(Subjects subjects) {
            JSONObject object = new JSONObject();
            try {
                object.put("title", subjects.title);
                object.put("rating", Rating.tojson(subjects.rating));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return object;
        }

        @Override
        public String toString() {
            return "count: " +  " title: " + "woshi";
        }
    }

    public static class Rating {
        private int max;
        private int average;


        public static Rating toModel(JSONObject jsonObject) {
            Rating rating = new Rating();
            try {
                rating.max = jsonObject.getInt("max");
                rating.average = jsonObject.getInt("average");
            }catch (JSONException e){
                e.printStackTrace();
            }
            return rating;
        }

        public static JSONObject tojson(Rating rating) {
            JSONObject object = new JSONObject();
            try {
                object.put("max", rating.max);
                object.put("average", rating.average);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return object;
        }
    }
}
