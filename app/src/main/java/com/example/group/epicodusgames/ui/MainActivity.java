package com.example.group.epicodusgames.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.epicodusgames.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyActivity";
    @BindView(R.id.playGames) Button mPlayGames;
    @BindView(R.id.checkWeather) Button mCheckWeather;
    @BindView(R.id.welcome) TextView mWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPlayGames.setOnClickListener(this);
        mCheckWeather.setOnClickListener(this);
        Typeface cursive = Typeface.createFromAsset(getAssets(), "fonts/cursive.ttf");
        mWelcome.setTypeface(cursive);
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
        }
    }
}
