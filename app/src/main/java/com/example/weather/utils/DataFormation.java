package com.example.weather.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class DataFormation {
    public static String unixTimestampToDateTimeString(int time) {

        Date date = new Date(time * 1000L);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getDefault());

        return sdf.format(date);
    }

    public static int kelvinToCelsius(double kelvin) {

        return (int) (kelvin - 273.15);
    }

    public static String unixTimestampToTimeString(int times) {

        Date date = new Date(1000L * times);

        SimpleDateFormat outputDateFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
        outputDateFormat.setTimeZone(TimeZone.getDefault());
        return outputDateFormat.format(date);
    }
}
