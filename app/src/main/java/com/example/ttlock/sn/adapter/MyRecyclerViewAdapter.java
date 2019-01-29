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
import com.example.ttlock.sn.bean.Responds.HouseSearchResponsesBean;
import com.example.ttlock.sn.bean.Responds.RoomSearchResponses;
import com.example.ttlock.sn.callback.ClickCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jl on 2019/1/9.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        {

    private Context mContext;
    private List<HouseSearchResponsesBean.DataBean> mhouseList = new ArrayList<>();

    private List<RoomSearchResponses> mRoomList = new ArrayList<>();
    /**
     * type 1表示查房，2表示重置密码，3表示待上架，4表还未绑定房源,5代表要绑定的房间
     */
    private int type;

    private ClickCallback mClickCallback;
    private   MyViewHolder myViewHolder;

    public MyRecyclerViewAdapter(int type,Context context,List<HouseSearchResponsesBean.DataBean> houseList ) {
        this.type = type;
        this.mContext = context;
        this.mhouseList = houseList;
    }

    public void  setClickCallback(ClickCallback clickCallback){
        this.mClickCallback = clickCallback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.houseinfo_list_item, parent, false);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mClickCallback.OnItemClick(v);
//            }
//        });

            return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            HouseSearchResponsesBean.DataBean houseInfo = mhouseList.get(position);
           MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.numberTV.setText(houseInfo.getSerialNumber());
            myViewHolder.addressTV.setText(houseInfo.getAddress()+houseInfo.getHouseNo());
            myViewHolder.typeTV.setText(houseInfo.getHouseType());
        myViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickCallback.OnItemClick(v,position);
            }
        });

    }


    @Override
    public int getItemCount() {
            return mhouseList.size() ;
    }



    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView numberTV, addressTV, typeTV;
        Button btnCheck, btnResetPW,btnBind;
        RelativeLayout functionLayout;
        ConstraintLayout constraintLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.ascc_house_layout);

            functionLayout = (RelativeLayout) itemView.findViewById(R.id.house_function_Layout);
            imageView = (ImageView) itemView.findViewById(R.id.house_imageView);
            numberTV = (TextView) itemView.findViewById(R.id.house_number);
            addressTV = (TextView) itemView.findViewById(R.id.house_address);
            typeTV = (TextView) itemView.findViewById(R.id.house_info);
            btnCheck = (Button) itemView.findViewById(R.id.btn_check_house);
            btnCheck.setOnClickListener(this);
            btnResetPW = (Button) itemView.findViewById(R.id.btn_password_reset);
            btnResetPW.setOnClickListener(this);
            btnBind = (Button)itemView.findViewById(R.id.btn_binding);
            btnBind.setOnClickListener(this);
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
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            mClickCallback.ItemOnClick(v,0);
        }
    }

}
