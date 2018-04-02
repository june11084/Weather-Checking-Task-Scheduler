package com.example.group.weatherAlarm.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.group.weatherAlarm.Constants;
import com.example.group.weatherAlarm.R;
import com.example.group.weatherAlarm.services.WeatherService;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyActivity";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mSavedLocation;
    @BindView(R.id.playGames) Button mPlayGames;
    @BindView(R.id.checkWeather) Button mCheckWeather;
    @BindView(R.id.welcome) TextView mWelcome;
    @BindView(R.id.weatherATM) TextView mWeatherATM;
    @BindView(R.id.locationATM) TextView mLocationATM;

    String weather = null;
    String location = null;

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
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        mSavedLocation = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        getWeather(mSavedLocation);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        } else if (id == R.id.action_location){
            changeLocation();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getBaseContext(), LoginActivity.class));
        finish();
    }

    private void changeLocation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setTitle("Enter Zip Code:");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String newLocation = input.getText().toString();
                addToSharedPreferences(newLocation);
                getWeather(newLocation);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        builder.show();
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
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
                weather = weatherService.getWeather(response);
                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if(weather == null){
                            changeLocation();
                        }
                        mWeatherATM.setText(weather);
                    }
                });
            }
        });
        weatherService.findWeather(zip, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                location = weatherService.getLocation(response);
                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mLocationATM.setText(location+":");
                    }
                });
            }
        });
    }
}
