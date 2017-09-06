package com.tnnowu.android.learnrecyclerview;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DemoAdapter myAdapter = new DemoAdapter(this, null, new DemoAdapter.onRecyclerViewItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                Snackbar.make(v, "第" + position + "行", Snackbar.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(myAdapter);
    }

}
