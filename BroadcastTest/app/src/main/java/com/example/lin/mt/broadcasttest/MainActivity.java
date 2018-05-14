package com.example.lin.mt.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;

    class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }
    }

    private LocalReceiver localReceiver;

    private IntentFilter intentFilterLocal;

    private LocalBroadcastManager localBroadcastManager;

    class NetworkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context,
                        "network is available + " + networkInfo.getTypeName(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 监听网络状态发生变化发出的广播
    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        // 注册广播
        registerReceiver(networkChangeReceiver, intentFilter);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
                // 发送无序广播
                // sendBroadcast(intent);
                // 发送有序广播
                sendOrderedBroadcast(intent, null);
                Intent intentLocal = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
                // 发送本地广播
                localBroadcastManager.sendBroadcast(intentLocal);
            }
        });
        intentFilterLocal = new IntentFilter();
        intentFilterLocal.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        // 注册本地广播监听器
        localBroadcastManager.registerReceiver(localReceiver, intentFilterLocal);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }
}
