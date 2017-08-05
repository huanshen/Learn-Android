package com.example.simplecursoradapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by shenjiaqi on 2017/8/4.
 */

public class MyAdapter extends CursorAdapter {
    public MyAdapter(Context context, Cursor c, boolean autoRequery){
        super(context, c , autoRequery);
    }

    public MyAdapter(Context context, Cursor c) {
        super(context, c);
    }

    public void bindView(View view, Context context, Cursor cursor){
        ViewHolder viewHolder=(ViewHolder) view.getTag();
        String name=cursor.getString(cursor.getColumnIndex(Person.NAME));//从数据库中查询姓名字段
        String phoneNumber=cursor.getString(cursor.getColumnIndex(Person.PHONENUMBER));//从数据库中查询电话字段

        viewHolder.tv_name.setText(name);
        viewHolder.tv_phonenumber.setText(phoneNumber);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent){
        ViewHolder viewHolder=new ViewHolder();
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.item,parent,false);
        // viewHolder 找到相关视图
        viewHolder.tv_name=(TextView) view.findViewById(R.id.tv_showusername);
        viewHolder.tv_phonenumber=(TextView) view.findViewById(R.id.tv_showusernumber);
        view.setTag(viewHolder);
        Log.i("cursor","newView="+view);
        return view;
    }

    class ViewHolder{
        TextView tv_name;
        TextView tv_phonenumber;
    }
}
