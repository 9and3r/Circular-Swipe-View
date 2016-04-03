package com.and3r.circularswipeview;

import android.content.Context;
import android.os.Handler;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ander on 2016/03/28.
 */
public class CircularSwipeWearableListView extends WearableListView implements ScrollFinishedListener, WearableListView.OnCentralPositionChangedListener {

    private int centralPosition;

    public CircularSwipeWearableListView(Context context) {
        super(context);
        init();
    }

    public CircularSwipeWearableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircularSwipeWearableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        addOnCentralPositionChangedListener(this);
    }

    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y/20);
    }

    @Override
    public void onScrollFinished() {
        smoothScrollToPosition(centralPosition);
    }

    @Override
    public void onCentralPositionChanged(int i) {
        centralPosition = i;
    }
}
