package com.example.shenjiaqi.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FeedMultipleImageItemView view1 = (FeedMultipleImageItemView) findViewById(R.id.feed_view);

        final RelativeLayout.LayoutParams mVideoPlayIconLP = (RelativeLayout.LayoutParams) view1.mVideoPlayIcon.getLayoutParams();


        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("LP+++", mVideoPlayIconLP.height + "");
                Log.i("LP+++", mVideoPlayIconLP.width + "");
                Log.i("LP+++", view1.mVideoPlayIcon.getDrawable().getIntrinsicHeight() + "");
            }
        });
    }
}
