package com.example.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.constant.PersonInfo;
import com.example.cursoradapterpractice.R;

public class MyCursorAdapter extends CursorAdapter {
	
	Context context=null;

public MyCursorAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
	}

	public MyCursorAdapter(Context context, Cursor c) {
		super(context, c);
	}


	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		
		ViewHolder viewHolder=new ViewHolder();
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view=inflater.inflate(R.layout.item_contacts,parent,false);
		
		viewHolder.tv_name=(TextView) view.findViewById(R.id.tv_showusername);
		viewHolder.tv_phonenumber=(TextView) view.findViewById(R.id.tv_showusernumber);
		view.setTag(viewHolder);
		Log.i("cursor","newView="+view);
		return view;
	}
	
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		Log.i("cursor","bindView="+view);
		ViewHolder viewHolder=(ViewHolder) view.getTag();
		String name=cursor.getString(cursor.getColumnIndex(PersonInfo.NAME));//从数据库中查询姓名字段
		String phoneNumber=cursor.getString(cursor.getColumnIndex(PersonInfo.PHONENUMBER));//从数据库中查询电话字段
		
		viewHolder.tv_name.setText(name);
		viewHolder.tv_phonenumber.setText(phoneNumber);
	}
	
	@Override
	public void changeCursor(Cursor cursor) {
		super.changeCursor(cursor);
	}
	
	class ViewHolder{
		TextView tv_name;
		TextView tv_phonenumber;
	}

}
