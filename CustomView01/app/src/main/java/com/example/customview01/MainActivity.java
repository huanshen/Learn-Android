package com.example.customview01;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btn = (Button) findViewById(R.id.shen);
		btn.setBackgroundResource(R.drawable.frame_test);
		AnimationDrawable drawable = (AnimationDrawable) btn.getBackground();
		drawable.start();
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.an_test);
		btn.startAnimation(animation);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "ss", Toast.LENGTH_SHORT).show();
			}
		});
	}


}
