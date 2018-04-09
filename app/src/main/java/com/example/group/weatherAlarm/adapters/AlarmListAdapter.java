package com.example.group.weatherAlarm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.example.group.weatherAlarm.models.TaskModel;
import java.util.ArrayList;
import butterknife.ButterKnife;

public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.AlarmViewHolder>{
    private ArrayList<TaskModel> mAlarms = new ArrayList<>();
    private Context mContext;

    public AlarmListAdapter(Context context, ArrayList<TaskModel> alarms){
        mAlarms = alarms;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public AlarmListAdapter.AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AlarmListAdapter.AlarmViewHolder holder, int position) {
        holder.bindAlarm(mAlarms.get(position));
    }

    @Override
    public int getItemCount() {
        return mAlarms.size();
    }

    public class AlarmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       // @BindView(R.id.restaurantImageView) ImageView mRestaurantImageView;

        private Context mContext;

        @Override
        public void onClick(View v) {
        }

        public AlarmViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindAlarm(TaskModel alarm) {
        }
    }
}
