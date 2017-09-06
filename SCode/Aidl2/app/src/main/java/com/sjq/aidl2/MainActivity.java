package com.sjq.aidl2;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private IMyAidlInterface mTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mTest = null;
        }

        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            mTest = IMyAidlInterface.Stub.asInterface(arg1);
            if (mTest == null) {
                Toast.makeText(MainActivity.this, "bind service failed",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "bind service sucess",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };
}
