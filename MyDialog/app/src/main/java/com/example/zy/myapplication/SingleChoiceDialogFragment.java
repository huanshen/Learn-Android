package com.example.zy.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by ZY on 2017/2/23.
 */

public class SingleChoiceDialogFragment extends DialogFragment {

    private String title;

    private String[] items;

    private DialogInterface.OnClickListener onClickListener;

    private DialogInterface.OnClickListener positiveCallback;

    public void show(String title, String[] items, DialogInterface.OnClickListener onClickListener,
                     DialogInterface.OnClickListener positiveCallback, FragmentManager fragmentManager) {
        this.title = title;
        this.items = items;
        this.onClickListener = onClickListener;
        this.positiveCallback = positiveCallback;
        show(fragmentManager, "SingleChoiceDialogFragment");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title).setSingleChoiceItems(items, 0, onClickListener)
                .setPositiveButton("确定", positiveCallback);
        return builder.create();
    }

}
