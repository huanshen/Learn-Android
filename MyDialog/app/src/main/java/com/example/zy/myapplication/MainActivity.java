package com.example.zy.myapplication;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewDialogFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDialog.showRalModelGuideDialog(MainActivity.this);
    }

    public void showButtonDialogFragment(View view) {
        ButtonDialogFragment buttonDialogFragment = new ButtonDialogFragment();
        buttonDialogFragment.show("Hi,你好", "叶应是叶", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "点击了确定 " + which, Toast.LENGTH_SHORT).show();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "点击了取消 " + which, Toast.LENGTH_SHORT).show();
            }
        }, getFragmentManager());
    }

    public void showNeutralDialogFragment(View view) {
        NeutralDialogFragment neutralDialogFragment = new NeutralDialogFragment();
        neutralDialogFragment.show("Hi,你好", "叶应是叶", "确定~", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "点击了按钮 " + which, Toast.LENGTH_SHORT).show();
            }
        }, getFragmentManager());
    }

    public void showItemsDialogFragment(View view) {
        ItemsDialogFragment itemsDialogFragment = new ItemsDialogFragment();
        String[] items = {"Hi", "Hello", "叶"};
        itemsDialogFragment.show("Hi,你好", items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "点击了第 " + (which + 1) + " 个选项", Toast.LENGTH_SHORT).show();
            }
        }, getFragmentManager());
    }

    public void showMultiChoiceDialogFragment(View view) {
        MultiChoiceDialogFragment multiChoiceDialogFragment = new MultiChoiceDialogFragment();
        final String[] items = {"Hi", "Hello", "叶"};
        final List<Integer> integerList = new ArrayList<>();
        multiChoiceDialogFragment.show("Hi,你好", items, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    integerList.add(which);
                } else {
                    integerList.remove(which);
                }
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hint = "";
                for (int i = 0; i < integerList.size(); i++) {
                    hint = items[integerList.get(i)] + hint;
                }
                Toast.makeText(MainActivity.this, hint, Toast.LENGTH_SHORT).show();
            }
        }, getFragmentManager());
    }

    private int index;

    public void showSingleChoiceDialogFragment(View view) {
        SingleChoiceDialogFragment singleChoiceDialogFragment = new SingleChoiceDialogFragment();
        String[] items = {"Hi", "Hello", "叶"};

        singleChoiceDialogFragment.show("Hi,你好", items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                index = which;
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "选择了第" + (index + 1) + "项", Toast.LENGTH_SHORT).show();
            }
        }, getFragmentManager());
    }

    public void showViewDialogFragment(View view) {
        ViewDialogFragment viewDialogFragment = new ViewDialogFragment();
        viewDialogFragment.show(getFragmentManager());
    }

    @Override
    public void onClick(String userName, String password) {
      //  Toast.makeText(MainActivity.this, "用户名: " + userName + " 密码: " + password, Toast.LENGTH_SHORT).show();
      //  showDialog();
        showMultiBtnDialog();
    }

    void showDialog() {
        int mStackLevel = 2;

        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = MyDialogFragment.newInstance(mStackLevel);
        newFragment.show(ft, "dialog");
    }

    private void showMultiBtnDialog(){
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MainActivity.this);
        normalDialog.setIcon(R.drawable.head);
        normalDialog.setTitle("我是一个普通Dialog").setMessage("你要点击哪一个按钮呢?");
        normalDialog.setPositiveButton("按钮1",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ...To-do
                    }
                });
        normalDialog.setNeutralButton("按钮2",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ...To-do
                    }
                });
        normalDialog.setNegativeButton("按钮3", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // ...To-do
            }
        });
        // 创建实例并显示
        normalDialog.show();
    }


}
