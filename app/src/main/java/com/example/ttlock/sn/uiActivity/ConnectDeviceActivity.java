package com.example.ttlock.sn.uiActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.browse.MediaBrowser;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.ttlock.MyApplication;
import com.example.ttlock.R;
import com.example.ttlock.activity.BaseActivity;
import com.example.ttlock.dao.DbService;
import com.example.ttlock.model.Key;
import com.example.ttlock.model.KeyObj;
import com.example.ttlock.net.ResponseService;
import com.example.ttlock.sn.adapter.MyDeviceRecyclerViewAdapter;
import com.example.ttlock.sn.bean.Request.LockFormRequest;
import com.example.ttlock.sn.bean.Responds.ChangeStateResetResponses;
import com.example.ttlock.sn.bean.Responds.LockResponsesBean;
import com.example.ttlock.sn.bean.Responds.RoomSearchResponses;
import com.example.ttlock.sn.callback.ClickCallback;
import com.example.ttlock.sn.network.ApiNet;
import com.example.ttlock.sp.MyPreference;
import com.google.gson.reflect.TypeToken;
import com.ttlock.bl.sdk.api.TTLockAPI;
import com.ttlock.bl.sdk.callback.TTLockCallback;
import com.ttlock.bl.sdk.entity.DeviceInfo;
import com.ttlock.bl.sdk.entity.Error;
import com.ttlock.bl.sdk.entity.LockData;
import com.ttlock.bl.sdk.scanner.ExtendedBluetoothDevice;
import com.ttlock.bl.sdk.util.GsonUtil;
import com.ttlock.bl.sdk.util.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.ttlock.MyApplication.mTTLockAPI;
import static com.example.ttlock.activity.MainActivity.curKey;

public class ConnectDeviceActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = "ConnectDeviceActivity";

