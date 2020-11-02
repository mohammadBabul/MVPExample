package com.example.weather.models;

import com.example.weather.WeatherDataResponseListener;

public interface WeatherDataShowModel {
   void getWeatherInformation(String cityId, WeatherDataResponseListener weatherDataResponseListener);
}
