package com.example.group.epicodusgames.services;

import com.example.group.epicodusgames.Constants;



import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class WeatherService {
    public static void findWeather(String zip, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.OPENWEATHER_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.OPENWEATHER_ZIP_QUERY_PARAMETER, zip);
        urlBuilder.addQueryParameter(Constants.OPENWEATHER_API_KEY_QUERY_PARAMETER, Constants.OPENWEATHER_TOKEN);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .header("Authorization", Constants.OPENWEATHER_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
