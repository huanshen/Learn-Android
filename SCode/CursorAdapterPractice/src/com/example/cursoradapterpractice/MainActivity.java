package com.example.cursoradapterpractice;

import com.example.adapter.MyCursorAdapter;
import com.example.constant.PersonInfo;
import com.example.database.InfoDatabaseHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static final String TAG = "MainActivity";
	private SQLiteDatabase dataBase;
	private SQLiteOpenHelper openHelper=null;
	private MyCursorAdapter myCursorAdapter;
	
	private EditText et_name;
	private EditText et_phonenumber;
	private Button btn_save;
	private ListView lv_test;
	
	private String userName;
	private String userPhoneNumber;
	private String orderBy;//查询数据库的排序
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViews();
		initData();
		setClickListener();
	}
	
	private void findViews() {
		et_name=(EditText) findViewById(R.id.et_name);
		et_phonenumber=(EditText) findViewById(R.id.et_phonenumber);
		btn_save=(Button) findViewById(R.id.btn_save);
		lv_test=(ListView) findViewById(R.id.lv_test);
	}
	
	private void initData() {
		openHelper=new InfoDatabaseHelper(this);
		dataBase=openHelper.getWritableDatabase();
		
		orderBy="_id desc";
		Cursor myCursor = dataBase.query(PersonInfo.PERSON_INFO_TABLE,null,null,null,null,null,orderBy);
		myCursorAdapter=new MyCursorAdapter(MainActivity.this,myCursor);
	    lv_test.setAdapter(myCursorAdapter);
	}
	
	private void setClickListener() {
		
		btn_save.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				userName=et_name.getText().toString();
			    userPhoneNumber=et_phonenumber.getText().toString();
			    
			    if(userName.equals("")){
			    	Toast.makeText(MainActivity.this, "用户名不能为空!",0).show();
			    	return;
			    }
			    if(userPhoneNumber.equals("")){
			    	Toast.makeText(MainActivity.this,"电话不能为空",0).show();
			    	return;
			    }
			    
			    ContentValues contentValues=new ContentValues();
			    contentValues.put(PersonInfo.NAME,userName);
			    contentValues.put(PersonInfo.PHONENUMBER,userPhoneNumber);
			    //把EditText中的文本插入数据库
			    dataBase.insert(PersonInfo.PERSON_INFO_TABLE,null,contentValues);
			    //根据 _id 降序插叙数据库保证最后插入的在最上面
			    Cursor myCursor = dataBase.query(PersonInfo.PERSON_INFO_TABLE,null,null,null,null,null,orderBy);
			    //Cursor改变调用chanageCursor()方法
			    myCursorAdapter.changeCursor(myCursor);
			}
		});
	}
}

