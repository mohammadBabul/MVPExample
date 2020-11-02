package com.example.weather.presenter;
import android.view.View;
import com.example.weather.WeatherDataResponseListener;
import com.example.weather.models.WeatherDataShowModel;
import com.example.weather.view.MainViewCallBack;

public class WeatherPresenter implements WeatherDataPresenter, WeatherDataResponseListener {

    MainViewCallBack view;
    WeatherDataShowModel model;

    public WeatherPresenter(MainViewCallBack mainViewCallBack, WeatherDataShowModel weatherDataShowModel){
        view = mainViewCallBack;
        model = weatherDataShowModel;
    }


    @Override
    public void fetchWeatherInfo(String city_id) {
        view.handleProgressBarVisibility(View.VISIBLE);
        model.getWeatherInformation(city_id, this);
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void getResponse(Object responseData) {
        view.handleProgressBarVisibility(View.GONE);
        view.onWeatherInfoFetchSuccess(responseData);
    }

    @Override
    public void getError(String errMsg) {
        view.handleProgressBarVisibility(View.GONE);
        view.onWeatherInfoFetchFailure(errMsg);
    }
}
