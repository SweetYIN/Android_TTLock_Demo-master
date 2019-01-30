package com.example.ttlock.sn.uiActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ttlock.R;
import com.example.ttlock.activity.BaseActivity;
import com.example.ttlock.sn.bean.Request.LoginRequestBean;
import com.example.ttlock.sn.bean.Responds.LoginResponsesBean;
import com.example.ttlock.sn.network.ApiNet;
import com.example.ttlock.sn.network.HttpUrlConfig;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private EditText nameET, passWordET;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isNetwork();
        initView();
    }



    private void isNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable()) {
            Toast.makeText(this, "您没有联网，请联网之后再进行查询~", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        nameET = (EditText) findViewById(R.id.login_name_et);
        passWordET = (EditText) findViewById(R.id.login_password_et);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                requestData();
//                Intent intent = new Intent(this,StartActivity.class);
//                startActivity(intent);
                break;
        }
    }

    private void requestData() {
        LoginRequestBean loginRequestBean = new LoginRequestBean();
        ApiNet apiNet = new ApiNet();
        String basic;
        if (!TextUtils.isEmpty(nameET.getText()) && !TextUtils.isEmpty(passWordET.getText())) {
//            String authorization = nameET.getText().toString() +":" +passWordET.getText().toString();
            String authorization = "android-c" + ":" + "wyTaK5gqpMVhEfWB8djf";
            basic = "Basic " + Base64.encodeToString(authorization.getBytes(), Base64.NO_WRAP);
            loginRequestBean.setUsername(nameET.getText().toString());
            loginRequestBean.setPassword(passWordET.getText().toString());
        } else {
            Toast.makeText(this, "用户名或密码为空", Toast.LENGTH_LONG).show();
            return;
        }
        showProgressDialog();
        apiNet.ApiLogin(basic, nameET.getText().toString(), passWordET.getText().toString())
                .subscribe(new Observer<LoginResponsesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResponsesBean value) {
                        cancelProgressDialog();
                        if (TextUtils.isEmpty(value.getAccess_token())) {
                            toast("");
                        } else {
                            HttpUrlConfig.Token = value.getAccess_token();
//                       uploadToken(HttpUrlConfig.Token);
                            openActivity();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        cancelProgressDialog();
                        toast("登录异常 " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void uploadToken(String token) {
        ApiNet apiNet = new ApiNet();
        if (TextUtils.isEmpty(token)) {
            cancelProgressDialog();
            toast("登录失败");
        } else {
            apiNet.ApiLoginSelect()
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(String value) {
                            cancelProgressDialog();
                            openActivity();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }


    private void openActivity() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);

    }
}
