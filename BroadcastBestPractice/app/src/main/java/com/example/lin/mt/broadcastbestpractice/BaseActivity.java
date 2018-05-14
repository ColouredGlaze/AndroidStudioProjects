package com.example.lin.mt.broadcastbestpractice;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Map;

/**
 * Created by MT-Lin on 2017/12/13.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    private LocalBroadcastManager localBroadcastManager;

    private LocalOfflineBroadcastReceiver localOfflineBroadcastReceiver;

    private ForceOfflineBroadcastReceiver forceOfflineBroadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, getClass().getSimpleName());
        ActivityController.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilterLocal = new IntentFilter();
        intentFilterLocal.addAction("com.example.broadcast.FORCE_OFFLINE_LOCAL");
        localOfflineBroadcastReceiver = new LocalOfflineBroadcastReceiver();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(localOfflineBroadcastReceiver, intentFilterLocal);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcast.FORCE_OFFLINE");
        forceOfflineBroadcastReceiver = new ForceOfflineBroadcastReceiver();
        registerReceiver(forceOfflineBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != localOfflineBroadcastReceiver){
            localBroadcastManager.unregisterReceiver(localOfflineBroadcastReceiver);
            localOfflineBroadcastReceiver = null;
        }
        if (null != forceOfflineBroadcastReceiver){
            unregisterReceiver(forceOfflineBroadcastReceiver);
            forceOfflineBroadcastReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }
}
