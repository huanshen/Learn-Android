package com.example.shenjiaqi.retrofit;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.shenjiaqi.retrofit.movie.MovieSubject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.sss);
        tv.setBackground(getResources().getDrawable(R.drawable.layer_list));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //获取接口实例
        MovieService movieService = retrofit.create(MovieService.class);
//调用方法得到一个Call
        Call<MovieSubject> call = movieService.getTop250(0,20);
        //进行网络请求
        call.enqueue(new Callback<MovieSubject>() {
            @Override
            public void onResponse(Call<MovieSubject> call, Response<MovieSubject> response) {
                int id = response.body().count;
                Log.i("ssssssss", id + "");
            }
            @Override
            public void onFailure(Call<MovieSubject> call, Throwable t) {
                Log.i("ssssssss", "tttttt");
                t.printStackTrace();
            }
        });



    }

    public interface MovieService {

        //获取豆瓣Top250 榜单
        @GET("top250")
        Call<MovieSubject> getTop250(@Query("start") int start, @Query("count")int count);

    }

}
