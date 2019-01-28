package com.example.ttlock.sn.network;



import com.example.ttlock.sn.bean.Request.HouseSearchRequestBean;
import com.example.ttlock.sn.bean.Request.LoginRequestBean;
import com.example.ttlock.sn.bean.Responds.HouseSearchResponsesBean;
import com.example.ttlock.sn.bean.Responds.HouseSelectResponsesBean;
import com.example.ttlock.sn.bean.Responds.LoginResponsesBean;
import com.ttlock.bl.sdk.entity.LockData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface NetInterface {

	/**登录**/
	@FormUrlEncoded
	@POST
	Observable<LoginResponsesBean> Login(@Url String url,
										 @Header("Authorization") String Authorization,
										 @Field("username") String usernme,
										 @Field("password") String password,
										 @Field("grant_type") String grant_type,
										 @Field("scope") String scope) ;
	/**房源**/
	@Headers("Content-Type:application/json")
	@POST(HttpUrlConfig.HouseSearchResources)
	Observable<HouseSearchResponsesBean> HouseSearch(@Body HouseSearchRequestBean houseSearchRequestBean);

	/**查房**/
	@Headers("Content-Type:application/json")
	@FormUrlEncoded
	@POST(HttpUrlConfig.ChangeStateCheck)
	Observable<String> ChangeStateCheck(@Field("roomId") String roomId);

	/**重置密码**/
	@Headers("Content-Type:application/json")
	@FormUrlEncoded
	@POST(HttpUrlConfig.ChangeStateReset)
	Observable<String> ChangeStateReset(@Field("roomId") String roomId);

	/**绑定锁**/
	@Headers("Content-Type:application/json")
	@POST(HttpUrlConfig.BindForApp)
	Observable<String> BindForApp(@Field("id")String id, @Body LockData lockData);








}
