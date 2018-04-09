package com.example.group.weatherAlarm.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.group.weatherAlarm.R;
import com.example.group.weatherAlarm.models.TaskModel;
import com.example.group.weatherAlarm.util.ItemTouchHelperAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseTaskListAdapter extends FirebaseRecyclerAdapter<TaskModel, FirebaseTaskViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private View.OnDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<TaskModel> mTasks = new ArrayList<>();

    public FirebaseTaskListAdapter(FirebaseRecyclerOptions<TaskModel> options,
                                         Query ref, View.OnDragListener onStartDragListener, Context context) {
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mTasks.add(dataSnapshot.getValue(TaskModel.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mTasks, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mTasks.remove(position);
        getRef(position).removeValue();
    }

    @Override
    protected void onBindViewHolder(final FirebaseTaskViewHolder holder, int position, TaskModel model) {
        holder.bindTask(model);

//        mOrientation = holder.itemView.getResources().getConfiguration().orientation;
//        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
//            createDetailFragment(0);
//        }
    }

    @Override
    public FirebaseTaskViewHolder onCreateViewHolder (ViewGroup parent,int viewType){

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_list_item, parent, false);

        return new FirebaseTaskViewHolder(view);
    }

    public void setIndexInFirebase() {
        for (TaskModel restaurant : mTasks) {
            int index = mTasks.indexOf(restaurant);
            DatabaseReference ref = getRef(index);
            ref.child("index").setValue(Integer.toString(index));
        }
    }

//    private void createDetailFragment(int position) {
//        // Creates new RestaurantDetailFragment with the given position:
//        RestaurantDetailFragment detailFragment = RestaurantDetailFragment.newInstance(mTasks, position, Constants.SOURCE_SAVED);
//        // Gathers necessary components to replace the FrameLayout in the layout with the RestaurantDetailFragment:
//        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
//        //  Replaces the FrameLayout with the RestaurantDetailFragment:
//        ft.replace(R.id.restaurantDetailContainer, detailFragment);
//        // Commits these changes:
//        ft.commit();
//    }
}
