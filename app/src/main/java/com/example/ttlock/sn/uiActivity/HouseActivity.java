package com.example.ttlock.sn.uiActivity;

import android.app.Activity;
import android.content.Intent;
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
import com.example.ttlock.sn.adapter.MyRoomSearchViewAdapter;
import com.example.ttlock.sn.bean.Request.HouseSearchRequestBean;
import com.example.ttlock.sn.bean.Request.RoomSearchRequest;
import com.example.ttlock.sn.bean.Responds.RoomSearchResponses;
import com.example.ttlock.sn.callback.ClickCallback;
import com.example.ttlock.sn.network.ApiNet;
import com.example.ttlock.sn.view.DefineOtherStylesBAGRefreshWithLoadView;
import com.ttlock.bl.sdk.entity.LockData;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HouseActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate
,View.OnClickListener{
    private  String TAG = "HouseActivity";

    // 返回的结果码
    private static final int REQUESTCODE = 1;

    private Button btnBack;

    private TextView titleTV;
    private BGARefreshLayout mBgaRefreshLayout;

    private RecyclerView recyclerView;

    private DefineOtherStylesBAGRefreshWithLoadView mDefineBAGRefreshWithLoadView;

    private  MyRoomSearchViewAdapter myRoomSearchViewAdapter;

    private int ALLSUM ;

    private int PAGE =  1;

    private List<RoomSearchResponses> houseInfos = new ArrayList<>();

    private int houseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        houseId = getIntent().getIntExtra("houseId",0);
        initView();
        Log.e(TAG,"houseId"+houseId);
        requestData(houseId);
    }

    private void initView() {
        titleTV = (TextView) findViewById(R.id.title_tv);
        titleTV.setText("未绑定的房间");
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
        myRoomSearchViewAdapter = new MyRoomSearchViewAdapter(5,this,houseInfos);
        myRoomSearchViewAdapter.setClickCallback(mClickCallback);
        recyclerView.setAdapter(myRoomSearchViewAdapter);

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
                    requestData(houseId);
                    mBgaRefreshLayout.endRefreshing();
                    break;
                case 1:
                    PAGE++;
                    requestData(houseId);
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
    /**
     * 请求房间列表
     */
    private void requestData(int houseId){
            showProgressDialog();
            RoomSearchRequest roomSearchRequest = new RoomSearchRequest();
            roomSearchRequest.setHouseId(houseId+"");
            ApiNet apiNet = new ApiNet();
            apiNet.ApiRoomSearch(roomSearchRequest)
                   .subscribe(new Observer<List<RoomSearchResponses>>() {
                       @Override
                       public void onSubscribe(Disposable d) {
                       }

                       @Override
                       public void onNext(List<RoomSearchResponses> value) {
                           cancelProgressDialog();
                           if (value.size() == 0){
                               toast("该房源下没有可绑锁房间");
                           }else{
                               houseInfos.addAll(value) ;
                               myRoomSearchViewAdapter.notifyDataSetChanged();
                               if(value.size() % 10 == 0){
                                   ALLSUM = value.size() / 10;
                               }else{
                                   ALLSUM = (value.size() / 10)+1;
                               }
                           }

                       }

                       @Override
                       public void onError(Throwable e) {
                           cancelProgressDialog();
                           toast("异常"+e.getMessage());
                           Log.e(TAG,"houseInfos = "+e.getMessage());
                       }

                       @Override
                       public void onComplete() {

                       }
                   });



    }

    private ClickCallback mClickCallback = new ClickCallback() {
        @Override
        public void ItemOnClick(View v, int position) {
            openActivity(houseInfos.get(position).getId());
        }

        @Override
        public void OnItemClick(View view, int position) {

        }

        @Override
        public void OnItemLongClick(View view, int position) {

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

    private void openActivity(int id){
//        Intent  intent = new Intent(this, MainActivity.class);
        Intent  intent = new Intent(this, ConnectDeviceActivity.class);
        intent.putExtra("type","1");
        intent.putExtra("ID",id);
        startActivityForResult(intent,REQUESTCODE);
    }




}
