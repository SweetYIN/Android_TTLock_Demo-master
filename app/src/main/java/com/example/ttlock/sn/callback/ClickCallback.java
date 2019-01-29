package com.example.ttlock.sn.callback;

import android.view.View;

/**
 * Created by jl on 2019/1/15.
 */

public interface ClickCallback {
    public void ItemOnClick(View v,int position);
    public void OnItemClick(View view,int position);
    public void OnItemLongClick(View view,int position);


}
