package com.smyhvae.widgetdemo;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public static final String WIDGET_BTN_ACTION = "widget_btn_action";
    private TestWidget mAppWidgetProvider = TestWidget.getInstance();
    private Context context;

    public MyService() {
    }
    public void onCreate(){
        super.onCreate();
        IntentFilter commandFilter = new IntentFilter();
        commandFilter.addAction("widget");
        registerReceiver(mIntentReceiver, commandFilter);
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("DemoLog", "TestService -> onStartCommand, startId: " + startId + ", Thread ID: " + Thread.currentThread().getId());
        intent = new Intent(WIDGET_BTN_ACTION);
        intent.putExtra("ss",1);
        sendBroadcast(intent);
        mAppWidgetProvider.perforUpdate( );
        return START_STICKY;
    }

    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("widget")){
                Toast.makeText(context, "widget", Toast.LENGTH_SHORT).show();
            }

        }
    };

}
