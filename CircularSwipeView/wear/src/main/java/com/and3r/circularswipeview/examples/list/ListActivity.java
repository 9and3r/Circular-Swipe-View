package com.and3r.circularswipeview.examples.list;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.view.View;


import com.and3r.circularswipeview.examples.R;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends BaseListActivity {



    private WearableListView wearableListView;

    private int centralPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list);

        wearableListView = (WearableListView) findViewById(R.id.wearable_list);
        loadApps();

        final AppListAdapter adapter = new AppListAdapter(this, apps);
        wearableListView.setAdapter(adapter);

        wearableListView.addOnCentralPositionChangedListener(new WearableListView.OnCentralPositionChangedListener() {
            @Override
            public void onCentralPositionChanged(int i) {
                centralPosition = i;
            }
        });

        findViewById(R.id.circular_swipe_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = manager.getLaunchIntentForPackage(apps.get(centralPosition).name);
                startActivity(i);
            }
        });


    }



}
