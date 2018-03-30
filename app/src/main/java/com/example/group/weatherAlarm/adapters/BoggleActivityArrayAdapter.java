package com.example.group.weatherAlarm.adapters;


import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class BoggleActivityArrayAdapter extends ArrayAdapter{
    private Context mContext;
    private ArrayList<String> mGames = new ArrayList<>();

    public BoggleActivityArrayAdapter(Context mContext, int resource, ArrayList<String> mGames) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mGames = mGames;
    }

    @Override
    public Object getItem(int position) {
        String games = mGames.get(position);
        return games;
    }

    @Override
    public int getCount() {
        return mGames.size();
    }
}
