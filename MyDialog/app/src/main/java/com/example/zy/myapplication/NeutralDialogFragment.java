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

public class NeutralDialogFragment extends DialogFragment {

    private DialogInterface.OnClickListener neutralCallback;

    private String title;

    private String message;

    private String hint;

    public void show(String title, String message, String hint, DialogInterface.OnClickListener neutralCallback,
                     FragmentManager fragmentManager) {
        this.title = title;
        this.message = message;
        this.hint = hint;
        this.neutralCallback = neutralCallback;
        show(fragmentManager, "NeutralDialogFragment");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton(hint, neutralCallback);
        return builder.create();
    }

}
