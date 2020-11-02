package com.example.weather.api;
import com.example.weather.utils.Constantdata;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReadyApiRequest {
    private static final ReadyApiRequest instance = new ReadyApiRequest();

    private ReadyApiRequest() {

    }

    public static ReadyApiRequest getInstance() {
        return instance;
    }


    public OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(new NetworkInterceptor());
        okHttpClient.connectTimeout(1500 * 60, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(1000 * 60, TimeUnit.MILLISECONDS);
//        okHttpClient.retryOnConnectionFailure(true);
        return okHttpClient.build();
    }
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public Retrofit getRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constantdata.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
