package com.example.weather;

/**
 * Created by newton on 7/6/17.
 */

public interface WeatherDataResponseListener {
    void getResponse(Object responseData);
    void getError(String errMsg);
}
