package com.mosl.tableview;

/**
 * Created by feat systems
 */

//This interface is the listener for TableFixHeaders scroll
public interface MyScrollListener {
    //this will be called on every scroll up and down
    //MotionEvent.ACTION_UP
    //MotionEvent.ACTION_DOWN
    void onScroll(int scrollDirection, int firstVisibleRow, int totalVisible);
}