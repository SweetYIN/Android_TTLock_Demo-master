package com.example.ttlock.sn.uiActivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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
import com.example.ttlock.sn.adapter.MyRoomSearchViewAdapter;
import com.example.ttlock.sn.bean.Request.HouseSearchRequestBean;
import com.example.ttlock.sn.bean.Request.RoomSearchRequest;
import com.example.ttlock.sn.bean.Responds.HouseSearchResponsesBean;
import com.example.ttlock.sn.bean.Responds.RoomSearchResponses;
import com.example.ttlock.sn.callback.ClickCallback;
import com.example.ttlock.sn.network.ApiNet;
import com.example.ttlock.sn.view.DefineOtherStylesBAGRefreshWithLoadView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class HouseResourceActivity extends BaseActivity implements View.OnClickListener,
        BGARefreshLayout.BGARefreshLayoutDelegate{
    private static  final String TAG = "HouseResourceActivity";
    // 返回的结果码
    private static final int REQUESTCODE = 1;

    private RecyclerView recyclerView;

    private BGARefreshLayout mBgaRefreshLayout;

    private DefineOtherStylesBAGRefreshWithLoadView mDefineBAGRefreshWithLoadView;
//    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private MyRoomSearchViewAdapter myRecyclerViewAdapter;
//    private List<HouseSearchResponsesBean.DataBean> houseInfos = new ArrayList<>();

    private List<RoomSearchResponses> houseInfos = new ArrayList<>();

    private Button btnBack;

    private TextView titleTV;
    private int ALLSUM ;
    private int PAGE =  1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_resource);
        initView();
        initAdapter();
        requestData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        requestData();
        Log.e(TAG,"onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    private void initView() {
        mBgaRefreshLayout = (BGARefreshLayout)findViewById(R.id.br_houseResource_refresh);
        titleTV = (TextView) findViewById(R.id.title_tv);
        titleTV.setText("未绑定的房源");
        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.house_recyclerView_resource);
        //设置刷新和加载监听
        mBgaRefreshLayout.setDelegate(this);
        mDefineBAGRefreshWithLoadView = new DefineOtherStylesBAGRefreshWithLoadView(this , true , true);
        //设置刷新样式
        mBgaRefreshLayout.setRefreshViewHolder(mDefineBAGRefreshWithLoadView);
        mDefineBAGRefreshWithLoadView.updateLoadingMoreText("上拉加载更多");
    }

    private void initAdapter() {

        //设置布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
//        myRecyclerViewAdapter = new MyRoomSearchViewAdapter(4,this,houseInfos);
        myRecyclerViewAdapter = new MyRoomSearchViewAdapter(5,this,houseInfos);
        myRecyclerViewAdapter.setClickCallback(mClickCallback);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }






    private ClickCallback mClickCallback = new ClickCallback() {
        @Override
        public void ItemOnClick(View v, int position) {
            openActivity2(houseInfos.get(position).getHouse().getId());
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

    private void openActivity(int houseId){
        Intent intent = new Intent(this,HouseActivity.class);
        intent.putExtra("houseId",houseId);
        startActivity(intent);
    }

    private void openActivity2(int id){
//        Intent  intent = new Intent(this, MainActivity.class);
        Intent  intent = new Intent(this, ConnectDeviceActivity.class);
        intent.putExtra("type","1");
        intent.putExtra("ID",id);
        startActivityForResult(intent,REQUESTCODE);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }




    /**
     * 请求未绑定房源
     */
    private void requestData(){
        showProgressDialog();
        RoomSearchRequest roomSearchRequest = new RoomSearchRequest();
        roomSearchRequest.setRoomState("CONFIGURATION");
        ApiNet apiNet = new ApiNet();
        apiNet.ApiRoomSearch(roomSearchRequest)
                .subscribe(new Observer<List<RoomSearchResponses>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<RoomSearchResponses> value) {
                        cancelProgressDialog();
                        houseInfos.addAll(value) ;
                        myRecyclerViewAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        cancelProgressDialog();
                        toast("查房列表异常"+e.getMessage());
                        Log.e(TAG,"houseInfos = "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private HouseSearchRequestBean getRequestDate(){
        HouseSearchRequestBean resourcesRequestBean  = new HouseSearchRequestBean();
        HouseSearchRequestBean.PagingBean pagingBean = new HouseSearchRequestBean.PagingBean();
        pagingBean.setNumber(PAGE);
        pagingBean.setSize(10);
        resourcesRequestBean.setPaging(pagingBean);
        resourcesRequestBean.setRoomState("CONFIGURATION");//正式字段
//        resourcesRequestBean.setRoomState("READY");//测试字段
        return resourcesRequestBean;
    }


}
