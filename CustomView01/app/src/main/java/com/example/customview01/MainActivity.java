package com.example.customview01;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.example.customview01.view.MyView2;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//setContentView(new MyView(this));
		setContentView(R.layout.layout);
	}


}
