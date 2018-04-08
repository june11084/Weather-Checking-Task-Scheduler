package com.example.group.weatherAlarm.models;

/**
 * Created by june1 on 3/30/2018.
 */

public class AlarmModel {
    String time;
    String pushId;
    public AlarmModel(String time){
        this.time = time;
    }

    public String getTime(){
        return time;
    }
    public String getPushId() {
        return pushId;
    }
    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
