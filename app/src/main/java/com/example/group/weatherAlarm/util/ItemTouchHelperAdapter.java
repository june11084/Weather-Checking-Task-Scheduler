package com.example.group.weatherAlarm.util;

/**
 * Created by june1 on 4/8/2018.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
