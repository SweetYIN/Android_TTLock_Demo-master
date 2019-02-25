package com.example.ttlock.sn.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ttlock.R;
import com.example.ttlock.sn.bean.Responds.RoomSearchResponses;
import com.example.ttlock.sn.callback.ClickCallback;
import com.example.ttlock.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jl on 2019/1/9.
 */

public class MyRoomSearchViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        {

    private Context mContext;

    private List<RoomSearchResponses> mRoomList = new ArrayList<>();
    /**
     * type 1表示查房，2表示重置密码，3表示待上架，4表还未绑定房源,5代表要绑定的房间
     */
    private int type;

    private ClickCallback mClickCallback;

    public MyRoomSearchViewAdapter(int type, Context context,List<RoomSearchResponses> roomList) {
        this.type = type;
        this.mContext = context;
        this.mRoomList = roomList;
    }



    public void  setClickCallback(ClickCallback clickCallback){
        this.mClickCallback = clickCallback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.houseinfo_list_item, parent, false);

            return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            RoomSearchResponses rommSearchResponses = mRoomList.get(position);
            final MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.numberTV.setText("房源编号："+rommSearchResponses.getHouse().getSerialNumber());
            myViewHolder.addressTV.setText(rommSearchResponses.getHouse().getAddress()+rommSearchResponses.getHouse().getHouseNo());
            myViewHolder.typeTV.setText(rommSearchResponses.getHouse().getLeaseType()+"/"+
                    rommSearchResponses.getHouse().getHouseType()+"/"+
                    rommSearchResponses.getHouse().getProvince());
        //roomSearchResponses.getHouse().getTopUrl()
        GlideUtils.setCircleImage(mContext,myViewHolder.imageView,"",R.mipmap.ic_launcher);
        myViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickCallback.OnItemClick(v,position);
            }
        });
        myViewHolder.btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickCallback.ItemOnClick(v,position);
            }
        });
        myViewHolder.btnResetPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickCallback.ItemOnClick(v,position);
            }
        });
        myViewHolder.btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickCallback.ItemOnClick(v,position);
            }
        });

    }


    @Override
    public int getItemCount() {
            return mRoomList.size();
    }



    private class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView numberTV, addressTV, typeTV;
        Button btnCheck, btnResetPW,btnBind;
        RelativeLayout functionLayout;
        ConstraintLayout constraintLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.ascc_house_layout);
            functionLayout = (RelativeLayout) itemView.findViewById(R.id.house_function_Layout);
            imageView = (ImageView) itemView.findViewById(R.id.house_imageView);
            numberTV = (TextView) itemView.findViewById(R.id.house_number);
            addressTV = (TextView) itemView.findViewById(R.id.house_address);
            typeTV = (TextView) itemView.findViewById(R.id.house_info);
            btnCheck = (Button) itemView.findViewById(R.id.btn_check_house);
            btnResetPW = (Button) itemView.findViewById(R.id.btn_password_reset);
            btnBind = (Button)itemView.findViewById(R.id.btn_binding);
            functionSelect();
        }
        private void functionSelect() {
            switch (type){
                case 1:
                    functionLayout.setVisibility(View.VISIBLE);
                    btnCheck.setVisibility(View.VISIBLE);
                    btnResetPW.setVisibility(View.GONE);
                    btnBind.setVisibility(View.GONE);
                    break;
                case 2:
                    functionLayout.setVisibility(View.VISIBLE);
                    btnCheck.setVisibility(View.GONE);
                    btnResetPW.setVisibility(View.VISIBLE);
                    btnBind.setVisibility(View.GONE);
                    break;
                case 3:
                    break;
                case 4:
                    functionLayout.setVisibility(View.GONE);
                    break;
                case 5:
                    functionLayout.setVisibility(View.VISIBLE);
                    btnBind.setVisibility(View.VISIBLE);
                    btnCheck.setVisibility(View.GONE);
                    btnResetPW.setVisibility(View.GONE);
                    imageView.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }

    }

}
