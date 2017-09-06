package com.qs.aidlclientdemo;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public interface MyPrinterInterface extends android.os.IInterface{
	public void print(String msg) throws android.os.RemoteException;
	public String add(String a ,String b) throws android.os.RemoteException;
	public abstract class Stub extends Binder implements MyPrinterInterface
	{
		private static final String DESCRIPTOR="MyPrinterInterface";
		public Stub(){
			attachInterface(this, DESCRIPTOR);
		}
		
		public static MyPrinterInterface asInterface(IBinder obj)
		{
			if(obj==null)return null;
		      System.out.println("we are talking to a remote one, we must use a proxy object to wrapper binder");  
	            return new Stub.Proxy(obj);  
		}
		static final int TRANSACTION_print =(IBinder.FIRST_CALL_TRANSACTION+0);
		static final int TRANSACTION_add =(IBinder.FIRST_CALL_TRANSACTION+1);
		private static class Proxy implements MyPrinterInterface
		{
			private IBinder mRemote;
			Proxy(IBinder remote)
			{
				mRemote = remote;
			}
			@Override
			public IBinder asBinder() {
				return mRemote;
			}

			@Override
			public void print(String msg) throws RemoteException {
				Parcel _data = Parcel.obtain();
				Parcel _reply =Parcel.obtain();
				try {
					_data.writeInterfaceToken(DESCRIPTOR);
					_data.writeString(msg);
					mRemote.transact(Stub.TRANSACTION_print, _data, _reply, 0);
				} catch (Exception e) {
				}finally{
					_data.recycle();
					_reply.recycle();
				}
			}
			@Override
			public String add(String a, String b) throws RemoteException {
				Parcel _data = Parcel.obtain();
				Parcel _reply =Parcel.obtain();
				String _result = null;
				try {
					_data.writeInterfaceToken(DESCRIPTOR);
					_data.writeString(a);
					_data.writeString(b);
					mRemote.transact(Stub.TRANSACTION_add, _data, _reply, 0);
					_reply.readException();
					_result = _reply.readString();
				} catch (Exception e) {
				}finally{
					_data.recycle();
					_reply.recycle();
				}
				return _result;
			}
			
		}
	}
}

