package com.example.ttlock.sn.uiActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ttlock.R;
import com.example.ttlock.activity.BaseActivity;
import com.example.ttlock.sn.bean.Responds.UserInfoResponses;
import com.example.ttlock.sn.bean.Responds.UserSessionResponses;
import com.example.ttlock.sn.network.ApiNet;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserActivity extends BaseActivity implements View.OnClickListener{

    private TextView titleTV;
    private Button btnBack;
    private ImageButton modifyPasswordTB;

    private TextView nickName_tv,userName_tv,password_tv,mobile_tv,email_tv,type_tv
            ,created_time_tv,updated_time_tv,group_tv,role_tv,salt_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
//        requestUserInfo();
        requestUserSessionInfo();
        initView();

    }



    private void initView() {
        titleTV = (TextView) findViewById(R.id.title_tv);
        titleTV.setText("我的资料");
        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        modifyPasswordTB = (ImageButton) findViewById(R.id.tb_modifyPassword);
        modifyPasswordTB.setOnClickListener(this);

        nickName_tv = (TextView) findViewById(R.id.nickName_tv);
        userName_tv = (TextView) findViewById(R.id.userName_tv);
        password_tv = (TextView) findViewById(R.id.password_tv);
        mobile_tv = (TextView) findViewById(R.id.mobile_tv);
        email_tv = (TextView) findViewById(R.id.email_tv);
        type_tv = (TextView) findViewById(R.id.type_tv);
        created_time_tv = (TextView) findViewById(R.id.created_time_tv);
        updated_time_tv = (TextView) findViewById(R.id.updated_time_tv);
        group_tv = (TextView) findViewById(R.id.group_tv);
        role_tv = (TextView) findViewById(R.id.role_tv);
        salt_tv = (TextView) findViewById(R.id.salt_tv);

    }

    private void setShowData(UserInfoResponses userInfoResponses){

        nickName_tv.setText(userInfoResponses.getNickname());
        userName_tv.setText(userInfoResponses.getUsername());
        password_tv.setText(userInfoResponses.getPassword());
        mobile_tv.setText(userInfoResponses.getMobile());
        email_tv.setText(userInfoResponses.getEmail());
        type_tv.setText(userInfoResponses.getType());
        created_time_tv.setText(userInfoResponses.getCreated());
        updated_time_tv.setText(userInfoResponses.getUpdated());
        group_tv.setText(userInfoResponses.getGroup().getName());
        role_tv.setText(userInfoResponses.getRole().getName());
        salt_tv.setText(userInfoResponses.getSalt());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_modify:
                Intent intent = new Intent(this,ModifyPasswordActivity.class);
                startActivity(intent);
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

    /**
     * 获取用户的session信息
     */
    private void requestUserSessionInfo(){
        showProgressDialog();
        ApiNet apiNet = new ApiNet();
        apiNet.ApiUserSessionInfo()
                .subscribe(new Observer<UserSessionResponses>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserSessionResponses value) {
                        cancelProgressDialog();
                        requestUserInfo(value.getUid());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    /**请求用户信息**/
    private void requestUserInfo(int userID) {
        showProgressDialog();
        ApiNet apiNet = new ApiNet();
        apiNet.ApiUserInfo("12")
                .subscribe(new Observer<UserInfoResponses>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserInfoResponses value) {
                        cancelProgressDialog();
                        setShowData(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        cancelProgressDialog();
                        toast("用户信息 "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
