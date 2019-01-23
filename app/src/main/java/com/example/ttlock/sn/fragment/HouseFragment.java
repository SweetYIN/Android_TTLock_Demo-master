package com.example.ttlock.sn.fragment;


import android.content.Context;
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
import com.example.ttlock.sn.bean.HouseInfo;
import com.example.ttlock.sn.bean.Request.HouseSearchRequestBean;
import com.example.ttlock.sn.bean.Responds.HouseSearchResponsesBean;
import com.example.ttlock.sn.bean.Responds.HouseSelectResponsesBean;
import com.example.ttlock.sn.callback.ClickCallback;
import com.example.ttlock.sn.network.ApiNet;
import com.example.ttlock.sn.uiActivity.HouseActivity;
import com.example.ttlock.sn.view.DefineOtherStylesBAGRefreshWithLoadView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class HouseFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate{

    private static final String TAG = "HouseFragment";
    private Context mContext;
    private BGARefreshLayout mBgaRefreshLayout;
    private RecyclerView recyclerView;

    private  MyRecyclerViewAdapter  myRecyclerViewAdapter;

    private DefineOtherStylesBAGRefreshWithLoadView mDefineBAGRefreshWithLoadView;

   private List<HouseSearchResponsesBean.DataBean> houseInfos = new ArrayList<>() ;


    private int ALLSUM ;
    private int PAGE =  1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_house, container, false);
//        getData();
        initView(view);
        requestData();
        return view;

    }

    private void initView(View view) {

        mBgaRefreshLayout =(BGARefreshLayout) view.findViewById(R.id.rl_houseFragment_refresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.house_recyclerView);
        //设置刷新和加载监听
        mBgaRefreshLayout.setDelegate(this);
        mDefineBAGRefreshWithLoadView = new DefineOtherStylesBAGRefreshWithLoadView(mContext , true , true);
        //设置刷新样式
        mBgaRefreshLayout.setRefreshViewHolder(mDefineBAGRefreshWithLoadView);
        mDefineBAGRefreshWithLoadView.updateLoadingMoreText("上拉加载更多");
        initAdpter();

    }
    private void initAdpter(){
        //设置布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(1,mContext,houseInfos);
        myRecyclerViewAdapter.setClickCallback(mClickCallback);
        recyclerView.setAdapter(myRecyclerViewAdapter);

    }

    private ClickCallback mClickCallback = new ClickCallback() {

        @Override
        public void ItemOnClick(View v) {

        }

        @Override
        public void OnItemClick(View view) {

        }

        @Override
        public void OnItemLongClick(View view) {

        }
    };
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
        if (null == houseInfos && getUserVisibleHint()) {
//            requestData();//解决第一个fragment无法加载数据问题
            Log.e(TAG,"requestData()");
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e(TAG,"House setUserVisibleHint");
//        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isVisible()) {//视图可见并且控件准备好了，每次都会调用
            if (houseInfos.size() == 0) {//如果数据为空了，则需要重新联网请求
                Log.e(TAG,"requestData()");
            }
        }



    }

    private HouseSearchRequestBean getRequestDate(){
        HouseSearchRequestBean resourcesRequestBean  = new HouseSearchRequestBean();
        HouseSearchRequestBean.PagingBean pagingBean = new HouseSearchRequestBean.PagingBean();
        pagingBean.setNumber(PAGE);
        pagingBean.setSize(10);
        resourcesRequestBean.setPaging(pagingBean);
        resourcesRequestBean.setRoomState("LEASING");
        return resourcesRequestBean;
    }
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        Log.e(TAG,"下拉刷新"+PAGE+"ALLSUM = "+ALLSUM);
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
}
