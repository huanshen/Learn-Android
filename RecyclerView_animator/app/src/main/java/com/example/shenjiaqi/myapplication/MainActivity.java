package com.example.shenjiaqi.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.shenjiaqi.myapplication.AdapterAnimator.AdapterAnimatorActivity;
import com.example.shenjiaqi.myapplication.EnterAdapterAnimation.EnterAnimatorActivity;
import com.example.shenjiaqi.myapplication.HScroller.Main3Activity;
import com.example.shenjiaqi.myapplication.MySQL.SQLActivity;
import com.example.shenjiaqi.myapplication.itemanimator.ItemAnimatorActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




       findViewById(R.id.adapter_animator_example).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdapterAnimatorActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.item_animator_example).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ItemAnimatorActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sp = getSharedPreferences("Launcher",MODE_PRIVATE);
        int isFirst = sp.getInt("isFirst",100);
        if (isFirst == 100){
            Log.d(TAG, "isFirst   " + isFirst);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("isFirst", -1);
            editor.apply();
        }
        Log.d(TAG, "isFirst   " + isFirst);
        setContentView(R.layout.activity_main);


        findViewById(R.id.enter_animator_example).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EnterAnimatorActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.hscroll_tab_viewPager_example).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.sql_tab_viewPager_example).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SQLActivity.class);
                startActivity(intent);
            }
        });





    }


}
