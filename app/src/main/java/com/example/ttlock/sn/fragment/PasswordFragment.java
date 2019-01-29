package com.example.ttlock.sn.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ttlock.R;
import com.example.ttlock.sn.adapter.MyRecyclerViewAdapter;
import com.example.ttlock.sn.bean.Request.HouseSearchRequestBean;
import com.example.ttlock.sn.bean.Responds.HouseSearchResponsesBean;
import com.example.ttlock.sn.callback.ClickCallback;
import com.example.ttlock.sn.network.ApiNet;
import com.example.ttlock.sn.uiActivity.ConnectDeviceActivity;
import com.example.ttlock.sn.view.DefineOtherStylesBAGRefreshWithLoadView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate{

    private static final String TAG = "HouseFragment";
    private Context mContext;

    private RecyclerView recyclerView;

    private BGARefreshLayout mBgaRefreshLayout;

    private DefineOtherStylesBAGRefreshWithLoadView mDefineBAGRefreshWithLoadView;

    private  MyRecyclerViewAdapter  myRecyclerViewAdapter;

    private List<HouseSearchResponsesBean.DataBean> houseInfos = new ArrayList<>() ;

    private int ALLSUM ;
    //分页请求
    private int PAGE =  1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e(TAG,"PasswordF onCreateView");
        View view =  inflater.inflate(R.layout.fragment_password, container, false);
        initView(view);
        initAdatper();
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG,"PasswordF onViewCreated");
        if (null == houseInfos && getUserVisibleHint()) {
//            requestData();//解决第一个fragment无法加载数据问题
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e(TAG,"PasswordF setUserVisibleHint"+"isVisibleToUser "+isVisibleToUser
                +"isVisible" +isVisible());
//        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isVisible()) {//视图可见并且控件准备好了，每次都会调用
            if ( houseInfos.size() == 0) {//如果数据为空了，则需要重新联网请求
                requestData();
            }
        }
    }

    private void initView(View view) {
        mBgaRefreshLayout =(BGARefreshLayout) view.findViewById(R.id.rl_passwordFragment_refresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.password_recyclerView);
        //设置刷新和加载监听
        mBgaRefreshLayout.setDelegate(this);
        mDefineBAGRefreshWithLoadView = new DefineOtherStylesBAGRefreshWithLoadView(mContext , true , true);
        //设置刷新样式
        mBgaRefreshLayout.setRefreshViewHolder(mDefineBAGRefreshWithLoadView);
        mDefineBAGRefreshWithLoadView.updateLoadingMoreText("上拉加载更多");


    }

    private void initAdatper(){
        //设置布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(2,mContext,houseInfos);
        myRecyclerViewAdapter.setClickCallback(mClickCallback);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }


    private ClickCallback mClickCallback = new ClickCallback() {
        @Override
        public void ItemOnClick(View v, int position) {
            Intent intent = new Intent(mContext, ConnectDeviceActivity.class);
            intent.putExtra("type","2");
            startActivity(intent);
        }

        @Override
        public void OnItemClick(View view, int position) {

        }

        @Override
        public void OnItemLongClick(View view, int position) {

        }
    };




    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mDefineBAGRefreshWithLoadView.updateLoadingMoreText("自定义加载更多");
        mDefineBAGRefreshWithLoadView.showLoadingMoreImg();
        ALLSUM = 0;
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
                    houseInfos.clear();
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
        pagingBean.setNumber(1);
        pagingBean.setSize(10);
        resourcesRequestBean.setPaging(pagingBean);
//        resourcesRequestBean.setRoomState("CLEANING");
        return resourcesRequestBean;
    }

    private void requestData(){
        HouseSearchRequestBean houseSearchRequestBean = getRequestDate();
        ApiNet apiNet = new ApiNet();
        apiNet.ApiHouseSearch(houseSearchRequestBean)
                .subscribe(new Observer<HouseSearchResponsesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HouseSearchResponsesBean value) {
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

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
