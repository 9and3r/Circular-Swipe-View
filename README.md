# Circular Swipe View

Circular Swipe View is a view for Android Wear.

Android wear watches have little screen. Because of that, little swipes must be done to scroll through a list. To avoid that this view allows to scroll making circular swipes in the edge of the watch doing one continuous swipe.

You can [download the sample apk](https://github.com/9and3r/Circular-Swipe-View/raw/master/CircularSwipeView/app/app-release.apk), install it on your phone and open the companion app. You will find two examples:
- Scrolling a list
- Zoom in a map

Sample video: https://youtu.be/QmUGJvLbpc4

## Installation ##

In the future this will be improved but currently you can simply copy the files directly from the [library source](https://github.com/9and3r/Circular-Swipe-View/tree/master/CircularSwipeView/library/src/main/java/com/and3r/circularswipeview).

## How to use ##

Add [CircularSwipeView](https://github.com/9and3r/Circular-Swipe-View/blob/master/CircularSwipeView/library/src/main/java/com/and3r/circularswipeview/CircularSwipeView.java) into your layout:
```XML
<com.and3r.circularswipeview.CircularSwipeView
            xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="@color/white"
            android:id="@+id/circular_swipe_view">
            
            <!-- Add your childs here -->
            
</com.and3r.circularswipeview.CircularSwipeView>
```
[CircularSwipeView](https://github.com/9and3r/Circular-Swipe-View/blob/master/CircularSwipeView/library/src/main/java/com/and3r/circularswipeview/CircularSwipeView.java) extends [FrameLayout](http://developer.android.com/reference/android/widget/FrameLayout.html). Add childs to it. The childs will get a call to [scrollBy(int,int)](http://developer.android.com/reference/android/view/View.html#scrollBy(0, scrolledAngle)) anytime the user scrolls where scrolledAngle is the scrolled angle in degrees*100 since last call. For example if a user scrolled 13.25 degrees the childs will get scrollBy(0, 1325).

You can also set a [clickListener](http://developer.android.com/reference/android/view/View.html#setOnClickListener(android.view.View.OnClickListener)) or/and a [longClickListener](http://developer.android.com/reference/android/view/View.html#setOnLongClickListener(android.view.View.OnLongClickListener)):

There is a [WearableListView](https://github.com/9and3r/Circular-Swipe-View/blob/master/CircularSwipeView/library/src/main/java/com/and3r/circularswipeview/CircularSwipeWearableListView.java) ready to use with CircularSwipeView. To use it simply put a [CircularSwipeWearableListView](https://github.com/9and3r/Circular-Swipe-View/blob/master/CircularSwipeView/library/src/main/java/com/and3r/circularswipeview/CircularSwipeWearableListView.java) inside a CircularSwipeView:
```XML
<com.and3r.circularswipeview.CircularSwipeView
            xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="@color/white"
            android:id="@+id/circular_swipe_view">

            <com.and3r.circularswipeview.CircularSwipeWearableListView
                android:id="@+id/wearable_list"
                android:layout_height="match_parent"
                android:layout_width="match_parent">
            </com.and3r.circularswipeview.CircularSwipeWearableListView>
</com.and3r.circularswipeview.CircularSwipeView>
```
## License ##

```
Copyright 2016 Ander Orbegozo

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
