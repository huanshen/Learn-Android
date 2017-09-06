package com.qs.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.widget.Toast;
//自己实现的AIDL方法：
//技术博客参考:http://blog.csdn.net/hitlion2008/article/details/9824009
public class MyAIDLService extends Service {
	private Handler mHandler = new Handler();
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
	
	private MyPrinterInterfaceStub mBinder = new MyPrinterInterfaceStub(); 
//	{
//		
//		@Override
//		public void print(String msg) throws RemoteException {
//			MyAIDLService.this.print(msg);
//		}
//
//		@Override
//		public String add(String a, String b) throws RemoteException {
//			return MyAIDLService.this.add(a,b);
//		}
//	};
//-------------------这个是Service提供出去的两个方法，我们在Binder中通过实现这两个函数的接口来调用它们	
	public void print(String msg)
	{
		try{
            System.out.println("Preparing printer...");  
            Thread.sleep(1000);  
            System.out.println("Connecting printer...");  
            Thread.sleep(1000);  
            System.out.println("Printing.... " + msg);  
            Thread.sleep(1000);  
            System.out.println( "Done");  
		}catch(Exception e)
		{
			
		}
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(MyAIDLService.this, "MyService printing is donw", 0).show();
			}
		});
	}
	
	public String add(String a,String b)
	{
		int sum = 0;
		try{
            sum  = Integer.parseInt(a)+Integer.parseInt(b) ;
            System.out.println("Printing.... " + sum );  
		}catch(Exception e)
		{
		}
		return String.valueOf(sum);
	}
	
//	public interface MyPrinterInterface extends IInterface {
//		public void print(String msg) throws android.os.RemoteException;
//		public String add(String a,String b) throws android.os.RemoteException;
//	}
	
	//继承自Binder的子类，定义了两个接口函数，没有去实现
	/*abstract*/ class MyPrinterInterfaceStub extends Binder /*implements MyPrinterInterface*/ implements IInterface{
		private static final String DESCRIPTOR="MyPrinterInterface";
		
		public MyPrinterInterfaceStub(){
			attachInterface(this, DESCRIPTOR);
		}
		@Override
		public IBinder asBinder() {
			return this;
		}
		
		public void print(String msg) throws RemoteException {
			MyAIDLService.this.print(msg);
		}

		public String add(String a, String b) throws RemoteException {
			return MyAIDLService.this.add(a,b);
		}
		
		@Override
		protected boolean onTransact(int code, Parcel data, Parcel reply,
				int flags) throws RemoteException {
			System.out.println("onTransact,code is "+code);
			switch(code)
			{
			case INTERFACE_TRANSACTION:
				System.out.println("MyPrinterInterfaceStub onTransact,code is "+code+" ,when this happens");
				reply.writeString(DESCRIPTOR);
				return true;
			case TRANSACTION_print:
				data.enforceInterface(DESCRIPTOR);
				String _arg0;
				_arg0 = data.readString();
				System.out.println("MyPrinterInterfaceStub ontransact, arg is "+_arg0+" , when this happened?");
				this.print(_arg0);
				reply.writeNoException();
				return true;
			case TRANSACTION_add:
				data.enforceInterface(DESCRIPTOR);
				String _arg2,_arg3,_result;
				_arg2 = data.readString();
				_arg3 = data.readString();
				 _result = this.add(_arg2, _arg3);
				 reply.writeNoException();
				 reply.writeString(_result);
				break;
			}
			return super.onTransact(code, data, reply, flags);
		}
		static final int TRANSACTION_print = (IBinder.FIRST_CALL_TRANSACTION+0);
		static final int TRANSACTION_add = (IBinder.FIRST_CALL_TRANSACTION+1);
	}
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("start myAidl Service------");
	}
}


