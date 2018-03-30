package com.example.group.epicodusgames.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.os.Bundle;

import com.example.group.epicodusgames.R;
import com.example.group.epicodusgames.adapters.WeatherAlarmClockAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAlarmClockActivity extends AppCompatActivity {
    @BindView(R.id.viewpager) ViewPager mViewPager;
    @BindView(R.id.sliding_tabs) TabLayout MSliding_Tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_alarm_clock);
        ButterKnife.bind(this);

        mViewPager.setAdapter(new WeatherAlarmClockAdapter(getSupportFragmentManager(),
                WeatherAlarmClockActivity.this));
        MSliding_Tabs.setupWithViewPager(mViewPager);
    }
}
