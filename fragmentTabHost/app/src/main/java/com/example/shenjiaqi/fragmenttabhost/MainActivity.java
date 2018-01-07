package com.example.shenjiaqi.fragmenttabhost;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shenjiaqi.fragmenttabhost.tab.FragmentPage1;
import com.example.shenjiaqi.fragmenttabhost.tab.FragmentPage2;
import com.example.shenjiaqi.fragmenttabhost.tab.FragmentPage3;
import com.example.shenjiaqi.fragmenttabhost.tab.FragmentPage4;
import com.example.shenjiaqi.fragmenttabhost.tab.TabHost;


public class MainActivity extends AppCompatActivity {
    private TabHost tabHost;

    private FragmentTabHost fragmentTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = new TabHost(getApplicationContext());

        // 实例化tabhost
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.onCreate(fragmentTabHost, getSupportFragmentManager());

    }


}