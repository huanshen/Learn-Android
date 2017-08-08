package com.sjq.fragment2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentOne extends Fragment implements OnClickListener
{

    private Button mBtn;

    /**
     * 设置按钮点击的回调
     * @author zhy
     *
     */
    public interface FOneBtnClickListener
    {
        void onFOneBtnClick();
    }
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.fragment_menu:
                Toast.makeText( getActivity(), "FragmentMenuItem1", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        mBtn = (Button) view.findViewById(R.id.id_fragment_one_btn);
        mBtn.setOnClickListener(this);
        return view;
    }

    /**
     * 交给宿主Activity处理，如果它希望处理
     */
    @Override
    public void onClick(View v)
    {
        if (getActivity() instanceof FOneBtnClickListener)
        {
            ((FOneBtnClickListener) getActivity()).onFOneBtnClick();
        }
    }

}
