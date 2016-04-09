package com.and3r.circularswipeview.examples.map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.and3r.circularswipeview.examples.BaseDismissActivity;
import com.and3r.circularswipeview.examples.R;


public class MapExampleActivity extends BaseDismissActivity {

    private CustomMapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_map);
        mapView = (CustomMapView) findViewById(R.id.map_view);
        mapView.createMap(savedInstanceState);

        findViewById(R.id.circular_swipe_view).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mDismissOverlay.show();
                return true;
            }
        });

        afterOnCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
