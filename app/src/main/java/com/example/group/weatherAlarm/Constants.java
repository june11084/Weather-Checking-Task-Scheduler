package com.example.group.weatherAlarm;

import com.example.group.weatherAlarm.BuildConfig;

import java.util.ArrayList;

public class Constants {
    public static final String OPENWEATHER_TOKEN = BuildConfig.OPENWEATHER_TOKEN;
    public static final String OPENWEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    public static final String OPENWEATHER_ZIP_QUERY_PARAMETER = "zip";
    public static final String OPENWEATHER_API_KEY_QUERY_PARAMETER = "appid";
    public static final String PREFERENCES_LOCATION_KEY = "location";
    public static final String FIREBASE_CHILD_TASKS = " tasks";
}
