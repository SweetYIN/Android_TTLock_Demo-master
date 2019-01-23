package com.example.ttlock.sn.network;


import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRetrofit {
	private  String tag = BaseRetrofit.class.getSimpleName();
//	private static final BaseRetrofit INSTANCE ;
	private static final int DEFAULT_READ_TIME_OUT = 10;
	private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s

	/**
	 * 获取BaseRetrofit
	 * @return
	 */
	public static BaseRetrofit getInstance(){
//		return SingletonHolder.INSTANCE;
		return new BaseRetrofit();
	}
	private static class SingletonHolder{
		private static final BaseRetrofit INSTANCE = new BaseRetrofit();
//		new BaseRetrofit();
	}
	private Retrofit mRetrofit;

	private BaseRetrofit(){
		// 创建 OKHttpClient
		HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {

			@Override
			public void log(String message) {
				Log.d("logInterceptor", message);
			}
		});
		logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间        builder.writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//写操作 超时时间
		builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//读操作超时时间
		builder.addNetworkInterceptor(logInterceptor);
		builder.addInterceptor(new MyHttpInterceptor());
		builder.addInterceptor(logInterceptor);


		// 创建Retrofit
		mRetrofit = new Retrofit.Builder()
				.client(builder.build())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl(HttpUrlConfig.ServerUrl)
				.build();
	}

	/**
	 * 获取对应的Service
	 * @param service Service 的 class
	 * @param <T>
	 * @return
	 */
	public <T> T create(Class<T> service){
		return mRetrofit.create(service);
	}


}
