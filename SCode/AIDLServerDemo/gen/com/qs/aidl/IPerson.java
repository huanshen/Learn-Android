/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: G:\\new_workspace_smartRoute\\AIDLServerDemo\\src\\com\\qs\\aidl\\IPerson.aidl
 */
package com.qs.aidl;
public interface IPerson extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.qs.aidl.IPerson
{
private static final java.lang.String DESCRIPTOR = "com.qs.aidl.IPerson";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.qs.aidl.IPerson interface,
 * generating a proxy if needed.
 */
public static com.qs.aidl.IPerson asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.qs.aidl.IPerson))) {
return ((com.qs.aidl.IPerson)iin);
}
return new com.qs.aidl.IPerson.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_greet:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.greet(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_add:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _result = this.add(_arg0, _arg1);
reply.writeNoException();
reply.writeString(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.qs.aidl.IPerson
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public java.lang.String greet(java.lang.String someone) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(someone);
mRemote.transact(Stub.TRANSACTION_greet, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String add(java.lang.String a, java.lang.String b) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(a);
_data.writeString(b);
mRemote.transact(Stub.TRANSACTION_add, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_greet = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_add = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public java.lang.String greet(java.lang.String someone) throws android.os.RemoteException;
public java.lang.String add(java.lang.String a, java.lang.String b) throws android.os.RemoteException;
}
