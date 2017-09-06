package com.example.expandablelistactivitytest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.SimpleExpandableListAdapter;
//首先继承ExpandableListActivity
public class MyExpendableListActivityActivity extends ExpandableListActivity{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Map<String,String>> list = new ArrayList<Map<String,String>>();//组名
        Map<String,String> map1 = new HashMap<String,String>();
        map1.put("group", "国家");
        Map<String,String> map2 = new HashMap<String,String>();
        map2.put("group", "语言");
        list.add(map1);
        list.add(map2);

        List<Map<String,String>> listChild1 = new ArrayList<Map<String,String>>();//child
        Map<String,String> map3 = new HashMap<String,String>();
        map3.put("country", "中国");
        listChild1.add(map3);
        Map<String,String> map4 = new HashMap<String,String>();
        map4.put("country", "美国");
        listChild1.add(map4);

        List<Map<String,String>> listChild2 = new ArrayList<Map<String,String>>();//child
        Map<String,String> map5 = new HashMap<String,String>();
        map5.put("country", "汉语");
        listChild2.add(map5);
        Map<String,String> map6 = new HashMap<String,String>();
        map6.put("country", "英语");
        listChild2.add(map6);

        List<List<Map<String,String>>> childs = new  ArrayList<List<Map<String,String>>>();//将两个child加入的集合中
        childs.add(listChild1);
        childs.add(listChild2);
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this, list, R.layout.group, new String[]{"group"},
                new int[]{R.id.tv_group}, childs, R.layout.child, new String[]{"country"}, new int[]{R.id.tv_child});
        setListAdapter(adapter);//适配器
    }
}
