package com.and3r.circularswipeview.examples.map;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

public class CustomMapView extends MapView implements OnMapReadyCallback {

    private GoogleMap map;

    public CustomMapView(Context context) {
        super(context);
    }

    public CustomMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomMapView(Context context, GoogleMapOptions options) {
        super(context, options);
    }

    @Override
    public void scrollBy(int x, int y) {
        if (map != null){
            float zoom = y / 100f / 15;
            map.moveCamera(CameraUpdateFactory.zoomBy(zoom));
        }
    }

    public void createMap(Bundle b){
        onCreate(b);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }
}
