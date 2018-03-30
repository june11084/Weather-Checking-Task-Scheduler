package com.example.group.weatherAlarm.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.group.weatherAlarm.R;
import com.example.group.weatherAlarm.services.WeatherService;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyActivity";
    @BindView(R.id.playGames) Button mPlayGames;
    @BindView(R.id.checkWeather) Button mCheckWeather;
    @BindView(R.id.welcome) TextView mWelcome;
    @BindView(R.id.weatherATM) TextView mWeatherATM;

    String weather = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPlayGames.setOnClickListener(this);
        mCheckWeather.setOnClickListener(this);
        mWeatherATM.setOnClickListener(this);
        Typeface cursive = Typeface.createFromAsset(getAssets(), "fonts/cursive.ttf");
        mWelcome.setTypeface(cursive);
        getWeather("97210");
    }

    @Override
    public void onClick(View v) {
        if(v == mPlayGames){
            Intent intent = new Intent(MainActivity.this, SelectGameActivity.class);
            intent.putExtra("message", "Game List");
            startActivity(intent);
        } else if (v == mCheckWeather){
            Intent intent = new Intent(MainActivity.this, WeatherAlarmClockActivity.class);
            startActivity(intent);
        } else if (v == mWeatherATM){
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://openweathermap.org/city/5746545"));
            startActivity(webIntent);
        }
    }

    private void getWeather(String zip) {
        final WeatherService weatherService = new WeatherService();
        weatherService.findWeather(zip, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                weather = weatherService.processResults(response);
                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mWeatherATM.setText(weather);
                    }
                });
            }
        });
    }
}
