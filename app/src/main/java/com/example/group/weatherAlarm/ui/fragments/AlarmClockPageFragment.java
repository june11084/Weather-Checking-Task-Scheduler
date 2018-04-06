package com.example.group.weatherAlarm.ui.fragments;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.example.group.weatherAlarm.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlarmClockPageFragment extends Fragment implements TimePickerDialog.OnTimeSetListener,View.OnClickListener {
    @BindView(R.id.addAlarmFa) FloatingActionButton mAddAlarmFa;
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static AlarmClockPageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        AlarmClockPageFragment fragment = new AlarmClockPageFragment();
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
        View view = inflater.inflate(R.layout.fragment_alarm_page, container, false);
        ButterKnife.bind(this, view);
        mAddAlarmFa.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == mAddAlarmFa){
            showTimePickerDialog(v);
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }

    public void showTimePickerDialog(View view) {
        TimePickerFragment newFragment = new TimePickerFragment();
        //newFragment.show(getChildFragmentManager(), "timePicker");
        FragmentManager fragment = getFragmentManager();
        newFragment.show(fragment, "timePicker");
    }

}
