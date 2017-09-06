package com.qs.aidlclientdemo;

import java.util.Date;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

import com.qs.aidl.IPerson;


public class MainActivity extends Activity {
	private IPerson person;
	private MyPrinterInterface myInterface;
    private ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			person = IPerson.Stub.asInterface(service);
		}
	};
	
    private ServiceConnection myconn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("myconn connected");
			myInterface = MyPrinterInterface.Stub.asInterface(service);
		}
	};
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //首先要拉起服务器的service程序然后才能调用远程服务器提供的api
        Intent intent = new Intent("android.intent.action.AIDLService");
        String time = new Date().toLocaleString();
        intent.putExtra("time", time);//这里我们还可以给服务器发送数据，在服务器的onBind函数中它的参数接收这个数据
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        doBindService();
    }
	
	public void greet(View v)
	{
		try {
			Toast.makeText(this,person.greet("qiusen "),0).show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	public void add(View v)
	{
		try {
			Toast.makeText(this,person.add("1","2"),0).show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	public  void printmsg(View v)
	{
		try {
			if(myInterface==null){
				System.out.println("myInterface is not success");
				return;
			}
			myInterface.print("this is a client msg");
			System.out.println("add sum is ="+myInterface.add("1", "7"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	} 
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(conn!=null)
			unbindService(conn);
		if(myconn!=null)
			unbindService(myconn);
	}
	
	private void doBindService()
	{
		Intent intent = new Intent("com.qs.MyAIDLService");
		bindService(intent, myconn, Context.BIND_AUTO_CREATE);
	}
}