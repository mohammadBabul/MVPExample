package com.example.weather.view;

public interface MainViewCallBack {
    void handleProgressBarVisibility(int visibility);
    void onWeatherInfoFetchSuccess(Object responsedata);
    void onWeatherInfoFetchFailure(String errMsg);
}
