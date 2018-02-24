package com.mj.utils.tools;

import android.content.Context;
import android.content.DialogInterface;

import com.mj.utils.view.SingleDialog;


/**
 * Created by tanlifei on 2017/11/20.
 */

public class CustomDialogUitls {

    public static final int OK = 1;
    public static final int CANCEL = 2;


    public static SingleDialog.Builder initSingleDialog(Context context, String title, String msg, String okTxt, final DialogBackcallInterface backcall) {
        SingleDialog.Builder builder = new SingleDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(msg);
        builder.setPositiveButton( "取消" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                backcall.dialogBackcall(dialog, OK);
                dialog.dismiss();
            }
        });
        builder.create().show();
        return builder;
    }


    public static SingleDialog.Builder defaultSingleDialog(Context context, String msg, final DialogBackcallInterface backcall) {
        return initSingleDialog(context, "", msg, "", backcall);
    }

    public interface DialogBackcallInterface {
        void dialogBackcall(DialogInterface dialog, int tag);
    }
}
