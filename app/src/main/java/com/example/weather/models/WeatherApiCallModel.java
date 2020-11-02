package com.example.weather.models;
import android.content.Context;
import android.util.Log;

import com.example.weather.WeatherDataResponseListener;
import com.example.weather.api.ApiRequests;
import com.example.weather.api.NoNetworkException;
import com.example.weather.api.ReadyApiRequest;
import com.example.weather.models.data_class.WeatherBase;
import java.net.SocketTimeoutException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;


public class WeatherApiCallModel implements WeatherDataShowModel {
    private ApiRequests mApiRequests;
    Context mContext;
    WeatherDataResponseListener mResponseListener;

    public WeatherApiCallModel(Context context) {
        mApiRequests = ReadyApiRequest.getInstance().getRetrofit(ReadyApiRequest.getInstance().getOkHttpClient()).create(ApiRequests.class);
        mContext = context;

    }



    @Override
    public void getWeatherInformation(String cityId, final WeatherDataResponseListener weatherDataResponseListener) {

        mResponseListener = weatherDataResponseListener;
        mApiRequests.getWeather("dhaka")
                .delay(600, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<WeatherBase>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(WeatherBase weatherBase) {
                        mResponseListener.getResponse(weatherBase);

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        getExceptions(throwable);

                    }
                });
    }





    private void getExceptions(Throwable throwable) {
        if (throwable instanceof NoNetworkException) {
            mResponseListener.getError("No connectivity");
        } else if (throwable instanceof HttpException) {
            int code = ((HttpException) throwable).code();
            System.out.println(" err code == " + code + "/" +
                    Objects.requireNonNull(((HttpException) throwable).response()).isSuccessful() + "/" +
                    Objects.requireNonNull(((HttpException) throwable).response()).body() + "/" +
                    Objects.requireNonNull(((HttpException) throwable).response()).message() + "/" +
                    Objects.requireNonNull(Objects.requireNonNull(((HttpException) throwable).response())
                            .errorBody()).byteStream().toString());

            switch (code) {
                case 500:
                    mResponseListener.getError(((HttpException) throwable).message());
                    break;
                case 501:
                    mResponseListener.getError(((HttpException) throwable).message());
                    break;
                case 401:
                    mResponseListener.getError(((HttpException) throwable).message());
                    break;
                case 400:
                   mResponseListener.getError(String.valueOf(((HttpException) throwable).response()));
                    break;
                case 403:
                    mResponseListener.getError(((HttpException) throwable).message());
                    break;
                case 404:
                    mResponseListener.getError(((HttpException) throwable).message());
                    break;
                default:
                    Log.e("default 1", "" + ((HttpException) throwable).message());
                    mResponseListener.getError(((HttpException) throwable).message());
                    break;
            }
        } else if (throwable instanceof SocketTimeoutException) {
            mResponseListener.getError("Request time out");
        } else {
            Log.e("default 2", "" + throwable.getMessage());
            mResponseListener.getError("" + throwable.getMessage());
        }
    }


}
