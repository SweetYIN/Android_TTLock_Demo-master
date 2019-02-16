package com.example.ttlock.sn.uiActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ttlock.R;
import com.example.ttlock.activity.BaseActivity;
import com.example.ttlock.sn.network.ApiNet;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ModifyPasswordActivity extends BaseActivity implements View.OnClickListener {

    private EditText passwordET;
    private Button btnModify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        initViewO();
    }

    private void initViewO() {
        passwordET = findViewById(R.id.et_modify_password);
        btnModify = findViewById(R.id.btn_modify);
        btnModify.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_modify:
                showProgressDialog();
                requestModify();

                break;
        }
    }

    private void requestModify() {
        ApiNet apiNet = new ApiNet();
        if (!TextUtils.isEmpty(passwordET.getText())){

        apiNet.ApiModifyPassword("",passwordET.getText().toString())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        cancelProgressDialog();
                        toast("修改密码成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        cancelProgressDialog();
                        toast("修改密码失败"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        }
    }
}
