package com.example.ttlock.sn.uiActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ttlock.R;
import com.example.ttlock.sn.bean.Responds.UserInfoResponses;
import com.example.ttlock.sn.network.ApiNet;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView titleTV;
    private Button btnBack,btnModify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        requestUserInfo();
        initView();

    }



    private void initView() {
        titleTV = (TextView) findViewById(R.id.title_tv);
        titleTV.setText("我的资料");
        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        btnModify = (Button) findViewById(R.id.btn_modify);
        btnModify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_modify:
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
    /**请求用户信息**/
    private void requestUserInfo() {
        ApiNet apiNet = new ApiNet();
        apiNet.ApiUserInfo("12")
                .subscribe(new Observer<UserInfoResponses>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserInfoResponses value) {

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
