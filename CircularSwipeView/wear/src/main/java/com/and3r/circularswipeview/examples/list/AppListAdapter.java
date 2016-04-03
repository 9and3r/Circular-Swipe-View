package com.and3r.circularswipeview.examples.list;

import android.content.Context;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.and3r.circularswipeview.examples.R;

import java.util.ArrayList;

/**
 * Created by ander on 2016/03/26.
 */
public class AppListAdapter extends WearableListView.Adapter {

    private ArrayList<AppInfo> apps;
    private final LayoutInflater mInflater;

    public AppListAdapter(Context context, ArrayList<AppInfo> pApps){
        mInflater = LayoutInflater.from(context);
        apps = pApps;
    }

    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(mInflater.inflate(R.layout.layout_app_item, null));
    }

    @Override
    public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.imageView.setImageDrawable(apps.get(position).icon);
        itemViewHolder.textView.setText(apps.get(position).label);
    }



    @Override
    public int getItemCount() {
        return apps.size();
    }


    public static class ItemViewHolder extends WearableListView.ViewHolder {

        public TextView textView;
        public ImageView imageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            // find the text view within the custom item's layout
            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

}
