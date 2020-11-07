package com.example.weather.api;

import android.util.Log;
import com.example.weather.AppApplication;
import com.example.weather.utils.Constantdata;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;



public class NetworkInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        NetworkModule networkModule = new NetworkModule(AppApplication.getInstance());
        if (networkModule.isConnected()) {
            return chain.proceed(getRequest(chain.request()));
        } else
            throw new NoNetworkException();
    }

    private Request getRequest(Request request){
        HttpUrl rootUrl = request.url();
        HttpUrl callUrl = rootUrl.newBuilder()
                .addQueryParameter("appid", Constantdata.APP_ID)
                .build();
        Log.e("call url", String.valueOf(callUrl));
        return request.newBuilder()
                .url(callUrl)
               // .addHeader("Content-type", "application/x-www-form-urlencoded")
               // .addHeader("Content-type", "application/json")
               // .addHeader("Authorization", "Bearer :")
                .build();

    }

}
