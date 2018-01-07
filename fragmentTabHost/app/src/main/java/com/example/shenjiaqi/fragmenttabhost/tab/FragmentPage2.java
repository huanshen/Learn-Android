package com.example.shenjiaqi.fragmenttabhost.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shenjiaqi.fragmenttabhost.R;

/**
 * Created by shenjiaqi on 2017/11/5.
 */

public class FragmentPage2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2, null);
    }
}
