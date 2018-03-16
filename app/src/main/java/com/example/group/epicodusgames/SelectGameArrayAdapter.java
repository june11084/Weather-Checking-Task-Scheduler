package com.example.group.epicodusgames;


import android.content.Context;
import android.widget.ArrayAdapter;

public class SelectGameArrayAdapter extends ArrayAdapter{
    private Context mContext;
    private String[] mGames;

    public SelectGameArrayAdapter(Context mContext, int resource, String[] mGames) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mGames = mGames;
    }

    @Override
    public Object getItem(int position) {
        String games = mGames[position];
        return games;
    }

    @Override
    public int getCount() {
        return mGames.length;
    }
}
