package com.example.lin.mt.broadcastbestpractice;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    private Button btnForceOffline;

    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnForceOffline = findViewById(R.id.force_offline);
        btnForceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLocal = new Intent("com.example.broadcast.FORCE_OFFLINE_LOCAL");
                localBroadcastManager = LocalBroadcastManager.getInstance(MainActivity.this);
                // 有使用到弹对话框的不能使用本地广播，原因看代码：LocalBroadcastManager.getInstance(Context context)
                // localBroadcastManager.sendBroadcast(intentLocal);
                Intent intent = new Intent("com.example.broadcast.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }
}
