package com.example.shenjiaqi.myapplication.itemanimator;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.shenjiaqi.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemAnimatorActivity extends AppCompatActivity {

    private static String[] data = new String[] {
            "Apple", "Ball", "Camera", "Day", "Egg", "Foo", "Google", "Hello", "Iron", "Japan", "Coke",
            "Dog", "Cat", "Yahoo", "Sony", "Canon", "Fujitsu", "USA", "Nexus", "LINE", "Haskell", "C++",
            "Java", "Go", "Swift", "Objective-c", "Ruby", "PHP", "Bash", "ksh", "C", "Groovy", "Kotlin"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final MainAdapter adapter = getMainAdapter();

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add("newly added item", 1);
            }
        });

        findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.remove(1);
            }
        });

    }

    @NonNull
    private MainAdapter getMainAdapter() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

        recyclerView.setLayoutManager(new LinearLayoutManager(ItemAnimatorActivity.this));

        recyclerView.setItemAnimator(new FadeItemAnimator());

        final MainAdapter adapter = new MainAdapter(ItemAnimatorActivity.this, new ArrayList<>(Arrays.asList(data)));
        recyclerView.setAdapter(adapter);
        return adapter;
    }
}
