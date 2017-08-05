package com.example.simplecursoradapter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SQLiteDatabase dataBase;
    private SQLiteOpenHelper openHelper = null;
    private MyAdapter myAdapter;

    private EditText et_name;
    private EditText et_phoneNumber;
    private Button btn_save;
    private ListView lv_test;

    private String userName;
    private String userPhoneNumber;
    private String orderBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initData();
        setClickListener();
    }

    private void findViews(){
        et_name = (EditText) findViewById(R.id.et_name);
        et_phoneNumber = (EditText) findViewById(R.id.et_phonenumber);
        btn_save = (Button) findViewById(R.id.btn_save);
        lv_test = (ListView) findViewById(R.id.lv_test);
    }

    private void initData() {
        openHelper = new InfoDatabaseHelper(this);
        dataBase = openHelper.getWritableDatabase();

        orderBy = "_id desc";

        Cursor cursor = dataBase.query(Person.PERSON_INFO_TABLE, null, null, null, null, null, orderBy);
        myAdapter = new MyAdapter(MainActivity.this, cursor);
        lv_test.setAdapter(myAdapter);
    }

    private void setClickListener() {

        btn_save.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                userName=et_name.getText().toString();
                userPhoneNumber=et_phoneNumber.getText().toString();

                if(userName.equals("")){
                    Toast.makeText(MainActivity.this, "用户名不能为空!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userPhoneNumber.equals("")){
                    Toast.makeText(MainActivity.this,"电话不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                ContentValues contentValues=new ContentValues();
                contentValues.put(Person.NAME,userName);
                contentValues.put(Person.PHONENUMBER,userPhoneNumber);
                //把EditText中的文本插入数据库
                dataBase.insert(Person.PERSON_INFO_TABLE,null,contentValues);
                //根据 _id 降序插叙数据库保证最后插入的在最上面
                Cursor myCursor = dataBase.query(Person.PERSON_INFO_TABLE,null,null,null,null,null,orderBy);
                //Cursor改变调用chanageCursor()方法
                myAdapter.changeCursor(myCursor);
            }
        });
    }
}
