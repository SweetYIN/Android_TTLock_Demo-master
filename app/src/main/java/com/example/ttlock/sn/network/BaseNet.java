package com.example.ttlock.sn.network;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 网络请求封装类
 * */
public class BaseNet  {
private String tag = BaseNet.class.getSimpleName();
	/**
	 *
	 * @param observable
	 * @param <T>
	 * @return
	 */
	protected  <T> Observable<T> observe(Observable<T> observable){
		return observable
				.subscribeOn(Schedulers.io())
				.unsubscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}



}
