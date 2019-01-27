package com.example.ttlock.sn.uiActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ttlock.R;
import com.example.ttlock.activity.BaseActivity;
import com.example.ttlock.sn.adapter.MyRecyclerViewAdapter;
import com.example.ttlock.sn.bean.Request.HouseSearchRequestBean;
import com.example.ttlock.sn.bean.Responds.HouseSearchResponsesBean;
import com.example.ttlock.sn.network.ApiNet;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CheckHouseResultActivity extends BaseActivity implements View.OnClickListener {

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
        String query = getIntent().getStringExtra("query");

        initView();
        requestData(query);

    }

    private void initView() {
        titleTV = (TextView) findViewById(R.id.title_tv);
        titleTV.setText("房源信息");
        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.check_house_result_recyclerView);
        initAdpter();
    }
    private void initAdpter(){
        //设置布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(5,this,houseInfos);
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
    /**
     * 请求要查找的房源
     */
    private void requestData(String query){

        HouseSearchRequestBean resourcesRequestBean  = new HouseSearchRequestBean();
        resourcesRequestBean.setSerialNumber(query);
        ApiNet apiNet = new ApiNet();
        apiNet.ApiHouseSearch(resourcesRequestBean)
                .subscribe(new Observer<HouseSearchResponsesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                            d.dispose();
                    }

                    @Override
                    public void onNext(HouseSearchResponsesBean value) {
                        Log.e(TAG,"value.getData() = "+value.getData().size());

                        houseInfos.addAll(value.getData()) ;
                        myRecyclerViewAdapter.notifyDataSetChanged();
//                        if(value.getTotal() % 10 == 0){
//                            ALLSUM = value.getTotal() / 10;
//                        }else{
//                            ALLSUM = (value.getTotal() / 10)+1;
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        cancelProgressDialog();
                        Log.e(TAG,"e " +e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
