package com.qs.aidldemo;

import com.qs.aidl.IPerson;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AIDLService extends Service{
	//拿到一个系统自动生成的代理类，然后实现代理类需要我们实现的两个函数
	//这也就是我们服务器提供的两个接口函数
	private IPerson.Stub stub = new IPerson.Stub() {
		
		@Override
		public String greet(String someone) throws RemoteException {
			return "hello "+someone;
		}
		
		@Override
		public String add(String a, String b) throws RemoteException {
			int aa = Integer.parseInt(a);
			int bb = Integer.parseInt(b);
			return String.valueOf(aa+bb);
		}
	};
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("data from client =="+intent.getExtras().getString("time"));
		return stub;
	}
	//我们可以看到当client调用bindService之后我们的onCreate方法被调用了
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("AIDLServerDemo service onCreate------");
	}
	//我们可以看到当client调用onDestory，也就是直接按返回键退出之后我们的onDestory方法被调用了
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("AIDLServerDemo service onDestroy------");
	}

}
