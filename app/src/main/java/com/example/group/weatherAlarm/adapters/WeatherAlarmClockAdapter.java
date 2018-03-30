package com.example.group.weatherAlarm.adapters;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.group.weatherAlarm.ui.fragments.TimerPageFragment;
import com.example.group.weatherAlarm.ui.fragments.AlarmClockPageFragment;
import com.example.group.weatherAlarm.ui.fragments.StopwatchPageFragment;


public class WeatherAlarmClockAdapter extends FragmentStatePagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Alarm", "Timer", "StopWatch" };
    private Context context;

    public WeatherAlarmClockAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = AlarmClockPageFragment.newInstance(position + 0);
                break;
            case 1:
                fragment = TimerPageFragment.newInstance(position + 1 );
                break;
            case 2:
                fragment = StopwatchPageFragment.newInstance(position +1);
                break;
        }
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}
