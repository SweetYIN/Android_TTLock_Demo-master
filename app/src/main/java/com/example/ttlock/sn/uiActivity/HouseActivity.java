package com.example.ttlock.sn.uiActivity;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ttlock.MyApplication;
import com.example.ttlock.R;
import com.example.ttlock.activity.BaseActivity;
import com.example.ttlock.activity.MainActivity;
import com.example.ttlock.adapter.KeyAdapter;
import com.example.ttlock.dao.DbService;
import com.example.ttlock.model.Key;
import com.example.ttlock.model.KeyObj;
import com.example.ttlock.net.ResponseService;
import com.example.ttlock.sn.adapter.MyRecyclerViewAdapter;
import com.example.ttlock.sn.bean.HouseInfo;
import com.example.ttlock.sn.bean.Request.HouseSearchRequestBean;
import com.example.ttlock.sn.bean.Responds.HouseSearchResponsesBean;
import com.example.ttlock.sn.callback.ClickCallback;
import com.example.ttlock.sn.network.ApiNet;
import com.example.ttlock.sn.view.DefineOtherStylesBAGRefreshWithLoadView;
import com.example.ttlock.sp.MyPreference;
import com.google.gson.reflect.TypeToken;
import com.ttlock.bl.sdk.util.GsonUtil;
import com.ttlock.bl.sdk.util.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

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
//            init();

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

    ////////////////////////////////////操作蓝牙部分////////////////////////////////////////////////////////////////////////

    private List<Key> keys;
    /**
     * Initialization
     */
    private void init() {
        //turn on bluetooth
        MyApplication.mTTLockAPI.requestBleEnable(this);
        LogUtil.d("start bluetooth service", DBG);
        MyApplication.mTTLockAPI.startBleService(this);
        //It need location permission to start bluetooth scan,or it can not scan device
        if(requestPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            MyApplication.mTTLockAPI.startBTDeviceScan();
        }

//        accessToken = MyPreference.getStr(this, MyPreference.ACCESS_TOKEN);
        keys = new ArrayList<>();
        syncData();
    }

    /**
     * synchronizes the data of key
     */
    private void syncData() {
        showProgressDialog();
        new AsyncTask<Void,String,String>() {

            @Override
            protected String doInBackground(Void... params) {
                //you can synchronizes all key datas when lastUpdateDate is 0
                String json = ResponseService.syncData(0);
                LogUtil.d("json:" + json, DBG);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    if(jsonObject.has("errcode")) {
                        toast(jsonObject.getString("description"));
                        requestToken();
                        return json;
                    }else{
                        Intent  intent = new Intent(HouseActivity.this, ConnectDeviceActivity.class);
                        startActivity(intent);


                    }
//                    use lastUpdateDate you can get the newly added key and data after the time
//                    long lastUpdateDate = jsonObject.getLong("lastUpdateDate");
//                    String keyList = jsonObject.getString("keyList");
//                    keys.clear();
//                    ArrayList<KeyObj> list = GsonUtil.toObject(keyList, new TypeToken<ArrayList<KeyObj>>(){});
//                    keys.addAll(convert2DbModel(list));
//
//                    //clear local keys and save new keys
//                    DbService.deleteAllKey();
//                    DbService.saveKeyList(keys);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return json;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.cancel();
//                Intent  intent = new Intent(HouseActivity.this, ConnectDeviceActivity.class);
//                startActivity(intent);
//                keyAdapter = new KeyAdapter(MainActivity.this, keys);
//                listView.setAdapter(keyAdapter);
//                listView.setOnCreateContextMenuListener(MainActivity.this);
            }
        }.execute();
    }


    private void requestToken(){
        new AsyncTask<Void, Integer, String>() {

            @Override
            protected String doInBackground(Void... params) {
                return ResponseService.auth("rubik_user ", "123456");
            }

            @Override
            protected void onPostExecute(String json) {
                String msg = getString(R.string.words_authorize_successed);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    if(jsonObject.has("errcode")) {
                        msg = jsonObject.getString("description");
                    } else {
                        String access_token = jsonObject.getString("access_token");
                        String openid = jsonObject.getString("openid");
                        LogUtil.e("access_token = "+access_token);
                        MyPreference.putStr(HouseActivity.this, MyPreference.ACCESS_TOKEN, access_token);
                        MyPreference.putStr(HouseActivity.this, MyPreference.OPEN_ID, openid);
//                        syncData();
                        Intent  intent = new Intent(HouseActivity.this, ConnectDeviceActivity.class);
                        startActivity(intent);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                toast(msg);
            }
        }.execute();
    }

    private static ArrayList<Key> convert2DbModel(ArrayList<KeyObj> list){
        ArrayList<Key> keyList = new ArrayList<>();
        if(list != null && list.size() > 0){
            for(KeyObj key : list){
                Key DbKey = new Key();
                DbKey.setUserType(key.userType);
                DbKey.setKeyStatus(key.keyStatus);
                DbKey.setLockId(key.lockId);
                DbKey.setKeyId(key.keyId);
                DbKey.setLockVersion(GsonUtil.toJson(key.lockVersion));
                DbKey.setLockName(key.lockName);
                DbKey.setLockAlias(key.lockAlias);
                DbKey.setLockMac(key.lockMac);
                DbKey.setElectricQuantity(key.electricQuantity);
                DbKey.setLockFlagPos(key.lockFlagPos);
                DbKey.setAdminPwd(key.adminPwd);
                DbKey.setLockKey(key.lockKey);
                DbKey.setNoKeyPwd(key.noKeyPwd);
                DbKey.setDeletePwd(key.deletePwd);
                DbKey.setPwdInfo(key.pwdInfo);
                DbKey.setTimestamp(key.timestamp);
                DbKey.setAesKeyStr(key.aesKeyStr);
                DbKey.setStartDate(key.startDate);
                DbKey.setEndDate(key.endDate);
                DbKey.setSpecialValue(key.specialValue);
                DbKey.setTimezoneRawOffset(key.timezoneRawOffset);
                DbKey.setKeyRight(key.keyRight);
                DbKey.setKeyboardPwdVersion(key.keyboardPwdVersion);
                DbKey.setRemoteEnable(key.remoteEnable);
                DbKey.setRemarks(key.remarks);

                keyList.add(DbKey);
            }
        }
        return keyList;
    }
}
