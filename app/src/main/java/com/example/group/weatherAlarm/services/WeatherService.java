package com.example.group.weatherAlarm.services;

import android.util.Log;

import com.example.group.weatherAlarm.Constants;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherService {
    public static void findWeather(String zip, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.OPENWEATHER_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.OPENWEATHER_ZIP_QUERY_PARAMETER, zip);
        urlBuilder.addQueryParameter(Constants.OPENWEATHER_API_KEY_QUERY_PARAMETER, Constants.OPENWEATHER_TOKEN);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
        Log.v("URL",url);
    }

    public String getWeather(Response response){
        String weather = null;
        try {
            String jsonData = response.body().string();
            JSONObject OpenWeatherJSON = new JSONObject(jsonData);
            JSONArray currentWeatherJSON = OpenWeatherJSON.getJSONArray("weather");
            JSONObject weatherNowJSON = currentWeatherJSON.getJSONObject(0);
            weather = weatherNowJSON.getString("description");

        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return weather;
    }

    public String getLocation(Response response){
        String location = null;
        try {
            String jsonData = response.body().string();
            Log.v("json", jsonData);
            JSONObject OpenWeatherJSON = new JSONObject(jsonData);

            location = OpenWeatherJSON.getString("name");

        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return location;
    }
}
