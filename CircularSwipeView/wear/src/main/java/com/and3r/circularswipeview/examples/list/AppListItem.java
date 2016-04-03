package com.and3r.circularswipeview.examples.list;

import android.content.Context;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.and3r.circularswipeview.examples.R;


/**
 * Created by ander on 2016/03/26.
 */
public class AppListItem extends LinearLayout implements WearableListView.OnCenterProximityListener{

    private ImageView mCircle;
    private TextView mName;

    private float mFadedTextAlpha;
    private int mFadedCircleColor;
    private int mChosenCircleColor;

    public AppListItem(Context context) {
        super(context);
    }

    public AppListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AppListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AppListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mFadedTextAlpha = 80/ 100f;
        //mFadedCircleColor = getResources().getColor(R.color.grey);
        //mChosenCircleColor = getResources().getColor(R.color.blue);
    }

    // Get references to the icon and text in the item layout definition
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // These are defined in the layout file for list items
        // (see next section)
        mCircle = (ImageView) findViewById(R.id.imageView);
        mName = (TextView) findViewById(R.id.textView);
    }

    @Override
    public void onCenterPosition(boolean animate) {
        //mName.setAlpha(1f);
        //((GradientDrawable) mCircle.getDrawable()).setColor(mChosenCircleColor);
    }

    @Override
    public void onNonCenterPosition(boolean animate) {
        //((GradientDrawable) mCircle.getDrawable()).setColor(mFadedCircleColor);
        //mName.setAlpha(mFadedTextAlpha);
    }
}
