package com.example.weather.presenter;

public interface WeatherDataPresenter {

    void  fetchWeatherInfo(String city_id);
    void  detachView();
}
