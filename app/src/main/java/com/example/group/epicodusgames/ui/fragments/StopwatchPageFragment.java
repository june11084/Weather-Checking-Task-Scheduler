package com.example.group.epicodusgames.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.group.epicodusgames.R;

/**
 * Created by june1 on 3/29/2018.
 */

public class StopwatchPageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static StopwatchPageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        StopwatchPageFragment fragment = new StopwatchPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alarm_fragment_page, container, false);
        TextView textView = (TextView) view;
        textView.setText("Fragment 3" + mPage);
        return view;
    }
}
