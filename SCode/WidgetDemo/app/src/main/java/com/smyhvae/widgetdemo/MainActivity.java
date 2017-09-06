package com.smyhvae.widgetdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button btn;
    public static final String WIDGET_BTN_ACTION = "widget_btn_action";
    private Intent intent;
    private MyService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);

        Intent intent1 = new Intent(this, MyService.class);
        startService(intent1);

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                intent = new Intent(WIDGET_BTN_ACTION);
                sendBroadcast(intent);
            }
        });


    }
}
