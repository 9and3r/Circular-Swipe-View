package com.and3r.circularswipeview.examples.list;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.view.View;


import com.and3r.circularswipeview.CircularSwipeView;
import com.and3r.circularswipeview.examples.BaseDismissActivity;
import com.and3r.circularswipeview.examples.R;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends BaseDismissActivity {

    private WearableListView wearableListView;
    protected PackageManager manager;
    protected ArrayList<AppInfo> apps;

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

        CircularSwipeView circularSwipeView = (CircularSwipeView) findViewById(R.id.circular_swipe_view);
        circularSwipeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = manager.getLaunchIntentForPackage(apps.get(centralPosition).name);
                startActivity(i);
            }
        });
        circularSwipeView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mDismissOverlay.show();
                return true;
            }
        });

        afterOnCreate();


    }

    protected void loadApps(){
        manager = getPackageManager();
        apps = new ArrayList<>();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
        for(ResolveInfo ri:availableActivities){
            AppInfo appInfo = new AppInfo();
            appInfo.name = ri.activityInfo.packageName;
            appInfo.label = ri.loadLabel(manager);
            appInfo.icon = ri.activityInfo.loadIcon(manager);
            apps.add(appInfo);
        }
    }



}
