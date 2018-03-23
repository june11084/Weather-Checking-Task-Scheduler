package com.example.group.epicodusgames.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.group.epicodusgames.R;
import com.example.group.epicodusgames.adapters.AlarmClockAdapter;
import com.example.group.epicodusgames.services.WeatherService;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherAlarmClockActivity extends AppCompatActivity {
    @BindView(R.id.viewpager) ViewPager mViewPager;
    @BindView(R.id.sliding_tabs) TabLayout MSliding_Tabs;
    String weather = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_alarm_clock);
        ButterKnife.bind(this);

        mViewPager.setAdapter(new AlarmClockAdapter(getSupportFragmentManager(),
                WeatherAlarmClockActivity.this));
        MSliding_Tabs.setupWithViewPager(mViewPager);
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
                WeatherAlarmClockActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                    }
                });
            }
        });
    }
}
