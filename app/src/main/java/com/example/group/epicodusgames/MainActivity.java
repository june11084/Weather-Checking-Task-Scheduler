package com.example.group.epicodusgames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyActivity";
    @BindView(R.id.playGames) Button mPlayGames;
    @BindView(R.id.checkWeather) Button mCheckWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPlayGames.setOnClickListener(this);
        mCheckWeather.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mPlayGames){
            Intent intent = new Intent(MainActivity.this, SelectGameActivity.class);
            intent.putExtra("message", "you are playing games");
            startActivity(intent);
        } else if (v == mCheckWeather){
            Toast.makeText(this,"Not Implemented",Toast.LENGTH_LONG).show();
        }
    }
}
