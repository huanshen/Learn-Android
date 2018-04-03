package com.example.zy.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by ZY on 2017/2/23.
 */

public class ViewDialogFragment extends DialogFragment {

    public interface Callback {
        void onClick(String userName, String password);
    }

    private Callback callback;

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, "ViewDialogFragment");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
      //  setStyle(STYLE_NO_INPUT, android.R.style.Theme_DeviceDefault_Dialog);

        final View view = inflater.inflate(R.layout.dialog_signin, null);
        builder.setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callback != null) {
                            EditText et_userName = (EditText) view.findViewById(R.id.username);
                            EditText et_password = (EditText) view.findViewById(R.id.password);
                            callback.onClick(et_userName.getText().toString(), et_password.getText().toString());
                        }
                    }
                })
        ;
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            callback = (Callback) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement Callback");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        callback = null;
    }
}
