package com.and3r.circularswipeview;

import android.support.v7.widget.RecyclerView;

/**
 * Created by ander on 2016/04/03.
 */
public class CircularLayoutManager extends RecyclerView.LayoutManager {

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }
}
