package com.example.ttlock.sn.uiActivity;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
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
import com.example.ttlock.sn.callback.ClickCallback;
import com.example.ttlock.sn.network.ApiNet;
import com.example.ttlock.sn.view.DefineOtherStylesBAGRefreshWithLoadView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HouseActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate
,View.OnClickListener{
    private static  final String TAG = "HouseActivity";
    private Button btnBack;

    private TextView titleTV;
    private BGARefreshLayout mBgaRefreshLayout;

    private RecyclerView recyclerView;

    private DefineOtherStylesBAGRefreshWithLoadView mDefineBAGRefreshWithLoadView;

    private  MyRecyclerViewAdapter myRecyclerViewAdapter;

    private int ALLSUM ;

    private int PAGE =  1;

    private List<HouseSearchResponsesBean.DataBean> houseInfos = new ArrayList<HouseSearchResponsesBean.DataBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        initView();
        requestData();

    }

    private void initView() {
        titleTV = (TextView) findViewById(R.id.title_tv);
        titleTV.setText("未绑定的房源");
        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        mBgaRefreshLayout =(BGARefreshLayout) findViewById(R.id.rl_House_refresh);
        recyclerView = (RecyclerView) findViewById(R.id.houses_recyclerView);
        //设置刷新和加载监听
        mBgaRefreshLayout.setDelegate(this);
        mDefineBAGRefreshWithLoadView = new DefineOtherStylesBAGRefreshWithLoadView(this , true , true);
        //设置刷新样式
        mBgaRefreshLayout.setRefreshViewHolder(mDefineBAGRefreshWithLoadView);
        mDefineBAGRefreshWithLoadView.updateLoadingMoreText("上拉加载更多");
        initAdpter();
    }
    private void initAdpter(){
        //设置布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
         myRecyclerViewAdapter = new MyRecyclerViewAdapter(5,this,houseInfos);
        myRecyclerViewAdapter.setClickCallback(mClickCallback);
        recyclerView.setAdapter(myRecyclerViewAdapter);

    }




    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mDefineBAGRefreshWithLoadView.updateLoadingMoreText("下拉刷新");
        mDefineBAGRefreshWithLoadView.showLoadingMoreImg();
        handler.sendEmptyMessageDelayed(0 , 2000);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        Log.e(TAG,"上拉加载"+PAGE +"ALLSUM"+ALLSUM);

        if( PAGE == ALLSUM){
            /** 设置文字 **/
            mDefineBAGRefreshWithLoadView.updateLoadingMoreText("没有更多数据");
            /** 隐藏图片 **/
            mDefineBAGRefreshWithLoadView.hideLoadingMoreImg();
            handler.sendEmptyMessageDelayed(2 , 2000);
            return true;
        }

        handler.sendEmptyMessageDelayed(1 , 2000);
        return true;
    }
    /** 模拟请求网络数据 */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    if (PAGE != ALLSUM){
                        houseInfos.clear();
                    }
                    PAGE++;
                    requestData();
                    mBgaRefreshLayout.endRefreshing();
                    break;
                case 1:
                    PAGE++;
                    requestData();
                    mBgaRefreshLayout.endLoadingMore();
                    break;
                case 2:
                    mBgaRefreshLayout.endLoadingMore();
                    break;
                default:
                    break;
            }
        }
    };

    private HouseSearchRequestBean getRequestDate(){
        HouseSearchRequestBean resourcesRequestBean  = new HouseSearchRequestBean();
        HouseSearchRequestBean.PagingBean pagingBean = new HouseSearchRequestBean.PagingBean();
        pagingBean.setNumber(PAGE);
        pagingBean.setSize(10);
        resourcesRequestBean.setPaging(pagingBean);
//        resourcesRequestBean.setRoomState("CONFIGURATION");//正式字段
        resourcesRequestBean.setRoomState("READY");//测试字段
        return resourcesRequestBean;
    }
    /**
     * 请求房源
     */
    private void requestData(){
        HouseSearchRequestBean houseSearchRequestBean = getRequestDate();
        ApiNet apiNet = new ApiNet();
        apiNet.ApiHouseSearch(houseSearchRequestBean)
                .subscribe(new Observer<HouseSearchResponsesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                            d.dispose();
                    }

                    @Override
                    public void onNext(HouseSearchResponsesBean value) {
//
                        houseInfos.addAll(value.getData()) ;
                        myRecyclerViewAdapter.notifyDataSetChanged();
                        if(value.getTotal() % 10 == 0){
                            ALLSUM = value.getTotal() / 10;
                        }else{
                            ALLSUM = (value.getTotal() / 10)+1;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"e " +e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private ClickCallback mClickCallback = new ClickCallback() {
        @Override
        public void ItemOnClick(View v) {

            openActivity();
        }

        @Override
        public void OnItemClick(View view) {

        }

        @Override
        public void OnItemLongClick(View view) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }

    private void openActivity(){
//        Intent  intent = new Intent(this, MainActivity.class);
        Intent  intent = new Intent(this, ConnectDeviceActivity.class);
        intent.putExtra("type","1");
        startActivity(intent);
    }

}