//    private AlertDialog.Builder builder;

    private ImageButton scanIB;
    private RecyclerView recyclerView;
    private List<ExtendedBluetoothDevice> devices = new ArrayList<>();
    private MyDeviceRecyclerViewAdapter myDeviceRecyclerViewAdapter;
    private TTLockAPI ttLockAPI;

    private int uid;



    /**
     * 1 代表绑定锁，2代表重置密码
     */
    private String Type ;

    private int bindid;
    private RoomSearchResponses roomSearchResponses;
    private List<Key> keys  = new ArrayList<>();


    private String lockName;
    private String password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_device);
        Type = getIntent().getStringExtra("type");
        if ("1".equals(Type)){

        bindid = getIntent().getIntExtra("ID",0);
        }else if ("2".equals(Type)){
            roomSearchResponses = (RoomSearchResponses)getIntent().getSerializableExtra("roomSearchResponses");
            getpassword();

        }
        Log.e(TAG,"Type ="+Type);
        initView();
        initAdapter();
        initToken();
        initBle();
        uid = MyPreference.getOpenid(this, MyPreference.OPEN_ID);

    }

    private void getpassword() {
       for (int j = 0 ;j < roomSearchResponses.getLockViewList().size() ;j++){
           if (roomSearchResponses.getLock().getId() == roomSearchResponses.getLockViewList().get(j).getLockId()){
               password = roomSearchResponses.getLockViewList().get(j).getKeyboardPwd();
           }
       }
    }

    private void initToken() {
        if ("access_token".equals(MyPreference.ACCESS_TOKEN)){
            requestToken();
//            syncData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ttLockAPI.stopBTDeviceScan();
        ttLockAPI.disconnect();
    }

    private void initAdapter() {
        //设置布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        myDeviceRecyclerViewAdapter = new MyDeviceRecyclerViewAdapter(this,Type,devices);
        myDeviceRecyclerViewAdapter.setClickCallback(itemClickCallback);
        recyclerView.setAdapter(myDeviceRecyclerViewAdapter);

    }

    private void initView() {
        scanIB = (ImageButton) findViewById(R.id.ib_scanDevice);
        scanIB.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.device_rl);
        if ("1".equals(Type)){
            scanIB.setVisibility(View.VISIBLE);
        }else if ("2".equals(Type)){
            scanIB.setVisibility(View.GONE);
        }

    }

    private void initEnableBle(){
        ttLockAPI.requestBleEnable(this);
        ttLockAPI.startBleService(this);

    }

    private void initBle() {
        ttLockAPI = new TTLockAPI(this,ttLockCallback);
        initEnableBle();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ib_scanDevice:
                scanBle();
                break;
        }

    }

    private void scanBle() {
        if(requestPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {

            ttLockAPI.startBTDeviceScan();
        }
    }

    private void ConnectBle(int position,String name) {
        lockName = name;
        showProgressDialog();
        mTTLockAPI.connect(devices.get(position));
    }
    /**
     * update scan device
     * @param extendedBluetoothDevice
     */
    public void updateDevice(ExtendedBluetoothDevice extendedBluetoothDevice) {
        Log.e(TAG,"size"+devices.size()+"\nisSettingMode ="+extendedBluetoothDevice.isSettingMode()+"Type = "+Type);

        if ("2".equals(Type)){
            if (extendedBluetoothDevice.getAddress().equals(roomSearchResponses.getLock().getData().getLockMac())){
                devices.add(extendedBluetoothDevice);
            }

        }else{

            for(int i = 0; i < devices.size() ;i++) {
                if(devices.contains(extendedBluetoothDevice)) {

                   return;
                }
            }
            if (extendedBluetoothDevice.isSettingMode()){
                devices.add(extendedBluetoothDevice);
            }

        }


        runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG,"size"+devices.size());
                    myDeviceRecyclerViewAdapter.notifyDataSetChanged();
                }
            });

    }




    private ClickCallback itemClickCallback = new ClickCallback() {
        @Override
        public void ItemOnClick(View v, int position) {

        }

        @Override
        public void OnItemClick(View view, int position) {
            ttLockAPI.stopBTDeviceScan();
            Log.e(TAG,"position = "+position);

            if ("1".equals(Type)){
            startAlertDialog("请输入锁的别名","取消","确定",position);
//            ttLockAPI.connect(devices.get(position));
            }else{
//                Key ckey = keys.get(0);
                ttLockAPI.connect(roomSearchResponses.getLock().getData().getLockMac());
            }
        }

        @Override
        public void OnItemLongClick(View view, int position) {

        }
    };



    private TTLockCallback ttLockCallback = new TTLockCallback() {
       @Override
       public void onFoundDevice(ExtendedBluetoothDevice extendedBluetoothDevice) {

           Log.e(TAG,"onFoundDevice = "+extendedBluetoothDevice.getAddress());
           updateDevice(extendedBluetoothDevice);

       }

       @Override
       public void onDeviceConnected(ExtendedBluetoothDevice extendedBluetoothDevice) {
           cancelProgressDialog();
           Log.e(TAG,"onDeviceConnected = "+extendedBluetoothDevice.toString());
            if ("1".equals(Type)){
                ttLockAPI.lockInitialize(extendedBluetoothDevice);
            }else if("2".equals(Type)){
                //TODO 重置密码
//                Key curKey = keys.get(0);

//                ttLockAPI.resetKeyboardPassword(null, uid, lockResponsesBean.getData().getLockVersion(), lockResponsesBean.getData().getAdminPwd(), lockResponsesBean.getData().getLockKey(), lockResponsesBean.getData().getLockFlagPos(), lockResponsesBean.getData().getAesKeyStr());

                mTTLockAPI.deleteOneKeyboardPassword(extendedBluetoothDevice,uid,roomSearchResponses.getLock().getData().getLockVersion(),
                        roomSearchResponses.getLock().getData().getAdminPwd(),roomSearchResponses.getLock().getData().getLockKey(),
                        roomSearchResponses.getLock().getData().getLockFlagPos(),3,password,roomSearchResponses.getLock().getData().getAesKeyStr());

            }



       }

       @Override
       public void onDeviceDisconnected(ExtendedBluetoothDevice extendedBluetoothDevice) {

       }

       @Override
       public void onGetLockVersion(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, int i2, int i3, int i4, Error error) {

       }

       @Override
       public void onLockInitialize(ExtendedBluetoothDevice extendedBluetoothDevice, final LockData lockData, Error error) {

           Log.e(TAG,"onLockInitialize"+extendedBluetoothDevice.getAddress()+"\n = error"+error);

           cancelProgressDialog();
           if(error == Error.SUCCESS) {
               Log.e(TAG,"lockData ="+lockData);

               final String lockDataJson = lockData.toJson();
               LockFormRequest lockFormRequest = new LockFormRequest();
               lockFormRequest.setData(lockData);
               lockFormRequest.setLockAlias(lockName);
               bindLock(bindid, lockFormRequest);

           } else {
               //失败
               toast(error.getErrorMsg());
           }



       }

       @Override
       public void onResetEKey(ExtendedBluetoothDevice extendedBluetoothDevice, int i, Error error) {

       }

       @Override
       public void onSetLockName(ExtendedBluetoothDevice extendedBluetoothDevice, String s, Error error) {

       }

       @Override
       public void onSetAdminKeyboardPassword(ExtendedBluetoothDevice extendedBluetoothDevice, String s, Error error) {

       }

       @Override
       public void onSetDeletePassword(ExtendedBluetoothDevice extendedBluetoothDevice, String s, Error error) {

       }

       @Override
       public void onUnlock(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, long l, Error error) {

       }

       @Override
       public void onSetLockTime(ExtendedBluetoothDevice extendedBluetoothDevice, Error error) {
           if (error == Error.SUCCESS){
               Log.e(TAG,"onSetLockTime");
//               requestModifyPassword(extendedBluetoothDevice);
               toast("时间校准成功");
           }

       }

       @Override
       public void onGetLockTime(ExtendedBluetoothDevice extendedBluetoothDevice, long l, Error error) {

       }

       @Override
       public void onResetKeyboardPassword(ExtendedBluetoothDevice extendedBluetoothDevice, String s, long l, Error error) {
           if (error == Error.SUCCESS){
//               //TODO 向后台传传重置密码和时间
//               (extendedBluetoothDevice);
//               Key curKey = keys.get(0);
                Log.e(TAG,"onResetKeyboardPassword");
//               ttLockAPI.setLockTime(null, uid, lockResponsesBean.getData().getLockVersion(), lockResponsesBean.getData().getLockKey(), System.currentTimeMillis(), lockResponsesBean.getData().getLockFlagPos(), lockResponsesBean.getData().getAesKeyStr(), lockResponsesBean.getData().getTimezoneRawOffset());



           }

       }

       @Override
       public void onSetMaxNumberOfKeyboardPassword(ExtendedBluetoothDevice extendedBluetoothDevice, int i, Error error) {

       }

       @Override
       public void onResetKeyboardPasswordProgress(ExtendedBluetoothDevice extendedBluetoothDevice, int i, Error error) {

       }

       @Override
       public void onResetLock(ExtendedBluetoothDevice extendedBluetoothDevice, Error error) {

       }

       @Override
       public void onAddKeyboardPassword(ExtendedBluetoothDevice extendedBluetoothDevice, int i, String s, long l, long l1, Error error) {

       }

       @Override
       public void onModifyKeyboardPassword(ExtendedBluetoothDevice extendedBluetoothDevice, int i, String s, String s1, Error error) {

       }

       @Override
       public void onDeleteOneKeyboardPassword(ExtendedBluetoothDevice extendedBluetoothDevice, int i, String s, Error error) {
           if (error == Error.SUCCESS){
//               //TODO 向后台传传重置密码和时间
               DeletePassword();
//               Key curKey = keys.get(0);
               Log.e(TAG,"onDeleteOneKeyboardPassword");
//               ttLockAPI.setLockTime(null, uid, lockResponsesBean.getData().getLockVersion(), lockResponsesBean.getData().getLockKey(), System.currentTimeMillis(), lockResponsesBean.getData().getLockFlagPos(), lockResponsesBean.getData().getAesKeyStr(), lockResponsesBean.getData().getTimezoneRawOffset());



           }
       }

       @Override
       public void onDeleteAllKeyboardPassword(ExtendedBluetoothDevice extendedBluetoothDevice, Error error) {

       }

       @Override
       public void onGetOperateLog(ExtendedBluetoothDevice extendedBluetoothDevice, String s, Error error) {

       }

       @Override
       public void onSearchDeviceFeature(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, Error error) {

       }

       @Override
       public void onAddICCard(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, long l, Error error) {

       }

       @Override
       public void onModifyICCardPeriod(ExtendedBluetoothDevice extendedBluetoothDevice, int i, long l, long l1, long l2, Error error) {

       }

       @Override
       public void onDeleteICCard(ExtendedBluetoothDevice extendedBluetoothDevice, int i, long l, Error error) {

       }

       @Override
       public void onClearICCard(ExtendedBluetoothDevice extendedBluetoothDevice, int i, Error error) {

       }

       @Override
       public void onSetWristbandKeyToLock(ExtendedBluetoothDevice extendedBluetoothDevice, int i, Error error) {

       }

       @Override
       public void onSetWristbandKeyToDev(Error error) {

       }

       @Override
       public void onSetWristbandKeyRssi(Error error) {

       }

       @Override
       public void onAddFingerPrint(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, long l, Error error) {

       }

       @Override
       public void onAddFingerPrint(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, long l, int i2, Error error) {

       }

       @Override
       public void onFingerPrintCollection(ExtendedBluetoothDevice extendedBluetoothDevice, int i, Error error) {

       }

       @Override
       public void onFingerPrintCollection(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, int i2, Error error) {

       }

       @Override
       public void onModifyFingerPrintPeriod(ExtendedBluetoothDevice extendedBluetoothDevice, int i, long l, long l1, long l2, Error error) {

       }

       @Override
       public void onDeleteFingerPrint(ExtendedBluetoothDevice extendedBluetoothDevice, int i, long l, Error error) {

       }

       @Override
       public void onClearFingerPrint(ExtendedBluetoothDevice extendedBluetoothDevice, int i, Error error) {

       }

       @Override
       public void onSearchAutoLockTime(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, int i2, int i3, Error error) {

       }

       @Override
       public void onModifyAutoLockTime(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, Error error) {

       }

       @Override
       public void onReadDeviceInfo(ExtendedBluetoothDevice extendedBluetoothDevice, DeviceInfo deviceInfo, Error error) {

       }

       @Override
       public void onEnterDFUMode(ExtendedBluetoothDevice extendedBluetoothDevice, Error error) {

       }

       @Override
       public void onGetLockSwitchState(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, Error error) {

       }

       @Override
       public void onLock(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, int i2, long l, Error error) {

       }

       @Override
       public void onScreenPasscodeOperate(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, Error error) {

       }

       @Override
       public void onRecoveryData(ExtendedBluetoothDevice extendedBluetoothDevice, int i, Error error) {

       }

       @Override
       public void onSearchICCard(ExtendedBluetoothDevice extendedBluetoothDevice, int i, String s, Error error) {

       }

       @Override
       public void onSearchFingerPrint(ExtendedBluetoothDevice extendedBluetoothDevice, int i, String s, Error error) {

       }

       @Override
       public void onSearchPasscode(ExtendedBluetoothDevice extendedBluetoothDevice, String s, Error error) {

       }

       @Override
       public void onSearchPasscodeParam(ExtendedBluetoothDevice extendedBluetoothDevice, int i, String s, long l, Error error) {

       }

       @Override
       public void onOperateRemoteUnlockSwitch(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, int i2, int i3, Error error) {

       }

       @Override
       public void onGetElectricQuantity(ExtendedBluetoothDevice extendedBluetoothDevice, int i, Error error) {

       }

       @Override
       public void onOperateAudioSwitch(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, int i2, Error error) {

       }

       @Override
       public void onOperateRemoteControl(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, int i2, Error error) {

       }

       @Override
       public void onOperateDoorSensorLocking(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, int i2, Error error) {

       }

       @Override
       public void onGetDoorSensorState(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, Error error) {

       }

       @Override
       public void onSetNBServer(ExtendedBluetoothDevice extendedBluetoothDevice, int i, Error error) {

       }

       @Override
       public void onGetAdminKeyboardPassword(ExtendedBluetoothDevice extendedBluetoothDevice, int i, String s, Error error) {

       }
   };




    private void requestToken(){
        if("access_token".equals(MyPreference.ACCESS_TOKEN)){
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
                            MyPreference.putStr(ConnectDeviceActivity.this, MyPreference.ACCESS_TOKEN, access_token);
                            MyPreference.putStr(ConnectDeviceActivity.this, MyPreference.OPEN_ID, openid);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    toast(msg);
                }
            }.execute();
        }

    }


    /**
     * 绑定锁
     */

    private void bindLock(final int id, LockFormRequest lockData) {
        Log.e(TAG,"id = "+id);
        ApiNet apiNet = new ApiNet();
        apiNet.ApiBindForApp(id,lockData)
                .subscribe(new Observer<ChangeStateResetResponses>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChangeStateResetResponses value) {
                        if (value.getCode() == 200){
                            toast("绑定锁成功");
                            finish();
                        }else{
                            toast("绑定锁失败 \n"+value.toString());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        toast("绑定锁异常 = "+e.getMessage());
                        finish();

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

//    /**
//     * 获取锁的信息
//     */
//    private  void  requestLockData(int roomID){
//        ApiNet apiNet = new ApiNet();
//        apiNet.ApiRequestLockData(roomID)
//                .subscribe(new Observer<LockResponsesBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(LockResponsesBean value) {
//                        toast("获取信息 = "+value.toString());
//                        lockResponsesBean = value;
//                        scanBle();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        toast("获取信息异常 = "+e.getMessage());
//                        finish();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//
//    }

    /**
     * 上传修改密码
     * @param
     */

    private void DeletePassword(){
        ApiNet apiNet = new ApiNet();
        apiNet.ApiDeletePs(roomSearchResponses.getHouse().getId())
                .subscribe(new Observer<ChangeStateResetResponses>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChangeStateResetResponses value) {
                        toast("删除密码成功");
//                        ttLockAPI.setLockTime(extendedBluetoothDevice, uid, curKey.getLockVersion(), curKey.getLockKey(), System.currentTimeMillis(), curKey.getLockFlagPos(), curKey.getAesKeyStr(), curKey.getTimezoneRawOffset());

                    }

                    @Override
                    public void onError(Throwable e) {

                        toast("删除密码异常 "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    ///////////////////////////////////////////////////测试//////////////////////////////////////////////

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
//                        Intent intent = new Intent(MainActivity.this, AuthActivity.class);
//                        startActivity(intent);
                        requestToken();
                        return json;
                    }
                    //use lastUpdateDate you can get the newly added key and data after the time
                    long lastUpdateDate = jsonObject.getLong("lastUpdateDate");
                    String keyList = jsonObject.getString("keyList");
//                    JSONArray jsonArray = jsonObject.getJSONArray("keyList");
                    keys.clear();
                    ArrayList<KeyObj> list = GsonUtil.toObject(keyList, new TypeToken<ArrayList<KeyObj>>(){});
                    keys.addAll(convert2DbModel(list));
//

                    //clear local keys and save new keys
                    DbService.deleteAllKey();
                    DbService.saveKeyList(keys);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return json;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.cancel();
                Log.e("key","keys = "+keys.size());
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

    private  void startAlertDialog(String title, String negative, String positive, final int position){

        final EditText inputServer = new EditText(this);
        AlertDialog.Builder  builder  = new AlertDialog.Builder(this);
        builder.setTitle(title).setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
                .setNegativeButton(negative, null);
        builder.setPositiveButton(positive, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                    inputServer.getText().toString();
                    ConnectBle(position,inputServer.getText().toString());


            }


        });
        builder.show();
    }


}
