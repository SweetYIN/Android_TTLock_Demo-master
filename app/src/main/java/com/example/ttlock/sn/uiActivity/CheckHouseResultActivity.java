package com.example.ttlock.sn.uiActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ttlock.R;
import com.example.ttlock.sn.adapter.MyRecyclerViewAdapter;
import com.example.ttlock.sn.bean.Responds.HouseSearchResponsesBean;

import java.util.ArrayList;
import java.util.List;

public class CheckHouseResultActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "CheckHouseResultActivity";

    private TextView titleTV;
    private Button btnBack,btnModify;

    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private List<HouseSearchResponsesBean.DataBean> houseInfos = new ArrayList<>() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_house_result);
        houseInfos = ((HouseSearchResponsesBean)getIntent().getSerializableExtra("value"))
                .getData() ;
        initView();

    }

    private void initView() {
        titleTV = (TextView) findViewById(R.id.title_tv);
        titleTV.setText("房源信息");
        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.check_house_result_recyclerView);
    }
    private void initAdpter(){
        //设置布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(1,this,houseInfos);
        recyclerView.setAdapter(myRecyclerViewAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

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
}
