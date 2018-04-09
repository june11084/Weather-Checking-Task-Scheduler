package com.example.group.weatherAlarm.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.group.weatherAlarm.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksPageFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.addAlarmFa) FloatingActionButton mAddAlarmFa;
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static TasksPageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        TasksPageFragment fragment = new TasksPageFragment();
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

    public void showTimePickerDialog(View view) {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.show(getChildFragmentManager(), "timePicker");
    }

}
