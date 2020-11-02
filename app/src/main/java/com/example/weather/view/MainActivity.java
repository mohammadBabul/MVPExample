package com.example.weather.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import com.example.weather.R;
import com.example.weather.models.WeatherApiCallModel;
import com.example.weather.models.data_class.WeatherBase;
import com.example.weather.presenter.WeatherDataPresenter;
import com.example.weather.presenter.WeatherPresenter;
import com.example.weather.utils.DataFormation;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements MainViewCallBack {


    WeatherDataPresenter presenter;
    WeatherApiCallModel model;
    Context mContext;
    Button btnShowWeather;
    Group groupView;
    ProgressBar progressBar;
    TextView tv_date_time, tv_temperature,tv_city_country, tv_weather_condition,tv_error_message,tv_humidity_value,tv_pressure_value,
            tv_visibility_value, iv_sunrise, tv_sunrise_time, tv_sunset_label, tv_sunset_time;
    ImageView iv_weather_condition,iv_sunset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        model = new WeatherApiCallModel(mContext);
        presenter = new WeatherPresenter(this, model);
        initializeView(mContext);
        btnShowWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupView.setVisibility(View.GONE);
                presenter.fetchWeatherInfo("dhaka");
            }
        });
    }

    private void initializeView(Context mContext){

        btnShowWeather = findViewById(R.id.btn_view_weather);
        groupView = findViewById(R.id.output_group);
        progressBar = findViewById(R.id.progressBar);
        tv_error_message = findViewById(R.id.tv_error_message);
        tv_date_time = findViewById(R.id.tv_date_time);
        tv_temperature = findViewById(R.id.tv_temperature);
        tv_city_country = findViewById(R.id.tv_city_country);
        tv_weather_condition = findViewById(R.id.tv_weather_condition);
        tv_humidity_value = findViewById(R.id.tv_humidity_value);
        tv_pressure_value = findViewById(R.id.tv_pressure_value);
        tv_visibility_value = findViewById(R.id.tv_visibility_value);
        tv_sunrise_time = findViewById(R.id.tv_sunrise_time);
        tv_sunset_time = findViewById(R.id.tv_sunset_time);
        iv_weather_condition = findViewById(R.id.iv_weather_condition);


    }

    @Override
    public void handleProgressBarVisibility(int visibility) {
            progressBar.setVisibility(visibility);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onWeatherInfoFetchSuccess(Object responsedata) {
        tv_error_message.setVisibility(View.GONE);
        groupView.setVisibility(View.VISIBLE);

        Log.e("data", new Gson().toJson(responsedata));

        WeatherBase weatherBase = (WeatherBase) responsedata;
        tv_date_time.setText(DataFormation.unixTimestampToDateTimeString(weatherBase.getDt())+"");
        tv_temperature.setText(DataFormation.kelvinToCelsius(weatherBase.getMain().getTemp())+"");
        tv_weather_condition.setText(weatherBase.getWeather().get(0).getDescription()+"");
        tv_humidity_value.setText(weatherBase.getMain().getHumidity()+"");
        tv_pressure_value.setText(weatherBase.getMain().getPressure()+"");
        tv_visibility_value.setText(weatherBase.getVisibility()+"");
        tv_sunrise_time.setText(DataFormation.unixTimestampToTimeString(weatherBase.getSys().getSunrise())+"");
        tv_sunset_time.setText(DataFormation.unixTimestampToTimeString(weatherBase.getSys().getSunset())+"");


    }

    @Override
    public void onWeatherInfoFetchFailure(String errMsg) {
        Log.e("error", errMsg+"");
        groupView.setVisibility(View.GONE);
        tv_error_message.setVisibility(View.VISIBLE);
        tv_error_message.setText(errMsg+"");

    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
