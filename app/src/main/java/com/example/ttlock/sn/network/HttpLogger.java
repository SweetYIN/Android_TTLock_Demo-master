package com.example.ttlock.sn.network;


import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by jl on 2019/1/18.
 */

public class HttpLogger implements  HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        Log.d("HttpLogInfo", message);
    }
}
