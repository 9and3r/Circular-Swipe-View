package com.and3r.circularswipeview.examples.list;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ander on 2016/04/03.
 */
public class BaseListActivity extends Activity{

    protected PackageManager manager;
    protected ArrayList<AppInfo> apps;

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
