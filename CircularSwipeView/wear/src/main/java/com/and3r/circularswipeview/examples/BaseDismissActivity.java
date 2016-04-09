package com.and3r.circularswipeview.examples;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.DismissOverlayView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.and3r.circularswipeview.examples.R;

/**
 * Created by ander on 2016/04/09.
 */
public abstract class BaseDismissActivity extends Activity{

    protected DismissOverlayView mDismissOverlay;


    protected void afterOnCreate(){
        mDismissOverlay = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        mDismissOverlay.setIntroText(R.string.long_press_intro);
        mDismissOverlay.showIntroIfNecessary();
    }



}
