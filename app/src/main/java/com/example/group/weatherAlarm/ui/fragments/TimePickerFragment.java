package com.example.group.weatherAlarm.ui.fragments;


import android.app.Dialog;


import android.app.TimePickerDialog;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;


import com.example.group.weatherAlarm.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimePickerFragment extends DialogFragment{
    @BindView(R.id.timePicker)TimePicker mTimerPicker;
    @BindView(R.id.task)EditText mTask;
    @BindView(R.id.addTask)Button mAddTask;
    public TimePickerFragment() {}

    public static TimePickerFragment newInstance(String title) {
        TimePickerFragment frag = new TimePickerFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fetch arguments from bundle and set title
        //String title = getArguments().getString("title", "Enter Name");
        //getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        //mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

}
