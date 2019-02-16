package com.example.ttlock.sn.network;


import android.util.Log;

import com.example.ttlock.sn.bean.Request.HouseSearchRequestBean;
import com.example.ttlock.sn.bean.Request.LockFormRequest;
import com.example.ttlock.sn.bean.Request.RoomSearchRequest;
import com.example.ttlock.sn.bean.Responds.ChangeStateResetResponses;
import com.example.ttlock.sn.bean.Responds.HouseSearchResponsesBean;
import com.example.ttlock.sn.bean.Responds.LoginResponsesBean;
import com.example.ttlock.sn.bean.Responds.RoomSearchResponses;
import com.example.ttlock.sn.bean.Responds.UserInfoResponses;
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
    public Observable<String> ApiChangeStateCheck(int roomId){
        return observe(mNetInterface.ChangeStateCheck(roomId))
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s;
                    }
                });
    }


    /**重置密码**/
    public Observable<ChangeStateResetResponses> ApiChangeStateReset(int roomId){
        return observe(mNetInterface.ChangeStateReset(roomId))
                .map(new Function<ChangeStateResetResponses, ChangeStateResetResponses>() {
                    @Override
                    public ChangeStateResetResponses apply(ChangeStateResetResponses changeStateResetResponses) throws Exception {
                        return changeStateResetResponses;
                    }
                });
    }


    /**绑定锁**/
    public Observable<ChangeStateResetResponses> ApiBindForApp(int id, LockData lockFormRequest){
            return observe(mNetInterface.BindForApp(HttpUrlConfig.BindForApp+id,lockFormRequest))
                    .map(new Function<ChangeStateResetResponses, ChangeStateResetResponses>() {
                        @Override
                        public ChangeStateResetResponses apply(ChangeStateResetResponses changeStateResetResponses) throws Exception {
                            return changeStateResetResponses;
                        }
                    });
    }

    /**用户信息**/
    public Observable<UserInfoResponses> ApiUserInfo(String id){
        return observe(mNetInterface.UserInfo(HttpUrlConfig.UserInfo,id))
                .map(new Function<UserInfoResponses, UserInfoResponses>() {
                    @Override
                    public UserInfoResponses apply(UserInfoResponses userInfoResponses) throws Exception {
                        return userInfoResponses;
                    }
                });
    }

    /**修改密码**/
    public Observable<String> ApiModifyPassword(String account,String password){
        return observe(mNetInterface.ModifyPassword(HttpUrlConfig.modifyPassword,account,password))
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s;
                    }
                });
    }
}
