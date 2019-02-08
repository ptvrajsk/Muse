package com.personal.prithivi.muse;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerView_ItemClickListener_Song implements RecyclerView.OnItemTouchListener {

    OnItemClickListener mListener;
    GestureDetector mDetector;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }


    public RecyclerView_ItemClickListener_Song(Context context, OnItemClickListener listener) {
        this.mListener = listener;

        mDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View childView = rv.findChildViewUnder(e.getX(), e.getY());

        if (childView != null && mListener !=null && mDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
            return true;
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
