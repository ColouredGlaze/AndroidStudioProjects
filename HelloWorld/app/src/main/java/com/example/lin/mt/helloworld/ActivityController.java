package com.example.lin.mt.helloworld;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MT-Lin on 2017/12/13.
 */

public class ActivityController {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
