package com.example.group.weatherAlarm.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group.weatherAlarm.Constants;
import com.example.group.weatherAlarm.R;
import com.example.group.weatherAlarm.adapters.FirebaseTaskListAdapter;
import com.example.group.weatherAlarm.models.TaskModel;
import com.example.group.weatherAlarm.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksPageFragment extends Fragment implements View.OnClickListener, View.OnDragListener {
    @BindView(R.id.addAlarmFa) FloatingActionButton mAddAlarmFa;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private DatabaseReference mRestaurantReference;
    private FirebaseTaskListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private Query taskQuery;

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
        View view = inflater.inflate(R.layout.fragment_tasks_page, container, false);
        ButterKnife.bind(this, view);
        mAddAlarmFa.setOnClickListener(this);
        setUpFirebaseAdapter();
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == mAddAlarmFa){
            showTimePickerDialog(v);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.setIndexInFirebase();
        mFirebaseAdapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        return false;
    }

    public void showTimePickerDialog(View view) {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.show(getChildFragmentManager(), "timePicker");
    }

    public void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mRestaurantReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_TASKS)
                .child(uid);

        taskQuery = mRestaurantReference.getRef().orderByChild(Constants.FIREBASE_QUERY_INDEX);
        FirebaseRecyclerOptions tasksOptions = new FirebaseRecyclerOptions.Builder<TaskModel>().setQuery(taskQuery,
                TaskModel.class).build();

        mFirebaseAdapter = new FirebaseTaskListAdapter(tasksOptions,taskQuery, this,getActivity());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mFirebaseAdapter.notifyDataSetChanged();
            }
        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

}
