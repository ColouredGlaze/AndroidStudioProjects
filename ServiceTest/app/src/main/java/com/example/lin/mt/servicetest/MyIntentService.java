package com.example.lin.mt.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 *
 * @author MT-Lin
 * @date 2017/12/19
 */

public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MyIntentService() {
        super(TAG);
        Log.d(TAG, "MyIntentService: ");
    }


    /**
     * 该方法是在子线程中运行的，不必担心ANR（Application Not Responding）问题
     * @param intent
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // 打印当前线程的id
        Log.d(TAG, "onHandleIntent: Thread id is " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
