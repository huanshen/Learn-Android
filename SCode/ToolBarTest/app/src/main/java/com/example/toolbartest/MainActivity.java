package com.example.toolbartest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Title");
        toolbar.setSubtitle("SubTitle");
        toolbar.setLogo(R.mipmap.ic_launcher);

        //设置导航图标要在setSupportActionBar方法之后
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.app_music);
    }
}
