package com.example.ttlock.sn.showUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

import com.example.ttlock.R;
import com.example.ttlock.activity.BaseActivity;

public class DialogUtil {

    public ProgressDialog progressDialog;

    private Handler handler;

    private Context mContext;

    public DialogUtil(Context mContext){

    }

    public void showProgressDialog() {
        showProgressDialog("wating.....");
    }

    public void showProgressDialog(final String msg) {
        if(progressDialog == null) {
//            progressDialog = new ProgressDialog();
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.setMessage(msg);
        progressDialog.show();
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
    }

    public void cancelProgressDialog() {
        if(progressDialog != null)
            progressDialog.cancel();
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
    }

}
