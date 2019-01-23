package com.example.ttlock.sn.uiActivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.browse.MediaBrowser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.ttlock.MyApplication;
import com.example.ttlock.R;
import com.example.ttlock.activity.BaseActivity;
import com.example.ttlock.adapter.FoundDeviceAdapter;
import com.example.ttlock.constant.BleConstant;
import com.example.ttlock.sn.adapter.MyDeviceRecyclerViewAdapter;
import com.example.ttlock.sn.adapter.MyRecyclerViewAdapter;
import com.example.ttlock.sn.callback.ClickCallback;
import com.example.ttlock.sn.callback.ItemClickCallback;
import com.ttlock.bl.sdk.api.TTLockAPI;
import com.ttlock.bl.sdk.callback.TTLockCallback;
import com.ttlock.bl.sdk.entity.DeviceInfo;
import com.ttlock.bl.sdk.entity.Error;
import com.ttlock.bl.sdk.entity.LockData;
import com.ttlock.bl.sdk.scanner.ExtendedBluetoothDevice;

import java.util.ArrayList;
import java.util.List;

public class ConnectDeviceActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = "ConnectDeviceActivity";

    private ImageButton scanIB;
    private RecyclerView recyclerView;
    private List<ExtendedBluetoothDevice> devices = new ArrayList<>();
    private MyDeviceRecyclerViewAdapter myDeviceRecyclerViewAdapter;
    private TTLockAPI ttLockAPI;
    /**
     * 1 代表绑定锁，2代表重置密码
     */
    private String Type ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_device);
        Type = getIntent().getStringExtra("type");
        initView();
        initAdapter();
        initBle();

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
        scanIB = findViewById(R.id.ib_scanDevice);
        scanIB.setOnClickListener(this);
        recyclerView = findViewById(R.id.device_rl);
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
    /**
     * update scan device
     * @param extendedBluetoothDevice
     */
    public void updateDevice(ExtendedBluetoothDevice extendedBluetoothDevice) {
            Log.e(TAG,"size"+devices.size());
            if (devices.size() == 0){
                devices.add(extendedBluetoothDevice);
            }else{
                for(ExtendedBluetoothDevice device:devices){
                    if(!device.getAddress().equals(extendedBluetoothDevice.getAddress())) {
                        devices.add(extendedBluetoothDevice);
                    }
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
        public void ItemOnClick(View v) {

        }

        @Override
        public void OnItemClick(View view) {
            ttLockAPI.stopBTDeviceScan();
            Log.e(TAG,"view.getTag() = "+view.getTag());
            showProgressDialog();
            ttLockAPI.connect(devices.get((int)view.getTag()));
        }

        @Override
        public void OnItemLongClick(View view) {

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
           Log.e(TAG,"onDeviceConnected = "+extendedBluetoothDevice.getAddress());

            ttLockAPI.lockInitialize(extendedBluetoothDevice);


       }

       @Override
       public void onDeviceDisconnected(ExtendedBluetoothDevice extendedBluetoothDevice) {

       }

       @Override
       public void onGetLockVersion(ExtendedBluetoothDevice extendedBluetoothDevice, int i, int i1, int i2, int i3, int i4, Error error) {

       }

       @Override
       public void onLockInitialize(ExtendedBluetoothDevice extendedBluetoothDevice, LockData lockData, Error error) {

           Log.e(TAG,"onLockInitialize"+extendedBluetoothDevice.getAddress());
           //TODOrequestData

           uploadData();


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

       }

       @Override
       public void onGetLockTime(ExtendedBluetoothDevice extendedBluetoothDevice, long l, Error error) {

       }

       @Override
       public void onResetKeyboardPassword(ExtendedBluetoothDevice extendedBluetoothDevice, String s, long l, Error error) {

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

    private void uploadData() {

    }


}
