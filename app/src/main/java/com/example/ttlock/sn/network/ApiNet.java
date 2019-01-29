package com.example.ttlock.sn.network;


import android.util.Log;

import com.example.ttlock.sn.bean.Request.HouseSearchRequestBean;
import com.example.ttlock.sn.bean.Request.LockFormRequest;
import com.example.ttlock.sn.bean.Request.RoomSearchRequest;
import com.example.ttlock.sn.bean.Responds.HouseSearchResponsesBean;
import com.example.ttlock.sn.bean.Responds.LoginResponsesBean;
import com.example.ttlock.sn.bean.Responds.RoomSearchResponses;
import com.ttlock.bl.sdk.entity.LockData;


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


    /**登录**/
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

    public Observable<String> ApiLoginSelect(){
        return observe(mNetInterface.LoginSelect(HttpUrlConfig.LoginSelect))
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s;
                    }
                });
    }

    /**房源**/
    public Observable<HouseSearchResponsesBean> ApiHouseSearch(HouseSearchRequestBean houseSearchRequestBean){
        return  observe(mNetInterface.HouseSearch(houseSearchRequestBean))
               .map(new Function<HouseSearchResponsesBean, HouseSearchResponsesBean>() {
                   @Override
                   public HouseSearchResponsesBean apply(HouseSearchResponsesBean houseSearchResponsesBean) throws Exception {
                       return houseSearchResponsesBean;
                   }
               });

    }

    /**房间**/
    public Observable<List<RoomSearchResponses>> ApiRoomSearch(RoomSearchRequest roomSearchRequest){
        return observe(mNetInterface.RoomSearch(roomSearchRequest))
                .map(new Function<List<RoomSearchResponses>, List<RoomSearchResponses>>() {
                    @Override
                    public List<RoomSearchResponses> apply(List<RoomSearchResponses> roomSearchResponses) throws Exception {
                        Log.e(TAG,"roomSearchResponses = "+roomSearchResponses.size());
                        return roomSearchResponses;
                    }
                });

    }



    /**查房**/
    public Observable<String> ApiChangeStateCheck(String roomId){
        return observe(mNetInterface.ChangeStateCheck(roomId))
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s;
                    }
                });
    }


    /**重置密码**/
    public Observable<String> ApiChangeStateReset(String roomId){
        return observe(mNetInterface.ChangeStateReset(roomId))
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s;
                    }
                });
    }


    /**绑定锁**/
    public Observable<String> ApiBindForApp(int id, LockFormRequest lockData){
        return observe(mNetInterface.BindForApp(id,lockData))
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s;
                    }
                });
    }


}
