package com.and3r.circularswipeview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.EventLog;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class CircularSwipeView extends FrameLayout{


    private MotionEvent lastEvent;
    private double previousAngle;
    private int centerX;
    private int centerY;
    private int smallestSize;

    private boolean possibleScroll;

    private int scrollSize = 45;
    private double startDistance;

    private float startX;
    private float startY;
    private long startTime;


    private Runnable longClickRunnable;

    private double totalScroll;




    public CircularSwipeView(Context context) {
        super(context);
        init();
    }

    public CircularSwipeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircularSwipeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CircularSwipeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        setEnabled(false);
        longClickRunnable = new Runnable() {
            @Override
            public void run() {
                handleLongClick();
            }
        };
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w/2;
        centerY = h/2;
        smallestSize = w;
        if (h < w){
            smallestSize = h;
        }
    }

    @Override
    public void scrollBy(int x, int y) {
        int size = getChildCount();
        for (int i=0; i<size; i++){
            getChildAt(i).scrollBy(x, y);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        lastEvent = event;
            switch (event.getActionMasked()){
                case MotionEvent.ACTION_DOWN:
                    handleActionDown(event);
                    break;
                case MotionEvent.ACTION_MOVE:
                    return handleActionMove(event);
                case MotionEvent.ACTION_UP:
                    handleActionUp(event);
                    handleActionUpOrCancel();
                    break;
                case MotionEvent.ACTION_CANCEL:
                    handleActionUpOrCancel();
                    break;
            }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        lastEvent = event;
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                handleActionDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                handleActionMove(event);
                break;
            case MotionEvent.ACTION_UP:
                handleActionUp(event);
                handleActionUpOrCancel();
                break;
            case MotionEvent.ACTION_CANCEL:
                handleActionUpOrCancel();
                break;
        }
        return true;
    }

    private void handleActionDown(MotionEvent event){
        startX = event.getX();
        startY = event.getY();
        startTime = System.currentTimeMillis();
        previousAngle = getAngle(event);
        double distance = distanceToCenter(event);
        if (distance > (smallestSize/2-scrollSize)){
            startDistance = distance;
            possibleScroll = true;
        }
        postDelayed(longClickRunnable, 1000);
    }

    private boolean handleActionMove(MotionEvent event){
        if (possibleScroll){
            double difference = getAngleDifference(getAngle(event));
            totalScroll += difference;
            scrollBy(0, (int)(difference*100));
            previousAngle = getAngle(event);
            if (Math.abs(totalScroll) > 5){
                return true;
            }else if (startDistance - distanceToCenter(event) > 15){
                possibleScroll = false;
            }
        }
        return false;
    }

    private boolean handleLongClick(){
        if (lastEvent != null &&
                (lastEvent.getActionMasked() != MotionEvent.ACTION_DOWN ||
                lastEvent.getActionMasked() != MotionEvent.ACTION_MOVE) &&
                Math.abs(startX-lastEvent.getX()) < 10 &&
                Math.abs(startY- lastEvent.getY()) <10 &&
                System.currentTimeMillis()-startTime > 1000){
            performLongClick();
            return true;
        }else{
            return false;
        }
    }

    private void handleActionUp(MotionEvent event){
        if (Math.abs(startX-event.getX()) < 10 && Math.abs(startY- event.getY()) <10
            & hasOnClickListeners() && System.currentTimeMillis()-startTime < 1000){
                performClick();
        }
    }

    private void handleActionUpOrCancel(){
        removeCallbacks(longClickRunnable);
        possibleScroll = false;
        totalScroll = 0;
        int size = getChildCount();
        for (int i=0; i<size; i++){
            View child = getChildAt(i);
            if (child instanceof ScrollFinishedListener){
                ((ScrollFinishedListener) child).onScrollFinished();
            }
        }
    }

    /**
     * Returns the difference between the previous angle and the newAngle
     * @param newAngle new angle value
     * @return Difference between the previous angle and the newAngle
     */
    private double getAngleDifference(double newAngle){
        double angle;
        if (Math.abs(newAngle - previousAngle) < 90){
            angle = newAngle-previousAngle;
        }else{
            angle = ((newAngle + 180) % 360) - ((previousAngle + 180) % 360);
        }
        return angle;
    }

    /**
     * Get the angle between the central point of this view and the point from the event.
     * The value will be always positive (0-360)
     * @param event The event to get the point
     * @return The angle between the central point of this view and the point from the event
     */
    private double getAngle(MotionEvent event){
        double angle = Math.atan2(event.getY()-centerY, event.getX()-centerX);
        angle = Math.toDegrees(angle);
        if (angle < 0){
            angle = 360 + angle;
        }
        return angle;
    }

    /**
     * Returns the distance from the event to the center of the view
     * @param event
     * @return Distance in pixels from the event position to the center of the view
     */
    private double distanceToCenter(MotionEvent event){
        double a = Math.pow(event.getX() - centerX, 2);
        double b = Math.pow(event.getY() - centerY, 2);
        return Math.sqrt(a+b);
    }
}
