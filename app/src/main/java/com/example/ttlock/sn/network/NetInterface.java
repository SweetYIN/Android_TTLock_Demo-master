package com.example.ttlock.sn.network;



import com.example.ttlock.sn.bean.Request.HouseSearchRequestBean;
import com.example.ttlock.sn.bean.Request.LockFormRequest;
import com.example.ttlock.sn.bean.Request.RoomSearchRequest;
import com.example.ttlock.sn.bean.Responds.ChangeStateResetResponses;
import com.example.ttlock.sn.bean.Responds.HouseSearchResponsesBean;
import com.example.ttlock.sn.bean.Responds.LockResponsesBean;
import com.example.ttlock.sn.bean.Responds.LoginResponsesBean;
import com.example.ttlock.sn.bean.Responds.RoomSearchResponses;
import com.example.ttlock.sn.bean.Responds.UserInfoResponses;
import com.example.ttlock.sn.bean.Responds.UserSessionResponses;
import com.ttlock.bl.sdk.entity.LockData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
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
	/**登录认证**/
	@POST
	Observable<String> LoginSelect(@Url String url);

	/**房源**/
	@Headers("Content-Type:application/json")
	@POST(HttpUrlConfig.HouseSearchResources)
	Observable<HouseSearchResponsesBean> HouseSearch(@Body RoomSearchRequest roomSearchRequest);

	/**房间**/
	@Headers("Content-Type:application/json")
	@POST(HttpUrlConfig.RoomSearch)
	Observable<List<RoomSearchResponses>> RoomSearch(@Body RoomSearchRequest roomSearchRequest);

	/**查房**/
	@Headers("Content-Type:application/json")
	@POST
	Observable<ChangeStateResetResponses> ChangeStateCheck(@Url String url);

	/**重置密码**/
	@Headers("Content-Type:application/json")
	@POST
	Observable<ChangeStateResetResponses> DeletePassword(@Url String url);

	/**绑定锁**/
	@Headers("Content-Type:application/json")
	@POST
	Observable<ChangeStateResetResponses> BindForApp(@Url String id, @Body LockFormRequest lockFormRequest);

	/**获取锁信息**/
	@Headers("Content-Type:application/json")
	@GET
	Observable<LockResponsesBean> requesLockInfot(@Url String id);



	/**获取用户信息**/
	@Headers("Content-Type:application/json")
	@GET
	Observable<UserInfoResponses> UserInfo(@Url String url) ;


	/**修改密码**/
	@FormUrlEncoded
	@POST
	Observable<String> ModifyPassword(@Url String url,@Field("account") String account,
								@Field("password") String password) ;



}
