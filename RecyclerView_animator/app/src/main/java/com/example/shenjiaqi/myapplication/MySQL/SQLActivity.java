package com.example.shenjiaqi.myapplication.MySQL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shenjiaqi.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SQLActivity extends AppCompatActivity {

    private ImageView ivBasicImage;
    private TextView textView;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        ivBasicImage = (ImageView) findViewById(R.id.ivBasicImage);
        textView = (TextView) findViewById(R.id.text) ;

        String url = "http://api.douban.com/v2/movie/top250?start=25&count=2";
        requestBlog(url);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
        dbHelper.getWritableDatabase();


        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // 查询Book表中所有的数据
        Cursor cursor = db.query("Movie", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor对象，取出数据并打印
                MovieModel movieModel = new MovieModel();
                movieModel.title = cursor.getString(cursor.getColumnIndex("title"));
                movieModel.count= cursor.getInt(cursor.getColumnIndex("count"));
                try{
                    String su = cursor.getString(cursor.getColumnIndex("subjects"));
                    JSONObject jsonObject = new JSONObject(su);
                    movieModel.subjects = MovieModel.toSubjectsModel(jsonObject);

                }catch (JSONException e) {
                    e.printStackTrace();
                }
          /*      Log.d("MainActivity", "book author is " + author);
                Log.d("MainActivity", "book pages is " + pages);
                Log.d("MainActivity", "book price is " + price);*/
          if (movieModel.subjects != null && movieModel.subjects.size() >0 ){
              MovieModel.Subjects subjects =  movieModel.subjects.get(0);
              textView.setText(subjects.title);
          }else {
              textView.setText(movieModel.title);
          }
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void requestBlog(String url) {

        OkHttpClient mHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();

        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    MovieModel movieModel = new MovieModel().toModel(jsonObject);
                    Log.d("shen", movieModel.toString());
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    // 开始组装第一条数据
                    values.put("count", movieModel.count);
                    values.put("title", movieModel.title);
                    values.put("subjects", movieModel.tojson().toString());
                    db.insert("Movie", null, values); // 插入第一条数据


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

}