package com.example.group.weatherAlarm.ui.fragments;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.group.weatherAlarm.Constants;
import com.example.group.weatherAlarm.R;
import com.example.group.weatherAlarm.models.TaskModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimePickerFragment extends DialogFragment implements View.OnClickListener{
    @BindView(R.id.timePicker)TimePicker mTimerPicker;
    @BindView(R.id.task)EditText mTask;
    @BindView(R.id.addTask)Button mAddTask;
    public TimePickerFragment() {}
    private TaskModel mTaskModel;
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
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        ButterKnife.bind(this,view);
        mAddTask.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

    }

    @Override
    public void onClick(View v) {
        String timeSet = "";
        int hours = mTimerPicker.getCurrentHour();
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";
        if(v == mAddTask){
            Log.v("task", mTimerPicker.getCurrentHour() + ":" + mTimerPicker.getCurrentMinute() + ":" + timeSet + ":" + mTask.getText().toString());
            String task = mTimerPicker.getCurrentHour() + ":" + mTimerPicker.getCurrentMinute() + ":" + timeSet + ":" + mTask.getText().toString();
            mTaskModel = new TaskModel(task);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference restaurantRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_TASKS)
                    .child(uid);

            DatabaseReference pushRef = restaurantRef.push();
            String pushId = pushRef.getKey();
            mTaskModel.setPushId(pushId);
            pushRef.setValue(mTaskModel);
            mTask.getText().clear();
        }
    }
}
