package com.sjq.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }

    IMyAidlInterface.Stub myAidlInterface = new IMyAidlInterface.Stub(){
        public int getCount (){
            return 34;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return myAidlInterface;
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
