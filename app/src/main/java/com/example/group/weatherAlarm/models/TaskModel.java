package com.example.group.weatherAlarm.models;

/**
 * Created by june1 on 3/30/2018.
 */

public class TaskModel {
    String task;
    String pushId;
    public TaskModel(){};
    public TaskModel(String task){
        this.task = task;
    }

    public String getTask(){
        return task;
    }
    public String getPushId() {
        return pushId;
    }
    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
