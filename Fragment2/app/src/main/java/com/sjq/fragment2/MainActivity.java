package com.sjq.fragment2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;


public class MainActivity extends Activity implements FragmentOne.FOneBtnClickListener,
        FragmentTwo.FTwoBtnClickListener
{

    private FragmentOne mFOne;
    private FragmentTwo mFTwo;
    private FragmentThree mFThree;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mFOne = new FragmentOne();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.add(R.id.id_content, mFOne, "ONE");
        tx.commit();

       /* if (savedInstanceState == null)
        {
            mFOne = new FragmentOne();
            FragmentManager fm1 = getFragmentManager();
            FragmentTransaction tx1 = fm1.beginTransaction();
            tx1.add(R.id.id_content, mFOne, "ONE");
            tx1.commit();
        }*/

    }





    /**
     * FragmentOne 按钮点击时的回调
     */
    @Override
    public void onFOneBtnClick()
    {

        if (mFTwo == null)
        {
            mFTwo = new FragmentTwo();
            mFTwo.setfTwoBtnClickListener(this);
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.id_content, mFTwo, "TWO");
        tx.addToBackStack(null);
        tx.commit();
    }

    /**
     * FragmentTwo 按钮点击时的回调
     */
    @Override
    public void onFTwoBtnClick()
    {
        if (mFThree == null)
        {
            mFThree = new FragmentThree();

        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.hide(mFTwo);
        tx.add(R.id.id_content, mFThree, "THREE");
        // tx.replace(R.id.id_content, fThree, "THREE");
        tx.addToBackStack(null);
        tx.commit();
    }

}
