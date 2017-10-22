package com.example.shenjiaqi.httpshiyong;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "test";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private String[] data;

    /**
     * 接收解析后传过来的数据
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            FeedModel feedModel = (FeedModel) msg.obj;
            initRecyclerView(feedModel.feedBaseModelList);
            Log.i("M", feedModel.error);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        okHttp_synchronousGet();
    }

    public void initRecyclerView(ArrayList<FeedBaseModel> feedBaseModelList) {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
     //   mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL, 10, getApplicationContext().getColor(getApplicationContext(), R.color.divide_gray_color)));
     //   mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //这句就是添加我们自定义的分隔线
        mRecyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL));

        // 具有点击功能的 1
        mAdapter = new MyAdapter(feedBaseModelList, new MyAdapter.onRecyclerViewItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(MainActivity.this, "第" + position + "行", Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);

    }

    /**
     * 发起网络请求
     */
    private void okHttp_synchronousGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "";
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).method("GET", null).build();
                    okhttp3.Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String responseString = (response.body() == null ? "" : response.body().string());
                        FeedModelParser parser = new FeedModelParser();
                        FeedModel feedModel = parser.parseResponse(responseString);
                        handler.sendMessage(handler.obtainMessage(22, feedModel));

                    } else {
                        Log.i(TAG, "okHttp is request error");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
