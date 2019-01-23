package com.example.ttlock.sn.network;


import android.util.Log;

import com.example.ttlock.sn.bean.Request.HouseSearchRequestBean;
import com.example.ttlock.sn.bean.Request.LoginRequestBean;
import com.example.ttlock.sn.bean.Responds.HouseSearchResponsesBean;
import com.example.ttlock.sn.bean.Responds.LoginResponsesBean;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by jl on 2019/1/10.
 */

public class ApiNet extends BaseNet{
    private String TAG = "ApiNet";
    private NetInterface mNetInterface;
    public ApiNet() {
        mNetInterface = BaseRetrofit.getInstance().create(NetInterface.class);
    }


    public Observable<LoginResponsesBean> ApiLogin(String Authorization,
                                                   String name,String pas){
        return observe(mNetInterface.Login(HttpUrlConfig.Login,
                Authorization,
                 name,pas,"password","all"))
                .map(new Function<LoginResponsesBean, LoginResponsesBean>() {
                    @Override
                    public LoginResponsesBean apply(LoginResponsesBean loginResponsesBean) throws Exception {
                        return loginResponsesBean;
                    }
                });
    }

    public Observable<HouseSearchResponsesBean> ApiHouseSearch(HouseSearchRequestBean houseSearchRequestBean){
        return  observe(mNetInterface.HouseSearch(houseSearchRequestBean))
               .map(new Function<HouseSearchResponsesBean, HouseSearchResponsesBean>() {
                   @Override
                   public HouseSearchResponsesBean apply(HouseSearchResponsesBean houseSearchResponsesBean) throws Exception {
                       return houseSearchResponsesBean;
                   }
               });

    }






}
