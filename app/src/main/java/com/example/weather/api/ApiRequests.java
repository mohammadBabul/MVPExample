package com.example.weather.api;
import com.example.weather.models.data_class.WeatherBase;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiRequests {

    @GET("weather")
    Single<WeatherBase> getWeather(
            @Query("id") String city_id
    );
}


