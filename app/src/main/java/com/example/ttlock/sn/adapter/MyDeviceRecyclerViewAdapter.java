package com.example.ttlock.sn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ttlock.R;
import com.example.ttlock.sn.bean.Responds.HouseSearchResponsesBean;
import com.example.ttlock.sn.callback.ClickCallback;
import com.ttlock.bl.sdk.scanner.ExtendedBluetoothDevice;

import java.util.List;

public class MyDeviceRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;

    private ClickCallback mClickCallback;

    private List<ExtendedBluetoothDevice> mLeDevices;

    private String mType;

    public MyDeviceRecyclerViewAdapter(Context context, String type,List<ExtendedBluetoothDevice> mLeDevices) {
        mContext = context;
        this.mLeDevices = mLeDevices;
        this.mType = type;
    }
    public void  setClickCallback(ClickCallback clickCallback){
        this.mClickCallback = clickCallback;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycleview_device, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ExtendedBluetoothDevice  extendedBluetoothDevice= mLeDevices.get(position);
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.nameTV.setText(extendedBluetoothDevice.getName());
        myViewHolder.addressTV.setText(extendedBluetoothDevice.getAddress());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickCallback.OnItemClick(v,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mLeDevices.size() ;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameTV,addressTV;
        Button btnResetPS;

        public MyViewHolder(View itemView) {
            super(itemView);
            nameTV = (TextView) itemView.findViewById(R.id.device_name_rv);
            addressTV = (TextView) itemView.findViewById(R.id.device_adress_rv);
            btnResetPS = (Button) itemView.findViewById(R.id.device_resetPS_rv);
            btnResetPS.setOnClickListener(this);
            if ("1".equals(mType)){
                btnResetPS.setVisibility(View.GONE);
            }else {
                btnResetPS.setVisibility(View.VISIBLE);
            }
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.device_resetPS_rv:
                    mClickCallback.ItemOnClick(v,0);
                    break;
            }
        }
    }

}
