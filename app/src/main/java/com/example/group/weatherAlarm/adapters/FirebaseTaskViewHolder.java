package com.example.group.weatherAlarm.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.group.weatherAlarm.R;
import com.example.group.weatherAlarm.models.TaskModel;
import com.example.group.weatherAlarm.util.ItemTouchHelperViewHolder;

public class FirebaseTaskViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    View mView;
    Context mContext;

    public FirebaseTaskViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    @Override
    public void onItemSelected() {
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }

    public void bindTask(TaskModel task) {
       TextView mTaskItem = (TextView) mView.findViewById(R.id.task);
        mTaskItem.setText(task.getTask());
    }
}
